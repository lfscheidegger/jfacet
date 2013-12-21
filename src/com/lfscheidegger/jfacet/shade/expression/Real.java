package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.facet.AttributeBuffer;

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

  public Real sin() {
    return new Real(ImmutableList.<Expression>of(this), NodeType.FunctionNodeType.forFunction("sin"));
  }

  public Real cos() {
    return new Real(ImmutableList.<Expression>of(this), NodeType.FunctionNodeType.forFunction("cos"));
  }

  public Real asin() {
    return new Real(ImmutableList.<Expression>of(this), NodeType.FunctionNodeType.forFunction("asin"));
  }

  public Real acos() {
    return new Real(ImmutableList.<Expression>of(this), NodeType.FunctionNodeType.forFunction("acos"));
  }

  public Real atan() {
    return new Real(ImmutableList.<Expression>of(this), NodeType.FunctionNodeType.forFunction("atan"));
  }

  public Real sqrt() {
    return new Real(ImmutableList.<Expression>of(this), NodeType.FunctionNodeType.forFunction("sqrt"));
  }

  private static final float DEGREES_TO_RADIANS = 0.01745329251f;
  public Real radians() {
    return mul(DEGREES_TO_RADIANS);
  }

  private static final float RADIANS_TO_DEGREES = 57.2957795131f;
  public Real degrees() {
    return mul(RADIANS_TO_DEGREES);
  }

  public static Real PI = new Real(3.14159265359f);
}
