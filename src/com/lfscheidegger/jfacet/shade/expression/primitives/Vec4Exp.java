package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Vec4Evaluators;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.Vec4Like;

public class Vec4Exp extends AbstractExpression<Vec4> implements Vec4Like {

  private static final Type TYPE = Type.VEC4_T;

  public Vec4Exp(Vec4 vec) {
    super(TYPE, ImmutableList.<Expression>of(), Vec4Evaluators.forConstant(vec));
  }

  public Vec4Exp(ImmutableList<Expression> parents, Evaluator<Vec4> evaluator) {
    super(TYPE, parents, evaluator);
  }
}
