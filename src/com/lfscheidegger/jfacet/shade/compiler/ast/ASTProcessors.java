package com.lfscheidegger.jfacet.shade.compiler.ast;

import com.lfscheidegger.jfacet.shade.expression.Expression;

import java.util.ArrayList;
import java.util.List;

public class ASTProcessors implements ASTProcessor {

  private final List<ASTProcessor> mProcessors;

  public ASTProcessors() {
    mProcessors = new ArrayList<ASTProcessor>();
  }

  @Override
  public Expression process(Expression expression) {
    for (ASTProcessor processor: mProcessors) {
      expression = processor.process(expression);
    }

    return expression;
  }

  public ASTProcessors add(ASTProcessor processor) {
    mProcessors.add(processor);
    return this;
  }
}
