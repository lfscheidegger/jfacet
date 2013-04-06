package com.lfscheidegger.jfacet.shade.expression.primitives.attribute;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.GlSlEvaluators;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;

import java.nio.FloatBuffer;

public class FloatAttribute extends FloatExp implements Attribute {

  private static final Type TYPE = Type.FLOAT_T;
  private final FloatBuffer mBuffer;

  public FloatAttribute(FloatBuffer buffer) {
    super(TYPE, GlSlType.ATTRIBUTE_T, ImmutableList.<Expression>of(), GlSlEvaluators.<Float>forGlSlQualified());

    mBuffer = buffer;
  }

  @Override
  public FloatBuffer getBuffer() {
    return mBuffer;
  }
}
