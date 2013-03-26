package com.lfscheidegger.jfacet.shade.internal.types;

import com.google.common.base.Objects;

import java.util.Arrays;

/**
 * Aggregate type for mat4
 */
public class Mat4 {

  private final Vec4[] mValues;

  public Mat4() {
    mValues = new Vec4[]{new Vec4(1, 0, 0, 0), new Vec4(0, 1, 0, 0), new Vec4(0, 0, 1, 0), new Vec4(0, 0, 0, 1)};
  }

  public Mat4(Vec4 c0, Vec4 c1, Vec4 c2, Vec4 c3) {
    mValues = new Vec4[]{c0, c1, c2, c3};
  }

  public Mat4(Mat4 other) {
    mValues = new Vec4[]{other.getC0(), other.getC1(), other.getC2(), other.getC3()};
  }

  public Mat4 setC0(Vec4 c0) {
    mValues[0] = c0;
    return this;
  }

  public Mat4 setC1(Vec4 c1) {
    mValues[1] = c1;
    return this;
  }

  public Mat4 setC2(Vec4 c2) {
    mValues[2] = c2;
    return this;
  }

  public Mat4 setC3(Vec4 c3) {
    mValues[3] = c3;
    return this;
  }

  public Mat4 set(Vec4 c, int idx) {
    mValues[idx] = c;
    return this;
  }

  public Vec4 getC0() {
    return mValues[0];
  }

  public Vec4 getC1() {
    return mValues[1];
  }

  public Vec4 getC2() {
    return mValues[2];
  }

  public Vec4 getC3() {
    return mValues[3];
  }

  public Vec4 get(int idx) {
    return mValues[idx];
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Mat4)) {
      return false;
    }

    return Arrays.equals(mValues, ((Mat4) other).mValues);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(mValues[0], mValues[1], mValues[2], mValues[3]);
  }
}
