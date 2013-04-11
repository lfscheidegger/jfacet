package com.lfscheidegger.jfacet.shade.camera;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Mat4Exp;
import com.lfscheidegger.jfacet.shade.expression.Vec3Exp;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.Vec3Like;

public class LookAtConfig implements CameraConfig {

  private Vec3Exp mEye;
  private Vec3Exp mCenter;
  private Vec3Exp mUp;

  LookAtConfig() {
    mEye = Shade.vec(0, 0, 0);
    mCenter = Shade.vec(0, 0, -1);
    mUp = Shade.vec(0, 1, 0);
  }

  public LookAtConfig setEye(Vec3Like eye) {
    mEye = Shade.promote(eye);
    return this;
  }

  public LookAtConfig setCenter(Vec3Like center) {
    mCenter = Shade.promote(center);
    return this;
  }

  public LookAtConfig setUp(Vec3Like up) {
    mUp = Shade.promote(up);
    return this;
  }

  @Override
  public Mat4Exp getMatrix() {
    Vec3Exp z = mEye.sub(mCenter).normalize();
    Vec3Exp x = mUp.cross(z).normalize();
    Vec3Exp y = mUp.normalize();

    // taken from
    // https://github.com/cscheid/facet/blob/master/src/shade/look_at.js
    return Shade.mat(
        Shade.vec(x.get(0), x.get(1), x.get(2), 0),
        Shade.vec(y.get(0), y.get(1), y.get(2), 0),
        Shade.vec(z.get(0), z.get(1), z.get(2), 0),
        Shade.vec(x.dot(mEye).neg(), y.dot(mEye).neg(), z.dot(mEye).neg(), 1));
  }
}
