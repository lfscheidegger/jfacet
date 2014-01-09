// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;

/**
 * {@code Expression} for boolean values
 */
public final class Bool extends Expression {

  private static final String GLSL_TYPE_NAME = "bool";

  @SuppressWarnings("all")
  private static <T extends Expression> T getExpressionForTernaryOperator(
      Bool condition,
      T ifExpression,
      T elseExpression) {
    ImmutableList<Expression> parents = ImmutableList.<Expression>of(
        condition,
        ifExpression,
        elseExpression);
    NodeType nodeType = NodeType.TERNARY;
    if (ifExpression instanceof Real) return (T)new Real(parents, nodeType);
    if (ifExpression instanceof Bool) return (T)new Bool(parents, nodeType);
    if (ifExpression instanceof Vec2) return (T)new Vec2(parents, nodeType);
    if (ifExpression instanceof Vec3) return (T)new Vec3(parents, nodeType);
    if (ifExpression instanceof Vec4) return (T)new Vec4(parents, nodeType);
    if (ifExpression instanceof BVec2) return (T)new BVec2(parents, nodeType);
    if (ifExpression instanceof BVec3) return (T)new BVec3(parents, nodeType);
    if (ifExpression instanceof BVec4) return (T)new BVec4(parents, nodeType);
    if (ifExpression instanceof Mat2) return (T)new Mat2(parents, nodeType);
    if (ifExpression instanceof Mat3) return (T)new Mat3(parents, nodeType);
    if (ifExpression instanceof Mat4) return (T)new Mat4(parents, nodeType);
    throw new IllegalArgumentException(
        "Can't use ternary operator for " + ifExpression.getClass().getSimpleName());
  }

  public static class TernaryElse<T extends Expression> {

    private final Bool mCondition;
    private final T mIfExpression;

    public TernaryElse(Bool condition, T ifExpression) {
      mCondition = condition;
      mIfExpression = ifExpression;
    }

    @SuppressWarnings("unchecked")
    public T else_(T elseExpression) {
      return Bool.getExpressionForTernaryOperator(mCondition, mIfExpression, elseExpression);
    }
  }

  public Bool(boolean c) {
    super(c, GLSL_TYPE_NAME);
  }

  public Bool(NodeType nodeType) {
    super(nodeType, GLSL_TYPE_NAME);
  }

  public Bool(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType, GLSL_TYPE_NAME);
  }

  public Bool and(Bool right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.AND);
  }

  public Bool and(boolean right) {
    return and(new Bool(right));
  }

  public Bool or(Bool right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.OR);
  }

  public Bool or(boolean right) {
    return or(new Bool(right));
  }

  public Bool xor(Bool right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.XOR);
  }

  public Bool xor(boolean right) {
    return xor(new Bool(right));
  }

  public <T extends Expression> TernaryElse<T> if_(T ifExpression) {
    return new TernaryElse<T>(this, ifExpression);
  }
}
