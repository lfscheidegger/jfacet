package com.lfscheidegger.jfacet.shade.internal;

import com.lfscheidegger.jfacet.shade.Expression;
import com.lfscheidegger.jfacet.shade.ExpressionImpl;

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
}
