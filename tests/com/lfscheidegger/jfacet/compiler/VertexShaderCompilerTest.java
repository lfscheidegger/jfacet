package com.lfscheidegger.jfacet.compiler;

import com.lfscheidegger.jfacet.facet.AttribBuffer;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;
import org.junit.Test;

public class VertexShaderCompilerTest {

  @Test
  public void testVertexShaderCompiler() {
    AttribBuffer vertexCoordinates = new AttribBuffer(new float[]{0, 0, 1, 0, 1, 1}, 2);
    Vector4 vertexPosition = new Vector4(vertexCoordinates);

    VertexShaderCompiler compiler = new VertexShaderCompiler(vertexPosition);
    String source = compiler.compile();

    System.err.println(source);
  }
}
