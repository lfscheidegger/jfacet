package com.lfscheidegger.jfacet.shade.compiler;

import com.lfscheidegger.jfacet.shade.expression.Expression;

public class CompilationHelper {

  private static int uniqueNameCounter = 0;

  public static String getUniqueName() {
    return "_glsl_name" + uniqueNameCounter++;
  }

  public static String getFullStatement(String name, Expression exp, CompilationContext context) {
    return exp.getType() + " " + name + " = " + exp.getGlSlExpression(context);
  }

  public static String getAssignmentStatement(String name, Expression exp, CompilationContext context) {
    return name + " = " + exp.getGlSlExpression(context);
  }
}
