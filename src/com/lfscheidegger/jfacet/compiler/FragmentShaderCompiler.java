// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.compiler;

import android.util.Log;
import com.google.common.collect.*;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.NodeType;
import com.lfscheidegger.jfacet.shade.expression.Vec4;

import java.util.List;
import java.util.Map;

public class FragmentShaderCompiler {

  public static final class CompilationResult {

    public final String code;
    public final ImmutableList<Expression> uniformExpressions;
    public final ImmutableMap<Expression, String> varyingExpressions;

    private CompilationResult(
        String code,
        List<Expression> uniformExpressions,
        Map<Expression, String> varyingExpressions) {
      this.code = code;
      this.uniformExpressions = ImmutableList.copyOf(uniformExpressions);
      this.varyingExpressions = ImmutableMap.copyOf(varyingExpressions);
    }
  }

  private final CompilationHelper mCompilationHelper;

  public FragmentShaderCompiler(CompilationHelper compilationHelper) {
    mCompilationHelper = compilationHelper;
  }

  public CompilationResult compile(Vec4 fragmentColor) {
    ImmutableList<Expression> sortedExpressions = TopologicalSorter.sort(fragmentColor);
    ImmutableSet<Expression> attributeExpressions = extractAttributeExpressions(sortedExpressions);
    ImmutableList<Expression> uniformExpressions =
        mCompilationHelper.extractUniformExpressions(fragmentColor);
    Map<Expression, String> varyingExpressions = Maps.newHashMap();

    StringBuilder sb = new StringBuilder();

    sb.append("precision highp float;\n");

    for (Expression attributeExpression : attributeExpressions) {
      String varyingName = mCompilationHelper.getVaryingName();
      varyingExpressions.put(attributeExpression, varyingName);
      mCompilationHelper.emitVaryingDeclaration(sb, attributeExpression, varyingName);
    }

    if (!uniformExpressions.isEmpty()) {
      sb.append("\n");
    }

    for (Expression expression : uniformExpressions) {
      mCompilationHelper.emitUniformDeclaration(sb, expression);
    }

    sb.append("void main() {\n");

    for (Expression expression : sortedExpressions) {
      if (expression.getNodeType() instanceof NodeType.AttributeNodeType) {
        continue;
      } else {
        mCompilationHelper.emitExpression(sb, expression);
      }

    }
    mCompilationHelper.emitAssignment(sb, "gl_FragColor", fragmentColor);

    sb.append("}\n");

    String source = replaceAttributeNames(sb.toString(), varyingExpressions);

    Log.i("FragmentShaderCompiler", source);

    return new CompilationResult(
        source,
        uniformExpressions,
        varyingExpressions);
  }

  private String replaceAttributeNames(
      String source,
      Map<Expression, String> varyingExpressions) {
    for (Expression attributeExpression : varyingExpressions.keySet()) {
      String attributeName = mCompilationHelper.getNameForExpression(attributeExpression);
      source = source.replaceAll(attributeName, varyingExpressions.get(attributeExpression));
    }

    return source;
  }

  private ImmutableSet<Expression> extractAttributeExpressions(
      ImmutableList<Expression> sortedExpressions) {
    ImmutableSet.Builder<Expression> builder = new ImmutableSet.Builder<Expression>();

    for (Expression expression : sortedExpressions) {
      if (expression.getNodeType() instanceof NodeType.AttributeNodeType) {
        builder.add(expression);
      }
    }

    return builder.build();
  }
}
