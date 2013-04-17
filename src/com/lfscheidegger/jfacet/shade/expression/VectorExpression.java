package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ComponentEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ConstantEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.primitives.Vector;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.VectorLike;

public final class VectorExpression extends AbstractExpression<Vector> implements VectorLike {

  public VectorExpression(Vector vec) {
    this(Type.getVecTypeForDimension(vec.getDimension()), new ConstantEvaluator<Vector>(vec));
  }

  public VectorExpression(Type type, Evaluator<Vector> evaluator) {
    this(type, ImmutableList.<Expression>of(), evaluator);
  }

  public VectorExpression(Type type, ImmutableList<Expression> parents, Evaluator<Vector> evaluator) {
    this(type, GlSlType.DEFAULT_T, parents, evaluator);
  }

  public VectorExpression(Type type, GlSlType glSlType, Evaluator<Vector> evaluator) {
    this(type, glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  public VectorExpression(Type type, GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Vector> evaluator) {
    super(type, glSlType, parents, evaluator);
  }

  public FloatExpression getX() {
    return get(0);
  }

  public FloatExpression getY() {
    return get(1);
  }

  public FloatExpression getZ() {
    return get(2);
  }

  public FloatExpression getW() {
    return get(3);
  }

  public FloatExpression get(int idx) {
    Preconditions.checkState(idx < getType().getDimension());

    return new FloatExpression(ImmutableList.<Expression>of(this), new ComponentEvaluator<Float>(idx));
  }

  public VectorExpression add(float other) { return Shade.add(this, other); }
  public VectorExpression add(FloatExpression other) { return Shade.add(this, other); }
  public VectorExpression add(VectorLike other) { return Shade.add(this, other); }

  public VectorExpression sub(float other) { return Shade.sub(this, other); }
  public VectorExpression sub(FloatExpression other) { return Shade.sub(this, other); }
  public VectorExpression sub(VectorLike other) { return Shade.sub(this, other); }

  public VectorExpression mul(float other) { return Shade.mul(this, other); }
  public VectorExpression mul(FloatExpression other) { return Shade.mul(this, other); }
  public VectorExpression mul(VectorLike other) { return Shade.mul(this, other); }

  public VectorExpression div(float other) { return Shade.div(this, other); }
  public VectorExpression div(FloatExpression other) { return Shade.div(this, other); }
  public VectorExpression div(VectorLike other) { return Shade.div(this, other); }

  public VectorExpression cross(VectorLike other) { return Shade.cross(this, other); }

  public VectorExpression normalize() { return Shade.normalize(this); }

  public FloatExpression dot(VectorLike other) { return Shade.dot(this, other); }
}
