package com.lfscheidegger.jfacet.compiler;

import android.util.Log;
import com.google.common.collect.*;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;

import java.util.List;
import java.util.Map;

public class FragmentShaderCompiler {

  private final Vector4 mFragmentColor;
  private final CompilationHelper mCompilationHelper;

  private final Map<Expression, String> mVaryingExpressions;

  private final List<Expression> mUniformExpressions;

  public FragmentShaderCompiler(Vector4 fragmentColor) {
    mFragmentColor = fragmentColor;
    mCompilationHelper = new CompilationHelper();
    mVaryingExpressions = Maps.newHashMap();

    final ImmutableSet.Builder<Expression> uniformBuilder = new ImmutableSet.Builder<Expression>();
    new ExpressionVisitor(mFragmentColor) {
      @Override
      public void visit(Expression expression) {
        if (expression.getNodeType() instanceof Expression.NodeType.UniformNodeType) {
          uniformBuilder.add(expression);
        }
      }
    }.run();
    mUniformExpressions = Lists.newArrayList(uniformBuilder.build());
  }

  public String compile() {
    ImmutableList<Expression> sortedExpressions = TopologicalSorter.sort(mFragmentColor);
    ImmutableSet<Expression> attributeExpressions = extractAttributeExpressions(sortedExpressions);

    StringBuilder sb = new StringBuilder();

    sb.append("precision highp float;\n");

    for (Expression attributeExpression : attributeExpressions) {
      String varyingName = mCompilationHelper.getVaryingName();
      mVaryingExpressions.put(attributeExpression, varyingName);
      mCompilationHelper.emitVaryingDeclaration(sb, attributeExpression, varyingName);
    }

    if (!mUniformExpressions.isEmpty()) {
      sb.append("\n");
    }

    for (Expression expression : mUniformExpressions) {
      mCompilationHelper.emitUniformDeclaration(sb, expression);
    }

    sb.append("void main() {\n");

    for (Expression expression : sortedExpressions) {
      if (expression.getNodeType() instanceof Expression.NodeType.AttributeNodeType) {
        continue;
      } else {
        mCompilationHelper.emitExpression(sb, expression);
      }

    }
    mCompilationHelper.emitAssignment(sb, "gl_FragColor", mFragmentColor);

    sb.append("}\n");

    String source = replaceAttributeNames(sb.toString());

    Log.i("FragmentShaderCompiler", source);

    return source;
  }

  private String replaceAttributeNames(String source) {
    for (Expression attributeExpression : mVaryingExpressions.keySet()) {
      String attributeName = mCompilationHelper.getNameForExpression(attributeExpression);
      source = source.replaceAll(attributeName, mVaryingExpressions.get(attributeExpression));
    }

    return source;
  }

  private ImmutableSet<Expression> extractAttributeExpressions(
      ImmutableList<Expression> sortedExpressions) {
    ImmutableSet.Builder<Expression> builder = new ImmutableSet.Builder<Expression>();

    for (Expression expression : sortedExpressions) {
      if (expression.getNodeType() instanceof Expression.NodeType.AttributeNodeType) {
        builder.add(expression);
      }
    }

    return builder.build();
  }

  public ImmutableMap<Expression, String> getVaryingExpressions() {
    return ImmutableMap.copyOf(mVaryingExpressions);
  }

  public List<Expression> getUniformExpressions() {
    return mUniformExpressions;
  }

  public CompilationHelper getCompilationHelper() {
    return mCompilationHelper;
  }
}
