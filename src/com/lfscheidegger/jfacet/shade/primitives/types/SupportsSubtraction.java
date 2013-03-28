package com.lfscheidegger.jfacet.shade.primitives.types;

/**
 * Interface for compound types that support subtraction against themselves and floats
 */
public interface SupportsSubtraction<T> {

  T sub(float other);
  T sub(T other);
}
