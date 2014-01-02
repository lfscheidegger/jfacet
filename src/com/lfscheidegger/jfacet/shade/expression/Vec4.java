package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.AttributeBuffer;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.StringHelper;

import java.util.Arrays;

public final class Vec4 extends Expression implements VecLike {

  private static final String GLSL_TYPE_NAME = "vec4";

  public static final class Primitive implements VecPrimitive {

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

    public Primitive setW(float value) {
      set(value, 3);
      return this;
    }

    public Primitive set(float value, int idx) {
      mValues[idx] = value;
      return this;
    }

    public Swizzle.Swizzle41XYZW<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> x() {
      return new Swizzle.Swizzle41XYZW<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("x", this);
    }

    public Swizzle.Swizzle41XYZW<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> y() {
      return new Swizzle.Swizzle41XYZW<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("y", this);
    }

    public Swizzle.Swizzle41XYZW<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> z() {
      return new Swizzle.Swizzle41XYZW<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("z", this);
    }

    public Swizzle.Swizzle41XYZW<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> w() {
      return new Swizzle.Swizzle41XYZW<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("w", this);
    }

    public Swizzle.Swizzle41RGBA<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> r() {
      return new Swizzle.Swizzle41RGBA<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("r", this);
    }

    public Swizzle.Swizzle41RGBA<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> g() {
      return new Swizzle.Swizzle41RGBA<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("g", this);
    }

    public Swizzle.Swizzle41RGBA<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> b() {
      return new Swizzle.Swizzle41RGBA<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("b", this);
    }

    public Swizzle.Swizzle41RGBA<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> a() {
      return new Swizzle.Swizzle41RGBA<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("a", this);
    }

    public Swizzle.Swizzle41STPQ<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> s() {
      return new Swizzle.Swizzle41STPQ<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("s", this);
    }

    public Swizzle.Swizzle41STPQ<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> t() {
      return new Swizzle.Swizzle41STPQ<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("t", this);
    }

    public Swizzle.Swizzle41STPQ<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> p() {
      return new Swizzle.Swizzle41STPQ<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("p", this);
    }

    public Swizzle.Swizzle41STPQ<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> q() {
      return new Swizzle.Swizzle41STPQ<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("q", this);
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

    public BVec4.Primitive isLessThan(Primitive right) {
      return new BVec4.Primitive(
          getX() < right.getX(), getY() < right.getY(), getZ() < right.getZ(), getW() < right.getW());
    }

    public BVec4.Primitive isLessThanOrEqual(Primitive right) {
      return new BVec4.Primitive(
          getX() <= right.getX(), getY() <= right.getY(), getZ() <= right.getZ(), getW() <= right.getW());
    }

    public BVec4.Primitive isGreaterThan(Primitive right) {
      return new BVec4.Primitive(
          getX() > right.getX(), getY() > right.getY(), getZ() > right.getZ(), getW() > right.getW());
    }

    public BVec4.Primitive isGreaterThanOrEqual(Primitive right) {
      return new BVec4.Primitive(
          getX() >= right.getX(), getY() >= right.getY(), getZ() >= right.getZ(), getW() >= right.getW());
    }

    public BVec4.Primitive isEqualComponentwise(Primitive right) {
      return new BVec4.Primitive(
          getX() == right.getX(), getY() == right.getY(), getZ() == right.getZ(), getW() == right.getW());
    }

    public BVec4.Primitive isNotEqualComponentwise(Primitive right) {
      return new BVec4.Primitive(
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
      return new StringHelper(GLSL_TYPE_NAME)
          .addValue(mValues[0])
          .addValue(mValues[1])
          .addValue(mValues[2])
          .addValue(mValues[3])
          .toString();
    }
  }

  public Vec4(float x, float y, float z, float w) {
    this(new Primitive(x, y, z, w));
  }

  public Vec4(Primitive primitive) {
    super(primitive, GLSL_TYPE_NAME);
  }

  public Vec4(Real x, Real y, Real z, Real w) {
    super(ImmutableList.<Expression>of(x, y, z, w), NodeType.CONS, GLSL_TYPE_NAME);
  }

  public Vec4(NodeType nodeType) {
    super(nodeType, GLSL_TYPE_NAME);
  }

  public Vec4(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType, GLSL_TYPE_NAME);
  }

