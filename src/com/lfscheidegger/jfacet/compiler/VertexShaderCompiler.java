package com.lfscheidegger.jfacet.compiler;

import android.util.Log;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;

import java.util.Map;

public class VertexShaderCompiler {

  private final Vector4 mVertexPosition;
  private final Map<Expression, String> mExpressionNames;

  private final ImmutableList<Expression> mAttributeExpressions;

  public VertexShaderCompiler(Vector4 vertexPosition) {
    mVertexPosition = vertexPosition;
    mExpressionNames = Maps.newHashMap();
    final ImmutableSet.Builder<Expression> builder = new ImmutableSet.Builder<Expression>();
    new ExpressionVisitor(mVertexPosition) {
      @Override
      public void visit(Expression expression) {
        if (expression.getAttributeBuffer().isPresent()) {
          builder.add(expression);
        }
      }
    }.run();
    mAttributeExpressions = builder.build().asList();
  }

  public String compile() {
    StringBuilder sb = new StringBuilder();

    for (Expression expression : mAttributeExpressions) {
      emitAttributeDeclaration(sb, expression);
    }
    sb.append("void main() {\n");

    ImmutableList<Expression> sortedExpressions = TopologicalSorter.sort(mVertexPosition);
    for (Expression expression : sortedExpressions) {
      emitExpression(sb, expression);
    }

    sb.append(String.format("gl_Position = %s;\n", getNameForExpression(mVertexPosition)));
    sb.append("}\n");

    String source = sb.toString();

    Log.i("VertexShaderCompiler", source);

    return source;
  }

  public ImmutableList<Expression> getAttributeExpressions() {
    return mAttributeExpressions;
  }

  public String getNameForExpression(Expression expression) {
    if (!mExpressionNames.containsKey(expression)) {
      mExpressionNames.put(expression, CompilationHelper.getUniqueName());
    }

    return mExpressionNames.get(expression);
  }

  private void emitAttributeDeclaration(StringBuilder sb, Expression attributeExpression) {
    sb.append(String.format(
        "attribute %s %s;\n",
        attributeExpression.getGlSlTypeName(),
        getNameForExpression(attributeExpression)));
  }

  private void emitExpression(StringBuilder sb, Expression expression) {
    String typeName = expression.getGlSlTypeName();

    if (!expression.getParents().isEmpty()) {
      Expression.NodeType nodeType = (Expression.NodeType)expression.getNodeType().get();
      if (nodeType.equals(Expression.NodeType.CONS)) {
        String parents = getParentsString(expression.getParents());
        sb.append(String.format(
            "%s %s = %s(%s);\n",
            typeName,
            getNameForExpression(expression),
            typeName,
            parents));
      } else if (nodeType instanceof Expression.NodeType.ComponentNodeType) {
        sb.append(String.format(
            "%s %s = %s;\n",
            typeName,
            getNameForExpression(expression),
            getComponentString((Expression.NodeType.ComponentNodeType)nodeType, (Expression)expression.getParents().get(0))));
      }
    } else if (expression.getPrimitive().isPresent()) {
      sb.append(String.format(
          "%s %s = %s;\n",
          typeName,
          getNameForExpression(expression),
          expression.getPrimitive().get()));
    }
  }

  private String getParentsString(ImmutableList<Expression> parents) {
    StringBuilder sb = new StringBuilder();

    sb.append(getNameForExpression(parents.get(0)));
    for (int i = 1; i < parents.size(); i++) {
      sb.append(", ").append(getNameForExpression(parents.get(i)));
    }

    return sb.toString();
  }

  private String getComponentString(
      Expression.NodeType.ComponentNodeType nodeType,
      Expression parent) {
    String[] components = new String[]{"x", "y", "z", "w"};

    return String.format(
        "%s.%s",
        getNameForExpression(parent),
        components[nodeType.getComponent()]);
  }
}
