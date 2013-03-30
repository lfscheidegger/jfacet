package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;

/**
 * {@code Expression} object that encapsulates a 2-dimensional floating-point value
 */
public class Vec2Expression extends AbstractExpression {

  private static final Type TYPE = Type.VEC2_T;

  private Vec2 mValue;

  public Vec2Expression(Vec2 value) {
    super(TYPE, ImmutableList.<Expression>of());

    mValue = value;
  }

  public Vec2Expression(Expression other) {
    super(TYPE, ImmutableList.<Expression>of(other));

    Preconditions.checkArgument(other.getType() == TYPE);
  }

  public Vec2Expression(Expression x, Expression y) {
    super(TYPE, ImmutableList.<Expression>of(x, y));

    Preconditions.checkArgument(x.getType() == Type.FLOAT_T && y.getType() == Type.FLOAT_T);
  }

  @Override
  public Vec2 evaluate() {
    switch(getParents().size()) {
      case 0: return mValue;
      case 1: return getParents().get(0).evaluate();
      case 2:
        return new Vec2(
            (Float)getParents().get(0).evaluate(),
            (Float)getParents().get(1).evaluate());
      default:
        throw new IllegalStateException(
            "Vec2Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  @Override
  public String getGlSlExpression() {
    return getGlSlExpression(TYPE, mValue);
  }

  public FloatExpression getX() {
    switch(getParents().size()) {
      case 0: return new FloatExpression(mValue.getX());
      case 1: return ((Vec2Expression)getParents().get(0)).getX();
      case 2: return (FloatExpression)getParents().get(0);
      default:
        throw new IllegalStateException(
            "Vec2Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public FloatExpression getY() {
    switch(getParents().size()) {
      case 0: return new FloatExpression(mValue.getY());
      case 1: return ((Vec2Expression)getParents().get(0)).getY();
      case 2: return (FloatExpression)getParents().get(1);
      default:
        throw new IllegalStateException(
            "Vec2Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public FloatExpression get(int idx) {
    switch(idx) {
      case 0: return getX();
      case 1: return getY();
      default: throw new IndexOutOfBoundsException();
    }
  }
}
