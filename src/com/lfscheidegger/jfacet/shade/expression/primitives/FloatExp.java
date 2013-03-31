package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
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
}
