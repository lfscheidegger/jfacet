package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.primitives.Vec3;

/**
 * {@code Operator} objects for {@code Vec3Exp}
 */
public class Vec3Operators {

  public static Operator<Vec3, Float, Vec3> forAdditionWithFloat() {
    return new NamedOperator<Vec3, Float, Vec3>("+") {
      @Override
      public Vec3 op(Vec3 left, Float right) {
        return left.add(right);
      }
    };
  }

  public static Operator<Vec3, Float, Vec3> forSubtractionWithFloat() {
    return new NamedOperator<Vec3, Float, Vec3>("-") {
      @Override
      public Vec3 op(Vec3 left, Float right) {
        return left.sub(right);
      }
    };
  }

  public static Operator<Vec3, Float, Vec3> forMultiplicationWithFloat() {
    return new NamedOperator<Vec3, Float, Vec3>("*") {
      @Override
      public Vec3 op(Vec3 left, Float right) {
        return left.mul(right);
      }
    };
  }

  public static Operator<Vec3, Float, Vec3> forDivisionWithFloat() {
    return new NamedOperator<Vec3, Float, Vec3>("/") {
      @Override
      public Vec3 op(Vec3 left, Float right) {
        return left.div(right);
      }
    };
  }

  public static Operator<Vec3, Vec3, Vec3> forAdditionWithVec3() {
    return new NamedOperator<Vec3, Vec3, Vec3>("+") {
      @Override
      public Vec3 op(Vec3 left, Vec3 right) {
        return left.add(right);
      }
    };
  }

  public static Operator<Vec3, Vec3, Vec3> forSubtractionWithVec3() {
    return new NamedOperator<Vec3, Vec3, Vec3>("-") {
      @Override
      public Vec3 op(Vec3 left, Vec3 right) {
        return left.sub(right);
      }
    };
  }

  public static Operator<Vec3, Vec3, Vec3> forMultiplicationWithVec3() {
    return new NamedOperator<Vec3, Vec3, Vec3>("*") {
      @Override
      public Vec3 op(Vec3 left, Vec3 right) {
        return left.mul(right);
      }
    };
  }

  public static Operator<Vec3, Vec3, Vec3> forDivisionWithVec3() {
    return new NamedOperator<Vec3, Vec3, Vec3>("/") {
      @Override
      public Vec3 op(Vec3 left, Vec3 right) {
        return left.div(right);
      }
    };
  }
}
