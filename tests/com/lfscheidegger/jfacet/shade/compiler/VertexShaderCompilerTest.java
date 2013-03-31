package com.lfscheidegger.jfacet.shade.compiler;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.primitives.Mat4;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.Mat4Like;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.Vec4Like;
import org.junit.Test;

/**
 * Tests for {@code VertexShaderCompiler}
 */
public class VertexShaderCompilerTest {

  @Test
  public void testVertexShaderCompiler() {
    VertexShaderCompiler vertexShaderCompiler = new VertexShaderCompiler(
        Shade.add(
            Shade.vec(1, 2, 3, 4),
            Shade.mul(
                Shade.vec(5, 6, 7, 8),
                Shade.neg(
                    Shade.vec(9, 10, 11, 12)))));

    String code = vertexShaderCompiler.compile();
    System.err.println(code);
  }

  @Test
  public void testVertexShaderCompilerWithPromotion() {
    VertexShaderCompiler vertexShaderCompiler = new VertexShaderCompiler(Shade.vec(1, 2, 3));

    String code = vertexShaderCompiler.compile();
    System.err.println(code);
  }

  @Test
  public void testVertexShaderCompilerWithAttribute() {
    Vec4Like vertexInput = Shade.attribute4f();
    Mat4Like mvp = Shade.mat(new Mat4());
    VertexShaderCompiler compiler = new VertexShaderCompiler(Shade.mul(mvp, vertexInput));

    String code = compiler.compile();
    System.err.println(code);
  }
}
