package com.lfscheidegger.jfacet.shade.expression.types;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.types.Vec4;

/**
 * {@code Expression} object encapsulating a 3-dimensional floating-point vector.
 */
public class Vec4Expression extends AbstractExpression {

  private Vec4 mValue;

  public Vec4Expression(Vec4 value) {
    super(Type.VEC4_T, ImmutableList.<Expression>of());

    mValue = value;
  }

  public Vec4Expression(Vec4Expression xyzw) {
    super(Type.VEC4_T, ImmutableList.<Expression>of(xyzw));
  }

  public Vec4Expression(FloatExpression x, FloatExpression y, FloatExpression z, FloatExpression w) {
    super(Type.VEC4_T, ImmutableList.<Expression>of(x, y, z, w));
  }


  @Override
  public Vec4 evaluate() {
    switch(getParents().size()) {
      case 0: return mValue;
      case 1: return getParents().get(0).evaluate();
      case 4:
        return new Vec4(
            (Float)getParents().get(0).evaluate(),
            (Float)getParents().get(1).evaluate(),
            (Float)getParents().get(2).evaluate(),
            (Float)getParents().get(3).evaluate());
      default:
        throw new IllegalStateException(
            "Vec4Expression cannot have more than 4 parents - found " + getParents().size());
    }
  }

  public FloatExpression getX() {
    switch(getParents().size()) {
      case 0: return new FloatExpression(mValue.getX());
      case 1: return ((Vec4Expression)getParents().get(0)).getX();
      case 4: return (FloatExpression)getParents().get(0);
      default:
        throw new IllegalStateException(
            "Vec2Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public FloatExpression getY() {
    switch(getParents().size()) {
      case 0: return new FloatExpression(mValue.getY());
      case 1: return ((Vec4Expression)getParents().get(0)).getY();
      case 4: return (FloatExpression)getParents().get(1);
      default:
        throw new IllegalStateException(
            "Vec2Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public FloatExpression getZ() {
    switch(getParents().size()) {
      case 0: return new FloatExpression(mValue.getZ());
      case 1: return ((Vec4Expression)getParents().get(0)).getZ();
      case 4: return (FloatExpression)getParents().get(2);
      default:
        throw new IllegalStateException(
            "Vec2Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public FloatExpression getW() {
    switch(getParents().size()) {
      case 0: return new FloatExpression(mValue.getW());
      case 1: return ((Vec4Expression)getParents().get(0)).getW();
      case 4: return (FloatExpression)getParents().get(3);
      default:
        throw new IllegalStateException(
            "Vec2Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public FloatExpression get(int idx) {
    switch(idx) {
      case 0: return getX();
      case 1: return getY();
      case 2: return getZ();
      case 3: return getW();
      default: throw new IndexOutOfBoundsException();
    }
  }
}
