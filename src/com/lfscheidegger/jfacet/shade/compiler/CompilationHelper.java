package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.base.Strings;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;

class CompilationHelper {

  private static final String PREFIX = "glsl_name";
  private static int sNameCounter = 0;
  public static synchronized String getUniqueGlSlName() {
    return PREFIX + sNameCounter++;
  }

  private static final int TAB_AMOUNT = 4;

  public static String getGlSlDeclarationStatement(
      GlSlType glSlType, Type type, String name, CompilationContext context) {
    return getGlSLQualifierForType(glSlType) + " " + type + " " + name + ";\n";
  }

  public static String getDeclarationAndAssignmentStatement(
      Type type, String name, String expression, CompilationContext context) {
    return type + " " + name + " = " + expression + ";\n";
  }

  public static String getAssignmentStatemenet(String name, String expression, CompilationContext context) {
    return name + " = " + expression + ";\n";
  }

  private static String getGlSLQualifierForType(GlSlType glSlType) {
    switch(glSlType) {
      case ATTRIBUTE_T:
        return "attribute";
      case UNIFORM_T:
        return "uniform";
      case VARYING_T:
        return "varying";
      case DEFAULT_T:
      default:
        return "";
    }
  }
}
