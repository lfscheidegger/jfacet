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
