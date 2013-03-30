package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Expression;

/**
 * Interface responsible for evaluating different instances of {@code Expression} objects
 */
public interface Evaluator<T> {

  public T evaluate(ImmutableList<Expression> parents);
}
