package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.*;

public class ComponentEvaluator<T> implements Evaluator<T>{

  private final int mIdx;

  public ComponentEvaluator(int idx) {
    mIdx = idx;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T evaluate(Expression expression) {
    Expression parent = (Expression)expression.getParents().get(0);

    switch(parent.getType()) {
      case VEC2_T: return (T)(Float.valueOf(((Vec2Exp)parent).evaluate().get(mIdx)));
      case VEC3_T: return (T)(Float.valueOf(((Vec3Exp)parent).evaluate().get(mIdx)));
      case VEC4_T: return (T)(Float.valueOf(((Vec4Exp)parent).evaluate().get(mIdx)));
      case MAT2_T: return (T)((Mat2Exp)parent).evaluate().get(mIdx);
      case MAT3_T: return (T)((Mat3Exp)parent).evaluate().get(mIdx);
      case MAT4_T: return (T)((Mat4Exp)parent).evaluate().get(mIdx);
    }

    throw new RuntimeException("Cannot evaluate component expression for type: " + parent.getType());
  }

  @Override
  public String getGlSlString(Expression<T> expression, CompilationContext context) {
    return GlSlExpressionHelper.getComponentExpression(
        expression.getType(), ((Expression) expression.getParents().get(0)).getGlSlString(context), mIdx);

  }
}