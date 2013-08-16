package com.lfscheidegger.jfacet.shade.expression.vector;

import com.lfscheidegger.jfacet.shade.expression.*;

public interface VectorExpression<T> extends SupportsBasicArithmetic<T> {

  public Real get(int idx);

  public T add(Real right);

  public T sub(Real right);

  public T mul(Real right);

  public T div(Real right);

  public Real dot(T right);

  public T normalize();

  public Real length();

  public Vector4 fill(Vector4 defaultExpression);

  public Real swizzle(char x);

  public Vector2 swizzle(char x, char y);

  public Vector3 swizzle(char x, char y, char z);

  public Vector4 swizzle(char x, char y, char z, char w);
}
