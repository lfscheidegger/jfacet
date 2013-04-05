package com.lfscheidegger.jfacet.shade.camera;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.Mat4Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec4Exp;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

public class Camera {

  private final Mat4Exp mMvpMatrix;

  public Camera(CameraConfig modelViewConfig, CameraConfig projectionConfig) {
    mMvpMatrix = projectionConfig.getMatrix().mul(modelViewConfig.getMatrix());
  }

  public Vec4Exp apply(Expression exp) {
    return mMvpMatrix.mul(Shade.fill(exp, new Vec4(0, 0, 0, 1)));
  }

  public static Camera perspective(int width, int height) {
    return new Camera(
        new LookAtConfig.Builder().build(),
        new PerspectiveConfig.Builder(width, height).build());
  }
}
