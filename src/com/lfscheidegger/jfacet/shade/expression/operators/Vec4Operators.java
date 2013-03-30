package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.primitives.Vec4;

/**
 * {@code Operator} objects for {@code Vec4Exp}
 */
public class Vec4Operators {

  public static Operator<Vec4, Float, Vec4> forAdditionWithFloat() {
    return new Operator<Vec4, Float, Vec4>() {
      @Override
      public Vec4 op(Vec4 left, Float right) {
        return new Vec4(left.getX() + right, left.getY() + right, left.getZ() + right, left.getW() + right);
      }
    };
  }

  public static Operator<Vec4, Float, Vec4> forSubtractionWithFloat() {
    return new Operator<Vec4, Float, Vec4>() {
      @Override
      public Vec4 op(Vec4 left, Float right) {
        return new Vec4(left.getX() - right, left.getY() - right, left.getZ() - right, left.getW() - right);
      }
    };
  }

  public static Operator<Vec4, Float, Vec4> forMultiplicationWithFloat() {
    return new Operator<Vec4, Float, Vec4>() {
      @Override
      public Vec4 op(Vec4 left, Float right) {
        return new Vec4(left.getX() * right, left.getY() * right, left.getZ() * right, left.getW() * right);
      }
    };
  }

  public static Operator<Vec4, Float, Vec4> forDivisionWithFloat() {
    return new Operator<Vec4, Float, Vec4>() {
      @Override
      public Vec4 op(Vec4 left, Float right) {
        return new Vec4(left.getX() / right, left.getY() / right, left.getZ() / right, left.getW() / right);
      }
    };
  }

  public static Operator<Vec4, Vec4, Vec4> forAdditionWithVec4() {
    return new Operator<Vec4, Vec4, Vec4>() {
      @Override
      public Vec4 op(Vec4 left, Vec4 right) {
        return new Vec4(left.getX() + right.getX(), left.getY() + right.getY(), left.getZ() + right.getZ(), left.getW() + right.getW());
      }
    };
  }

  public static Operator<Vec4, Vec4, Vec4> forSubtractionWithVec4() {
    return new Operator<Vec4, Vec4, Vec4>() {
      @Override
      public Vec4 op(Vec4 left, Vec4 right) {
        return new Vec4(left.getX() - right.getX(), left.getY() - right.getY(), left.getZ() - right.getZ(), left.getW() - right.getW());
      }
    };
  }

  public static Operator<Vec4, Vec4, Vec4> forMultiplicationWithVec4() {
    return new Operator<Vec4, Vec4, Vec4>() {
      @Override
      public Vec4 op(Vec4 left, Vec4 right) {
        return new Vec4(left.getX() * right.getX(), left.getY() * right.getY(), left.getZ() * right.getZ(), left.getW() * right.getW());
      }
    };
  }

  public static Operator<Vec4, Vec4, Vec4> forDivisionWithVec4() {
    return new Operator<Vec4, Vec4, Vec4>() {
      @Override
      public Vec4 op(Vec4 left, Vec4 right) {
        return new Vec4(left.getX() / right.getX(), left.getY() / right.getY(), left.getZ() / right.getZ(), left.getW() / right.getW());
      }
    };
  }
}
