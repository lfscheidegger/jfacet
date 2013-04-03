package com.lfscheidegger.jfacet.facet;

import android.opengl.GLES20;
import com.lfscheidegger.jfacet.Program;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public class Drawable {

  private final int mDimension;
  private final Geometry mGeometry;
  private final Program mProgram;

  public Drawable(Geometry geometry, Expression position, Expression fragColor) {
    mDimension = position.getType().getDimension();
    mGeometry = geometry;
    mProgram = new Program(position, fragColor);

    mProgram.bake();
  }

  public void draw() {
    mProgram.use();
    try {
      GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, mGeometry.getBufferSize() / mDimension);
    } finally {
      mProgram.stopUsing();
    }
  }
}