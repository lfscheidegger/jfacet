package com.lfscheidegger.jfacet.shade;

import android.os.SystemClock;
import com.google.common.base.Preconditions;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.FloatExpression;
import com.lfscheidegger.jfacet.shade.expression.MatrixExpression;
import com.lfscheidegger.jfacet.shade.expression.VectorExpression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.glsl.UniformEvaluator;
import com.lfscheidegger.jfacet.shade.primitives.Matrix;
import com.lfscheidegger.jfacet.shade.primitives.Vector;

public final class Parameter {

  public static FloatExpression now() {
    return parameter(Shade.constant(0), new UniformEvaluator.Refreshable<Float>() {
      @Override
      public void refresh(UniformEvaluator<Float> uniform) {
        uniform.set(Shade.constant(SystemClock.uptimeMillis() / 1000.0f));
      }
    });
  }

  public static FloatExpression parameter(FloatExpression value) {
    return new FloatExpression(GlSlType.UNIFORM_T, new UniformEvaluator<Float>(value));
  }

  public static FloatExpression parameter(FloatExpression value, UniformEvaluator.Refreshable<Float> refresher) {
    return new FloatExpression(GlSlType.UNIFORM_T, new UniformEvaluator<Float>(value, refresher));
  }

  public static VectorExpression parameter(VectorExpression value) {
    return new VectorExpression(value.getType(), GlSlType.UNIFORM_T, new UniformEvaluator<Vector>(value));
  }

  public static MatrixExpression parameter(MatrixExpression value) {
    return new MatrixExpression(value.getType(), GlSlType.UNIFORM_T, new UniformEvaluator<Matrix>(value));
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
