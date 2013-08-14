package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public final class ConstructorEvaluator<T> implements Evaluator<T> {

  @Override
  @SuppressWarnings("unchecked")
  public T evaluate(Expression expression) {
    ImmutableList<Expression> p = expression.getParents();
    Type type = expression.getType();
    switch(type) {
      case VEC2_T: return (T)(new Vector((Float)p.get(0).evaluate(), (Float)p.get(1).evaluate()));
      case VEC3_T:
        return (T)(new Vector((Float)p.get(0).evaluate(), (Float)p.get(1).evaluate(), (Float)p.get(2).evaluate()));
      case VEC4_T: return (T)(new Vector(
          (Float)p.get(0).evaluate(),
          (Float)p.get(1).evaluate(),
          (Float)p.get(2).evaluate(),
          (Float)p.get(3).evaluate()));
      case MAT2_T: return (T)(new Matrix((Vector)p.get(0).evaluate(), (Vector)p.get(1).evaluate()));
      case MAT3_T:
        return (T)(new Matrix((Vector)p.get(0).evaluate(), (Vector)p.get(1).evaluate(), (Vector)p.get(2).evaluate()));
      case MAT4_T:
        return (T)(new Matrix(
            (Vector)p.get(0).evaluate(),
            (Vector)p.get(1).evaluate(),
            (Vector)p.get(2).evaluate(),
            (Vector)p.get(3).evaluate()));
    }

    throw new RuntimeException("Cannot evaluate constructed expression from type: " + type);
  }

  @Override
  public String getGlSlString(Expression expression, CompilationContext context) {
    ImmutableList<Expression> parents = expression.getParents();
    Type type = expression.getType();
    if (type == Type.VEC2_T || type == Type.MAT2_T) {
      return GlSlExpressionHelper.getCommaExpression(
          type, parents.get(0).getGlSlString(context), parents.get(1).getGlSlString(context));
    } else if (type == Type.VEC3_T || type == Type.MAT3_T) {
      return GlSlExpressionHelper.getCommaExpression(
          type,
          parents.get(0).getGlSlString(context),
          parents.get(1).getGlSlString(context),
          parents.get(2).getGlSlString(context));
    } else if (type == Type.VEC4_T || type == Type.MAT4_T) {
      return GlSlExpressionHelper.getCommaExpression(
          type,
          parents.get(0).getGlSlString(context),
          parents.get(1).getGlSlString(context),
          parents.get(2).getGlSlString(context),
          parents.get(3).getGlSlString(context));
    }

    throw new RuntimeException("Cannot get glsl string from expression type: " + type);
  }
}