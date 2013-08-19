package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public class TernaryOperationEvaluator<T> implements Evaluator<T> {

  @Override
  @SuppressWarnings("unchecked")
  public T evaluate(Expression<T> expression) {
    Bool booleanExpression = (Bool)expression.getParents().get(0);
    Expression<T> ifExpression = (Expression<T>)expression.getParents().get(1);
    Expression<T> elseExpression = (Expression<T>)expression.getParents().get(2);

    return booleanExpression.evaluate() ? ifExpression.evaluate() : elseExpression.evaluate();
  }

  @Override
  @SuppressWarnings("unchecked")
  public String getGlSlString(Expression<T> expression, CompilationContext context) {
    Bool booleanExpression = (Bool)expression.getParents().get(0);
    Expression<T> ifExpression = (Expression<T>)expression.getParents().get(1);
    Expression<T> elseExpression = (Expression<T>)expression.getParents().get(2);

    return GlSlExpressionHelper.getTernaryOperatorExpression(
        ifExpression.getType(),
        expression.getParents().get(0).getGlSlString(context),
        expression.getParents().get(1).getGlSlString(context),
        expression.getParents().get(2).getGlSlString(context));
  }
}
