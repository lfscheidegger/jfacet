package com.lfscheidegger.jfacet.shade.primitives.types;

/**
 * Interface for compound types that support subtraction against themselves and floats
 */
public interface SupportsSubtraction<T> {

  public T sub(float other);
  public T sub(T other);
}
