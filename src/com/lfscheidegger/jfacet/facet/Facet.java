package com.lfscheidegger.jfacet.facet;

import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec2Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec3Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec4Exp;

public class Facet {

  public static Drawable bake(Geometry geometry, Expression position) {
    return bake(geometry, position, Shade.vec(1, 1, 1));
  }

  public static Drawable bake(Geometry geometry, Expression position, Expression fragColor) {

    if (fragColor.getGlSlType() == GlSlType.ATTRIBUTE_T) {
      switch(fragColor.getType()) {
        case FLOAT_T:
          fragColor = Shade.varying((FloatExp)fragColor); break;
        case VEC2_T:
          fragColor = Shade.varying((Vec2Exp)fragColor); break;
        case VEC3_T:
          fragColor = Shade.varying((Vec3Exp)fragColor); break;
        case VEC4_T:
          fragColor = Shade.varying((Vec4Exp)fragColor); break;
      }
    }

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
