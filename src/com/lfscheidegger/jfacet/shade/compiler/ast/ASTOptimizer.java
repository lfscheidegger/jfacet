package com.lfscheidegger.jfacet.shade.compiler.ast;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.expression.*;

import java.util.List;

public class ASTOptimizer implements ASTProcessor {

  @Override
  public Expression process(Expression value) {
    if (value.getGlSlType() != GlSlType.DEFAULT_T) {
      return value;
    }

    if (isConstant(value)) {
      return wrap(value);
    }

    ImmutableList.Builder<Expression> parentBuilder = new ImmutableList.Builder<Expression>();
    for (Expression parent: (List<Expression>)value.getParents()) {
      parentBuilder.add(process(parent));
    }

    ((AbstractExpression)value).resetParents(parentBuilder.build());
    return value;
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
      case FLOAT_T: return new Real((Float)expression.evaluate());
      case VEC2_T: // fallthrough
      case VEC3_T: // fallthrough
      case VEC4_T: return new VectorExpression((Vector)expression.evaluate());
      case MAT2_T: // fallthrough
      case MAT3_T: // fallthrough
      case MAT4_T: return new MatrixExpression((Matrix)expression.evaluate());
      default:
        throw new RuntimeException("Can't wrap expression of type: " + expression.getType());
    }
  }
}
