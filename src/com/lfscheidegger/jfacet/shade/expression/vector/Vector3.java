package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.facet.AttributeBuffer;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.StringHelper;

import java.util.Arrays;

public final class Vector3 extends Expression implements VectorExpression<Real> {

  private static final String GLSL_TYPE_NAME = "vec3";

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
      return new StringHelper(GLSL_TYPE_NAME)
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
    super(primitive, GLSL_TYPE_NAME);
  }

  public Vector3(Real x, Real y, Real z) {
    super(ImmutableList.<Expression>of(x, y, z), NodeType.CONS, GLSL_TYPE_NAME);
  }

  public Vector3(NodeType nodeType) {
    super(nodeType, GLSL_TYPE_NAME);
  }

  public Vector3(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType, GLSL_TYPE_NAME);
  }

  public Vector3(AttributeBuffer attributeBuffer) {
    super(NodeType.AttributeNodeType.forAttribute(attributeBuffer), GLSL_TYPE_NAME);
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

  public Vector3 radians() { return function("radians", this); }

  public Vector3 degrees() { return function("degrees", this); }

  public Vector3 sin() { return function("sin", this); }

  public Vector3 cos() { return function("cos", this); }

  public Vector3 tan() { return function("tan", this); }

  public Vector3 asin() { return function("asin", this); }

  public Vector3 acos() { return function("acos", this); }

  public Vector3 atan() { return function("atan", this); }

  public Vector3 atan(Vector3 rhs) { return function("atan", this, rhs); }

  public Vector3 pow() { return function("pow", this); }

  public Vector3 exp() { return function("exp", this); }

  public Vector3 log() { return function("log", this); }

  public Vector3 exp2() { return function("exp2", this); }

  public Vector3 log2() { return function("log2", this); }

  public Vector3 sqrt() { return function("sqrt", this); }

  public Vector3 inversesqrt() { return function("inversesqrt", this); }

  public Vector3 abs() { return function("abs", this); }

  public Vector3 sign() { return function("sign", this); }

  public Vector3 floor() { return function("floor", this); }

  public Vector3 ceil() { return function("ceil", this); }

  public Vector3 fract() { return function("fract", this); }

  public Vector3 mod(float rhs) { return mod(Shade.constant(rhs)); }

  public Vector3 mod(Real rhs) { return function("mod", this, rhs); }

  public Vector3 mod(Vector3 rhs) { return function("mod", this, rhs); }

  public Vector3 min(float rhs) { return min(Shade.constant(rhs)); }

  public Vector3 min(Real rhs) { return function("min", this, rhs); }

  public Vector3 min(Vector3 rhs) { return function("min", this, rhs); }

  public Vector3 max(float rhs) { return max(Shade.constant(rhs)); }

  public Vector3 max(Real rhs) { return function("max", this, rhs); }

  public Vector3 max(Vector3 rhs) { return function("max", this, rhs); }

  public Vector3 clamp(float minValue, float maxValue) {
    return clamp(Shade.constant(minValue), Shade.constant(maxValue));
  }

  public Vector3 clamp(float minValue, Real maxValue) {
    return clamp(Shade.constant(minValue), maxValue);
  }

  public Vector3 clamp(Real minValue, float maxValue) {
    return clamp(minValue, Shade.constant(maxValue));
  }

  public Vector3 clamp(Real minValue, Real maxValue) { return function("clamp", this, minValue, maxValue); }

  public Vector3 clamp(Vector3 minValue, Vector3 maxValue) { return function("clamp", this, minValue, maxValue); }

  public Vector3 mix(float minValue, float maxValue) {
    return mix(Shade.constant(minValue), Shade.constant(maxValue));
  }

  public Vector3 mix(float minValue, Real maxValue) {
    return mix(Shade.constant(minValue), maxValue);
  }

  public Vector3 mix(Real minValue, float maxValue) {
    return mix(minValue, Shade.constant(maxValue));
  }

  public Vector3 mix(Real minValue, Real maxValue) { return function("mix", this, minValue, maxValue); }

  public Vector3 mix(Vector3 minValue, Vector3 maxValue) { return function("mix", this, minValue, maxValue); }

  public Vector3 step(float edge) { return step(Shade.constant(edge)); }

  public Vector3 step(Real edge) { return function("step", edge, this); }

  public Vector3 step(Vector3 edge) { return function("edge", edge, this); }

  public Vector3 smoothStep(float edge0, float edge1) {
    return smoothStep(Shade.constant(edge0), Shade.constant(edge1));
  }

  public Vector3 smoothStep(float edge0, Real edge1) {
    return smoothStep(Shade.constant(edge0), edge1);
  }

  public Vector3 smoothStep(Real edgeo0, float edge1) {
    return smoothStep(edgeo0, Shade.constant(edge1));
  }

  public Vector3 smoothStep(Real edge0, Real edge1) {
    return function("edge", edge0, edge1, this);
  }

  public Real length() {
    return new Real(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("length"));
  }

  public Real distance(Vector3 rhs) {
    return new Real(
        ImmutableList.<Expression>of(this, rhs),
        NodeType.FunctionNodeType.forFunction("distance"));
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
    return function("normalize", this);
  }

  public Vector3 faceForward(Vector3 i, Vector3 nRef) {
    return function("faceforward", this, i, nRef);
  }

  public Vector3 reflect(Vector3 normal) {
    return function("reflect", this, normal);
  }

  public Vector3 refract(Vector3 normal, float eta) {
    return refract(normal, Shade.constant(eta));
  }

  public Vector3 refract(Vector3 normal, Real eta) {
    return function("refract", this, normal, eta);
  }

  private Vector3 function(String name, Expression... arguments) {
    return new Vector3(ImmutableList.copyOf(arguments), NodeType.FunctionNodeType.forFunction(name));
  }
}

