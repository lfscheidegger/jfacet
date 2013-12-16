package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;

/**
 * {@code Expression} for boolean values
 */
public final class Bool extends AbstractExpression<Boolean> {

  public static class TernaryElse<T extends Expression> {

    private final Bool mCondition;
    private final T mIfExpression;

    public TernaryElse(Bool condition, T ifExpression) {
      mCondition = condition;
      mIfExpression = ifExpression;
    }

    @SuppressWarnings("unchecked")
    public T else_(T elseExpression) {
      return (T)mIfExpression.getExpressionForTernaryOperator(mCondition, elseExpression);
    }
  }

  public Bool(boolean c) {
    super(c);
  }

  public Bool(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType);
  }

  @Override
  public Bool getExpressionForTernaryOperator(Bool condition, Expression<Boolean> elseExpression) {
    return new Bool(
        ImmutableList.<Expression>of(condition, this, elseExpression),
        NodeType.TERNARY);
  }

  @Override
  public String getGlSlTypeName() {
    return "bool";
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
