package com.lfscheidegger.jfacet.shade.expression.primitives.attribute;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.GlSlEvaluators;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec3Exp;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;

import java.nio.FloatBuffer;

public class Vec3Attribute extends Vec3Exp implements Attribute {

  private static final Type TYPE = Type.VEC3_T;
  private final FloatBuffer mBuffer;

  public Vec3Attribute(FloatBuffer buffer) {
    super(TYPE, GlSlType.ATTRIBUTE_T, ImmutableList.<Expression>of(), GlSlEvaluators.<Vec3>forGlSlQualified());

    mBuffer = buffer;
  }

  @Override
  public FloatBuffer getBuffer() {
    return mBuffer;
  }
}
