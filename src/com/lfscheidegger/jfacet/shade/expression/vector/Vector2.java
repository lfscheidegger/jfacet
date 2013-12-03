package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.vector.swizzle.Swizzle;
import com.lfscheidegger.jfacet.shade.expression.vector.swizzle.SupportsSwizzling2;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.StringUtils;
import com.lfscheidegger.jfacet.utils.SwizzleUtils;

import java.util.Arrays;

public final class Vector2 extends AbstractExpression<Vector2.Primitive>
    implements VectorExpression<Vector2> {

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
    public Float swizzle(Swizzle.D21 value) {
      return get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(0)));
    }

    @Override
    public Vector2.Primitive swizzle(Swizzle.D22 value) {
      return new Vector2.Primitive(
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(0))),
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(1))));
    }

    @Override
    public Vector3.Primitive swizzle(Swizzle.D23 value) {
      return new Vector3.Primitive(
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(0))),
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(1))),
          get(SwizzleUtils.getIndexForSwizzle(value.toString().charAt(2))));
    }

    @Override
    public Vector4.Primitive swizzle(Swizzle.D24 value) {
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
      return StringUtils.toStringHelper("vec2")
          .addValue(mValues[0])
          .addValue(mValues[1])
          .toString();
    }
  }

  private final Optional<Primitive> mPrimitive;

  public Vector2(float x, float y) {
    super();
    mPrimitive = Optional.of(new Primitive(x, y));
  }

  public Vector2(Real x, Real y) {
    super(ImmutableList.<Expression>of(x, y), NodeType.CONS);
    mPrimitive = Optional.absent();
  }

  public Vector2(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType);
    mPrimitive = Optional.absent();
  }

  @Override
  public Vector2 getExpressionForTernaryOperator(Bool condition, Expression<Primitive> elseExpression) {
    return new Vector2(
        ImmutableList.<Expression>of(condition, this, elseExpression),
        NodeType.TERNARY);
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
    return new Real(
        ImmutableList.<Expression>of(this),
        NodeType.ComponentNodeType.forComponent(idx));
  }

  @Override
  public Vector2 add(float right) {
    return add(new Real(right));
  }

  @Override
  public Vector2 add(Real right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  @Override
  public Vector2 add(Vector2 right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  @Override
  public Vector2 sub(float right) {
    return sub(new Real(right));
  }

  @Override
  public Vector2 sub(Real right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  @Override
  public Vector2 sub(Vector2 right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  @Override
  public Vector2 mul(float right) {
    return mul(new Real(right));
  }

  @Override
  public Vector2 mul(Real right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  @Override
  public Vector2 mul(Vector2 right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  @Override
  public Vector2 div(float right) {
    return div(new Real(right));
  }

  @Override
  public Vector2 div(Real right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  @Override
  public Vector2 div(Vector2 right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  @Override
  public Vector2 neg() {
    return new Vector2(ImmutableList.<Expression>of(this), NodeType.NEG);
  }

  @Override
  public Real dot(Vector2 right) {
    return new Real(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("dot"));
  }

  @Override
  public Vector2 normalize() {
    return new Vector2(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("normalize"));
  }

  @Override
  public Real length() {
    return new Real(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("length"));
  }

  public BVector2 isLessThan(Vector2 right) {
    return new BVector2(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("lessThan"));
  }

  public BVector2 isLessThanOrEqual(Vector2 right) {
    return new BVector2(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("lessThanEqual"));
  }


  public BVector2 isGreaterThan(Vector2 right) {
    return new BVector2(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("greaterThan"));
  }

  public BVector2 isGreaterThanOrEqual(Vector2 right) {
    return new BVector2(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("greaterThanEqual"));
  }

  public BVector2 isEqualComponentwise(Vector2 right) {
    return new BVector2(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("equal"));
  }

  public BVector2 isNotEqualComponentwise(Vector2 right) {
    return new BVector2(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("notEqual"));
  }

  public Bool isEqual(Vector2 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.EQ);
  }

  public Bool isNotEqual(Vector2 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.NEQ);
  }

  // TODO: swizzling

  @Override
  public Vector4 fill(Vector4 defaultExpression) {
    return new Vector4(getX(), getY(), defaultExpression.getZ(), defaultExpression.getW());
  }
}
