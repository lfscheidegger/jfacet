package com.lfscheidegger.jfacet.compiler;

import android.util.Log;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.lfscheidegger.jfacet.shade.GlSlQualifier;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;

import java.util.Map;

public class FragmentShaderCompiler {

  private final Vector4 mFragmentColor;
  private final CompilationHelper mCompilationHelper;

  private final Map<Expression, String> mVaryingExpressions;

  public FragmentShaderCompiler(Vector4 fragmentColor) {
    mFragmentColor = fragmentColor;
    mCompilationHelper = new CompilationHelper();
    mVaryingExpressions = Maps.newHashMap();
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

    sb.append("void main() {\n");

    for (Expression expression : sortedExpressions) {
      if(expression.getGlSlQualifier().isPresent() &&
          expression.getGlSlQualifier().get().equals(GlSlQualifier.ATTRIBUTE_T)) {
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

  private ImmutableSet<Expression> extractAttributeExpressions(
      ImmutableList<Expression> sortedExpressions) {
    ImmutableSet.Builder<Expression> builder = new ImmutableSet.Builder<Expression>();

    for (Expression expression : sortedExpressions) {
      if (expression.getGlSlQualifier().isPresent() &&
          expression.getGlSlQualifier().get().equals(GlSlQualifier.ATTRIBUTE_T)) {
        builder.add(expression);
      }
    }

    return builder.build();
  }

  public ImmutableMap<Expression, String> getVaryingExpressions() {
    return ImmutableMap.copyOf(mVaryingExpressions);
  }

  private String replaceAttributeNames(String source) {
    for (Expression attributeExpression : mVaryingExpressions.keySet()) {
      String attributeName = mCompilationHelper.getNameForExpression(attributeExpression);
      source = source.replaceAll(attributeName, mVaryingExpressions.get(attributeExpression));
    }

    return source;
  }
}
