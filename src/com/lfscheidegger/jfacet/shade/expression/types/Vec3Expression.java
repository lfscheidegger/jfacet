package com.lfscheidegger.jfacet.shade.expression.types;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.types.Vec3;

/**
 * {@code Expression} object encapsulating a 3-dimensional floating-point vector.
 */
public class Vec3Expression extends AbstractExpression {

  private Vec3 mValue;

  public Vec3Expression(Vec3 value) {
    super(Type.VEC3_T, ImmutableList.<Expression>of());

    mValue = value;
  }

  public Vec3Expression(Vec3Expression xyz) {
    super(Type.VEC3_T, ImmutableList.<Expression>of(xyz));
  }

  public Vec3Expression(FloatExpression x, FloatExpression y, FloatExpression z) {
    super(Type.VEC3_T, ImmutableList.<Expression>of(x, y, z));
  }

  @Override
  public Vec3 evaluate() {
    switch(getParents().size()) {
      case 0: return mValue;
      case 1: return getParents().get(0).evaluate();
      case 3:
        return new Vec3(
            (Float)getParents().get(0).evaluate(),
            (Float)getParents().get(1).evaluate(),
            (Float)getParents().get(2).evaluate());
      default:
        throw new IllegalStateException(
            "Vec3Expression cannot have more than 3 parents - found " + getParents().size());
    }
  }

  public FloatExpression getX() {
    switch(getParents().size()) {
      case 0: return new FloatExpression(mValue.getX());
      case 1: return ((Vec3Expression)getParents().get(0)).getX();
      case 3: return (FloatExpression)getParents().get(0);
      default:
        throw new IllegalStateException(
            "Vec2Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public FloatExpression getY() {
    switch(getParents().size()) {
      case 0: return new FloatExpression(mValue.getY());
      case 1: return ((Vec3Expression)getParents().get(0)).getY();
      case 3: return (FloatExpression)getParents().get(1);
      default:
        throw new IllegalStateException(
            "Vec2Expression cannot have more than 2 parents - found " + getParents().size());
    }
  }

  public FloatExpression getZ() {
    switch(getParents().size()) {
      case 0: return new FloatExpression(mValue.getZ());
      case 1: return ((Vec3Expression)getParents().get(0)).getZ();
      case 3: return (FloatExpression)getParents().get(2);
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
      default: throw new IndexOutOfBoundsException();
    }
  }
}
