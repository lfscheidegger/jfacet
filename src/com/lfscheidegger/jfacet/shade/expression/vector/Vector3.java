package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.lfscheidegger.jfacet.facet.AttribBuffer;
import com.lfscheidegger.jfacet.shade.GlSlQualifier;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.StringUtils;
import com.lfscheidegger.jfacet.utils.SwizzleUtils;

import java.util.Arrays;

public final class Vector3 extends AbstractExpression<Vector3.Primitive>
    implements VectorExpression<Real, Vector4> {

  public static final class Primitive implements VectorPrimitive {

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

    @Override
    public float get(int idx) {
      return mValues[idx];
    }

    public Swizzle.Swizzle31XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> x() {
      return new Swizzle.Swizzle31XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("x", this);
    }

    public Swizzle.Swizzle31XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> y() {
      return new Swizzle.Swizzle31XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("y", this);
    }

    public Swizzle.Swizzle31XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> z() {
      return new Swizzle.Swizzle31XYZW<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("z", this);
    }

    public Swizzle.Swizzle31RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> r() {
      return new Swizzle.Swizzle31RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("r", this);
    }

    public Swizzle.Swizzle31RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> g() {
      return new Swizzle.Swizzle31RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("g", this);
    }

    public Swizzle.Swizzle31RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> b() {
      return new Swizzle.Swizzle31RGBA<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("b", this);
    }

    public Swizzle.Swizzle31STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> s() {
      return new Swizzle.Swizzle31STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("s", this);
    }

    public Swizzle.Swizzle31STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> t() {
      return new Swizzle.Swizzle31STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("t", this);
    }

    public Swizzle.Swizzle31STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive> p() {
      return new Swizzle.Swizzle31STPQ<Float, Vector2.Primitive, Vector3.Primitive, Vector4.Primitive>("p", this);
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

    public Primitive cross(Primitive other) {
      return new Primitive(
          getY() * other.getZ() - getZ() * other.getY(),
          getZ() * other.getX() - getX() * other.getZ(),
          getX() * other.getY() - getY() * other.getX());
    }

    public BVector3.Primitive isLessThan(Primitive right) {
      return new BVector3.Primitive(getX() < right.getX(), getY() < right.getY(), getZ() < right.getZ());
    }

    public BVector3.Primitive isLessThanOrEqual(Primitive right) {
      return new BVector3.Primitive(getX() <= right.getX(), getY() <= right.getY(), getZ() <= right.getZ());
    }

    public BVector3.Primitive isGreaterThan(Primitive right) {
      return new BVector3.Primitive(getX() > right.getX(), getY() > right.getY(), getZ() > right.getZ());
    }

    public BVector3.Primitive isGreaterThanOrEqual(Primitive right) {
      return new BVector3.Primitive(getX() >= right.getX(), getY() >= right.getY(), getZ() >= right.getZ());
    }

    public BVector3.Primitive isEqualComponentwise(Primitive right) {
      return new BVector3.Primitive(getX() == right.getX(), getY() == right.getY(), getZ() == right.getZ());
    }

    public BVector3.Primitive isNotEqualComponentwise(Primitive right) {
      return new BVector3.Primitive(getX() != right.getX(), getY() != right.getY(), getZ() != right.getZ());
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
      return StringUtils.toStringHelper("vec3")
          .addValue(mValues[0])
          .addValue(mValues[1])
          .addValue(mValues[2])
          .toString();
    }
  }

  private final Optional<Primitive> mPrimitive;

  private final Optional<AttribBuffer> mAttributeBuffer;

  public Vector3(float x, float y, float z) {
    super();
    mPrimitive = Optional.of(new Primitive(x, y, z));
    mAttributeBuffer = Optional.absent();
  }

  public Vector3(Real x, Real y, Real z) {
    super(ImmutableList.<Expression>of(x, y, z), NodeType.CONS);
    mPrimitive = Optional.absent();
    mAttributeBuffer = Optional.absent();
  }

  public Vector3(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType);
    mPrimitive = Optional.absent();
    mAttributeBuffer = Optional.absent();
  }

  public Vector3(AttribBuffer attributeBuffer) {
    super(GlSlQualifier.ATTRIBUTE_T);
    mPrimitive = Optional.absent();
    mAttributeBuffer = Optional.of(attributeBuffer);
  }

  @Override
  public Vector3 getExpressionForTernaryOperator(Bool condition, Expression<Primitive> elseExpression) {
    return new Vector3(
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

  @Override
  public Real get(int idx) {
    Preconditions.checkState(idx < 3);
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

  public Swizzle.Swizzle31XYZW<Real, Vector2, Vector3, Vector4> x() {
    return new Swizzle.Swizzle31XYZW<Real, Vector2, Vector3, Vector4>("x", this);
  }

  public Swizzle.Swizzle31XYZW<Real, Vector2, Vector3, Vector4> y() {
    return new Swizzle.Swizzle31XYZW<Real, Vector2, Vector3, Vector4>("y", this);
  }

  public Swizzle.Swizzle31XYZW<Real, Vector2, Vector3, Vector4> z() {
    return new Swizzle.Swizzle31XYZW<Real, Vector2, Vector3, Vector4>("z", this);
  }

  public Swizzle.Swizzle31RGBA<Real, Vector2, Vector3, Vector4> r() {
    return new Swizzle.Swizzle31RGBA<Real, Vector2, Vector3, Vector4>("r", this);
  }

  public Swizzle.Swizzle31RGBA<Real, Vector2, Vector3, Vector4> g() {
    return new Swizzle.Swizzle31RGBA<Real, Vector2, Vector3, Vector4>("g", this);
  }

  public Swizzle.Swizzle31RGBA<Real, Vector2, Vector3, Vector4> b() {
    return new Swizzle.Swizzle31RGBA<Real, Vector2, Vector3, Vector4>("b", this);
  }

  public Swizzle.Swizzle31STPQ<Real, Vector2, Vector3, Vector4> s() {
    return new Swizzle.Swizzle31STPQ<Real, Vector2, Vector3, Vector4>("s", this);
  }

  public Swizzle.Swizzle31STPQ<Real, Vector2, Vector3, Vector4> t() {
    return new Swizzle.Swizzle31STPQ<Real, Vector2, Vector3, Vector4>("t", this);
  }

  public Swizzle.Swizzle31STPQ<Real, Vector2, Vector3, Vector4> p() {
    return new Swizzle.Swizzle31STPQ<Real, Vector2, Vector3, Vector4>("p", this);
  }

  public Vector3 add(float right) {
    return add(new Real(right));
  }

  public Vector3 add(Real right) {
    return new Vector3(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Vector3 add(Vector3 right) {
    return new Vector3(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Vector3 sub(float right) {
    return sub(new Real(right));
  }

  public Vector3 sub(Real right) {
    return new Vector3(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Vector3 sub(Vector3 right) {
    return new Vector3(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Vector3 mul(float right) {
    return mul(new Real(right));
  }

  public Vector3 mul(Real right) {
    return new Vector3(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Vector3 mul(Vector3 right) {
    return new Vector3(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Vector3 div(float right) {
    return div(new Real(right));
  }

  public Vector3 div(Real right) {
    return new Vector3(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Vector3 div(Vector3 right) {
    return new Vector3(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Vector3 neg() {
    return new Vector3(ImmutableList.<Expression>of(this), NodeType.NEG);
  }

  public Real dot(Vector3 right) {
    return new Real(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("dot"));
  }

  public Vector3 cross(Vector3 right) {
    return new Vector3(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("cross"));
  }

  public Vector3 normalize() {
    return new Vector3(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("normalize"));
  }

  public Real length() {
    return new Real(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("length"));
  }

  public BVector3 isLessThan(Vector3 right) {
    return new BVector3(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("lessThan"));
  }

  public BVector3 isLessThanOrEqual(Vector3 right) {
    return new BVector3(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("lessThanEqual"));
  }


  public BVector3 isGreaterThan(Vector3 right) {
    return new BVector3(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("greaterThan"));
  }

  public BVector3 isGreaterThanOrEqual(Vector3 right) {
    return new BVector3(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("greaterThanEqual"));
  }

  public BVector3 isEqualComponentwise(Vector3 right) {
    return new BVector3(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("equal"));
  }

  public BVector3 isNotEqualComponentwise(Vector3 right) {
    return new BVector3(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("notEqual"));
  }

  public Bool isEqual(Vector3 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.EQ);
  }

  public Bool isNotEqual(Vector3 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.NEQ);
  }

  @Override
  public Vector4 fill(Vector4 defaultExpression) {
    return new Vector4(getX(), getY(), getZ(), defaultExpression.getW());
  }
}

