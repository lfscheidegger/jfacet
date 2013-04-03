package com.lfscheidegger.jfacet.facet;

import android.opengl.GLES20;
import com.google.common.base.Preconditions;
import com.lfscheidegger.jfacet.Program;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public class Drawable {

  private final int mDimension;
  private final Geometry mGeometry;
  private Program mProgram;

  private final Expression mPosition;
  private final Expression mFragColor;

  public Drawable(Geometry geometry, Expression position, Expression fragColor) {
    mPosition = position;
    mFragColor = fragColor;

    mDimension = position.getType().getDimension();
    mGeometry = geometry;
  }

  public void bake() {
    mProgram = new Program(mPosition, mFragColor);
    mProgram.bake();
  }

  public void draw() {
    Preconditions.checkNotNull(mProgram);

    mProgram.use();
    try {
      GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, mGeometry.getBufferSize() / mDimension);
    } finally {
      mProgram.stopUsing();
    }
  }
}