package com.lfscheidegger.jfacet.compiler;

import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;

public class FragmentShaderCompiler {

  private final Vector4 mFragmentColor;

  public FragmentShaderCompiler(Vector4 fragmentColor) {
    mFragmentColor = fragmentColor;
  }

  public String compile() {
    StringBuilder sb = new StringBuilder();
    sb.append("precision highp float;\n");

    sb.append("void main() {\n");
    sb.append("gl_FragColor = vec4(1, 0, 0, 1);\n");
    sb.append("}\n");

    return sb.toString();
  }
}
