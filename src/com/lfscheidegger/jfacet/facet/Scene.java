package com.lfscheidegger.jfacet.facet;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

public final class Scene {

  private final Set<Drawable> mDrawables;

  private final Set<Runnable> mRunnables;

  public Scene() {
    mDrawables = Sets.newHashSet();
    mRunnables = Sets.newHashSet();
  }

  public Scene add(Drawable drawable) {
    mDrawables.add(drawable);

    return this;
  }

  public Scene add(Drawable... drawables) {
    for (Drawable drawable : drawables) {
      mDrawables.add(drawable);
    }
    return this;
  }

  public Scene add(Runnable runnable) {
    mRunnables.add(runnable);

    return this;
  }

  public void bake() {
    for (Drawable drawable: mDrawables) {
      drawable.bake();
    }
  }

  public void draw() {
    for (Runnable runnable : mRunnables) {
      runnable.run();
    }

    for (Drawable drawable: mDrawables) {
      drawable.draw();
    }
  }
}
