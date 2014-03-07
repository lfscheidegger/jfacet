// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet;

import android.opengl.GLES20;
import com.lfscheidegger.jfacet.shade.expression.VecLike;

public final class Drawable implements Runnable {

  private final Geometry mGeometry;
  private Program mProgram;

  private final VecLike mPosition;
  private final VecLike mFragColor;

  public Drawable(
      Geometry geometry,
      VecLike position,
      VecLike fragColor) {
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
        mGeometry.getPrimitiveType().glMode,
        mGeometry.getIndexBuffer().getElementCount(),
        GLES20.GL_UNSIGNED_INT,
        mGeometry.getIndexBuffer().getBuffer());
  }
}