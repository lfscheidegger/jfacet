package com.lfscheidegger.jfacet.shade.camera;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.primitives.Mat4Exp;
import com.lfscheidegger.jfacet.shade.primitives.Mat4;

public class Camera {

  private final Mat4Exp mModelView;
  private final Mat4Exp mProjection;

  public Camera() {
    mModelView = Shade.mat(new Mat4());
    mProjection = Shade.mat(new Mat4());
  }

  public static Camera perspective() {
    return null;
  }
}
