package com.lfscheidegger.jfacet.facet;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;
import com.lfscheidegger.jfacet.shade.expression.vector.VectorExpression;

import java.util.HashSet;
import java.util.Set;

public final class Scene {

  public static final class SceneConfig {

    public static final class Builder {

      private Vector4.Primitive mClearColor;

      public Builder() {

      }

      public Builder setClearColor(VectorExpression clearColor) {
        mClearColor = clearColor.fill(Shade.vec(0, 0, 0, 1)).evaluate();
        return this;
      }

      public SceneConfig build() {
        mClearColor = (mClearColor == null) ? Shade.vec(0, 0, 0, 1).evaluate() : mClearColor;
        return new SceneConfig(mClearColor);
      }
    }

    private final Vector4.Primitive mClearColor;

    public SceneConfig(
        Vector4.Primitive clearColor) {
      mClearColor = clearColor;
    }

    public Vector4.Primitive getClearColor() {
      return mClearColor;
    }
  }

  private final SceneConfig mSceneConfig;
  private final Set<Drawable> mDrawables;
  private Drawable[] mDrawableCache;

  public Scene(SceneConfig sceneConfig) {
    mSceneConfig = sceneConfig;
    mDrawables = new HashSet<Drawable>();
  }

  public Scene add(Drawable drawable) {
    mDrawables.add(drawable);

    return this;
  }

  public void bake() {
    mDrawableCache = new Drawable[mDrawables.size()];

    int count = 0;
    for (Drawable drawable: mDrawables) {
      drawable.bake();
      mDrawableCache[count++] = drawable;
    }
  }

  public void draw() {
    for (int i = 0; i < mDrawableCache.length; i++) {
      mDrawableCache[i].draw();
    }
  }

  public SceneConfig getSceneConfig() {
    return mSceneConfig;
  }
}
