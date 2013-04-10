package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.primitives.Mat2;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;

/**
 * {@code Operator} objects for {@code Mat2Exp}
 */
public class Mat2Operators {

  public static Operator<Mat2, Float, Mat2> forAdditionWithFloat() {
    return new NamedOperator<Mat2, Float, Mat2>("+") {
      @Override
      public Mat2 op(Mat2 left, Float right) {
        return left.add(right);
      }
    };
  }

  public static Operator<Mat2, Float, Mat2> forSubtractionWithFloat() {
    return new NamedOperator<Mat2, Float, Mat2>("-") {
      @Override
      public Mat2 op(Mat2 left, Float right) {
        return left.sub(right);
      }
    };
  }

  public static Operator<Mat2, Float, Mat2> forMultiplicationWithFloat() {
    return new NamedOperator<Mat2, Float, Mat2>("*") {
      @Override
      public Mat2 op(Mat2 left, Float right) {
        return left.mul(right);
      }
    };
  }

  public static Operator<Mat2, Float, Mat2> forDivisionWithFloat() {
    return new NamedOperator<Mat2, Float, Mat2>("/") {
      @Override
      public Mat2 op(Mat2 left, Float right) {
        return left.div(right);
      }
    };
  }

  public static Operator<Mat2, Mat2, Mat2> forAdditionWithMat2() {
    return new NamedOperator<Mat2, Mat2, Mat2>("+") {
      @Override
      public Mat2 op(Mat2 left, Mat2 right) {
        return left.add(right);
      }
    };
  }

  public static Operator<Mat2, Mat2, Mat2> forSubtractionWithMat2() {
    return new NamedOperator<Mat2, Mat2, Mat2>("-") {
      @Override
      public Mat2 op(Mat2 left, Mat2 right) {
        return left.sub(right);
      }
    };
  }

  public static Operator<Mat2, Mat2, Mat2> forMultiplicationWithMat2() {
    return new NamedOperator<Mat2, Mat2, Mat2>("*") {
      @Override
      public Mat2 op(Mat2 left, Mat2 right) {
        return left.mul(right);
      }
    };
  }

  public static Operator<Mat2, Mat2, Mat2> forDivisionWithMat2() {
    return new NamedOperator<Mat2, Mat2, Mat2>("/") {
      @Override
      public Mat2 op(Mat2 left, Mat2 right) {
        return left.div(right);
      }
    };
  }

  public static Operator<Mat2, Vec2, Vec2> forMultiplicationWithVec2() {
    return new NamedOperator<Mat2, Vec2, Vec2>("*") {
      @Override
      public Vec2 op(Mat2 left, Vec2 right) {
        return left.mul(right);
      }
    };
  }
}
