package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public class VertexShaderCompiler extends AbstractCompiler {

  private final Expression mVertexPosition;

  public VertexShaderCompiler(Expression vertexPosition) {
    mVertexPosition = promote(vertexPosition);
  }

  @Override
  public String compile() {
    ImmutableList<Expression> sortedExpressions = new TopologicalSorter(mVertexPosition).sort();

    String preamble = compilePreamble(sortedExpressions);
    String main = compileMain(sortedExpressions);

    return preamble + main;
  }

  private String compileMain(ImmutableList<Expression> sortedExpressions) {
    StringBuilder mainBodyStringBuilder = new StringBuilder();

    getContext().pushScope();
    try {
      for (Expression expression: sortedExpressions) {
        if (expression.getGlSlType() != GlSlType.DEFAULT_T) {
          continue;
        }
        mainBodyStringBuilder.append(CompilationHelper.getDeclarationAndAssignmentStatement(
            expression.getType(),
            getContext().getExpressionName(expression),
            expression.getGlSlString(getContext()),
            getContext()));
      }

      mainBodyStringBuilder.append(CompilationHelper.getAssignmentStatemenet(
          "gl_Position", getContext().getExpressionName(mVertexPosition), getContext()));
      return wrapInMain(mainBodyStringBuilder.toString());
    } finally {
      getContext().popScope();
    }
  }

  private String compilePreamble(ImmutableList<Expression> sortedExpressions) {
    StringBuilder declarationsStringBuilder = new StringBuilder();

    for (Expression expression: sortedExpressions) {
      switch(expression.getGlSlType()) {
        case ATTRIBUTE_T:
          declarationsStringBuilder.append(CompilationHelper.getGlSlDeclarationStatement(
              expression.getType(),
              expression.getGlSlType().toString(),
              getContext().getExpressionName(expression),
              getContext()));
          break;
      }
    }

    return declarationsStringBuilder.toString();
  }
}
