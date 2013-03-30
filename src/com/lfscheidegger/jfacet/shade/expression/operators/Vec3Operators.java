package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.primitives.Vec3;

/**
 * {@code Operator} objects for {@code Vec3Exp}
 */
public class Vec3Operators {

  public static Operator<Vec3, Float, Vec3> forAdditionWithFloat() {
    return new Operator<Vec3, Float, Vec3>() {
      @Override
      public Vec3 op(Vec3 left, Float right) {
        return new Vec3(left.getX() + right, left.getY() + right, left.getZ() + right);
      }
    };
  }

  public static Operator<Vec3, Float, Vec3> forSubtractionWithFloat() {
    return new Operator<Vec3, Float, Vec3>() {
      @Override
      public Vec3 op(Vec3 left, Float right) {
        return new Vec3(left.getX() - right, left.getY() - right, left.getZ() - right);
      }
    };
  }

  public static Operator<Vec3, Float, Vec3> forMultiplicationWithFloat() {
    return new Operator<Vec3, Float, Vec3>() {
      @Override
      public Vec3 op(Vec3 left, Float right) {
        return new Vec3(left.getX() * right, left.getY() * right, left.getZ() * right);
      }
    };
  }

  public static Operator<Vec3, Float, Vec3> forDivisionWithFloat() {
    return new Operator<Vec3, Float, Vec3>() {
      @Override
      public Vec3 op(Vec3 left, Float right) {
        return new Vec3(left.getX() / right, left.getY() / right, left.getZ() / right);
      }
    };
  }

  public static Operator<Vec3, Vec3, Vec3> forAdditionWithVec3() {
    return new Operator<Vec3, Vec3, Vec3>() {
      @Override
      public Vec3 op(Vec3 left, Vec3 right) {
        return new Vec3(left.getX() + right.getX(), left.getY() + right.getY(), left.getZ() + right.getZ());
      }
    };
  }

  public static Operator<Vec3, Vec3, Vec3> forSubtractionWithVec3() {
    return new Operator<Vec3, Vec3, Vec3>() {
      @Override
      public Vec3 op(Vec3 left, Vec3 right) {
        return new Vec3(left.getX() - right.getX(), left.getY() - right.getY(), left.getZ() - right.getZ());
      }
    };
  }

  public static Operator<Vec3, Vec3, Vec3> forMultiplicationWithVec3() {
    return new Operator<Vec3, Vec3, Vec3>() {
      @Override
      public Vec3 op(Vec3 left, Vec3 right) {
        return new Vec3(left.getX() * right.getX(), left.getY() * right.getY(), left.getZ() * right.getZ());
      }
    };
  }

  public static Operator<Vec3, Vec3, Vec3> forDivisionWithVec3() {
    return new Operator<Vec3, Vec3, Vec3>() {
      @Override
      public Vec3 op(Vec3 left, Vec3 right) {
        return new Vec3(left.getX() / right.getX(), left.getY() / right.getY(), left.getZ() / right.getZ());
      }
    };
  }
}
