package com.lfscheidegger.jfacet.shade.expression.vector.swizzle;

public interface SupportsSwizzling4<DIM1, DIM2, DIM3, DIM4> {

  public DIM1 swizzle(Swizzle.D41 value);

  public DIM2 swizzle(Swizzle.D42 value);

  public DIM3 swizzle(Swizzle.D43 value);

  public DIM4 swizzle(Swizzle.D44 value);
}
