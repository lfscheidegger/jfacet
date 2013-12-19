package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;

public abstract class AbstractExpression<T> implements Expression<T> {

  private final NodeType mNodeType;
  private final ImmutableList<Expression> mParents;

  public AbstractExpression(T primitive) {
    mNodeType = NodeType.PrimitiveNodeType.forPrimitive(primitive);
    mParents = ImmutableList.of();
  }

  public AbstractExpression(NodeType nodeType) {
    mNodeType = nodeType;
    mParents = ImmutableList.of();
  }

  public AbstractExpression(ImmutableList<Expression> parents, NodeType nodeType) {
    mNodeType = nodeType;
    mParents = parents;
  }

  @Override
  public ImmutableList<Expression> getParents() {
    return mParents;
  }

  @Override
  public NodeType getNodeType() {
    return mNodeType;
  }
}
