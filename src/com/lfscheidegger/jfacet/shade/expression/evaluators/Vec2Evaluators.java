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
import com.lfscheidegger.jfacet.shade.primitives.Vec2;

/**
 * {@code Evaluator} objects for {@code Vec2Exp}
 */
public class Vec2Evaluators {

  private static final Type TYPE = Type.VEC2_T;

  public static Evaluator<Vec2> forOperationWithVec2(final Operator<Vec2, Vec2, Vec2> operator) {
    return new BinaryOpEvaluator<Vec2, Vec2, Vec2>(TYPE, operator) {
      @Override
      public Vec2 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Vec2Exp left = (Vec2Exp)parents.get(0);
        Vec2Exp right = (Vec2Exp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }
}
