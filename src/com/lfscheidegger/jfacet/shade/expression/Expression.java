// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;

public abstract class Expression {

  private final ImmutableList<Expression> mParents;
  private final NodeType mNodeType;
  private final String mGlSlTypeName;

  public <T> Expression(T primitive, String glSlTypeName) {
    mParents = ImmutableList.of();
    mNodeType = NodeType.PrimitiveNodeType.forPrimitive(primitive);
    mGlSlTypeName = glSlTypeName;
  }

  public Expression(NodeType nodeType, String glSlTypeName) {
    mParents = ImmutableList.of();
    mNodeType = nodeType;
    mGlSlTypeName = glSlTypeName;
  }

  public Expression(ImmutableList<Expression> parents, NodeType nodeType, String glSlTypeName) {
    mParents = parents;
    mNodeType = nodeType;
    mGlSlTypeName = glSlTypeName;
  }

  public ImmutableList<Expression> getParents() {
    return mParents;
  }

  public NodeType getNodeType() {
    return mNodeType;
  }

  public final String getGlSlTypeName() { return mGlSlTypeName; }
}
