package com.lfscheidegger.jfacet.shade;

/**
 * Namespace for deafult GLSL primitives.
 */
public enum Type {
  /**
   * atomic floating-point value
   */
  FLOAT_T("float", 1),

  /**
   * 2-dimensional floating-point vector
   */
  VEC2_T("vec2", 2),

  /**
   * 3-dimensional floating-point vector
   */
  VEC3_T("vec3", 3),

  /**
   * 4-dimensional floating-point vector
   */
  VEC4_T("vec4", 4),

  /**
   * 2x2-dimensional floating-point matrix
   */
  MAT2_T("mat2", -1),

  /**
   * 3x3-dimensional floating-point matrix
   */
  MAT3_T("mat3", -1),

  /**
   * 4x4-dimensional floating-point matrix
   */
  MAT4_T("mat4", -1),

  /**
   * opaque type for 2D texture sampler
   */
  SAMPLER2D_T("sampler2D", -1);

  private final String mValue;
  private final int mDimension;

  Type(String value, int dimension) {
    mValue = value;
    mDimension = dimension;
  }

  @Override
  public String toString() {
    return mValue;
  }

  public int getDimension() {
    if (mDimension == -1) {
      throw new RuntimeException("Cannot call getDimension() on matrix type " + toString());
    }

    return mDimension;
  }
}
