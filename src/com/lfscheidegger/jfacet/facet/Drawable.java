package com.lfscheidegger.jfacet.facet;

import android.opengl.GLES20;
import com.lfscheidegger.jfacet.Program;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;
import com.lfscheidegger.jfacet.shade.expression.vector.VectorExpression;

public final class Drawable<T> implements Runnable {

  private final Geometry mGeometry;
  private Program mProgram;

  private final VectorExpression<T, Vector4> mPosition;
  private final VectorExpression<T, Vector4> mFragColor;

  public Drawable(
      Geometry geometry,
      VectorExpression<T, Vector4> position,
      VectorExpression<T, Vector4> fragColor) {
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