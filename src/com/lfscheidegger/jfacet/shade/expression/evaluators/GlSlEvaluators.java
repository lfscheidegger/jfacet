package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public class GlSlEvaluators {
  public static <T> Evaluator<T> forGlSlQualified() {
    return new Evaluator<T>() {
      @Override
      public T evaluate(Expression expression) {
        throw new EvaluationException("Cannot evaluate qualified expression");
      }

      @Override
      public String getGlSlString(Expression expression, CompilationContext context) {
        return context.getExpressionName(expression);
      }
    };
  }
}
