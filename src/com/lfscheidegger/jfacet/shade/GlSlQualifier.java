package com.lfscheidegger.jfacet.shade;

/**
 * This enumeration contains possible variable qualifiers for GLSL.
 */
public enum GlSlQualifier {

  /**
   * Used to denote uniform variables
   */
  UNIFORM_T("uniform"),

  /**
   * Used to denote attribute variables
   */
  ATTRIBUTE_T("attribute"),

  /**
   * Used to denote varying variables
   */
  VARYING_T("varying");

  private final String mValue;

  GlSlQualifier(String value) {
    mValue = value;
  }

  @Override
  public String toString() {
    return mValue;
  }
}
