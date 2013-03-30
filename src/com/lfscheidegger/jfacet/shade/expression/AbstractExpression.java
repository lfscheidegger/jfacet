package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;

public abstract class AbstractExpression<T> implements Expression<T> {

  private final Type mType;
  private final ImmutableList<Expression> mParents;
  private final Evaluator<T> mEvaluator;

  public AbstractExpression(Type type, ImmutableList<Expression> parents, Evaluator<T> evaluator) {
    mType = type;
    mParents = parents;
    mEvaluator = evaluator;
  }

  @Override
  public final Type getType() {
    return mType;
  }

  @Override
  public final T evaluate() {
    return mEvaluator.evaluate(mParents);
  }

  @Override
  public final ImmutableList<Expression> getParents() {
    return mParents;
  }
}
