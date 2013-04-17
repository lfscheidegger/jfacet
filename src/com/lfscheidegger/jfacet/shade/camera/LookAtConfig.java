package com.lfscheidegger.jfacet.shade.camera;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.MatrixExpression;
import com.lfscheidegger.jfacet.shade.expression.VectorExpression;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.VectorLike;

public final class LookAtConfig implements CameraConfig {

  private VectorExpression mEye;
  private VectorExpression mCenter;
  private VectorExpression mUp;

  public LookAtConfig() {
    mEye = Shade.vec(0, 0, 0);
    mCenter = Shade.vec(0, 0, -1);
    mUp = Shade.vec(0, 1, 0);
  }

  public LookAtConfig setEye(VectorLike eye) {
    mEye = Shade.promote(eye);
    return this;
  }

  public LookAtConfig setCenter(VectorLike center) {
    mCenter = Shade.promote(center);
    return this;
  }

  public LookAtConfig setUp(VectorLike up) {
    mUp = Shade.promote(up);
    return this;
  }

  @Override
  public MatrixExpression getMatrix() {
    VectorExpression z = mEye.sub(mCenter).normalize();
    VectorExpression x = mUp.cross(z).normalize();
    VectorExpression y = mUp.normalize();

    // taken from
    // https://github.com/cscheid/facet/blob/master/src/shade/look_at.js
    return Shade.mat(
        Shade.vec(x.get(0), x.get(1), x.get(2), 0),
        Shade.vec(y.get(0), y.get(1), y.get(2), 0),
        Shade.vec(z.get(0), z.get(1), z.get(2), 0),
        Shade.vec(x.dot(mEye).neg(), y.dot(mEye).neg(), z.dot(mEye).neg(), 1));
  }
}
