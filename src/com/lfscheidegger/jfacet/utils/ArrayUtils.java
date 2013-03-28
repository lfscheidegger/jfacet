package com.lfscheidegger.jfacet.utils;

import com.google.common.base.Preconditions;

public class ArrayUtils {

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
