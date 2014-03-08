// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet;

import com.google.common.collect.Sets;

import java.util.Set;

public final class Scene implements Runnable {

  private final Set<Runnable> mRunnables;

  public Scene() {
    mRunnables = Sets.newHashSet();
  }

  public Scene add(Runnable... runnables) {
    for (Runnable runnable : runnables) {
      mRunnables.add(runnable);
    }

    return this;
  }

  public void build() {
    for (Runnable runnable: mRunnables) {
      if (runnable instanceof Drawable) {
        ((Drawable) runnable).build();
      }
    }
  }

  public void run() {
    for (Runnable runnable : mRunnables) {
      runnable.run();
    }
  }
}
