package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.google.common.collect.ImmutableMap;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.compiler.GlSlExpressionHelper;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.VectorPrimitive;

public class SwizzleEvaluator<T> implements Evaluator<T> {

  private final String mSwizzleParam;

  private final ImmutableMap<Character, Integer> mSwizzleMap =
      new ImmutableMap.Builder<Character, Integer>()
          .put('x', 0).put('y', 1).put('z', 2).put('w', 3)
          .put('r', 0).put('g', 1).put('b', 2).put('a', 3)
          .put('s', 0).put('t', 1).put('p', 2).put('q', 3).build();

  public SwizzleEvaluator(String swizzleParam) {
    mSwizzleParam = swizzleParam;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T evaluate(Expression<T> expression) {

    VectorPrimitive primitive = ((VectorPrimitive)expression.evaluate());
     switch(mSwizzleParam.length()) {
      case 1:
        return (T)(Float.valueOf(primitive.get(mSwizzleMap.get(mSwizzleParam.charAt(0)))));
      case 2:
        return (T)(new Vec2(
            primitive.get(mSwizzleMap.get(mSwizzleParam.charAt(0))),
            primitive.get(mSwizzleMap.get(mSwizzleParam.charAt(1)))));
       case 3:
         return (T)(new Vec3(
             primitive.get(mSwizzleMap.get(mSwizzleParam.charAt(0))),
             primitive.get(mSwizzleMap.get(mSwizzleParam.charAt(1))),
             primitive.get(mSwizzleMap.get(mSwizzleParam.charAt(2)))));
       case 4:
         return (T)(new Vec4(
             primitive.get(mSwizzleMap.get(mSwizzleParam.charAt(0))),
             primitive.get(mSwizzleMap.get(mSwizzleParam.charAt(1))),
             primitive.get(mSwizzleMap.get(mSwizzleParam.charAt(2))),
             primitive.get(mSwizzleMap.get(mSwizzleParam.charAt(3)))));
     }

    throw new RuntimeException("Invalid length for swizzling: " + mSwizzleParam.length());
  }

  @Override
  public String getGlSlString(Expression<T> expression, CompilationContext compilationContext) {
    Type type =
        (mSwizzleParam.length() == 1) ? Type.FLOAT_T :
        (mSwizzleParam.length() == 2) ? Type.VEC2_T :
        (mSwizzleParam.length() == 3) ? Type.VEC3_T : Type.VEC4_T;
    return GlSlExpressionHelper.getWrappedExpression(
        type, expression.getGlSlString(compilationContext) + "." + mSwizzleParam);
  }
}
