package com.lfscheidegger.jfacet.shade;

import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.transform.Rotation;
import com.lfscheidegger.jfacet.shade.transform.Scale;
import com.lfscheidegger.jfacet.shade.transform.Transform;
import com.lfscheidegger.jfacet.shade.transform.Translation;

/**
 * Convenience methods to fill primitive types into expressions
 */
public final class Shade {

  // ===================================================================================================================
  // Basic transformations
  // ===================================================================================================================

  public static Transform scale(float sx, float sy, float sz) {
    return scale(Shade.constant(sx), Shade.constant(sy), Shade.constant(sz));
  }

  public static Transform scale(float sx, float sy, Real sz) {
    return scale(Shade.constant(sx), Shade.constant(sy), sz);
  }

  public static Transform scale(float sx, Real sy, float sz) {
    return scale(Shade.constant(sx), sy, Shade.constant(sz));
  }

  public static Transform scale(float sx, Real sy, Real sz) {
    return scale(Shade.constant(sx), sy, sz);
  }

  public static Transform scale(Real sx, float sy, float sz) {
    return scale(sx, Shade.constant(sy), Shade.constant(sz));
  }

  public static Transform scale(Real sx, float sy, Real sz) {
    return scale(sx, Shade.constant(sy), sz);
  }

  public static Transform scale(Real sx, Real sy, float sz) {
    return scale(sx,  sy, Shade.constant(sz));
  }

  public static Transform scale(Real sx, Real sy, Real sz) {
    return new Scale(sx, sy, sz);
  }

  public static Transform translation(float x, float y, float z) {
    return translation(Shade.constant(x), Shade.constant(y), Shade.constant(z));
  }

  public static Transform translation(float x, float y, Real z) {
    return translation(Shade.constant(x), Shade.constant(y), z);
  }

  public static Transform translation(float x, Real y, float z) {
    return translation(Shade.constant(x), y, Shade.constant(z));
  }

  public static Transform translation(float x, Real y, Real z) {
    return translation(Shade.constant(x), y, z);
  }

  public static Transform translation(Real x, float y, float z) {
    return translation(x, Shade.constant(y), Shade.constant(z));
  }

  public static Transform translation(Real x, float y, Real z) {
    return translation(x, Shade.constant(y), z);
  }

  public static Transform translation(Real x, Real y, float z) {
    return translation(x, y, Shade.constant(z));
  }

  public static Transform translation(Real x, Real y, Real z) {
    return new Translation(x, y, z);
  }

  public static Transform rotation(float angle, VectorLike axis) {
    return rotation(Shade.constant(angle), axis);
  }

  public static Transform rotation(Real angle, VectorLike axis) {
    return new Rotation(angle, promote(axis));
  }

  // ===================================================================================================================
  // GLSL stuff
  // ===================================================================================================================

  /*public static VectorExpression fill(Expression vector, VectorLike defaultValues) {
    VectorExpression defaultExpression = promote(defaultValues);

    switch(vector.getType()) {
      case FLOAT_T:
        return Shade.vec(
            (Real)vector,
            defaultExpression.getY(),
            defaultExpression.getZ(),
            defaultExpression.getW());
      case VEC2_T:
        return Shade.vec(
            ((VectorExpression)vector).getX(),
            ((VectorExpression)vector).getY(),
            defaultExpression.getZ(),
            defaultExpression.getW());
      case VEC3_T:
        return Shade.vec(
            ((VectorExpression)vector).getX(),
            ((VectorExpression)vector).getY(),
            ((VectorExpression)vector).getZ(),
            defaultExpression.getW());
      case VEC4_T:
        return (VectorExpression)vector;
      default: throw new RuntimeException("Cannot fill " + vector.getType() + " to vec4");
    }
  }  */
}
