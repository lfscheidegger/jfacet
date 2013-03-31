package com.lfscheidegger.jfacet.facet.renderer;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class FacetRenderer implements GLSurfaceView.Renderer {

  private final Vec4 mClearColor;

  public FacetRenderer(Expression<Vec4> clearColor) {
    if (clearColor == null) {
      clearColor = Shade.vec(0, 0, 0, 0);
    }

    if (clearColor.getType() != Type.VEC4_T) {
      throw new IllegalArgumentException("clearColor must be of type VEC4_T");
    }

    mClearColor = clearColor.evaluate();
  }

  @Override
  public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    GLES20.glClearColor(mClearColor.get(0), mClearColor.get(1), mClearColor.get(2), mClearColor.get(3));
  }

  @Override
  public void onSurfaceChanged(GL10 gl, int width, int height) {
    GLES20.glViewport(0, 0, width, height);
  }

  @Override
  public void onDrawFrame(GL10 gl) {

  }
}
