package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.primitives.Matrix;
import com.lfscheidegger.jfacet.shade.primitives.Vector;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.SupportsBasicArithmetic;

public final class BasicArithmeticOperators {
  public static <T extends SupportsBasicArithmetic> Operator<T, Float, T> forAdditionWithFloat() {
    return new NamedOperator<T, Float, T>("+") {
      @Override
      public T op(T left, Float right) {
        return (T)left.add(right.floatValue());
      }
    };
  }

  public static <T extends SupportsBasicArithmetic> Operator<T, Float, T> forSubtractionWithFloat() {
    return new NamedOperator<T, Float, T>("-") {
      @Override
      public T op(T left, Float right) {
        return (T)left.sub(right.floatValue());
      }
    };
  }

  public static <T extends SupportsBasicArithmetic> Operator<T, Float, T> forMultiplicationWithFloat() {
    return new NamedOperator<T, Float, T>("*") {
      @Override
      public T op(T left, Float right) {
        return (T)left.mul(right.floatValue());
      }
    };
  }

  public static <T extends SupportsBasicArithmetic> Operator<T, Float, T> forDivisionWithFloat() {
    return new NamedOperator<T, Float, T>("/") {
      @Override
      public T op(T left, Float right) {
        return (T)left.div(right.floatValue());
      }
    };
  }

  public static <T extends SupportsBasicArithmetic> Operator<T, T, T> forAdditionWithSame() {
    return new NamedOperator<T, T, T>("+") {
      @Override
      public T op(T left, T right) {
        return (T)left.add(right);
      }
    };
  }

  public static <T extends SupportsBasicArithmetic> Operator<T, T, T> forSubtractionWithSame() {
    return new NamedOperator<T, T, T>("-") {
      @Override
      public T op(T left, T right) {
        return (T)left.sub(right);
      }
    };
  }

  public static <T extends SupportsBasicArithmetic> Operator<T, T, T> forMultiplicationWithSame() {
    return new NamedOperator<T, T, T>("*") {
      @Override
      public T op(T left, T right) {
        return (T)left.mul(right);
      }
    };
  }

  public static <T extends SupportsBasicArithmetic> Operator<T, T, T> forDivisionWithSame() {
    return new NamedOperator<T, T, T>("/") {
      @Override
      public T op(T left, T right) {
        return (T)left.div(right);
      }
    };
  }

  public static Operator<Matrix, Vector, Vector> forLinearTransform() {
    return new NamedOperator<Matrix, Vector, Vector>("*") {
      @Override
      public Vector op(Matrix left, Vector right) {
        return left.mul(right);
      }
    };
  }
}
