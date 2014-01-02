package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.MatrixUtils;
import com.lfscheidegger.jfacet.utils.StringHelper;

import java.util.Arrays;

public final class Mat3 extends Expression {

  private static final String GLSL_TYPE_NAME = "mat3";

  public static final class Primitive {

    private final float[] mValues;

    public Primitive() {
      mValues = new float[9];
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          mValues[i * 3 + j] = (i == j) ? 1 : 0;
        }
      }
    }

    public Primitive(Vec3.Primitive c0, Vec3.Primitive c1, Vec3.Primitive c2) {
      mValues = new float[]{
          c0.getX(), c0.getY(), c0.getZ(),
          c1.getX(), c1.getY(), c1.getZ(),
          c2.getX(), c2.getY(), c2.getZ() };
    }

    private Primitive(float[] other) {
      mValues = other;
    }

    public Vec3.Primitive getC0() {
      return get(0);
    }

    public Vec3.Primitive getC1() {
      return get(1);
    }

    public Vec3.Primitive getC2() {
      return get(2);
    }

    public Vec3.Primitive get(int idx) {
      return new Vec3.Primitive(mValues[3 * idx], mValues[3 * idx + 1], mValues[3 * idx + 2]);
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
      return new Primitive(ArrayUtils.mulMatrix(mValues, other.mValues, 3));
    }

    public Primitive mul(float t) {
      return new Primitive(ArrayUtils.mul(mValues, t));
    }

    public Vec3.Primitive mul(Vec3.Primitive vec) {
      return new Vec3.Primitive(
          mValues[0] * vec.getX() + mValues[3] * vec.getY() + mValues[6] * vec.getZ(),
          mValues[1] * vec.getX() + mValues[4] * vec.getY() + mValues[7] * vec.getZ(),
          mValues[2] * vec.getX() + mValues[5] * vec.getY() + mValues[8] * vec.getZ());
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
      return MatrixUtils.determinant3(mValues);
    }

    public Primitive transpose() {
      return new Primitive(MatrixUtils.transpose3(mValues));
    }

    public Primitive inverse() {
      return new Primitive(MatrixUtils.inverse3(mValues));
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
          .addValue(new Vec3.Primitive(mValues[0], mValues[1], mValues[2]))
          .addValue(new Vec3.Primitive(mValues[3], mValues[4], mValues[5]))
          .addValue(new Vec3.Primitive(mValues[6], mValues[7], mValues[8]))
          .toString();
    }
  }

  public Mat3() {
    super(new Primitive(), GLSL_TYPE_NAME);
  }

  public Mat3(Vec3 c0, Vec3 c1, Vec3 c2) {
    super(ImmutableList.<Expression>of(c0, c1, c2), NodeType.CONS, GLSL_TYPE_NAME);
  }

  public Mat3(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType, GLSL_TYPE_NAME);
  }

  public Vec3 getC0() {
    return get(0);
  }

  public Vec3 getC1() {
    return get(1);
  }

  public Vec3 getC2() {
    return get(2);
  }

  public Vec3 get(int idx) {
    Preconditions.checkState(idx < 3);
    return new Vec3(
        ImmutableList.<Expression>of(this),
        NodeType.ComponentNodeType.forComponent(idx));
  }

  public Mat3 add(float right) {
    return add(new Real(right));
  }

  public Mat3 add(Real right) {
    return new Mat3(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Mat3 add(Mat3 right) {
    return new Mat3(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Mat3 sub(float right) {
    return sub(new Real(right));
  }

  public Mat3 sub(Real right) {
    return new Mat3(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Mat3 sub(Mat3 right) {
    return new Mat3(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Mat3 mul(float right) {
    return mul(new Real(right));
  }

  public Mat3 mul(Real right) {
    return new Mat3(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Mat3 mul(Mat3 right) {
    return new Mat3(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Vec3 mul(Vec3 right) {
    return new Vec3(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Mat3 div(float right) {
    return div(new Real(right));
  }

  public Mat3 div(Real right) {
    return new Mat3(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Mat3 div(Mat3 right) {
    return new Mat3(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Mat3 neg() {
    return new Mat3(ImmutableList.<Expression>of(this), NodeType.NEG);
  }

  public Bool isEqual(Mat3 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.EQ);
  }

  public Bool isNotEqual(Mat3 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.NEQ);
  }

  public Mat3 matrixCompMult(Mat3 right) {
    return new Mat3(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("matrixCompMult"));
  }
}