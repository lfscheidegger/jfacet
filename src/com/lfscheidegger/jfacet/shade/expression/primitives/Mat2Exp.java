package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Mat2Evaluators;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Vec2Evaluators;
import com.lfscheidegger.jfacet.shade.primitives.Mat2;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.Mat2Like;

public class Mat2Exp extends AbstractExpression<Mat2> implements Mat2Like {

  private static final Type TYPE = Type.MAT2_T;

  public Mat2Exp(Mat2 mat) {
    this(TYPE, GlSlType.DEFAULT_T, ImmutableList.<Expression>of(), Mat2Evaluators.forConstant(mat));
  }

  public Mat2Exp(ImmutableList<Expression> parents, Evaluator<Mat2> evaluator) {
    this(TYPE, GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Mat2Exp(Type type, GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Mat2> evaluator) {
    super(type, glSlType, parents, evaluator);
  }

  public Vec2Exp getC0() {
    return get(0);
  }

  public Vec2Exp getC1() {
    return get(1);
  }

  public Vec2Exp get(int idx) {
    return new Vec2Exp(ImmutableList.<Expression>of(this), Vec2Evaluators.forMat2Component(idx));
  }

  public Mat2Exp add(float other) { return Shade.add(this, other); }
  public Mat2Exp add(FloatExp other) { return Shade.add(this, other); }
  public Mat2Exp add(Mat2Exp other) { return Shade.add(this, other); }

  public Mat2Exp sub(float other) { return Shade.sub(this, other); }
  public Mat2Exp sub(FloatExp other) { return Shade.sub(this, other); }
  public Mat2Exp sub(Mat2Exp other) { return Shade.sub(this, other); }

  public Mat2Exp mul(float other) { return Shade.mul(this, other); }
  public Mat2Exp mul(FloatExp other) { return Shade.mul(this, other); }
  public Mat2Exp mul(Mat2Exp other) { return Shade.mul(this, other); }

  public Mat2Exp div(float other) { return Shade.div(this, other); }
  public Mat2Exp div(FloatExp other) { return Shade.div(this, other); }
  public Mat2Exp div(Mat2Exp other) { return Shade.div(this, other); }

  public Mat2Exp neg() { return Shade.neg(this); }
}
