package com.lfscheidegger.jfacet.compiler;

import android.util.Log;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;

import java.util.List;
import java.util.Map;

public class VertexShaderCompiler {

  private final Vector4 mVertexPosition;
  private final CompilationHelper mCompilationHelper;
  private final List<Expression> mAttributeExpressions;
  private final List<Expression> mUniformExpressions;

  private Map<Expression, String> mVaryingExpressions = Maps.newHashMap();

  public VertexShaderCompiler(Vector4 vertexPosition) {
    mVertexPosition = vertexPosition;
    mCompilationHelper = new CompilationHelper();

    final ImmutableSet.Builder<Expression> attributeBuilder = new ImmutableSet.Builder<Expression>();
    new ExpressionVisitor(mVertexPosition) {
      @Override
      public void visit(Expression expression) {
        if (expression.getNodeType() instanceof Expression.NodeType.AttributeNodeType) {
          attributeBuilder.add(expression);
        }
      }
    }.run();
    mAttributeExpressions = Lists.newArrayList(attributeBuilder.build());

    final ImmutableSet.Builder<Expression> uniformBuilder = new ImmutableSet.Builder<Expression>();
    new ExpressionVisitor(mVertexPosition) {
      @Override
      public void visit(Expression expression) {
        if (expression.getNodeType() instanceof Expression.NodeType.UniformNodeType) {
          uniformBuilder.add(expression);
        }
      }
    }.run();
    mUniformExpressions = Lists.newArrayList(uniformBuilder.build());
  }

  public void setVaryingExpressions(Map<Expression, String> varyingExpressions) {
    mVaryingExpressions = varyingExpressions;
    mAttributeExpressions.addAll(varyingExpressions.keySet());
  }

  public String compile() {
    StringBuilder sb = new StringBuilder();

    ImmutableSet.Builder<Expression> builder = new ImmutableSet.Builder<Expression>();
    builder
        .addAll(mAttributeExpressions)
        .addAll(mVaryingExpressions.keySet());

    for (Expression expression : builder.build()) {
      mCompilationHelper.emitAttributeDeclaration(sb, expression);
    }

    if (!mUniformExpressions.isEmpty()) {
      sb.append("\n");
    }

    for (Expression expression : mUniformExpressions) {
      mCompilationHelper.emitUniformDeclaration(sb, expression);
    }

    sb.append("\n");

    for (Expression attribute : mVaryingExpressions.keySet()) {
      String varyingName = mVaryingExpressions.get(attribute);
      mCompilationHelper.emitVaryingDeclaration(sb, attribute, varyingName);
    }

    sb.append("void main() {\n");

    ImmutableList<Expression> sortedExpressions = TopologicalSorter.sort(mVertexPosition);
    for (Expression expression : sortedExpressions) {
      mCompilationHelper.emitExpression(sb, expression);
    }
    mCompilationHelper.emitAssignment(sb, "gl_Position", mVertexPosition);

    for (Expression expression : mVaryingExpressions.keySet()) {
      mCompilationHelper.emitAssignment(sb, mVaryingExpressions.get(expression), expression);
    }

    sb.append("}\n");

    Log.i("VertexShaderCompiler", sb.toString());

    return sb.toString();
  }

  public List<Expression> getAttributeExpressions() {
    return mAttributeExpressions;
  }

  public List<Expression> getUniformExpressions() { return mUniformExpressions; }

  public CompilationHelper getCompilationHelper() {
    return mCompilationHelper;
  }
}
