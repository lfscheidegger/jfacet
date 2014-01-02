package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.utils.StringHelper;

import java.util.Arrays;

public final class BVec2 extends Expression {

  private static final String GLSL_TYPE_NAME = "bvec2";

  public static final class Primitive implements BVecPrimitive {

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

    public Swizzle.Swizzle21XYZW<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive> x() {
      return new Swizzle.Swizzle21XYZW<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive>("x", this);
    }

    public Swizzle.Swizzle21XYZW<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive> y() {
      return new Swizzle.Swizzle21XYZW<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive>("y", this);
    }

    public Swizzle.Swizzle21RGBA<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive> r() {
      return new Swizzle.Swizzle21RGBA<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive>("r", this);
    }

    public Swizzle.Swizzle21RGBA<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive> g() {
      return new Swizzle.Swizzle21RGBA<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive>("g", this);
    }

    public Swizzle.Swizzle21STPQ<Boolean, BVec2.Primitive, Vec3.Primitive, BVec4.Primitive> s() {
      return new Swizzle.Swizzle21STPQ<Boolean, BVec2.Primitive, Vec3.Primitive, BVec4.Primitive>("s", this);
    }

    public Swizzle.Swizzle21STPQ<Boolean, BVec2.Primitive, Vec3.Primitive, BVec4.Primitive> t() {
      return new Swizzle.Swizzle21STPQ<Boolean, BVec2.Primitive, Vec3.Primitive, BVec4.Primitive>("t", this);
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
      return new StringHelper(GLSL_TYPE_NAME)
          .addValue(mValues[0])
          .addValue(mValues[1])
          .toString();
    }
  }

  public BVec2(boolean x, boolean y) {
    super(new Primitive(x, y), GLSL_TYPE_NAME);
  }

  public BVec2(Bool x, Bool y) {
    super(ImmutableList.<Expression>of(x, y), NodeType.CONS, GLSL_TYPE_NAME);
  }

  public BVec2(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType, GLSL_TYPE_NAME);
  }

  public Bool getX() {
    return get(0);
  }

  public Bool getY() {
    return get(1);
  }

  public Bool get(int idx) {
    Preconditions.checkState(idx < 2);
    return new Bool(
        ImmutableList.<Expression>of(this),
        NodeType.ComponentNodeType.forComponent(idx));
  }

  public Swizzle.Swizzle21XYZW<Bool, BVec2, BVec3, BVec4> x() {
    return new Swizzle.Swizzle21XYZW<Bool, BVec2, BVec3, BVec4>("x", this);
  }

  public Swizzle.Swizzle21XYZW<Bool, BVec2, BVec3, BVec4> y() {
    return new Swizzle.Swizzle21XYZW<Bool, BVec2, BVec3, BVec4>("y", this);
  }

  public Swizzle.Swizzle21RGBA<Bool, BVec2, BVec3, BVec4> r() {
    return new Swizzle.Swizzle21RGBA<Bool, BVec2, BVec3, BVec4>("r", this);
  }

  public Swizzle.Swizzle21RGBA<Bool, BVec2, BVec3, BVec4> g() {
    return new Swizzle.Swizzle21RGBA<Bool, BVec2, BVec3, BVec4>("g", this);
  }

  public Swizzle.Swizzle21STPQ<Bool, BVec2, Vec3, BVec4> s() {
    return new Swizzle.Swizzle21STPQ<Bool, BVec2, Vec3, BVec4>("s", this);
  }

  public Swizzle.Swizzle21STPQ<Bool, BVec2, Vec3, BVec4> t() {
    return new Swizzle.Swizzle21STPQ<Bool, BVec2, Vec3, BVec4>("t", this);
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

  public BVec2 not() {
    return new BVec2(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("not"));
  }
}
