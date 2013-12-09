package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.utils.StringUtils;

import java.util.Arrays;

public final class BVector4 extends AbstractExpression<BVector4.Primitive>
    implements VectorExpression<Bool> {

  public static final class Primitive implements BVectorPrimitive {

    private final boolean[] mValues;

    public Primitive(boolean x, boolean y, boolean z, boolean w) {
      mValues = new boolean[] {x, y, z, w};
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

    public boolean getW() {
      return mValues[3];
    }

    @Override
    public boolean get(int idx) {
      return mValues[idx];
    }

    public Swizzle.Swizzle41XYZW<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive> x() {
      return new Swizzle.Swizzle41XYZW<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive>("x", this);
    }

    public Swizzle.Swizzle41XYZW<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive> y() {
      return new Swizzle.Swizzle41XYZW<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive>("y", this);
    }

    public Swizzle.Swizzle41XYZW<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive> z() {
      return new Swizzle.Swizzle41XYZW<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive>("z", this);
    }

    public Swizzle.Swizzle41XYZW<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive> w() {
      return new Swizzle.Swizzle41XYZW<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive>("w", this);
    }

    public Swizzle.Swizzle41RGBA<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive> r() {
      return new Swizzle.Swizzle41RGBA<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive>("r", this);
    }

    public Swizzle.Swizzle41RGBA<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive> g() {
      return new Swizzle.Swizzle41RGBA<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive>("g", this);
    }

    public Swizzle.Swizzle41RGBA<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive> b() {
      return new Swizzle.Swizzle41RGBA<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive>("b", this);
    }

    public Swizzle.Swizzle41RGBA<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive> a() {
      return new Swizzle.Swizzle41RGBA<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive>("a", this);
    }

    public Swizzle.Swizzle41STPQ<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive> s() {
      return new Swizzle.Swizzle41STPQ<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive>("s", this);
    }

    public Swizzle.Swizzle41STPQ<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive> t() {
      return new Swizzle.Swizzle41STPQ<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive>("t", this);
    }

    public Swizzle.Swizzle41STPQ<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive> p() {
      return new Swizzle.Swizzle41STPQ<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive>("p", this);
    }

    public Swizzle.Swizzle41STPQ<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive> q() {
      return new Swizzle.Swizzle41STPQ<Boolean, BVector2.Primitive, BVector3.Primitive, BVector4.Primitive>("q", this);
    }

    public boolean any() {
      return mValues[0] || mValues[1] || mValues[2] || mValues[3];
    }

    public boolean all() {
      return mValues[0] && mValues[1] && mValues[2] && mValues[3];
    }

    public Primitive not() {
      return new Primitive(new boolean[]{!mValues[0], !mValues[1], !mValues[2], !mValues[3]});
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
      return StringUtils.toStringHelper("bvec4")
          .addValue(mValues[0])
          .addValue(mValues[1])
          .addValue(mValues[2])
          .addValue(mValues[3])
          .toString();
    }
  }

  private final Optional<Primitive> mPrimitive;

  public BVector4(boolean x, boolean y, boolean z, boolean w) {
    super();
    mPrimitive = Optional.of(new Primitive(x, y, z, w));
  }

  public BVector4(Bool x, Bool y, Bool z, Bool w) {
    super(ImmutableList.<Expression>of(x, y, z, w), NodeType.CONS);
    mPrimitive = Optional.absent();
  }

  public BVector4(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType);
    mPrimitive = Optional.absent();
  }

  @Override
  public BVector4 getExpressionForTernaryOperator(Bool condition, Expression<Primitive> elseExpression) {
    return new BVector4(
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

  public Bool getW() {
    return get(3);
  }

  @Override
  public Bool get(int idx) {
    Preconditions.checkState(idx < 4);
    return new Bool(
        ImmutableList.<Expression>of(this),
        NodeType.ComponentNodeType.forComponent(idx));
  }

  public Swizzle.Swizzle41XYZW<Bool, BVector2, BVector3, BVector4> x() {
    return new Swizzle.Swizzle41XYZW<Bool, BVector2, BVector3, BVector4>("x", this);
  }

  public Swizzle.Swizzle41XYZW<Bool, BVector2, BVector3, BVector4> y() {
    return new Swizzle.Swizzle41XYZW<Bool, BVector2, BVector3, BVector4>("y", this);
  }

  public Swizzle.Swizzle41XYZW<Bool, BVector2, BVector3, BVector4> z() {
    return new Swizzle.Swizzle41XYZW<Bool, BVector2, BVector3, BVector4>("z", this);
  }

  public Swizzle.Swizzle41XYZW<Bool, BVector2, BVector3, BVector4> w() {
    return new Swizzle.Swizzle41XYZW<Bool, BVector2, BVector3, BVector4>("w", this);
  }

  public Swizzle.Swizzle41RGBA<Bool, BVector2, BVector3, BVector4> r() {
    return new Swizzle.Swizzle41RGBA<Bool, BVector2, BVector3, BVector4>("r", this);
  }

  public Swizzle.Swizzle41RGBA<Bool, BVector2, BVector3, BVector4> g() {
    return new Swizzle.Swizzle41RGBA<Bool, BVector2, BVector3, BVector4>("g", this);
  }

  public Swizzle.Swizzle41RGBA<Bool, BVector2, BVector3, BVector4> b() {
    return new Swizzle.Swizzle41RGBA<Bool, BVector2, BVector3, BVector4>("b", this);
  }

  public Swizzle.Swizzle41RGBA<Bool, BVector2, BVector3, BVector4> a() {
    return new Swizzle.Swizzle41RGBA<Bool, BVector2, BVector3, BVector4>("a", this);
  }

  public Swizzle.Swizzle41STPQ<Bool, BVector2, BVector3, BVector4> s() {
    return new Swizzle.Swizzle41STPQ<Bool, BVector2, BVector3, BVector4>("s", this);
  }

  public Swizzle.Swizzle41STPQ<Bool, BVector2, BVector3, BVector4> t() {
    return new Swizzle.Swizzle41STPQ<Bool, BVector2, BVector3, BVector4>("t", this);
  }

  public Swizzle.Swizzle41STPQ<Bool, BVector2, BVector3, BVector4> p() {
    return new Swizzle.Swizzle41STPQ<Bool, BVector2, BVector3, BVector4>("p", this);
  }

  public Swizzle.Swizzle41STPQ<Bool, BVector2, BVector3, BVector4> q() {
    return new Swizzle.Swizzle41STPQ<Bool, BVector2, BVector3, BVector4>("q", this);
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

  public BVector4 not() {
    return new BVector4(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("not"));
  }
}
