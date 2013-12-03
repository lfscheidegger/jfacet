package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlQualifier;

public abstract class AbstractExpression<T> implements Expression<T> {

  private final Optional<GlSlQualifier> mGlSlQualifier;
  private final Optional<NodeType> mNodeType;
  private final ImmutableList<Expression> mParents;

  public AbstractExpression() {
    mGlSlQualifier = Optional.absent();
    mNodeType = Optional.absent();
    mParents = ImmutableList.of();
  }

  public AbstractExpression(ImmutableList<Expression> parents, NodeType nodeType) {
    mGlSlQualifier = Optional.absent();
    mNodeType = Optional.of(nodeType);
    mParents = parents;
  }

  @Override
  public ImmutableList<Expression> getParents() {
    return mParents;
  }

  @Override
  public Optional<GlSlQualifier> getGlSlQualifier() {
    return mGlSlQualifier;
  }

  @Override
  public Optional<NodeType> getNodeType() {
    return mNodeType;
  }
}
