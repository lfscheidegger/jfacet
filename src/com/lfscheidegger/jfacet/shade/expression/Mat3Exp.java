package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Mat3Evaluators;
import com.lfscheidegger.jfacet.shade.primitives.Mat3;

public class Mat3Exp extends AbstractExpression<Mat3> {

  private static final Type TYPE = Type.MAT3_T;

  public Mat3Exp(Mat3 vec) {
    super(TYPE, ImmutableList.<Expression>of(), Mat3Evaluators.forConstant(vec));
  }

  public Mat3Exp(ImmutableList<Expression> parents, Evaluator<Mat3> evaluator) {
    super(TYPE, parents, evaluator);
  }
}
