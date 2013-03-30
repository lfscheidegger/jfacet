package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Mat2Evaluators;
import com.lfscheidegger.jfacet.shade.primitives.Mat2;

public class Mat2Exp extends AbstractExpression<Mat2> {

  private static final Type TYPE = Type.MAT2_T;

  public Mat2Exp(Mat2 vec) {
    super(TYPE, ImmutableList.<Expression>of(), Mat2Evaluators.forConstant(vec));
  }

  public Mat2Exp(ImmutableList<Expression> parents, Evaluator<Mat2> evaluator) {
    super(TYPE, parents, evaluator);
  }
}
