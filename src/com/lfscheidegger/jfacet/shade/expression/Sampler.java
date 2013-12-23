package com.lfscheidegger.jfacet.shade.expression;

import android.graphics.Bitmap;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;

public final class Sampler extends Expression {

  public static final class SamplerData {

    public int textureHandle;

    public Bitmap bitmap;
  }

  public Sampler(NodeType nodeType) {
    super(nodeType);
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
