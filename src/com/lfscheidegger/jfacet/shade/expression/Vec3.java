// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.AttributeBuffer;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.utils.ArrayUtils;
import com.lfscheidegger.jfacet.utils.StringHelper;

import java.util.Arrays;

public final class Vec3 extends Expression implements VecLike {

  private static final String GLSL_TYPE_NAME = "vec3";

  public static final class Primitive implements VecPrimitive {

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

    public Swizzle.Swizzle31XYZW<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> x() {
      return new Swizzle.Swizzle31XYZW<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("x", this);
    }

    public Swizzle.Swizzle31XYZW<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> y() {
      return new Swizzle.Swizzle31XYZW<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("y", this);
    }

    public Swizzle.Swizzle31XYZW<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> z() {
      return new Swizzle.Swizzle31XYZW<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("z", this);
    }

    public Swizzle.Swizzle31RGBA<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> r() {
      return new Swizzle.Swizzle31RGBA<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("r", this);
    }

    public Swizzle.Swizzle31RGBA<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> g() {
      return new Swizzle.Swizzle31RGBA<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("g", this);
    }

    public Swizzle.Swizzle31RGBA<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> b() {
      return new Swizzle.Swizzle31RGBA<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("b", this);
    }

    public Swizzle.Swizzle31STPQ<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> s() {
      return new Swizzle.Swizzle31STPQ<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("s", this);
    }

    public Swizzle.Swizzle31STPQ<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> t() {
      return new Swizzle.Swizzle31STPQ<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("t", this);
    }

    public Swizzle.Swizzle31STPQ<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive> p() {
      return new Swizzle.Swizzle31STPQ<Float, Vec2.Primitive, Vec3.Primitive, Vec4.Primitive>("p", this);
    }

    public Primitive plus(Primitive other) {
      return new Primitive(ArrayUtils.add(mValues, other.mValues));
    }

    public Primitive plus(float t) {
      return new Primitive(ArrayUtils.add(mValues, t));
    }

    public Primitive minus(Primitive other) {
      return new Primitive(ArrayUtils.sub(mValues, other.mValues));
    }

    public Primitive minus(float t) {
      return new Primitive(ArrayUtils.sub(mValues, t));
    }

    public Primitive times(Primitive other) {
      return new Primitive(ArrayUtils.mul(mValues, other.mValues));
    }

    public Primitive times(float t) {
      return new Primitive(ArrayUtils.mul(mValues, t));
    }

    public Primitive div(Primitive other) {
      return new Primitive(ArrayUtils.div(mValues, other.mValues));
    }

    public Primitive div(float t) {
      return new Primitive(ArrayUtils.div(mValues, t));
    }

