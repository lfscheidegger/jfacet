package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Expression;

import java.util.LinkedHashSet;

/**
 * Sorts {@code Expression} objects topologically, i.e. after sorting, an {@code Expression} object is
 * guaranteed to appear on the list strictly after all of its parents.
 */
public class TopologicalSorter {

  private final ImmutableList<Expression> mRoots;
  private LinkedHashSet<Expression> mSortedList;

  public TopologicalSorter(Expression... roots) {
    mRoots = ImmutableList.copyOf(roots);
  }

  public ImmutableList<Expression> sort() {

    // I'm not using the Map interface here to make it clear that this is
    // a "listy" map - it preserves insertion order
    mSortedList = new LinkedHashSet<Expression>();

    for (Expression exp: mRoots) {
      processExpression(exp);
    }

    return ImmutableList.copyOf(mSortedList);
  }

  private void processExpression(Expression expression) {
    if (mSortedList.contains(expression)) {
      return;
    }

    if (expression.getParents().size() == 0) {
      mSortedList.add(expression);
      return;
    }

    for (Expression parent: expression.getParents()) {
      processExpression(parent);
    }
    mSortedList.add(expression);
  }
}
