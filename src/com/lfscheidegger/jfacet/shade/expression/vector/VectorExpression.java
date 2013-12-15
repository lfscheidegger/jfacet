package com.lfscheidegger.jfacet.shade.expression.vector;

public interface VectorExpression<T, VEC4_T> {

  public T get(int idx);

  public VEC4_T fill(VEC4_T defaultExpression);
}
