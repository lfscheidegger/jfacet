package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec2Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec3Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec4Exp;

public class FloatEvaluators {

  private final static Type TYPE = Type.FLOAT_T;

  public static Evaluator<Float> forOperation(final Operator<Float, Float, Float> operator) {
    return new BinaryOpEvaluator<Float, Float, Float>(TYPE, operator) {
      @Override
      public Float evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        FloatExp left = (FloatExp)parents.get(0);
        FloatExp right = (FloatExp)parents.get(1);
        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }

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