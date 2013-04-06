package com.lfscheidegger.jfacet.shade.expression.primitives.uniform;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.GlSlEvaluators;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;

public class FloatUniform extends FloatExp implements Uniform {

  private FloatExp mValue;
  private float mCachedValue;

  public FloatUniform(FloatExp value) {
    super(Type.FLOAT_T, GlSlType.UNIFORM_T, ImmutableList.<Expression>of(), GlSlEvaluators.<Float>forGlSlQualified());

    set(value);
  }

  @Override
  public void refresh() {
  }

  public void set(FloatExp value) {
    mValue = value;
    mCachedValue = value.evaluate();
  }

  public void set(float val) {
    mCachedValue = val;
  }

  public float get() {
    return mCachedValue;
  }
}
