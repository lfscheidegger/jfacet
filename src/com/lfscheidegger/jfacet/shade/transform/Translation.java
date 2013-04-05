package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.primitives.Mat4;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

public class Translation extends AbstractTransform {

  public Translation(float x, float y, float z) {
    super(Shade.mat(new Mat4(
        new Vec4(1, 0, 0, 0),
        new Vec4(0, 1, 0, 0),
        new Vec4(0, 0, 1, 0),
        new Vec4(x, y, z, 1))));
  }
}
