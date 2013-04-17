package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.MatrixExpression;
import com.lfscheidegger.jfacet.shade.expression.VectorExpression;

public final class ComponentEvaluator<T> implements Evaluator<T> {

  private final int mIdx;

  public ComponentEvaluator(int idx) {
    mIdx = idx;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T evaluate(Expression expression) {
    Expression parent = (Expression)expression.getParents().get(0);

    if (parent instanceof VectorExpression) {
      return (T)(Float.valueOf(((VectorExpression)parent).evaluate().get(mIdx)));
    } else if (parent instanceof MatrixExpression) {
      return (T)((MatrixExpression)parent).evaluate().get(mIdx);
    } else {
      throw new RuntimeException("Cannot evaluate component expression for: " + expression.getClass().getSimpleName());
    }
  }

  @Override
  public String getGlSlString(Expression<T> expression, CompilationContext context) {
    return GlSlExpressionHelper.getComponentExpression(
        expression.getType(), ((Expression) expression.getParents().get(0)).getGlSlString(context), mIdx);

  }
}