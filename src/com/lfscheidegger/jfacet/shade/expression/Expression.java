package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;

public abstract class Expression {

  private final NodeType mNodeType;
  private final ImmutableList<Expression> mParents;

  public <T> Expression(T primitive) {
    mNodeType = NodeType.PrimitiveNodeType.forPrimitive(primitive);
    mParents = ImmutableList.of();
  }

  public Expression(NodeType nodeType) {
    mNodeType = nodeType;
    mParents = ImmutableList.of();
  }

  public Expression(ImmutableList<Expression> parents, NodeType nodeType) {
    mNodeType = nodeType;
    mParents = parents;
  }

  public ImmutableList<Expression> getParents() {
    return mParents;
  }

  public NodeType getNodeType() {
    return mNodeType;
  }

  public abstract String getGlSlTypeName();
}
