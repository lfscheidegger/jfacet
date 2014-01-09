// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.shade.camera;

import com.lfscheidegger.jfacet.shade.transform.Transform4;

public final class Camera extends Transform4 {

  public Camera(CameraConfig modelViewConfig, CameraConfig projectionConfig) {
    super(projectionConfig.getMatrix().times(modelViewConfig.getMatrix()));
  }

  public static Camera perspective(int width, int height) {
    return new Camera(new LookAtConfig(), new PerspectiveConfig(width, height));
  }

  public static Camera perspective(CameraConfig lookAtConfig, int width, int height) {
    return new Camera(lookAtConfig, new PerspectiveConfig(width, height));
  }

  public static Camera ortho(CameraConfig lookAtConfig, CameraConfig orthographicConfig) {
    return new Camera(lookAtConfig, orthographicConfig);
  }
}
