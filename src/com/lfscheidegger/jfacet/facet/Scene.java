package com.lfscheidegger.jfacet.facet;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;
import com.lfscheidegger.jfacet.shade.expression.vector.VectorExpression;

import java.util.HashSet;
import java.util.Set;

public final class Scene {

  public static final class Builder {

    private Vector4.Primitive mClearColor;

    public Builder setClearColor(VectorExpression clearColor) {
      //mClearColor = clearColor.fill(Shade.vec(0, 0, 0, 1)).evaluate();
      return this;
    }

    public Vector4.Primitive getClearColor() {
      return mClearColor;
    }

    public Scene build() {
      //mClearColor = (mClearColor == null) ? Shade.vec(0, 0, 0, 1).evaluate() : mClearColor;
      return new Scene(this);
    }
  }

  private final Vector4.Primitive mClearColor;
  private final Set<Drawable> mDrawables;

  public Scene() {
    this(new Builder());
  }

  Scene(Builder builder) {
    mClearColor = builder.getClearColor();

    mDrawables = new HashSet<Drawable>();
  }

  public Vector4.Primitive getClearColor() {
    return mClearColor;
  }

  public Scene add(Drawable drawable) {
    mDrawables.add(drawable);

    return this;
  }

  public void bake() {
    for (Drawable drawable: mDrawables) {
      drawable.bake();
    }
  }

  public void draw() {
    for (Drawable drawable: mDrawables) {
      drawable.draw();
    }
  }
}
