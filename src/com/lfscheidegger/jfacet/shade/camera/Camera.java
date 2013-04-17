package com.lfscheidegger.jfacet.shade.camera;

import com.lfscheidegger.jfacet.shade.transform.AbstractTransform;

public final class Camera extends AbstractTransform {

  public Camera(CameraConfig modelViewConfig, CameraConfig projectionConfig) {
    super(projectionConfig.getMatrix().mul(modelViewConfig.getMatrix()));
  }

  public static Camera perspective(int width, int height) {
    return new Camera(new LookAtConfig(), new PerspectiveConfig(width, height));
  }

  public static Camera perspective(CameraConfig lookAtConfig, int width, int height) {
    return new Camera(lookAtConfig, new PerspectiveConfig(width, height));
  }
}
