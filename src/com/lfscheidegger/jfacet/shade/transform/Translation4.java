package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Real;

public class Translation4 extends AbstractTransform4 {

  public Translation4(float x, float y, float z) {
    super(Shade.mat(
        Shade.vec(1, 0, 0, 0),
        Shade.vec(0, 1, 0, 0),
        Shade.vec(0, 0, 1, 0),
        Shade.vec(x, y, z, 1)));
  }

  public Translation4(Real x, Real y, Real z) {
    super(Shade.mat(
        Shade.vec(1, 0, 0, 0),
        Shade.vec(0, 1, 0, 0),
        Shade.vec(0, 0, 1, 0),
        Shade.vec(x, y, z, 1)));
  }
}
