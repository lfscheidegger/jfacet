package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.primitives.Mat4;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

/**
 * {@code Operator} objects for {@code Mat4Exp}
 */
public class Mat4Operators {

  public static Operator<Mat4, Vec4, Vec4> forMultiplicationWithVec4() {
    return new NamedOperator<Mat4, Vec4, Vec4>("*") {
      @Override
      public Vec4 op(Mat4 left, Vec4 right) {
        return left.mul(right);
      }
    };
  }
}
