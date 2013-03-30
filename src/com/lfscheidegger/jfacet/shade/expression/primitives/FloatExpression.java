package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;

/**
 * {@code Expression} object that encapsulates an atomic floating-point value
 */
public class FloatExpression extends AbstractExpression {

  private final static Type TYPE = Type.FLOAT_T;

  private float mValue;

  public FloatExpression(float t) {
    super(TYPE, ImmutableList.<Expression>of());

    mValue = t;
  }

  public FloatExpression(Expression other) {
    super(TYPE, ImmutableList.<Expression>of(other));

    Preconditions.checkArgument(other.getType() == TYPE);
  }

  @Override
  public Float evaluate() {
    switch(getParents().size()) {
      case 0:
        return mValue;
      case 1:
        Expression parent = getParents().get(0);
        return parent.evaluate();
      default:
        throw new IllegalStateException(
            "FloatExpression cannot have more than 1 parent - found " + getParents().size());
    }
  }

  @Override
  public String getGlSlExpression() {
    if (getParents().size() == 0) {
      return "float(" + mValue + ")";
    }

    return getParents().get(0).getGlSlExpression();
  }
}
