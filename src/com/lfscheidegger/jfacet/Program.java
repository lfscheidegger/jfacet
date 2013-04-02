package com.lfscheidegger.jfacet;

import android.opengl.GLES20;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.DefaultCompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.ShaderCompiler;
import com.lfscheidegger.jfacet.shade.expression.Expression;

import java.nio.FloatBuffer;

public class Program {

  private int mVertexShaderHandle;
  private int mFragmentShaderHandle;
  private int mProgramHandle;

  private final CompilationContext mCompilationContext;

  private final Expression mPosition;
  private final Expression mFragColor;

  public Program(Expression position, Expression fragColor) {
    mCompilationContext = new DefaultCompilationContext();

    mPosition = position;
    mFragColor = fragColor;
  }

  public void bake() {
    ShaderCompiler vertexShaderCompiler = new ShaderCompiler(
        mCompilationContext,
        ImmutableMap.<String, Expression>of("gl_Position", mPosition));
    ShaderCompiler fragmentShaderCompiler = new ShaderCompiler(
        mCompilationContext,
        ImmutableMap.<String, Expression>of("gl_FragColor", mFragColor));

    String vertexShaderSource = vertexShaderCompiler.compile();
    String fragmentShaderSource = fragmentShaderCompiler.compile();

    mVertexShaderHandle = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
    mFragmentShaderHandle = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
    mProgramHandle = GLES20.glCreateProgram();

    GLES20.glShaderSource(mVertexShaderHandle, vertexShaderSource);
    GLES20.glCompileShader(mVertexShaderHandle);
    GLES20.glShaderSource(mFragmentShaderHandle, fragmentShaderSource);
    GLES20.glCompileShader(mFragmentShaderHandle);

    GLES20.glAttachShader(mProgramHandle, mVertexShaderHandle);
    GLES20.glAttachShader(mProgramHandle, mFragmentShaderHandle);

    GLES20.glLinkProgram(mProgramHandle);
  }

  public void bind(Expression attributeExpression, FloatBuffer buffer) {
    Preconditions.checkArgument(attributeExpression.getGlSlType() == GlSlType.ATTRIBUTE_T);

    int attribLocationHandle = GLES20.glGetAttribLocation(
        mProgramHandle,
        mCompilationContext.getExpressionName(attributeExpression));

    int size = 4;
    switch(attributeExpression.getType()) {
      case FLOAT_T:
        size = 1;
        break;
      case VEC2_T:
        size = 2;
        break;
      case VEC3_T:
        size = 3;
        break;
      case VEC4_T:
        size = 4;
        break;
      default:
        throw new RuntimeException("Unsupported type for attribute binding: " + attributeExpression.getType());
    }

    GLES20.glVertexAttribPointer(attribLocationHandle, size, GLES20.GL_FLOAT, false, 0, buffer);
  }

  public void use() {
    GLES20.glUseProgram(mProgramHandle);
  }
}
