package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.facet.AttributeBuffer;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.StringHelper;

import java.util.Arrays;

public final class Vector3 extends AbstractExpression implements VectorExpression<Real, Vector4> {

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

    public Primitive setX(float value) {
      set(value, 0);
      return this;
    }

    public Primitive setY(float value) {
      set(value, 1);
      return this;
    }

    public Primitive setZ(float value) {
      set(value, 2);
      return this;
    }

    public Primitive set(float value, int idx) {
      mValues[idx] = value;
      return this;
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
      return new StringHelper("vec3")
          .addValue(mValues[0])
          .addValue(mValues[1])
          .addValue(mValues[2])
          .toString();
    }
  }

  public Vector3(float x, float y, float z) {
    this(new Primitive(x, y, z));
  }

  public Vector3(Primitive primitive) {
    super(primitive);
  }

  public Vector3(Real x, Real y, Real z) {
    super(ImmutableList.<Expression>of(x, y, z), NodeType.CONS);
  }

  public Vector3(NodeType nodeType) {
    super(nodeType);
  }

  public Vector3(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType);
  }

  public Vector3(AttributeBuffer attributeBuffer) {
    super(NodeType.AttributeNodeType.forAttribute(attributeBuffer));
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
  public String getGlSlTypeName() {
    return "vec3";
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

  public Vector3 radians() { return function("radians"); }

  public Vector3 degrees() { return function("degrees"); }

  public Vector3 sin() { return function("sin"); }

  public Vector3 cos() { return function("cos"); }

  public Vector3 tan() { return function("tan"); }

  public Vector3 asin() { return function("asin"); }

  public Vector3 acos() { return function("acos"); }

  public Vector3 atan() { return function("atan"); }

  public Vector3 atan(Vector3 rhs) { return function("atan", rhs); }

  public Vector3 pow(Vector3 rhs) { return function("pow", rhs); }

  public Vector3 exp(Vector3 rhs) { return function("exp", rhs); }

  public Vector3 log(Vector3 rhs) { return function("log", rhs); }

  public Vector3 exp2(Vector3 rhs) { return function("exp2", rhs); }

  public Vector3 log2(Vector3 rhs) { return function("log2", rhs); }

  public Vector3 sqrt() { return function("sqrt"); }

  public Vector3 inversesqrt() { return function("inversesqrt"); }

  private Vector3 function(String name, Expression... extraArguments) {
    ImmutableList<Expression> parents = new ImmutableList.Builder<Expression>()
        .add(this)
        .addAll(ImmutableList.copyOf(extraArguments)).build();

    return new Vector3(parents, NodeType.FunctionNodeType.forFunction(name));
  }

  public Vector3 reflect(Vector3 orientation) {
    return new Vector3(ImmutableList.<Expression>of(this, orientation), NodeType.FunctionNodeType.forFunction("reflect"));
  }

  @Override
  public Vector4 fill(Vector4 defaultExpression) {
    return new Vector4(getX(), getY(), getZ(), defaultExpression.getW());
  }
}

