package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public abstract class AbstractExpression<T> implements Expression<T> {

  private final Optional<NodeType> mNodeType;
  private final ImmutableList<Expression> mParents;

  public AbstractExpression(T primitive) {
    mNodeType = Optional.of(NodeType.PrimitiveNodeType.forPrimitive(primitive));
    mParents = ImmutableList.of();
  }

  public AbstractExpression(NodeType nodeType) {
    mNodeType = Optional.of(nodeType);
    mParents = ImmutableList.of();
  }

  public AbstractExpression(ImmutableList<Expression> parents, NodeType nodeType) {
    mNodeType = Optional.of(nodeType);
    mParents = parents;
  }

  @Override
  public ImmutableList<Expression> getParents() {
    return mParents;
  }

  @Override
  public Optional<NodeType> getNodeType() {
    return mNodeType;
  }
}
