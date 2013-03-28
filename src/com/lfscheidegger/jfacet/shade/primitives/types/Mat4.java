package com.lfscheidegger.jfacet.shade.primitives.types;

import com.google.common.base.Objects;
import com.lfscheidegger.jfacet.shade.utils.ArrayUtils;

import java.util.Arrays;

/**
 * Aggregate type for mat4
 */
public class Mat4 implements SupportsBasicArithmetic<Mat4> {

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

  Mat4(float[] other) {
    mValues = other;
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

  @Override
  public Mat4 add(float t) {
    return new Mat4(ArrayUtils.add(mValues, t));
  }

  @Override
  public Mat4 add(Mat4 other) {
    return new Mat4(ArrayUtils.add(mValues, other.mValues));
  }

  @Override
  public Mat4 sub(float t) {
    return new Mat4(ArrayUtils.sub(mValues, t));
  }

  @Override
  public Mat4 sub(Mat4 other) {
    return new Mat4(ArrayUtils.sub(mValues, other.mValues));
  }

  @Override
  public Mat4 neg() {
    return new Mat4(ArrayUtils.mul(mValues, -1));
  }

  @Override
  public Mat4 mul(float t) {
    return new Mat4(ArrayUtils.mul(mValues, t));
  }

  @Override
  public Mat4 mul(Mat4 mat) {
    return new Mat4(ArrayUtils.mulMatrix(mValues, mat.mValues, 4));
  }

  @Override
  public Mat4 div(float t) {
    return new Mat4(ArrayUtils.div(mValues, t));
  }

  @Override
  public Mat4 div(Mat4 other) {
    return new Mat4(ArrayUtils.div(mValues, other.mValues));
  }

  public Vec4 mul(Vec4 vec) {
    return new Vec4(
        mValues[0] * vec.getX() + mValues[4] * vec.getY() + mValues[8] * vec.getZ() + mValues[12] * vec.getW(),
        mValues[1] * vec.getX() + mValues[5] * vec.getY() + mValues[9] * vec.getZ() + mValues[13] * vec.getW(),
        mValues[2] * vec.getX() + mValues[6] * vec.getY() + mValues[10] * vec.getZ() + mValues[14] * vec.getW(),
        mValues[3] * vec.getX() + mValues[7] * vec.getY() + mValues[11] * vec.getZ() + mValues[15] * vec.getW());
  }
}
