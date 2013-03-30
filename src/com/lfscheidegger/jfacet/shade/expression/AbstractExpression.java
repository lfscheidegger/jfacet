package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;

/**
 * Abstract basic implementation of {@code Expression}.
 */
public abstract class AbstractExpression implements Expression {

  private final Type mType;
  private final ImmutableList<Expression> mParents;

  public AbstractExpression(
      Type type,
      ImmutableList<Expression> parents) {
    mType = type;
    mParents = parents;
  }

  @Override
  public final Type getType() {
    return mType;
  }

  @Override
  public final ImmutableList<Expression> getParents() {
    return mParents;
  }

  protected final String glSlExpressionHelper(ImmutableList<Expression> parents) {
    return getType() + "(" + Joiner.on(", ").join(
        Collections2.transform(parents, new Function<Expression, String>() {
          @Override
          public String apply(Expression expression) {
            return expression.getGlSlExpression();
          }
        })) + ")";
  }

  public String getGlSlExpression(Object value) {
    if (getParents().size() == 0) {
      return String.valueOf(value);
    }

    return glSlExpressionHelper(getParents());
  }

  @Override
  public String getGlSlExpression(final CompilationContext compilationContext) {
    ImmutableList<Expression> parents = getParents();
    if (parents.size() == 0) {
      return getGlSlExpression();
    }

    return getType() + "(" + Joiner.on(", ").join(
        Collections2.transform(parents, new Function<Expression, String>() {
          @Override
          public String apply(Expression expression) {
            return compilationContext.getName(expression);
          }
        })) + ")";
  }
}
