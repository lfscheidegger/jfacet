package com.lfscheidegger.jfacet.shade;

import android.os.SystemClock;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.UniformEvaluator;
import com.lfscheidegger.jfacet.shade.expression.primitives.*;
import com.lfscheidegger.jfacet.shade.primitives.*;

public class Parameter {

  public static FloatExp now() {
    return parameter(Shade.constant(0), new UniformEvaluator.Refreshable<Float>() {
      @Override
      public void refresh(UniformEvaluator<Float> uniform) {
        uniform.set(Shade.constant(SystemClock.uptimeMillis() / 1000.0f));
      }
    });
  }

  public static FloatExp parameter(FloatExp value) {
    return new FloatExp(GlSlType.UNIFORM_T, ImmutableList.<Expression>of(), new UniformEvaluator<Float>(value));
  }

  public static FloatExp parameter(FloatExp value, UniformEvaluator.Refreshable<Float> refresher) {
    return new FloatExp(
        GlSlType.UNIFORM_T,
        ImmutableList.<Expression>of(),
        new UniformEvaluator<Float>(value, refresher));
  }

  public static Vec2Exp parameter(Vec2Exp value) {
    return new Vec2Exp(GlSlType.UNIFORM_T, ImmutableList.<Expression>of(), new UniformEvaluator<Vec2>(value));
  }

  public static Vec2Exp parameter(Vec2Exp value, UniformEvaluator.Refreshable<Vec2> refresher) {
    return new Vec2Exp(
        GlSlType.UNIFORM_T,
        ImmutableList.<Expression>of(),
        new UniformEvaluator<Vec2>(value, refresher));
  }

  public static Vec3Exp parameter(Vec3Exp value) {
    return new Vec3Exp(GlSlType.UNIFORM_T, ImmutableList.<Expression>of(), new UniformEvaluator<Vec3>(value));
  }

  public static Vec3Exp parameter(Vec3Exp value, UniformEvaluator.Refreshable<Vec3> refresher) {
    return new Vec3Exp(
        GlSlType.UNIFORM_T,
        ImmutableList.<Expression>of(),
        new UniformEvaluator<Vec3>(value, refresher));
  }

  public static Vec4Exp parameter(Vec4Exp value) {
    return new Vec4Exp(GlSlType.UNIFORM_T, ImmutableList.<Expression>of(), new UniformEvaluator<Vec4>(value));
  }

  public static Vec4Exp parameter(Vec4Exp value, UniformEvaluator.Refreshable<Vec4> refresher) {
    return new Vec4Exp(
        GlSlType.UNIFORM_T,
        ImmutableList.<Expression>of(),
        new UniformEvaluator<Vec4>(value, refresher));
  }

  public static Mat2Exp parameter(Mat2Exp value) {
    return new Mat2Exp(GlSlType.UNIFORM_T, ImmutableList.<Expression>of(), new UniformEvaluator<Mat2>(value));
  }

  public static Mat2Exp parameter(Mat2Exp value, UniformEvaluator.Refreshable<Mat2> refresher) {
    return new Mat2Exp(
        GlSlType.UNIFORM_T,
        ImmutableList.<Expression>of(),
        new UniformEvaluator<Mat2>(value, refresher));
  }

  public static Mat3Exp parameter(Mat3Exp value) {
    return new Mat3Exp(GlSlType.UNIFORM_T, ImmutableList.<Expression>of(), new UniformEvaluator<Mat3>(value));
  }

  public static Mat3Exp parameter(Mat3Exp value, UniformEvaluator.Refreshable<Mat3> refresher) {
    return new Mat3Exp(
        GlSlType.UNIFORM_T,
        ImmutableList.<Expression>of(),
        new UniformEvaluator<Mat3>(value, refresher));
  }

  public static Mat4Exp parameter(Mat4Exp value) {
    return new Mat4Exp(GlSlType.UNIFORM_T, ImmutableList.<Expression>of(), new UniformEvaluator<Mat4>(value));
  }

  public static Mat4Exp parameter(Mat4Exp value, UniformEvaluator.Refreshable<Mat4> refresher) {
    return new Mat4Exp(
        GlSlType.UNIFORM_T,
        ImmutableList.<Expression>of(),
        new UniformEvaluator<Mat4>(value, refresher));
  }

  public static void set(Expression param, Expression value) {
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
