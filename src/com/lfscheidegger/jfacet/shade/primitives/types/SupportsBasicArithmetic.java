package com.lfscheidegger.jfacet.shade.primitives.types;

/**
 * Interface for primitive types that support basic linear algebaric operations
 */
public interface SupportsBasicArithmetic<T> {

  public T add(float other);
  public T add(T other);

  public T sub(float other);
  public T sub(T other);

  public T mul(float other);
  public T mul(T other);

  public T div(float other);
  public T div(T other);

  public T neg();
}
