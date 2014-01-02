package com.lfscheidegger.jfacet.facet;

import android.opengl.GLES20;
import com.lfscheidegger.jfacet.Program;
import com.lfscheidegger.jfacet.shade.expression.VectorExpression;

public final class Drawable implements Runnable {

  private final Geometry mGeometry;
  private Program mProgram;

  private final VectorExpression mPosition;
  private final VectorExpression mFragColor;

  public Drawable(
      Geometry geometry,
      VectorExpression position,
      VectorExpression fragColor) {
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