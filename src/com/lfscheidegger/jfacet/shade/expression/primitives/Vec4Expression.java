package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

/**
 * {@code Expression} object encapsulating a 3-dimensional floating-point vector.
 */
public class Vec4Expression extends AbstractExpression {

  private static final Type TYPE = Type.VEC4_T;

  private Vec4 mValue;

  public Vec4Expression(Vec4 value) {
    super(TYPE, ImmutableList.<Expression>of());

    mValue = value;
  }

  public Vec4Expression(Expression xyzw) {
    super(TYPE, ImmutableList.<Expression>of(xyzw));

    Preconditions.checkArgument(xyzw.getType() == TYPE);
  }

  public Vec4Expression(Expression x, Expression y, Expression z, Expression w) {
    super(TYPE, ImmutableList.<Expression>of(x, y, z, w));

    Preconditions.checkArgument(
        x.getType() == Type.FLOAT_T &&
        y.getType() == Type.FLOAT_T &&
        z.getType() == Type.FLOAT_T &&
        w.getType() == Type.FLOAT_T);
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

  @Override
  public String getGlSlExpression() {
    return getGlSlExpression(mValue);
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
