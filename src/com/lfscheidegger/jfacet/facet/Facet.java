package com.lfscheidegger.jfacet.facet;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.*;

public class Facet {

  public static Drawable bake(Geometry geometry, Expression position) {
    return bake(geometry, position, Shade.vec(1, 1, 1));
  }

  public static Drawable bake(Geometry geometry, Expression position, Expression fragColor) {
    if (fragColor.getGlSlType() == GlSlType.ATTRIBUTE_T) {
      switch(fragColor.getType()) {
        case FLOAT_T:
          fragColor = Shade.varyingf((FloatExp) fragColor); break;
        case VEC2_T:
          fragColor = Shade.varying2f((Vec2Exp) fragColor); break;
        case VEC3_T:
          fragColor = Shade.varying3f((Vec3Exp) fragColor); break;
        case VEC4_T:
          fragColor = Shade.varying4f((Vec4Exp) fragColor); break;
      }
    }

    return new Drawable(geometry, position, fragColor);
  }

  public static Geometry model(GeometryConfig config) {
    return new Geometry(config);
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
