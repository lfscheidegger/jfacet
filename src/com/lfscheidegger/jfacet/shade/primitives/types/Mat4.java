package com.lfscheidegger.jfacet.shade.primitives.types;

import com.google.common.base.Objects;

import java.util.Arrays;

/**
 * Aggregate type for mat4
 */
public class Mat4 {

  private final float[] mValues;

  public Mat4() {
    mValues = new float[]{
        1, 0, 0, 0,
        0, 1, 0, 0,
        0, 0, 1, 0,
        0, 0, 0, 1};
  }

  public Mat4(Vec4 c0, Vec4 c1, Vec4 c2, Vec4 c3) {
    mValues = new float[] {
        c0.get(0), c0.get(1), c0.get(2), c0.get(3),
        c1.get(0), c1.get(1), c1.get(2), c1.get(3),
        c2.get(0), c2.get(1), c2.get(2), c2.get(3),
        c3.get(0), c3.get(1), c3.get(2), c3.get(3)
    };
  }

  public Mat4(Mat4 other) {
    mValues = new float[] {
        other.get(0).get(0), other.get(0).get(1), other.get(0).get(2), other.get(0).get(3),
        other.get(1).get(0), other.get(1).get(1), other.get(1).get(2), other.get(1).get(3),
        other.get(2).get(0), other.get(2).get(1), other.get(2).get(2), other.get(2).get(3),
        other.get(3).get(0), other.get(3).get(1), other.get(3).get(2), other.get(3).get(3),
    };
  }

  public Mat4 setC0(Vec4 c0) {
    mValues[0] = c0.get(0);
    mValues[1] = c0.get(1);
    mValues[2] = c0.get(2);
    mValues[3] = c0.get(3);
    return this;
  }

  public Mat4 setC1(Vec4 c1) {
    mValues[4] = c1.get(0);
    mValues[5] = c1.get(1);
    mValues[6] = c1.get(2);
    mValues[7] = c1.get(3);
    return this;
  }

  public Mat4 setC2(Vec4 c2) {
    mValues[8] = c2.get(0);
    mValues[9] = c2.get(1);
    mValues[10] = c2.get(2);
    mValues[11] = c2.get(3);
    return this;
  }

  public Mat4 setC3(Vec4 c3) {
    mValues[12] = c3.get(0);
    mValues[13] = c3.get(1);
    mValues[14] = c3.get(2);
    mValues[15] = c3.get(3);
    return this;
  }

  public Mat4 set(Vec4 c, int idx) {
    mValues[4 * idx] = c.get(0);
    mValues[4 * idx + 1] = c.get(1);
    mValues[4 * idx + 2] = c.get(2);
    mValues[4 * idx + 3] = c.get(3);
    return this;
  }

  public Vec4 getC0() {
    return new Vec4(mValues[0], mValues[1], mValues[2], mValues[3]);
  }

  public Vec4 getC1() {
    return new Vec4(mValues[4], mValues[5], mValues[6], mValues[7]);
  }

  public Vec4 getC2() {
    return new Vec4(mValues[8], mValues[9], mValues[10], mValues[11]);
  }

  public Vec4 getC3() {
    return new Vec4(mValues[12], mValues[13], mValues[14], mValues[15]);
  }

  public Vec4 get(int idx) {
    return new Vec4(mValues[4 * idx], mValues[4 * idx + 1], mValues[4 * idx + 2], mValues[4 * idx + 3]);
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

  @Override
  public String toString() {
    return Objects.toStringHelper(Mat4.class)
        .addValue(new Vec4(mValues[0], mValues[1], mValues[2], mValues[3]))
        .addValue(new Vec4(mValues[4], mValues[5], mValues[6], mValues[7]))
        .addValue(new Vec4(mValues[8], mValues[9], mValues[10], mValues[11]))
        .addValue(new Vec4(mValues[12], mValues[13], mValues[14], mValues[15]))
        .toString();
  }
}
