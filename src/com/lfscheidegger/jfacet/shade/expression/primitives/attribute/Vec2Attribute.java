package com.lfscheidegger.jfacet.shade.expression.primitives.attribute;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.GlSlEvaluators;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec2Exp;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;

import java.nio.FloatBuffer;

public class Vec2Attribute extends Vec2Exp implements Attribute {

  private static final Type TYPE = Type.VEC2_T;
  private final FloatBuffer mBuffer;

  public Vec2Attribute(FloatBuffer buffer) {
    super(TYPE, GlSlType.ATTRIBUTE_T, ImmutableList.<Expression>of(), GlSlEvaluators.<Vec2>forAttribute());

    mBuffer = buffer;
  }

  @Override
  public FloatBuffer getBuffer() {
    return mBuffer;
  }
}
