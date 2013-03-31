package com.lfscheidegger.jfacet.shade;

/**
 * Namespace for GLSL expression types.
 */
public enum GlSlType {

  /**
   * Type for uniform variables
   */
  UNIFORM_T("uniform"),

  /**
   * Type for attribute variables
   */
  ATTRIBUTE_T("attribute"),

  /**
   * Type for varying variables
   */
  VARYING_T("varying"),

  /**
   * Type for default (evaluable) variables
   */
  DEFAULT_T("default");

  private final String mValue;

  GlSlType(String value) {
    mValue = value;
  }

  @Override
  public String toString() {
    return mValue;
  }
}
