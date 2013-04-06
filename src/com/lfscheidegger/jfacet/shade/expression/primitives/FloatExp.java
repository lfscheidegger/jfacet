package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.FloatEvaluators;

/**
 * {code Expression} for floats
 */
public class FloatExp extends AbstractExpression<Float> {

  private static final Type TYPE = Type.FLOAT_T;

  public FloatExp(float c) {
    this(TYPE, GlSlType.DEFAULT_T, ImmutableList.<Expression>of(), FloatEvaluators.forConstant(c));
  }

  public FloatExp(ImmutableList<Expression> parents, Evaluator<Float> operationEvaluator) {
    this(TYPE, GlSlType.DEFAULT_T, parents, operationEvaluator);
  }

  public FloatExp(Type type, GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Float> evaluator) {
    super(type, glSlType, parents, evaluator);
  }

  public FloatExp add(float other) { return Shade.add(this, other); }
  public FloatExp add(FloatExp other) { return Shade.add(this, other); }

  public FloatExp sub(float other) { return Shade.sub(this, other); }
  public FloatExp sub(FloatExp other) { return Shade.sub(this, other); }

  public FloatExp mul(float other) { return Shade.mul(this, other); }
  public FloatExp mul(FloatExp other) { return Shade.mul(this, other); }

  public FloatExp div(float other) { return Shade.div(this, other); }
  public FloatExp div(FloatExp other) { return Shade.div(this, other); }

  public FloatExp neg() { return Shade.neg(this); }

  public FloatExp sin() { return com.lfscheidegger.jfacet.shade.Math.sin(this); }
  public FloatExp cos() { return com.lfscheidegger.jfacet.shade.Math.cos(this); }
}
