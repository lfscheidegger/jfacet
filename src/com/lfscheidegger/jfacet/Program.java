package com.lfscheidegger.jfacet;

import android.opengl.GLES20;
import com.badlogic.gdx.backends.android.AndroidGL20;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.lfscheidegger.jfacet.facet.AttribBuffer;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Parameter;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.DefaultCompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.FragmentShaderCompiler;
import com.lfscheidegger.jfacet.shade.compiler.VertexShaderCompiler;
import com.lfscheidegger.jfacet.shade.compiler.ast.ASTOptimizer;
import com.lfscheidegger.jfacet.shade.compiler.ast.ASTProcessors;
import com.lfscheidegger.jfacet.shade.compiler.ast.FragmentAttributeExtractor;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.SamplerExpression;
import com.lfscheidegger.jfacet.shade.expression.Vector4;
import com.lfscheidegger.jfacet.shade.expression.VectorExpression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.glsl.UniformEvaluator;

import java.util.HashSet;
import java.util.Set;

public final class Program {

  private int mProgramHandle;

  private final CompilationContext mCompilationContext;

  private final Expression mPosition;
  private final Expression mFragColor;

  private AndroidGL20 mAndroidGL;

  // Attribute data
  private final AttribBuffer[] mAttribBuffers;
  private final Expression[] mAttribExpressions;
  private final int[] mAttribLocations;

  // Uniform data
  private final Expression[] mUniformExpressions;
  private final int[] mUniformLocations;

  public Program(
      VectorExpression position,
      VectorExpression fragColor,
      ImmutableMap<AttribBuffer, Expression> attributeMap) {
    mCompilationContext = new DefaultCompilationContext();

    ASTOptimizer optimizer = new ASTOptimizer();

    //mPosition = optimizer.process(Shade.fill(position, new Vector(0, 0, 0, 1)));
    mPosition = optimizer.process(position.fill(Shade.vec(0, 0, 0, 1)));
    mFragColor = new ASTProcessors()
        .add(optimizer)
        .add(new FragmentAttributeExtractor())
        .process(fragColor.fill(Shade.vec(0, 0, 0, 1)));

    // initialize data for attributes
    int count = 0;
    mAttribBuffers = new AttribBuffer[attributeMap.size()];
    for (AttribBuffer attribBuffer: attributeMap.keySet()) {
      mAttribBuffers[count++] = attribBuffer;
    }
    mAttribExpressions = new Expression[attributeMap.size()];
    for (int i = 0; i < mAttribBuffers.length; i++) {
      mAttribExpressions[i] = attributeMap.get(mAttribBuffers[i]);
    }
    mAttribLocations = new int[attributeMap.size()];

    // initialize data for uniforms
    Set<Expression> uniformExpressions = new HashSet<Expression>();
    extractUniforms(mPosition, uniformExpressions);
    extractUniforms(mFragColor, uniformExpressions);

    mUniformExpressions = new Expression[uniformExpressions.size()];
    count = 0;
    for (Expression expression: uniformExpressions) {
      mUniformExpressions[count++] = expression;
    }
    mUniformLocations = new int[uniformExpressions.size()];
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
    bindAttributeLocations();

    GLES20.glLinkProgram(mProgramHandle);

    bindUniformLocations();
    loadTextures();

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

  private Set<Expression> extractUniforms(Expression exp, Set<Expression> existing) {
    if (exp.getGlSlType() == GlSlType.UNIFORM_T) {
      existing.add(exp);
      return existing;
    }

    for (Expression parent: (ImmutableList<Expression>)exp.getParents()) {
      extractUniforms(parent, existing);
    }

    return existing;
  }

  private void bindAttributeLocations() {
    for (int i = 0; i < mAttribExpressions.length; i++) {
      GLES20.glBindAttribLocation(mProgramHandle, i, mCompilationContext.getExpressionName(mAttribExpressions[i]));
      mAttribLocations[i] = i;
    }
  }

  private void bindUniformLocations() {
    for (int i = 0; i < mUniformExpressions.length; i++) {
      int location =
          GLES20.glGetUniformLocation(mProgramHandle, mCompilationContext.getExpressionName(mUniformExpressions[i]));
      mUniformLocations[i] = location;
    }
  }

  private void loadTextures() {
    for (int i = 0; i < mUniformExpressions.length; i++) {
      if (mUniformExpressions[i].getType() != Type.SAMPLER2D_T) {
        continue;
      }

      ((SamplerExpression)mUniformExpressions[i]).bake();
    }
  }

  private void bindAttributes() {
    for (int i = 0; i < mAttribExpressions.length; i++) {
      int attribHandle = mAttribLocations[i];
      AttribBuffer buffer = mAttribBuffers[i];
      int size = buffer.getDimension();

      GLES20.glVertexAttribPointer(attribHandle, size, GLES20.GL_FLOAT, false, 0, buffer.getBuffer());
      GLES20.glEnableVertexAttribArray(attribHandle);
    }
  }

  private void bindUniforms() {
    int textureUnitCounter = 0;

    for (int i = 0; i < mUniformLocations.length; i++) {
      Preconditions.checkState(textureUnitCounter < GLES20.GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS);

      int uniformHandle = mUniformLocations[i];

      UniformEvaluator evaluator = (UniformEvaluator)mUniformExpressions[i].getEvaluator();
      evaluator.refresh();
      switch(mUniformExpressions[i].getType()) {
        case FLOAT_T:
          GLES20.glUniform1f(uniformHandle, (Float)evaluator.get()); break;
        case VEC2_T:
          GLES20.glUniform2fv(uniformHandle, 1, ((Vector) evaluator.get()).getArray(), 0); break;
        case VEC3_T:
          GLES20.glUniform3fv(uniformHandle, 1, ((Vector) evaluator.get()).getArray(), 0); break;
        case VEC4_T:
          GLES20.glUniform4fv(uniformHandle, 1, ((Vector) evaluator.get()).getArray(), 0); break;
        case MAT2_T:
          GLES20.glUniformMatrix2fv(uniformHandle, 1, false, ((Matrix) evaluator.get()).getArray(), 0); break;
        case MAT3_T:
          GLES20.glUniformMatrix3fv(uniformHandle, 1, false, ((Matrix) evaluator.get()).getArray(), 0); break;
        case MAT4_T:
          GLES20.glUniformMatrix4fv(uniformHandle, 1, false, ((Matrix) evaluator.get()).getArray(), 0); break;
        case SAMPLER2D_T:
          GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + textureUnitCounter);
          GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, Parameter.<Integer>get(mUniformExpressions[i]));
          GLES20.glUniform1i(uniformHandle, textureUnitCounter);
          textureUnitCounter++;
          break;

      }
    }
  }

  public void use() {
    GLES20.glUseProgram(mProgramHandle);
    bindAttributes();
    bindUniforms();
  }
}
