package com.lfscheidegger.jfacet.shade.primitives.types;


/**
 * Interface for compound types that support multiplication against themselves and floats
 */
public interface SupportsMultiplication<T> {

  public T mul(float other);
  public T mul(T other);
}
