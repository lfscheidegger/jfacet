package com.lfscheidegger.jfacet.shade.internal;

import com.lfscheidegger.jfacet.shade.Expression;
import com.lfscheidegger.jfacet.shade.ExpressionImpl;
import com.lfscheidegger.jfacet.shade.internal.types.*;

import static com.lfscheidegger.jfacet.shade.Types.*;

/**
 * Contains methods to create constant Shade {@code Expression} values.
 */
public class Constant {

  private static String getGlSlExpression(String type, String... values) {
    StringBuilder b = new StringBuilder();
    b.append(type).append("(");
    b.append(values[0]);
    for (int i = 1; i < values.length; i++) {
      b.append(", ").append(values[i]);
    }
    return b.append(")").toString();
  }

  private static String getGlSlExpression(Vec2 vec) {
    return new StringBuilder()
        .append(VEC2_T).append("(")
        .append(vec.get(0)).append(", ")
        .append(vec.get(1)).append(")")
        .toString();
  }

  private static String getGlSlExpression(Vec3 vec) {
    return new StringBuilder()
        .append(VEC3_T).append("(")
        .append(vec.get(0)).append(", ")
        .append(vec.get(1)).append(", ")
        .append(vec.get(2)).append(")")
        .toString();
  }

  private static String getGlSlExpression(Vec4 vec) {
    return new StringBuilder()
        .append(VEC4_T).append("(")
        .append(vec.get(0)).append(", ")
        .append(vec.get(1)).append(", ")
        .append(vec.get(2)).append(", ")
        .append(vec.get(3)).append(")")
        .toString();
  }

  public static Expression constant(float... args) {
    switch(args.length) {
      case 1:
        return new ExpressionImpl.Builder(FLOAT_T, getGlSlExpression(FLOAT_T, String.valueOf(args[0]))).build();
      case 2:
        return new ExpressionImpl.Builder(VEC2_T, getGlSlExpression(
            VEC2_T, String.valueOf(args[0]), String.valueOf(args[1]))).build();
      case 3:
        return new ExpressionImpl.Builder(
            VEC3_T, getGlSlExpression(
            VEC3_T,
            String.valueOf(args[0]),
            String.valueOf(args[1]),
            String.valueOf(args[2]))).build();
      case 4:
        return new ExpressionImpl.Builder(
            VEC4_T, getGlSlExpression(
            VEC4_T,
            String.valueOf(args[0]),
            String.valueOf(args[1]),
            String.valueOf(args[2]),
            String.valueOf(args[3]))).build();
      default:
        throw new IllegalArgumentException("Got invalid number of float arguments " + args.length);
    }
  }

  public static Expression constant(Vec2 xy) {
    return new ExpressionImpl.Builder(VEC2_T, getGlSlExpression(
        VEC2_T, String.valueOf(xy.getX()), String.valueOf(xy.getY()))).build();
  }

  public static Expression constant(Vec3 xyz) {
    return new ExpressionImpl.Builder(VEC3_T, getGlSlExpression(
        VEC3_T, String.valueOf(xyz.getX()), String.valueOf(xyz.getY()), String.valueOf(xyz.getZ()))).build();
  }

  public static Expression constant(Vec4 xyzw) {
    return new ExpressionImpl.Builder(VEC4_T, getGlSlExpression(
        VEC4_T,
        String.valueOf(xyzw.getX()),
        String.valueOf(xyzw.getY()),
        String.valueOf(xyzw.getZ()),
        String.valueOf(xyzw.getW()))).build();
  }

  public static Expression constant(Mat2 mat) {
    return new ExpressionImpl.Builder(MAT2_T, getGlSlExpression(
        MAT2_T,
        getGlSlExpression(mat.get(0)),
        getGlSlExpression(mat.get(1)))).build();
  }

  public static Expression constant(Mat3 mat) {
    return new ExpressionImpl.Builder(MAT3_T, getGlSlExpression(
        MAT3_T,
        getGlSlExpression(mat.get(0)),
        getGlSlExpression(mat.get(1)),
        getGlSlExpression(mat.get(2)))).build();
  }

  public static Expression constant(Mat4 mat) {
    return new ExpressionImpl.Builder(MAT4_T, getGlSlExpression(
        MAT4_T,
        getGlSlExpression(mat.get(0)),
        getGlSlExpression(mat.get(1)),
        getGlSlExpression(mat.get(2)),
        getGlSlExpression(mat.get(3)))).build();
  }
}
