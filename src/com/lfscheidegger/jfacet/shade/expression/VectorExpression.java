package com.lfscheidegger.jfacet.shade.expression;

public interface VectorExpression<T> extends Expression<T> {

  public FloatExp get(int idx);
}
