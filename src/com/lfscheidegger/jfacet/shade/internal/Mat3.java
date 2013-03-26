package com.lfscheidegger.jfacet.shade.internal;

import com.google.common.base.Objects;

import java.util.Arrays;

/**
 * Aggregate type for mat3.
 */
public class Mat3 {
  private final Vec3[] mValues;

  public Mat3() {
    mValues = new Vec3[]{new Vec3(1, 0, 0), new Vec3(0, 1, 0), new Vec3(0, 0, 1)};
  }

  public Mat3(Vec3 c0, Vec3 c1, Vec3 c2) {
    mValues = new Vec3[]{c0, c1, c2};
  }

  public Mat3(Mat3 other) {
    mValues = new Vec3[]{other.getC0(), other.getC1(), other.getC2()};
  }

  public Mat3 setC0(Vec3 c0) {
    mValues[0] = c0;
    return this;
  }

  public Mat3 setC1(Vec3 c1) {
    mValues[1] = c1;
    return this;
  }

  public Mat3 setC2(Vec3 c2) {
    mValues[2] = c2;
    return this;
  }

  public Mat3 set(Vec3 c, int idx) {
    mValues[idx] = c;
    return this;
  }

  public Vec3 getC0() {
    return mValues[0];
  }

  public Vec3 getC1() {
    return mValues[1];
  }

  public Vec3 getC2() {
    return mValues[2];
  }

  public Vec3 get(int idx) {
    return mValues[idx];
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Mat3)) {
      return false;
    }

    return Arrays.equals(mValues, ((Mat3) other).mValues);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(mValues[0], mValues[1], mValues[2]);
  }
}
