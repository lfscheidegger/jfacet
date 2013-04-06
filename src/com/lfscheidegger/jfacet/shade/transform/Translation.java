package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;

public class Translation extends AbstractTransform {

  public Translation(float x, float y, float z) {
    this(Shade.constant(x), Shade.constant(y), Shade.constant(z));
  }

  public Translation(float x, float y, FloatExp z) {
    this(Shade.constant(x), Shade.constant(y), z);
  }

  public Translation(float x, FloatExp y, float z) {
    this(Shade.constant(x), y, Shade.constant(z));
  }

  public Translation(float x, FloatExp y, FloatExp z) {
    this(Shade.constant(x), y, z);
  }

  public Translation(FloatExp x, float y, float z) {
    this(x, Shade.constant(y), Shade.constant(z));
  }

  public Translation(FloatExp x, float y, FloatExp z) {
    this(x, Shade.constant(y), z);
  }

  public Translation(FloatExp x, FloatExp y, float z) {
    this(x, y, Shade.constant(z));
  }

  public Translation(FloatExp x, FloatExp y, FloatExp z) {
    super(Shade.mat(
        Shade.vec(1, 0, 0, 0),
        Shade.vec(0, 1, 0, 0),
        Shade.vec(0, 0, 1, 0),
        Shade.vec(x, y, z, 1)));
  }
}
