// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.utils;

import com.google.common.base.Preconditions;

public final class ArrayUtils {

  private ArrayUtils() {}

  public static float[] add(float[] left, float t) {
    float[] result = new float[left.length];

    for (int i = 0; i < left.length; i++) {
      result[i] = left[i] + t;
    }

    return result;
  }

  public static float[] add(float[] left, float[] right) {
    Preconditions.checkState(left.length == right.length);

    float[] result = new float[left.length];
    for (int i = 0; i < left.length; i++) {
      result[i] = left[i] + right[i];
    }

    return result;
  }

  public static float[] sub(float[] left, float[] right) {
    Preconditions.checkState(left.length == right.length);

    float[] result = new float[left.length];
    for (int i = 0; i < left.length; i++) {
      result[i] = left[i] - right[i];
    }

    return result;
  }

  public static float[] sub(float[] left, float t) {
    float[] result = new float[left.length];

    for (int i = 0; i < left.length; i++) {
      result[i] = left[i] - t;
    }

    return result;
  }

  public static float[] mul(float[] left, float[] right) {
    Preconditions.checkState(left.length == right.length);

    float[] result = new float[left.length];
    for (int i = 0; i < left.length; i++) {
      result[i] = left[i] * right[i];
    }

    return result;
  }

  public static float[] mul(float[] left, float t) {
    float[] result = new float[left.length];

    for (int i = 0; i < left.length; i++) {
      result[i] = left[i] * t;
    }

    return result;
  }

  public static float[] div(float[] left, float t) {
    float[] result = new float[left.length];

    for (int i = 0; i < left.length; i++) {
      result[i] = left[i] / t;
    }

    return result;
  }

  public static float[] div(float[] left, float[] right) {
    Preconditions.checkState(left.length == right.length);

    float[] result = new float[left.length];
    for (int i = 0; i < left.length; i++) {
      result[i] = left[i] / right[i];
    }

    return result;
  }

  public static float[] neg(float[] values) {
    float[] result = new float[values.length];

    for (int i = 0; i < values.length; i++) {
      result[i] = -values[i];
    }

    return result;
  }

  public static float dot(float[] left, float[] right) {
    Preconditions.checkState(left.length == right.length);

    float result = 0;
    for (int i = 0; i < left.length; i++) {
      result += left[i] * right[i];
    }

    return result;
  }

  public static float[] normalize(float[] values) {
    float norm = length(values);

    float[] result = new float[values.length];
    for (int i = 0; i < values.length; i++) {
      result[i] = values[i] / norm;
    }

    return result;
  }

  public static float length(float[] values) {
    return (float)Math.sqrt(dot(values, values));
  }

  public static float[] mulMatrix(float[] left, float[] right, int dim) {
    Preconditions.checkState(left.length == right.length && right.length == dim * dim);
    float[] result = new float[left.length];
    for (int row = 0; row < dim; row++) {
      for (int col = 0; col < dim; col++) {
        float val = 0;
        for (int k = 0; k < dim; k++) {
          val += left[row + k * dim] * right[col * dim + k];
        }
        result[dim * col + row] = val;
      }
    }

    return result;
  }
}
