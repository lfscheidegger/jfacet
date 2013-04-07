package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Mat4Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec4Exp;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;
import com.lfscheidegger.jfacet.shade.primitives.Mat4;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

/**
 * {@code Evaluator} objects for {@code Mat4Exp}
 */
public class Mat4Evaluators {

  private static final Type TYPE = Type.MAT4_T;

  public static Evaluator<Mat4> forConstant(final Mat4 c) {
    return new Evaluator<Mat4>() {
      @Override
      public Mat4 evaluate(Expression expression) {
        return c;
      }

      @Override
      public String getGlSlString(Expression expression, CompilationContext context) {
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
      public String getGlSlString(Expression expression, CompilationContext context) {
        ImmutableList<Expression> parents = expression.getParents();
        return GlSlExpressionHelper.getCommaExpression(
            TYPE,
            parents.get(0).getGlSlString(context),
            parents.get(1).getGlSlString(context),
            parents.get(2).getGlSlString(context),
            parents.get(3).getGlSlString(context));
      }
    };
  }

  public static Evaluator<Mat4> forOperationWithFloat(final Operator<Mat4, Float, Mat4> operator) {
    return new BinaryOpEvaluator<Mat4, Float, Mat4>(TYPE, operator) {
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
    return new BinaryOpEvaluator<Mat4, Mat4, Mat4>(TYPE, operator) {
      @Override
      public Mat4 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Mat4Exp left = (Mat4Exp)parents.get(0);
        Mat4Exp right = (Mat4Exp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }

  public static Evaluator<Mat4> forNegation() {
    return new Evaluator<Mat4>() {
      @Override
      public Mat4 evaluate(Expression expression) {
        Mat4Exp parent = (Mat4Exp)expression.getParents().get(0);
        return parent.evaluate().neg();
      }

      @Override
      public String getGlSlString(Expression expression, CompilationContext context) {
        return GlSlExpressionHelper.getUnOpExpression(
            TYPE, "-", ((Expression)expression.getParents().get(0)).getGlSlString(context));
      }
    };
  }

  public static Evaluator<Vec4> forOperationWithVec4(final Operator<Mat4, Vec4, Vec4> operator) {
    return new BinaryOpEvaluator<Mat4, Vec4, Vec4>(Type.VEC4_T, operator) {
      @Override
      public Vec4 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Mat4Exp left = (Mat4Exp)parents.get(0);
        Vec4Exp right = (Vec4Exp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }
}
