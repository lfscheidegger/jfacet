package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;

public class FloatOperationEvaluator<OPERAND_T>
    extends BinaryOpEvaluator<OPERAND_T, Float, OPERAND_T> {

  private final Operator<OPERAND_T, Float, OPERAND_T> mOperator;

  public FloatOperationEvaluator(Type type, Operator<OPERAND_T, Float, OPERAND_T> operator) {
    super(type, operator);
    mOperator = operator;
  }

  @Override
  @SuppressWarnings("unchecked")
  public OPERAND_T evaluate(Expression expression) {
    ImmutableList<Expression> parents = expression.getParents();
    Expression left = parents.get(0);
    FloatExp right = (FloatExp)parents.get(1);
    return mOperator.op((OPERAND_T)left.evaluate(), right.evaluate());
  }
}