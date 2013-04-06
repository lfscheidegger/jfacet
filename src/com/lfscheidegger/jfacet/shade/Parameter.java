package com.lfscheidegger.jfacet.shade;

import android.os.SystemClock;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.primitives.uniform.FloatUniform;

public class Parameter {

  public static FloatExp now() {
    return new FloatUniform(Shade.constant(0)) {
      @Override
      public void refresh() {
        set(SystemClock.uptimeMillis() / 1000.0f);
      }
    };
  }
}
