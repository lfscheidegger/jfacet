package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec2Exp;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;

/**
 * {@code Evaluator} objects for {@code Vec2Exp}
 */
public class Vec2Evaluators {

  public static Evaluator<Vec2> forConstant(final Vec2 c) {
    return new Evaluator<Vec2>() {
      @Override
      public Vec2 evaluate(Expression expression) {
        return c;
      }

      @Override
      public String getGlSlString(Expression expression) {
        return c.toString();
      }
    };
  }

  public static Evaluator<Vec2> forComponents() {
    return new Evaluator<Vec2>() {
      @Override
      public Vec2 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        FloatExp left = (FloatExp)parents.get(0);
        FloatExp right = (FloatExp)parents.get(1);

        return new Vec2(left.evaluate(), right.evaluate());
      }

      @Override
      public String getGlSlString(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        return GlSlExpressionHelper.getCommaExpression(Type.VEC2_T, parents.get(0), parents.get(1));
      }
    };
  }

  public static Evaluator<Vec2> forOperationWithFloat(final Operator<Vec2, Float, Vec2> operator) {
    return new BinaryOpEvaluator<Vec2, Float, Vec2>(Type.VEC2_T, operator) {
      @Override
      public Vec2 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Vec2Exp left = (Vec2Exp)parents.get(0);
        FloatExp right = (FloatExp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }

  public static Evaluator<Vec2> forOperationWithVec2(final Operator<Vec2, Vec2, Vec2> operator) {
    return new BinaryOpEvaluator<Vec2, Vec2, Vec2>(Type.VEC2_T, operator) {
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
