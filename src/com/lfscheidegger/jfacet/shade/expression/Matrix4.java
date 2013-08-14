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

public final class Matrix4 extends AbstractExpression<Matrix4.Primitive> {

  public static final class Primitive implements SupportsBasicArithmetic<Primitive> {

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
      return MatrixUtils.determinant4(mValues);
    }

    public Primitive transpose() {
      return new Primitive(MatrixUtils.transpose4(mValues));
    }

    public Primitive inverse() {
      return new Primitive(MatrixUtils.inverse4(mValues));
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
      return StringUtils.toStringHelper(Type.MAT4_T)
          .addValue(new Vector4.Primitive(mValues[ 0], mValues[ 1], mValues[ 2], mValues[ 3]))
          .addValue(new Vector4.Primitive(mValues[ 4], mValues[ 5], mValues[ 6], mValues[ 7]))
          .addValue(new Vector4.Primitive(mValues[ 8], mValues[ 9], mValues[10], mValues[11]))
          .addValue(new Vector4.Primitive(mValues[12], mValues[13], mValues[14], mValues[15]))
          .toString();
    }
  }

  public Matrix4(Primitive mat) {
    this(new ConstantEvaluator<Primitive>(mat));
  }

  public Matrix4(Vector4 c0, Vector4 c1, Vector4 c2, Vector4 c3) {
    this (ImmutableList.<Expression>of(c0, c1, c2, c3), new ConstructorEvaluator<Primitive>());
  }

  public Matrix4(Evaluator<Primitive> evaluator) {
    this(ImmutableList.<Expression>of(), evaluator);
  }

  public Matrix4(ImmutableList<Expression> parents, Evaluator<Primitive> evaluator) {
    this(GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Matrix4(GlSlType glSlType, Evaluator<Primitive> evaluator) {
    this(glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  public Matrix4(GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Primitive> evaluator) {
    super(Type.MAT4_T, glSlType, parents, evaluator);
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

  public Vector4 get(int idx) {
    Preconditions.checkState(idx < 4);
    return new Vector4(ImmutableList.<Expression>of(this), new ComponentEvaluator<Vector4.Primitive>(idx));
  }

  public Matrix4 add(float right) {
    return add(new Real(right));
  }

  public Matrix4 add(Real right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forAdditionWithFloat()));
  }

  public Matrix4 add(Primitive right) {
    return add(new Matrix4(right));
  }

  public Matrix4 add(Matrix4 right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forAdditionWithSame()));
  }

  public Matrix4 sub(float right) {
    return sub(new Real(right));
  }

  public Matrix4 sub(Real right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forSubtractionWithFloat()));
  }

  public Matrix4 sub(Primitive right) {
    return sub(new Matrix4(right));
  }

  public Matrix4 sub(Matrix4 right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forSubtractionWithSame()));
  }

  public Matrix4 mul(float right) {
    return mul(new Real(right));
  }

  public Matrix4 mul(Real right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forMultiplicationWithFloat()));
  }

  public Matrix4 mul(Primitive right) {
    return mul(new Matrix4(right));
  }

  public Matrix4 mul(Matrix4 right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forMultiplicationWithSame()));
  }
  public Matrix4 div(float right) {
    return div(new Real(right));
  }

  public Matrix4 div(Real right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forDivisionWithFloat()));
  }

  public Matrix4 div(Primitive right) {
    return div(new Matrix4(right));
  }

  public Matrix4 div(Matrix4 right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forDivisionWithSame()));
  }

  public Matrix4 neg(Matrix4 exp) {
    return new Matrix4(ImmutableList.<Expression>of(exp), new NegationEvaluator<Primitive>());
  }
}
