package com.lfscheidegger.jfacet.shade.expression.operators;

public abstract class NamedOperator<LEFT_T, RIGHT_T, RESULT_T> implements Operator<LEFT_T, RIGHT_T, RESULT_T> {

  private final String mOperatorSymbol;

  public NamedOperator(String operatorSymbol) {
    mOperatorSymbol = operatorSymbol;
  }

  @Override
  public String getOperatorSymbol() {
    return mOperatorSymbol;
  }
}
