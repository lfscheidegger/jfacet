package com.lfscheidegger.jfacet.shade.expression;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.TernaryOperationEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.glsl.UniformEvaluator;

public final class SamplerExpression extends AbstractExpression<Integer> {

  private final Bitmap mTexture;

  public SamplerExpression(Bitmap texture) {
    super(Type.SAMPLER2D_T, GlSlType.UNIFORM_T, ImmutableList.<Expression>of(), new UniformEvaluator<Integer>());
    mTexture = texture;
  }

  @Override
  public SamplerExpression getExpressionForTernaryOperator(Bool condition, Expression<Integer> elseExpression) {
    // TODO: refactor this to do something useful
    throw new RuntimeException("ternary operators for samplers haven't been implemented yet :(");
  }

  public void bake() {
    int[] handleBuffer = new int[1];
    GLES20.glGenTextures(1, handleBuffer, 0);

    ((UniformEvaluator<Integer>)getEvaluator()).set(handleBuffer[0]);

    if (handleBuffer[0] == 0) {
      throw new RuntimeException("Error allocating texture");
    }

    GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, handleBuffer[0]);
    GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
    GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

    GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, mTexture, 0);
    GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
  }
}
