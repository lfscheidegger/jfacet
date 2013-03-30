package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.Mat2;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;

/**
 * {@code Expression} object that encapsulates a {@code Mat2} object
 */
public class Mat2Expression extends AbstractExpression {

  private static final Type TYPE = Type.MAT2_T;

  private Mat2 mValue;

  public Mat2Expression(Mat2 value) {
    super(TYPE, ImmutableList.<Expression>of());

    mValue = value;
  }

  public Mat2Expression(Expression mat2) {
    super(TYPE, ImmutableList.<Expression>of(mat2));

    Preconditions.checkArgument(mat2.getType() == TYPE);
  }

  public Mat2Expression(Expression c0, Expression c1) {
    super(TYPE, ImmutableList.<Expression>of(c0, c1));

    Preconditions.checkArgument(c0.getType() == Type.VEC2_T && c1.getType() == Type.VEC2_T);
  }

  @Override
  public Mat2 evaluate() {
    switch(getParents().size()) {
      case 0: return mValue;
      case 1: return getParents().get(0).evaluate();
      case 2:
        return new Mat2(
            (Vec2)getParents().get(0).evaluate(),
            (Vec2)getParents().get(1).evaluate());
      default:
        throw new IllegalStateException(
            "Mat2Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  @Override
  public String getGlSlExpression() {
    return getGlSlExpression(mValue);
  }

  public Vec2Expression getC0() {
    switch(getParents().size()) {
      case 0: return new Vec2Expression(mValue.getC0());
      case 1: return ((Mat2Expression)getParents().get(0)).getC0();
      case 2: return (Vec2Expression)getParents().get(0);
      default:
        throw new IllegalStateException(
            "Mat2Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public Vec2Expression getC1() {
    switch(getParents().size()) {
      case 0: return new Vec2Expression(mValue.getC1());
      case 1: return ((Mat2Expression)getParents().get(0)).getC1();
      case 2: return (Vec2Expression)getParents().get(1);
      default:
        throw new IllegalStateException(
            "Mat2Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public Vec2Expression get(int idx) {
    switch(idx) {
      case 0: return getC0();
      case 1: return getC1();
      default: throw new IndexOutOfBoundsException();
    }
  }
}
