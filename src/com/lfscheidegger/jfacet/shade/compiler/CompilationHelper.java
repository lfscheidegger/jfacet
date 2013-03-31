package com.lfscheidegger.jfacet.shade.compiler;

import com.lfscheidegger.jfacet.shade.Type;

class CompilationHelper {

  private static final String PREFIX = "glsl_name";
  private static int sNameCounter = 0;
  public static synchronized String getUniqueGlSlName() {
    return PREFIX + sNameCounter++;
  }

  public static String getDeclarationAndAssignmentStatement(Type type, String name, String expression) {
    return "    " + type + " " + name + " = " + expression + ";\n";
  }

  public static String getAssignmentStatemenet(String name, String expression) {
    return "    " + name + " = " + expression + ";\n";
  }
}
