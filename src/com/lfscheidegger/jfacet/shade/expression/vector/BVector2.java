package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.NodeType;
import com.lfscheidegger.jfacet.utils.StringUtils;

import java.util.Arrays;

public final class BVector2 extends AbstractExpression implements VectorExpression<Bool, BVector4> {

  public static final class Primitive implements BVectorPrimitive {

    private final boolean[] mValues;

    public Primitive(boolean x, boolean y) {
      mValues = new boolean[] {x, y};
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

    @Override
    public boolean get(int idx) {
      return mValues[idx];
    }

    public Swizzle.Swizzle21XYZW<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive> x() {
      return new Swizzle.Swizzle21XYZW<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive>("x", this);
    }

    public Swizzle.Swizzle21XYZW<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive> y() {
      return new Swizzle.Swizzle21XYZW<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive>("y", this);
    }

    public Swizzle.Swizzle21RGBA<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive> r() {
      return new Swizzle.Swizzle21RGBA<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive>("r", this);
    }

    public Swizzle.Swizzle21RGBA<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive> g() {
      return new Swizzle.Swizzle21RGBA<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive>("g", this);
    }

    public Swizzle.Swizzle21STPQ<Boolean, BVector2.Primitive, Vector3.Primitive, BVector4.Primitive> s() {
      return new Swizzle.Swizzle21STPQ<Boolean, BVector2.Primitive, Vector3.Primitive, BVector4.Primitive>("s", this);
    }

    public Swizzle.Swizzle21STPQ<Boolean, BVector2.Primitive, Vector3.Primitive, BVector4.Primitive> t() {
      return new Swizzle.Swizzle21STPQ<Boolean, BVector2.Primitive, Vector3.Primitive, BVector4.Primitive>("t", this);
    }

    public boolean any() {
      return mValues[0] || mValues[1];
    }

    public boolean all() {
      return mValues[0] && mValues[1];
    }

    public Primitive not() {
      return new Primitive(new boolean[]{!mValues[0], !mValues[1]});
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
      return StringUtils.toStringHelper("bvec2")
          .addValue(mValues[0])
          .addValue(mValues[1])
          .toString();
    }
  }

  public BVector2(boolean x, boolean y) {
    super(new Primitive(x, y));
  }

  public BVector2(Bool x, Bool y) {
    super(ImmutableList.<Expression>of(x, y), NodeType.CONS);
  }

  public BVector2(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType);
  }

  public Bool getX() {
    return get(0);
  }

  public Bool getY() {
    return get(1);
  }

  @Override
  public Bool get(int idx) {
    Preconditions.checkState(idx < 2);
    return new Bool(
        ImmutableList.<Expression>of(this),
        NodeType.ComponentNodeType.forComponent(idx));
  }

  @Override
  public String getGlSlTypeName() {
    return "bvec2";
  }

  public Swizzle.Swizzle21XYZW<Bool, BVector2, BVector3, BVector4> x() {
    return new Swizzle.Swizzle21XYZW<Bool, BVector2, BVector3, BVector4>("x", this);
  }

  public Swizzle.Swizzle21XYZW<Bool, BVector2, BVector3, BVector4> y() {
    return new Swizzle.Swizzle21XYZW<Bool, BVector2, BVector3, BVector4>("y", this);
  }

  public Swizzle.Swizzle21RGBA<Bool, BVector2, BVector3, BVector4> r() {
    return new Swizzle.Swizzle21RGBA<Bool, BVector2, BVector3, BVector4>("r", this);
  }

  public Swizzle.Swizzle21RGBA<Bool, BVector2, BVector3, BVector4> g() {
    return new Swizzle.Swizzle21RGBA<Bool, BVector2, BVector3, BVector4>("g", this);
  }

  public Swizzle.Swizzle21STPQ<Bool, BVector2, Vector3, BVector4> s() {
    return new Swizzle.Swizzle21STPQ<Bool, BVector2, Vector3, BVector4>("s", this);
  }

  public Swizzle.Swizzle21STPQ<Bool, BVector2, Vector3, BVector4> t() {
    return new Swizzle.Swizzle21STPQ<Bool, BVector2, Vector3, BVector4>("t", this);
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

  public BVector2 not() {
    return new BVector2(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("not"));
  }

  @Override
  public BVector4 fill(BVector4 defaultExpression) {
    return new BVector4(getX(), getY(), defaultExpression.getZ(), defaultExpression.getW());
  }
}
