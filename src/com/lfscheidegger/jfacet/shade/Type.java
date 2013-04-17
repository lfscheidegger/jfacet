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
  MAT2_T("mat2", 2),

  /**
   * 3x3-dimensional floating-point matrix
   */
  MAT3_T("mat3", 3),

  /**
   * 4x4-dimensional floating-point matrix
   */
  MAT4_T("mat4", 4),

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

  public static Type getVecTypeForDimension(int dimension) {
    switch(dimension) {
      case 2: return VEC2_T;
      case 3: return VEC3_T;
      case 4: return VEC4_T;
      default: throw new RuntimeException("Invalid dimension for vector: " + dimension);
    }
  }

  public static Type getMatTypeForDimension(int dimension) {
    switch(dimension) {
      case 2: return MAT2_T;
      case 3: return MAT3_T;
      case 4: return MAT4_T;
      default: throw new RuntimeException("Invalid dimension for matrix: " + dimension);
    }
  }
}
