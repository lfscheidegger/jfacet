package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.Mat4;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

/**
 * {@code Expression} object that encapsulates a {@code Mat2} object
 */
public class Mat4Expression extends AbstractExpression {

  private static final Type TYPE = Type.MAT4_T;

  private Mat4 mValue;

  public Mat4Expression(Mat4 value) {
    super(TYPE, ImmutableList.<Expression>of());

    mValue = value;
  }

  public Mat4Expression(Expression mat4) {
    super(TYPE, ImmutableList.<Expression>of(mat4));

    Preconditions.checkArgument(mat4.getType() == TYPE);
  }

  public Mat4Expression(Expression c0, Expression c1, Expression c2, Expression c3) {
    super(TYPE, ImmutableList.<Expression>of(c0, c1, c2, c3));

    Preconditions.checkArgument(
        c0.getType() == Type.VEC4_T &&
        c1.getType() == Type.VEC4_T &&
        c2.getType() == Type.VEC4_T &&
        c3.getType() == Type.VEC4_T);
  }

  @Override
  public Mat4 evaluate() {
    switch(getParents().size()) {
      case 0: return mValue;
      case 1: return getParents().get(0).evaluate();
      case 4:
        return new Mat4(
            (Vec4)getParents().get(0).evaluate(),
            (Vec4)getParents().get(1).evaluate(),
            (Vec4)getParents().get(2).evaluate(),
            (Vec4)getParents().get(3).evaluate());
      default:
        throw new IllegalStateException(
            "Mat4Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  @Override
  public String getGlSlExpression() {
    return getGlSlExpression(TYPE, mValue);
  }

  public Vec4Expression getC0() {
    switch(getParents().size()) {
      case 0: return new Vec4Expression(mValue.getC0());
      case 1: return ((Mat4Expression)getParents().get(0)).getC0();
      case 4: return (Vec4Expression)getParents().get(0);
      default:
        throw new IllegalStateException(
            "Mat4Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public Vec4Expression getC1() {
    switch(getParents().size()) {
      case 0: return new Vec4Expression(mValue.getC1());
      case 1: return ((Mat4Expression)getParents().get(0)).getC1();
      case 4: return (Vec4Expression)getParents().get(1);
      default:
        throw new IllegalStateException(
            "Mat4Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public Vec4Expression getC2() {
    switch(getParents().size()) {
      case 0: return new Vec4Expression(mValue.getC2());
      case 1: return ((Mat4Expression)getParents().get(0)).getC2();
      case 4: return (Vec4Expression)getParents().get(2);
      default:
        throw new IllegalStateException(
            "Mat4Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public Vec4Expression getC3() {
    switch(getParents().size()) {
      case 0: return new Vec4Expression(mValue.getC3());
      case 1: return ((Mat4Expression)getParents().get(0)).getC3();
      case 4: return (Vec4Expression)getParents().get(3);
      default:
        throw new IllegalStateException(
            "Mat4Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public Vec4Expression get(int idx) {
    switch(idx) {
      case 0: return getC0();
      case 1: return getC1();
      case 2: return getC2();
      case 3: return getC3();
      default: throw new IndexOutOfBoundsException();
    }
  }
}
