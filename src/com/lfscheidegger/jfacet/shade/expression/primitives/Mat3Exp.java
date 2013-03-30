package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Mat3Evaluators;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Vec3Evaluators;
import com.lfscheidegger.jfacet.shade.primitives.Mat3;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.Mat3Like;

public class Mat3Exp extends AbstractExpression<Mat3> implements Mat3Like {

  private static final Type TYPE = Type.MAT3_T;

  public Mat3Exp(Mat3 vec) {
    super(TYPE, ImmutableList.<Expression>of(), Mat3Evaluators.forConstant(vec));
  }

  public Mat3Exp(ImmutableList<Expression> parents, Evaluator<Mat3> evaluator) {
    super(TYPE, parents, evaluator);
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
    return new Vec3Exp(ImmutableList.<Expression>of(this), Vec3Evaluators.forMat3Component(idx));
  }
}