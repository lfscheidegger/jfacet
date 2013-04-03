package com.lfscheidegger.jfacet.shade.expression.primitives.attribute;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.GlSlEvaluators;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec4Exp;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

import java.nio.FloatBuffer;

public class Vec4Attribute extends Vec4Exp implements Attribute {

  private static final Type TYPE = Type.VEC4_T;
  private final FloatBuffer mBuffer;

  public Vec4Attribute(FloatBuffer buffer) {
    super(TYPE, GlSlType.ATTRIBUTE_T, ImmutableList.<Expression>of(), GlSlEvaluators.<Vec4>forAttribute());

    mBuffer = buffer;
  }

  @Override
  public FloatBuffer getBuffer() {
    return mBuffer;
  }
}