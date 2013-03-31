package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.primitives.Mat4;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

/**
 * {@code Operator} objects for {@code Mat4Exp}
 */
public class Mat4Operators {

  public static Operator<Mat4, Float, Mat4> forAdditionWithFloat() {
    return new NamedOperator<Mat4, Float, Mat4>("+") {
      @Override
      public Mat4 op(Mat4 left, Float right) {
        return new Mat4(
            left.getC0().add(right),
            left.getC1().add(right),
            left.getC2().add(right),
            left.getC3().add(right));
      }
    };
  }

  public static Operator<Mat4, Float, Mat4> forSubtractionWithFloat() {
    return new NamedOperator<Mat4, Float, Mat4>("-") {
      @Override
      public Mat4 op(Mat4 left, Float right) {
        return new Mat4(
            left.getC0().sub(right),
            left.getC1().sub(right),
            left.getC2().sub(right),
            left.getC3().sub(right));
      }
    };
  }

  public static Operator<Mat4, Float, Mat4> forMultiplicationWithFloat() {
    return new NamedOperator<Mat4, Float, Mat4>("*") {
      @Override
      public Mat4 op(Mat4 left, Float right) {
        return new Mat4(
            left.getC0().mul(right),
            left.getC1().mul(right),
            left.getC2().mul(right),
            left.getC3().mul(right));
      }
    };
  }

  public static Operator<Mat4, Float, Mat4> forDivisionWithFloat() {
    return new NamedOperator<Mat4, Float, Mat4>("/") {
      @Override
      public Mat4 op(Mat4 left, Float right) {
        return new Mat4(
            left.getC0().div(right),
            left.getC1().div(right),
            left.getC2().div(right),
            left.getC3().div(right));
      }
    };
  }

  public static Operator<Mat4, Mat4, Mat4> forAdditionWithMat4() {
    return new NamedOperator<Mat4, Mat4, Mat4>("+") {
      @Override
      public Mat4 op(Mat4 left, Mat4 right) {
        return new Mat4(
            left.getC0().add(right.getC0()),
            left.getC1().add(right.getC1()),
            left.getC2().add(right.getC2()),
            left.getC3().add(right.getC3()));
      }
    };
  }

  public static Operator<Mat4, Mat4, Mat4> forSubtractionWithMat4() {
    return new NamedOperator<Mat4, Mat4, Mat4>("-") {
      @Override
      public Mat4 op(Mat4 left, Mat4 right) {
        return new Mat4(
            left.getC0().sub(right.getC0()),
            left.getC1().sub(right.getC1()),
            left.getC2().sub(right.getC2()),
            left.getC3().sub(right.getC3()));
      }
    };
  }

  public static Operator<Mat4, Mat4, Mat4> forMultiplicationWithMat4() {
    return new NamedOperator<Mat4, Mat4, Mat4>("*") {
      @Override
      public Mat4 op(Mat4 left, Mat4 right) {
        return new Mat4(
            left.getC0().mul(right.getC0()),
            left.getC1().mul(right.getC1()),
            left.getC2().mul(right.getC2()),
            left.getC3().mul(right.getC3()));
      }
    };
  }

  public static Operator<Mat4, Mat4, Mat4> forDivisionWithMat4() {
    return new NamedOperator<Mat4, Mat4, Mat4>("/") {
      @Override
      public Mat4 op(Mat4 left, Mat4 right) {
        return new Mat4(
            left.getC0().div(right.getC0()),
            left.getC1().div(right.getC1()),
            left.getC2().div(right.getC2()),
            left.getC3().div(right.getC3()));
      }
    };
  }

  public static Operator<Mat4, Vec4, Vec4> forMultiplicationWithVec4() {
    return new NamedOperator<Mat4, Vec4, Vec4>("*") {
      @Override
      public Vec4 op(Mat4 left, Vec4 right) {
        return left.mul(right);
      }
    };
  }
}
