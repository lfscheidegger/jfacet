package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.*;
import com.lfscheidegger.jfacet.shade.expression.operators.BasicArithmeticOperators;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.MatrixUtils;
import com.lfscheidegger.jfacet.utils.StringUtils;

import java.util.Arrays;

public final class Matrix4
    extends AbstractExpression<Matrix4.Primitive>
    implements MatrixExpression<Matrix4, Vector4> {

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

    public Vector4.Primitive transform(Vector4.Primitive vec) {
      return new Vector4.Primitive(
          mValues[0] * vec.getX() + mValues[4] * vec.getY() + mValues[ 8] * vec.getZ() + mValues[12] * vec.getW(),
          mValues[1] * vec.getX() + mValues[5] * vec.getY() + mValues[ 9] * vec.getZ() + mValues[13] * vec.getW(),
          mValues[2] * vec.getX() + mValues[6] * vec.getY() + mValues[10] * vec.getZ() + mValues[14] * vec.getW(),
          mValues[3] * vec.getX() + mValues[7] * vec.getY() + mValues[11] * vec.getZ() + mValues[15] * vec.getW());
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

  public Vector4 getC3() {
    return get(3);
  }

  @Override
  public Vector4 get(int idx) {
    Preconditions.checkState(idx < 4);
    return new Vector4(ImmutableList.<Expression>of(this), new ComponentEvaluator<Vector4.Primitive>(idx));
  }

  @Override
  public Matrix4 add(float right) {
    return add(new Real(right));
  }

  @Override
  public Matrix4 add(Real right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forAdditionWithFloat()));
  }

  @Override
  public Matrix4 add(Matrix4 right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forAdditionWithSame()));
  }

  @Override
  public Matrix4 sub(float right) {
    return sub(new Real(right));
  }

  @Override
  public Matrix4 sub(Real right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forSubtractionWithFloat()));
  }

  @Override
  public Matrix4 sub(Matrix4 right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forSubtractionWithSame()));
  }

  @Override
  public Matrix4 mul(float right) {
    return mul(new Real(right));
  }

  @Override
  public Matrix4 mul(Real right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forMultiplicationWithFloat()));
  }

  @Override
  public Matrix4 mul(Matrix4 right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forMultiplicationWithSame()));
  }

  @Override
  public Matrix4 div(float right) {
    return div(new Real(right));
  }

  @Override
  public Matrix4 div(Real right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forDivisionWithFloat()));
  }

  @Override
  public Matrix4 div(Matrix4 right) {
    return new Matrix4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forDivisionWithSame()));
  }

  @Override
  public Matrix4 neg() {
    return new Matrix4(ImmutableList.<Expression>of(this), new NegationEvaluator<Primitive>());
  }

  @Override
  public Vector4 transform(Vector4 right) {
    return new Vector4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Vector4.Primitive, Vector4.Primitive>(BasicArithmeticOperators.forLinearTransform4()));
  }
}
