package com.lfscheidegger.jfacet.shade.primitives;

import com.google.common.base.Preconditions;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.MatrixLike;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.SupportsBasicArithmetic;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.MatrixUtils;
import com.lfscheidegger.jfacet.utils.StringUtils;

import java.util.Arrays;

public final class Matrix implements SupportsBasicArithmetic<Matrix>, MatrixLike {

  private final float[] mValues;
  private final int mDimension;

  public Matrix(int dimension) {
    Preconditions.checkState(dimension > 1 && dimension <= 4);

    mDimension = dimension;
    mValues = new float[mDimension * mDimension];
    for (int i = 0; i < mDimension; i++) {
      for (int j = 0; j < mDimension; j++) {
        mValues[i * mDimension + j] = (i == j) ? 1 : 0;
      }
    }
  }

  public Matrix(Vector c0, Vector c1) {
    Preconditions.checkState(c0.getDimension() == 2 && c1.getDimension() == 2);

    mDimension = 2;
    mValues = new float[]{c0.getX(), c0.getY(), c1.getX(), c1.getY()};
  }

  public Matrix(Vector c0, Vector c1, Vector c2) {
    Preconditions.checkState(c0.getDimension() == 3 && c1.getDimension() == 3 && c2.getDimension() == 3);

    mDimension = 3;
    mValues = new float[]{
        c0.getX(), c0.getY(), c0.getZ(),
        c1.getX(), c1.getY(), c1.getZ(),
        c2.getX(), c2.getY(), c2.getZ()};
  }

  public Matrix(Vector c0, Vector c1, Vector c2, Vector c3) {
    Preconditions.checkState(
        c0.getDimension() == 4 && c1.getDimension() == 4 && c2.getDimension() == 4 && c3.getDimension() == 4);

    mDimension = 4;
    mValues = new float[]{
        c0.getX(), c0.getY(), c0.getZ(), c0.getW(),
        c1.getX(), c1.getY(), c1.getZ(), c1.getW(),
        c2.getX(), c2.getY(), c2.getZ(), c2.getW(),
        c3.getX(), c3.getY(), c3.getZ(), c3.getW()};
  }

  Matrix(float[] other) {
    Preconditions.checkState(other.length == 4 || other.length == 9 || other.length == 16);

    mDimension = (other.length == 4) ? 2 : (other.length == 9) ? 3 : 4;
    mValues = other;
  }

  public int getDimension() {
    return mDimension;
  }

  public Matrix setC0(Vector c0) {
    return set(c0, 0);
  }

  public Matrix setC1(Vector c1) {
    return set(c1, 1);
  }

  public Matrix setC2(Vector c2) {
    return set(c2, 2);
  }

  public Matrix setC3(Vector c3) {
    return set(c3, 3);
  }

  public Matrix set(Vector c, int idx) {
    Preconditions.checkState(mDimension == c.getDimension());

    mValues[mDimension * idx] = c.getX();
    mValues[mDimension * idx + 1] = c.getY();
    if (mDimension > 2) {
      mValues[mDimension * idx + 2] = c.getZ();
    }
    if (mDimension > 3) {
      mValues[mDimension * idx + 3] = c.getW();
    }

    return this;
  }

  public Vector getC0() {
    return get(0);
  }

  public Vector getC1() {
    return get(1);
  }

  public Vector getC2() {
    return get(2);
  }

  public Vector getC3() {
    return get(3);
  }

  public Vector get(int idx) {
    switch(mDimension) {
      case 2: return new Vector(mValues[2 * idx], mValues[2 * idx + 1]);
      case 3: return new Vector(mValues[3 * idx], mValues[3 * idx + 1], mValues[3 * idx + 2]);
      case 4: return new Vector(mValues[4 * idx], mValues[4 * idx + 1], mValues[4 * idx + 2], mValues[4 * idx + 3]);
      default: throw new RuntimeException("Invalid dimension for matrix: " + mDimension);
    }
  }

  public float[] getArray() {
    return mValues;
  }

  public Matrix add(Matrix other) {
    Preconditions.checkState(mDimension == other.mDimension);

    return new Matrix(ArrayUtils.add(mValues, other.mValues));
  }

  public Matrix add(float t) {
    return new Matrix(ArrayUtils.add(mValues, t));
  }

  public Matrix sub(Matrix other) {
    Preconditions.checkState(mDimension == other.mDimension);

    return new Matrix(ArrayUtils.sub(mValues, other.mValues));
  }

  public Matrix sub(float t) {
    return new Matrix(ArrayUtils.sub(mValues, t));
  }

