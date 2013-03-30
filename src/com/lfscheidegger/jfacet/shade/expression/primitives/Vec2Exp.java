package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Vec2Evaluators;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;

public class Vec2Exp extends AbstractExpression<Vec2> {

  private static final Type TYPE = Type.VEC2_T;

  public Vec2Exp(Vec2 vec) {
    super(TYPE, ImmutableList.<Expression>of(), Vec2Evaluators.forConstant(vec));
  }

  public Vec2Exp(ImmutableList<Expression> parents, Evaluator<Vec2> evaluator) {
    super(TYPE, parents, evaluator);
  }
}
