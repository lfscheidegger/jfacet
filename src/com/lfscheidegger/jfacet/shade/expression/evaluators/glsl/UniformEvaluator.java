package com.lfscheidegger.jfacet.shade.expression.evaluators.glsl;

import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;

public final class UniformEvaluator<T> implements Evaluator<T> {

  private T mValue;
  private Refreshable<T> mRefreshable;

  public interface Refreshable<T> {
    public void refresh(UniformEvaluator<T> evaluator);

  }

  public UniformEvaluator() {
    mValue = null;
    mRefreshable = null;
  }

  public UniformEvaluator(Expression<T> value) {
    this(value, null);
  }

  public UniformEvaluator(Expression<T> value, Refreshable<T> refreshable) {
    mValue = value.evaluate();
    mRefreshable = refreshable;
  }

  public void set(T value) {
    mValue = value;
  }

  public void set(Expression<T> value) {
    mValue = value.evaluate();
  }

  public T get() {
    return mValue;
  }

  public void refresh() {
    if (mRefreshable == null) {
      return;
    }

    mRefreshable.refresh(this);
  }

  @Override
  public T evaluate(Expression<T> expression) {
    return get();
  }

  /*@Override
  public String getGlSlString(Expression<T> expression, CompilationContext compilationContext) {
    return compilationContext.getExpressionName(expression);
  }*/
}
