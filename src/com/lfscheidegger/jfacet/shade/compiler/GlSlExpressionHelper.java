package com.lfscheidegger.jfacet.shade.compiler;

import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public class GlSlExpressionHelper {

  public static String getWrappedExpression(Type type, String innerExpression) {
    return type + "(" + innerExpression + ")";
  }

  public static String getCommaExpression(Type type, Expression... expressions) {
    StringBuilder b = new StringBuilder();
    b.append(type).append("(");
    if (expressions.length > 0) {
      b.append(expressions[0].getGlSlString());
    }

    for (int i = 1; i < expressions.length; i++) {
      b.append(", ").append(expressions[i].getGlSlString());
    }

    return b.append(")").toString();
  }

  public static String getBinOpString(Type type, String opSymbol, Expression left, Expression right) {
    return type + "(" + left.getGlSlString() + " " + opSymbol + " " + right.getGlSlString() + ")";
  }

  public static String getComponentExpression(Type type, Expression exp, int idx) {
    return type + "(" + exp.getGlSlString() + "[" + idx + "])";
  }
}
