package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;

import java.util.HashMap;
import java.util.Map;


public class VertexShaderCompiler {


  private final CompilationContext mContext = new CompilationContext() {
    private final Map<Expression, String> mExpressionNameMap = new HashMap<Expression, String>();

    @Override
    public String getName(Expression exp) {
      if (!mExpressionNameMap.containsKey(exp)) {
        mExpressionNameMap.put(exp, CompilationHelper.getUniqueName());
      }
      return mExpressionNameMap.get(exp);
    }
  };
  private final TopologicalSorter mTopologicalSorter;
  private final Expression mVertexPositionExpression;

  public VertexShaderCompiler(Expression vertexPositionExpression) {
    mTopologicalSorter = new TopologicalSorter(vertexPositionExpression);
    mVertexPositionExpression = vertexPositionExpression;
  }

  public String compile() {
    ImmutableList<Expression> sortedExpressions = mTopologicalSorter.sort();

    StringBuilder mainBodyStringBuilder = new StringBuilder();
    mainBodyStringBuilder.append("void main() {\n");
    for (Expression expression: sortedExpressions) {

      if (expression == mVertexPositionExpression) {
        mainBodyStringBuilder
            .append("    ")
            .append(CompilationHelper.getAssignmentStatement("gl_Position", expression, mContext))
            .append(";\n");
      } else {
        mainBodyStringBuilder
            .append("    ")
            .append(CompilationHelper.getFullStatement(mContext.getName(expression), expression, mContext))
            .append(";\n");
      }
    }

    mainBodyStringBuilder.append("}\n");
    return mainBodyStringBuilder.toString();
  }
}
