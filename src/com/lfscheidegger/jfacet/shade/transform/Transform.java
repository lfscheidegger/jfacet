package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.MatrixExpression;
import com.lfscheidegger.jfacet.shade.expression.VectorExpression;

public interface Transform {

  /**
   * Unfortunately we must support {@code Expression} instead of {@code VectorExpression} here
   * because of FloatExpressions
   */
  public VectorExpression apply(Expression exp);

  public Transform apply(Transform other);

  public MatrixExpression getMatrix();
}
