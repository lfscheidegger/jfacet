package com.lfscheidegger.jfacet.shade;

import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.operations.*;
import com.lfscheidegger.jfacet.shade.expression.primitives.*;
import com.lfscheidegger.jfacet.shade.primitives.*;

/**
 * Convenience methods to promote primitive types into expressions
 */
public class Shade {

  public static Expression vec(Vec2 vec) {
    return new Vec2Expression(vec);
  }

  public static Expression vec(Vec3 vec) {
    return new Vec3Expression(vec);
  }

  public static Expression vec(Vec4 vec) {
    return new Vec4Expression(vec);
  }

  public static Expression vec(Object x, Object y) {
    return new Vec2Expression(promote(x), promote(y));
  }

  public static Expression vec(Object x, Object y, Object z) {
    return new Vec3Expression(promote(x), promote(y), promote(z));
  }

  public static Expression vec(Object x, Object y, Object z, Object w) {
    return new Vec4Expression(promote(x), promote(y), promote(z), promote(w));
  }

  public static Expression mat(Mat2 mat) {
    return new Mat2Expression(mat);
  }

  public static Expression mat(Mat3 mat) {
    return new Mat3Expression(mat);
  }

  public static Expression mat(Mat4 mat) {
    return new Mat4Expression(mat);
  }

  public static Expression mat(Object c0, Object c1) {
    if (c0 instanceof Vec2) {
      c0 = new Vec2Expression((Vec2)c0);
    }
    if (c1 instanceof Vec2) {
      c1 = new Vec2Expression((Vec2)c1);
    }

    return new Mat2Expression((Vec2Expression)c0, (Vec2Expression)c1);
  }

  public static Expression mat(Object c0, Object c1, Object c2) {
    if (c0 instanceof Vec3) {
      c0 = new Vec3Expression((Vec3)c0);
    }
    if (c1 instanceof Vec3) {
      c1 = new Vec3Expression((Vec3)c1);
    }
    if (c2 instanceof Vec3) {
      c2 = new Vec3Expression((Vec3)c2);
    }

    return new Mat3Expression((Vec3Expression)c0, (Vec3Expression)c1, (Vec3Expression)c2);
  }

  public static Expression mat(Object c0, Object c1, Object c2, Object c3) {
    if (c0 instanceof Vec4) {
      c0 = new Vec4Expression((Vec4)c0);
    }
    if (c1 instanceof Vec4) {
      c1 = new Vec4Expression((Vec4)c1);
    }
    if (c2 instanceof Vec4) {
      c2 = new Vec4Expression((Vec4)c2);
    }
    if (c3 instanceof Vec4) {
      c3 = new Vec4Expression((Vec4)c3);
    }

    return new Mat4Expression((Vec4Expression)c0, (Vec4Expression)c1, (Vec4Expression)c2, (Vec4Expression)c3);
  }

  public static Expression add(Expression left, Expression right) {
    return new AddExpression(left, right);
  }

  public static Expression sub(Expression left, Expression right) {
    return new SubExpression(left, right);
  }

  public static Expression mul(Expression left, Expression right) {
    return new MulExpression(left, right);
  }

  public static Expression div(Expression left, Expression right) {
    return new DivExpression(left, right);
  }

  public static Expression neg(Expression exp) {
    return new NegExpression(exp);
  }

  private static Expression promote(Object v) {
    if (v instanceof FloatExpression) {
      return (FloatExpression)v;
    }

    return new FloatExpression((Float)v);
  }
}
