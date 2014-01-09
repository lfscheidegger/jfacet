// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.shade.camera;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Mat4;
import com.lfscheidegger.jfacet.shade.expression.Vec3;

public final class LookAtConfig implements CameraConfig {

  private Vec3 mEye;
  private Vec3 mCenter;
  private Vec3 mUp;

  public LookAtConfig() {
    mEye = Shade.vec(0, 0, 0);
    mCenter = Shade.vec(0, 0, -1);
    mUp = Shade.vec(0, 1, 0);
  }

  public LookAtConfig setEye(Vec3 eye) {
    mEye = eye;
    return this;
  }

  public LookAtConfig setCenter(Vec3 center) {
    mCenter = center;
    return this;
  }

  public LookAtConfig setUp(Vec3 up) {
    mUp = up;
    return this;
  }

  @Override
  public Mat4 getMatrix() {
    Vec3 z = mEye.minus(mCenter).normalize();
    Vec3 x = mUp.cross(z).normalize();
    Vec3 y = mUp.normalize();

    // taken from
    // https://github.com/cscheid/facet/blob/master/src/shade/look_at.js
    return Shade.mat(
        Shade.vec(x.get(0), x.get(1), x.get(2), 0),
        Shade.vec(y.get(0), y.get(1), y.get(2), 0),
        Shade.vec(z.get(0), z.get(1), z.get(2), 0),
        Shade.vec(x.dot(mEye).negative(), y.dot(mEye).negative(), z.dot(mEye).negative(), 1));
  }
}
