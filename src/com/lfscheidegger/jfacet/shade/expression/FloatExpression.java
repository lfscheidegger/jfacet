package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ConstantEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;

/**
 * {code Expression} for floats
 */
public final class FloatExpression extends AbstractExpression<Float> {

  private static final Type TYPE = Type.FLOAT_T;

  public FloatExpression(float c) {
    this(new ConstantEvaluator<Float>(c));
  }

  public FloatExpression(Evaluator<Float> evaluator) {
    this(ImmutableList.<Expression>of(), evaluator);
  }

  public FloatExpression(ImmutableList<Expression> parents, Evaluator<Float> evaluator) {
    this(GlSlType.DEFAULT_T, parents, evaluator);
  }

  public FloatExpression(GlSlType glSlType, Evaluator<Float> evaluator) {
    this(glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  public FloatExpression(GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Float> evaluator) {
    super(TYPE, glSlType, parents, evaluator);
  }

  public FloatExpression add(float other) { return Shade.add(this, other); }
  public FloatExpression add(FloatExpression other) { return Shade.add(this, other); }

  public FloatExpression sub(float other) { return Shade.sub(this, other); }
  public FloatExpression sub(FloatExpression other) { return Shade.sub(this, other); }

  public FloatExpression mul(float other) { return Shade.mul(this, other); }
  public FloatExpression mul(FloatExpression other) { return Shade.mul(this, other); }

  public FloatExpression div(float other) { return Shade.div(this, other); }
  public FloatExpression div(FloatExpression other) { return Shade.div(this, other); }

  public FloatExpression neg() { return Shade.neg(this); }

  public FloatExpression sin() { return com.lfscheidegger.jfacet.shade.Math.sin(this); }
  public FloatExpression cos() { return com.lfscheidegger.jfacet.shade.Math.cos(this); }
  public FloatExpression radians() { return com.lfscheidegger.jfacet.shade.Math.radians(this); }
}
