package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.Vec3Exp;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;

/**
 * {@code Evaluator} objects for {@code Vec3Exp}
 */
public class Vec3Evaluators {

  public static Evaluator<Vec3> forConstant(final Vec3 c) {
    return new Evaluator<Vec3>() {
      @Override
      public Vec3 evaluate(ImmutableList<Expression> parents) {
        return c;
      }
    };
  }

  public static Evaluator<Vec3> forComponents() {
    return new Evaluator<Vec3>() {
      @Override
      public Vec3 evaluate(ImmutableList<Expression> parents) {
        FloatExp x = (FloatExp)parents.get(0);
        FloatExp y = (FloatExp)parents.get(1);
        FloatExp z = (FloatExp)parents.get(2);

        return new Vec3(x.evaluate(), y.evaluate(), z.evaluate());
      }
    };
  }

  public static Evaluator<Vec3> forOperationWithFloat(final Operator<Vec3, Float, Vec3> operator) {
    return new Evaluator<Vec3>() {
      @Override
      public Vec3 evaluate(ImmutableList<Expression> parents) {
        Vec3Exp left = (Vec3Exp)parents.get(0);
        FloatExp right = (FloatExp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }

  public static Evaluator<Vec3> forOperationWithVec3(final Operator<Vec3, Vec3, Vec3> operator) {
    return new Evaluator<Vec3>() {
      @Override
      public Vec3 evaluate(ImmutableList<Expression> parents) {
        Vec3Exp left = (Vec3Exp)parents.get(0);
        Vec3Exp right = (Vec3Exp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }
}
