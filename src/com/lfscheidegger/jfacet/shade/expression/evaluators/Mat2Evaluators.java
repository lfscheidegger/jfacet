package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Mat2Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec2Exp;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;
import com.lfscheidegger.jfacet.shade.primitives.Mat2;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;

/**
 * {@code Evaluator} objects for {@code Mat2Exp}
 */
public class Mat2Evaluators {

  private static final Type TYPE = Type.MAT2_T;

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
      public String getGlSlString(Expression expression, CompilationContext context) {
        ImmutableList<Expression> parents = expression.getParents();
        return GlSlExpressionHelper.getCommaExpression(
            TYPE, parents.get(0).getGlSlString(context), parents.get(1).getGlSlString(context));
      }
    };
  }

  public static Evaluator<Mat2> forOperationWithFloat(final Operator<Mat2, Float, Mat2> operator) {
    return new BinaryOpEvaluator<Mat2, Float, Mat2>(TYPE, operator) {
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
    return new BinaryOpEvaluator<Mat2, Mat2, Mat2>(TYPE, operator) {
      @Override
      public Mat2 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Mat2Exp left = (Mat2Exp)parents.get(0);
        Mat2Exp right = (Mat2Exp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }

  public static Evaluator<Vec2> forOperationWithVec2(final Operator<Mat2, Vec2, Vec2> operator) {
    return new BinaryOpEvaluator<Mat2, Vec2, Vec2>(Type.VEC2_T, operator) {
      @Override
      public Vec2 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Mat2Exp left = (Mat2Exp)parents.get(0);
        Vec2Exp right = (Vec2Exp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }

  public static Evaluator<Mat2> forNegation() {
    return new Evaluator<Mat2>() {
      @Override
      public Mat2 evaluate(Expression expression) {
        Mat2Exp parent = (Mat2Exp)expression.getParents().get(0);
        return parent.evaluate().neg();
      }

      @Override
      public String getGlSlString(Expression expression, CompilationContext context) {
        return GlSlExpressionHelper.getUnOpExpression(
            TYPE, "-", ((Expression)expression.getParents().get(0)).getGlSlString(context));
      }
    };
  }
}
