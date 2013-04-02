package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public class ShaderCompiler extends AbstractCompiler {

  private final ImmutableList<Expression> mSortedExpressions;

  public ShaderCompiler(ImmutableMap<String, Expression> outputExpressions) {
    mSortedExpressions = new TopologicalSorter(
        ImmutableList.<Expression>copyOf(outputExpressions.values())).sort();
  }

  public ShaderCompiler(
      CompilationContext context,
      ImmutableMap<String, Expression> outputExpressions) {
    super(context);
    mSortedExpressions = new TopologicalSorter(
        ImmutableList.<Expression> copyOf(outputExpressions.values())).sort();
  }

  public String compile() {
    ImmutableList.Builder<String> mainBuilder = new ImmutableList.Builder<String>();
    ImmutableList.Builder<String> preambleBuilder = new ImmutableList.Builder<String>();

    for (Expression expression: mSortedExpressions) {
      if (expression.getGlSlType() == GlSlType.DEFAULT_T) {
        mainBuilder.add(CompilationHelper.getDeclarationAndAssignmentStatement(
            expression.getType(),
            getContext().getExpressionName(expression),
            expression.getGlSlString(getContext()),
            getContext()));
      } else if (expression.getGlSlType() == GlSlType.ATTRIBUTE_T) {
        preambleBuilder.add(CompilationHelper.getGlSlDeclarationStatement(
            expression.getGlSlType(),
            expression.getType(),
            getContext().getExpressionName(expression),
            getContext()));
      }
    }

    return getPreamble(preambleBuilder.build()) + getMainBody(mainBuilder.build());
  }

  private String getPreamble(ImmutableList<String> preambleStatements) {
    StringBuilder builder = new StringBuilder();
    for (String statement: preambleStatements) {
      builder.append(statement);
    }

    return builder.toString();
  }

  private String getMainBody(ImmutableList<String> mainBodyStatements) {
    StringBuilder builder = new StringBuilder();

    builder.append("void main(){\n");
    getContext().pushScope();
    try {
      for (String statement: mainBodyStatements) {
        builder.append(Strings.repeat(" ", 4 * getContext().getScopeLevel())).append(statement);
      }
    } finally {
      getContext().popScope();
    }

    builder.append("}\n");
    return builder.toString();
  }
}
