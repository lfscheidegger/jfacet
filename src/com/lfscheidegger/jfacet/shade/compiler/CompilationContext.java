package com.lfscheidegger.jfacet.shade.compiler;

import com.lfscheidegger.jfacet.shade.expression.Expression;

public interface CompilationContext {

  public String getExpressionName(Expression exp);

  public int getScopeLevel();

  public void pushScope();

  public void popScope();
}
