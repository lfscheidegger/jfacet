package com.lfscheidegger.jfacet.facet;

import com.lfscheidegger.jfacet.Program;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public class Drawable {

  private final Geometry mGeometry;
  private final Program mProgram;

  public Drawable(Geometry geometry, Expression position, Expression fragColor) {
    mGeometry = geometry;
    mProgram = new Program(position, fragColor);

    mProgram.bake();
  }

  public void draw() {
    mProgram.use();
  }
}