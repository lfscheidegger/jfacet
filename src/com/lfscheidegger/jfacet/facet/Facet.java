package com.lfscheidegger.jfacet.facet;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public class Facet {

  public static Drawable bake(Geometry geometry, Expression position) {
    return new Drawable(geometry, position, Shade.vec(1, 1, 1));
  }

  public static Drawable bake(Geometry geometry, Expression position, Expression fragColor) {
    return new Drawable(geometry, position, fragColor);
  }

  public static Geometry model(ModelType type, float[] vertices) {
    return new Geometry(type, vertices);
  }
}
