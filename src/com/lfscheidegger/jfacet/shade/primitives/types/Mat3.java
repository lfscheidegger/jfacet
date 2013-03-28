package com.lfscheidegger.jfacet.shade.primitives.types;

import com.google.common.base.Objects;
import com.lfscheidegger.jfacet.shade.utils.ArrayUtils;

import java.util.Arrays;

/**
 * Aggregate type for mat3.
 */
public class Mat3 implements
    SupportsAddition<Mat3>,
    SupportsSubtraction<Mat3>,
    SupportsMultiplication<Mat3> {

  private final float[] mValues;

  public Mat3() {
    mValues = new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1};
  }

  public Mat3(Vec3 c0, Vec3 c1, Vec3 c2) {
    mValues = new float[] {
        c0.get(0), c0.get(1), c0.get(2),
        c1.get(0), c1.get(1), c1.get(2),
        c2.get(0), c2.get(1), c2.get(2)
    };
  }

  public Mat3(Mat3 other) {
    mValues = new float[] {
        other.get(0).get(0), other.get(0).get(1), other.get(0).get(2),
        other.get(1).get(0), other.get(1).get(1), other.get(1).get(2),
        other.get(2).get(0), other.get(2).get(1), other.get(2).get(2)
    };
  }

  Mat3(float[] other) {
    mValues = other;
  }

  public Mat3 setC0(Vec3 c0) {
    mValues[0] = c0.get(0);
    mValues[1] = c0.get(1);
    mValues[2] = c0.get(2);
    return this;
  }

  public Mat3 setC1(Vec3 c1) {
    mValues[3] = c1.get(0);
    mValues[4] = c1.get(1);
    mValues[5] = c1.get(2);
    return this;
  }

  public Mat3 setC2(Vec3 c2) {
    mValues[6] = c2.get(0);
    mValues[7] = c2.get(1);
    mValues[8] = c2.get(2);
    return this;
  }

  public Mat3 set(Vec3 c, int idx) {
    mValues[3 * idx] = c.get(0);
    mValues[3 * idx + 1] = c.get(1);
    mValues[3 * idx + 2] = c.get(2);
    return this;
  }

  public Vec3 getC0() {
    return new Vec3(mValues[0], mValues[1], mValues[2]);
  }

  public Vec3 getC1() {
    return new Vec3(mValues[3], mValues[4], mValues[5]);
  }

  public Vec3 getC2() {
    return new Vec3(mValues[6], mValues[7], mValues[8]);
  }

  public Vec3 get(int idx) {
    return new Vec3(mValues[3 * idx], mValues[3 * idx + 1], mValues[3 * idx + 2]);
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

  @Override
  public String toString() {
    return Objects.toStringHelper(Mat3.class)
        .addValue(new Vec3(mValues[0], mValues[1], mValues[2]))
        .addValue(new Vec3(mValues[3], mValues[4], mValues[5]))
        .addValue(new Vec3(mValues[6], mValues[7], mValues[8]))
        .toString();
  }

  public Mat3 add(float t) {
    return new Mat3(ArrayUtils.add(mValues, t));
  }

  public Mat3 add(Mat3 other) {
    return new Mat3(ArrayUtils.add(mValues, other.mValues));
  }

  public Mat3 sub(float t) {
    return new Mat3(ArrayUtils.sub(mValues, t));
  }

  public Mat3 sub(Mat3 other) {
    return new Mat3(ArrayUtils.sub(mValues, other.mValues));
  }

  public Mat3 neg() {
    return new Mat3(ArrayUtils.mul(mValues, -1));
  }

  public Mat3 mul(float t) {
    return new Mat3(ArrayUtils.mul(mValues, t));
  }

  public Vec3 mul(Vec3 vec) {
    return new Vec3(
        mValues[0] * vec.getX() + mValues[3] * vec.getY() + mValues[6] * vec.getZ(),
        mValues[1] * vec.getX() + mValues[4] * vec.getY() + mValues[7] * vec.getZ(),
        mValues[2] * vec.getX() + mValues[5] * vec.getY() + mValues[8] * vec.getZ());
  }

  public Mat3 mul(Mat3 mat) {
    return new Mat3(ArrayUtils.mulMatrix(mValues, mat.mValues, 3));
  }
}
