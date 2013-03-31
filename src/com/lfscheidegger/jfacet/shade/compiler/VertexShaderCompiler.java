package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Expression;

import java.util.HashMap;
import java.util.Map;

public class VertexShaderCompiler {

  private final Expression mVertexPosition;
  private final CompilationContext mContext = new CompilationContext() {

    private final Map<Expression, String> mExpressionNameMap = new HashMap<Expression, String>();

    @Override
    public String getExpressionName(Expression exp) {
      if (!mExpressionNameMap.containsKey(exp)) {
        mExpressionNameMap.put(exp, CompilationHelper.getUniqueGlSlName());
      }

      return mExpressionNameMap.get(exp);
    }
  };

  public VertexShaderCompiler(Expression vertexPosition) {
    mVertexPosition = vertexPosition;
  }

  public String compile() {

    StringBuilder mainBodyStringBuilder = new StringBuilder();
    mainBodyStringBuilder.append("void main() {\n");

    ImmutableList<Expression> sortedExpressions = new TopologicalSorter(mVertexPosition).sort();
    for (Expression expression: sortedExpressions) {
      mainBodyStringBuilder.append(CompilationHelper.getDeclarationAndAssignmentStatement(
          expression.getType(),
          mContext.getExpressionName(expression),
          expression.getGlSlString(mContext)));
    }

    mainBodyStringBuilder.append(CompilationHelper.getAssignmentStatemenet(
        "gl_Position", mContext.getExpressionName(mVertexPosition)
    ));

    mainBodyStringBuilder.append("}\n");
    return mainBodyStringBuilder.toString();
  }
}
