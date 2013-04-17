package com.lfscheidegger.jfacet.shade.primitives;

import com.google.common.base.Preconditions;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.SupportsBasicArithmetic;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.SupportsNormalization;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.VectorLike;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.StringUtils;

import java.util.Arrays;

public final class Vector implements SupportsBasicArithmetic<Vector>, SupportsNormalization<Vector>, VectorLike {

  private final float[] mValues;
  private final int mDimension;

  public Vector(float x, float y) {
    mDimension = 2;
    mValues = new float[]{x, y};
  }

  public Vector(float x, float y, float z) {
    mDimension = 3;
    mValues = new float[]{x, y, z};
  }

  public Vector(float x, float y, float z, float w) {
    mDimension = 4;
    mValues = new float[]{x, y, z, w};
  }

  Vector(float[] buffer) {
    Preconditions.checkState(buffer.length > 1 && buffer.length <= 4);

    mDimension = buffer.length;
    mValues = buffer;
  }

  public int getDimension() {
    return mDimension;
  }

  public Vector setX(float value) {
    return set(value, 0);
  }

  public Vector setY(float value) {
    return set(value, 1);
  }

  public Vector setZ(float value) {
    return set(value, 2);
  }

  public Vector setW(float value) {
    return set(value, 3);
  }

  public Vector set(float value, int component) {
    Preconditions.checkState(component < mDimension);
    mValues[component] = value;
    return this;
  }

  public float getX() {
    return get(0);
  }

  public float getY() {
    return get(1);
  }

  public float getZ() {
    return get(2);
  }

  public float getW() {
    return get(3);
  }

  public float get(int component) {
    Preconditions.checkState(component < mDimension);
    return mValues[component];
  }

  public float[] getArray() {
    return mValues;
  }

  public Vector add(Vector other) {
    Preconditions.checkState(mDimension == other.mDimension);
    return new Vector(ArrayUtils.add(mValues, other.mValues));
  }

  public Vector add(float t) {
    return new Vector(ArrayUtils.add(mValues, t));
  }

  public Vector sub(Vector other) {
    Preconditions.checkState(mDimension == other.mDimension);
    return new Vector(ArrayUtils.sub(mValues, other.mValues));
  }

  public Vector sub(float t) {
    return new Vector(ArrayUtils.sub(mValues, t));
  }

  public Vector mul(Vector other) {
    Preconditions.checkState(mDimension == other.mDimension);
    return new Vector(ArrayUtils.mul(mValues, other.mValues));
  }

  public Vector mul(float t) {
    return new Vector(ArrayUtils.mul(mValues, t));
  }

  public Vector div(Vector other) {
    Preconditions.checkState(mDimension == other.mDimension);
    return new Vector(ArrayUtils.div(mValues, other.mValues));
  }

  public Vector div(float t) {
    return new Vector(ArrayUtils.div(mValues, t));
  }

  public Vector neg() {
    return new Vector(ArrayUtils.neg(mValues));
  }

  public Vector normalize() {
    return new Vector(ArrayUtils.normalize(mValues));
  }

  public Vector cross(Vector other) {
    Preconditions.checkState(mDimension == 3 && mDimension == other.mDimension);

    return new Vector(
        getY() * other.getZ() - getZ() * other.getY(),
        getZ() * other.getX() - getX() * other.getZ(),
        getX() * other.getY() - getY() * other.getX());
  }

  public float dot(Vector other) {
    Preconditions.checkState(mDimension == other.mDimension);

    return ArrayUtils.dot(mValues, other.mValues);
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Vector)) {
      return false;
    }

    return Arrays.equals(mValues, ((Vector)other).mValues);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(mValues);
  }

  @Override
  public String toString() {
    Type type;
    switch(mDimension) {
      case 2: type = Type.VEC2_T; break;
      case 3: type = Type.VEC3_T; break;
      case 4: type = Type.VEC4_T; break;
      default: throw new RuntimeException("Invalid dimension for vector: " + mDimension);
    }

    StringUtils.StringHelper helper = StringUtils.toStringHelper(type);
    for (int i = 0; i < mDimension; i++) {
      helper.addValue(StringUtils.wrapFloat(mValues[i]));
    }

    return helper.toString();
  }
}
