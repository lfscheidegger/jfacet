package com.lfscheidegger.jfacet.shade.expression.types;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.types.Mat3;
import com.lfscheidegger.jfacet.shade.primitives.types.Vec3;

/**
 * {@code Expression} object that encapsulates a {@code Mat2} object
 */
public class Mat3Expression extends AbstractExpression {

  private Mat3 mValue;

  public Mat3Expression(Mat3 value) {
    super(Type.MAT3_T, ImmutableList.<Expression>of());

    mValue = value;
  }

  public Mat3Expression(Mat3Expression mat3) {
    super(Type.MAT3_T, ImmutableList.<Expression>of(mat3));
  }

  public Mat3Expression(Vec3Expression c0, Vec3Expression c1, Vec3Expression c2) {
    super(Type.MAT3_T, ImmutableList.<Expression>of(c0, c1, c2));
  }

  @Override
  public Mat3 evaluate() {
    switch(getParents().size()) {
      case 0: return mValue;
      case 1: return getParents().get(0).evaluate();
      case 3:
        return new Mat3(
            (Vec3)getParents().get(0).evaluate(),
            (Vec3)getParents().get(1).evaluate(),
            (Vec3)getParents().get(2).evaluate());
      default:
        throw new IllegalStateException(
            "Mat2Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public Vec3Expression getC0() {
    switch(getParents().size()) {
      case 0: return new Vec3Expression(mValue.getC0());
      case 1: return ((Mat3Expression)getParents().get(0)).getC0();
      case 3: return (Vec3Expression)getParents().get(0);
      default:
        throw new IllegalStateException(
            "Mat2Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public Vec3Expression getC1() {
    switch(getParents().size()) {
      case 0: return new Vec3Expression(mValue.getC1());
      case 1: return ((Mat3Expression)getParents().get(0)).getC1();
      case 3: return (Vec3Expression)getParents().get(1);
      default:
        throw new IllegalStateException(
            "Mat2Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public Vec3Expression getC2() {
    switch(getParents().size()) {
      case 0: return new Vec3Expression(mValue.getC2());
      case 1: return ((Mat3Expression)getParents().get(0)).getC2();
      case 3: return (Vec3Expression)getParents().get(2);
      default:
        throw new IllegalStateException(
            "Mat2Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public Vec3Expression get(int idx) {
    switch(idx) {
      case 0: return getC0();
      case 1: return getC1();
      case 2: return getC2();
      default: throw new IndexOutOfBoundsException();
    }
  }
}
