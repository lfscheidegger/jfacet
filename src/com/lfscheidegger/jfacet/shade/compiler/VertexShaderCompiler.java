package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public class VertexShaderCompiler extends AbstractCompiler {

  private final Expression mVertexPosition;

  public VertexShaderCompiler(Expression vertexPosition) {
    mVertexPosition = promote(vertexPosition);
  }

  @Override
  public String compile() {
    StringBuilder mainBodyStringBuilder = new StringBuilder();

    ImmutableList<Expression> sortedExpressions = new TopologicalSorter(mVertexPosition).sort();
    for (Expression expression: sortedExpressions) {
      mainBodyStringBuilder.append(CompilationHelper.getDeclarationAndAssignmentStatement(
          expression.getType(),
          getContext().getExpressionName(expression),
          expression.getGlSlString(getContext())));
    }

    mainBodyStringBuilder.append(CompilationHelper.getAssignmentStatemenet(
        "gl_Position", getContext().getExpressionName(mVertexPosition)
    ));

    return wrapInMain(mainBodyStringBuilder.toString());
  }
}
