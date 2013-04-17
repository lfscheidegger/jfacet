package com.lfscheidegger.jfacet.shade.camera;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.MatrixExpression;

public class PerspectiveConfig implements CameraConfig {

  private float mFieldOfViewY = 45;
  private float mNearDistance = 0.1f;
  private float mFarDistance = 100;

  private final float mAspectRatio;

  public PerspectiveConfig(int width, int height) {
    mAspectRatio = width / (float)height;
  }

  public PerspectiveConfig setFieldOfViewY(float fieldOfViewY) {
    mFieldOfViewY = fieldOfViewY;
    return this;
  }

  public PerspectiveConfig setNearDistance(float nearDistance) {
    mNearDistance = nearDistance;
    return this;
  }

  public PerspectiveConfig setFarDistance(float farDistance) {
    mFarDistance = farDistance;
    return this;
  }

  @Override
  public MatrixExpression getMatrix() {
    float fovy = (float)(Math.PI * mFieldOfViewY / 180);

    float f = (float)(1 / Math.tan(fovy / 2.0));

    // taken from
    // http://unspecified.wordpress.com/2012/06/21/calculating-the-gluperspective-matrix-and-other-opengl-matrix-maths/
    return Shade.mat(
        Shade.vec(f / mAspectRatio, 0, 0, 0),
        Shade.vec(0, f, 0, 0),
        Shade.vec(0, 0, (mNearDistance + mFarDistance) / (mNearDistance - mFarDistance), -1),
        Shade.vec(0, 0, (2 * mNearDistance * mFarDistance) / (mNearDistance - mFarDistance), 0));
  }
}
