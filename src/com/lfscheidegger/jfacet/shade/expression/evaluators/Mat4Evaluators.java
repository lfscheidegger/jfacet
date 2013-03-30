package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Mat4Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec4Exp;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;
import com.lfscheidegger.jfacet.shade.primitives.Mat4;

/**
 * {@code Evaluator} objects for {@code Mat4Exp}
 */
public class Mat4Evaluators {

  public static Evaluator<Mat4> forConstant(final Mat4 c) {
    return new Evaluator<Mat4>() {
      @Override
      public Mat4 evaluate(Expression expression) {
        return c;
      }

      @Override
      public String getGlSlString(Expression expression) {
        return c.toString();
      }
    };
  }

  public static Evaluator<Mat4> forComponents() {
    return new Evaluator<Mat4>() {
      @Override
      public Mat4 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Vec4Exp x = (Vec4Exp)parents.get(0);
        Vec4Exp y = (Vec4Exp)parents.get(1);
        Vec4Exp z = (Vec4Exp)parents.get(2);
        Vec4Exp w = (Vec4Exp)parents.get(3);

        return new Mat4(x.evaluate(), y.evaluate(), z.evaluate(), w.evaluate());
      }

      @Override
      public String getGlSlString(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        return GlSlExpressionHelper.getCommaExpression(
            Type.MAT4_T, parents.get(0), parents.get(1), parents.get(2), parents.get(3));
      }
    };
  }

  public static Evaluator<Mat4> forOperationWithFloat(final Operator<Mat4, Float, Mat4> operator) {
    return new BinaryOpEvaluator<Mat4, Float, Mat4>(Type.MAT4_T, operator) {
      @Override
      public Mat4 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Mat4Exp left = (Mat4Exp)parents.get(0);
        FloatExp right = (FloatExp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }

  public static Evaluator<Mat4> forOperationWithMat4(final Operator<Mat4, Mat4, Mat4> operator) {
    return new BinaryOpEvaluator<Mat4, Mat4, Mat4>(Type.MAT4_T, operator) {
      @Override
      public Mat4 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Mat4Exp left = (Mat4Exp)parents.get(0);
        Mat4Exp right = (Mat4Exp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }
}
