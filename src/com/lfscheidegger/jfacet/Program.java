// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet;

import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.badlogic.gdx.backends.android.AndroidGL20;
import com.google.common.base.Preconditions;
import com.lfscheidegger.jfacet.compiler.CompilationHelper;
import com.lfscheidegger.jfacet.compiler.FragmentShaderCompiler;
import com.lfscheidegger.jfacet.compiler.VertexShaderCompiler;
import com.lfscheidegger.jfacet.shade.Parameter;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.Sampler2D;

import java.util.List;

public final class Program {

  private final CompilationHelper mCompilationHelper;
  private final VertexShaderCompiler mVertexShaderCompiler;
  private final FragmentShaderCompiler mFragmentShaderCompiler;

  private VertexShaderCompiler.CompilationResult mVertexShaderCompilationResult;
  private FragmentShaderCompiler.CompilationResult mFragmentShaderCompilationResult;

  private AndroidGL20 mAndroidGL;

  private int mMaxTextureUnits;
  private int mProgramHandle;

  public Program() {
    mCompilationHelper = new CompilationHelper();
    mVertexShaderCompiler = new VertexShaderCompiler(mCompilationHelper);
    mFragmentShaderCompiler = new FragmentShaderCompiler(mCompilationHelper);
  }

  public void build(VecLike vertexPosition, VecLike fragmentColor) {
    vertexPosition = fill(vertexPosition);
    fragmentColor = fill(fragmentColor);

    mAndroidGL = new AndroidGL20();

    mFragmentShaderCompilationResult = mFragmentShaderCompiler.compile((Vec4)fragmentColor);
    mVertexShaderCompilationResult = mVertexShaderCompiler.compile(
        (Vec4)vertexPosition,
        mFragmentShaderCompilationResult);

    int vertexShaderHandle = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
    int fragmentShaderHandle = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);

    int[] intBuffer = new int[1];
    GLES20.glGetIntegerv(GLES20.GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS, intBuffer, 0);
    mMaxTextureUnits = intBuffer[0];

    mProgramHandle = GLES20.glCreateProgram();

    compileShader(vertexShaderHandle, mVertexShaderCompilationResult.code);
    compileShader(fragmentShaderHandle, mFragmentShaderCompilationResult.code);

    GLES20.glAttachShader(mProgramHandle, vertexShaderHandle);
    GLES20.glAttachShader(mProgramHandle, fragmentShaderHandle);

    linkProgram();

    loadTextures(mFragmentShaderCompilationResult.uniformExpressions);
    loadTextures(mVertexShaderCompilationResult.uniformExpressions);
  }

  private Vec4 fill(VecLike vector) {
    Vec4 defaultValue = Shade.vec(0, 0, 0, 1);
    if (vector instanceof Vec2) {
      return Shade.vec((Vec2)vector, defaultValue.z().w().get());
    } else if (vector instanceof Vec3) {
      return Shade.vec((Vec3)vector, defaultValue.w().get());
    } else if (vector instanceof Vec4) {
      return (Vec4)vector;
    }

    throw new IllegalArgumentException("Can't fill from " + vector.getClass().getSimpleName());
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

  private void loadTextures(List<Expression> uniformExpressions) {
    for (Expression expression : uniformExpressions) {
      if (!(expression instanceof Sampler2D)) {
        continue;
      }

      Sampler2D.SamplerData samplerData = Parameter.get((Sampler2D) expression);

      final int[] textures = new int[1];
      GLES20.glGenTextures(1, textures, 0);
      GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textures[0]);

      GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
      GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
      GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
      GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);
      GLUtils.texImage2D(
          GLES20.GL_TEXTURE_2D,
          0,
          GLUtils.getInternalFormat(samplerData.bitmap),
          samplerData.bitmap,
          GLUtils.getType(samplerData.bitmap),
          0);
      GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

      samplerData.textureHandle = textures[0];
    }
  }

  private void bindAttributeLocations() {
    List<Expression> attributeExpressions = mVertexShaderCompilationResult.attributeExpressions;
    for (int i = 0; i < attributeExpressions.size(); i++) {
      GLES20.glBindAttribLocation(
          mProgramHandle,
          i,
          mCompilationHelper.getNameForExpression(attributeExpressions.get(i)));
    }
  }

  private void bindAttributes() {
    List<Expression> attributeExpressions = mVertexShaderCompilationResult.attributeExpressions;
    for (int i = 0; i < attributeExpressions.size(); i++) {
      VertexDataBuffer buffer =
          ((NodeType.AttributeNodeType) attributeExpressions
              .get(i)
              .getNodeType())
              .getAttributeBuffer();
      int dimension = buffer.getDimension();

      GLES20.glVertexAttribPointer(i, dimension, GLES20.GL_FLOAT, false, 0, buffer.getBuffer());
      GLES20.glEnableVertexAttribArray(i);
    }
  }

  private void bindUniforms(List<Expression> uniformExpressions) {
    int textureUnitCounter = 0;

    for (Expression expression : uniformExpressions) {
      String name = mCompilationHelper.getNameForExpression(expression);
      int location = GLES20.glGetUniformLocation(mProgramHandle, name);
      if (expression instanceof Real) {
        GLES20.glUniform1f(location, Parameter.get((Real) expression));
      } else if (expression instanceof Sampler2D) {
        Preconditions.checkArgument(textureUnitCounter < mMaxTextureUnits);

        Sampler2D.SamplerData samplerData = Parameter.get((Sampler2D) expression);
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + textureUnitCounter);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, samplerData.textureHandle);
        GLES20.glUniform1i(location, textureUnitCounter);
        textureUnitCounter++;
      } else if (expression instanceof Vec2) {
        Vec2.Primitive primitive = Parameter.get((Vec2) expression);
        GLES20.glUniform2f(location, primitive.getX(), primitive.getY());
      } else if (expression instanceof Vec3) {
        Vec3.Primitive primitive = Parameter.get((Vec3) expression);
        GLES20.glUniform3f(location, primitive.getX(), primitive.getY(), primitive.getZ());
      } else if (expression instanceof Vec4) {
        Vec4.Primitive primitive = Parameter.get((Vec4) expression);
        GLES20.glUniform4f(location, primitive.getX(), primitive.getY(), primitive.getZ(), primitive.getW());
      }
    }
  }

  private void bindUniforms() {
    bindUniforms(mVertexShaderCompilationResult.uniformExpressions);
    bindUniforms(mFragmentShaderCompilationResult.uniformExpressions);
  }

  public void use() {
    GLES20.glUseProgram(mProgramHandle);

    bindUniforms();

    bindAttributes();
  }
}
