package com.lfscheidegger.jfacet.shade.compiler;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExpression;
import org.junit.Test;

/**
 * Unit tests for {@code VertexShaderCompiler}
 */
public class VertexShaderCompilerTest {

  @Test
  public void testVertexShaderCompiler() {
    Expression a = new FloatExpression(3);

    Expression b = new FloatExpression(a);
    Expression c = new FloatExpression(a);

    Expression d = Shade.add(b, c);

    VertexShaderCompiler compiler = new VertexShaderCompiler(d);

    String result = compiler.compile();
    float duh = 2;
  }
}
