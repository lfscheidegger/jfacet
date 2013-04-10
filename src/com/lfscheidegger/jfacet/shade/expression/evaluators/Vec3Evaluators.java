package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Mat3Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec3Exp;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;

/**
 * {@code Evaluator} objects for {@code Vec3Exp}
 */
public class Vec3Evaluators {

  private static final Type TYPE = Type.VEC3_T;

  public static Evaluator<Vec3> forMat3Component(final int idx) {
    return new Evaluator<Vec3>() {
      @Override
      public Vec3 evaluate(Expression expression) {
        return ((Mat3Exp)expression.getParents().get(0)).evaluate().get(idx);
      }

      @Override
      public String getGlSlString(Expression expression, CompilationContext context) {
        return GlSlExpressionHelper.getComponentExpression(TYPE,
            ((Expression)expression.getParents().get(0)).getGlSlString(context), idx);
      }
    };
  }

  public static Evaluator<Vec3> forOperationWithFloat(final Operator<Vec3, Float, Vec3> operator) {
    return new BinaryOpEvaluator<Vec3, Float, Vec3>(TYPE, operator) {
      @Override
      public Vec3 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Vec3Exp left = (Vec3Exp)parents.get(0);
        FloatExp right = (FloatExp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }

  public static Evaluator<Vec3> forOperationWithVec3(final Operator<Vec3, Vec3, Vec3> operator) {
    return new BinaryOpEvaluator<Vec3, Vec3, Vec3>(TYPE, operator) {
      @Override
      public Vec3 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Vec3Exp left = (Vec3Exp)parents.get(0);
        Vec3Exp right = (Vec3Exp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }

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