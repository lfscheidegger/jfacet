package com.lfscheidegger.jfacet.shade.compiler;

import com.lfscheidegger.jfacet.shade.expression.Expression;

/**
 * Context for compilation of expressions
 */
public interface CompilationContext {

  /**
   * Returns a {@code String} naming this expression.
   */
  public String getName(Expression exp);
}
