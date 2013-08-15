package com.lfscheidegger.jfacet.shade.expression;

public interface MatrixExpression<MATRIX_T, VECTOR_T> extends SupportsBasicArithmetic<MATRIX_T> {

  public VECTOR_T get(int idx);

  public MATRIX_T add(Real right);

  public MATRIX_T sub(Real right);

  public MATRIX_T mul(Real right);

  public MATRIX_T div(Real right);

  public VECTOR_T transform(VECTOR_T right);
}
