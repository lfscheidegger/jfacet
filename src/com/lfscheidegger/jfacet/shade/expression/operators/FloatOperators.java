package com.lfscheidegger.jfacet.shade.expression.operators;

/**
 * {@code Operator} objects for {@code Float}
 */
public class FloatOperators {
  public static Operator<Float, Float, Float> forAddition() {
    return new Operator<Float, Float, Float>() {
      @Override
      public Float op(Float left, Float right) {
        return left + right;
      }
    };
  }

  public static Operator<Float, Float, Float> forSubtraction() {
    return new Operator<Float, Float, Float>() {
      @Override
      public Float op(Float left, Float right) {
        return left - right;
      }
    };
  }

  public static Operator<Float, Float, Float> forMultiplication() {
    return new Operator<Float, Float, Float>() {
      @Override
      public Float op(Float left, Float right) {
        return left * right;
      }
    };
  }

  public static Operator<Float, Float, Float> forDivision() {
    return new Operator<Float, Float, Float>() {
      @Override
      public Float op(Float left, Float right) {
        return left / right;
      }
    };
  }
}
