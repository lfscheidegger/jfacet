package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ConstantEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;

/**
 * {@code Expression} for boolean values
 */
public final class Bool extends AbstractExpression<Boolean> {

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
}
