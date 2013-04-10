package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.primitives.Vec4;

/**
 * {@code Operator} objects for {@code Vec4Exp}
 */
public class Vec4Operators {

  public static Operator<Vec4, Float, Vec4> forAdditionWithFloat() {
    return new NamedOperator<Vec4, Float, Vec4>("+") {
      @Override
      public Vec4 op(Vec4 left, Float right) {
        return left.add(right);
      }
    };
  }

  public static Operator<Vec4, Float, Vec4> forSubtractionWithFloat() {
    return new NamedOperator<Vec4, Float, Vec4>("-") {
      @Override
      public Vec4 op(Vec4 left, Float right) {
        return left.sub(right);
      }
    };
  }

  public static Operator<Vec4, Float, Vec4> forMultiplicationWithFloat() {
    return new NamedOperator<Vec4, Float, Vec4>("*") {
      @Override
      public Vec4 op(Vec4 left, Float right) {
        return left.mul(right);
      }
    };
  }

  public static Operator<Vec4, Float, Vec4> forDivisionWithFloat() {
    return new NamedOperator<Vec4, Float, Vec4>("/") {
      @Override
      public Vec4 op(Vec4 left, Float right) {
        return left.div(right);
      }
    };
  }

  public static Operator<Vec4, Vec4, Vec4> forAdditionWithVec4() {
    return new NamedOperator<Vec4, Vec4, Vec4>("+") {
      @Override
      public Vec4 op(Vec4 left, Vec4 right) {
        return left.add(right);
      }
    };
  }

  public static Operator<Vec4, Vec4, Vec4> forSubtractionWithVec4() {
    return new NamedOperator<Vec4, Vec4, Vec4>("-") {
      @Override
      public Vec4 op(Vec4 left, Vec4 right) {
        return left.sub(right);
      }
    };
  }

  public static Operator<Vec4, Vec4, Vec4> forMultiplicationWithVec4() {
    return new NamedOperator<Vec4, Vec4, Vec4>("*") {
      @Override
      public Vec4 op(Vec4 left, Vec4 right) {
        return left.mul(right);
      }
    };
  }

  public static Operator<Vec4, Vec4, Vec4> forDivisionWithVec4() {
    return new NamedOperator<Vec4, Vec4, Vec4>("/") {
      @Override
      public Vec4 op(Vec4 left, Vec4 right) {
        return left.div(right);
      }
    };
  }
}