    public Primitive negative() {
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

    public BVec3.Primitive isLessThan(Primitive right) {
      return new BVec3.Primitive(getX() < right.getX(), getY() < right.getY(), getZ() < right.getZ());
    }

    public BVec3.Primitive isLessThanOrEqual(Primitive right) {
      return new BVec3.Primitive(getX() <= right.getX(), getY() <= right.getY(), getZ() <= right.getZ());
    }

    public BVec3.Primitive isGreaterThan(Primitive right) {
      return new BVec3.Primitive(getX() > right.getX(), getY() > right.getY(), getZ() > right.getZ());
    }

    public BVec3.Primitive isGreaterThanOrEqual(Primitive right) {
      return new BVec3.Primitive(getX() >= right.getX(), getY() >= right.getY(), getZ() >= right.getZ());
    }

    public BVec3.Primitive isEqualComponentwise(Primitive right) {
      return new BVec3.Primitive(getX() == right.getX(), getY() == right.getY(), getZ() == right.getZ());
    }

    public BVec3.Primitive isNotEqualComponentwise(Primitive right) {
      return new BVec3.Primitive(getX() != right.getX(), getY() != right.getY(), getZ() != right.getZ());
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

  public Vec3(float x, float y, float z) {
    this(new Primitive(x, y, z));
  }

  public Vec3(Primitive primitive) {
    super(primitive, GLSL_TYPE_NAME);
  }

  public Vec3(Real x, Real y, Real z) {
    super(ImmutableList.<Expression>of(x, y, z), NodeType.CONS, GLSL_TYPE_NAME);
  }

  public Vec3(NodeType nodeType) {
    super(nodeType, GLSL_TYPE_NAME);
  }

  public Vec3(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType, GLSL_TYPE_NAME);
  }

  public Vec3(AttributeBuffer attributeBuffer) {
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

  public Real get(int idx) {
    Preconditions.checkState(idx < 3);
    return new Real(
        ImmutableList.<Expression>of(this),
        NodeType.ComponentNodeType.forComponent(idx));
  }

  public Swizzle.Swizzle31XYZW<Real, Vec2, Vec3, Vec4> x() {
    return new Swizzle.Swizzle31XYZW<Real, Vec2, Vec3, Vec4>("x", this);
  }

  public Swizzle.Swizzle31XYZW<Real, Vec2, Vec3, Vec4> y() {
    return new Swizzle.Swizzle31XYZW<Real, Vec2, Vec3, Vec4>("y", this);
  }

  public Swizzle.Swizzle31XYZW<Real, Vec2, Vec3, Vec4> z() {
    return new Swizzle.Swizzle31XYZW<Real, Vec2, Vec3, Vec4>("z", this);
  }

  public Swizzle.Swizzle31RGBA<Real, Vec2, Vec3, Vec4> r() {
    return new Swizzle.Swizzle31RGBA<Real, Vec2, Vec3, Vec4>("r", this);
  }

  public Swizzle.Swizzle31RGBA<Real, Vec2, Vec3, Vec4> g() {
    return new Swizzle.Swizzle31RGBA<Real, Vec2, Vec3, Vec4>("g", this);
  }

  public Swizzle.Swizzle31RGBA<Real, Vec2, Vec3, Vec4> b() {
    return new Swizzle.Swizzle31RGBA<Real, Vec2, Vec3, Vec4>("b", this);
  }

  public Swizzle.Swizzle31STPQ<Real, Vec2, Vec3, Vec4> s() {
    return new Swizzle.Swizzle31STPQ<Real, Vec2, Vec3, Vec4>("s", this);
  }

  public Swizzle.Swizzle31STPQ<Real, Vec2, Vec3, Vec4> t() {
    return new Swizzle.Swizzle31STPQ<Real, Vec2, Vec3, Vec4>("t", this);
  }

  public Swizzle.Swizzle31STPQ<Real, Vec2, Vec3, Vec4> p() {
    return new Swizzle.Swizzle31STPQ<Real, Vec2, Vec3, Vec4>("p", this);
  }

  public Vec3 plus(float right) {
    return plus(new Real(right));
  }

  public Vec3 plus(Real right) {
    return new Vec3(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Vec3 plus(Vec3 right) {
    return new Vec3(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Vec3 minus(float right) {
    return minus(new Real(right));
  }

  public Vec3 minus(Real right) {
    return new Vec3(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Vec3 minus(Vec3 right) {
    return new Vec3(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Vec3 times(float right) {
    return times(new Real(right));
  }

  public Vec3 times(Real right) {
    return new Vec3(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Vec3 times(Vec3 right) {
    return new Vec3(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Vec3 div(float right) {
    return div(new Real(right));
  }

  public Vec3 div(Real right) {
    return new Vec3(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Vec3 div(Vec3 right) {
    return new Vec3(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Vec3 negative() {
    return new Vec3(ImmutableList.<Expression>of(this), NodeType.NEG);
  }

  public BVec3 isLessThan(Vec3 right) {
    return new BVec3(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("lessThan"));
  }

  public BVec3 isLessThanOrEqual(Vec3 right) {
    return new BVec3(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("lessThanEqual"));
  }


  public BVec3 isGreaterThan(Vec3 right) {
    return new BVec3(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("greaterThan"));
  }

  public BVec3 isGreaterThanOrEqual(Vec3 right) {
    return new BVec3(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("greaterThanEqual"));
  }

  public BVec3 isEqualComponentwise(Vec3 right) {
    return new BVec3(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("equal"));
  }

  public BVec3 isNotEqualComponentwise(Vec3 right) {
    return new BVec3(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("notEqual"));
  }

  public Bool isEqual(Vec3 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.EQ);
  }

  public Bool isNotEqual(Vec3 right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.NEQ);
  }

  public Vec3 radians() { return function("radians", this); }

  public Vec3 degrees() { return function("degrees", this); }

  public Vec3 sin() { return function("sin", this); }

  public Vec3 cos() { return function("cos", this); }

  public Vec3 tan() { return function("tan", this); }

  public Vec3 asin() { return function("asin", this); }

  public Vec3 acos() { return function("acos", this); }

  public Vec3 atan() { return function("atan", this); }

  public Vec3 atan(Vec3 rhs) { return function("atan", this, rhs); }

  public Vec3 pow() { return function("pow", this); }

  public Vec3 exp() { return function("exp", this); }

  public Vec3 log() { return function("log", this); }

  public Vec3 exp2() { return function("exp2", this); }

  public Vec3 log2() { return function("log2", this); }

  public Vec3 sqrt() { return function("sqrt", this); }

  public Vec3 inversesqrt() { return function("inversesqrt", this); }

  public Vec3 abs() { return function("abs", this); }

  public Vec3 sign() { return function("sign", this); }

  public Vec3 floor() { return function("floor", this); }

  public Vec3 ceil() { return function("ceil", this); }

  public Vec3 fract() { return function("fract", this); }

  public Vec3 mod(float rhs) { return mod(Shade.constant(rhs)); }

  public Vec3 mod(Real rhs) { return function("mod", this, rhs); }

  public Vec3 mod(Vec3 rhs) { return function("mod", this, rhs); }

  public Vec3 min(float rhs) { return min(Shade.constant(rhs)); }

  public Vec3 min(Real rhs) { return function("min", this, rhs); }

  public Vec3 min(Vec3 rhs) { return function("min", this, rhs); }

  public Vec3 max(float rhs) { return max(Shade.constant(rhs)); }

  public Vec3 max(Real rhs) { return function("max", this, rhs); }

  public Vec3 max(Vec3 rhs) { return function("max", this, rhs); }

  public Vec3 clamp(float minValue, float maxValue) {
    return clamp(Shade.constant(minValue), Shade.constant(maxValue));
  }

  public Vec3 clamp(float minValue, Real maxValue) {
    return clamp(Shade.constant(minValue), maxValue);
  }

  public Vec3 clamp(Real minValue, float maxValue) {
    return clamp(minValue, Shade.constant(maxValue));
  }

  public Vec3 clamp(Real minValue, Real maxValue) { return function("clamp", this, minValue, maxValue); }

  public Vec3 clamp(Vec3 minValue, Vec3 maxValue) { return function("clamp", this, minValue, maxValue); }

  public Vec3 mix(float minValue, float maxValue) {
    return mix(Shade.constant(minValue), Shade.constant(maxValue));
  }

  public Vec3 mix(float minValue, Real maxValue) {
    return mix(Shade.constant(minValue), maxValue);
  }

  public Vec3 mix(Real minValue, float maxValue) {
    return mix(minValue, Shade.constant(maxValue));
  }

  public Vec3 mix(Real minValue, Real maxValue) { return function("mix", this, minValue, maxValue); }

  public Vec3 mix(Vec3 minValue, Vec3 maxValue) { return function("mix", this, minValue, maxValue); }

  public Vec3 step(float edge) { return step(Shade.constant(edge)); }

  public Vec3 step(Real edge) { return function("step", edge, this); }

  public Vec3 step(Vec3 edge) { return function("edge", edge, this); }

  public Vec3 smoothStep(float edge0, float edge1) {
    return smoothStep(Shade.constant(edge0), Shade.constant(edge1));
  }

  public Vec3 smoothStep(float edge0, Real edge1) {
    return smoothStep(Shade.constant(edge0), edge1);
  }

  public Vec3 smoothStep(Real edgeo0, float edge1) {
    return smoothStep(edgeo0, Shade.constant(edge1));
  }

  public Vec3 smoothStep(Real edge0, Real edge1) {
    return function("edge", edge0, edge1, this);
  }

  public Real length() {
    return new Real(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("length"));
  }

  public Real distance(Vec3 rhs) {
    return new Real(
        ImmutableList.<Expression>of(this, rhs),
        NodeType.FunctionNodeType.forFunction("distance"));
  }

  public Real dot(Vec3 right) {
    return new Real(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("dot"));
  }

  public Vec3 cross(Vec3 right) {
    return new Vec3(
        ImmutableList.<Expression>of(this, right),
        NodeType.FunctionNodeType.forFunction("cross"));
  }

  public Vec3 normalize() {
    return function("normalize", this);
  }

  public Vec3 faceForward(Vec3 i, Vec3 nRef) {
    return function("faceforward", this, i, nRef);
  }

  public Vec3 reflect(Vec3 normal) {
    return function("reflect", this, normal);
  }

  public Vec3 refract(Vec3 normal, float eta) {
    return refract(normal, Shade.constant(eta));
  }

  public Vec3 refract(Vec3 normal, Real eta) {
    return function("refract", this, normal, eta);
  }

  private Vec3 function(String name, Expression... arguments) {
    return new Vec3(ImmutableList.copyOf(arguments), NodeType.FunctionNodeType.forFunction(name));
  }
}

