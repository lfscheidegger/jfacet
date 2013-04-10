package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.primitives.Mat2;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;

/**
 * {@code Operator} objects for {@code Mat2Exp}
 */
public class Mat2Operators {

  public static Operator<Mat2, Vec2, Vec2> forMultiplicationWithVec2() {
    return new NamedOperator<Mat2, Vec2, Vec2>("*") {
      @Override
      public Vec2 op(Mat2 left, Vec2 right) {
        return left.mul(right);
      }
    };
  }
}
