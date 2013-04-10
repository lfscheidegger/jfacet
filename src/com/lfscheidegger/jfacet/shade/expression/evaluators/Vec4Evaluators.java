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
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

/**
 * {@code Evaluator} objects for {@code Vec4Exp}
 */
public class Vec4Evaluators {

  private static final Type TYPE = Type.VEC4_T;

  public static Evaluator<Vec4> forMat4Component(final int idx) {
    return new Evaluator<Vec4>() {
      @Override
      public Vec4 evaluate(Expression expression) {
        return ((Mat4Exp)expression.getParents().get(0)).evaluate().get(idx);
      }

      @Override
      public String getGlSlString(Expression expression, CompilationContext context) {
        return GlSlExpressionHelper.getComponentExpression(
            TYPE, ((Expression)expression.getParents().get(0)).getGlSlString(context), idx);
      }
    };
  }

  public static Evaluator<Vec4> forOperationWithFloat(final Operator<Vec4, Float, Vec4> operator) {
    return new BinaryOpEvaluator<Vec4, Float, Vec4>(TYPE, operator) {
      @Override
      public Vec4 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Vec4Exp left = (Vec4Exp)parents.get(0);
        FloatExp right = (FloatExp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }

  public static Evaluator<Vec4> forOperationWithVec4(final Operator<Vec4, Vec4, Vec4> operator) {
    return new BinaryOpEvaluator<Vec4, Vec4, Vec4>(TYPE, operator) {
      @Override
      public Vec4 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Vec4Exp left = (Vec4Exp)parents.get(0);
        Vec4Exp right = (Vec4Exp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }
}