package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.collect.ImmutableList;
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
    super(TYPE, ImmutableList.<Expression>of(), FloatEvaluators.forConstant(c));
  }

  public FloatExp(ImmutableList<Expression> parents, Evaluator<Float> operationEvaluator) {
    super(TYPE, parents, operationEvaluator);
  }
}
