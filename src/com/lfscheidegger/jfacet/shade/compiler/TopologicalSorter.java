package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.expression.Expression;

import java.util.LinkedHashSet;

public class TopologicalSorter {

  public static interface ParentObtainer {
    public ImmutableList<Expression> getParents(Expression exp);
  }

  private final ParentObtainer mParentObtainer;
  private final LinkedHashSet<Expression> mSortedExpressionList;

  public static TopologicalSorter forVertexShaderCompiler() {
    return new TopologicalSorter(new ParentObtainer() {
      @Override
      public ImmutableList<Expression> getParents(Expression exp) {
        return exp.getParents();
      }
    });
  }

  public static TopologicalSorter forFragmentShaderCompiler() {
    return new TopologicalSorter(new ParentObtainer() {
      @Override
      public ImmutableList<Expression> getParents(Expression exp) {
        if (exp.getGlSlType() == GlSlType.VARYING_T) {
          return ImmutableList.of();
        }

        return exp.getParents();
      }
    });
  }

  public TopologicalSorter(ParentObtainer parentObtainer) {
    mSortedExpressionList = new LinkedHashSet<Expression>();
    mParentObtainer = parentObtainer;
  }

  public ImmutableList<Expression> sort(ImmutableList<Expression> expressions) {
    for (Expression root: expressions) {
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

    for (Expression dependency: mParentObtainer.getParents(exp)) {
      sortExpression(dependency);
    }

    mSortedExpressionList.add(exp);
  }
}
