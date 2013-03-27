package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.base.Preconditions;
import com.lfscheidegger.jfacet.shade.expression.Expression;

import java.util.ArrayList;
import java.util.List;

import static com.lfscheidegger.jfacet.shade.Type.*;

/**
 * Compiles a vertex shader.
 */
public class VertexShaderCompiler implements Compiler {

  private final Expression mPositionExpression;

  private List<String> mSource;

  public VertexShaderCompiler(Expression positionExpression) {
    Preconditions.checkArgument(
        positionExpression.getType().equals(VEC4_T),
        "Expression for position must be of type VEC4_T");

    mPositionExpression = positionExpression;
    mSource = new ArrayList<String>();
  }

  @Override
  public String compile() {
    mSource.add("precision highp float;\n");
    mSource.add("void main() {\n");
    mSource.add("}\n");

    return buildSource(mSource);
  }

  private String buildSource(List<String> source) {
    StringBuilder b = new StringBuilder();
    for (String line: source) {
      b.append(line);
    }

    return b.toString();
  }

  private String assignment(String name, String value) {
    return "    " + name + " = " + value + ";\n";
  }
}
