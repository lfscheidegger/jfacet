package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.FloatEvaluators;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Vec3Evaluators;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.Vec3Like;

public class Vec3Exp extends AbstractExpression<Vec3> implements Vec3Like {

  private static final Type TYPE = Type.VEC3_T;

  public Vec3Exp(Vec3 vec) {
    super(TYPE, ImmutableList.<Expression>of(), Vec3Evaluators.forConstant(vec));
  }

  public Vec3Exp(ImmutableList<Expression> parents, Evaluator<Vec3> evaluator) {
    super(TYPE, parents, evaluator);
  }

  public FloatExp getX() {
    return new FloatExp(ImmutableList.<Expression>of(this), FloatEvaluators.forVec3Component(0));
  }

  public FloatExp getY() {
    return new FloatExp(ImmutableList.<Expression>of(this), FloatEvaluators.forVec3Component(1));
  }

  public FloatExp getZ() {
    return new FloatExp(ImmutableList.<Expression>of(this), FloatEvaluators.forVec3Component(2));
  }

  public FloatExp get(int idx) {
    return new FloatExp(ImmutableList.<Expression>of(this), FloatEvaluators.forVec3Component(idx));
  }
}
