package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.expression.*;
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

  public static Operator<Matrix2.Primitive, Vector2.Primitive, Vector2.Primitive> forLinearTransform2() {
    return new NamedOperator<Matrix2.Primitive, Vector2.Primitive, Vector2.Primitive>("*") {
      @Override
      public Vector2.Primitive op(Matrix2.Primitive left, Vector2.Primitive right) {
        return left.mul(right);
      }
    };
  }

  public static Operator<Matrix3.Primitive, Vector3.Primitive, Vector3.Primitive> forLinearTransform3() {
    return new NamedOperator<Matrix3.Primitive, Vector3.Primitive, Vector3.Primitive>("*") {
      @Override
      public Vector3.Primitive op(Matrix3.Primitive left, Vector3.Primitive right) {
        return left.mul(right);
      }
    };
  }

  public static Operator<Matrix4.Primitive, Vector4.Primitive, Vector4.Primitive> forLinearTransform4() {
    return new NamedOperator<Matrix4.Primitive, Vector4.Primitive, Vector4.Primitive>("*") {
      @Override
      public Vector4.Primitive op(Matrix4.Primitive left, Vector4.Primitive right) {
        return left.mul(right);
      }
    };
  }
}
