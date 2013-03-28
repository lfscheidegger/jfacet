package com.lfscheidegger.jfacet.shade.expression.types;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.types.Mat4;
import com.lfscheidegger.jfacet.shade.types.Vec4;

/**
 * {@code Expression} object that encapsulates a {@code Mat2} object
 */
public class Mat4Expression extends AbstractExpression {

  private Mat4 mValue;

  public Mat4Expression(Mat4 value) {
    super(Type.MAT4_T, ImmutableList.<Expression>of());

    mValue = value;
  }

  public Mat4Expression(Mat4Expression mat4) {
    super(Type.MAT4_T, ImmutableList.<Expression>of(mat4));
  }

  public Mat4Expression(Vec4Expression c0, Vec4Expression c1, Vec4Expression c2, Vec4Expression c3) {
    super(Type.MAT4_T, ImmutableList.<Expression>of(c0, c1, c2, c3));
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
