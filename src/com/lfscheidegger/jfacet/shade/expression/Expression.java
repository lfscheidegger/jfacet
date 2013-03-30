package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;

/**
 * A Shade expression.
 */
public interface Expression {

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
  public <T> T evaluate();

  /**
   * Returns a String for this expression's GLSL representation
   */
  public String getGlSlExpression();

  /**
   * Returns a String for this expression's GLSL representation, where
   * subexpressions are replaced by their names in a {@code CompilationContext}
   */
  public String getGlSlExpression(CompilationContext compilationContext);
}
