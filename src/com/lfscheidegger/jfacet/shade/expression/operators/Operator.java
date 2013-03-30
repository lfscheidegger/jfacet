package com.lfscheidegger.jfacet.shade.expression.operators;

/**
 * Wraps a strongly typed binary operation
 */
public interface Operator<LEFT_T, RIGHT_T, RESULT_T> {

  public RESULT_T op(LEFT_T left, RIGHT_T right);
}
