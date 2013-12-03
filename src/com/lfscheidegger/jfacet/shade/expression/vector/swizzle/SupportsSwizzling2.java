package com.lfscheidegger.jfacet.shade.expression.vector.swizzle;

public interface SupportsSwizzling2<DIM1, DIM2, DIM3, DIM4> {

  public DIM1 swizzle(Swizzle.D21 value);

  public DIM2 swizzle(Swizzle.D22 value);

  public DIM3 swizzle(Swizzle.D23 value);

  public DIM4 swizzle(Swizzle.D24 value);
}
