package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.evaluators.*;
import com.lfscheidegger.jfacet.shade.expression.operators.BasicArithmeticOperators;
import com.lfscheidegger.jfacet.shade.expression.vector.swizzle.S;
import com.lfscheidegger.jfacet.shade.expression.vector.swizzle.SupportsSwizzling3;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.StringUtils;
import com.lfscheidegger.jfacet.utils.SwizzleUtils;

import java.util.Arrays;

public final class Vector3 extends AbstractExpression<Vector3.Primitive>
    implements VectorExpression<Vector3>, SupportsSwizzling3<Real, Vector2, Vector3, Vector4> {

  public static final class Primitive implements
      SupportsBasicArithmetic<Primitive>,
      SupportsSwizzling3<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> {

    private final float[] mValues;

    public Primitive(float x, float y, float z) {
      mValues = new float[] {x, y, z};
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

    public float getZ() {
      return mValues[2];
    }

    public float get(int idx) {
      return mValues[idx];
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
      return new Primitive(ArrayUtils.mul(mValues, other.mValues));
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
      return new Primitive(ArrayUtils.neg(mValues));
    }

    public Primitive normalize() {
      return new Primitive(ArrayUtils.normalize(mValues));
    }

    public float length() {
      return ArrayUtils.length(mValues);
    }

    public float dot(Primitive other) {
      return ArrayUtils.dot(mValues, other.mValues);
    }

    public Primitive cross(Primitive other) {
      return new Primitive(
          getY() * other.getZ() - getZ() * other.getY(),
          getZ() * other.getX() - getX() * other.getZ(),
          getX() * other.getY() - getY() * other.getX());
    }

    @Override
    public Float swizzle(S.D31 value) {
      return get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(0)));
    }

    @Override
    public Vector2.Primitive swizzle(S.D32 value) {
      return new Vector2.Primitive(
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(0))),
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(1))));
    }

    @Override
    public Vector3.Primitive swizzle(S.D33 value) {
      return new Vector3.Primitive(
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(0))),
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(1))),
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(2))));
    }

    @Override
    public Vector4.Primitive swizzle(S.D34 value) {
      return new Vector4.Primitive(
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(0))),
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(1))),
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(2))),
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(3))));
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
      return StringUtils.toStringHelper(Type.VEC3_T)
          .addValue(mValues[0])
          .addValue(mValues[1])
          .addValue(mValues[2])
          .toString();
    }
  }

  public Vector3(float x, float y, float z) {
    this(ImmutableList.<Expression>of(), new ConstantEvaluator<Primitive>(new Primitive(x, y, z)));
  }

  public Vector3(Real x, Real y, Real z) {
    this(ImmutableList.<Expression>of(x, y, z), new ConstructorEvaluator<Primitive>());
  }

  public Vector3(ImmutableList<Expression> parents, Evaluator<Primitive> evaluator) {
    this(GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Vector3(GlSlType glSlType, Evaluator<Primitive> evaluator) {
    this(glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  private Vector3(GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Primitive> evaluator) {
    super(Type.VEC3_T, glSlType, parents, evaluator);
  }

  public Real getX() {
    return get(0);
  }

  public Real getY() {
    return get(1);
  }

  public Real getZ() {
    return get(2);
  }

  @Override
  public Real get(int idx) {
    Preconditions.checkState(idx < 3);
    return new Real(ImmutableList.<Expression>of(this), new ComponentEvaluator<Float>(idx));
  }

  @Override
  public Vector3 add(float right) {
    return add(new Real(right));
  }

  @Override
  public Vector3 add(Real right) {
    return new Vector3(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forAdditionWithFloat()));
  }

  @Override
  public Vector3 add(Vector3 right) {
    return new Vector3(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forAdditionWithSame()));
  }

  @Override
  public Vector3 sub(float right) {
    return sub(new Real(right));
  }

  @Override
  public Vector3 sub(Real right) {
    return new Vector3(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(
            BasicArithmeticOperators.<Primitive>forSubtractionWithFloat()));
  }

  @Override
  public Vector3 sub(Vector3 right) {
    return new Vector3(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forSubtractionWithSame()));
  }

  @Override
  public Vector3 mul(float right) {
    return mul(new Real(right));
  }

  @Override
  public Vector3 mul(Real right) {
    return new Vector3(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(
            BasicArithmeticOperators.<Primitive>forMultiplicationWithFloat()));
  }

  @Override
  public Vector3 mul(Vector3 right) {
    return new Vector3(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(
            BasicArithmeticOperators.<Primitive>forMultiplicationWithSame()));
  }

  @Override
  public Vector3 div(float right) {
    return div(new Real(right));
  }

  @Override
  public Vector3 div(Real right) {
    return new Vector3(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forDivisionWithFloat()));
  }

  @Override
  public Vector3 div(Vector3 right) {
    return new Vector3(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forDivisionWithSame()));
  }

  @Override
  public Vector3 neg() {
    return new Vector3(
        ImmutableList.<Expression>of(this),
        new NegationEvaluator<Primitive>());
  }

  @Override
  public Real dot(Vector3 right) {
    return new Real(ImmutableList.<Expression>of(this, right), new FunctionEvaluator<Float>(Type.FLOAT_T, "dot") {
      @Override
      public Float evaluate(Expression expression) {
        Vector3 left = (Vector3)expression.getParents().get(0);
        Vector3 right = (Vector3)expression.getParents().get(1);

        return left.evaluate().dot(right.evaluate());
      }
    });
  }

  @Override
  public Vector3 normalize() {
    return new Vector3(
        ImmutableList.<Expression>of(this),
        new FunctionEvaluator<Primitive>(Type.VEC3_T, "normalize") {
          @Override
          public Primitive evaluate(Expression expression) {
            Vector3 parent = (Vector3)expression.getParents().get(0);
            return parent.evaluate().normalize();
          }
        });
  }

  @Override
  public Real length() {
    return new Real(
        ImmutableList.<Expression>of(this),
        new FunctionEvaluator<Float>(Type.FLOAT_T, "length") {
          @Override
          public Float evaluate(Expression<Float> expression) {
            Vector3 parent = (Vector3)expression.getParents().get(0);
            return parent.evaluate().length();
          }
        });
  }

  public Vector3 cross(Vector3 right) {
    return new Vector3(
        ImmutableList.<Expression>of(this, right),
        new FunctionEvaluator<Primitive>(Type.VEC3_T, "cross") {
          @Override
          public Primitive evaluate(Expression expression) {
            Vector3 left = (Vector3)expression.getParents().get(0);
            Vector3 right = (Vector3)expression.getParents().get(1);
            return left.evaluate().cross(right.evaluate());
          }
        });
  }

  @Override
  public Real swizzle(S.D31 value) {
    return new Real(ImmutableList.<Expression>of(this), new SwizzleEvaluator<Float>(value, Type.FLOAT_T));
  }

  @Override
  public Vector2 swizzle(S.D32 value) {
    return new Vector2(ImmutableList.<Expression>of(this), new SwizzleEvaluator<Vector2.Primitive>(value, Type.VEC2_T));
  }

  @Override
  public Vector3 swizzle(S.D33 value) {
    return new Vector3(ImmutableList.<Expression>of(this), new SwizzleEvaluator<Vector3.Primitive>(value, Type.VEC3_T));
  }

  @Override
  public Vector4 swizzle(S.D34 value) {
    return new Vector4(ImmutableList.<Expression>of(this), new SwizzleEvaluator<Vector4.Primitive>(value, Type.VEC4_T));
  }

  @Override
  public Vector4 fill(Vector4 defaultExpression) {
    return new Vector4(getX(), getY(), getZ(), defaultExpression.getW());
  }
}

