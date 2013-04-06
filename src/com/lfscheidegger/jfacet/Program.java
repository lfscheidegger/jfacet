package com.lfscheidegger.jfacet;

import android.opengl.GLES20;
import com.badlogic.gdx.backends.android.AndroidGL20;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.compiler.*;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.attribute.Attribute;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

import java.nio.FloatBuffer;
import java.util.HashSet;
import java.util.Set;

public class Program {

  private int mProgramHandle;

  private final CompilationContext mCompilationContext;

  private final Expression mPosition;
  private final Expression mFragColor;

  private AndroidGL20 mAndroidGL;

  private final Set<Expression> mAttributeSet;

  // this parent obtainer cuts the DAG away from varying's parents, to make sure we don't look
  // at them when compiling a fragment shader.
  private final TopologicalSorter.ParentObtainer mVaryingAwareParentObtainer = new TopologicalSorter.ParentObtainer() {
    @Override
    public ImmutableList<Expression> getParents(Expression exp) {
      if (exp.getGlSlType() == GlSlType.VARYING_T) {
        return ImmutableList.of();
      }

      return exp.getParents();
    }
  };

  public Program(Expression position, Expression fragColor) {
    mCompilationContext = new DefaultCompilationContext();

    mPosition = Shade.fill(position, new Vec4(0, 0, 0, 1));
    mFragColor = Shade.fill(fragColor, new Vec4(0, 0, 0, 1));

    mAttributeSet = new HashSet<Expression>();
  }

  public void bake() {
    mAndroidGL = new AndroidGL20();

    FragmentShaderCompiler fragmentShaderCompiler = new FragmentShaderCompiler(
        ImmutableMap.<String, Expression>of("gl_FragColor", mFragColor),
        mCompilationContext);
    String fragmentShaderSource = fragmentShaderCompiler.compile();

    VertexShaderCompiler vertexShaderCompiler = new VertexShaderCompiler(
        getVertexShaderCompilationNames(fragmentShaderCompiler.getVaryingExpressions()),
        mCompilationContext);
    String vertexShaderSource = vertexShaderCompiler.compile();

    int vertexShaderHandle = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
    int fragmentShaderHandle = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
    mProgramHandle = GLES20.glCreateProgram();

    compileShader(vertexShaderHandle, vertexShaderSource);
    compileShader(fragmentShaderHandle, fragmentShaderSource);

    GLES20.glAttachShader(mProgramHandle, vertexShaderHandle);
    GLES20.glAttachShader(mProgramHandle, fragmentShaderHandle);

    linkProgram();
  }

  private ImmutableMap<String, Expression> getVertexShaderCompilationNames(ImmutableSet<Expression> varyings) {
    ImmutableMap.Builder<String, Expression> builder = new ImmutableBiMap.Builder<String, Expression>();

    for (Expression varying: varyings) {
      builder.put(mCompilationContext.getExpressionName(varying), varying);
    }

    builder.put("gl_Position", mPosition);
    return builder.build();
  }

  private void compileShader(int shaderHandle, String shaderSource) {
    GLES20.glShaderSource(shaderHandle, shaderSource);
    GLES20.glCompileShader(shaderHandle);

    // Get the compilation status.
    final int[] compileStatus = new int[1];
    GLES20.glGetShaderiv(shaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

    // If the compilation failed, delete the shader.
    if (compileStatus[0] == 0) {
      String compileLog = mAndroidGL.glGetShaderInfoLog(shaderHandle);
      System.err.println(compileLog);

      GLES20.glDeleteShader(shaderHandle);
      throw new RuntimeException("Error compiling program\n" + compileLog);
    }
  }

  private void linkProgram() {
    extractAttributes(mPosition);
    extractAttributes(mFragColor);
    bindAttributeLocations();

    GLES20.glLinkProgram(mProgramHandle);

    // Get the link status.
    final int[] linkStatus = new int[1];
    GLES20.glGetProgramiv(mProgramHandle, GLES20.GL_LINK_STATUS, linkStatus, 0);

    // If the link failed, delete the program.
    if (linkStatus[0] == 0) {
      String linkLog = mAndroidGL.glGetProgramInfoLog(mProgramHandle);
      System.err.println(linkLog);

      GLES20.glDeleteProgram(mProgramHandle);
      throw new RuntimeException("Error linking program");
    }
  }

  private void extractAttributes(Expression exp) {
    if (exp.getGlSlType() == GlSlType.ATTRIBUTE_T) {
      mAttributeSet.add(exp);
    }

    for (Expression parent : (ImmutableList<Expression>)exp.getParents()) {
      extractAttributes(parent);
    }
  }

  private void bindAttributeLocations() {
    Preconditions.checkState(mAttributeSet.size() <= GLES20.GL_MAX_VERTEX_ATTRIBS);

    int count = 0;
    for (Expression attribute: mAttributeSet) {
      GLES20.glBindAttribLocation(mProgramHandle, count++, mCompilationContext.getExpressionName(attribute));
    }
  }

  private void bindAttributes() {
    for (Expression attribute: mAttributeSet) {
      int attribHandle = GLES20.glGetAttribLocation(mProgramHandle, mCompilationContext.getExpressionName(attribute));
      FloatBuffer buffer = ((Attribute)attribute).getBuffer();
      int size = attribute.getType().getDimension();
      GLES20.glEnableVertexAttribArray(attribHandle);

      GLES20.glVertexAttribPointer(attribHandle, size, GLES20.GL_FLOAT, false, 0, buffer);
    }
  }

  private void unbindAttributes() {
    for (Expression attribute: mAttributeSet) {
      int attribHandle = GLES20.glGetAttribLocation(mProgramHandle, mCompilationContext.getExpressionName(attribute));
      GLES20.glDisableVertexAttribArray(attribHandle);
    }
  }

  public void use() {
    GLES20.glUseProgram(mProgramHandle);
    bindAttributes();
  }

  public void stopUsing() {
    unbindAttributes();
  }
}
