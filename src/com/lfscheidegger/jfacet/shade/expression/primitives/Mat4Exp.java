package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Mat4Evaluators;
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
}
