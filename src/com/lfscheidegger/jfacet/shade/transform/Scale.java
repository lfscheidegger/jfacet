package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.FloatExp;

public class Scale extends AbstractTransform {

  public Scale(float sx, float sy, float sz) {
    this(Shade.constant(sx), Shade.constant(sy), Shade.constant(sz));
  }

  public Scale(float sx, float sy, FloatExp sz) {
    this(Shade.constant(sx), Shade.constant(sy), sz);
  }

  public Scale(float sx, FloatExp sy, float sz) {
    this(Shade.constant(sx), sy, Shade.constant(sz));
  }

  public Scale(float sx, FloatExp sy, FloatExp sz) {
    this(Shade.constant(sx), sy, sz);
  }

  public Scale(FloatExp sx, float sy, float sz) {
    this(sx, Shade.constant(sy), Shade.constant(sz));
  }

  public Scale(FloatExp sx, float sy, FloatExp sz) {
    this(sx, Shade.constant(sy), sz);
  }

  public Scale(FloatExp sx, FloatExp sy, float sz) {
    this(sx, sy, Shade.constant(sz));
  }

  public Scale(FloatExp sx, FloatExp sy, FloatExp sz) {
    super(Shade.mat(
        Shade.vec(sx, 0, 0, 0),
        Shade.vec(0, sy, 0, 0),
        Shade.vec(0, 0, sz, 0),
        Shade.vec(0, 0, 0, 1)));
  }
}
