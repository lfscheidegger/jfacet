package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.BinaryOperationEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ConstantEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.TernaryOperationEvaluator;
import com.lfscheidegger.jfacet.shade.expression.operators.BooleanOperators;

/**
 * {@code Expression} for boolean values
 */
public final class Bool extends AbstractExpression<Boolean> {

  static class TernaryElse<T extends Expression> {

    private final Bool mCondition;
    private final T mIfExpression;

    public TernaryElse(Bool condition, T ifExpression) {
      mCondition = condition;
      mIfExpression = ifExpression;
    }

    @SuppressWarnings("unchecked")
    public T else_(T elseExpression) {
      return (T)mIfExpression.getExpressionForTernaryOperator(mCondition, elseExpression);
    }
  }

  public Bool(boolean c) {
    this(new ConstantEvaluator<Boolean>(c));
  }

  public Bool(Evaluator<Boolean> evaluator) {
    this(ImmutableList.<Expression>of(), evaluator);
  }

  public Bool(ImmutableList<Expression> parents, Evaluator<Boolean> evaluator) {
    this(GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Bool(GlSlType glSlType, Evaluator<Boolean> evaluator) {
    this(glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  public Bool(GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Boolean> evaluator) {
    super(Type.BOOL_T, glSlType, parents, evaluator);
  }

  @Override
  public Bool getExpressionForTernaryOperator(Bool condition, Expression<Boolean> elseExpression) {
    return new Bool(ImmutableList.<Expression>of(condition, this, elseExpression),
        new TernaryOperationEvaluator<Boolean>());
  }

  public Bool and(Bool right) {
    return new Bool(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Boolean, Boolean, Boolean>(BooleanOperators.forAnd()));
  }

  public Bool and(boolean right) {
    return and(new Bool(right));
  }

  public Bool or(Bool right) {
    return new Bool(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Boolean, Boolean, Boolean>(BooleanOperators.forOr()));
  }

  public Bool or(boolean right) {
    return or(new Bool(right));
  }

  public Bool xor(Bool right) {
    return new Bool(
        ImmutableList.<Expression>of(this, right),
        new BinaryOperationEvaluator<Boolean, Boolean, Boolean>(BooleanOperators.forXor()));
  }

  public Bool xor(boolean right) {
    return xor(new Bool(right));
  }

  public <T extends Expression> TernaryElse<T> if_(T ifExpression) {
    return new TernaryElse<T>(this, ifExpression);
  }
}
