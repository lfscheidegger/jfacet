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
        return left.add(right);
      }
    };
  }

  public static Operator<Mat4, Float, Mat4> forSubtractionWithFloat() {
    return new NamedOperator<Mat4, Float, Mat4>("-") {
      @Override
      public Mat4 op(Mat4 left, Float right) {
        return left.sub(right);
      }
    };
  }

  public static Operator<Mat4, Float, Mat4> forMultiplicationWithFloat() {
    return new NamedOperator<Mat4, Float, Mat4>("*") {
      @Override
      public Mat4 op(Mat4 left, Float right) {
        return left.mul(right);
      }
    };
  }

  public static Operator<Mat4, Float, Mat4> forDivisionWithFloat() {
    return new NamedOperator<Mat4, Float, Mat4>("/") {
      @Override
      public Mat4 op(Mat4 left, Float right) {
        return left.div(right);
      }
    };
  }

  public static Operator<Mat4, Mat4, Mat4> forAdditionWithMat4() {
    return new NamedOperator<Mat4, Mat4, Mat4>("+") {
      @Override
      public Mat4 op(Mat4 left, Mat4 right) {
        return left.add(right);
      }
    };
  }

  public static Operator<Mat4, Mat4, Mat4> forSubtractionWithMat4() {
    return new NamedOperator<Mat4, Mat4, Mat4>("-") {
      @Override
      public Mat4 op(Mat4 left, Mat4 right) {
        return left.sub(right);
      }
    };
  }

  public static Operator<Mat4, Mat4, Mat4> forMultiplicationWithMat4() {
    return new NamedOperator<Mat4, Mat4, Mat4>("*") {
      @Override
      public Mat4 op(Mat4 left, Mat4 right) {
        return left.mul(right);
      }
    };
  }

  public static Operator<Mat4, Mat4, Mat4> forDivisionWithMat4() {
    return new NamedOperator<Mat4, Mat4, Mat4>("/") {
      @Override
      public Mat4 op(Mat4 left, Mat4 right) {
        return left.div(right);
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
