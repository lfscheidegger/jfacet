package com.lfscheidegger.jfacet.shade.primitives;

import com.google.common.base.Objects;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.Mat4Like;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.SupportsBasicArithmetic;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.StringUtils;

import java.util.Arrays;

/**
 * Aggregate type for mat4
 */
public final class Mat4 implements Mat4Like, SupportsBasicArithmetic<Mat4> {

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

  public float[] getArray() {
    return mValues;
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
    return StringUtils.toStringHelper(Type.MAT4_T)
        .addValue(new Vec4(mValues[0], mValues[1], mValues[2], mValues[3]))
        .addValue(new Vec4(mValues[4], mValues[5], mValues[6], mValues[7]))
        .addValue(new Vec4(mValues[8], mValues[9], mValues[10], mValues[11]))
        .addValue(new Vec4(mValues[12], mValues[13], mValues[14], mValues[15]))
        .toString();
  }

  public Mat4 add(float t) {
    return new Mat4(ArrayUtils.add(mValues, t));
  }

  public Mat4 add(Mat4 other) {
    return new Mat4(ArrayUtils.add(mValues, other.mValues));
  }

  public Mat4 sub(float t) {
    return new Mat4(ArrayUtils.sub(mValues, t));
  }

  public Mat4 sub(Mat4 other) {
    return new Mat4(ArrayUtils.sub(mValues, other.mValues));
  }

  public Mat4 neg() {
    return new Mat4(ArrayUtils.mul(mValues, -1));
  }

  public Mat4 mul(float t) {
    return new Mat4(ArrayUtils.mul(mValues, t));
  }

  public Mat4 mul(Mat4 mat) {
    return new Mat4(ArrayUtils.mulMatrix(mValues, mat.mValues, 4));
  }

  public Mat4 div(float t) {
    return new Mat4(ArrayUtils.div(mValues, t));
  }

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

  public float determinant() {
    float a00 = mValues[0], a01 = mValues[1], a02 = mValues[2], a03 = mValues[3];
    float a10 = mValues[4], a11 = mValues[5], a12 = mValues[6], a13 = mValues[7];
    float a20 = mValues[8], a21 = mValues[9], a22 = mValues[10], a23 = mValues[11];
    float a30 = mValues[12], a31 = mValues[13], a32 = mValues[14], a33 = mValues[15];

    return a30*a21*a12*a03 - a20*a31*a12*a03 - a30*a11*a22*a03 + a10*a31*a22*a03 +
        a20*a11*a32*a03 - a10*a21*a32*a03 - a30*a21*a02*a13 + a20*a31*a02*a13 +
        a30*a01*a22*a13 - a00*a31*a22*a13 - a20*a01*a32*a13 + a00*a21*a32*a13 +
        a30*a11*a02*a23 - a10*a31*a02*a23 - a30*a01*a12*a23 + a00*a31*a12*a23 +
        a10*a01*a32*a23 - a00*a11*a32*a23 - a20*a11*a02*a33 + a10*a21*a02*a33 +
        a20*a01*a12*a33 - a00*a21*a12*a33 - a10*a01*a22*a33 + a00*a11*a22*a33;
  }

  public Mat4 transpose() {
    float[] result = new float[16];
    result[0] = mValues[0];
    result[1] = mValues[4];
    result[2] = mValues[8];
    result[3] = mValues[12];
    result[4] = mValues[1];
    result[5] = mValues[5];
    result[6] = mValues[9];
    result[7] = mValues[13];
    result[8] =  mValues[2];
    result[9] =  mValues[6];
    result[10] = mValues[10];
    result[11] = mValues[14];
    result[12] = mValues[3];
    result[13] = mValues[7];
    result[14] = mValues[11];
    result[15] = mValues[15];

    return new Mat4(result);
  }

  public Mat4 inverse() {
    float a00 = mValues[0], a01 = mValues[1], a02 = mValues[2], a03 = mValues[3];
    float a10 = mValues[4], a11 = mValues[5], a12 = mValues[6], a13 = mValues[7];
    float a20 = mValues[8], a21 = mValues[9], a22 = mValues[10], a23 = mValues[11];
    float a30 = mValues[12], a31 = mValues[13], a32 = mValues[14], a33 = mValues[15];

    float b00 = a00*a11 - a01*a10;
    float b01 = a00*a12 - a02*a10;
    float b02 = a00*a13 - a03*a10;
    float b03 = a01*a12 - a02*a11;
    float b04 = a01*a13 - a03*a11;
    float b05 = a02*a13 - a03*a12;
    float b06 = a20*a31 - a21*a30;
    float b07 = a20*a32 - a22*a30;
    float b08 = a20*a33 - a23*a30;
    float b09 = a21*a32 - a22*a31;
    float b10 = a21*a33 - a23*a31;
    float b11 = a22*a33 - a23*a32;

    // Calculate the determinant (inlined to avoid double-caching)
    float det = (b00*b11 - b01*b10 + b02*b09 + b03*b08 - b04*b07 + b05*b06);

    float[] result = new float[16];
    result[0] = (a11*b11 - a12*b10 + a13*b09)/det;
    result[1] = (-a01*b11 + a02*b10 - a03*b09)/det;
    result[2] = (a31*b05 - a32*b04 + a33*b03)/det;
    result[3] = (-a21*b05 + a22*b04 - a23*b03)/det;
    result[4] = (-a10*b11 + a12*b08 - a13*b07)/det;
    result[5] = (a00*b11 - a02*b08 + a03*b07)/det;
    result[6] = (-a30*b05 + a32*b02 - a33*b01)/det;
    result[7] = (a20*b05 - a22*b02 + a23*b01)/det;
    result[8] = (a10*b10 - a11*b08 + a13*b06)/det;
    result[9] = (-a00*b10 + a01*b08 - a03*b06)/det;
    result[10] = (a30*b04 - a31*b02 + a33*b00)/det;
    result[11] = (-a20*b04 + a21*b02 - a23*b00)/det;
    result[12] = (-a10*b09 + a11*b07 - a12*b06)/det;
    result[13] = (a00*b09 - a01*b07 + a02*b06)/det;
    result[14] = (-a30*b03 + a31*b01 - a32*b00)/det;
    result[15] = (a20*b03 - a21*b01 + a22*b00)/det;

    return new Mat4(result);
  }
}
