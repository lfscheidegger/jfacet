package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.AttributeBuffer;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.StringHelper;

import java.util.Arrays;

public final class Vector2 extends Expression implements VectorExpression<Real> {

  private static final String GLSL_TYPE_NAME = "vec2";

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

    public Primitive setX(float value) {
      set(value, 0);
      return this;
    }

    public Primitive setY(float value) {
      set(value, 1);
      return this;
    }

    public Primitive set(float value, int idx) {
      mValues[idx] = value;
      return this;
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
      return new StringHelper(GLSL_TYPE_NAME)
          .addValue(mValues[0])
          .addValue(mValues[1])
          .toString();
    }
  }

  public Vector2(float x, float y) {
    this(new Primitive(x, y));
  }

  public Vector2(Primitive primitive) {
    super(primitive, GLSL_TYPE_NAME);
  }

  public Vector2(Real x, Real y) {
    super(ImmutableList.<Expression>of(x, y), NodeType.CONS, GLSL_TYPE_NAME);
  }

  public Vector2(NodeType nodeType) {
    super(nodeType, GLSL_TYPE_NAME);
  }

  public Vector2(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType, GLSL_TYPE_NAME);
  }

  public Vector2(AttributeBuffer attributeBuffer) {
    super(NodeType.AttributeNodeType.forAttribute(attributeBuffer), GLSL_TYPE_NAME);
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

  public Vector2 radians() { return function("radians", this); }

  public Vector2 degrees() { return function("degrees", this); }

  public Vector2 sin() { return function("sin", this); }

  public Vector2 cos() { return function("cos", this); }

  public Vector2 tan() { return function("tan", this); }

  public Vector2 asin() { return function("asin", this); }

  public Vector2 acos() { return function("acos", this); }

  public Vector2 atan() { return function("atan", this); }

  public Vector2 atan(Vector2 rhs) { return function("atan", this, rhs); }

  public Vector2 pow() { return function("pow", this); }

  public Vector2 exp() { return function("exp"); }

  public Vector2 log() { return function("log", this); }

  public Vector2 exp2() { return function("exp2", this); }

  public Vector2 log2() { return function("log2", this); }

  public Vector2 sqrt() { return function("sqrt", this); }

  public Vector2 inversesqrt() { return function("inversesqrt", this); }

  public Vector2 abs() { return function("abs", this); }

  public Vector2 sign() { return function("sign", this); }

  public Vector2 floor() { return function("floor", this); }

  public Vector2 ceil() { return function("ceil", this); }

  public Vector2 fract() { return function("fract", this); }

  public Vector2 mod(float rhs) { return mod(Shade.constant(rhs)); }

  public Vector2 mod(Real rhs) { return function("mod", this, rhs); }

  public Vector2 mod(Vector2 rhs) { return function("mod", this, rhs); }

  public Vector2 min(float rhs) { return min(Shade.constant(rhs)); }

  public Vector2 min(Real rhs) { return function("min", this, rhs); }

  public Vector2 min(Vector2 rhs) { return function("min", this, rhs); }

  public Vector2 max(float rhs) { return max(Shade.constant(rhs)); }

  public Vector2 max(Real rhs) { return function("max", this, rhs); }

  public Vector2 max(Vector2 rhs) { return function("max", this, rhs); }

  public Vector2 clamp(float minValue, float maxValue) {
    return clamp(Shade.constant(minValue), Shade.constant(maxValue));
  }

  public Vector2 clamp(float minValue, Real maxValue) {
    return clamp(Shade.constant(minValue), maxValue);
  }

  public Vector2 clamp(Real minValue, float maxValue) {
    return clamp(minValue, Shade.constant(maxValue));
  }

  public Vector2 clamp(Real minValue, Real maxValue) { return function("clamp", this, minValue, maxValue); }

  public Vector2 clamp(Vector2 minValue, Vector2 maxValue) { return function("clamp", this, minValue, maxValue); }

  public Vector2 mix(float minValue, float maxValue) {
    return mix(Shade.constant(minValue), Shade.constant(maxValue));
  }

  public Vector2 mix(float minValue, Real maxValue) {
    return mix(Shade.constant(minValue), maxValue);
  }

  public Vector2 mix(Real minValue, float maxValue) {
    return mix(minValue, Shade.constant(maxValue));
  }

  public Vector2 mix(Real minValue, Real maxValue) { return function("mix", this, minValue, maxValue); }

  public Vector2 mix(Vector2 minValue, Vector2 maxValue) { return function("mix", this, minValue, maxValue); }

  public Vector2 step(float edge) { return step(Shade.constant(edge)); }

  public Vector2 step(Real edge) { return function("step", edge, this); }

  public Vector2 step(Vector2 edge) { return function("edge", edge, this); }

  public Vector2 smoothStep(float edge0, float edge1) {
    return smoothStep(Shade.constant(edge0), Shade.constant(edge1));
  }

  public Vector2 smoothStep(float edge0, Real edge1) {
    return smoothStep(Shade.constant(edge0), edge1);
  }

  public Vector2 smoothStep(Real edgeo0, float edge1) {
    return smoothStep(edgeo0, Shade.constant(edge1));
  }

  public Vector2 smoothStep(Real edge0, Real edge1) {
    return function("edge", edge0, edge1, this);
  }

  public Real length() {
    return new Real(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("length"));
  }

  public Real distance(Vector2 rhs) {
    return new Real(
        ImmutableList.<Expression>of(this, rhs),
        NodeType.FunctionNodeType.forFunction("distance"));
  }

  public Real dot(Vector2 right) {
    return new Real(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("dot"));
  }

  public Vector2 normalize() {
    return function("normalize", this);
  }

  public Vector2 faceForward(Vector2 i, Vector2 nRef) {
    return function("faceforward", this, i, nRef);
  }

  public Vector2 reflect(Vector2 normal) {
    return function("reflect", this, normal);
  }

  public Vector2 refract(Vector2 normal, float eta) {
    return refract(normal, Shade.constant(eta));
  }

  public Vector2 refract(Vector2 normal, Real eta) {
    return function("refract", this, normal, eta);
  }

  private Vector2 function(String name, Expression... arguments) {
    return new Vector2(ImmutableList.copyOf(arguments), NodeType.FunctionNodeType.forFunction(name));
  }
}
