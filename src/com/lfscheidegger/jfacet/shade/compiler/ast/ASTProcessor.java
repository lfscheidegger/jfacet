package com.lfscheidegger.jfacet.shade.compiler.ast;

import com.lfscheidegger.jfacet.shade.expression.Expression;

public interface ASTProcessor {

  public Expression process(Expression expression);
}
