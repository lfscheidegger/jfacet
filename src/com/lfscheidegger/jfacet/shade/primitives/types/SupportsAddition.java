package com.lfscheidegger.jfacet.shade.primitives.types;

/**
 * Interface for compound types that support addition against themselves and floats
 */
public interface SupportsAddition<T> {

  T add(float other);
  T add(T other);
}
