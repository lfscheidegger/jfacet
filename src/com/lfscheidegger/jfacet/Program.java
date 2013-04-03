package com.lfscheidegger.jfacet;

import android.opengl.GLES20;
import com.badlogic.gdx.backends.android.AndroidGL20;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.DefaultCompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.ShaderCompiler;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.attribute.Attribute;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

public class Program {

  private int mProgramHandle;

  private final CompilationContext mCompilationContext;

  private final Expression mPosition;
  private final Expression mFragColor;

  private AndroidGL20 mAndroidGL;

  private final Map<Expression, FloatBuffer> mAttributeMap;

  public Program(Expression position, Expression fragColor) {
    mCompilationContext = new DefaultCompilationContext();

    mPosition = position;
    mFragColor = fragColor;

    mAttributeMap = new HashMap<Expression, FloatBuffer>();
  }

  public void bake() {
    mAndroidGL = new AndroidGL20();

    ShaderCompiler vertexShaderCompiler = new ShaderCompiler(
        mCompilationContext,
        ImmutableMap.<String, Expression>of("gl_Position", mPosition));
    ShaderCompiler fragmentShaderCompiler = new ShaderCompiler(
        mCompilationContext,
        ImmutableMap.<String, Expression>of("gl_FragColor", mFragColor));

    String vertexShaderSource = vertexShaderCompiler.compile();
    String fragmentShaderSource = fragmentShaderCompiler.compile();

    int vertexShaderHandle = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
    int fragmentShaderHandle = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
    mProgramHandle = GLES20.glCreateProgram();

    compileShader(vertexShaderHandle, vertexShaderSource);
    compileShader(fragmentShaderHandle, fragmentShaderSource);

    GLES20.glAttachShader(mProgramHandle, vertexShaderHandle);
    GLES20.glAttachShader(mProgramHandle, fragmentShaderHandle);

    linkProgram();
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
      throw new RuntimeException("Error compiling program");
    }
  }

  private void linkProgram() {
    extractAttributes(mPosition);
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
      mAttributeMap.put(exp, ((Attribute)exp).getBuffer());
      return;
    }

    for (Expression parent : (ImmutableList<Expression>)exp.getParents()) {
      extractAttributes(parent);
    }
  }

  private void bindAttributeLocations() {
    Preconditions.checkState(mAttributeMap.size() <= GLES20.GL_MAX_VERTEX_ATTRIBS);

    int count = 0;
    for (Map.Entry<Expression, FloatBuffer> entry: mAttributeMap.entrySet()) {
      Expression expression = entry.getKey();
      Preconditions.checkState(expression instanceof Attribute);

      GLES20.glBindAttribLocation(mProgramHandle, count++, mCompilationContext.getExpressionName(expression));
    }
  }

  private void bindAttributes() {
    for (Map.Entry<Expression, FloatBuffer> entry: mAttributeMap.entrySet()) {
      int attribLocationHandle = GLES20.glGetAttribLocation(
          mProgramHandle,
          mCompilationContext.getExpressionName(entry.getKey()));
      Type type = entry.getKey().getType();
      FloatBuffer buffer = ((Attribute)entry.getKey()).getBuffer();
      int size = type.getDimension();
      GLES20.glVertexAttribPointer(attribLocationHandle, size, GLES20.GL_FLOAT, false, 0, buffer);
      GLES20.glEnableVertexAttribArray(attribLocationHandle);
    }
  }

  private void unbindAttributes() {
    for (Map.Entry<Expression, FloatBuffer> entry: mAttributeMap.entrySet()) {
      int attribLocationHandle = GLES20.glGetAttribLocation(
          mProgramHandle,
          mCompilationContext.getExpressionName(entry.getKey()));
      GLES20.glDisableVertexAttribArray(attribLocationHandle);
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
