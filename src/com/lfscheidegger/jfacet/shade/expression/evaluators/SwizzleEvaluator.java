package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.vector.swizzle.S;
import com.lfscheidegger.jfacet.shade.expression.vector.swizzle.SupportsSwizzling2;
import com.lfscheidegger.jfacet.shade.expression.vector.swizzle.SupportsSwizzling3;
import com.lfscheidegger.jfacet.shade.expression.vector.swizzle.SupportsSwizzling4;

public class SwizzleEvaluator<T> implements Evaluator<T> {

  private final Object mAccessors;
  private final Type mType;

  public SwizzleEvaluator(Object value, Type type) {
    mAccessors = value;
    mType = type;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T evaluate(Expression<T> expression) {
    Object vector = expression.getParents().get(0).evaluate();
    if (vector instanceof SupportsSwizzling2) {
      return evaluate2((SupportsSwizzling2)vector);
    } else if (vector instanceof SupportsSwizzling3) {
      return evaluate3((SupportsSwizzling3)vector);
    } else if (vector instanceof SupportsSwizzling4) {
      return evaluate4((SupportsSwizzling4)vector);
    } else {
      throw new RuntimeException("Unsupported expression for swizzling " + expression);
    }
  }

  @Override
  public String getGlSlString(Expression<T> expression, CompilationContext compilationContext) {
    return GlSlExpressionHelper.getSwizzleExpression(
        mType,
        expression.getGlSlString(compilationContext),
        mAccessors.toString());
  }

  @SuppressWarnings("unchecked")
  private T evaluate2(SupportsSwizzling2 parent) {
    if (mAccessors instanceof S.D21) {
      return (T)parent.swizzle((S.D21)mAccessors);
    } else if (mAccessors instanceof S.D22) {
      return (T)parent.swizzle((S.D22)mAccessors);
    } else if (mAccessors instanceof S.D23) {
      return (T)parent.swizzle((S.D23)mAccessors);
    } else {
      return (T)parent.swizzle((S.D24)mAccessors);
    }
  }

  @SuppressWarnings("unchecked")
  private T evaluate3(SupportsSwizzling3 parent) {
    if (mAccessors instanceof S.D31) {
      return (T)parent.swizzle((S.D31)mAccessors);
    } else if (mAccessors instanceof S.D32) {
      return (T)parent.swizzle((S.D32)mAccessors);
    } else if (mAccessors instanceof S.D33) {
      return (T)parent.swizzle((S.D33)mAccessors);
    } else {
      return (T)parent.swizzle((S.D34)mAccessors);
    }
  }

  @SuppressWarnings("unchecked")
  private T evaluate4(SupportsSwizzling4 parent) {
    if (mAccessors instanceof S.D41) {
      return (T)parent.swizzle((S.D41)mAccessors);
    } else if (mAccessors instanceof S.D42) {
      return (T)parent.swizzle((S.D42)mAccessors);
    } else if (mAccessors instanceof S.D43) {
      return (T)parent.swizzle((S.D43)mAccessors);
    } else {
      return (T)parent.swizzle((S.D44)mAccessors);
    }
  }
}
