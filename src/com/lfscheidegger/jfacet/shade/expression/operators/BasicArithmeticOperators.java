package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.primitives.*;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.SupportsBasicArithmetic;

public class BasicArithmeticOperators {
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

  public static Operator forLinearTransform() {
    return new NamedOperator("*") {
      @Override
      public Object op(Object left, Object right) {
        if (left instanceof Mat2) {
          return ((Mat2)left).mul((Vec2)right);
        } else if (left instanceof Mat3) {
          return ((Mat3)left).mul((Vec3)right);
        } else if (left instanceof Mat4) {
          return ((Mat4)left).mul((Vec4)right);
        }

        throw new RuntimeException("Unsupported type for linear transform: " + left);
      }
    };
  }
}
