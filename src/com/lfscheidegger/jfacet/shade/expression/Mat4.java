// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.utils.MatrixUtils;
import com.lfscheidegger.jfacet.utils.StringHelper;

import java.util.Arrays;

public final class Mat4 extends Expression {

  private static final String GLSL_TYPE_NAME = "mat4";

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

    public Primitive(Vec4.Primitive c0, Vec4.Primitive c1, Vec4.Primitive c2, Vec4.Primitive c3) {
      mValues = new float[]{
          c0.getX(), c0.getY(), c0.getZ(), c0.getW(),
          c1.getX(), c1.getY(), c1.getZ(), c1.getW(),
          c2.getX(), c2.getY(), c2.getZ(), c2.getW(),
          c3.getX(), c3.getY(), c3.getZ(), c3.getW()};
    }

    private Primitive(float[] other) {
      mValues = other;
    }

    public Vec4.Primitive getC0() {
      return get(0);
    }

    public Vec4.Primitive getC1() {
      return get(1);
    }

    public Vec4.Primitive getC2() {
      return get(2);
    }

    public Vec4.Primitive getC3() {
      return get(3);
    }

    public Vec4.Primitive get(int idx) {
      return new Vec4.Primitive(mValues[4 * idx], mValues[4 * idx + 1], mValues[4 * idx + 2], mValues[4 * idx + 3]);
    }

    public Primitive plus(Primitive other) {
      return new Primitive(ArrayUtils.add(mValues, other.mValues));
    }

    public Primitive plus(float t) {
      return new Primitive(ArrayUtils.add(mValues, t));
    }

    public Primitive minus(Primitive other) {
      return new Primitive(ArrayUtils.sub(mValues, other.mValues));
    }

    public Primitive minus(float t) {
      return new Primitive(ArrayUtils.sub(mValues, t));
    }

    public Primitive times(Primitive other) {
      return new Primitive(ArrayUtils.mulMatrix(mValues, other.mValues, 4));
    }

    public Primitive times(float t) {
      return new Primitive(ArrayUtils.mul(mValues, t));
    }

    public Vec4.Primitive times(Vec4.Primitive vec) {
      return new Vec4.Primitive(
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

    public Primitive negative() {
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
      return new StringHelper(GLSL_TYPE_NAME)
          .addValue(new Vec4.Primitive(mValues[ 0], mValues[ 1], mValues[ 2], mValues[ 3]))
          .addValue(new Vec4.Primitive(mValues[ 4], mValues[ 5], mValues[ 6], mValues[ 7]))
          .addValue(new Vec4.Primitive(mValues[ 8], mValues[ 9], mValues[10], mValues[11]))
          .addValue(new Vec4.Primitive(mValues[12], mValues[13], mValues[14], mValues[15]))
          .toString();
    }
  }

  public Mat4() {
    super(new Primitive(), GLSL_TYPE_NAME);
  }

  public Mat4(Vec4 c0, Vec4 c1, Vec4 c2, Vec4 c3) {
    super(ImmutableList.<Expression>of(c0, c1, c2, c3), NodeType.CONS, GLSL_TYPE_NAME);
  }

  public Mat4(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType, GLSL_TYPE_NAME);
  }

  public Vec4 getC0() {
    return get(0);
  }

  public Vec4 getC1() {
    return get(1);
  }

  public Vec4 getC2() {
    return get(2);
  }

  public Vec4 getC3() {
    return get(3);
  }

  public Vec4 get(int idx) {
    Preconditions.checkState(idx < 4);
    return new Vec4(ImmutableList.<Expression>of(this), NodeType.ComponentNodeType.forComponent(idx));
  }

  public Mat4 plus(float right) {
    return plus(new Real(right));
  }

  public Mat4 plus(Real right) {
    return new Mat4(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Mat4 plus(Mat4 right) {
    return new Mat4(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Mat4 minus(float right) {
    return minus(new Real(right));
  }

  public Mat4 minus(Real right) {
    return new Mat4(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Mat4 minus(Mat4 right) {
    return new Mat4(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Mat4 times(float right) {
    return times(new Real(right));
  }

  public Mat4 times(Real right) {
    return new Mat4(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Mat4 times(Mat4 right) {
    return new Mat4(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Vec4 times(Vec4 right) {
    return new Vec4(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Mat4 div(float right) {
    return div(new Real(right));
  }

  public Mat4 div(Real right) {
    return new Mat4(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Mat4 div(Mat4 right) {
    return new Mat4(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Mat4 negative() {
    return new Mat4(ImmutableList.<Expression>of(this), NodeType.NEG);
  }

  public Bool isEqual(Mat4 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.EQ);
  }

  public Bool isNotEqual(Mat4 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.NEQ);
  }

  public Mat4 matrixCompMult(Mat4 right) {
    return new Mat4(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("matrixCompMult"));
  }
}
