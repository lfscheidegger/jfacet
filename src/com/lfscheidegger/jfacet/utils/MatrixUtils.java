package com.lfscheidegger.jfacet.utils;

import com.google.common.base.Preconditions;

public class MatrixUtils {

  public static float determinant2(float[] values) {
    Preconditions.checkState(values.length == 4);

    return values[0] * values[3] - values[1] * values[2];
  }

  public static float determinant3(float[] values) {
    Preconditions.checkState(values.length == 9);

    float a00 = values[0], a01 = values[1], a02 = values[2];
    float a10 = values[3], a11 = values[4], a12 = values[5];
    float a20 = values[6], a21 = values[7], a22 = values[8];

    return a00*a11*a22 + a01*a12*a20 + a02*a10*a21
        - a02*a11*a20 - a01*a10*a22 - a00*a12*a21;
  }

  public static float determinant4(float[] values) {
    Preconditions.checkState(values.length == 16);

    float a00 = values[0], a01 = values[1], a02 = values[2], a03 = values[3];
    float a10 = values[4], a11 = values[5], a12 = values[6], a13 = values[7];
    float a20 = values[8], a21 = values[9], a22 = values[10], a23 = values[11];
    float a30 = values[12], a31 = values[13], a32 = values[14], a33 = values[15];

    return a30*a21*a12*a03 - a20*a31*a12*a03 - a30*a11*a22*a03 + a10*a31*a22*a03 +
        a20*a11*a32*a03 - a10*a21*a32*a03 - a30*a21*a02*a13 + a20*a31*a02*a13 +
        a30*a01*a22*a13 - a00*a31*a22*a13 - a20*a01*a32*a13 + a00*a21*a32*a13 +
        a30*a11*a02*a23 - a10*a31*a02*a23 - a30*a01*a12*a23 + a00*a31*a12*a23 +
        a10*a01*a32*a23 - a00*a11*a32*a23 - a20*a11*a02*a33 + a10*a21*a02*a33 +
        a20*a01*a12*a33 - a00*a21*a12*a33 - a10*a01*a22*a33 + a00*a11*a22*a33;
  }

  public static float[] transpose2(float[] values) {
    Preconditions.checkState(values.length == 4);

    float[] result = new float[4];
    result[0] = values[0];
    result[1] = values[2];
    result[2] = values[1];
    result[3] = values[3];

    return result;
  }

  public static float[] transpose3(float[] values) {
    Preconditions.checkState(values.length == 9);

    float[] result = new float[9];
    result[0] = values[0];
    result[1] = values[3];
    result[2] = values[6];
    result[3] = values[1];
    result[4] = values[4];
    result[5] = values[7];
    result[6] = values[2];
    result[7] = values[5];
    result[8] =  values[8];

    return result;
  }

  public static float[] transpose4(float[] values) {
    Preconditions.checkState(values.length == 16);

    float[] result = new float[16];
    result[0] = values[0];
    result[1] = values[4];
    result[2] = values[8];
    result[3] = values[12];
    result[4] = values[1];
    result[5] = values[5];
    result[6] = values[9];
    result[7] = values[13];
    result[8] =  values[2];
    result[9] =  values[6];
    result[10] = values[10];
    result[11] = values[14];
    result[12] = values[3];
    result[13] = values[7];
    result[14] = values[11];
    result[15] = values[15];

    return result;
  }

  public static float[] inverse2(float[] values) {
    Preconditions.checkState(values.length == 4);

    float a00 = values[0], a01 = values[1];
    float a10 = values[2], a11 = values[3];

    // Calculate the determinant (inlined to avoid double-caching)
    float det = (a00*a11 - a01*a10);
    Preconditions.checkState(det != 0, "Singular matrix");

    float[] result = new float[4];
    result[0] =  a11/det;
    result[1] = -a01/det;
    result[2] = -a10/det;
    result[3] =  a00/det;

    return result;
  }

  public static float[] inverse3(float[] values) {
    float a00 = values[0], a01 = values[3], a02 = values[6];
    float a10 = values[1], a11 = values[4], a12 = values[7];
    float a20 = values[2], a21 = values[5], a22 = values[8];

    // Calculate the determinant (inlined to avoid double-caching)
    float det = a00*a11*a22 + a01*a12*a20 + a02*a10*a21
        - a02*a11*a20 - a01*a10*a22 - a00*a12*a21;
    Preconditions.checkState(det != 0, "Singular matrix");

    float[] result = new float[9];
    result[0] = ( a11*a22 - a12*a21)/det;
    result[1] = (-a10*a22 + a12*a20)/det;
    result[2] = ( a10*a21 - a11*a20)/det;
    result[3] = (-a01*a22 + a02*a21)/det;
    result[4] = ( a00*a22 - a02*a20)/det;
    result[5] = (-a00*a21 + a01*a20)/det;
    result[6] = ( a01*a12 - a02*a11)/det;
    result[7] = (-a00*a12 + a02*a10)/det;
    result[8] = ( a00*a11 - a01*a10)/det;

    return result;
  }

  public static float[] inverse4(float[] values) {
    float a00 = values[0], a01 = values[1], a02 = values[2], a03 = values[3];
    float a10 = values[4], a11 = values[5], a12 = values[6], a13 = values[7];
    float a20 = values[8], a21 = values[9], a22 = values[10], a23 = values[11];
    float a30 = values[12], a31 = values[13], a32 = values[14], a33 = values[15];

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
    Preconditions.checkState(det != 0, "Singular matrix");

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

    return result;
  }
}
