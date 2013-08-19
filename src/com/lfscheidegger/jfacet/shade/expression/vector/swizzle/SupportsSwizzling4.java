package com.lfscheidegger.jfacet.shade.expression.vector.swizzle;

public interface SupportsSwizzling4<DIM1, DIM2, DIM3, DIM4> {

  public DIM1 swizzle(S.D41 value);

  public DIM2 swizzle(S.D42 value);

  public DIM3 swizzle(S.D43 value);

  public DIM4 swizzle(S.D44 value);
}
