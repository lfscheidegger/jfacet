// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.utils.StringHelper;

import java.util.Arrays;

public final class BVec3 extends Expression {

  private static final String GLSL_TYPE_NAME = "bvec3";

  public static final class Primitive implements BVecPrimitive {

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

    @Override
    public boolean get(int idx) {
      return mValues[idx];
    }

    public Swizzle.Swizzle31XYZW<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive> x() {
      return new Swizzle.Swizzle31XYZW<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive>("x", this);
    }

    public Swizzle.Swizzle31XYZW<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive> y() {
      return new Swizzle.Swizzle31XYZW<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive>("y", this);
    }

    public Swizzle.Swizzle31XYZW<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive> z() {
      return new Swizzle.Swizzle31XYZW<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive>("z", this);
    }

    public Swizzle.Swizzle31RGBA<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive> r() {
      return new Swizzle.Swizzle31RGBA<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive>("r", this);
    }

    public Swizzle.Swizzle31RGBA<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive> g() {
      return new Swizzle.Swizzle31RGBA<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive>("g", this);
    }

    public Swizzle.Swizzle31RGBA<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive> b() {
      return new Swizzle.Swizzle31RGBA<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive>("b", this);
    }

    public Swizzle.Swizzle31STPQ<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive> s() {
      return new Swizzle.Swizzle31STPQ<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive>("s", this);
    }

    public Swizzle.Swizzle31STPQ<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive> t() {
      return new Swizzle.Swizzle31STPQ<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive>("t", this);
    }

    public Swizzle.Swizzle31STPQ<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive> p() {
      return new Swizzle.Swizzle31STPQ<Boolean, BVec2.Primitive, BVec3.Primitive, BVec4.Primitive>("p", this);
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
      return new StringHelper(GLSL_TYPE_NAME)
          .addValue(mValues[0])
          .addValue(mValues[1])
          .addValue(mValues[2])
          .toString();
    }
  }

  public BVec3(boolean x, boolean y, boolean z) {
    super(new Primitive(x, y, z), GLSL_TYPE_NAME);
  }

  public BVec3(Bool x, Bool y, Bool z) {
    super(ImmutableList.<Expression>of(x, y, z), NodeType.CONS, GLSL_TYPE_NAME);
  }

  public BVec3(ImmutableList<Expression> parents, NodeType nodeType) {
    super(parents, nodeType, GLSL_TYPE_NAME);
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

  public Swizzle.Swizzle31XYZW<Bool, BVec2, BVec3, BVec4> x() {
    return new Swizzle.Swizzle31XYZW<Bool, BVec2, BVec3, BVec4>("x", this);
  }

  public Swizzle.Swizzle31XYZW<Bool, BVec2, BVec3, BVec4> y() {
    return new Swizzle.Swizzle31XYZW<Bool, BVec2, BVec3, BVec4>("y", this);
  }

  public Swizzle.Swizzle31XYZW<Bool, BVec2, BVec3, BVec4> z() {
    return new Swizzle.Swizzle31XYZW<Bool, BVec2, BVec3, BVec4>("z", this);
  }

  public Swizzle.Swizzle31RGBA<Bool, BVec2, BVec3, BVec4> r() {
    return new Swizzle.Swizzle31RGBA<Bool, BVec2, BVec3, BVec4>("r", this);
  }

  public Swizzle.Swizzle31RGBA<Bool, BVec2, BVec3, BVec4> g() {
    return new Swizzle.Swizzle31RGBA<Bool, BVec2, BVec3, BVec4>("g", this);
  }

  public Swizzle.Swizzle31RGBA<Bool, BVec2, BVec3, BVec4> b() {
    return new Swizzle.Swizzle31RGBA<Bool, BVec2, BVec3, BVec4>("b", this);
  }

  public Swizzle.Swizzle31STPQ<Bool, BVec2, BVec3, BVec4> s() {
    return new Swizzle.Swizzle31STPQ<Bool, BVec2, BVec3, BVec4>("s", this);
  }

  public Swizzle.Swizzle31STPQ<Bool, BVec2, BVec3, BVec4> t() {
    return new Swizzle.Swizzle31STPQ<Bool, BVec2, BVec3, BVec4>("t", this);
  }

  public Swizzle.Swizzle31STPQ<Bool, BVec2, BVec3, BVec4> p() {
    return new Swizzle.Swizzle31STPQ<Bool, BVec2, BVec3, BVec4>("p", this);
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

  public BVec3 not() {
    return new BVec3(
        ImmutableList.<Expression>of(this),
        NodeType.FunctionNodeType.forFunction("not"));
  }
}
