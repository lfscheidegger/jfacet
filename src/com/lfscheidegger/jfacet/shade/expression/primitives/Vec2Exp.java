package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.FloatEvaluators;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Vec2Evaluators;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.Vec2Like;

public class Vec2Exp extends AbstractExpression<Vec2> implements Vec2Like {

  private static final Type TYPE = Type.VEC2_T;

  public Vec2Exp(Vec2 vec) {
    this(TYPE, GlSlType.DEFAULT_T, ImmutableList.<Expression>of(), Vec2Evaluators.forConstant(vec));
  }

  public Vec2Exp(ImmutableList<Expression> parents, Evaluator<Vec2> evaluator) {
    this(TYPE, GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Vec2Exp(Type type, GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Vec2> evaluator) {
    super(type, glSlType, parents, evaluator);
  }

  public FloatExp getX() {
    return get(0);
  }

  public FloatExp getY() {
    return get(1);
  }

  public FloatExp get(int idx) {
    return new FloatExp(ImmutableList.<Expression>of(this), FloatEvaluators.forVec2Component(idx));
  }

  public Vec2Exp add(float other) { return Shade.add(this, other); }
  public Vec2Exp add(FloatExp other) { return Shade.add(this, other); }
  public Vec2Exp add(Vec2Exp other) { return Shade.add(this, other); }

  public Vec2Exp sub(float other) { return Shade.sub(this, other); }
  public Vec2Exp sub(FloatExp other) { return Shade.sub(this, other); }
  public Vec2Exp sub(Vec2Exp other) { return Shade.sub(this, other); }

  public Vec2Exp mul(float other) { return Shade.mul(this, other); }
  public Vec2Exp mul(FloatExp other) { return Shade.mul(this, other); }
  public Vec2Exp mul(Vec2Exp other) { return Shade.mul(this, other); }

  public Vec2Exp div(float other) { return Shade.div(this, other); }
  public Vec2Exp div(FloatExp other) { return Shade.div(this, other); }
  public Vec2Exp div(Vec2Exp other) { return Shade.div(this, other); }

  public Vec2Exp neg() { return Shade.neg(this); }
}
