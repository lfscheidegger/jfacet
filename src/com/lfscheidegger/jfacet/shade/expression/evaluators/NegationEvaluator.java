package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.FloatExpression;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.SupportsBasicArithmetic;

public class NegationEvaluator<T> implements Evaluator<T> {

  @Override
  @SuppressWarnings("unchecked")
  public T evaluate(Expression expression) {
    Expression parent = (Expression)expression.getParents().get(0);

    if (parent instanceof FloatExpression) {
      return (T)Float.valueOf(- ((FloatExpression)parent).evaluate());
    } else {
      return (T)((SupportsBasicArithmetic)parent.evaluate()).neg();
    }
  }

  @Override
  public String getGlSlString(Expression expression, CompilationContext context) {
    ImmutableList<Expression> parents = expression.getParents();
    return GlSlExpressionHelper.getUnOpExpression(expression.getType(), "-", parents.get(0).getGlSlString(context));
  }
}
