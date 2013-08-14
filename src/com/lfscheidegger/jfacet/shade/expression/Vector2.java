package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.*;
import com.lfscheidegger.jfacet.shade.expression.operators.BasicArithmeticOperators;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.SupportsBasicArithmetic;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.SwizzleUtils;

import java.util.Arrays;

public final class Vector2 extends AbstractExpression<Vector2.Primitive> {

  public static final class Primitive implements SupportsBasicArithmetic<Primitive> {

    private final float[] mValues;

    public Primitive(float x, float y) {
      mValues = new float[] {x, y};
    }

    private Primitive(float[] other) {
      mValues = other;
    }

    public float getX() {
      return mValues[0];
    }

    public float getY() {
      return mValues[1];
    }

    public float get(int idx) {
      return mValues[idx];
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
      return new Primitive(ArrayUtils.mul(mValues, other.mValues));
    }

    public Primitive mul(float t) {
      return new Primitive(ArrayUtils.mul(mValues, t));
    }

    public Primitive div(Primitive other) {
      return new Primitive(ArrayUtils.mul(mValues, other.mValues));
    }

    public Primitive div(float t) {
      return new Primitive(ArrayUtils.div(mValues, t));
    }

    public Primitive neg() {
      return new Primitive(ArrayUtils.neg(mValues));
    }

    public Primitive normalize() {
      return new Primitive(ArrayUtils.normalize(mValues));
    }

    public float dot(Primitive other) {
      return ArrayUtils.dot(mValues, other.mValues);
    }

    public float swizzle(char c) {
      return get(SwizzleUtils.getIndexForSwizzle(c));
    }

    public Vector2.Primitive swizzle(char x, char y) {
      return new Vector2.Primitive(
          SwizzleUtils.getIndexForSwizzle(x),
          SwizzleUtils.getIndexForSwizzle(y));
    }

    public Vector3.Primitive swizzle(char x, char y, char z) {
      return new Vector3.Primitive(
          SwizzleUtils.getIndexForSwizzle(x),
          SwizzleUtils.getIndexForSwizzle(y),
          SwizzleUtils.getIndexForSwizzle(z));
    }

    public Vector4.Primitive swizzle(char x, char y, char z, char w) {
      return new Vector4.Primitive(
          SwizzleUtils.getIndexForSwizzle(x),
          SwizzleUtils.getIndexForSwizzle(y),
          SwizzleUtils.getIndexForSwizzle(z),
          SwizzleUtils.getIndexForSwizzle(w));
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
      return Objects.toStringHelper(Primitive.class)
          .addValue(mValues[0])
          .addValue(mValues[1])
          .toString();
    }
  }

  public Vector2(Primitive vec) {
    this(new ConstantEvaluator<Primitive>(vec));
  }

  public Vector2(Real x, Real y) {
    this(ImmutableList.<Expression>of(x, y), new ConstructorEvaluator<Primitive>());
  }

  public Vector2(Evaluator<Primitive> evaluator) {
    this(ImmutableList.<Expression>of(), evaluator);
  }

  public Vector2(ImmutableList<Expression> parents, Evaluator<Primitive> evaluator) {
    this(GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Vector2(GlSlType glSlType, Evaluator<Primitive> evaluator) {
    this(glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  public Vector2(GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Primitive> evaluator) {
    super(Type.VEC2_T, glSlType, parents, evaluator);
  }

  public Real getX() {
    return get(0);
  }

  public Real getY() {
    return get(1);
  }

  public Real get(int idx) {
    Preconditions.checkState(idx < 2);
    return new Real(ImmutableList.<Expression>of(this), new ComponentEvaluator<Float>(idx));
  }

  public Vector2 add(float right) {
    return add(new Real(right));
  }

  public Vector2 add(Real right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forAdditionWithFloat()));
  }

  public Vector2 add(Vector2.Primitive right) {
    return add(new Vector2(right));
  }

  public Vector2 add(Vector2 right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forAdditionWithSame()));
  }

  public Vector2 sub(float right) {
    return sub(new Real(right));
  }

  public Vector2 sub(Real right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(
            BasicArithmeticOperators.<Primitive>forSubtractionWithFloat()));
  }

  public Vector2 sub(Vector2.Primitive right) {
    return sub(new Vector2(right));
  }

  public Vector2 sub(Vector2 right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forSubtractionWithSame()));
  }

  public Vector2 mul(float right) {
    return mul(new Real(right));
  }

  public Vector2 mul(Real right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(
            BasicArithmeticOperators.<Primitive>forMultiplicationWithFloat()));
  }

  public Vector2 mul(Vector2.Primitive right) {
    return mul(new Vector2(right));
  }

  public Vector2 mul(Vector2 right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(
            BasicArithmeticOperators.<Primitive>forMultiplicationWithSame()));
  }

  public Vector2 div(float right) {
    return div(new Real(right));
  }

  public Vector2 div(Real right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forDivisionWithFloat()));
  }

  public Vector2 div(Vector2.Primitive right) {
    return div(new Vector2(right));
  }

  public Vector2 div(Vector2 right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forDivisionWithSame()));
  }

  public Vector2 neg() {
    return new Vector2(
        ImmutableList.<Expression>of(this),
        new NegationEvaluator<Primitive>());
  }

  public Real dot(Vector2.Primitive right) {
    return dot(new Vector2(right));
  }

  public Real dot(Vector2 right) {
    return new Real(ImmutableList.<Expression>of(this, right), new FunctionEvaluator<Float>(Type.FLOAT_T, "dot") {
      @Override
      public Float evaluate(Expression expression) {
        Vector2 left = (Vector2)expression.getParents().get(0);
        Vector2 right = (Vector2)expression.getParents().get(1);

        return left.evaluate().dot(right.evaluate());
      }
    });
  }

  public Vector2 normalize() {
    return new Vector2(
        ImmutableList.<Expression>of(this),
        new FunctionEvaluator<Primitive>(Type.VEC2_T, "normalize") {
          @Override
          public Primitive evaluate(Expression expression) {
            Vector2 parent = (Vector2)expression.getParents().get(0);
            return parent.evaluate().normalize();
          }
        });
  }

  public Real swizzle(char x) {
    return new Real(ImmutableList.<Expression>of(this), new SwizzleEvaluator<Float>(x));
  }

  public Vector2 swizzle(char x, char y) {
    return new Vector2(ImmutableList.<Expression>of(this), new SwizzleEvaluator<Vector2.Primitive>(x, y));
  }

  public Vector3 swizzle(char x, char y, char z) {
    return new Vector3(ImmutableList.<Expression>of(this), new SwizzleEvaluator<Vector3.Primitive>(x, y, z));
  }

  public Vector4 swizzle(char x, char y, char z, char w) {
    return new Vector4(ImmutableList.<Expression>of(this), new SwizzleEvaluator<Vector4.Primitive>(x, y, z, w));
  }

  public Vector4 fill(Vector4 defaultExpression) {
    return new Vector4(getX(), getY(), defaultExpression.getZ(), defaultExpression.getW());
  }
}
