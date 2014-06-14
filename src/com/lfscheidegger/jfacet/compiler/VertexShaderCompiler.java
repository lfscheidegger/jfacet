// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.compiler;

import android.util.Log;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.NodeType;
import com.lfscheidegger.jfacet.shade.expression.Vec4;

import java.util.List;
import java.util.Map;

public class VertexShaderCompiler {

  public static final class CompilationResult {

    public final String code;
    public final ImmutableList<Expression> attributeExpressions;
    public final ImmutableList<Expression> uniformExpressions;

    private CompilationResult(
        String code,
        ImmutableList<Expression> attributeExpressions,
        ImmutableList<Expression> uniformExpressions) {
      this.code = code;
      this.attributeExpressions = attributeExpressions;
      this.uniformExpressions = uniformExpressions;
    }
  }

  private final CompilationHelper mCompilationHelper;

  public VertexShaderCompiler(CompilationHelper compilationHelper) {
    mCompilationHelper = compilationHelper;
  }

  public CompilationResult compile(Vec4 vertexPosition, Map<Expression, String> varyingExpressions) {
    ImmutableList<Expression> attributeExpressions =
        ImmutableList.copyOf(new ImmutableSet.Builder<Expression>()
        .addAll(mCompilationHelper.extractAttributeExpressions(vertexPosition))
        .addAll(varyingExpressions.keySet())
        .build());

    ImmutableList<Expression> uniformExpressions =
        mCompilationHelper.extractUniformExpressions(vertexPosition);

    StringBuilder sb = new StringBuilder();

    for (Expression expression : attributeExpressions) {
      mCompilationHelper.emitAttributeDeclaration(sb, expression);
    }

    if (!uniformExpressions.isEmpty()) {
      sb.append("\n");
    }

    for (Expression expression : uniformExpressions) {
      mCompilationHelper.emitUniformDeclaration(sb, expression);
    }

    sb.append("\n");

    for (Expression attribute : varyingExpressions.keySet()) {
      String varyingName = varyingExpressions.get(attribute);
      mCompilationHelper.emitVaryingDeclaration(sb, attribute, varyingName);
    }

    sb.append("void main() {\n");

    ImmutableList<Expression> sortedExpressions = TopologicalSorter.sort(vertexPosition);
    for (Expression expression : sortedExpressions) {
      mCompilationHelper.emitExpression(sb, expression);
    }
    mCompilationHelper.emitAssignment(sb, "gl_Position", vertexPosition);

    for (Expression expression : varyingExpressions.keySet()) {
      mCompilationHelper.emitAssignment(sb, varyingExpressions.get(expression), expression);
    }

    sb.append("}\n");

    Log.i("VertexShaderCompiler", sb.toString());

    return new CompilationResult(
        sb.toString(),
        attributeExpressions,
        uniformExpressions);
  }
}
