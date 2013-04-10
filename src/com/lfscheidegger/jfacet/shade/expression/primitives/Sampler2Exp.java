package com.lfscheidegger.jfacet.shade.expression.primitives;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.UniformEvaluator;

public class Sampler2Exp extends AbstractExpression<Float> {

  private final Bitmap mTexture;
  private int mHandle;

  public Sampler2Exp(Bitmap texture) {
    super(
        Type.SAMPLER2D_T,
        GlSlType.UNIFORM_T,
        ImmutableList.<Expression>of(),
        new UniformEvaluator<Float>(Shade.constant(0)));
    mTexture = texture;
  }

  public void bake() {
    int[] handleBuffer = new int[1];
    GLES20.glGenTextures(1, handleBuffer, 0);

    mHandle = handleBuffer[0];

    if (mHandle == 0) {
      throw new RuntimeException("Error allocating texture");
    }

    GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mHandle);
    GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
    GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

    GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, mTexture, 0);
    GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
  }

  public int getTextureHandle() {
    return mHandle;
  }
}
