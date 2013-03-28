package com.lfscheidegger.jfacet.shade.primitives.types;

import com.google.common.base.Objects;

import java.util.Arrays;

/**
 * Aggregate type for vec2.
 */
public final class Vec2 implements
    SupportsAddition<Vec2>,
    SupportsSubtraction<Vec2>,
    SupportsMultiplication<Vec2> {

  private final float[] mValues;

  public Vec2() {
    mValues = new float[]{0, 0};
  }

  public Vec2(float x, float y) {
    mValues = new float[]{x, y};
  }

  public Vec2(Vec2 other) {
    mValues = new float[]{other.getX(), other.getY()};
  }

  public Vec2 setX(float x) {
    mValues[0] = x;
    return this;
  }

  public Vec2 setY(float y) {
    mValues[1] = y;
    return this;
  }

  public Vec2 set(float v, int idx) {
    mValues[idx] = v;
    return this;
  }

  public float getX() {
    return mValues[0];
  }

  public float getY() {
    return mValues[1];
  }

  public float get(int idx) {
    return mValues[idx];
  }

  public Vec2 add(Vec2 other) {
    return new Vec2(mValues[0] + other.mValues[0], mValues[1] + other.mValues[1]);
  }

  public Vec2 add(float t) {
    return new Vec2(mValues[0] + t, mValues[1] + t);
  }

  public Vec2 sub(Vec2 other) {
    return new Vec2(mValues[0] - other.mValues[0], mValues[1] - other.mValues[1]);
  }

  public Vec2 sub(float t) {
    return new Vec2(mValues[0] - t, mValues[1] - t);
  }

  public Vec2 neg() {
    return new Vec2(-mValues[0], -mValues[1]);
  }

  public Vec2 mul(float t) {
    return new Vec2(mValues[0] * t, mValues[1] * t);
  }

  public Vec2 mul(Vec2 other) {
    return new Vec2(mValues[0] * other.mValues[0], mValues[1] * other.mValues[1]);
  }

  public Vec2 div(float t) {
    return new Vec2(mValues[0] / t, mValues[1] / t);
  }

  public Vec2 div(Vec2 other) {
    return new Vec2(mValues[0] / other.mValues[0], mValues[1] / other.mValues[1]);
  }

  public float dot(Vec2 other) {
    return mValues[0] * other.mValues[0] + mValues[1] * other.mValues[1];
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Vec2)) {
      return false;
    }

    return Arrays.equals(mValues, ((Vec2)other).mValues);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(mValues[0], mValues[1]);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(Vec2.class)
        .addValue(mValues[0])
        .addValue(mValues[1])
        .toString();
  }
}
