package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
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

  public static Evaluator<Vec3> forConstant(final Vec3 c) {
    return new Evaluator<Vec3>() {
      @Override
      public Vec3 evaluate(Expression expression) {
        return c;
      }

      @Override
      public String getGlSlString(Expression expression) {
        return c.toString();
      }
    };
  }

  public static Evaluator<Vec3> forComponents() {
    return new Evaluator<Vec3>() {
      @Override
      public Vec3 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        FloatExp x = (FloatExp)parents.get(0);
        FloatExp y = (FloatExp)parents.get(1);
        FloatExp z = (FloatExp)parents.get(2);

        return new Vec3(x.evaluate(), y.evaluate(), z.evaluate());
      }

      @Override
      public String getGlSlString(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        return GlSlExpressionHelper.getCommaExpression(Type.VEC3_T, parents.get(0), parents.get(1), parents.get(2));
      }
    };
  }

  public static Evaluator<Vec3> forMat3Component(final int idx) {
    return new Evaluator<Vec3>() {
      @Override
      public Vec3 evaluate(Expression expression) {
        return ((Mat3Exp)expression.getParents().get(0)).evaluate().get(idx);
      }

      @Override
      public String getGlSlString(Expression expression) {
        return GlSlExpressionHelper.getComponentExpression(Type.VEC3_T, (Expression) expression.getParents().get(0), idx);
      }
    };
  }

  public static Evaluator<Vec3> forOperationWithFloat(final Operator<Vec3, Float, Vec3> operator) {
    return new BinaryOpEvaluator<Vec3, Float, Vec3>(Type.VEC3_T, operator) {
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
    return new BinaryOpEvaluator<Vec3, Vec3, Vec3>(Type.VEC3_T, operator) {
      @Override
      public Vec3 evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        Vec3Exp left = (Vec3Exp)parents.get(0);
        Vec3Exp right = (Vec3Exp)parents.get(1);

        return operator.op(left.evaluate(), right.evaluate());
      }
    };
  }
}