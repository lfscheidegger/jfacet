package com.lfscheidegger.jfacet.shade;

import android.os.SystemClock;
import com.google.common.base.Preconditions;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.evaluators.glsl.UniformEvaluator;

public final class Parameter {

  public static Real now() {
    return parameter(Shade.constant(0f), new UniformEvaluator.Refreshable<Float>() {
      @Override
      public void refresh(UniformEvaluator<Float> uniform) {
        uniform.set(Shade.constant(SystemClock.uptimeMillis() / 1000.0f));
      }
    });
  }

  public static Real parameter(Real value) {
    return new Real(GlSlType.UNIFORM_T, new UniformEvaluator<Float>(value));
  }

  public static Real parameter(Real value, UniformEvaluator.Refreshable<Float> refresher) {
    return new Real(GlSlType.UNIFORM_T, new UniformEvaluator<Float>(value, refresher));
  }

  public static void set(Expression param, Expression value) {
    Preconditions.checkState(param.getType() == value.getType());

    if (param.getGlSlType() != GlSlType.UNIFORM_T) {
      throw new RuntimeException("Cannot set value for glsl type " + param.getGlSlType());
    }

    ((UniformEvaluator)param.getEvaluator()).set(value);
  }

  public static <T> T get(Expression<T> param) {
    if (param.getGlSlType() != GlSlType.UNIFORM_T) {
      throw new RuntimeException("Cannot get uniform value for glsl type " + param.getGlSlType());
    }

    return ((UniformEvaluator<T>)param.getEvaluator()).get();
  }
}
