package com.lfscheidegger.jfacet.shade.camera;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.primitives.Mat4Exp;

public class PerspectiveConfig implements CameraConfig {

  public static final class Builder {

    private float mFieldOfViewY = 45;
    private float mNearDistance = 0.1f;
    private float mFarDistance = 100;

    private final float mAspectRatio;

    public Builder(int width, int height) {
      mAspectRatio = width / (float)height;
    }

    public Builder setFieldOfViewY(float fieldOfViewY) {
      mFieldOfViewY = fieldOfViewY;
      return this;
    }

    public Builder setNearDistance(float nearDistance) {
      mNearDistance = nearDistance;
      return this;
    }

    public Builder setFarDistance(float farDistance) {
      mFarDistance = farDistance;
      return this;
    }

    public PerspectiveConfig build() {
      return new PerspectiveConfig(this);
    }
  }

  private final float mFieldOfViewY;
  private final float mNearDistance;
  private final float mFarDistance;

  private final float mAspectRatio;

  PerspectiveConfig(Builder builder) {
    mFieldOfViewY = builder.mFieldOfViewY;
    mNearDistance = builder.mNearDistance;
    mFarDistance = builder.mFarDistance;

    mAspectRatio = builder.mAspectRatio;
  }

  @Override
  public Mat4Exp getMatrix() {
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
