package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.matrix.Matrix2;
import com.lfscheidegger.jfacet.shade.expression.matrix.Matrix3;
import com.lfscheidegger.jfacet.shade.expression.matrix.Matrix4;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;

public final class ComponentEvaluator<T> implements Evaluator<T> {

  private final int mIdx;

  public ComponentEvaluator(int idx) {
    mIdx = idx;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T evaluate(Expression expression) {
    Expression parent = (Expression)expression.getParents().get(0);

    if (parent instanceof Vector2) {
      return (T)Float.valueOf(((Vector2) parent).evaluate().get(mIdx));
    } else if (parent instanceof Vector3) {
      return (T)Float.valueOf(((Vector3) parent).evaluate().get(mIdx));
    } else if (parent instanceof Vector4) {
      return (T)Float.valueOf(((Vector4) parent).evaluate().get(mIdx));
    } else if (parent instanceof Matrix2) {
      return (T)((Matrix2)parent).evaluate().get(mIdx);
    } else if (parent instanceof Matrix3) {
      return (T)((Matrix3)parent).evaluate().get(mIdx);
    } else if (parent instanceof Matrix4) {
      return (T)((Matrix4)parent).evaluate().get(mIdx);
    }

    throw new RuntimeException("Cannot evaluate component expression for: " + expression.getClass().getSimpleName());
  }

  /*@Override
  public String getGlSlString(Expression<T> expression, CompilationContext context) {
    return GlSlExpressionHelper.getComponentExpression(
        expression.getType(), (expression.getParents().get(0)).getGlSlString(context), mIdx);

  }*/
}