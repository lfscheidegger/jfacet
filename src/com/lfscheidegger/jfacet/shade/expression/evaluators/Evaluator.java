package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.Expression;

/**
 * Interface responsible for evaluating different instances of {@code Expression} objects
 */
public interface Evaluator<T> {

  public static class EvaluationException extends RuntimeException {
    public EvaluationException(String message) {
      super(message);
    }
  }

  public T evaluate(Expression expression);

  public String getGlSlString(Expression expression, CompilationContext context);
}
