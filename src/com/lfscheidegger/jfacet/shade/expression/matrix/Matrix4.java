package com.lfscheidegger.jfacet.shade.expression.matrix;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.MatrixUtils;
import com.lfscheidegger.jfacet.utils.StringHelper;

import java.util.Arrays;

public final class Matrix4 extends Expression {

  public static final class Primitive {

    private final float[] mValues;

    public Primitive() {
      mValues = new float[16];
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
          mValues[i * 4 + j] = (i == j) ? 1 : 0;
        }
      }
    }

    public Primitive(Vector4.Primitive c0, Vector4.Primitive c1, Vector4.Primitive c2, Vector4.Primitive c3) {
      mValues = new float[]{
          c0.getX(), c0.getY(), c0.getZ(), c0.getW(),
          c1.getX(), c1.getY(), c1.getZ(), c1.getW(),
          c2.getX(), c2.getY(), c2.getZ(), c2.getW(),
          c3.getX(), c3.getY(), c3.getZ(), c3.getW()};
    }

    private Primitive(float[] other) {
      mValues = other;
    }

    public Vector4.Primitive getC0() {
      return get(0);
    }

    public Vector4.Primitive getC1() {
      return get(1);
    }

    public Vector4.Primitive getC2() {
      return get(2);
    }

    public Vector4.Primitive getC3() {
      return get(3);
    }

    public Vector4.Primitive get(int idx) {
      return new Vector4.Primitive(mValues[4 * idx], mValues[4 * idx + 1], mValues[4 * idx + 2], mValues[4 * idx + 3]);
    }

    public Primitive add(Primitive other) {
      return new Primitive(ArrayUtils.add(mValues, other.mValues));
    }

    public Primitive add(float t) {
      return new Primitive(ArrayUtils.add(mValues, t));
    }

    public Primitive sub(Primitive other) {
      return new Primitive(ArrayUtils.sub(mValues, other.mValues));
    }

    public Primitive sub(float t) {
      return new Primitive(ArrayUtils.sub(mValues, t));
    }

    public Primitive mul(Primitive other) {
      return new Primitive(ArrayUtils.mulMatrix(mValues, other.mValues, 4));
    }

    public Primitive mul(float t) {
      return new Primitive(ArrayUtils.mul(mValues, t));
    }

    public Vector4.Primitive mul(Vector4.Primitive vec) {
      return new Vector4.Primitive(
          mValues[0] * vec.getX() + mValues[4] * vec.getY() + mValues[ 8] * vec.getZ() + mValues[12] * vec.getW(),
          mValues[1] * vec.getX() + mValues[5] * vec.getY() + mValues[ 9] * vec.getZ() + mValues[13] * vec.getW(),
          mValues[2] * vec.getX() + mValues[6] * vec.getY() + mValues[10] * vec.getZ() + mValues[14] * vec.getW(),
          mValues[3] * vec.getX() + mValues[7] * vec.getY() + mValues[11] * vec.getZ() + mValues[15] * vec.getW());
    }

    public Primitive div(Primitive other) {
      return new Primitive(ArrayUtils.div(mValues, other.mValues));
    }

    public Primitive div(float t) {
      return new Primitive(ArrayUtils.div(mValues, t));
    }

    public Primitive neg() {
      return new Primitive(ArrayUtils.mul(mValues, -1));
    }

    public float determinant() {
      return MatrixUtils.determinant4(mValues);
    }

    public Primitive transpose() {
      return new Primitive(MatrixUtils.transpose4(mValues));
    }

    public Primitive inverse() {
      return new Primitive(MatrixUtils.inverse4(mValues));
    }

    public Primitive matrixCompMult(Primitive right) {
      return new Primitive(MatrixUtils.matrixCompMult(mValues, right.mValues));
    }

    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Primitive)) {
        return false;
      }

      return Arrays.equals(mValues, ((Primitive) other).mValues);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(mValues);
    }

    @Override
    public String toString() {
      return new StringHelper("mat4")
          .addValue(new Vector4.Primitive(mValues[ 0], mValues[ 1], mValues[ 2], mValues[ 3]))
          .addValue(new Vector4.Primitive(mValues[ 4], mValues[ 5], mValues[ 6], mValues[ 7]))
          .addValue(new Vector4.Primitive(mValues[ 8], mValues[ 9], mValues[10], mValues[11]))
          .addValue(new Vector4.Primitive(mValues[12], mValues[13], mValues[14], mValues[15]))
          .toString();
    }
  }

  public Matrix4() {
    super(new Primitive());
  }

  public Matrix4(Vector4 c0, Vector4 c1, Vector4 c2, Vector4 c3) {
    super(ImmutableList.<Expression>of(c0, c1, c2, c3), NodeType.CONS);
  }

  public Matrix4(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType);
  }

  @Override
  public String getGlSlTypeName() {
    return "mat4";
  }

  public Vector4 getC0() {
    return get(0);
  }

  public Vector4 getC1() {
    return get(1);
  }

  public Vector4 getC2() {
    return get(2);
  }

  public Vector4 getC3() {
    return get(3);
  }

  public Vector4 get(int idx) {
    Preconditions.checkState(idx < 4);
    return new Vector4(ImmutableList.<Expression>of(this), NodeType.ComponentNodeType.forComponent(idx));
  }

  public Matrix4 add(float right) {
    return add(new Real(right));
  }

  public Matrix4 add(Real right) {
    return new Matrix4(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Matrix4 add(Matrix4 right) {
    return new Matrix4(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Matrix4 sub(float right) {
    return sub(new Real(right));
  }

  public Matrix4 sub(Real right) {
    return new Matrix4(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Matrix4 sub(Matrix4 right) {
    return new Matrix4(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Matrix4 mul(float right) {
    return mul(new Real(right));
  }

  public Matrix4 mul(Real right) {
    return new Matrix4(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Matrix4 mul(Matrix4 right) {
    return new Matrix4(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Vector4 mul(Vector4 right) {
    return new Vector4(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Matrix4 div(float right) {
    return div(new Real(right));
  }

  public Matrix4 div(Real right) {
    return new Matrix4(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Matrix4 div(Matrix4 right) {
    return new Matrix4(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Matrix4 neg() {
    return new Matrix4(ImmutableList.<Expression>of(this), NodeType.NEG);
  }

  public Bool isEqual(Matrix4 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.EQ);
  }

  public Bool isNotEqual(Matrix4 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.NEQ);
  }

  public Matrix4 matrixCompMult(Matrix4 right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("matrixCompMult"));
  }
}
