package com.lfscheidegger.jfacet.compiler;

import com.lfscheidegger.jfacet.facet.AttribBuffer;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

/**
 * This class contains some end-to-end tests for the vertex shader compiler. It's impossible
 * to test the compiler to exhaustion, so these tests contain some basic tests that should
 * output working shader code.
 */
@RunWith(RobolectricTestRunner.class)
public class VertexShaderCompilerTest {

  @Test
  public void testNoTransform() {
    AttribBuffer vertexCoordinates = new AttribBuffer(
        new float[]{
            0, 0, 0, 1,
            1, 0, 0, 1,
            1, 1, 0, 1}, 4);
    Vector4 vertexPosition = new Vector4(vertexCoordinates);

    VertexShaderCompiler compiler = new VertexShaderCompiler(vertexPosition);
    String source = compiler.compile();

    String expected =
        "attribute vec4 var_0;\n" +
        "\n" +
        "void main() {\n" +
        "gl_Position = var_0;\n" +
        "}\n";

    assertEquals(source, expected);
  }

  @Test
  public void testNoTransformWithFill() {
    AttribBuffer vertexCoordinates = new AttribBuffer(
        new float[] {
            0, 0,
            1, 0,
            1, 1 }, 2);
    Vector2 vertexPosition = new Vector2(vertexCoordinates);

    VertexShaderCompiler compiler = new VertexShaderCompiler(
        vertexPosition.fill(new Vector4(0, 0, 0, 1)));

    String source = compiler.compile();

    String expected =
        "attribute vec2 var_0;\n" +
        "\n" +
        "void main() {\n" +
        "float var_1 = var_0.x;\n" +
        "float var_2 = var_0.y;\n" +
        "vec4 var_3 = vec4(0.0, 0.0, 0.0, 1.0);\n" +
        "float var_4 = var_3.z;\n" +
        "float var_5 = var_3.w;\n" +
        "vec4 var_6 = vec4(var_1, var_2, var_4, var_5);\n" +
        "gl_Position = var_6;\n" +
        "}\n";
    assertEquals(source, expected);
  }

  @Test
  public void testNoTransformWithTernary() {
    AttribBuffer vertexCoordinates1 = new AttribBuffer(new float[] {
        0, 0, 0, 1,
        1, 0, 0, 1,
        1, 1, 0, 1,
        0, 1, 0, 1}, 4),
        vertexCoordinates2 = new AttribBuffer(new float[] {
            0, 0, 0, 1,
            1, 0, 0, 1,
            1, 1, 0, 1,
            0, 1, 0, 1}, 4);

    Vector4 vertexPosition = new Bool(true)
        .if_(new Vector4(vertexCoordinates1))
        .else_(new Vector4(vertexCoordinates2));

    VertexShaderCompiler compiler = new VertexShaderCompiler(vertexPosition);
    String source = compiler.compile();

    String expected =
        "attribute vec4 var_0;\n" +
        "attribute vec4 var_1;\n" +
        "\n" +
        "void main() {\n" +
        "bool var_2 = true;\n" +
        "vec4 var_3 = var_2 ? var_0 : var_1;\n" +
        "gl_Position = var_3;\n" +
        "}\n";
    assertEquals(source, expected);
  }

  @Test
  public void testNoTransformWithAddition() {
    AttribBuffer
        vertexCoordinates1 = new AttribBuffer(new float[] {
        0, 0, 0, 1,
        1, 0, 0, 1,
        1, 1, 0, 1,
        0, 1, 0, 1}, 4),
        vertexCoordinates2 = new AttribBuffer(new float[] {
        0, 0, 0, 1,
        1, 0, 0, 1,
        1, 1, 0, 1,
        0, 1, 0, 1}, 4);

    Vector4 vertexPosition =
        new Vector4(vertexCoordinates1)
            .add(new Vector4(vertexCoordinates2));

    VertexShaderCompiler compiler = new VertexShaderCompiler(vertexPosition);
    String source = compiler.compile();

    String expected =
        "attribute vec4 var_0;\n" +
        "attribute vec4 var_1;\n" +
        "\n" +
        "void main() {\n" +
        "vec4 var_2 = var_0 + var_1;\n" +
        "gl_Position = var_2;\n" +
        "}\n";
    assertEquals(expected, source);
  }
}
