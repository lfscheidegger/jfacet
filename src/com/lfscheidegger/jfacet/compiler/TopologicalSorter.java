package com.lfscheidegger.jfacet.compiler;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.lfscheidegger.jfacet.shade.expression.Expression;

import java.util.Set;

public class TopologicalSorter {

  private static void sortInternal(Expression root, Set<Expression> sorted) {
    for (Expression parent : (ImmutableList<Expression>)root.getParents()) {
      sortInternal(parent, sorted);
    }

    sorted.add(root);
  }

  public static ImmutableList<Expression> sort(Expression root) {
    Set<Expression> sorted = Sets.newLinkedHashSet();
    sortInternal(root, sorted);
    return new ImmutableList.Builder<Expression>().addAll(sorted).build();
  }
}
