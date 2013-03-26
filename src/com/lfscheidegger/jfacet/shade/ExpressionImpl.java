package com.lfscheidegger.jfacet.shade;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of {@code Expression}
 */
public class ExpressionImpl implements Expression, ContainsGlSlExpression {

  public final static class Builder {
    private final String mType;
    private final List<Expression> mParents;
    private String mGlSlExpression;

    public Builder(
        String type,
        String glSlExpression) {
      mType = type;
      mParents = new ArrayList<Expression>();
      mGlSlExpression = glSlExpression;
    }

    public Builder addParent(Expression parent) {
      mParents.add(parent);
      return this;
    }

    public Expression build() {
      return new ExpressionImpl(mType, ImmutableList.copyOf(mParents), mGlSlExpression);
    }
  }

  private final String mType;
  private final ImmutableList<Expression> mParents;

  private final String mGlSlExpression;

  public ExpressionImpl(
      String type,
      ImmutableList<Expression> parents,
      String glSlExpression) {
    mType = type;
    mParents = parents;
    mGlSlExpression = glSlExpression;
  }

  @Override
  public String getGlSlExpression() {
    return mGlSlExpression;
  }
}
