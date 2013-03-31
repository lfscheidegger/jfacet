package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec2Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec3Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec4Exp;

import java.util.HashMap;
import java.util.Map;

public class VertexShaderCompiler {

  private final Expression mVertexPosition;
  private final CompilationContext mContext = new CompilationContext() {

    private final Map<Expression, String> mExpressionNameMap = new HashMap<Expression, String>();

    @Override
    public String getExpressionName(Expression exp) {
      if (!mExpressionNameMap.containsKey(exp)) {
        mExpressionNameMap.put(exp, CompilationHelper.getUniqueGlSlName());
      }

      return mExpressionNameMap.get(exp);
    }
  };

  public VertexShaderCompiler(Expression vertexPosition) {
    mVertexPosition = promote(vertexPosition);
  }

  private Vec4Exp promote(Expression vertexPosition) {
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

  public String compile() {

    StringBuilder mainBodyStringBuilder = new StringBuilder();
    mainBodyStringBuilder.append("void main() {\n");

    ImmutableList<Expression> sortedExpressions = new TopologicalSorter(mVertexPosition).sort();
    for (Expression expression: sortedExpressions) {
      mainBodyStringBuilder.append(CompilationHelper.getDeclarationAndAssignmentStatement(
          expression.getType(),
          mContext.getExpressionName(expression),
          expression.getGlSlString(mContext)));
    }

    mainBodyStringBuilder.append(CompilationHelper.getAssignmentStatemenet(
        "gl_Position", mContext.getExpressionName(mVertexPosition)
    ));

    mainBodyStringBuilder.append("}\n");
    return mainBodyStringBuilder.toString();
  }
}