  public Vec4(AttributeBuffer attributeBuffer) {
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

  public Real getW() {
    return get(3);
  }

  public Real get(int idx) {
    Preconditions.checkState(idx < 4);
    return new Real(
        ImmutableList.<Expression>of(this),
        NodeType.ComponentNodeType.forComponent(idx));
  }

  public Swizzle.Swizzle41XYZW<Real, Vec2, Vec3, Vec4> x() {
    return new Swizzle.Swizzle41XYZW<Real, Vec2, Vec3, Vec4>("x", this);
  }

  public Swizzle.Swizzle41XYZW<Real, Vec2, Vec3, Vec4> y() {
    return new Swizzle.Swizzle41XYZW<Real, Vec2, Vec3, Vec4>("y", this);
  }

  public Swizzle.Swizzle41XYZW<Real, Vec2, Vec3, Vec4> z() {
    return new Swizzle.Swizzle41XYZW<Real, Vec2, Vec3, Vec4>("z", this);
  }

  public Swizzle.Swizzle41XYZW<Real, Vec2, Vec3, Vec4> w() {
    return new Swizzle.Swizzle41XYZW<Real, Vec2, Vec3, Vec4>("w", this);
  }

  public Swizzle.Swizzle41RGBA<Real, Vec2, Vec3, Vec4> r() {
    return new Swizzle.Swizzle41RGBA<Real, Vec2, Vec3, Vec4>("r", this);
  }

  public Swizzle.Swizzle41RGBA<Real, Vec2, Vec3, Vec4> g() {
    return new Swizzle.Swizzle41RGBA<Real, Vec2, Vec3, Vec4>("g", this);
  }

  public Swizzle.Swizzle41RGBA<Real, Vec2, Vec3, Vec4> b() {
    return new Swizzle.Swizzle41RGBA<Real, Vec2, Vec3, Vec4>("b", this);
  }

  public Swizzle.Swizzle41RGBA<Real, Vec2, Vec3, Vec4> a() {
    return new Swizzle.Swizzle41RGBA<Real, Vec2, Vec3, Vec4>("a", this);
  }

  public Swizzle.Swizzle41STPQ<Real, Vec2, Vec3, Vec4> s() {
    return new Swizzle.Swizzle41STPQ<Real, Vec2, Vec3, Vec4>("s", this);
  }

  public Swizzle.Swizzle41STPQ<Real, Vec2, Vec3, Vec4> t() {
    return new Swizzle.Swizzle41STPQ<Real, Vec2, Vec3, Vec4>("t", this);
  }

  public Swizzle.Swizzle41STPQ<Real, Vec2, Vec3, Vec4> p() {
    return new Swizzle.Swizzle41STPQ<Real, Vec2, Vec3, Vec4>("p", this);
  }

  public Swizzle.Swizzle41STPQ<Real, Vec2, Vec3, Vec4> q() {
    return new Swizzle.Swizzle41STPQ<Real, Vec2, Vec3, Vec4>("q", this);
  }

  public Vec4 add(float right) {
    return add(new Real(right));
  }

  public Vec4 add(Real right) {
    return new Vec4(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Vec4 add(Vec4 right) {
    return new Vec4(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Vec4 sub(float right) {
    return sub(new Real(right));
  }

  public Vec4 sub(Real right) {
    return new Vec4(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Vec4 sub(Vec4 right) {
    return new Vec4(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Vec4 mul(float right) {
    return mul(new Real(right));
  }

  public Vec4 mul(Real right) {
    return new Vec4(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Vec4 mul(Vec4 right) {
    return new Vec4(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Vec4 div(float right) {
    return div(new Real(right));
  }

  public Vec4 div(Real right) {
    return new Vec4(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Vec4 div(Vec4 right) {
    return new Vec4(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Vec4 neg() {
    return new Vec4(ImmutableList.<Expression>of(this), NodeType.NEG);
  }

  public BVec4 isLessThan(Vec4 right) {
    return new BVec4(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("lessThan"));
  }

  public BVec4 isLessThanOrEqual(Vec4 right) {
    return new BVec4(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("lessThanEqual"));
  }


  public BVec4 isGreaterThan(Vec4 right) {
    return new BVec4(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("greaterThan"));
  }

  public BVec4 isGreaterThanOrEqual(Vec4 right) {
    return new BVec4(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("greaterThanEqual"));
  }

  public BVec4 isEqualComponentwise(Vec4 right) {
    return new BVec4(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("equal"));
  }

  public BVec4 isNotEqualComponentwise(Vec4 right) {
    return new BVec4(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("notEqual"));
  }

  public Bool isEqual(Vec4 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.EQ);
  }

  public Bool isNotEqual(Vec4 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.NEQ);
  }

  public Vec4 radians() { return function("radians", this); }

  public Vec4 degrees() { return function("degrees", this); }

  public Vec4 sin() { return function("sin", this); }

  public Vec4 cos() { return function("cos", this); }

  public Vec4 tan() { return function("tan", this); }

  public Vec4 asin() { return function("asin", this); }

  public Vec4 acos() { return function("acos", this); }

  public Vec4 atan() { return function("atan", this); }

  public Vec4 atan(Vec4 rhs) { return function("atan", this, rhs); }

  public Vec4 pow() { return function("pow", this); }

  public Vec4 exp() { return function("exp", this); }

  public Vec4 log() { return function("log", this); }

  public Vec4 exp2() { return function("exp2", this); }

  public Vec4 log2() { return function("log2", this); }

  public Vec4 sqrt() { return function("sqrt", this); }

  public Vec4 inversesqrt() { return function("inversesqrt", this); }

  public Vec4 abs() { return function("abs", this); }

  public Vec4 sign() { return function("sign", this); }

  public Vec4 floor() { return function("floor", this); }

  public Vec4 ceil() { return function("ceil", this); }

  public Vec4 fract() { return function("fract", this); }

  public Vec4 mod(float rhs) { return mod(Shade.constant(rhs)); }

  public Vec4 mod(Real rhs) { return function("mod", this, rhs); }

  public Vec4 mod(Vec4 rhs) { return function("mod", this, rhs); }

  public Vec4 min(float rhs) { return min(Shade.constant(rhs)); }

  public Vec4 min(Real rhs) { return function("min", this, rhs); }

  public Vec4 min(Vec4 rhs) { return function("min", this, rhs); }

  public Vec4 max(float rhs) { return max(Shade.constant(rhs)); }

  public Vec4 max(Real rhs) { return function("max", this, rhs); }

  public Vec4 max(Vec4 rhs) { return function("max", this, rhs); }

  public Vec4 clamp(float minValue, float maxValue) {
    return clamp(Shade.constant(minValue), Shade.constant(maxValue));
  }

  public Vec4 clamp(float minValue, Real maxValue) {
    return clamp(Shade.constant(minValue), maxValue);
  }

  public Vec4 clamp(Real minValue, float maxValue) {
    return clamp(minValue, Shade.constant(maxValue));
  }

  public Vec4 clamp(Real minValue, Real maxValue) { return function("clamp", this, minValue, maxValue); }

  public Vec4 clamp(Vec4 minValue, Vec4 maxValue) { return function("clamp", this, minValue, maxValue); }

  public Vec4 mix(float minValue, float maxValue) {
    return mix(Shade.constant(minValue), Shade.constant(maxValue));
  }

  public Vec4 mix(float minValue, Real maxValue) {
    return mix(Shade.constant(minValue), maxValue);
  }

  public Vec4 mix(Real minValue, float maxValue) {
    return mix(minValue, Shade.constant(maxValue));
  }

  public Vec4 mix(Real minValue, Real maxValue) { return function("mix", this, minValue, maxValue); }

  public Vec4 mix(Vec4 minValue, Vec4 maxValue) { return function("mix", this, minValue, maxValue); }

  public Vec4 step(float edge) { return step(Shade.constant(edge)); }

  public Vec4 step(Real edge) { return function("step", edge, this); }

  public Vec4 step(Vec4 edge) { return function("edge", edge, this); }

  public Vec4 smoothStep(float edge0, float edge1) {
    return smoothStep(Shade.constant(edge0), Shade.constant(edge1));
  }

  public Vec4 smoothStep(float edge0, Real edge1) {
    return smoothStep(Shade.constant(edge0), edge1);
  }

  public Vec4 smoothStep(Real edgeo0, float edge1) {
    return smoothStep(edgeo0, Shade.constant(edge1));
  }

  public Vec4 smoothStep(Real edge0, Real edge1) {
    return function("edge", edge0, edge1, this);
  }

  public Real length() {
    return new Real(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("length"));
  }

  public Real distance(Vec4 rhs) {
    return new Real(
        ImmutableList.<Expression>of(this, rhs),
        NodeType.FunctionNodeType.forFunction("distance"));
  }

  public Real dot(Vec4 right) {
    return new Real(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("dot"));
  }

  public Vec4 cross(Vec4 right) {
    return new Vec4(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("cross"));
  }

  public Vec4 normalize() {
    return function("normalize", this);
  }

  public Vec4 faceForward(Vec4 i, Vec4 nRef) {
    return function("faceforward", this, i, nRef);
  }

  public Vec4 reflect(Vec4 normal) {
    return function("reflect", this, normal);
  }

  public Vec4 refract(Vec4 normal, float eta) {
    return refract(normal, Shade.constant(eta));
  }

  public Vec4 refract(Vec4 normal, Real eta) {
    return function("refract", this, normal, eta);
  }

  private Vec4 function(String name, Expression... arguments) {
    return new Vec4(ImmutableList.copyOf(arguments), NodeType.FunctionNodeType.forFunction(name));
  }
}
