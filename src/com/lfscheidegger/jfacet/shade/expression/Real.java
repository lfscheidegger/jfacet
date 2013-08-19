package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.*;
import com.lfscheidegger.jfacet.shade.expression.operators.BooleanOperators;
import com.lfscheidegger.jfacet.shade.expression.operators.RealOperators;

/**
 * {code Expression} for floats
 */
public final class Real extends AbstractExpression<Float> implements SupportsBasicArithmetic<Real> {

  public Real(float c) {
    this(ImmutableList.<Expression>of(), new ConstantEvaluator<Float>(c));
  }

  public Real(ImmutableList<Expression> parents, Evaluator<Float> evaluator) {
    this(GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Real(GlSlType glSlType, Evaluator<Float> evaluator) {
    this(glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  private Real(GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Float> evaluator) {
    super(Type.FLOAT_T, glSlType, parents, evaluator);
  }

  @Override
  public Real getExpressionForTernaryOperator(Bool condition, Expression<Float> elseExpression) {
    return new Real(
        ImmutableList.<Expression>of(condition, this, elseExpression),
        new TernaryOperationEvaluator<Float>());
  }

  @Override
  public Real add(Real right) {
    return new Real(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Float, Float, Float>(RealOperators.forAddition()));
  }

  @Override
  public Real add(float right) {
    return add(new Real(right));
  }

  @Override
  public Real sub(Real right) {
    return new Real(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Float, Float, Float>(RealOperators.forSubtraction()));
  }

  @Override
  public Real sub(float right) {
    return sub(new Real(right));
  }

  @Override
  public Real mul(Real right) {
    return new Real(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Float, Float, Float>(RealOperators.forMultiplication()));
  }

  @Override
  public Real mul(float right) {
    return mul(new Real(right));
  }

  @Override
  public Real div(Real right) {
    return new Real(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Float, Float, Float>(RealOperators.forDivision()));
  }

  @Override
  public Real div(float right) {
    return div(new Real(right));
  }

  @Override
  public Real neg() {
    return new Real(ImmutableList.<Expression>of(this), new NegationEvaluator<Float>());
  }

  public Bool isGreaterThan(Real right) {
    return new Bool(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Float, Float, Boolean>(BooleanOperators.forGreaterThanReal()));
  }

  public Bool isGreaterThan(float right) {
    return isGreaterThan(new Real(right));
  }

  public Bool isGreaterThanOrEqual(Real right) {
    return new Bool(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Float, Float, Boolean>(BooleanOperators.forGreaterThanOrEqualReal()));
  }

  public Bool isGreaterThanOrEqual(float right) {
    return isGreaterThanOrEqual(new Real(right));
  }

  public Bool isLessThan(Real right) {
    return new Bool(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Float, Float, Boolean>(BooleanOperators.forLessThanReal()));
  }

  public Bool isLessThan(float right) {
    return isLessThan(new Real(right));
  }

  public Bool isLessThanOrEqual(Real right) {
    return new Bool(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Float, Float, Boolean>(BooleanOperators.forLessThanOrEqualReal()));
  }

  public Bool isLessThanOrEqual(float right) {
    return isLessThanOrEqual(new Real(right));
  }

  public Bool isEqual(Real right) {
    return new Bool(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Float, Float, Boolean>(BooleanOperators.forEqualsReal()));
  }

  public Bool isEqual(float right) {
    return isEqual(new Real(right));
  }

  public Bool isNotEqual(Real right) {
    return new Bool(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Float, Float, Boolean>(BooleanOperators.forNotEqualsReal()));
  }

  public Bool isNotEqual(float right) {
    return isNotEqual(new Real(right));
  }
}
