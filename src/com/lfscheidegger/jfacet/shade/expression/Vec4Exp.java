package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ComponentEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ConstantEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.Vec4Like;

public class Vec4Exp extends AbstractExpression<Vec4> implements Vec4Like, VectorExpression<Vec4> {

  private static final Type TYPE = Type.VEC4_T;

  public Vec4Exp(Vec4 vec) {
    this(new ConstantEvaluator<Vec4>(vec));
  }

  public Vec4Exp(Evaluator<Vec4> evaluator) {
    this(ImmutableList.<Expression>of(), evaluator);
  }

  public Vec4Exp(ImmutableList<Expression> parents, Evaluator<Vec4> evaluator) {
    this(GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Vec4Exp(GlSlType glSlType, Evaluator<Vec4> evaluator) {
    this(glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  public Vec4Exp(GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Vec4> evaluator) {
    super(TYPE, glSlType, parents, evaluator);
  }

  public FloatExp getX() {
    return get(0);
  }

  public FloatExp getY() {
    return get(1);
  }

  public FloatExp getZ() {
    return get(2);
  }

  public FloatExp getW() {
    return get(3);
  }

  public FloatExp get(int idx) {
    return new FloatExp(ImmutableList.<Expression>of(this), new ComponentEvaluator<Float>(idx));
  }

  public Vec4Exp add(float other) { return Shade.add(this, other); }
  public Vec4Exp add(FloatExp other) { return Shade.add(this, other); }
  public Vec4Exp add(Vec4Like other) { return Shade.add(this, other); }

  public Vec4Exp sub(float other) { return Shade.sub(this, other); }
  public Vec4Exp sub(FloatExp other) { return Shade.sub(this, other); }
  public Vec4Exp sub(Vec4Like other) { return Shade.sub(this, other); }

  public Vec4Exp mul(float other) { return Shade.mul(this, other); }
  public Vec4Exp mul(FloatExp other) { return Shade.mul(this, other); }
  public Vec4Exp mul(Vec4Like other) { return Shade.mul(this, other); }

  public Vec4Exp div(float other) { return Shade.div(this, other); }
  public Vec4Exp div(FloatExp other) { return Shade.div(this, other); }
  public Vec4Exp div(Vec4Like other) { return Shade.div(this, other); }

  public Vec4Exp neg() { return Shade.neg(this); }

  public FloatExp dot(Vec4Like other) { return Shade.dot(this, other); }

  public Vec4Exp normalize() { return com.lfscheidegger.jfacet.shade.Math.normalize(this); }
}
