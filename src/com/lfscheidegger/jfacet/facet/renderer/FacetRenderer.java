package com.lfscheidegger.jfacet.facet.renderer;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import com.lfscheidegger.jfacet.facet.*;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class FacetRenderer implements GLSurfaceView.Renderer {

  private final Scene mScene;

  public FacetRenderer(Scene scene) {
    mScene = scene;
  }

  @Override
  public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    Vec4 clearColor = mScene.getSceneConfig().getClearColor();
    GLES20.glClearColor(clearColor.get(0), clearColor.get(1), clearColor.get(2), clearColor.get(3));

    mScene.bake();
  }

  @Override
  public void onSurfaceChanged(GL10 gl, int width, int height) {
    GLES20.glViewport(0, 0, width, height);
  }

  @Override
  public void onDrawFrame(GL10 gl) {
    GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

    mScene.draw();
  }
}
