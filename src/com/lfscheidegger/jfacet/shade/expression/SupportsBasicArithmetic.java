package com.lfscheidegger.jfacet.shade.expression;

public interface SupportsBasicArithmetic<T> {

  public T add(float right);
  public T add(T right);

  public T sub(float right);
  public T sub(T right);

  public T mul(float right);
  public T mul(T right);

  public T div(float right);
  public T div(T right);

  public T neg();
}
