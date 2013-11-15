package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public final class ConstantEvaluator<T> implements Evaluator<T> {

  private final T mValue;

  public ConstantEvaluator(T value) {
    mValue = value;
  }

  @Override
  public T evaluate(Expression<T> expression) {
    return mValue;
  }

  /*@Override
  public String getGlSlString(Expression expression, CompilationContext context) {
    if (mValue instanceof Float) {
      return GlSlExpressionHelper.getWrappedExpression(Type.FLOAT_T, String.valueOf(mValue));
    }

    return mValue.toString();
  }*/
}
