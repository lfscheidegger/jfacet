package com.lfscheidegger.jfacet.shade.expression.operations;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.types.SupportsBasicArithmetic;

public class NegExpression extends AbstractExpression {

  private final Type mParentType;

  public NegExpression(Expression parent) {
    super(parent.getType(), ImmutableList.<Expression>of(parent));

    mParentType = parent.getType();
  }

  @Override
  public Object evaluate() {
    if (mParentType == Type.FLOAT_T) {
      return - (Float)getParents().get(0).evaluate();
    } else {
      return ((SupportsBasicArithmetic)getParents().get(0).evaluate()).neg();
    }
  }
}
