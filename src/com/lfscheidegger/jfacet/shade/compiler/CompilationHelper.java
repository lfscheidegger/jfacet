package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.base.Strings;
import com.lfscheidegger.jfacet.shade.Type;

class CompilationHelper {

  private static final String PREFIX = "glsl_name";
  private static int sNameCounter = 0;
  public static synchronized String getUniqueGlSlName() {
    return PREFIX + sNameCounter++;
  }

  private static final int TAB_AMOUNT = 4;

  public static String getGlSlDeclarationStatement(
      Type type, String glSlQualifier, String name, CompilationContext context) {
    return Strings.repeat(" ", TAB_AMOUNT * context.getScopeLevel()) + glSlQualifier + " " + type + " " + name + ";\n";
  }

  public static String getDeclarationAndAssignmentStatement(
      Type type, String name, String expression, CompilationContext context) {
    return Strings.repeat(" ", TAB_AMOUNT * context.getScopeLevel()) + type + " " + name + " = " + expression + ";\n";
  }

  public static String getAssignmentStatemenet(String name, String expression, CompilationContext context) {
    return Strings.repeat(" ", TAB_AMOUNT * context.getScopeLevel()) + name + " = " + expression + ";\n";
  }
}
