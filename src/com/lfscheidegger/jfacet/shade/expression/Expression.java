package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;

/**
 * A Shade expression.
 */
public interface Expression<T> {

  /**
   * Returns the primitive type of the value of evaluating this expression.
   */
  public Type getType();

  /**
   * Returns the GLSL type of the value for this expression.
   */
  public GlSlType getGlSlType();

  /**
   * Returns a possibly empty list of parents for this expression.
   */
  public ImmutableList<Expression> getParents();

  /**
   * Returns the primitive type result of evaluating this expression, if
   * that's possible
   */
  public T evaluate();

  /**
   * Returns the {@code Evaluator} object used for this expression
   */
  public Evaluator<T> getEvaluator();

  /**
   * Returns a string representing this {@code Expression} in GLSL, using the provided {@code CompilationContext}
   * object to obtain names for its subexpressions
   */
  public String getGlSlString(CompilationContext context);
}
