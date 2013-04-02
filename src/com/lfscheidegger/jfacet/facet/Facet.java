package com.lfscheidegger.jfacet.facet;

import com.lfscheidegger.jfacet.shade.expression.Expression;

public class Facet {
  public static Drawable bake(Geometry geometry, Expression vertex, Expression color) {
    return null;
  }

  public static Geometry model(ModelType type, float[] vertices) {
    return new Geometry(type, BufferHelper.getBufferFromArray(vertices));
  }
}
