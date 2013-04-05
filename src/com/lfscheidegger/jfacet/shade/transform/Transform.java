package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.expression.Expression;

public interface Transform {

  public Expression apply(Expression exp);
}
