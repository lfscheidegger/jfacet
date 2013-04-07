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

  public static Evaluator<Vec3> forConstant(final Vec3 c) {
    return new Evaluator<Vec3>() {
      @Override
      public Vec3 evaluate(Expression expression) {
        return c;
      }

      @Override
      public String getGlSlString(Expression expression, CompilationContext context) {
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
      public String getGlSlString(Expression expression, CompilationContext context) {
        ImmutableList<Expression> parents = expression.getParents();
        return GlSlExpressionHelper.getCommaExpression(TYPE,
            parents.get(0).getGlSlString(context),
            parents.get(1).getGlSlString(context),
            parents.get(2).getGlSlString(context));
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

  public static Evaluator<Vec3> forNegation() {
    return new Evaluator<Vec3>() {
      @Override
      public Vec3 evaluate(Expression expression) {
        Vec3Exp parent = (Vec3Exp)expression.getParents().get(0);
        return parent.evaluate().neg();
      }

      @Override
      public String getGlSlString(Expression expression, CompilationContext context) {
        return GlSlExpressionHelper.getUnOpExpression(
            TYPE, "-", ((Expression)expression.getParents().get(0)).getGlSlString(context));
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