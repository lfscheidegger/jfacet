package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.MatrixUtils;
import com.lfscheidegger.jfacet.utils.StringHelper;

import java.util.Arrays;

public final class Mat2 extends Expression {

  private static final String GLSL_TYPE_NAME = "mat2";

  public static final class Primitive {

    private final float[] mValues;

    public Primitive() {
      mValues = new float[4];
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 2; j++) {
          mValues[i * 2 + j] = (i == j) ? 1 : 0;
        }
      }
    }

    public Primitive(Vec2.Primitive c0, Vec2.Primitive c1) {
      mValues = new float[]{c0.getX(), c0.getY(), c1.getX(), c1.getY()};
    }

    private Primitive(float[] other) {
      mValues = other;
    }

    public Vec2.Primitive getC0() {
      return get(0);
    }

    public Vec2.Primitive getC1() {
      return get(1);
    }

    public Vec2.Primitive get(int idx) {
      return new Vec2.Primitive(mValues[2 * idx], mValues[2 * idx + 1]);
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
      return new Primitive(ArrayUtils.mulMatrix(mValues, other.mValues, 2));
    }

    public Primitive mul(float t) {
      return new Primitive(ArrayUtils.mul(mValues, t));
    }

    public Vec2.Primitive mul(Vec2.Primitive vec) {
      return new Vec2.Primitive(
          mValues[0] * vec.getX() + mValues[2] * vec.getY(),
          mValues[1] * vec.getX() + mValues[3] * vec.getY());
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
      return MatrixUtils.determinant2(mValues);
    }

    public Primitive transpose() {
      return new Primitive(MatrixUtils.transpose2(mValues));
    }

    public Primitive inverse() {
      return new Primitive(MatrixUtils.inverse2(mValues));
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
          .addValue(new Vec2.Primitive(mValues[0], mValues[1]))
          .addValue(new Vec2.Primitive(mValues[2], mValues[3]))
          .toString();
    }
  }

  public Mat2() {
    super(new Primitive(), GLSL_TYPE_NAME);
  }

  public Mat2(Vec2 c0, Vec2 c1) {
    super(ImmutableList.<Expression>of(c0, c1), NodeType.CONS, GLSL_TYPE_NAME);
  }

  public Mat2(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType, GLSL_TYPE_NAME);
  }

  public Vec2 getC0() {
    return get(0);
  }

  public Vec2 getC1() {
    return get(1);
  }

  public Vec2 get(int idx) {
    Preconditions.checkState(idx < 2);
    return new Vec2(
        ImmutableList.<Expression>of(this),
        NodeType.ComponentNodeType.forComponent(idx));
  }

  public Mat2 add(float right) {
    return add(new Real(right));
  }

  public Mat2 add(Real right) {
    return new Mat2(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Mat2 add(Mat2 right) {
    return new Mat2(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Mat2 sub(float right) {
    return sub(new Real(right));
  }

  public Mat2 sub(Real right) {
    return new Mat2(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Mat2 sub(Mat2 right) {
    return new Mat2(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Mat2 mul(float right) {
    return mul(new Real(right));
  }

  public Mat2 mul(Real right) {
    return new Mat2(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Mat2 mul(Mat2 right) {
    return new Mat2(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Vec2 mul(Vec2 right) {
    return new Vec2(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Mat2 div(float right) {
    return div(new Real(right));
  }

  public Mat2 div(Real right) {
    return new Mat2(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Mat2 div(Mat2 right) {
    return new Mat2(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Mat2 neg() {
    return new Mat2(ImmutableList.<Expression>of(this), NodeType.NEG);
  }

  public Bool isEqual(Mat2 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.EQ);
  }

  public Bool isNotEqual(Mat2 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.NEQ);
  }

  public Mat2 matrixCompMult(Mat2 right) {
    return new Mat2(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("matrixCompMult"));
  }
}
