// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Extends {@code GLSurfaceView} and sets up some basic OpenGL context stuff.
 */
public class FacetView extends GLSurfaceView {

  public FacetView(Context context) {
    this(context, null);
  }

  public FacetView(Context context, AttributeSet attrs) {
    super(context, attrs);

    setEGLContextClientVersion(2);
  }
}
