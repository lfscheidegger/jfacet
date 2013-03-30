package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;

public class FloatEvaluators {

  public static Evaluator<Float> forConstant(final float c) {
    return new Evaluator<Float>() {
      @Override
      public Float evaluate(ImmutableList<Expression> parents) {
        return c;
      }
    };
  }

  public static Evaluator<Float> forOperation(final Operator<Float, Float, Float> operator) {
    return new Evaluator<Float>() {
      @Override
      public Float evaluate(ImmutableList<Expression> parents) {
        FloatExp left = (FloatExp)parents.get(0);
        FloatExp right = (FloatExp)parents.get(1);
        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }
}
