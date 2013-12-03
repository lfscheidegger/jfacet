package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.utils.StringUtils;

import java.util.Arrays;

public final class BVector3 extends AbstractExpression<BVector3.Primitive> {

  public static final class Primitive {

    private final boolean[] mValues;

    public Primitive(boolean x, boolean y, boolean z) {
      mValues = new boolean[] {x, y, z};
    }

    private Primitive(boolean[] other) {
      mValues = other;
    }

    public boolean getX() {
      return mValues[0];
    }

    public boolean getY() {
      return mValues[1];
    }

    public boolean getZ() {
      return mValues[2];
    }

    public boolean get(int idx) {
      return mValues[idx];
    }

    public boolean any() {
      return mValues[0] || mValues[1] || mValues[2];
    }

    public boolean all() {
      return mValues[0] && mValues[1] && mValues[2];
    }

    public Primitive not() {
      return new Primitive(new boolean[]{!mValues[0], !mValues[1], !mValues[2]});
    }

    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Primitive)) {
        return false;
      }

      return Arrays.equals(mValues, ((Primitive) other).mValues);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(mValues);
    }

    @Override
    public String toString() {
      return StringUtils.toStringHelper("bvec3")
          .addValue(mValues[0])
          .addValue(mValues[1])
          .addValue(mValues[2])
          .toString();
    }
  }

  private final Optional<Primitive> mPrimitive;

  public BVector3(boolean x, boolean y, boolean z) {
    super();
    mPrimitive = Optional.of(new Primitive(x, y, z));
  }

  public BVector3(Bool x, Bool y, Bool z) {
    super(ImmutableList.<Expression>of(x, y, z), NodeType.CONS);
    mPrimitive = Optional.absent();
  }

  public BVector3(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType);
    mPrimitive = Optional.absent();
  }

  @Override
  public BVector3 getExpressionForTernaryOperator(Bool condition, Expression<Primitive> elseExpression) {
    return new BVector3(
        ImmutableList.<Expression>of(condition, this, elseExpression),
        NodeType.TERNARY);
  }

  public Bool getX() {
    return get(0);
  }

  public Bool getY() {
    return get(1);
  }

  public Bool getZ() {
    return get(2);
  }

  public Bool get(int idx) {
    Preconditions.checkState(idx < 3);
    return new Bool(
        ImmutableList.<Expression>of(this),
        NodeType.ComponentNodeType.forComponent(idx));
  }

  public Bool any() {
    return new Bool(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("any"));
  }

  public Bool all() {
    return new Bool(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("all"));
  }

  public BVector3 not() {
    return new BVector3(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("not"));
  }
}
