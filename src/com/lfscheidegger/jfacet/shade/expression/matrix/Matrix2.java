package com.lfscheidegger.jfacet.shade.expression.matrix;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.MatrixUtils;
import com.lfscheidegger.jfacet.utils.StringUtils;

import java.util.Arrays;

public final class Matrix2
    extends AbstractExpression<Matrix2.Primitive>
    implements MatrixExpression<Matrix2, Matrix2.Primitive, Vector2> {

  public static final class Primitive implements SupportsBasicArithmetic<Primitive> {

    private final float[] mValues;

    public Primitive() {
      mValues = new float[4];
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 2; j++) {
          mValues[i * 2 + j] = (i == j) ? 1 : 0;
        }
      }
    }

    public Primitive(Vector2.Primitive c0, Vector2.Primitive c1) {
      mValues = new float[]{c0.getX(), c0.getY(), c1.getX(), c1.getY()};
    }

    private Primitive(float[] other) {
      mValues = other;
    }

    public Vector2.Primitive getC0() {
      return get(0);
    }

    public Vector2.Primitive getC1() {
      return get(1);
    }

    public Vector2.Primitive get(int idx) {
      return new Vector2.Primitive(mValues[2 * idx], mValues[2 * idx + 1]);
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
      return new Primitive(ArrayUtils.mulMatrix(mValues, other.mValues, 2));
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

    public Vector2.Primitive transform(Vector2.Primitive vec) {
      return new Vector2.Primitive(
          mValues[0] * vec.getX() + mValues[2] * vec.getY(),
          mValues[1] * vec.getX() + mValues[3] * vec.getY());
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
      return StringUtils.toStringHelper("mat2")
          .addValue(new Vector2.Primitive(mValues[0], mValues[1]))
          .addValue(new Vector2.Primitive(mValues[2], mValues[3]))
          .toString();
    }
  }

  private final Optional<Primitive> mPrimitive;

  public Matrix2() {
    super();
    mPrimitive = Optional.of(new Primitive());
  }

  public Matrix2(Vector2 c0, Vector2 c1) {
    super(ImmutableList.<Expression>of(c0, c1), NodeType.CONS);
    mPrimitive = Optional.absent();
  }

  public Matrix2(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType);
    mPrimitive = Optional.absent();
  }

  @Override
  public Matrix2 getExpressionForTernaryOperator(Bool condition, Expression<Primitive> elseExpression) {
    return new Matrix2(
        ImmutableList.<Expression>of(condition, this, elseExpression),
        NodeType.TERNARY);
  }

  public Vector2 getC0() {
    return get(0);
  }

  public Vector2 getC1() {
    return get(1);
  }

  @Override
  public Vector2 get(int idx) {
    Preconditions.checkState(idx < 2);
    return new Vector2(
        ImmutableList.<Expression>of(this),
        NodeType.ComponentNodeType.forComponent(idx));
  }

  @Override
  public Matrix2 add(float right) {
    return add(new Real(right));
  }

  @Override
  public Matrix2 add(Real right) {
    return new Matrix2(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  @Override
  public Matrix2 add(Matrix2 right) {
    return new Matrix2(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  @Override
  public Matrix2 sub(float right) {
    return sub(new Real(right));
  }

  @Override
  public Matrix2 sub(Real right) {
    return new Matrix2(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  @Override
  public Matrix2 sub(Matrix2 right) {
    return new Matrix2(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  @Override
  public Matrix2 mul(float right) {
    return mul(new Real(right));
  }

  @Override
  public Matrix2 mul(Real right) {
    return new Matrix2(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  @Override
  public Matrix2 mul(Matrix2 right) {
    return new Matrix2(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  @Override
  public Matrix2 div(float right) {
    return div(new Real(right));
  }

  @Override
  public Matrix2 div(Real right) {
    return new Matrix2(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  @Override
  public Matrix2 div(Matrix2 right) {
    return new Matrix2(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  @Override
  public Matrix2 neg() {
    return new Matrix2(ImmutableList.<Expression>of(this), NodeType.NEG);
  }

  @Override
  public Vector2 transform(Vector2 right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  @Override
  public Bool isEqual(Matrix2 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.EQ);
  }

  @Override
  public Bool isNotEqual(Matrix2 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.NEQ);
  }

  public Matrix2 matrixCompMult(Matrix2 right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("matrixCompMult"));
  }
}
