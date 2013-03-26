package com.lfscheidegger.jfacet.shade.internal.types;

import com.google.common.base.Objects;

import java.util.Arrays;

/**
 * Aggregate type for mat2
 */
public class Mat2 {
  public Vec2[] mValues;

  public Mat2() {
    mValues = new Vec2[]{new Vec2(1, 0), new Vec2(0, 1)};
  }

  public Mat2(Vec2 c0, Vec2 c1) {
    mValues = new Vec2[]{c0, c1};
  }

  public Mat2(Mat2 other) {
    mValues = new Vec2[] {other.getC0(), other.getC1()};
  }

  public Mat2 setC0(Vec2 c0) {
    mValues[0] = c0;
    return this;
  }

  public Mat2 setC1(Vec2 c1) {
    mValues[1] = c1;
    return this;
  }

  public Mat2 set(Vec2 c, int idx) {
    mValues[idx] = c;
    return this;
  }

  public Vec2 getC0() {
    return mValues[0];
  }

  public Vec2 getC1() {
    return mValues[1];
  }

  public Vec2 get(int idx) {
    return mValues[idx];
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Mat2)) {
      return false;
    }

    return Arrays.equals(mValues, ((Mat2)other).mValues);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(mValues[0], mValues[1]);
  }
}
