package com.lfscheidegger.jfacet.compiler;

import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

/**
 * This class contains some end-to-end tests for the fragment shader compiler. It's impossible
 * to test the compiler to exhaustion, so these tests contain some basic tests that should
 * output working shader code.
 */
@RunWith(RobolectricTestRunner.class)
public class FragmentShaderCompilerTest {

  @Test
  public void testNoVarying() {
    FragmentShaderCompiler compiler = new FragmentShaderCompiler(new Vector4(1, 0, 0, 1));
    String source = compiler.compile();

    String expected =
        "precision highp float;\n" +
        "void main() {\n" +
        "vec4 var_0 = vec4(1.0, 0.0, 0.0, 1.0);\n" +
        "gl_FragColor = var_0;\n" +
        "}\n";

    assertEquals(expected, source);
  }
}
