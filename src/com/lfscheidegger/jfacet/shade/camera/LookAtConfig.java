package com.lfscheidegger.jfacet.shade.camera;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Matrix4;
import com.lfscheidegger.jfacet.shade.expression.Vector3;

public final class LookAtConfig implements CameraConfig {

  private Vector3 mEye;
  private Vector3 mCenter;
  private Vector3 mUp;

  public LookAtConfig() {
    mEye = Shade.vec(0, 0, 0);
    mCenter = Shade.vec(0, 0, -1);
    mUp = Shade.vec(0, 1, 0);
  }

  public LookAtConfig setEye(Vector3 eye) {
    mEye = eye;
    return this;
  }

  public LookAtConfig setCenter(Vector3 center) {
    mCenter = center;
    return this;
  }

  public LookAtConfig setUp(Vector3 up) {
    mUp = up;
    return this;
  }

  @Override
  public Matrix4 getMatrix() {
    Vector3 z = mEye.sub(mCenter).normalize();
    Vector3 x = mUp.cross(z).normalize();
    Vector3 y = mUp.normalize();

    // taken from
    // https://github.com/cscheid/facet/blob/master/src/shade/look_at.js
    return Shade.mat(
        Shade.vec(x.get(0), x.get(1), x.get(2), 0),
        Shade.vec(y.get(0), y.get(1), y.get(2), 0),
        Shade.vec(z.get(0), z.get(1), z.get(2), 0),
        Shade.vec(x.dot(mEye).neg(), y.dot(mEye).neg(), z.dot(mEye).neg(), 1));
  }
}
