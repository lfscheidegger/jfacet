package com.lfscheidegger.jfacet.shade.compiler;

import com.lfscheidegger.jfacet.shade.expression.Expression;

import java.util.HashMap;
import java.util.Map;

public class DefaultCompilationContext implements CompilationContext {

  private final Map<Expression, String> mExpressionNameMap = new HashMap<Expression, String>();
  private int mScopeLevel = 0;

  @Override
  public String getExpressionName(Expression exp) {
    if (!mExpressionNameMap.containsKey(exp)) {
      mExpressionNameMap.put(exp, CompilationHelper.getUniqueGlSlName());
    }

    return mExpressionNameMap.get(exp);
  }

  @Override
  public int getScopeLevel() {
    return mScopeLevel;
  }

  @Override
  public void pushScope() {
    mScopeLevel++;
  }

  @Override
  public void popScope() {
    mScopeLevel = (mScopeLevel > 0) ? mScopeLevel - 1: 0;
  }
}
