package com.lfscheidegger.jfacet.shade.expression.matrix;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.MatrixUtils;
import com.lfscheidegger.jfacet.utils.StringUtils;

import java.util.Arrays;

public final class Matrix3
    extends AbstractExpression<Matrix3.Primitive>
    implements MatrixExpression<Matrix3, Vector3> {

  public static final class Primitive implements SupportsBasicArithmetic<Primitive> {

    private final float[] mValues;

    public Primitive() {
      mValues = new float[9];
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          mValues[i * 3 + j] = (i == j) ? 1 : 0;
        }
      }
    }

    public Primitive(Vector3.Primitive c0, Vector3.Primitive c1, Vector3.Primitive c2) {
      mValues = new float[]{
          c0.getX(), c0.getY(), c0.getZ(),
          c1.getX(), c1.getY(), c1.getZ(),
          c2.getX(), c2.getY(), c2.getZ() };
    }

    private Primitive(float[] other) {
      mValues = other;
    }

    public Vector3.Primitive getC0() {
      return get(0);
    }

    public Vector3.Primitive getC1() {
      return get(1);
    }

    public Vector3.Primitive getC2() {
      return get(2);
    }

    public Vector3.Primitive get(int idx) {
      return new Vector3.Primitive(mValues[3 * idx], mValues[3 * idx + 1], mValues[3 * idx + 2]);
    }

    @Override
    public Primitive add(Primitive other) {
      return new Primitive(ArrayUtils.add(mValues, other.mValues));
    }

    @Override
    public Primitive add(float t) {
      return new Primitive(ArrayUtils.add(mValues, t));
    }

    @Override
    public Primitive sub(Primitive other) {
      return new Primitive(ArrayUtils.sub(mValues, other.mValues));
    }

    @Override
    public Primitive sub(float t) {
      return new Primitive(ArrayUtils.sub(mValues, t));
    }

    @Override
    public Primitive mul(Primitive other) {
      return new Primitive(ArrayUtils.mulMatrix(mValues, other.mValues, 3));
    }

    @Override
    public Primitive mul(float t) {
      return new Primitive(ArrayUtils.mul(mValues, t));
    }

    @Override
    public Primitive div(Primitive other) {
      return new Primitive(ArrayUtils.div(mValues, other.mValues));
    }

    @Override
    public Primitive div(float t) {
      return new Primitive(ArrayUtils.div(mValues, t));
    }

    @Override
    public Primitive neg() {
      return new Primitive(ArrayUtils.mul(mValues, -1));
    }

    public Vector3.Primitive transform(Vector3.Primitive vec) {
      return new Vector3.Primitive(
          mValues[0] * vec.getX() + mValues[3] * vec.getY() + mValues[6] * vec.getZ(),
          mValues[1] * vec.getX() + mValues[4] * vec.getY() + mValues[7] * vec.getZ(),
          mValues[2] * vec.getX() + mValues[5] * vec.getY() + mValues[8] * vec.getZ());
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
      return StringUtils.toStringHelper("mat3")
          .addValue(new Vector3.Primitive(mValues[0], mValues[1], mValues[2]))
          .addValue(new Vector3.Primitive(mValues[3], mValues[4], mValues[5]))
          .addValue(new Vector3.Primitive(mValues[6], mValues[7], mValues[8]))
          .toString();
    }
  }

  private final Optional<Primitive> mPrimitive;

  public Matrix3() {
    super();
    mPrimitive = Optional.of(new Primitive());
  }

  public Matrix3(Vector3 c0, Vector3 c1, Vector3 c2) {
    super(ImmutableList.<Expression>of(c0, c1, c2), NodeType.CONS);
    mPrimitive = Optional.absent();
  }

  public Matrix3(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType);
    mPrimitive = Optional.absent();
  }

  @Override
  public Matrix3 getExpressionForTernaryOperator(Bool condition, Expression<Primitive> elseExpression) {
    return new Matrix3(
        ImmutableList.<Expression>of(condition, this, elseExpression),
        NodeType.TERNARY);
  }

  public Vector3 getC0() {
    return get(0);
  }

  public Vector3 getC1() {
    return get(1);
  }

  public Vector3 getC2() {
    return get(2);
  }

  @Override
  public Vector3 get(int idx) {
    Preconditions.checkState(idx < 3);
    return new Vector3(
        ImmutableList.<Expression>of(this),
        NodeType.ComponentNodeType.forComponent(idx));
  }

  @Override
  public Matrix3 add(float right) {
    return add(new Real(right));
  }

  @Override
  public Matrix3 add(Real right) {
    return new Matrix3(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  @Override
  public Matrix3 add(Matrix3 right) {
    return new Matrix3(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  @Override
  public Matrix3 sub(float right) {
    return sub(new Real(right));
  }

  @Override
  public Matrix3 sub(Real right) {
    return new Matrix3(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  @Override
  public Matrix3 sub(Matrix3 right) {
    return new Matrix3(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  @Override
  public Matrix3 mul(float right) {
    return mul(new Real(right));
  }

  @Override
  public Matrix3 mul(Real right) {
    return new Matrix3(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  @Override
  public Matrix3 mul(Matrix3 right) {
    return new Matrix3(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  @Override
  public Matrix3 div(float right) {
    return div(new Real(right));
  }

  @Override
  public Matrix3 div(Real right) {
    return new Matrix3(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  @Override
  public Matrix3 div(Matrix3 right) {
    return new Matrix3(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  @Override
  public Matrix3 neg() {
    return new Matrix3(ImmutableList.<Expression>of(this), NodeType.NEG);
  }

  @Override
  public Vector3 transform(Vector3 right) {
    return new Vector3(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  @Override
  public Bool isEqual(Matrix3 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.EQ);
  }

  @Override
  public Bool isNotEqual(Matrix3 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.EQ);
  }

  public Matrix3 matrixCompMult(Matrix3 right) {
    return new Matrix3(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("matrixCompMult"));
  }
}