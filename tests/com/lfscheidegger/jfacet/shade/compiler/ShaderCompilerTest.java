package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.collect.ImmutableMap;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.Mat4;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.Mat4Like;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.Vec4Like;
import org.junit.Test;

/**
 * Tests for {@code ShaderCompiler}
 */
public class ShaderCompilerTest {

  @Test
  public void testVertexShaderCompiler() {
    ShaderCompiler shaderCompiler = new ShaderCompiler(
        ImmutableMap.<String, Expression>of(
            "gl_Position",
            Shade.add(
                Shade.vec(1, 2, 3, 4),
                Shade.mul(
                    Shade.vec(5, 6, 7, 8),
                    Shade.neg(
                        Shade.vec(9, 10, 11, 12))))));

    String code = shaderCompiler.compile();
    System.err.println(code);
  }

  @Test
  public void testVertexShaderCompilerWithPromotion() {
    ShaderCompiler shaderCompiler = new ShaderCompiler(
        ImmutableMap.<String, Expression>of(
            "gl_Position", Shade.vec(1, 2, 3)));

    String code = shaderCompiler.compile();
    System.err.println(code);
  }

  @Test
  public void testVertexShaderCompilerWithAttribute() {
    Vec4Like vertexInput = Shade.attribute4f();
    Mat4Like mvp = Shade.mat(new Mat4());
    ShaderCompiler shaderCompiler = new ShaderCompiler(
        ImmutableMap.<String, Expression>of(
            "gl_Position", Shade.mul(mvp, vertexInput)));

    String code = shaderCompiler.compile();
    System.err.println(code);
  }
}
