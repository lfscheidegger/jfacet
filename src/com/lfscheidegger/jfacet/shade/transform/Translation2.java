package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Real;

public class Translation2 extends Transform2 {

  public Translation2(float dx) {
    super(Shade.mat(
        Shade.vec(1, 0),
        Shade.vec(dx, 1)));
  }

  public Translation2(Real dx) {
    super(Shade.mat(
        Shade.vec(1, 0),
        Shade.vec(dx, 1)));
  }
}
