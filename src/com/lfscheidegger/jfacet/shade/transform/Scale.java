package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.FloatExp;

public class Scale extends AbstractTransform {

  public Scale(FloatExp sx, FloatExp sy, FloatExp sz) {
    super(Shade.mat(
        Shade.vec(sx, 0, 0, 0),
        Shade.vec(0, sy, 0, 0),
        Shade.vec(0, 0, sz, 0),
        Shade.vec(0, 0, 0, 1)));
  }
}
