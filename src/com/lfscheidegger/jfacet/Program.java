package com.lfscheidegger.jfacet;

import android.opengl.GLES20;
import com.badlogic.gdx.backends.android.AndroidGL20;
import com.google.common.base.Optional;
import com.lfscheidegger.jfacet.compiler.FragmentShaderCompiler;
import com.lfscheidegger.jfacet.compiler.VertexShaderCompiler;
import com.lfscheidegger.jfacet.facet.AttributeBuffer;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;
import com.lfscheidegger.jfacet.shade.expression.vector.VectorExpression;

import java.util.List;

public final class Program {

  private final Vector4 mPosition;
  private final Vector4 mFragColor;

  private final VertexShaderCompiler mVertexShaderCompiler;
  private final FragmentShaderCompiler mFragmentShaderCompiler;

  private AndroidGL20 mAndroidGL;
  private int mProgramHandle;

  public <T> Program(VectorExpression<T, Vector4> position, VectorExpression<T, Vector4> fragColor) {
    mPosition = position.fill(new Vector4(0, 0, 0, 1));
    mFragColor = fragColor.fill(new Vector4(0, 0, 0, 1));

    mVertexShaderCompiler = new VertexShaderCompiler(mPosition);
    mFragmentShaderCompiler = new FragmentShaderCompiler(mFragColor);
  }

  public void bake() {
    mAndroidGL = new AndroidGL20();

    String fragmentShaderSource = mFragmentShaderCompiler.compile();

    mVertexShaderCompiler.setVaryingExpressions(mFragmentShaderCompiler.getVaryingExpressions());
    String vertexShaderSource = mVertexShaderCompiler.compile();

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
      throw new RuntimeException("Error compiling program\n" + compileLog);
    }
  }

  private void linkProgram() {
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

  private void bindAttributeLocations() {
    List<Expression> attributeExpressions = mVertexShaderCompiler.getAttributeExpressions();
    for (int i = 0; i < attributeExpressions.size(); i++) {
      GLES20.glBindAttribLocation(
          mProgramHandle,
          i,
          mVertexShaderCompiler
              .getCompilationHelper()
              .getNameForExpression(attributeExpressions.get(i)));
    }
  }

  private void bindAttributes() {
    List<Expression> attributeExpressions = mVertexShaderCompiler.getAttributeExpressions();
    for (int i = 0; i < attributeExpressions.size(); i++) {

      AttributeBuffer buffer =
          ((Expression.NodeType.AttributeNodeType) attributeExpressions
              .get(i)
              .getNodeType())
              .getAttributeBuffer();
      int dimension = buffer.getDimension();

      GLES20.glVertexAttribPointer(i, dimension, GLES20.GL_FLOAT, false, 0, buffer.getBuffer());
      GLES20.glEnableVertexAttribArray(i);
    }
  }

  public void use() {
    GLES20.glUseProgram(mProgramHandle);
    bindAttributes();
  }
}
