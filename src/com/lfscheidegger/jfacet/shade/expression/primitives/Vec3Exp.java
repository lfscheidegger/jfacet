package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
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
    this(TYPE, GlSlType.DEFAULT_T, ImmutableList.<Expression>of(), Vec3Evaluators.forConstant(vec));
  }

  public Vec3Exp(ImmutableList<Expression> parents, Evaluator<Vec3> evaluator) {
    this(TYPE, GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Vec3Exp(Type type, GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Vec3> evaluator) {
    super(type, glSlType, parents, evaluator);
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

  public FloatExp get(int idx) {
    return new FloatExp(ImmutableList.<Expression>of(this), FloatEvaluators.forVec3Component(idx));
  }

  public Vec3Exp add(float other) { return Shade.add(this, other); }
  public Vec3Exp add(FloatExp other) { return Shade.add(this, other); }
  public Vec3Exp add(Vec3Like other) { return Shade.add(this, other); }

  public Vec3Exp sub(float other) { return Shade.sub(this, other); }
  public Vec3Exp sub(FloatExp other) { return Shade.sub(this, other); }
  public Vec3Exp sub(Vec3Like other) { return Shade.sub(this, other); }

  public Vec3Exp mul(float other) { return Shade.mul(this, other); }
  public Vec3Exp mul(FloatExp other) { return Shade.mul(this, other); }
  public Vec3Exp mul(Vec3Like other) { return Shade.mul(this, other); }

  public Vec3Exp div(float other) { return Shade.div(this, other); }
  public Vec3Exp div(FloatExp other) { return Shade.div(this, other); }
  public Vec3Exp div(Vec3Like other) { return Shade.div(this, other); }

  public Vec3Exp neg() { return Shade.neg(this); }

  public FloatExp dot(Vec3Like other) { return Shade.dot(this, other); }

  public Vec3Exp normalize() { return com.lfscheidegger.jfacet.shade.Math.normalize(this); }

  public Vec3Exp cross(Vec3Like other) { return Shade.cross(this, other); }
}
