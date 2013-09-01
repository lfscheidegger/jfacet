package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;

public abstract class AbstractExpression<T> implements Expression<T> {

  private final Type mType;
  private final GlSlType mGlSlType;
  private ImmutableList<Expression> mParents;
  private Evaluator<T> mEvaluator;

  public AbstractExpression(Type type, GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<T> evaluator) {
    mType = type;
    mGlSlType = glSlType;
    mParents = parents;
    mEvaluator = evaluator;
  }

  @Override
  public final Type getType() {
    return mType;
  }

  @Override
  public final GlSlType getGlSlType() {
    return mGlSlType;
  }

  @Override
  public final T evaluate() {
    return mEvaluator.evaluate(this);
  }

  @Override
  public final Evaluator<T> getEvaluator() {
    return mEvaluator;
  }

  @Override
  public final String getGlSlString(CompilationContext context) {
    return mEvaluator.getGlSlString(this, context);
  }

  @Override
  public final ImmutableList<Expression> getParents() {
    return mParents;
  }
}
