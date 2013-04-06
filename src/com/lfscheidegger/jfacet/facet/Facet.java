package com.lfscheidegger.jfacet.facet;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public class Facet {

  public static Drawable bake(Geometry geometry, Expression position) {
    return bake(geometry, position, Shade.vec(1, 1, 1));
  }

  public static Drawable bake(Geometry geometry, Expression position, Expression fragColor) {
    return new Drawable(geometry, position, fragColor);
  }

  public static Geometry model(ModelType type, float[] vertices, int dimension) {
    return new Geometry(type, new AttribBuffer(vertices, dimension));
  }

  public static Geometry model(
      ModelType type,
      float[] vertices, int vertexDimension,
      float[] colors, int colorDimension) {
    return new Geometry(
        type,
        new AttribBuffer(vertices, vertexDimension),
        new AttribBuffer(colors, colorDimension));
  }
}
