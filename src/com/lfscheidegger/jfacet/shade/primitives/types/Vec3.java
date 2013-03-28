package com.lfscheidegger.jfacet.shade.primitives.types;

import com.google.common.base.Objects;

import java.util.Arrays;

/**
 * Aggregate type for vec3.
 */
public class Vec3 {

  private float[] mValues;

  public Vec3() {
    mValues = new float[]{0, 0, 0};
  }

  public Vec3(float x, float y, float z) {
    mValues = new float[]{x, y, z};
  }

  public Vec3(Vec2 xy, float z) {
    mValues = new float[]{xy.getX(), xy.getY(), z};
  }

  public Vec3(float x, Vec2 yz) {
    mValues = new float[]{x, yz.getX(), yz.getY()};
  }

  public Vec3(Vec3 other) {
    mValues = new float[]{other.getX(), other.getY(), other.getZ()};
  }

  public Vec3 setX(float x) {
    mValues[0] = x;
    return this;
  }

  public Vec3 setY(float y) {
    mValues[1] = y;
    return this;
  }

  public Vec3 setZ(float z) {
    mValues[2] = z;
    return this;
  }

  public Vec3 set(float v, int idx) {
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

  public float get(int idx) {
    return mValues[idx];
  }

  public Vec3 add(Vec3 other) {
    return new Vec3(mValues[0] + other.mValues[0], mValues[1] + other.mValues[1], mValues[2] + other.mValues[2]);
  }

  public Vec3 add(float t) {
    return new Vec3(mValues[0] + t, mValues[1] + t, mValues[2] + t);
  }

  public Vec3 sub(Vec3 other) {
    return new Vec3(mValues[0] - other.mValues[0], mValues[1] - other.mValues[1], mValues[2] - other.mValues[2]);
  }

  public Vec3 sub(float t) {
    return new Vec3(mValues[0] - t, mValues[1] - t, mValues[2] - t);
  }

  public Vec3 neg() {
    return new Vec3(-mValues[0], -mValues[1], -mValues[2]);
  }

  public Vec3 mul(float t) {
    return new Vec3(mValues[0] * t, mValues[1] * t, mValues[2] * t);
  }

  public Vec3 mul(Vec3 other) {
    return new Vec3(mValues[0] * other.mValues[0], mValues[1] * other.mValues[1], mValues[2] * other.mValues[2]);
  }

  public Vec3 div(float t) {
    return new Vec3(mValues[0] / t, mValues[1] / t, mValues[2] / t);
  }

  public Vec3 div(Vec3 other) {
    return new Vec3(mValues[0] / other.mValues[0], mValues[1] / other.mValues[1], mValues[2] / other.mValues[2]);
  }

  public float dot(Vec3 other) {
    return mValues[0] * other.mValues[0] + mValues[1] * other.mValues[1] + mValues[2] * other.mValues[2];
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Vec3)) {
      return false;
    }

    return Arrays.equals(mValues, ((Vec3) other).mValues);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(mValues[0], mValues[1], mValues[2]);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(Vec3.class)
        .addValue(mValues[0])
        .addValue(mValues[1])
        .addValue(mValues[2])
        .toString();
  }
}
