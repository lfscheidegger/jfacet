package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.*;
import com.lfscheidegger.jfacet.shade.primitives.Mat3;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.Mat3Like;

public class Mat3Exp extends AbstractExpression<Mat3> implements Mat3Like {

  private static final Type TYPE = Type.MAT3_T;

  public Mat3Exp(Mat3 mat) {
    this(new ConstantEvaluator<Mat3>(mat));
  }

  public Mat3Exp(Evaluator<Mat3> evaluator) {
    this(ImmutableList.<Expression>of(), evaluator);
  }

  public Mat3Exp(ImmutableList<Expression> parents, Evaluator<Mat3> evaluator) {
    this(GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Mat3Exp(GlSlType glSlType, Evaluator<Mat3> evaluator) {
    this(glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  public Mat3Exp(GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Mat3> evaluator) {
    super(TYPE, glSlType, parents, evaluator);
  }

  public Vec3Exp getC0() {
    return get(0);
  }

  public Vec3Exp getC1() {
    return get(1);
  }

  public Vec3Exp getC2() {
    return get(2);
  }

  public Vec3Exp get(int idx) {
    return new Vec3Exp(ImmutableList.<Expression>of(this), new ComponentEvaluator<Vec3>(idx));
  }

  public Mat3Exp add(float other) { return Shade.add(this, other); }
  public Mat3Exp add(FloatExp other) { return Shade.add(this, other); }
  public Mat3Exp add(Mat3Like other) { return Shade.add(this, other); }

  public Mat3Exp sub(float other) { return Shade.sub(this, other); }
  public Mat3Exp sub(FloatExp other) { return Shade.sub(this, other); }
  public Mat3Exp sub(Mat3Like other) { return Shade.sub(this, other); }

  public Mat3Exp mul(float other) { return Shade.mul(this, other); }
  public Mat3Exp mul(FloatExp other) { return Shade.mul(this, other); }
  public Mat3Exp mul(Mat3Like other) { return Shade.mul(this, other); }

  public Vec3Exp mul(Vec3Exp other) { return Shade.mul(this, other); }

  public Mat3Exp div(float other) { return Shade.div(this, other); }
  public Mat3Exp div(FloatExp other) { return Shade.div(this, other); }
  public Mat3Exp div(Mat3Like other) { return Shade.div(this, other); }

  public Mat3Exp neg() { return Shade.neg(this); }
}
