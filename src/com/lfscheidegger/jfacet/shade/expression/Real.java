package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.facet.AttributeBuffer;
import com.lfscheidegger.jfacet.shade.GlSlQualifier;

/**
 * {code Expression} for floats
 */
public final class Real extends AbstractExpression<Float> {

  private final Optional<AttributeBuffer> mAttributeBuffer;

  public Real(float c) {
    super(c);
    mAttributeBuffer = Optional.absent();
  }

  public Real(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType);
    mAttributeBuffer = Optional.absent();
  }

  public Real(AttributeBuffer attributeBuffer) {
    super(GlSlQualifier.ATTRIBUTE_T);
    mAttributeBuffer = Optional.of(attributeBuffer);
  }

  @Override
  public Real getExpressionForTernaryOperator(Bool condition, Expression<Float> elseExpression) {
    return new Real(
        ImmutableList.<Expression>of(condition, this, elseExpression),
        NodeType.TERNARY);
  }

  @Override
  public Optional<AttributeBuffer> getAttributeBuffer() {
    return mAttributeBuffer;
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
}
