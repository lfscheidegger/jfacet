package com.lfscheidegger.jfacet.shade.primitives;

import com.google.common.base.Objects;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.Mat2Like;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.SupportsBasicArithmetic;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.StringUtils;

import java.util.Arrays;

/**
 * Aggregate type for mat2
 */
public final class Mat2 implements Mat2Like, SupportsBasicArithmetic<Mat2> {

  private final float[] mValues;

  public Mat2() {
    mValues = new float[]{1, 0, 0, 1};
  }

  public Mat2(Vec2 c0, Vec2 c1) {
    mValues = new float[]{c0.getX(), c0.getY(), c1.getX(), c1.getY()};
  }

  public Mat2(Mat2 other) {
    mValues = new float[] {other.getC0().getX(), other.getC0().getY(), other.getC1().getX(), other.getC1().getY()};
  }

  Mat2(float[] other) {
    mValues = other;
  }

  public Mat2 setC0(Vec2 c0) {
    mValues[0] = c0.getX();
    mValues[1] = c0.getY();
    return this;
  }

  public Mat2 setC1(Vec2 c1) {
    mValues[2] = c1.getX();
    mValues[3] = c1.getY();
    return this;
  }

  public Mat2 set(Vec2 c, int idx) {
    mValues[2 * idx] = c.getX();
    mValues[2 * idx + 1] = c.getY();
    return this;
  }

  public Vec2 getC0() {
    return new Vec2(mValues[0], mValues[1]);
  }

  public Vec2 getC1() {
    return new Vec2(mValues[2], mValues[3]);
  }

  public Vec2 get(int idx) {
    return new Vec2(mValues[2 * idx], mValues[2 * idx + 1]);
  }

  public float[] getArray() {
    return mValues;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Mat2)) {
      return false;
    }

    return Arrays.equals(mValues, ((Mat2) other).mValues);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(mValues[0], mValues[1]);
  }

  @Override
  public String toString() {
    return StringUtils.toStringHelper(Type.MAT2_T)
        .addValue(new Vec2(mValues[0], mValues[1]))
        .addValue(new Vec2(mValues[2], mValues[3]))
        .toString();
  }

  public Mat2 add(float t) {
    return new Mat2(ArrayUtils.add(mValues, t));
  }

  public Mat2 add(Mat2 other) {
    return new Mat2(ArrayUtils.add(mValues, other.mValues));
  }

  public Mat2 sub(float t) {
    return new Mat2(ArrayUtils.sub(mValues, t));
  }

  public Mat2 sub(Mat2 other) {
    return new Mat2(ArrayUtils.sub(mValues, other.mValues));
  }

  public Mat2 neg() {
    return new Mat2(ArrayUtils.mul(mValues, -1));
  }

  public Mat2 mul(float t) {
    return new Mat2(ArrayUtils.mul(mValues, t));
  }

  public Mat2 mul(Mat2 mat) {
    float[] buf = new float[] {
        mValues[0] * mat.mValues[0] + mValues[2] * mat.mValues[1],
        mValues[1] * mat.mValues[0] + mValues[3] * mat.mValues[1],
        mValues[0] * mat.mValues[2] + mValues[2] * mat.mValues[3],
        mValues[1] * mat.mValues[2] + mValues[3] * mat.mValues[3],
    };
    return new Mat2(buf);
  }

  public Mat2 div(float t) {
    return new Mat2(ArrayUtils.div(mValues, t));
  }

  public Mat2 div(Mat2 other) {
    return new Mat2(ArrayUtils.div(mValues, other.mValues));
  }

  public Vec2 mul(Vec2 vec) {
    return new Vec2(
        mValues[0] * vec.getX() + mValues[2] * vec.getY(),
        mValues[1] * vec.getY() + mValues[3] * vec.getY());
  }

  public float determinant() {
    return mValues[0] * mValues[3] - mValues[1] * mValues[2];
  }

  public  Mat2 transpose() {
    float[] result = new float[4];
    result[0] = mValues[0];
    result[1] = mValues[2];
    result[2] = mValues[1];
    result[3] = mValues[3];

    return new Mat2(result);
  }

  public Mat2 inverse() {
    float a00 = mValues[0], a01 = mValues[1];
    float a10 = mValues[2], a11 = mValues[3];

    // Calculate the determinant (inlined to avoid double-caching)
    float det = (a00*a11 - a01*a10);
    if (det == 0) {
      throw new RuntimeException("Singular matrix");
    }

    float[] result = new float[4];
    result[0] =  a11/det;
    result[1] = -a01/det;
    result[2] = -a10/det;
    result[3] =  a00/det;

    return new Mat2(result);
  }
}
