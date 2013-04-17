package com.lfscheidegger.jfacet.facet;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.SamplerExpression;

public final class Facet {

  public static Drawable bake(Geometry geometry, Expression position) {
    return bake(geometry, position, Shade.vec(1, 1, 1));
  }

  public static Drawable bake(Geometry geometry, Expression position, Expression fragColor) {
    return new Drawable(geometry, position, fragColor);
  }

  public static Geometry model(GeometryConfig config) {
    return new Geometry(config);
  }

  public static SamplerExpression texture(Resources resources, int resId) {
    Matrix matrix = new Matrix();
    matrix.preScale(1, -1);
    Bitmap bitmap = BitmapFactory.decodeResource(resources, resId);
    return texture(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true));
  }

  public static SamplerExpression texture(Bitmap texture) {
    return new SamplerExpression(texture);
  }
}
