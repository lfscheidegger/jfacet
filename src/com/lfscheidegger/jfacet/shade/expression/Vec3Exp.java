package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Vec3Evaluators;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;

public class Vec3Exp extends AbstractExpression<Vec3> {

  private static final Type TYPE = Type.VEC3_T;

  public Vec3Exp(Vec3 vec) {
    super(TYPE, ImmutableList.<Expression>of(), Vec3Evaluators.forConstant(vec));
  }

  public Vec3Exp(ImmutableList<Expression> parents, Evaluator<Vec3> evaluator) {
    super(TYPE, parents, evaluator);
  }
}