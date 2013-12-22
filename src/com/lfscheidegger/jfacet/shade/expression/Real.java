package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.facet.AttributeBuffer;
import com.lfscheidegger.jfacet.shade.Shade;

/**
 * {code Expression} for floats
 */
public final class Real extends AbstractExpression {

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

  public Real exp(float rhs) { return exp(Shade.constant(rhs)); }

  public Real exp(Real rhs) { return function("exp", this, rhs); }

  public Real log(float rhs) { return log(Shade.constant(rhs)); }

  public Real log(Real rhs) { return function("log", this, rhs); }

  public Real exp2(float rhs) { return exp2(Shade.constant(rhs)); }

  public Real exp2(Real rhs) { return function("exp2", this, rhs); }

  public Real log2(float rhs) { return log2(Shade.constant(rhs)); }

  public Real log2(Real rhs) { return function("log2", this, rhs); }

  public Real sqrt() { return function("sqrt", this); }

  public Real inversesqrt() { return function("inversesqrt", this); }

  public Real mod(float rhs) {
    return mod(Shade.constant(rhs));
  }

  public Real mod(Real rhs) {
    return new Real(ImmutableList.<Expression>of(this, rhs), NodeType.FunctionNodeType.forFunction("mod"));
  }

  public Real min(float rhs) {
    return min(Shade.constant(rhs));
  }

  public Real min(Real rhs) {
    return new Real(ImmutableList.<Expression>of(this, rhs), NodeType.FunctionNodeType.forFunction("min"));
  }

  public Real max(float rhs) {
    return max(Shade.constant(rhs));
  }

  public Real max(Real rhs) {
    return new Real(ImmutableList.<Expression>of(this, rhs), NodeType.FunctionNodeType.forFunction("max"));
  }

  private Real function(String name, Expression... parameters) {
    return new Real(ImmutableList.copyOf(parameters), NodeType.FunctionNodeType.forFunction(name));
  }

  public static Real PI = new Real(3.14159265359f);
}
