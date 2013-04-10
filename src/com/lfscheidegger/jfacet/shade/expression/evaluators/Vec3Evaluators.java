package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec3Exp;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;

/**
 * {@code Evaluator} objects for {@code Vec3Exp}
 */
public class Vec3Evaluators {

  public static Evaluator<Vec3> forCrossProduct() {
    return new FunctionEvaluator<Vec3>(Type.VEC3_T, "cross") {
      @Override
      public Vec3 evaluate(Expression expression) {
        Vec3Exp left = (Vec3Exp)expression.getParents().get(0);
        Vec3Exp right = (Vec3Exp)expression.getParents().get(1);
        return left.evaluate().cross(right.evaluate());
      }
    };
  }
}