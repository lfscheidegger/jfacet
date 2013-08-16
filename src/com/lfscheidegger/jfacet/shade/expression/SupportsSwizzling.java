package com.lfscheidegger.jfacet.shade.expression;

import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;

public interface SupportsSwizzling {

  public float swizzle(char x);

  public Vector2.Primitive swizzle(char x, char y);

  public Vector3.Primitive swizzle(char x, char y, char z);

  public Vector4.Primitive swizzle(char x, char y, char z, char w);
}
