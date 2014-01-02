package com.lfscheidegger.jfacet;

import android.opengl.GLES20;
import com.lfscheidegger.jfacet.shade.expression.VecExpression;

public final class Drawable implements Runnable {

  private final Geometry mGeometry;
  private Program mProgram;

  private final VecExpression mPosition;
  private final VecExpression mFragColor;

  public Drawable(
      Geometry geometry,
      VecExpression position,
      VecExpression fragColor) {
    mPosition = position;
    mFragColor = fragColor;
    mGeometry = geometry;
  }

  public void bake() {
    mProgram = new Program(mPosition, mFragColor);
    mProgram.bake();
  }

  public void run() {
    mProgram.use();
    GLES20.glDrawElements(
        GLES20.GL_TRIANGLES,
        mGeometry.getIndexBuffer().getElementCount(),
        GLES20.GL_UNSIGNED_INT,
        mGeometry.getIndexBuffer().getBuffer());
  }
}