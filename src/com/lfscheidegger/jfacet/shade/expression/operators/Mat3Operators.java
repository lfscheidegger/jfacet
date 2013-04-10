package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.primitives.Mat3;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;

/**
 * {@code Operator} objects for {@code Mat3Exp}
 */
public class Mat3Operators {

  public static Operator<Mat3, Float, Mat3> forAdditionWithFloat() {
    return new NamedOperator<Mat3, Float, Mat3>("+") {
      @Override
      public Mat3 op(Mat3 left, Float right) {
        return left.add(right);
      }
    };
  }

  public static Operator<Mat3, Float, Mat3> forSubtractionWithFloat() {
    return new NamedOperator<Mat3, Float, Mat3>("-") {
      @Override
      public Mat3 op(Mat3 left, Float right) {
        return left.sub(right);
      }
    };
  }

  public static Operator<Mat3, Float, Mat3> forMultiplicationWithFloat() {
    return new NamedOperator<Mat3, Float, Mat3>("*") {
      @Override
      public Mat3 op(Mat3 left, Float right) {
        return left.mul(right);
      }
    };
  }

  public static Operator<Mat3, Float, Mat3> forDivisionWithFloat() {
    return new NamedOperator<Mat3, Float, Mat3>("/") {
      @Override
      public Mat3 op(Mat3 left, Float right) {
        return left.div(right);
      }
    };
  }

  public static Operator<Mat3, Mat3, Mat3> forAdditionWithMat3() {
    return new NamedOperator<Mat3, Mat3, Mat3>("+") {
      @Override
      public Mat3 op(Mat3 left, Mat3 right) {
        return left.add(right);
      }
    };
  }

  public static Operator<Mat3, Mat3, Mat3> forSubtractionWithMat3() {
    return new NamedOperator<Mat3, Mat3, Mat3>("-") {
      @Override
      public Mat3 op(Mat3 left, Mat3 right) {
        return left.sub(right);
      }
    };
  }

  public static Operator<Mat3, Mat3, Mat3> forMultiplicationWithMat3() {
    return new NamedOperator<Mat3, Mat3, Mat3>("*") {
      @Override
      public Mat3 op(Mat3 left, Mat3 right) {
        return left.mul(right);
      }
    };
  }

  public static Operator<Mat3, Mat3, Mat3> forDivisionWithMat3() {
    return new NamedOperator<Mat3, Mat3, Mat3>("/") {
      @Override
      public Mat3 op(Mat3 left, Mat3 right) {
        return left.div(right);
      }
    };
  }

  public static Operator<Mat3, Vec3, Vec3> forMultiplicationWithVec3() {
    return new NamedOperator<Mat3, Vec3, Vec3>("*") {
      @Override
      public Vec3 op(Mat3 left, Vec3 right) {
        return left.mul(right);
      }
    };
  }
}
