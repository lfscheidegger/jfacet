package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ComponentEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ConstantEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.primitives.Mat2;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.Mat2Like;

public class Mat2Exp extends AbstractExpression<Mat2> implements Mat2Like {

  private static final Type TYPE = Type.MAT2_T;

  public Mat2Exp(Mat2 mat) {
    this(new ConstantEvaluator<Mat2>(mat));
  }

  public Mat2Exp(Evaluator<Mat2> evaluator) {
    this(ImmutableList.<Expression>of(), evaluator);
  }

  public Mat2Exp(ImmutableList<Expression> parents, Evaluator<Mat2> evaluator) {
    this(GlSlType.DEFAULT_T, parents, evaluator);
  }

  public Mat2Exp(GlSlType glSlType, Evaluator<Mat2> evaluator) {
    this(glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  public Mat2Exp(GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Mat2> evaluator) {
    super(TYPE, glSlType, parents, evaluator);
  }

  public Vec2Exp getC0() {
    return get(0);
  }

  public Vec2Exp getC1() {
    return get(1);
  }

  public Vec2Exp get(int idx) {
    return new Vec2Exp(ImmutableList.<Expression>of(this), new ComponentEvaluator<Vec2>(idx));
  }

  public Mat2Exp add(float other) { return Shade.add(this, other); }
  public Mat2Exp add(FloatExp other) { return Shade.add(this, other); }
  public Mat2Exp add(Mat2Like other) { return Shade.add(this, other); }

  public Mat2Exp sub(float other) { return Shade.sub(this, other); }
  public Mat2Exp sub(FloatExp other) { return Shade.sub(this, other); }
  public Mat2Exp sub(Mat2Like other) { return Shade.sub(this, other); }

  public Mat2Exp mul(float other) { return Shade.mul(this, other); }
  public Mat2Exp mul(FloatExp other) { return Shade.mul(this, other); }
  public Mat2Exp mul(Mat2Like other) { return Shade.mul(this, other); }

  public Vec2Exp mul(Vec2Exp other) { return Shade.mul(this, other); }

  public Mat2Exp div(float other) { return Shade.div(this, other); }
  public Mat2Exp div(FloatExp other) { return Shade.div(this, other); }
  public Mat2Exp div(Mat2Like other) { return Shade.div(this, other); }

  public Mat2Exp neg() { return Shade.neg(this); }
}
