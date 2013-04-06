package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.expression.Expression;

import java.util.Map;

public abstract class AbstractCompiler implements Compiler {

  private final ImmutableMap<String, Expression> mOutputExpressions;
  private final TopologicalSorter mTopologicalSorter;
  private final CompilationContext mContext;

  public AbstractCompiler(
      ImmutableMap<String, Expression> outputExpressions,
      TopologicalSorter topologicalSorter,
      CompilationContext context) {
    mOutputExpressions = outputExpressions;
    mTopologicalSorter = topologicalSorter;
    mContext = context;
  }

  @Override
  public final String compile() {
    ImmutableList.Builder<String> mainBodyBuilder = new ImmutableList.Builder<String>();
    ImmutableList.Builder<String> preambleBuilder = new ImmutableList.Builder<String>();

    ImmutableList<Expression> sortedExpressions = mTopologicalSorter.sort(
        ImmutableList.copyOf(mOutputExpressions.values()));

    for (Expression expression: sortedExpressions) {
      if (expression.getGlSlType() == GlSlType.DEFAULT_T) {
        compileDefaultExpression(expression, mainBodyBuilder, preambleBuilder);
      } else if (expression.getGlSlType() == GlSlType.ATTRIBUTE_T) {
        compileAttributeExpression(expression, mainBodyBuilder, preambleBuilder);
      } else if (expression.getGlSlType() == GlSlType.VARYING_T) {
        compileVaryingExpression(expression, mainBodyBuilder, preambleBuilder);
      } else if (expression.getGlSlType() == GlSlType.UNIFORM_T) {
        compileUniformExpression(expression, mainBodyBuilder, preambleBuilder);
      }
    }

    for (Map.Entry<String, Expression> entry : mOutputExpressions.entrySet()) {
      if (entry.getValue().getGlSlType() == GlSlType.VARYING_T) {
        mainBodyBuilder.add(CompilationHelper.getAssignmentStatemenet(
            entry.getKey(), getContext().getExpressionName((Expression)entry.getValue().getParents().get(0)), getContext()));
      } else {
        mainBodyBuilder.add(CompilationHelper.getAssignmentStatemenet(
            entry.getKey(), getContext().getExpressionName(entry.getValue()), getContext()));
      }
    }

    return getPreamble(preambleBuilder.build()) + getMainBody(mainBodyBuilder.build());
  }

  protected final CompilationContext getContext() {
    return mContext;
  }

  public void compileDefaultExpression(
      Expression expression,
      ImmutableList.Builder<String> mainBodyBuilder,
      ImmutableList.Builder<String> preambleBuilder) {
    mainBodyBuilder.add(CompilationHelper.getDeclarationAndAssignmentStatement(
        expression.getType(),
        getContext().getExpressionName(expression),
        expression.getGlSlString(getContext()),
        getContext()));
  }

  public void compileAttributeExpression(
      Expression expression,
      ImmutableList.Builder<String> mainBodyBuilder,
      ImmutableList.Builder<String> preambleBuilder) {
    preambleBuilder.add(CompilationHelper.getGlSlDeclarationStatement(
        expression.getGlSlType(),
        expression.getType(),
        getContext().getExpressionName(expression),
        getContext()));
  }

  public void compileUniformExpression(
      Expression expression,
      ImmutableList.Builder<String> mainBodyBuilder,
      ImmutableList.Builder<String> preambleBuilder) {
    preambleBuilder.add(CompilationHelper.getGlSlDeclarationStatement(
        expression.getGlSlType(),
        expression.getType(),
        getContext().getExpressionName(expression),
        getContext()));
  }

  protected abstract void compileVaryingExpression(
      Expression expression,
      ImmutableList.Builder<String> mainBodyBuilder,
      ImmutableList.Builder<String> preambleBuilder);

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
