package com.lfscheidegger.jfacet.shade.expression.vector.swizzle;

public interface SupportsSwizzling3<DIM1, DIM2, DIM3, DIM4> {

  public DIM1 swizzle(S.D31 value);

  public DIM2 swizzle(S.D32 value);

  public DIM3 swizzle(S.D33 value);

  public DIM4 swizzle(S.D34 value);
}
