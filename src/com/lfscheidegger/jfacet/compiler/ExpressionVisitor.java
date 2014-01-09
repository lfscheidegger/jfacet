// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.compiler;

import com.lfscheidegger.jfacet.shade.expression.Expression;

import java.util.List;

public abstract class ExpressionVisitor {

  private final Expression mRoot;

  public ExpressionVisitor(Expression root) {
    mRoot = root;
  }

  private void runInternal(Expression expression) {
    for (Expression parent : (List<Expression>)expression.getParents()) {
      runInternal(parent);
    }

    visit(expression);
  }

  public void run() {
    runInternal(mRoot);
  }

  public abstract void visit(Expression expression);
}
