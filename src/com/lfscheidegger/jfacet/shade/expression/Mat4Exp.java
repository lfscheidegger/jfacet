package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Mat4Evaluators;
import com.lfscheidegger.jfacet.shade.primitives.Mat4;

public class Mat4Exp extends AbstractExpression<Mat4> {

  private static final Type TYPE = Type.MAT4_T;

  public Mat4Exp(Mat4 vec) {
    super(TYPE, ImmutableList.<Expression>of(), Mat4Evaluators.forConstant(vec));
  }

  public Mat4Exp(ImmutableList<Expression> parents, Evaluator<Mat4> evaluator) {
    super(TYPE, parents, evaluator);
  }
}
