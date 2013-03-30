package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;

public abstract class BinaryOpEvaluator<LEFT_T, RIGHT_T, RESULT_T> implements Evaluator<RESULT_T> {

  private final Type mType;
  private final Operator<LEFT_T, RIGHT_T, RESULT_T> mOperator;

  public BinaryOpEvaluator(Type type, Operator<LEFT_T, RIGHT_T, RESULT_T> operator) {
    mType = type;
    mOperator = operator;
  }

  @Override
  public final String getGlSlString(Expression expression) {
    ImmutableList<Expression> parents = expression.getParents();

    return GlSlExpressionHelper.getBinOpString(
        mType, mOperator.getOperatorSymbol(), parents.get(0), parents.get(1));
  }
}
