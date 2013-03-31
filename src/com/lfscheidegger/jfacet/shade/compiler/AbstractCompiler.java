package com.lfscheidegger.jfacet.shade.compiler;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec2Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec3Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec4Exp;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCompiler implements Compiler {

  private final CompilationContext mContext = new CompilationContext() {

    private final Map<Expression, String> mExpressionNameMap = new HashMap<Expression, String>();
    private int mScopeLevel = 0;

    @Override
    public String getExpressionName(Expression exp) {
      if (!mExpressionNameMap.containsKey(exp)) {
        mExpressionNameMap.put(exp, CompilationHelper.getUniqueGlSlName());
      }

      return mExpressionNameMap.get(exp);
    }

    @Override
    public int getScopeLevel() {
      return mScopeLevel;
    }

    @Override
    public void pushScope() {
      mScopeLevel++;
    }

    @Override
    public void popScope() {
      mScopeLevel = (mScopeLevel > 0) ? mScopeLevel - 1: 0;
    }
  };

  protected final CompilationContext getContext() {
    return mContext;
  }

  protected final Vec4Exp promote(Expression vertexPosition) {
    if (vertexPosition instanceof FloatExp) {
      return Shade.vec((FloatExp)vertexPosition, 0, 0, 1);
    } else if (vertexPosition instanceof Vec2Exp) {
      FloatExp x = ((Vec2Exp) vertexPosition).getX();
      FloatExp y = ((Vec2Exp) vertexPosition).getY();
      return Shade.vec(x, y, 0, 1);
    } else if (vertexPosition instanceof Vec3Exp) {
      FloatExp x = ((Vec3Exp) vertexPosition).getX();
      FloatExp y = ((Vec3Exp) vertexPosition).getY();
      FloatExp z = ((Vec3Exp) vertexPosition).getZ();
      return Shade.vec(x, y, z, 1);
    } else if (vertexPosition instanceof Vec4Exp) {
      return (Vec4Exp)vertexPosition;
    }

    throw new IllegalArgumentException("Expression for vertex position cannot be of type " + vertexPosition.getType());
  }

  protected final String wrapInMain(String statements) {
    return "void main() {\n" + statements + "}\n";
  }
}
