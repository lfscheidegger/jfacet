package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
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

  public static Evaluator<Float> forConstant(final float c) {
    return new Evaluator<Float>() {
      @Override
      public Float evaluate(Expression expression) {
        return c;
      }

      @Override
      public String getGlSlString(Expression expression) {
        return GlSlExpressionHelper.getWrappedExpression(TYPE, String.valueOf(c));
      }

      @Override
      public String getGlSlString(Expression expression, CompilationContext context) {
        return getGlSlString(expression);
      }
    };
  }

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

  public static Evaluator<Float> forNegation() {
    return new Evaluator<Float>() {
      @Override
      public Float evaluate(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        return - ((FloatExp)parents.get(0)).evaluate();
      }

      @Override
      public String getGlSlString(Expression expression) {
        ImmutableList<Expression> parents = expression.getParents();
        return GlSlExpressionHelper.getUnOpExpression(TYPE, "-", parents.get(0).getGlSlString());
      }

      @Override
      public String getGlSlString(Expression expression, CompilationContext context) {
        ImmutableList<Expression> parents = expression.getParents();
        return GlSlExpressionHelper.getUnOpExpression(TYPE, "-", context.getExpressionName(parents.get(0)));
      }
    };
  }

  public static Evaluator<Float> forVec2Component(final int idx) {
    return new Evaluator<Float>() {
      @Override
      public Float evaluate(Expression expression) {
        return ((Vec2Exp)expression.getParents().get(0)).evaluate().get(idx);
      }

      @Override
      public String getGlSlString(Expression expression) {
        return GlSlExpressionHelper.getComponentExpression(
            TYPE, ((Expression) expression.getParents().get(0)).getGlSlString(), idx);
      }

      @Override
      public String getGlSlString(Expression expression, CompilationContext context) {
        return GlSlExpressionHelper.getComponentExpression(
            TYPE, context.getExpressionName(((Expression) expression.getParents().get(0))), idx);
      }
    };
  }

  public static Evaluator<Float> forVec3Component(final int idx) {
    return new Evaluator<Float>() {
      @Override
      public Float evaluate(Expression expression) {
        return ((Vec3Exp)expression.getParents().get(0)).evaluate().get(idx);
      }

      @Override
      public String getGlSlString(Expression expression) {
        return GlSlExpressionHelper.getComponentExpression(
            TYPE, ((Expression) expression.getParents().get(0)).getGlSlString(), idx);
      }

      @Override
      public String getGlSlString(Expression expression, CompilationContext context) {
        return GlSlExpressionHelper.getComponentExpression(
            TYPE, context.getExpressionName(((Expression) expression.getParents().get(0))), idx);
      }
    };
  }

  public static Evaluator<Float> forVec4Component(final int idx) {
    return new Evaluator<Float>() {
      @Override
      public Float evaluate(Expression expression) {
        return ((Vec4Exp)expression.getParents().get(0)).evaluate().get(idx);
      }

      @Override
      public String getGlSlString(Expression expression) {
        return GlSlExpressionHelper.getComponentExpression(
            TYPE, ((Expression)expression.getParents().get(0)).getGlSlString(), idx);
      }

      @Override
      public String getGlSlString(Expression expression, CompilationContext context) {
        return GlSlExpressionHelper.getComponentExpression(
            TYPE, context.getExpressionName(((Expression) expression.getParents().get(0))), idx);
      }
    };
  }
}
