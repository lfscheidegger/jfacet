package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public class VertexShaderCompiler extends AbstractCompiler {

  /*public VertexShaderCompiler(ImmutableMap<String, Expression> outputExpressions, CompilationContext context) {
    super(outputExpressions, TopologicalSorter.forVertexShaderCompiler(), context);
  }

  @Override
  public void compileVaryingExpression(
      Expression expression,
      ImmutableList.Builder<String> mainBodyBuilder,
      ImmutableList.Builder<String> preambleBuilder) {
    preambleBuilder.add(CompilationHelper.getGlSlDeclarationStatement(
        expression.getGlSlType(),
        expression.getType(),
        getContext().getExpressionName(expression),
        getContext()));
  }*/
}
