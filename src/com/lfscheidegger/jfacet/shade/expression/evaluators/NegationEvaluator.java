package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.*;

public class NegationEvaluator<T> implements Evaluator<T> {

  @Override
  @SuppressWarnings("unchecked")
  public T evaluate(Expression expression) {
    Expression parent = (Expression)expression.getParents().get(0);
    switch(parent.getType()) {
      case FLOAT_T: return (T)Float.valueOf(- ((FloatExp)parent).evaluate());
      case VEC2_T: return (T)((Vec2Exp)parent).evaluate().neg();
      case VEC3_T: return (T)((Vec3Exp)parent).evaluate().neg();
      case VEC4_T: return (T)((Vec4Exp)parent).evaluate().neg();
      case MAT2_T: return (T)((Mat2Exp)parent).evaluate().neg();
      case MAT3_T: return (T)((Mat3Exp)parent).evaluate().neg();
      case MAT4_T: return (T)((Mat4Exp)parent).evaluate().neg();
    }

    throw new RuntimeException("Cannot negate for type: " + parent.getType());
  }

  @Override
  public String getGlSlString(Expression expression, CompilationContext context) {
    ImmutableList<Expression> parents = expression.getParents();
    return GlSlExpressionHelper.getUnOpExpression(expression.getType(), "-", parents.get(0).getGlSlString(context));
  }
}
