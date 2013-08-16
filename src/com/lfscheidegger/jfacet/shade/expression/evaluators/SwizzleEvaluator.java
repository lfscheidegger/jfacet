package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.vector.VectorPrimitive;

public class SwizzleEvaluator<T> implements Evaluator<T> {

  private final int mDimension;
  private final char[] mAccessors;

  public SwizzleEvaluator(char x) {
    mDimension = 1;
    mAccessors = new char[]{x};
  }

  public SwizzleEvaluator(char x, char y) {
    mDimension = 2;
    mAccessors = new char[]{x, y};
  }

  public SwizzleEvaluator(char x, char y, char z) {
    mDimension = 3;
    mAccessors = new char[]{x, y, z};
  }

  public SwizzleEvaluator(char x, char y, char z, char w) {
    mDimension = 4;
    mAccessors = new char[]{x, y, z, w};
  }

  @Override
  @SuppressWarnings("unchecked")
  public T evaluate(Expression<T> expression) {
    VectorPrimitive vector = (VectorPrimitive)expression.getParents().get(0).evaluate();
    switch(mDimension) {
      case 1: return (T)(Float.valueOf(vector.swizzle(mAccessors[0])));
      case 2: return (T)vector.swizzle(mAccessors[0], mAccessors[1]);
      case 3: return (T)vector.swizzle(mAccessors[0], mAccessors[1], mAccessors[2]);
      case 4: return (T)vector.swizzle(mAccessors[0], mAccessors[1], mAccessors[2], mAccessors[3]);
      default: throw new RuntimeException("Unsupported dimension for swizzling: " + mDimension);
    }
  }

  @Override
  public String getGlSlString(Expression<T> expression, CompilationContext compilationContext) {
    Type type;
    switch(mDimension) {
      case 1: type = Type.FLOAT_T; break;
      case 2: type = Type.VEC2_T; break;
      case 3: type = Type.VEC3_T; break;
      case 4: type = Type.VEC4_T; break;
      default: throw new RuntimeException("Unsupported dimension for swizzling: " + mDimension);
    }

    return GlSlExpressionHelper.getSwizzleExpression(type, expression.getGlSlString(compilationContext), mAccessors);
  }
}
