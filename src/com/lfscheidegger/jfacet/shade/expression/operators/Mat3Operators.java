package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.primitives.Mat3;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;

/**
 * {@code Operator} objects for {@code Mat3Exp}
 */
public class Mat3Operators {

  public static Operator<Mat3, Vec3, Vec3> forMultiplicationWithVec3() {
    return new NamedOperator<Mat3, Vec3, Vec3>("*") {
      @Override
      public Vec3 op(Mat3 left, Vec3 right) {
        return left.mul(right);
      }
    };
  }
}
