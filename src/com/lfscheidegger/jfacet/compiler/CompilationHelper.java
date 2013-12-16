package com.lfscheidegger.jfacet.compiler;

class CompilationHelper {

  private static int sNameCounter = 0;

  public static String getUniqueName() {
    return "var_" + sNameCounter++;
  }
}
