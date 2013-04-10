package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.*;

public class ConstructorEvaluator<T> implements Evaluator<T> {

  private final Type mType;


  public ConstructorEvaluator(Type type) {
    mType = type;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T evaluate(Expression expression) {
    ImmutableList<Expression> p = expression.getParents();
    switch(mType) {
      case VEC2_T:
        return (T)(new Vec2((Float)p.get(0).evaluate(), (Float)p.get(1).evaluate()));
      case VEC3_T:
        return (T)(new Vec3((Float)p.get(0).evaluate(), (Float)p.get(1).evaluate(), (Float)p.get(2).evaluate()));
      case VEC4_T:
        return (T)(new Vec4(
            (Float)p.get(0).evaluate(),
            (Float)p.get(1).evaluate(),
            (Float)p.get(2).evaluate(),
            (Float)p.get(3).evaluate()));
      case MAT2_T:
        return (T)(new Mat2((Vec2)p.get(0).evaluate(), (Vec2)p.get(1).evaluate()));
      case MAT3_T:
        return (T)(new Mat3((Vec3)p.get(0).evaluate(), (Vec3)p.get(1).evaluate(), (Vec3)p.get(2).evaluate()));
      case MAT4_T:
        return (T)(new Mat4(
            (Vec4)p.get(0).evaluate(),
            (Vec4)p.get(1).evaluate(),
            (Vec4)p.get(2).evaluate(),
            (Vec4)p.get(3).evaluate()));
    }

    throw new RuntimeException("Cannot evaluate constructed expression from type: " + mType);
  }

  @Override
  public String getGlSlString(Expression expression, CompilationContext context) {
    ImmutableList<Expression> parents = expression.getParents();
    if (mType == Type.VEC2_T || mType == Type.MAT2_T) {
      return GlSlExpressionHelper.getCommaExpression(
          mType, parents.get(0).getGlSlString(context), parents.get(1).getGlSlString(context));
    } else if (mType == Type.VEC3_T || mType == Type.MAT3_T) {
      return GlSlExpressionHelper.getCommaExpression(
          mType,
          parents.get(0).getGlSlString(context),
          parents.get(1).getGlSlString(context),
          parents.get(2).getGlSlString(context));
    } else if (mType == Type.VEC4_T || mType == Type.MAT4_T) {
      return GlSlExpressionHelper.getCommaExpression(
          mType,
          parents.get(0).getGlSlString(context),
          parents.get(1).getGlSlString(context),
          parents.get(2).getGlSlString(context),
          parents.get(3).getGlSlString(context));
    }

    throw new RuntimeException("Cannot get glsl string from expression type: " + mType);
  }
}