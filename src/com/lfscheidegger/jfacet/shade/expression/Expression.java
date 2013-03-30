package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;

/**
 * A Shade expression.
 */
public interface Expression<T> {

  /**
   * Returns the primitive type of the value of evaluating this expression.
   */
  public Type getType();

  /**
   * Returns a possibly empty list of parents for this expression.
   */
  public ImmutableList<Expression> getParents();

  /**
   * Returns the primitive type result of evaluating this expression, if
   * that's possible
   */
  public T evaluate();
}
