package com.lfscheidegger.jfacet.shade.expression;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;

public class Sampler extends AbstractExpression {

  public static final class SamplerData {

    public int textureHandle;

    public Bitmap bitmap;
  }

  public Sampler(NodeType nodeType) {
    super(nodeType);
  }

  @Override
  public Sampler getExpressionForTernaryOperator(Bool condition, Expression elseExpression) {
    throw new UnsupportedOperationException("No ternary operator for samplers :(");
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
