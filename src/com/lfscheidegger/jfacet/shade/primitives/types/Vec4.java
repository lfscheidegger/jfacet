package com.lfscheidegger.jfacet.shade.primitives.types;

import com.google.common.base.Objects;

import java.util.Arrays;

/**
 * Aggregate type for vec3.
 */
public class Vec4 {

  private float[] mValues;

  public Vec4() {
    mValues = new float[]{0, 0, 0, 0};
  }

  public Vec4(float x, float y, float z, float w) {
    mValues = new float[]{x, y, z, w};
  }

  public Vec4(Vec2 xy, float z, float w) {
    mValues = new float[]{xy.getX(), xy.getY(), z, w};
  }

  public Vec4(float x, Vec2 yz, float w) {
    mValues = new float[]{x, yz.getX(), yz.getY(), w};
  }

  public Vec4(float x, float y, Vec2 zw) {
    mValues = new float[]{x, y, zw.getX(), zw.getY()};
  }

  public Vec4(Vec2 xy, Vec2 zw) {
    mValues = new float[]{xy.getX(), xy.getY(), zw.getX(), zw.getY()};
  }

  public Vec4(Vec3 xyz, float w) {
    mValues = new float[]{xyz.getX(), xyz.getY(), xyz.getZ(), w};
  }

  public Vec4(float x, Vec3 yzw) {
    mValues = new float[]{x, yzw.getX(), yzw.getY(), yzw.getZ()};
  }

  public Vec4(Vec4 other) {
    mValues = new float[]{other.getX(), other.getY(), other.getZ(), other.getW()};
  }

  public Vec4 setX(float x) {
    mValues[0] = x;
    return this;
  }

  public Vec4 setY(float y) {
    mValues[1] = y;
    return this;
  }

  public Vec4 setZ(float z) {
    mValues[2] = z;
    return this;
  }

  public Vec4 setW(float w) {
    mValues[3] = w;
    return this;
  }

  public Vec4 set(float v, int idx) {
    mValues[idx] = v;
    return this;
  }

  public float getX() {
    return mValues[0];
  }

  public float getY() {
    return mValues[1];
  }

  public float getZ() {
    return mValues[2];
  }

  public float getW() {
    return mValues[3];
  }

  public float get(int idx) {
    return mValues[idx];
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Vec4)) {
      return false;
    }

    return Arrays.equals(mValues, ((Vec4) other).mValues);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(mValues[0], mValues[1], mValues[2], mValues[3]);
  }

  public Vec4 add(Vec4 other) {
    return new Vec4(
        mValues[0] + other.mValues[0],
        mValues[1] + other.mValues[1],
        mValues[2] + other.mValues[2],
        mValues[3] + other.mValues[3]);
  }

  public Vec4 add(float t) {
    return new Vec4(mValues[0] + t, mValues[1] + t, mValues[2] + t, mValues[3] + t);
  }

  public Vec4 sub(Vec4 other) {
    return new Vec4(
        mValues[0] - other.mValues[0],
        mValues[1] - other.mValues[1],
        mValues[2] - other.mValues[2],
        mValues[3] - other.mValues[3]);
  }

  public Vec4 sub(float t) {
    return new Vec4(mValues[0] - t, mValues[1] - t, mValues[2] - t, mValues[3] - t);
  }

  public Vec4 neg() {
    return new Vec4(-mValues[0], -mValues[1], -mValues[2], -mValues[3]);
  }

  public Vec4 mul(float t) {
    return new Vec4(mValues[0] * t, mValues[1] * t, mValues[2] * t, mValues[3] * t);
  }

  public Vec4 mul(Vec4 other) {
    return new Vec4(
        mValues[0] * other.mValues[0],
        mValues[1] * other.mValues[1],
        mValues[2] * other.mValues[2],
        mValues[3] * other.mValues[3]);
  }

  public Vec4 div(float t) {
    return new Vec4(mValues[0] / t, mValues[1] / t, mValues[2] / t, mValues[3] / t);
  }

  public Vec4 div(Vec4 other) {
    return new Vec4(
        mValues[0] / other.mValues[0],
        mValues[1] / other.mValues[1],
        mValues[2] / other.mValues[2],
        mValues[3] / other.mValues[3]);
  }

  public float dot(Vec4 other) {
    return
        mValues[0] * other.mValues[0] +
        mValues[1] * other.mValues[1] +
        mValues[2] * other.mValues[2] +
        mValues[3] * other.mValues[3];
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(Vec4.class)
        .addValue(mValues[0])
        .addValue(mValues[1])
        .addValue(mValues[2])
        .addValue(mValues[3])
        .toString();
  }
}
