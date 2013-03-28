package com.lfscheidegger.jfacet.shade.primitives.types;

/**
 * Interface for compound types that support addition against themselves and floats
 */
public interface SupportsAddition<T> {

  public T add(float other);
  public T add(T other);
}
