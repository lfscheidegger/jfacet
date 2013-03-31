package com.lfscheidegger.jfacet.shade.compiler;

import com.lfscheidegger.jfacet.shade.Shade;
import org.junit.Test;

/**
 * Tests for {@code VertexShaderCompiler}
 */
public class VertexShaderCompilerTest {

  @Test
  public void testVertexShaderCompiler() {
    VertexShaderCompiler compiler = new VertexShaderCompiler(
        Shade.add(
            Shade.vec(1, 2, 3, 4),
            Shade.mul(
                Shade.vec(5, 6, 7, 8),
                Shade.neg(
                    Shade.vec(9, 10, 11, 12)))));

    String code = compiler.compile();
    System.err.println(code);
  }

  @Test
  public void testVertexShaderCompilerWithPromotion() {
    VertexShaderCompiler compiler = new VertexShaderCompiler(Shade.vec(1, 2, 3));

    String code = compiler.compile();
    System.err.println(code);
  }
}
