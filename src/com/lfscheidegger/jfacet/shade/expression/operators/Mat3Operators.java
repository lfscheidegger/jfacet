package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.primitives.Mat3;

/**
 * {@code Operator} objects for {@code Mat3Exp}
 */
public class Mat3Operators {

  public static Operator<Mat3, Float, Mat3> forAdditionWithFloat() {
    return new Operator<Mat3, Float, Mat3>() {
      @Override
      public Mat3 op(Mat3 left, Float right) {
        return new Mat3(left.getC0().add(right), left.getC1().add(right), left.getC2().add(right));
      }
    };
  }

  public static Operator<Mat3, Float, Mat3> forSubtractionWithFloat() {
    return new Operator<Mat3, Float, Mat3>() {
      @Override
      public Mat3 op(Mat3 left, Float right) {
        return new Mat3(left.getC0().sub(right), left.getC1().sub(right), left.getC2().sub(right));
      }
    };
  }

  public static Operator<Mat3, Float, Mat3> forMultiplicationWithFloat() {
    return new Operator<Mat3, Float, Mat3>() {
      @Override
      public Mat3 op(Mat3 left, Float right) {
        return new Mat3(left.getC0().mul(right), left.getC1().mul(right), left.getC2().mul(right));
      }
    };
  }

  public static Operator<Mat3, Float, Mat3> forDivisionWithFloat() {
    return new Operator<Mat3, Float, Mat3>() {
      @Override
      public Mat3 op(Mat3 left, Float right) {
        return new Mat3(left.getC0().div(right), left.getC1().div(right), left.getC2().div(right));
      }
    };
  }

  public static Operator<Mat3, Mat3, Mat3> forAdditionWithMat3() {
    return new Operator<Mat3, Mat3, Mat3>() {
      @Override
      public Mat3 op(Mat3 left, Mat3 right) {
        return new Mat3(left.getC0().add(right.getC0()), left.getC1().add(right.getC1()), left.getC2().add(right.getC2()));
      }
    };
  }

  public static Operator<Mat3, Mat3, Mat3> forSubtractionWithMat3() {
    return new Operator<Mat3, Mat3, Mat3>() {
      @Override
      public Mat3 op(Mat3 left, Mat3 right) {
        return new Mat3(left.getC0().sub(right.getC0()), left.getC1().sub(right.getC1()), left.getC2().sub(right.getC2()));
      }
    };
  }

  public static Operator<Mat3, Mat3, Mat3> forMultiplicationWithMat3() {
    return new Operator<Mat3, Mat3, Mat3>() {
      @Override
      public Mat3 op(Mat3 left, Mat3 right) {
        return new Mat3(left.getC0().mul(right.getC0()), left.getC1().mul(right.getC1()), left.getC2().mul(right.getC2()));
      }
    };
  }

  public static Operator<Mat3, Mat3, Mat3> forDivisionWithMat3() {
    return new Operator<Mat3, Mat3, Mat3>() {
      @Override
      public Mat3 op(Mat3 left, Mat3 right) {
        return new Mat3(left.getC0().div(right.getC0()), left.getC1().div(right.getC1()), left.getC2().div(right.getC2()));
      }
    };
  }
}