package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec2Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec3Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec4Exp;

public class FloatEvaluators {

  public static Evaluator<Float> forVec2Dot() {
    return new FunctionEvaluator<Float>(Type.FLOAT_T, "dot") {
      @Override
      public Float evaluate(Expression expression) {
        Vec2Exp left = (Vec2Exp)expression.getParents().get(0);
        Vec2Exp right = (Vec2Exp)expression.getParents().get(1);

        return left.evaluate().dot(right.evaluate());
      }
    };
  }

  public static Evaluator<Float> forVec3Dot() {
    return new FunctionEvaluator<Float>(Type.FLOAT_T, "dot") {
      @Override
      public Float evaluate(Expression expression) {
        Vec3Exp left = (Vec3Exp)expression.getParents().get(0);
        Vec3Exp right = (Vec3Exp)expression.getParents().get(1);

        return left.evaluate().dot(right.evaluate());
      }
    };
  }

  public static Evaluator<Float> forVec4Dot() {
    return new FunctionEvaluator<Float>(Type.FLOAT_T, "dot") {
      @Override
      public Float evaluate(Expression expression) {
        Vec4Exp left = (Vec4Exp)expression.getParents().get(0);
        Vec4Exp right = (Vec4Exp)expression.getParents().get(1);

        return left.evaluate().dot(right.evaluate());
      }
    };
  }
}