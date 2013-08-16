package com.lfscheidegger.jfacet.shade;

/**
 * Namespace for deafult GLSL primitives.
 */
public enum Type {
  /**
   * atomic floating-point value
   */
  FLOAT_T("float"),

  /**
   * 2-dimensional floating-point vector
   */
  VEC2_T("vec2"),

  /**
   * 3-dimensional floating-point vector
   */
  VEC3_T("vec3"),

  /**
   * 4-dimensional floating-point vector
   */
  VEC4_T("vec4"),

  /**
   * Atomic boolean value
   */
  BOOL_T("bool"),

  /**
   * 2x2-dimensional floating-point matrix
   */
  MAT2_T("mat2"),

  /**
   * 3x3-dimensional floating-point matrix
   */
  MAT3_T("mat3"),

  /**
   * 4x4-dimensional floating-point matrix
   */
  MAT4_T("mat4"),

  /**
   * opaque type for 2D texture sampler
   */
  SAMPLER2D_T("sampler2D");

  private final String mValue;

  Type(String value) {
    mValue = value;
  }

  @Override
  public String toString() {
    return mValue;
  }
}
