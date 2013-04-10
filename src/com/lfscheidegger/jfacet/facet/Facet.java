package com.lfscheidegger.jfacet.facet;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.*;

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

  public static Geometry model(
      ModelType type,
      int[] indices,
      float[] vertices, int dimension) {
    return new Geometry(type, new IndexBuffer(indices), new AttribBuffer(vertices, dimension));
  }

  public static Geometry model(
      ModelType type,
      int[] indices,
      float[] vertices, int vertexDimension,
      float[] colors, int colorDimension) {
    return new Geometry(
        type,
        new IndexBuffer(indices),
        new AttribBuffer(vertices, vertexDimension),
        new AttribBuffer(colors, colorDimension));
  }

  public static Sampler2DExp texture(Resources resources, int resId) {
    Matrix matrix = new Matrix();
    matrix.preScale(1, -1);
    Bitmap bitmap = BitmapFactory.decodeResource(resources, resId);
    return texture(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true));
  }

  public static Sampler2DExp texture(Bitmap texture) {
    return new Sampler2DExp(texture);
  }
}
