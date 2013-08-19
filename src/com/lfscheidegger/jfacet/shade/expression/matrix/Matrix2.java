package com.lfscheidegger.jfacet.shade.expression.matrix;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.evaluators.*;
import com.lfscheidegger.jfacet.shade.expression.operators.BasicArithmeticOperators;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.MatrixUtils;
import com.lfscheidegger.jfacet.utils.StringUtils;

import java.util.Arrays;

public final class Matrix2
    extends AbstractExpression<Matrix2.Primitive>
    implements MatrixExpression<Matrix2, Vector2> {

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
      return StringUtils.toStringHelper(Type.MAT2_T)
          .addValue(new Vector2.Primitive(mValues[0], mValues[1]))
          .addValue(new Vector2.Primitive(mValues[2], mValues[3]))
          .toString();
    }
  }

  public Matrix2() {
    this(ImmutableList.<Expression>of(Shade.vec(1, 0), Shade.vec(0, 1)),
        new ConstructorEvaluator<Primitive>());
  }

  public Matrix2(Vector2 c0, Vector2 c1) {
    this(ImmutableList.<Expression>of(c0, c1), new ConstructorEvaluator<Primitive>());
  }

  private Matrix2(ImmutableList<Expression> parents, Evaluator<Primitive> evaluator) {
    this(GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Matrix2(GlSlType glSlType, Evaluator<Primitive> evaluator) {
    this(glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  private Matrix2(GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Primitive> evaluator) {
    super(Type.MAT2_T, glSlType,
        parents, evaluator);
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
    return new Vector2(ImmutableList.<Expression>of(this), new ComponentEvaluator<Vector2.Primitive>(idx));
  }

  @Override
  public Matrix2 add(float right) {
    return add(new Real(right));
  }

  @Override
  public Matrix2 add(Real right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forAdditionWithFloat()));
  }

  @Override
  public Matrix2 add(Matrix2 right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forAdditionWithSame()));
  }

  @Override
  public Matrix2 sub(float right) {
    return sub(new Real(right));
  }

  @Override
  public Matrix2 sub(Real right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forSubtractionWithFloat()));
  }

  @Override
  public Matrix2 sub(Matrix2 right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forSubtractionWithSame()));
  }

  @Override
  public Matrix2 mul(float right) {
    return mul(new Real(right));
  }

  @Override
  public Matrix2 mul(Real right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forMultiplicationWithFloat()));
  }

  @Override
  public Matrix2 mul(Matrix2 right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forMultiplicationWithSame()));
  }

  @Override
  public Matrix2 div(float right) {
    return div(new Real(right));
  }

  @Override
  public Matrix2 div(Real right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forDivisionWithFloat()));
  }

  @Override
  public Matrix2 div(Matrix2 right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forDivisionWithSame()));
  }

  @Override
  public Matrix2 neg() {
    return new Matrix2(ImmutableList.<Expression>of(this), new NegationEvaluator<Primitive>());
  }

  @Override
  public Vector2 transform(Vector2 right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Vector2.Primitive, Vector2.Primitive>(BasicArithmeticOperators.forLinearTransform2()));
  }

  public Matrix2 matrixCompMult(Matrix2 right) {
    return new Matrix2(
        ImmutableList.<Expression>of(this, right),
        new FunctionEvaluator<Primitive>(Type.MAT2_T, "matrixCompMult") {
          @Override
          public Primitive evaluate(Expression<Primitive> expression) {
            Matrix2 left = (Matrix2)expression.getParents().get(0);
            Matrix2 right = (Matrix2)expression.getParents().get(1);
            return left.evaluate().matrixCompMult(right.evaluate());
          }
        });
  }
}
