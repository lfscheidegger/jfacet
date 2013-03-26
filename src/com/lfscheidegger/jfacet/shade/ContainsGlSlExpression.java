package com.lfscheidegger.jfacet.shade;

/**
 * Interface for classes that can be turned into a GLSL expression
 */
public interface ContainsGlSlExpression {

  /**
   * Returns a {@code String} representing this Object's GLSL expression
   */
  public String getGlSlExpression();
}
