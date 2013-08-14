package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.*;
import com.lfscheidegger.jfacet.shade.expression.operators.BasicArithmeticOperators;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.SupportsBasicArithmetic;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.MatrixUtils;
import com.lfscheidegger.jfacet.utils.StringUtils;

import java.util.Arrays;

public final class Matrix2 extends AbstractExpression<Matrix2.Primitive> {

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

    public float[] getArray() {
      return mValues;
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

    public Vector2.Primitive mul(Vector2.Primitive vec) {
      return new Vector2.Primitive(
          mValues[0] * vec.getX() + mValues[2] * vec.getY(),
          mValues[1] * vec.getY() + mValues[3] * vec.getY());
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
      return StringUtils.toStringHelper(Type.MAT2_T)
          .addValue(new Vector2.Primitive(mValues[0], mValues[1]))
          .addValue(new Vector2.Primitive(mValues[2], mValues[3]))
          .toString();
    }
  }

  public Matrix2(Primitive mat) {
    this(new ConstantEvaluator<Primitive>(mat));
  }

  public Matrix2(Vector2 c0, Vector2 c1) {
    this (ImmutableList.<Expression>of(c0, c1), new ConstructorEvaluator<Primitive>());
  }

  public Matrix2(Evaluator<Primitive> evaluator) {
    this(ImmutableList.<Expression>of(), evaluator);
  }

  public Matrix2(ImmutableList<Expression> parents, Evaluator<Primitive> evaluator) {
    this(GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Matrix2(GlSlType glSlType, Evaluator<Primitive> evaluator) {
    this(glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  public Matrix2(GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Primitive> evaluator) {
    super(Type.MAT2_T, glSlType,
        parents, evaluator);
  }

  public Vector2 getC0() {
    return get(0);
  }

  public Vector2 getC1() {
    return get(1);
  }

  public Vector2 get(int idx) {
    Preconditions.checkState(idx < 2);
    return new Vector2(ImmutableList.<Expression>of(this), new ComponentEvaluator<Vector2.Primitive>(idx));
  }

  public Matrix2 add(float right) {
    return add(new Real(right));
  }

  public Matrix2 add(Real right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forAdditionWithFloat()));
  }

  public Matrix2 add(Primitive right) {
    return add(new Matrix2(right));
  }

  public Matrix2 add(Matrix2 right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forAdditionWithSame()));
  }

  public Matrix2 sub(float right) {
    return sub(new Real(right));
  }

  public Matrix2 sub(Real right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forSubtractionWithFloat()));
  }

  public Matrix2 sub(Primitive right) {
    return sub(new Matrix2(right));
  }

  public Matrix2 sub(Matrix2 right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forSubtractionWithSame()));
  }

  public Matrix2 mul(float right) {
    return mul(new Real(right));
  }

  public Matrix2 mul(Real right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forMultiplicationWithFloat()));
  }

  public Matrix2 mul(Primitive right) {
    return mul(new Matrix2(right));
  }

  public Matrix2 mul(Matrix2 right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forMultiplicationWithSame()));
  }
  public Matrix2 div(float right) {
    return div(new Real(right));
  }

  public Matrix2 div(Real right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forDivisionWithFloat()));
  }

  public Matrix2 div(Primitive right) {
    return div(new Matrix2(right));
  }

  public Matrix2 div(Matrix2 right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forDivisionWithSame()));
  }

  public Matrix2 neg(Matrix2 exp) {
    return new Matrix2(ImmutableList.<Expression>of(exp), new NegationEvaluator<Primitive>());
  }
}
