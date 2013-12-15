package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.facet.AttribBuffer;
import com.lfscheidegger.jfacet.shade.GlSlQualifier;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.StringUtils;

import java.util.Arrays;

public final class Vector4 extends AbstractExpression<Vector4.Primitive>
    implements VectorExpression<Real, Vector4> {

  public static final class Primitive implements
      SupportsBasicArithmetic<Primitive>, VectorPrimitive {

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

    @Override
    public float get(int idx) {
      return mValues[idx];
    }

    public Swizzle.Swizzle41XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> x() {
      return new Swizzle.Swizzle41XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("x", this);
    }

    public Swizzle.Swizzle41XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> y() {
      return new Swizzle.Swizzle41XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("y", this);
    }

    public Swizzle.Swizzle41XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> z() {
      return new Swizzle.Swizzle41XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("z", this);
    }

    public Swizzle.Swizzle41XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> w() {
      return new Swizzle.Swizzle41XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("w", this);
    }

    public Swizzle.Swizzle41RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> r() {
      return new Swizzle.Swizzle41RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("r", this);
    }

    public Swizzle.Swizzle41RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> g() {
      return new Swizzle.Swizzle41RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("g", this);
    }

    public Swizzle.Swizzle41RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> b() {
      return new Swizzle.Swizzle41RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("b", this);
    }

    public Swizzle.Swizzle41RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> a() {
      return new Swizzle.Swizzle41RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("a", this);
    }

    public Swizzle.Swizzle41STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> s() {
      return new Swizzle.Swizzle41STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("s", this);
    }

    public Swizzle.Swizzle41STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> t() {
      return new Swizzle.Swizzle41STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("t", this);
    }

    public Swizzle.Swizzle41STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> p() {
      return new Swizzle.Swizzle41STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("p", this);
    }

    public Swizzle.Swizzle41STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> q() {
      return new Swizzle.Swizzle41STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("q", this);
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
      return StringUtils.toStringHelper("vec4")
          .addValue(mValues[0])
          .addValue(mValues[1])
          .addValue(mValues[2])
          .addValue(mValues[3])
          .toString();
    }
  }

  private Optional<Primitive> mPrimitive;

  private Optional<AttribBuffer> mAttributeBuffer;

  public Vector4(float x, float y, float z, float w) {
    super();
    mPrimitive = Optional.of(new Primitive(x, y, z, w));
    mAttributeBuffer = Optional.absent();
  }

  public Vector4(Real x, Real y, Real z, Real w) {
    super(ImmutableList.<Expression>of(x, y, z, w), NodeType.CONS);
    mPrimitive = Optional.absent();
    mAttributeBuffer = Optional.absent();
  }

  public Vector4(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType);
    mPrimitive = Optional.absent();
    mAttributeBuffer = Optional.absent();
  }

  public Vector4(AttribBuffer attributeBuffer) {
    super(GlSlQualifier.ATTRIBUTE_T);
    mPrimitive = Optional.absent();
    mAttributeBuffer = Optional.of(attributeBuffer);
  }

  @Override
  public Vector4 getExpressionForTernaryOperator(Bool condition, Expression<Primitive> elseExpression) {
    return new Vector4(
        ImmutableList.<Expression>of(condition, this, elseExpression),
        NodeType.TERNARY);
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
    return new Real(
        ImmutableList.<Expression>of(this),
        NodeType.ComponentNodeType.forComponent(idx));
  }

  @Override
  public Optional<Primitive> getPrimitive() {
    return mPrimitive;
  }

  public Optional<AttribBuffer> getAttribBuffer() {
    return mAttributeBuffer;
  }

  public Swizzle.Swizzle41XYZW<Real, Vector2, Vector3, Vector4> x() {
    return new Swizzle.Swizzle41XYZW<Real, Vector2, Vector3, Vector4>("x", this);
  }

  public Swizzle.Swizzle41XYZW<Real, Vector2, Vector3, Vector4> y() {
    return new Swizzle.Swizzle41XYZW<Real, Vector2, Vector3, Vector4>("y", this);
  }

  public Swizzle.Swizzle41XYZW<Real, Vector2, Vector3, Vector4> z() {
    return new Swizzle.Swizzle41XYZW<Real, Vector2, Vector3, Vector4>("z", this);
  }

  public Swizzle.Swizzle41XYZW<Real, Vector2, Vector3, Vector4> w() {
    return new Swizzle.Swizzle41XYZW<Real, Vector2, Vector3, Vector4>("w", this);
  }

  public Swizzle.Swizzle41RGBA<Real, Vector2, Vector3, Vector4> r() {
    return new Swizzle.Swizzle41RGBA<Real, Vector2, Vector3, Vector4>("r", this);
  }

  public Swizzle.Swizzle41RGBA<Real, Vector2, Vector3, Vector4> g() {
    return new Swizzle.Swizzle41RGBA<Real, Vector2, Vector3, Vector4>("g", this);
  }

  public Swizzle.Swizzle41RGBA<Real, Vector2, Vector3, Vector4> b() {
    return new Swizzle.Swizzle41RGBA<Real, Vector2, Vector3, Vector4>("b", this);
  }

  public Swizzle.Swizzle41RGBA<Real, Vector2, Vector3, Vector4> a() {
    return new Swizzle.Swizzle41RGBA<Real, Vector2, Vector3, Vector4>("a", this);
  }

  public Swizzle.Swizzle41STPQ<Real, Vector2, Vector3, Vector4> s() {
    return new Swizzle.Swizzle41STPQ<Real, Vector2, Vector3, Vector4>("s", this);
  }

  public Swizzle.Swizzle41STPQ<Real, Vector2, Vector3, Vector4> t() {
    return new Swizzle.Swizzle41STPQ<Real, Vector2, Vector3, Vector4>("t", this);
  }

  public Swizzle.Swizzle41STPQ<Real, Vector2, Vector3, Vector4> p() {
    return new Swizzle.Swizzle41STPQ<Real, Vector2, Vector3, Vector4>("p", this);
  }

  public Swizzle.Swizzle41STPQ<Real, Vector2, Vector3, Vector4> q() {
    return new Swizzle.Swizzle41STPQ<Real, Vector2, Vector3, Vector4>("q", this);
  }

  public Vector4 add(float right) {
    return add(new Real(right));
  }

  public Vector4 add(Real right) {
    return new Vector4(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Vector4 add(Vector4 right) {
    return new Vector4(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Vector4 sub(float right) {
    return sub(new Real(right));
  }

  public Vector4 sub(Real right) {
    return new Vector4(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Vector4 sub(Vector4 right) {
    return new Vector4(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Vector4 mul(float right) {
    return mul(new Real(right));
  }

  public Vector4 mul(Real right) {
    return new Vector4(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Vector4 mul(Vector4 right) {
    return new Vector4(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Vector4 div(float right) {
    return div(new Real(right));
  }

  public Vector4 div(Real right) {
    return new Vector4(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Vector4 div(Vector4 right) {
    return new Vector4(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Vector4 neg() {
    return new Vector4(ImmutableList.<Expression>of(this), NodeType.NEG);
  }

  public Real dot(Vector4 right) {
    return new Real(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("dot"));
  }

  public Vector4 normalize() {
    return new Vector4(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("normalize"));
  }

  public Real length() {
    return new Real(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("length"));
  }

  public BVector4 isLessThan(Vector4 right) {
    return new BVector4(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("lessThan"));
  }

  public BVector4 isLessThanOrEqual(Vector4 right) {
    return new BVector4(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("lessThanEqual"));
  }


  public BVector4 isGreaterThan(Vector4 right) {
    return new BVector4(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("greaterThan"));
  }

  public BVector4 isGreaterThanOrEqual(Vector4 right) {
    return new BVector4(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("greaterThanEqual"));
  }

  public BVector4 isEqualComponentwise(Vector4 right) {
    return new BVector4(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("equal"));
  }

  public BVector4 isNotEqualComponentwise(Vector4 right) {
    return new BVector4(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("notEqual"));
  }

  public Bool isEqual(Vector4 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.EQ);
  }

  public Bool isNotEqual(Vector4 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.NEQ);
  }

  @Override
  public Vector4 fill(Vector4 defaultExpression) {
    return this;
  }
}
