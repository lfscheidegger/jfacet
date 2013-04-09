package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.*;
import com.lfscheidegger.jfacet.shade.primitives.*;

import java.util.List;

public class ASTOptimizer {

  public Expression optimize(Expression value) {
    if (value.getGlSlType() != GlSlType.DEFAULT_T) {
      return value;
    }

    if (isConstant(value)) {
      return wrap(value);
    }

    ImmutableList.Builder<Expression> parentBuilder = new ImmutableList.Builder<Expression>();
    for (Expression parent: (List<Expression>)value.getParents()) {
      parentBuilder.add(optimize(parent));
    }

    return new AbstractExpression(value.getType(), value.getGlSlType(), parentBuilder.build(), value.getEvaluator()) {};
  }

  private boolean isConstant(Expression expression) {
    if (expression.getGlSlType() != GlSlType.DEFAULT_T) {
      return false;
    }

    ImmutableList<Expression> parents = expression.getParents();

    if (parents.size() == 0) {
      return expression.getGlSlType() == GlSlType.DEFAULT_T;
    }

    for (Expression parent: parents) {
      if (!isConstant(parent)) {
        return false;
      }
    }

    return true;
  }

  private Expression wrap(Expression expression) {
    switch(expression.getType()) {
      case FLOAT_T: return new FloatExp((Float)expression.evaluate());
      case VEC2_T: return new Vec2Exp((Vec2)expression.evaluate());
      case VEC3_T: return new Vec3Exp((Vec3)expression.evaluate());
      case VEC4_T: return new Vec4Exp((Vec4)expression.evaluate());
      case MAT2_T: return new Mat2Exp((Mat2)expression.evaluate());
      case MAT3_T: return new Mat3Exp((Mat3)expression.evaluate());
      case MAT4_T: return new Mat4Exp((Mat4)expression.evaluate());
      default:
        throw new RuntimeException("Can't wrap expression of type: " + expression.getType());
    }
  }
}
