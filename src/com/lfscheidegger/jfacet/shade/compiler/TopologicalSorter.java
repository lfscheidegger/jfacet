package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Expression;

import java.util.LinkedHashSet;

public class TopologicalSorter {

  private final ImmutableList<Expression> mExpressions;
  private final LinkedHashSet<Expression> mSortedExpressionList;

  public TopologicalSorter(Expression... roots) {
    mExpressions = ImmutableList.copyOf(roots);
    mSortedExpressionList = new LinkedHashSet<Expression>();
  }

  public ImmutableList<Expression> sort() {
    for (Expression root: mExpressions) {
      sortExpression(root);
    }

    return ImmutableList.copyOf(mSortedExpressionList);
  }

  private void sortExpression(Expression exp) {
    if (mSortedExpressionList.contains(exp)) {
      return;
    }

    if (exp.getParents().size() == 0) {
      mSortedExpressionList.add(exp);
      return;
    }

    for (Expression dependency: (ImmutableList<Expression>)exp.getParents()) {
      sortExpression(dependency);
    }

    mSortedExpressionList.add(exp);
  }
}
