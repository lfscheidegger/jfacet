package com.lfscheidegger.jfacet.shade.expression;

interface SupportsSwizzling {

  public float swizzle(char x);

  public Vector2.Primitive swizzle(char x, char y);

  public Vector3.Primitive swizzle(char x, char y, char z);

  public Vector4.Primitive swizzle(char x, char y, char z, char w);
}
