package com.lfscheidegger.jfacet.shade.compiler;

import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public class GlSlExpressionHelper {

  public static String getWrappedExpression(Type type, String innerExpression) {
    return type + "(" + innerExpression + ")";
  }

  public static String getCommaExpression(Type type, String... expressions) {
    StringBuilder b = new StringBuilder();
    b.append(type).append("(");
    if (expressions.length > 0) {
      b.append(expressions[0]);
    }

    for (int i = 1; i < expressions.length; i++) {
      b.append(", ").append(expressions[i]);
    }

    return b.append(")").toString();
  }

  public static String getBinOpString(Type type, String opSymbol, String left, String right) {
    return getWrappedExpression(type, left + " " + opSymbol + " " + right);
  }

  public static String getComponentExpression(Type type, String exp, int idx) {
    return getWrappedExpression(type, exp + "[" + idx + "]");
  }
}
