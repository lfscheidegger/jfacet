package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.primitives.Mat2;

/**
 * {@code Operator} objects for {@code Mat2Exp}
 */
public class Mat2Operators {

  public static Operator<Mat2, Float, Mat2> forAdditionWithFloat() {
    return new NamedOperator<Mat2, Float, Mat2>("+") {
      @Override
      public Mat2 op(Mat2 left, Float right) {
        return new Mat2(left.getC0().add(right), left.getC1().add(right));
      }
    };
  }

  public static Operator<Mat2, Float, Mat2> forSubtractionWithFloat() {
    return new NamedOperator<Mat2, Float, Mat2>("-") {
      @Override
      public Mat2 op(Mat2 left, Float right) {
        return new Mat2(left.getC0().sub(right), left.getC1().sub(right));
      }
    };
  }

  public static Operator<Mat2, Float, Mat2> forMultiplicationWithFloat() {
    return new NamedOperator<Mat2, Float, Mat2>("*") {
      @Override
      public Mat2 op(Mat2 left, Float right) {
        return new Mat2(left.getC0().mul(right), left.getC1().mul(right));
      }
    };
  }

  public static Operator<Mat2, Float, Mat2> forDivisionWithFloat() {
    return new NamedOperator<Mat2, Float, Mat2>("/") {
      @Override
      public Mat2 op(Mat2 left, Float right) {
        return new Mat2(left.getC0().div(right), left.getC1().div(right));
      }
    };
  }

  public static Operator<Mat2, Mat2, Mat2> forAdditionWithMat2() {
    return new NamedOperator<Mat2, Mat2, Mat2>("+") {
      @Override
      public Mat2 op(Mat2 left, Mat2 right) {
        return new Mat2(left.getC0().add(right.getC0()), left.getC1().add(right.getC1()));
      }
    };
  }

  public static Operator<Mat2, Mat2, Mat2> forSubtractionWithMat2() {
    return new NamedOperator<Mat2, Mat2, Mat2>("-") {
      @Override
      public Mat2 op(Mat2 left, Mat2 right) {
        return new Mat2(left.getC0().sub(right.getC0()), left.getC1().sub(right.getC1()));
      }
    };
  }

  public static Operator<Mat2, Mat2, Mat2> forMultiplicationWithMat2() {
    return new NamedOperator<Mat2, Mat2, Mat2>("*") {
      @Override
      public Mat2 op(Mat2 left, Mat2 right) {
        return new Mat2(left.getC0().mul(right.getC0()), left.getC1().mul(right.getC1()));
      }
    };
  }

  public static Operator<Mat2, Mat2, Mat2> forDivisionWithMat2() {
    return new NamedOperator<Mat2, Mat2, Mat2>("/") {
      @Override
      public Mat2 op(Mat2 left, Mat2 right) {
        return new Mat2(left.getC0().div(right.getC0()), left.getC1().div(right.getC1()));
      }
    };
  }
}
