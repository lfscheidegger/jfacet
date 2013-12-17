package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import com.lfscheidegger.jfacet.facet.AttributeBuffer;
import com.lfscheidegger.jfacet.shade.GlSlQualifier;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.StringUtils;

import java.util.Arrays;

public final class Vector2 extends AbstractExpression<Vector2.Primitive>
    implements VectorExpression<Real, Vector4> {

  public static final class Primitive implements VectorPrimitive {

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

    @Override
    public float get(int idx) {
      return mValues[idx];
    }

    public Swizzle.Swizzle21XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> x() {
      return new Swizzle.Swizzle21XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("x", this);
    }

    public Swizzle.Swizzle21XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> y() {
      return new Swizzle.Swizzle21XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("y", this);
    }

    public Swizzle.Swizzle21RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> r() {
      return new Swizzle.Swizzle21RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("r", this);
    }

    public Swizzle.Swizzle21RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> g() {
      return new Swizzle.Swizzle21RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("g", this);
    }

    public Swizzle.Swizzle21STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> s() {
      return new Swizzle.Swizzle21STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("s", this);
    }

    public Swizzle.Swizzle21STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> t() {
      return new Swizzle.Swizzle21STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("t", this);
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
      return new Primitive(ArrayUtils.div(mValues, other.mValues));
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

  private final Optional<AttributeBuffer> mAttributeBuffer;

  public Vector2(float x, float y) {
    super(new Primitive(x, y));
    mAttributeBuffer = Optional.absent();
  }

  public Vector2(Real x, Real y) {
    super(ImmutableList.<Expression>of(x, y), NodeType.CONS);
    mAttributeBuffer = Optional.absent();
  }

  public Vector2(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType);
    mAttributeBuffer = Optional.absent();
  }

  public Vector2(AttributeBuffer attributeBuffer) {
    super(GlSlQualifier.ATTRIBUTE_T);
    mAttributeBuffer = Optional.of(attributeBuffer);
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
  public Optional<AttributeBuffer> getAttributeBuffer() {
    return mAttributeBuffer;
  }

  @Override
  public String getGlSlTypeName() {
    return "vec2";
  }

  public Swizzle.Swizzle21XYZW<Real, Vector2, Vector3, Vector4> x() {
    return new Swizzle.Swizzle21XYZW<Real, Vector2, Vector3, Vector4>("x", this);
  }

  public Swizzle.Swizzle21XYZW<Real, Vector2, Vector3, Vector4> y() {
    return new Swizzle.Swizzle21XYZW<Real, Vector2, Vector3, Vector4>("y", this);
  }

  public Swizzle.Swizzle21RGBA<Real, Vector2, Vector3, Vector4> r() {
    return new Swizzle.Swizzle21RGBA<Real, Vector2, Vector3, Vector4>("r", this);
  }

  public Swizzle.Swizzle21RGBA<Real, Vector2, Vector3, Vector4> g() {
    return new Swizzle.Swizzle21RGBA<Real, Vector2, Vector3, Vector4>("g", this);
  }

  public Swizzle.Swizzle21STPQ<Real, Vector2, Vector3, Vector4> s() {
    return new Swizzle.Swizzle21STPQ<Real, Vector2, Vector3, Vector4>("s", this);
  }

  public Swizzle.Swizzle21STPQ<Real, Vector2, Vector3, Vector4> t() {
    return new Swizzle.Swizzle21STPQ<Real, Vector2, Vector3, Vector4>("t", this);
  }

  public Vector2 add(float right) {
    return add(new Real(right));
  }

  public Vector2 add(Real right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Vector2 add(Vector2 right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Vector2 sub(float right) {
    return sub(new Real(right));
  }

  public Vector2 sub(Real right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Vector2 sub(Vector2 right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Vector2 mul(float right) {
    return mul(new Real(right));
  }

  public Vector2 mul(Real right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Vector2 mul(Vector2 right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Vector2 div(float right) {
    return div(new Real(right));
  }

  public Vector2 div(Real right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Vector2 div(Vector2 right) {
    return new Vector2(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Vector2 neg() {
    return new Vector2(ImmutableList.<Expression>of(this), NodeType.NEG);
  }

  public Real dot(Vector2 right) {
    return new Real(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("dot"));
  }

  public Vector2 normalize() {
    return new Vector2(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("normalize"));
  }

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

  @Override
  public Vector4 fill(Vector4 defaultExpression) {
    return new Vector4(getX(), getY(), defaultExpression.getZ(), defaultExpression.getW());
  }
}
