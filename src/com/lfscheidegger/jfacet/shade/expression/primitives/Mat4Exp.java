package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.collect.ImmutableList;
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

  public Mat4Exp(Mat4 vec) {
    super(TYPE, ImmutableList.<Expression>of(), Mat4Evaluators.forConstant(vec));
  }

  public Mat4Exp(ImmutableList<Expression> parents, Evaluator<Mat4> evaluator) {
    super(TYPE, parents, evaluator);
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
}
