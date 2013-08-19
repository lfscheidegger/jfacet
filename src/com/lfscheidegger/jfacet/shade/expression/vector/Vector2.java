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
import com.lfscheidegger.jfacet.shade.expression.vector.swizzle.SupportsSwizzling2;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.StringUtils;
import com.lfscheidegger.jfacet.utils.SwizzleUtils;

import java.util.Arrays;

public final class Vector2 extends AbstractExpression<Vector2.Primitive>
    implements VectorExpression<Vector2>, SupportsSwizzling2<Real, Vector2, Vector3, Vector4> {

  public static final class Primitive implements
      SupportsBasicArithmetic<Primitive>,
      SupportsSwizzling2<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> {

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

    public BVector2.Primitive isLessThan(Primitive right) {
      return new BVector2.Primitive(getX() < right.getX(), getY() < right.getY());
    }

    public BVector2.Primitive isLessThanOrEqual(Primitive right) {
      return new BVector2.Primitive(getX() <= right.getX(), getY() <= right.getY());
    }

    public BVector2.Primitive isGreaterThan(Primitive right) {
      return new BVector2.Primitive(getX() > right.getX(), getY() > right.getY());
    }

    public BVector2.Primitive isGreaterThanOrEqual(Primitive right) {
      return new BVector2.Primitive(getX() >= right.getX(), getY() >= right.getY());
    }

    public BVector2.Primitive isEqualComponentwise(Primitive right) {
      return new BVector2.Primitive(getX() == right.getX(), getY() == right.getY());
    }

    public BVector2.Primitive isNotEqualComponentwise(Primitive right) {
      return new BVector2.Primitive(getX() != right.getX(), getY() != right.getY());
    }

    @Override
    public Float swizzle(S.D21 value) {
      return get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(0)));
    }

    @Override
    public Vector2.Primitive swizzle(S.D22 value) {
      return new Vector2.Primitive(
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(0))),
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(1))));
    }

    @Override
    public Vector3.Primitive swizzle(S.D23 value) {
      return new Vector3.Primitive(
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(0))),
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(1))),
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(2))));
    }

    @Override
    public Vector4.Primitive swizzle(S.D24 value) {
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
      return StringUtils.toStringHelper(Type.VEC2_T)
          .addValue(mValues[0])
          .addValue(mValues[1])
          .toString();
    }
  }

  public Vector2(float x, float y) {
    this(ImmutableList.<Expression>of(), new ConstantEvaluator<Primitive>(new Primitive(x, y)));
  }

  public Vector2(Real x, Real y) {
    this(ImmutableList.<Expression>of(x, y), new ConstructorEvaluator<Primitive>());
  }

  public Vector2(ImmutableList<Expression> parents, Evaluator<Primitive> evaluator) {
    this(GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Vector2(GlSlType glSlType, Evaluator<Primitive> evaluator) {
    this(glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  private Vector2(GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Primitive> evaluator) {
    super(Type.VEC2_T, glSlType, parents, evaluator);
  }

  public Real getX() {
    return get(0);
  }

  public Real getY() {
    return get(1);
  }

  @Override
  public Real get(int idx) {
    Preconditions.checkState(idx < 2);
    return new Real(ImmutableList.<Expression>of(this), new ComponentEvaluator<Float>(idx));
  }

  @Override
  public Vector2 add(float right) {
    return add(new Real(right));
  }

  @Override
  public Vector2 add(Real right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forAdditionWithFloat()));
  }

  @Override
  public Vector2 add(Vector2 right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forAdditionWithSame()));
  }

  @Override
  public Vector2 sub(float right) {
    return sub(new Real(right));
  }

  @Override
  public Vector2 sub(Real right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(
            BasicArithmeticOperators.<Primitive>forSubtractionWithFloat()));
  }

  @Override
  public Vector2 sub(Vector2 right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forSubtractionWithSame()));
  }

  @Override
  public Vector2 mul(float right) {
    return mul(new Real(right));
  }

  @Override
  public Vector2 mul(Real right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(
            BasicArithmeticOperators.<Primitive>forMultiplicationWithFloat()));
  }

  @Override
  public Vector2 mul(Vector2 right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(
            BasicArithmeticOperators.<Primitive>forMultiplicationWithSame()));
  }

  @Override
  public Vector2 div(float right) {
    return div(new Real(right));
  }

  @Override
  public Vector2 div(Real right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Float, Primitive>(BasicArithmeticOperators.<Primitive>forDivisionWithFloat()));
  }

  @Override
  public Vector2 div(Vector2 right) {
    return new Vector2(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Primitive, Primitive, Primitive>(BasicArithmeticOperators.<Primitive>forDivisionWithSame()));
  }

  @Override
  public Vector2 neg() {
    return new Vector2(
        ImmutableList.<Expression>of(this),
        new NegationEvaluator<Primitive>());
  }

  @Override
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

  @Override
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

  @Override
  public Real length() {
    return new Real(
        ImmutableList.<Expression>of(this),
        new FunctionEvaluator<Float>(Type.FLOAT_T, "length") {
          @Override
          public Float evaluate(Expression<Float> expression) {
            Vector2 parent = (Vector2)expression.getParents().get(0);
            return parent.evaluate().length();
          }
        });
  }

  public BVector2 isLessThan(Vector2 right) {
    return new BVector2(
        ImmutableList.<Expression>of(this, right),
        new FunctionEvaluator<BVector2.Primitive>(Type.BVEC2_T, "lessThan") {
          @Override
          public BVector2.Primitive evaluate(Expression<BVector2.Primitive> expression) {
            Vector2 left = (Vector2)expression.getParents().get(0);
            Vector2 right = (Vector2)expression.getParents().get(1);
            return left.evaluate().isLessThan(right.evaluate());
          }
        });
  }

  public BVector2 isLessThanOrEqual(Vector2 right) {
    return new BVector2(
        ImmutableList.<Expression>of(this, right),
        new FunctionEvaluator<BVector2.Primitive>(Type.BVEC2_T, "lessThanEqual") {
          @Override
          public BVector2.Primitive evaluate(Expression<BVector2.Primitive> expression) {
            Vector2 left = (Vector2)expression.getParents().get(0);
            Vector2 right = (Vector2)expression.getParents().get(1);
            return left.evaluate().isLessThanOrEqual(right.evaluate());
          }
        });
  }


  public BVector2 isGreaterThan(Vector2 right) {
    return new BVector2(
        ImmutableList.<Expression>of(this, right),
        new FunctionEvaluator<BVector2.Primitive>(Type.BVEC2_T, "greaterThan") {
          @Override
          public BVector2.Primitive evaluate(Expression<BVector2.Primitive> expression) {
            Vector2 left = (Vector2)expression.getParents().get(0);
            Vector2 right = (Vector2)expression.getParents().get(1);
            return left.evaluate().isGreaterThan(right.evaluate());
          }
        });
  }

  public BVector2 isGreaterThanOrEqual(Vector2 right) {
    return new BVector2(
        ImmutableList.<Expression>of(this, right),
        new FunctionEvaluator<BVector2.Primitive>(Type.BVEC2_T, "greaterThanEqual") {
          @Override
          public BVector2.Primitive evaluate(Expression<BVector2.Primitive> expression) {
            Vector2 left = (Vector2)expression.getParents().get(0);
            Vector2 right = (Vector2)expression.getParents().get(1);
            return left.evaluate().isGreaterThanOrEqual(right.evaluate());
          }
        });
  }

  public BVector2 isEqualComponentwise(Vector2 right) {
    return new BVector2(
        ImmutableList.<Expression>of(this, right),
        new FunctionEvaluator<BVector2.Primitive>(Type.BVEC2_T, "equal") {
          @Override
          public BVector2.Primitive evaluate(Expression<BVector2.Primitive> expression) {
            Vector2 left = (Vector2)expression.getParents().get(0);
            Vector2 right = (Vector2)expression.getParents().get(1);
            return left.evaluate().isEqualComponentwise(right.evaluate());
          }
        });
  }

  public BVector2 isNotEqualComponentwise(Vector2 right) {
    return new BVector2(
        ImmutableList.<Expression>of(this, right),
        new FunctionEvaluator<BVector2.Primitive>(Type.BVEC2_T, "notEqual") {
          @Override
          public BVector2.Primitive evaluate(Expression<BVector2.Primitive> expression) {
            Vector2 left = (Vector2)expression.getParents().get(0);
            Vector2 right = (Vector2)expression.getParents().get(1);
            return left.evaluate().isNotEqualComponentwise(right.evaluate());
          }
        });
  }

  public Bool isEqual(Vector2 right) {
    return new Bool(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator(BooleanOperators.forEqualsVector()));
  }

  public Bool isNotEqual(Vector2 right) {
    return new Bool(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator(BooleanOperators.forNotEqualsVector()));
  }

  @Override
  public Real swizzle(S.D21 value) {
    return new Real(ImmutableList.<Expression>of(this), new SwizzleEvaluator<Float>(value, Type.FLOAT_T));
  }

  @Override
  public Vector2 swizzle(S.D22 value) {
    return new Vector2(ImmutableList.<Expression>of(this), new SwizzleEvaluator<Vector2.Primitive>(value, Type.VEC2_T));
  }

  @Override
  public Vector3 swizzle(S.D23 value) {
    return new Vector3(ImmutableList.<Expression>of(this), new SwizzleEvaluator<Vector3.Primitive>(value, Type.VEC3_T));
  }

  @Override
  public Vector4 swizzle(S.D24 value) {
    return new Vector4(ImmutableList.<Expression>of(this), new SwizzleEvaluator<Vector4.Primitive>(value, Type.VEC4_T));
  }

  @Override
  public Vector4 fill(Vector4 defaultExpression) {
    return new Vector4(getX(), getY(), defaultExpression.getZ(), defaultExpression.getW());
  }
}
