package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;

/**
 * A Shade expression.
 */
public interface Expression {

  /**
   * Returns the {@code NodeType} of this expression
   */
  public NodeType getNodeType();

  /**
   * Returns a possibly empty list of parents for this expression.
   */
  public ImmutableList<Expression> getParents();

  public String getGlSlTypeName();
}
