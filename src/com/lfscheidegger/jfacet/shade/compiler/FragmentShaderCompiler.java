package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.lfscheidegger.jfacet.shade.expression.Expression;

import java.util.HashSet;
import java.util.Set;

public class FragmentShaderCompiler extends AbstractCompiler {

  private final Set<Expression> mVaryingExpressions;

  public FragmentShaderCompiler(ImmutableMap<String, Expression> outputExpressions, CompilationContext context) {
    super(outputExpressions, TopologicalSorter.forFragmentShaderCompiler(), context);

    mVaryingExpressions = new HashSet<Expression>();
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
    mVaryingExpressions.add(expression);
  }

  public ImmutableSet<Expression> getVaryingExpressions() {
    return ImmutableSet.copyOf(mVaryingExpressions);
  }
}
