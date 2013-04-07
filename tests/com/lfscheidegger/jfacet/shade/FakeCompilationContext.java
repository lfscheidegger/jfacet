package com.lfscheidegger.jfacet.shade;

import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public class FakeCompilationContext implements CompilationContext {

  @Override
  public String getExpressionName(Expression exp) {
    return "a";
  }

  @Override
  public int getScopeLevel() {
    return 0;
  }

  @Override
  public void pushScope() {}
  @Override
  public void popScope() {}
}
