// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.shade.expression;

import android.graphics.Bitmap;
import com.google.common.collect.ImmutableList;

public final class Sampler2D extends Expression {

  private static final String GLSL_TYPE_NAME = "sampler2D";

  public static final class SamplerData {

    public int textureHandle;

    public Bitmap bitmap;
  }

  public Sampler2D(NodeType nodeType) {
    super(nodeType, GLSL_TYPE_NAME);
  }

  public Vec4 texture(Vec2 textureCoordinates) {
    return new Vec4(
        ImmutableList.<Expression>of(this, textureCoordinates),
        NodeType.FunctionNodeType.forFunction("texture2D"));
  }
}
