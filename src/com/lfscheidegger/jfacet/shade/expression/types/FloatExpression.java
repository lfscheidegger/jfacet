package com.lfscheidegger.jfacet.shade.expression.types;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;

/**
 * {@code Expression} object that encapsulates an atomic floating-point value
 */
public class FloatExpression extends AbstractExpression {

  private float mValue;

  public FloatExpression(float t) {
    super(Type.FLOAT_T, ImmutableList.<Expression>of());
    mValue = t;
  }

  public FloatExpression(Expression other) {
    super(Type.FLOAT_T, ImmutableList.<Expression>of(other));

    Preconditions.checkState(other.getType() == Type.FLOAT_T);
  }

  @Override
  public Float evaluate() {
    switch(getParents().size()) {
      case 0:
        return mValue;
      case 1:
        Expression parent = getParents().get(0);
        Preconditions.checkState(parent.getType() == Type.FLOAT_T);
        return parent.evaluate();
      default:
        throw new IllegalStateException(
            "FloatExpression cannot have more than 1 parent - found " + getParents().size());
    }
  }
}
