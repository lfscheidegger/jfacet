// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
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

    public Primitive plus(Primitive other) {
      return new Primitive(ArrayMathUtils.add(mValues, other.mValues));
    }

    public Primitive plus(float t) {
      return new Primitive(ArrayMathUtils.add(mValues, t));
    }

    public Primitive minus(Primitive other) {
      return new Primitive(ArrayMathUtils.sub(mValues, other.mValues));
    }

    public Primitive minus(float t) {
      return new Primitive(ArrayMathUtils.sub(mValues, t));
    }

    public Primitive times(Primitive other) {
      return new Primitive(ArrayMathUtils.mulMatrix(mValues, other.mValues, 3));
    }

    public Primitive times(float t) {
      return new Primitive(ArrayMathUtils.mul(mValues, t));
    }

    public Vec3.Primitive times(Vec3.Primitive vec) {
      return new Vec3.Primitive(
          mValues[0] * vec.getX() + mValues[3] * vec.getY() + mValues[6] * vec.getZ(),
          mValues[1] * vec.getX() + mValues[4] * vec.getY() + mValues[7] * vec.getZ(),
          mValues[2] * vec.getX() + mValues[5] * vec.getY() + mValues[8] * vec.getZ());
    }

    public Primitive div(Primitive other) {
      return new Primitive(ArrayMathUtils.div(mValues, other.mValues));
    }

    public Primitive div(float t) {
      return new Primitive(ArrayMathUtils.div(mValues, t));
    }

    public Primitive negative() {
      return new Primitive(ArrayMathUtils.mul(mValues, -1));
    }

    public float determinant() {
      return ArrayMathUtils.determinant3(mValues);
    }

    public Primitive transpose() {
      return new Primitive(ArrayMathUtils.transpose3(mValues));
    }

    public Primitive inverse() {
      return new Primitive(ArrayMathUtils.inverse3(mValues));
    }

    public Primitive matrixCompMult(Primitive right) {
      return new Primitive(ArrayMathUtils.matrixCompMult(mValues, right.mValues));
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

  public Mat3 plus(float right) {
    return plus(new Real(right));
  }

  public Mat3 plus(Real right) {
    return new Mat3(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Mat3 plus(Mat3 right) {
    return new Mat3(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Mat3 minus(float right) {
    return minus(new Real(right));
  }

  public Mat3 minus(Real right) {
    return new Mat3(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Mat3 minus(Mat3 right) {
    return new Mat3(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Mat3 times(float right) {
    return times(new Real(right));
  }

  public Mat3 times(Real right) {
    return new Mat3(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Mat3 times(Mat3 right) {
    return new Mat3(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Vec3 times(Vec3 right) {
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

  public Mat3 negative() {
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