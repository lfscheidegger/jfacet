package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Mat2Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec2Exp;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;
import com.lfscheidegger.jfacet.shade.primitives.Mat2;

/**
 * {@code Evaluator} objects for {@code Mat2Exp}
 */
public class Mat2Evaluators {

  public static Evaluator<Mat2> forConstant(final Mat2 c) {
    return new Evaluator<Mat2>() {
      @Override
      public Mat2 evaluate(Expression expression) {
        return c;
      }

      @Override
      public String getGlSlString(Expression expression) {
        return c.toString();
      }
    };
  }

  public static Evaluator<Mat2> forComponents() {
    return new Evaluator<Mat2>() {
      @Override
      public Mat2 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Vec2Exp left = (Vec2Exp)parents.get(0);
        Vec2Exp right = (Vec2Exp)parents.get(1);

        return new Mat2(left.evaluate(), right.evaluate());
      }

      @Override
      public String getGlSlString(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        return GlSlExpressionHelper.getCommaExpression(Type.MAT2_T, parents.get(0), parents.get(1));
      }
    };
  }

  public static Evaluator<Mat2> forOperationWithFloat(final Operator<Mat2, Float, Mat2> operator) {
    return new BinaryOpEvaluator<Mat2, Float, Mat2>(Type.MAT2_T, operator) {
      @Override
      public Mat2 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Mat2Exp left = (Mat2Exp)parents.get(0);
        FloatExp right = (FloatExp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }

  public static Evaluator<Mat2> forOperationWithMat2(final Operator<Mat2, Mat2, Mat2> operator) {
    return new BinaryOpEvaluator<Mat2, Mat2, Mat2>(Type.MAT2_T, operator) {
      @Override
      public Mat2 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Mat2Exp left = (Mat2Exp)parents.get(0);
        Mat2Exp right = (Mat2Exp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }
}