  public Matrix mul(Matrix other) {
    Preconditions.checkState(mDimension == other.mDimension);

    return new Matrix(ArrayUtils.mulMatrix(mValues, other.mValues, mDimension));
  }

  public Matrix mul(float t) {
    return new Matrix(ArrayUtils.mul(mValues, t));
  }

  public Vector mul(Vector vec) {
    Preconditions.checkState(mDimension == vec.getDimension());

    switch(mDimension) {
      case 2:
        return new Vector(
            mValues[0] * vec.getX() + mValues[2] * vec.getY(),
            mValues[1] * vec.getY() + mValues[3] * vec.getY());
      case 3:
        return new Vector(
            mValues[0] * vec.getX() + mValues[3] * vec.getY() + mValues[6] * vec.getZ(),
            mValues[1] * vec.getX() + mValues[4] * vec.getY() + mValues[7] * vec.getZ(),
            mValues[2] * vec.getX() + mValues[5] * vec.getY() + mValues[8] * vec.getZ());
      case 4:
        return new Vector(
            mValues[0] * vec.getX() + mValues[4] * vec.getY() + mValues[8] * vec.getZ() + mValues[12] * vec.getW(),
            mValues[1] * vec.getX() + mValues[5] * vec.getY() + mValues[9] * vec.getZ() + mValues[13] * vec.getW(),
            mValues[2] * vec.getX() + mValues[6] * vec.getY() + mValues[10] * vec.getZ() + mValues[14] * vec.getW(),
            mValues[3] * vec.getX() + mValues[7] * vec.getY() + mValues[11] * vec.getZ() + mValues[15] * vec.getW());
      default: throw new RuntimeException("Invalid dimension for matrix: " + mDimension);
    }
  }

  public Matrix div(Matrix other) {
    Preconditions.checkState(mDimension == other.mDimension);

    return new Matrix(ArrayUtils.div(mValues, other.mValues));
  }

  public Matrix div(float t) {
    return new Matrix(ArrayUtils.div(mValues, t));
  }

  public Matrix neg() {
    return new Matrix(ArrayUtils.mul(mValues, -1));
  }

  public float determinant() {
    switch(mDimension) {
      case 2: return MatrixUtils.determinant2(mValues);
      case 3: return MatrixUtils.determinant3(mValues);
      case 4: return MatrixUtils.determinant4(mValues);
      default: throw new RuntimeException("Invalid dimension for matrix: " + mDimension);
    }
  }

  public Matrix transpose() {
    switch(mDimension) {
      case 2: return new Matrix(MatrixUtils.transpose2(mValues));
      case 3: return new Matrix(MatrixUtils.transpose3(mValues));
      case 4: return new Matrix(MatrixUtils.transpose4(mValues));
      default: throw new RuntimeException("Invalid dimension for matrix: " + mDimension);
    }
  }

  public Matrix inverse() {
    switch(mDimension) {
      case 2: return new Matrix(MatrixUtils.inverse2(mValues));
      case 3: return new Matrix(MatrixUtils.inverse3(mValues));
      case 4: return new Matrix(MatrixUtils.inverse4(mValues));
      default: throw new RuntimeException("Invalid dimension for matrix: " + mDimension);
    }
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Matrix)) {
      return false;
    }

    return Arrays.equals(mValues, ((Matrix) other).mValues);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(mValues);
  }

  @Override
  public String toString() {
    switch(mDimension) {
      case 2:
        return StringUtils.toStringHelper(Type.MAT2_T)
            .addValue(new Vector(mValues[0], mValues[1]))
            .addValue(new Vector(mValues[2], mValues[3]))
            .toString();
      case 3:
        return StringUtils.toStringHelper(Type.MAT3_T)
            .addValue(new Vector(mValues[0], mValues[1], mValues[2]))
            .addValue(new Vector(mValues[3], mValues[4], mValues[5]))
            .addValue(new Vector(mValues[6], mValues[7], mValues[8]))
            .toString();
      case 4:
        return StringUtils.toStringHelper(Type.MAT4_T)
            .addValue(new Vector(mValues[0], mValues[1], mValues[2], mValues[3]))
            .addValue(new Vector(mValues[4], mValues[5], mValues[6], mValues[7]))
            .addValue(new Vector(mValues[8], mValues[9], mValues[10], mValues[11]))
            .addValue(new Vector(mValues[12], mValues[13], mValues[14], mValues[15]))
            .toString();
      default: throw new RuntimeException("Invalid dimension for vector: " + mDimension);
    }
  }
}
