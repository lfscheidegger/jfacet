package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.primitives.Vec2;

/**
 * {@code Operator} objects for {@code Vec2Exp}
 */
public class Vec2Operators {

  public static Operator<Vec2, Float, Vec2> forAdditionWithFloat() {
    return new NamedOperator<Vec2, Float, Vec2>("+") {
      @Override
      public Vec2 op(Vec2 left, Float right) {
        return new Vec2(left.getX() + right, left.getY() + right);
      }
    };
  }

  public static Operator<Vec2, Float, Vec2> forSubtractionWithFloat() {
    return new NamedOperator<Vec2, Float, Vec2>("-") {
      @Override
      public Vec2 op(Vec2 left, Float right) {
        return new Vec2(left.getX() - right, left.getY() - right);
      }
    };
  }

  public static Operator<Vec2, Float, Vec2> forMultiplicationWithFloat() {
    return new NamedOperator<Vec2, Float, Vec2>("*") {
      @Override
      public Vec2 op(Vec2 left, Float right) {
        return new Vec2(left.getX() * right, left.getY() * right);
      }
    };
  }

  public static Operator<Vec2, Float, Vec2> forDivisionWithFloat() {
    return new NamedOperator<Vec2, Float, Vec2>("/") {
      @Override
      public Vec2 op(Vec2 left, Float right) {
        return new Vec2(left.getX() / right, left.getY() / right);
      }
    };
  }

  public static Operator<Vec2, Vec2, Vec2> forAdditionWithVec2() {
    return new NamedOperator<Vec2, Vec2, Vec2>("+") {
      @Override
      public Vec2 op(Vec2 left, Vec2 right) {
        return new Vec2(left.getX() + right.getX(), left.getY() + right.getY());
      }
    };
  }

  public static Operator<Vec2, Vec2, Vec2> forSubtractionWithVec2() {
    return new NamedOperator<Vec2, Vec2, Vec2>("-") {
      @Override
      public Vec2 op(Vec2 left, Vec2 right) {
        return new Vec2(left.getX() - right.getX(), left.getY() - right.getY());
      }
    };
  }

  public static Operator<Vec2, Vec2, Vec2> forMultiplicationWithVec2() {
    return new NamedOperator<Vec2, Vec2, Vec2>("*") {
      @Override
      public Vec2 op(Vec2 left, Vec2 right) {
        return new Vec2(left.getX() * right.getX(), left.getY() * right.getY());
      }
    };
  }

  public static Operator<Vec2, Vec2, Vec2> forDivisionWithVec2() {
    return new NamedOperator<Vec2, Vec2, Vec2>("/") {
      @Override
      public Vec2 op(Vec2 left, Vec2 right) {
        return new Vec2(left.getX() / right.getX(), left.getY() / right.getY());
      }
    };
  }
}
