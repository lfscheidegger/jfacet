package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;

/**
 * Abstract basic implementation of {@code Expression}.
 */
public abstract class AbstractExpression implements Expression {

  private final Type mType;
  private final ImmutableList<Expression> mParents;

  public AbstractExpression(
      Type type,
      ImmutableList<Expression> parents) {
    mType = type;
    mParents = parents;
  }

  @Override
  public final Type getType() {
    return mType;
  }

  @Override
  public final ImmutableList<Expression> getParents() {
    return mParents;
  }
}
