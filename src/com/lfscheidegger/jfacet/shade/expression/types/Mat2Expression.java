package com.lfscheidegger.jfacet.shade.expression.types;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.types.Mat2;
import com.lfscheidegger.jfacet.shade.types.Vec2;

/**
 * {@code Expression} object that encapsulates a {@code Mat2} object
 */
public class Mat2Expression extends AbstractExpression {

  private Mat2 mValue;

  public Mat2Expression(Mat2 value) {
    super(Type.MAT2_T, ImmutableList.<Expression>of());

    mValue = value;
  }

  public Mat2Expression(Mat2Expression mat2) {
    super(Type.MAT2_T, ImmutableList.<Expression>of(mat2));
  }

  public Mat2Expression(Vec2Expression c0, Vec2Expression c1) {
    super(Type.MAT2_T, ImmutableList.<Expression>of(c0, c1));
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
