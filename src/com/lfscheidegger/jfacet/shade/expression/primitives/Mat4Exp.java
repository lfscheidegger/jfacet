package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Mat4Evaluators;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Vec4Evaluators;
import com.lfscheidegger.jfacet.shade.primitives.Mat4;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.Mat4Like;

public class Mat4Exp extends AbstractExpression<Mat4> implements Mat4Like {

  private static final Type TYPE = Type.MAT4_T;

  public Mat4Exp(Mat4 mat) {
    this(Mat4Evaluators.forConstant(mat));
  }

  public Mat4Exp(Evaluator<Mat4> evaluator) {
    this(ImmutableList.<Expression>of(), evaluator);
  }

  public Mat4Exp(ImmutableList<Expression> parents, Evaluator<Mat4> evaluator) {
    this(GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Mat4Exp(GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Mat4> evaluator) {
    super(TYPE, glSlType, parents, evaluator);
  }

  public Vec4Exp getC0() {
    return get(0);
  }

  public Vec4Exp getC1() {
    return get(1);
  }

  public Vec4Exp getC2() {
    return get(2);
  }

  public Vec4Exp getC3() {
    return get(3);
  }

  public Vec4Exp get(int idx) {
    return new Vec4Exp(ImmutableList.<Expression>of(this), Vec4Evaluators.forMat4Component(idx));
  }

  public Mat4Exp add(float other) { return Shade.add(this, other); }
  public Mat4Exp add(FloatExp other) { return Shade.add(this, other); }
  public Mat4Exp add(Mat4Like other) { return Shade.add(this, other); }

  public Mat4Exp sub(float other) { return Shade.sub(this, other); }
  public Mat4Exp sub(FloatExp other) { return Shade.sub(this, other); }
  public Mat4Exp sub(Mat4Like other) { return Shade.sub(this, other); }

  public Mat4Exp mul(float other) { return Shade.mul(this, other); }
  public Mat4Exp mul(FloatExp other) { return Shade.mul(this, other); }
  public Mat4Exp mul(Mat4Like other) { return Shade.mul(this, other); }

  public Vec4Exp mul(Vec4Exp other) { return Shade.mul(this, other); }

  public Mat4Exp div(float other) { return Shade.div(this, other); }
  public Mat4Exp div(FloatExp other) { return Shade.div(this, other); }
  public Mat4Exp div(Mat4Like other) { return Shade.div(this, other); }

  public Mat4Exp neg() { return Shade.neg(this); }
}
