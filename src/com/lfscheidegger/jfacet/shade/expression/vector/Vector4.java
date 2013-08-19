package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.evaluators.*;
import com.lfscheidegger.jfacet.shade.expression.operators.BasicArithmeticOperators;
import com.lfscheidegger.jfacet.shade.expression.operators.BooleanOperators;
import com.lfscheidegger.jfacet.shade.expression.vector.swizzle.S;
import com.lfscheidegger.jfacet.shade.expression.vector.swizzle.SupportsSwizzling4;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.StringUtils;
import com.lfscheidegger.jfacet.utils.SwizzleUtils;

import java.util.Arrays;

public final class Vector4 extends AbstractExpression<Vector4.Primitive>
    implements VectorExpression<Vector4>, SupportsSwizzling4<Real, Vector2, Vector3, Vector4> {

  public static final class Primitive implements
      SupportsBasicArithmetic<Primitive>,
      SupportsSwizzling4<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> {

    private final float[] mValues;

    public Primitive(float x, float y, float z, float w) {
      mValues = new float[] {x, y, z, w};
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

    public float getW() {
      return mValues[3];
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

    public BVector4.Primitive isLessThan(Primitive right) {
      return new BVector4.Primitive(
          getX() < right.getX(), getY() < right.getY(), getZ() < right.getZ(), getW() < right.getW());
    }

    public BVector4.Primitive isLessThanOrEqual(Primitive right) {
      return new BVector4.Primitive(
          getX() <= right.getX(), getY() <= right.getY(), getZ() <= right.getZ(), getW() <= right.getW());
    }

    public BVector4.Primitive isGreaterThan(Primitive right) {
      return new BVector4.Primitive(
          getX() > right.getX(), getY() > right.getY(), getZ() > right.getZ(), getW() > right.getW());
    }

    public BVector4.Primitive isGreaterThanOrEqual(Primitive right) {
      return new BVector4.Primitive(
          getX() >= right.getX(), getY() >= right.getY(), getZ() >= right.getZ(), getW() >= right.getW());
    }

    public BVector4.Primitive isEqualComponentwise(Primitive right) {
      return new BVector4.Primitive(
          getX() == right.getX(), getY() == right.getY(), getZ() == right.getZ(), getW() == right.getW());
    }

    public BVector4.Primitive isNotEqualComponentwise(Primitive right) {
      return new BVector4.Primitive(
          getX() != right.getX(), getY() != right.getY(), getZ() != right.getZ(), getW() != right.getW());
    }


    @Override
    public Float swizzle(S.D41 value) {
      return get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(0)));
    }

    @Override
    public Vector2.Primitive swizzle(S.D42 value) {
      return new Vector2.Primitive(
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(0))),
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(1))));
    }

    @Override
    public Vector3.Primitive swizzle(S.D43 value) {
      return new Vector3.Primitive(
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(0))),
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(1))),
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(2))));
    }

    @Override
    public Vector4.Primitive swizzle(S.D44 value) {
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
      return StringUtils.toStringHelper(Type.VEC4_T)
          .addValue(mValues[0])
          .addValue(mValues[1])
          .addValue(mValues[2])
          .addValue(mValues[3])
          .toString();
    }
  }

  public Vector4(float x, float y, float z, float w) {
    this(ImmutableList.<Expression>of(), new ConstantEvaluator<Primitive>(new Primitive(x, y, z, w)));
  }

  public Vector4(Real x, Real y, Real z, Real w) {
    this(ImmutableList.<Expression>of(x, y, z, w), new ConstructorEvaluator<Primitive>());
  }

  public Vector4(ImmutableList<Expression> parents, Evaluator<Primitive> evaluator) {
    this(GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Vector4(GlSlType glSlType, Evaluator<Primitive> evaluator) {
    this(glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  private Vector4(GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Primitive> evaluator) {
    super(Type.VEC4_T, glSlType, parents, evaluator);
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

  public Real getW() {
    return get(3);
  }

  @Override
  public Real get(int idx) {
    Preconditions.checkState(idx < 4);
    return new Real(ImmutableList.<Expression>of(this), new ComponentEvaluator<Float>(idx));
  }

  @Override
  public Vector4 add(float right) {
    return add(new Real(right));
  }

  @Override
  public Vector4 add(Real right) {
    return new Vector4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forAdditionWithFloat()));
  }

  @Override
  public Vector4 add(Vector4 right) {
    return new Vector4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forAdditionWithSame()));
  }

  @Override
  public Vector4 sub(float right) {
    return sub(new Real(right));
  }

  @Override
  public Vector4 sub(Real right) {
    return new Vector4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(
            BasicArithmeticOperators.<Primitive>forSubtractionWithFloat()));
  }

  @Override
  public Vector4 sub(Vector4 right) {
    return new Vector4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forSubtractionWithSame()));
  }

  @Override
  public Vector4 mul(float right) {
    return mul(new Real(right));
  }

  @Override
  public Vector4 mul(Real right) {
    return new Vector4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(
            BasicArithmeticOperators.<Primitive>forMultiplicationWithFloat()));
  }

  @Override
  public Vector4 mul(Vector4 right) {
    return new Vector4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(
            BasicArithmeticOperators.<Primitive>forMultiplicationWithSame()));
  }

  @Override
  public Vector4 div(float right) {
    return div(new Real(right));
  }

  @Override
  public Vector4 div(Real right) {
    return new Vector4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forDivisionWithFloat()));
  }

  @Override
  public Vector4 div(Vector4 right) {
    return new Vector4(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forDivisionWithSame()));
  }

  @Override
  public Vector4 neg() {
    return new Vector4(
        ImmutableList.<Expression>of(this),
        new NegationEvaluator<Primitive>());
  }

  @Override
  public Real dot(Vector4 right) {
    return new Real(ImmutableList.<Expression>of(this, right), new FunctionEvaluator<Float>(Type.FLOAT_T, "dot") {
      @Override
      public Float evaluate(Expression expression) {
        Vector4 left = (Vector4)expression.getParents().get(0);
        Vector4 right = (Vector4)expression.getParents().get(1);

        return left.evaluate().dot(right.evaluate());
      }
    });
  }

  @Override
  public Vector4 normalize() {
    return new Vector4(
        ImmutableList.<Expression>of(this),
        new FunctionEvaluator<Primitive>(Type.VEC4_T, "normalize") {
          @Override
          public Primitive evaluate(Expression expression) {
            Vector4 parent = (Vector4)expression.getParents().get(0);
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
            Vector4 parent = (Vector4)expression.getParents().get(0);
            return parent.evaluate().length();
          }
        });
  }

  public BVector4 isLessThan(Vector4 right) {
    return new BVector4(
        ImmutableList.<Expression>of(this, right),
        new FunctionEvaluator<BVector4.Primitive>(Type.BVEC4_T, "lessThan") {
          @Override
          public BVector4.Primitive evaluate(Expression<BVector4.Primitive> expression) {
            Vector4 left = (Vector4)expression.getParents().get(0);
            Vector4 right = (Vector4)expression.getParents().get(1);
            return left.evaluate().isLessThan(right.evaluate());
          }
        });
  }

  public BVector4 isLessThanOrEqual(Vector4 right) {
    return new BVector4(
        ImmutableList.<Expression>of(this, right),
        new FunctionEvaluator<BVector4.Primitive>(Type.BVEC4_T, "lessThanEqual") {
          @Override
          public BVector4.Primitive evaluate(Expression<BVector4.Primitive> expression) {
            Vector4 left = (Vector4)expression.getParents().get(0);
            Vector4 right = (Vector4)expression.getParents().get(1);
            return left.evaluate().isLessThanOrEqual(right.evaluate());
          }
        });
  }


  public BVector4 isGreaterThan(Vector4 right) {
    return new BVector4(
        ImmutableList.<Expression>of(this, right),
        new FunctionEvaluator<BVector4.Primitive>(Type.BVEC4_T, "greaterThan") {
          @Override
          public BVector4.Primitive evaluate(Expression<BVector4.Primitive> expression) {
            Vector4 left = (Vector4)expression.getParents().get(0);
            Vector4 right = (Vector4)expression.getParents().get(1);
            return left.evaluate().isGreaterThan(right.evaluate());
          }
        });
  }

  public BVector4 isGreaterThanOrEqual(Vector4 right) {
    return new BVector4(
        ImmutableList.<Expression>of(this, right),
        new FunctionEvaluator<BVector4.Primitive>(Type.BVEC4_T, "greaterThanEqual") {
          @Override
          public BVector4.Primitive evaluate(Expression<BVector4.Primitive> expression) {
            Vector4 left = (Vector4)expression.getParents().get(0);
            Vector4 right = (Vector4)expression.getParents().get(1);
            return left.evaluate().isGreaterThanOrEqual(right.evaluate());
          }
        });
  }

  public BVector4 isEqualComponentwise(Vector4 right) {
    return new BVector4(
        ImmutableList.<Expression>of(this, right),
        new FunctionEvaluator<BVector4.Primitive>(Type.BVEC4_T, "equal") {
          @Override
          public BVector4.Primitive evaluate(Expression<BVector4.Primitive> expression) {
            Vector4 left = (Vector4)expression.getParents().get(0);
            Vector4 right = (Vector4)expression.getParents().get(1);
            return left.evaluate().isEqualComponentwise(right.evaluate());
          }
        });
  }

  public BVector4 isNotEqualComponentwise(Vector4 right) {
    return new BVector4(
        ImmutableList.<Expression>of(this, right),
        new FunctionEvaluator<BVector4.Primitive>(Type.BVEC4_T, "notEqual") {
          @Override
          public BVector4.Primitive evaluate(Expression<BVector4.Primitive> expression) {
            Vector4 left = (Vector4)expression.getParents().get(0);
            Vector4 right = (Vector4)expression.getParents().get(1);
            return left.evaluate().isNotEqualComponentwise(right.evaluate());
          }
        });
  }

  public Bool isEqual(Vector4 right) {
    return new Bool(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator(BooleanOperators.forEqualsVector()));
  }

  public Bool isNotEqual(Vector4 right) {
    return new Bool(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator(BooleanOperators.forNotEqualsVector()));
  }


  @Override
  public Real swizzle(S.D41 value) {
    return new Real(ImmutableList.<Expression>of(this), new SwizzleEvaluator<Float>(value, Type.FLOAT_T));
  }

  @Override
  public Vector2 swizzle(S.D42 value) {
    return new Vector2(ImmutableList.<Expression>of(this), new SwizzleEvaluator<Vector2.Primitive>(value, Type.VEC2_T));
  }

  @Override
  public Vector3 swizzle(S.D43 value) {
    return new Vector3(ImmutableList.<Expression>of(this), new SwizzleEvaluator<Vector3.Primitive>(value, Type.VEC3_T));
  }

  @Override
  public Vector4 swizzle(S.D44 value) {
    return new Vector4(ImmutableList.<Expression>of(this), new SwizzleEvaluator<Vector4.Primitive>(value, Type.VEC4_T));
  }

  @Override
  public Vector4 fill(Vector4 defaultExpression) {
    return this;
  }
}
