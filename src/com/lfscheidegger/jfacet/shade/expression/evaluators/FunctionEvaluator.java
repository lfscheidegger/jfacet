package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public abstract class FunctionEvaluator<T> implements Evaluator<T> {

  private final Type mType;

  private final String mFunctionName;

  public FunctionEvaluator(Type type, String functionName) {
    mType = type;
    mFunctionName = functionName;
  }

  @Override
  public String getGlSlString(Expression expression, final CompilationContext compilationContext) {
    return GlSlExpressionHelper.getFunctionExpression(
        mType,
        mFunctionName,
        (String[])Collections2.transform(expression.getParents(), new Function<Expression, String>() {
          @Override
          public String apply(Expression parent) {
            return parent.getGlSlString(compilationContext);
          }
        }).toArray(new String[expression.getParents().size()]));
  }

  public Type getType() {
    return mType;
  }

  public String getFunctionName() {
    return mFunctionName;
  }
}
