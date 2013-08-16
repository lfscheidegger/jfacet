package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;

public final class BinaryOperationEvaluator<LEFT_T, RIGHT_T, RESULT_T> implements Evaluator<RESULT_T> {

  @VisibleForTesting
  public final Operator<LEFT_T, RIGHT_T, RESULT_T> mOperator;

  public BinaryOperationEvaluator(Operator<LEFT_T, RIGHT_T, RESULT_T> operator) {
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

  @Override
  public final String getGlSlString(Expression expression, CompilationContext context) {
    ImmutableList<Expression> parents = expression.getParents();

    return GlSlExpressionHelper.getBinOpExpression(
        expression.getType(), mOperator.getOperatorSymbol(), parents.get(0).getGlSlString(context), parents.get(1).getGlSlString(context));
  }
}