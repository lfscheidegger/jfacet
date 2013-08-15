package com.lfscheidegger.jfacet.shade.expression;

public interface VectorExpression<T> extends SupportsBasicArithmetic<T> {

  public T add(Real right);

  public T sub(Real right);

  public T mul(Real right);

  public T div(Real right);

  public Vector4 fill(Vector4 defaultExpression);
}
