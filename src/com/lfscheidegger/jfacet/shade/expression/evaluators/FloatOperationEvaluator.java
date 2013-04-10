package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;

public class FloatOperationEvaluator<LEFT_T, RIGHT_T, RESULT_T>
    extends BinaryOpEvaluator<LEFT_T, RIGHT_T, RESULT_T> {

  private final Operator<LEFT_T, RIGHT_T, RESULT_T> mOperator;

  public FloatOperationEvaluator(Type type, Operator<LEFT_T, RIGHT_T, RESULT_T> operator) {
    super(type, operator);
    mOperator = operator;
  }

  @Override
  @SuppressWarnings("unchecked")
  public RESULT_T evaluate(Expression expression) {
    ImmutableList<Expression> parents = expression.getParents();
    Expression left = parents.get(0);
    Expression right = parents.get(1);
    return mOperator.op((LEFT_T)left.evaluate(), (RIGHT_T)right.evaluate());
  }
}