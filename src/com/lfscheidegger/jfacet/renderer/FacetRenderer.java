// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.renderer;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import com.lfscheidegger.jfacet.Scene;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class FacetRenderer implements GLSurfaceView.Renderer {

  private final Scene mScene;

  public FacetRenderer(Scene scene) {
    mScene = scene;
  }

  @Override
  public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    GLES20.glEnable(GLES20.GL_DEPTH_TEST);
    mScene.bake();
  }

  @Override
  public void onSurfaceChanged(GL10 gl, int width, int height) {
    GLES20.glViewport(0, 0, width, height);
  }

  @Override
  public void onDrawFrame(GL10 gl) {
    GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
    mScene.run();
  }
}
