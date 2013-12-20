package com.lfscheidegger.jfacet.shade.expression;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;

public class Sampler extends AbstractExpression<Integer> {

  public Sampler(NodeType nodeType) {
    super(nodeType);
  }

  /*public Sampler(Bitmap bitmap) {
    super(NodeType.SamplerNodeType.forSampler(getTextureName(bitmap)));
  }*/

  @Override
  public Sampler getExpressionForTernaryOperator(Bool condition, Expression<Integer> elseExpression) {
    throw new UnsupportedOperationException("No ternary operator for samplers :(");
  }

  private static int getTextureName(Bitmap bitmap) {
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
        GLUtils.getInternalFormat(bitmap),
        bitmap,
        GLUtils.getType(bitmap),
        0);
    return textures[0];
  }

  @Override
  public String getGlSlTypeName() {
    return "sampler2D";
  }

  public Vector4 texture(Vector2 textureCoordinates) {
    return new Vector4(
        ImmutableList.<Expression>of(this, textureCoordinates),
        NodeType.FunctionNodeType.forFunction("texture2D"));
  }
}
