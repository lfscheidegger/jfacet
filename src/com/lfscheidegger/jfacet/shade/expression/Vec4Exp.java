package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Vec4Evaluators;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

public class Vec4Exp extends AbstractExpression<Vec4> {

  private static final Type TYPE = Type.VEC4_T;

  public Vec4Exp(Vec4 vec) {
    super(TYPE, ImmutableList.<Expression>of(), Vec4Evaluators.forConstant(vec));
  }

  public Vec4Exp(ImmutableList<Expression> parents, Evaluator<Vec4> evaluator) {
    super(TYPE, parents, evaluator);
  }
}
