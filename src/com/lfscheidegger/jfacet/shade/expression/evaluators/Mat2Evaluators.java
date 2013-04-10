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
}
