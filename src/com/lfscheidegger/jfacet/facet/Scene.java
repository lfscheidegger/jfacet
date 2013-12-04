package com.lfscheidegger.jfacet.facet;

import java.util.HashSet;
import java.util.Set;

public final class Scene {

  private final Set<Drawable> mDrawables;

  public Scene() {
    mDrawables = new HashSet<Drawable>();
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
