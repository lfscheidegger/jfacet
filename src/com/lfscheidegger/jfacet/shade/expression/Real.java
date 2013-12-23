package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.facet.AttributeBuffer;
import com.lfscheidegger.jfacet.shade.Shade;

/**
 * {code Expression} for floats
 */
public final class Real extends Expression {

  public Real(float c) {
    super(c);
  }

  public Real(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType);
  }

  public Real(NodeType nodeType) {
    super(nodeType);
  }

  public Real(AttributeBuffer attributeBuffer) {
    super(NodeType.AttributeNodeType.forAttribute(attributeBuffer));
  }

  @Override
  public String getGlSlTypeName() {
    return "float";
  }

  public Real add(Real right) {
    return new Real(ImmutableList.<Expression>of(this, right), NodeType.ADD);
  }

  public Real add(float right) {
    return add(new Real(right));
  }

  public Real sub(Real right) {
    return new Real(ImmutableList.<Expression>of(this, right), NodeType.SUB);
  }

  public Real sub(float right) {
    return sub(new Real(right));
  }

  public Real mul(Real right) {
    return new Real(ImmutableList.<Expression>of(this, right), NodeType.MUL);
  }

  public Real mul(float right) {
    return mul(new Real(right));
  }

  public Real div(Real right) {
    return new Real(ImmutableList.<Expression>of(this, right), NodeType.DIV);
  }

  public Real div(float right) {
    return div(new Real(right));
  }

  public Real neg() {
    return new Real(ImmutableList.<Expression>of(this), NodeType.NEG);
  }

  public Bool isGreaterThan(Real right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.GT);
  }

  public Bool isGreaterThan(float right) {
    return isGreaterThan(new Real(right));
  }

  public Bool isGreaterThanOrEqual(Real right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.GEQ);
  }

  public Bool isGreaterThanOrEqual(float right) {
    return isGreaterThanOrEqual(new Real(right));
  }

  public Bool isLessThan(Real right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.LT);
  }

  public Bool isLessThan(float right) {
    return isLessThan(new Real(right));
  }

  public Bool isLessThanOrEqual(Real right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.LEQ);
  }

  public Bool isLessThanOrEqual(float right) {
    return isLessThanOrEqual(new Real(right));
  }

  public Bool isEqual(Real right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.EQ);
  }

  public Bool isEqual(float right) {
    return isEqual(new Real(right));
  }

  public Bool isNotEqual(Real right) {
    return new Bool(ImmutableList.<Expression>of(this, right), NodeType.NEQ);
  }

  public Bool isNotEqual(float right) {
    return isNotEqual(new Real(right));
  }

  public Real radians() { return function("radians", this); }

  public Real degrees() { return function("degrees", this); }

  public Real sin() { return function("sin", this); }

  public Real cos() { return function("cos", this); }

  public Real tan() { return function("tan", this); }

  public Real asin() { return function("asin", this); }

  public Real acos() { return function("acos", this); }

  public Real atan() { return function("atan", this); }

  public Real atan(float rhs) { return atan(Shade.constant(rhs)); }

  public Real atan(Real rhs) { return function("atan", this, rhs); }

  public Real pow(float rhs) { return pow(Shade.constant(rhs)); }

  public Real pow(Real rhs) { return function("pow", this, rhs); }

  public Real exp() { return function("exp", this); }

  public Real log() { return function("log", this); }

  public Real exp2() { return function("exp2", this); }

  public Real log2() { return function("log2", this); }

  public Real sqrt() { return function("sqrt", this); }

  public Real inversesqrt() { return function("inversesqrt", this); }

  public Real abs() { return function("abs", this); }

  public Real sign() { return function("sign", this); }

  public Real floor() { return function("floor", this); }

  public Real ceil() { return function("ceil", this); }

  public Real fract() { return function("fract", this); }

  public Real mod(float rhs) { return mod(Shade.constant(rhs)); }

  public Real mod(Real rhs) { return function("mod", this, rhs); }

  public Real min(float rhs) { return min(Shade.constant(rhs)); }

  public Real min(Real rhs) { return function("min", this, rhs); }

  public Real max(float rhs) { return max(Shade.constant(rhs)); }

  public Real max(Real rhs) { return function("max", this, rhs); }

  public Real clamp(float minValue, float maxValue) {
    return clamp(Shade.constant(minValue), Shade.constant(maxValue));
  }

  public Real clamp(float minValue, Real maxValue) {
    return clamp(Shade.constant(minValue), maxValue);
  }

  public Real clamp(Real minValue, float maxValue) {
    return clamp(minValue, Shade.constant(maxValue));
  }

  public Real clamp(Real minValue, Real maxValue) { return function("clamp", this, minValue, maxValue); }

  public Real mix(float minValue, float maxValue) {
    return mix(Shade.constant(minValue), Shade.constant(maxValue));
  }

  public Real mix(float minValue, Real maxValue) {
    return mix(Shade.constant(minValue), maxValue);
  }

  public Real mix(Real minValue, float maxValue) {
    return mix(minValue, Shade.constant(maxValue));
  }

  public Real mix(Real minValue, Real maxValue) { return function("mix", this, minValue, maxValue); }

  public Real step(float edge) { return step(Shade.constant(edge)); }

  public Real step(Real edge) { return function("edge", edge, this); }

  public Real smoothStep(float edge0, float edge1) {
    return smoothStep(Shade.constant(edge0), Shade.constant(edge1));
  }

  public Real smoothStep(float edge0, Real edge1) {
    return smoothStep(Shade.constant(edge0), edge1);
  }

  public Real smoothStep(Real edgeo0, float edge1) {
    return smoothStep(edgeo0, Shade.constant(edge1));
  }

  public Real smoothStep(Real edge0, Real edge1) {
    return function("smoothStep", edge0, edge1, this);
  }

  private Real function(String name, Expression... arguments) {
    return new Real(ImmutableList.copyOf(arguments), NodeType.FunctionNodeType.forFunction(name));
  }

  public static Real PI = new Real(3.14159265359f);
}
