package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.Mat3Exp;
import com.lfscheidegger.jfacet.shade.expression.Vec3Exp;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;
import com.lfscheidegger.jfacet.shade.primitives.Mat3;

/**
 * {@code Evaluator} objects for {@code Mat3Exp}
 */
public class Mat3Evaluators {

  public static Evaluator<Mat3> forConstant(final Mat3 c) {
    return new Evaluator<Mat3>() {
      @Override
      public Mat3 evaluate(ImmutableList<Expression> parents) {
        return c;
      }
    };
  }

  public static Evaluator<Mat3> forComponents() {
    return new Evaluator<Mat3>() {
      @Override
      public Mat3 evaluate(ImmutableList<Expression> parents) {
        Vec3Exp x = (Vec3Exp)parents.get(0);
        Vec3Exp y = (Vec3Exp)parents.get(1);
        Vec3Exp z = (Vec3Exp)parents.get(2);

        return new Mat3(x.evaluate(), y.evaluate(), z.evaluate());
      }
    };
  }

  public static Evaluator<Mat3> forOperationWithFloat(final Operator<Mat3, Float, Mat3> operator) {
    return new Evaluator<Mat3>() {
      @Override
      public Mat3 evaluate(ImmutableList<Expression> parents) {
        Mat3Exp left = (Mat3Exp)parents.get(0);
        FloatExp right = (FloatExp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }

  public static Evaluator<Mat3> forOperationWithMat3(final Operator<Mat3, Mat3, Mat3> operator) {
    return new Evaluator<Mat3>() {
      @Override
      public Mat3 evaluate(ImmutableList<Expression> parents) {
        Mat3Exp left = (Mat3Exp)parents.get(0);
        Mat3Exp right = (Mat3Exp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }
}
