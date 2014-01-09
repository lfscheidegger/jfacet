// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Real;

public class Translation3 extends Transform3 {

  public Translation3(float dx, float dy) {
    super(Shade.mat(
        Shade.vec(1, 0, 0),
        Shade.vec(0, 1, 0),
        Shade.vec(dx, dy, 1)));
  }

  public Translation3(Real dx, Real dy) {
    super(Shade.mat(
        Shade.vec(1, 0, 0),
        Shade.vec(0, 1, 0),
        Shade.vec(dx, dy, 1)));
  }
}
