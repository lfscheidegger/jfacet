package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.*;
import com.lfscheidegger.jfacet.shade.expression.operators.FloatOperators;

/**
 * {code Expression} for floats
 */
public final class Real extends AbstractExpression<Float> {

  public Real(float c) {
    this(new ConstantEvaluator<Float>(c));
  }

  public Real(Evaluator<Float> evaluator) {
    this(ImmutableList.<Expression>of(), evaluator);
  }

  public Real(ImmutableList<Expression> parents, Evaluator<Float> evaluator) {
    this(GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Real(GlSlType glSlType, Evaluator<Float> evaluator) {
    this(glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  public Real(GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Float> evaluator) {
    super(Type.FLOAT_T, glSlType, parents, evaluator);
  }

  public Real add(Real right) {
    return new Real(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Float, Float, Float>(FloatOperators.forAddition()));
  }

  public Real add(float right) {
    return add(new Real(right));
  }

  public Real sub(Real right) {
    return new Real(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Float, Float, Float>(FloatOperators.forSubtraction()));
  }

  public Real sub(float right) {
    return sub(new Real(right));
  }

  public Real mul(Real right) {
    return new Real(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Float, Float, Float>(FloatOperators.forMultiplication()));
  }

  public Real mul(float right) {
    return mul(new Real(right));
  }

  public Real div(Real right) {
    return new Real(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Float, Float, Float>(FloatOperators.forDivision()));
  }

  public Real div(float right) {
    return div(new Real(right));
  }

  public Real neg() {
    return new Real(ImmutableList.<Expression>of(this), new NegationEvaluator<Float>());
  }

  public Real sqrt() {
    return new Real(ImmutableList.<Expression>of(this), new FunctionEvaluator<Float>(Type.FLOAT_T, "sqrt") {
      @Override
      public Float evaluate(Expression expression) {
        Real parent = (Real)expression.getParents().get(0);
        return (float)java.lang.Math.sqrt(parent.evaluate());
      }
    });
  }

  public Real sin() {
    return new Real(ImmutableList.<Expression>of(this), new FunctionEvaluator<Float>(Type.FLOAT_T, "sin") {
      @Override
      public Float evaluate(Expression expression) {
        Real parent = (Real)expression.getParents().get(0);
        return (float)java.lang.Math.sin(parent.evaluate());
      }
    });
  }

  public Real cos() {
    return new Real(ImmutableList.<Expression>of(this), new FunctionEvaluator<Float>(Type.FLOAT_T, "cos") {
      @Override
      public Float evaluate(Expression expression) {
        Real parent = (Real)expression.getParents().get(0);
        return (float)java.lang.Math.cos(parent.evaluate());
      }
    });
  }

  public Real radians() {
    return this.mul((float)(java.lang.Math.PI / 180.0));
  }
}
