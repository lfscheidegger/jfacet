package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;

public class FloatEvaluators {

  public static Evaluator<Float> forConstant(final float c) {
    return new Evaluator<Float>() {
      @Override
      public Float evaluate(Expression expression) {
        return c;
      }

      @Override
      public String getGlSlString(Expression expression) {
        return GlSlExpressionHelper.getWrappedExpression(Type.FLOAT_T, String.valueOf(c));
      }
    };
  }

  public static Evaluator<Float> forOperation(final Operator<Float, Float, Float> operator) {
    return new BinaryOpEvaluator<Float, Float, Float>(Type.FLOAT_T, operator) {
      @Override
      public Float evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        FloatExp left = (FloatExp)parents.get(0);
        FloatExp right = (FloatExp)parents.get(1);
        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }
}
