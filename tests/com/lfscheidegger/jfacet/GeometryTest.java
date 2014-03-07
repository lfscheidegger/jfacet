// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet;

import com.lfscheidegger.jfacet.shade.expression.NodeType;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.Vec2;
import com.lfscheidegger.jfacet.shade.expression.Vec3;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@code Geometry}
 */
public class GeometryTest {

  private Geometry mGeometry;

  @Before
  public void setUp() {
    int[] indices = new int[]{0, 1, 2};
    float[] vertices = new float[]{0, 0, 1, 0, 1, 1};

    mGeometry = new Geometry(indices, vertices, 2);
  }

  @Test
  public void testGetVertices() {
    Vec2 vertices = mGeometry.getVertices2();
    assertTrue(vertices.getNodeType() instanceof NodeType.AttributeNodeType);
  }

  @Test
  public void testGetColors() {
    mGeometry.setColors(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1}, 3);

    Vec3 colors = mGeometry.getColors3();
    assertTrue(colors.getNodeType() instanceof NodeType.AttributeNodeType);
  }

  @Test
  public void testGetTexCoords() {
    mGeometry.setTexCoords(new float[]{0, 0, 1, 0, 1, 1}, 2);

    Vec2 texCoords = mGeometry.getTexCoords2();
    assertTrue(texCoords.getNodeType() instanceof NodeType.AttributeNodeType);
  }

  @Test
  public void testGetNormals() {
    mGeometry.setNormals(new float[]{0, 0, -1, 0, 0, -1, 0, 0, -1}, 3);

    Vec3 normals = mGeometry.getNormals3();
    assertTrue(normals.getNodeType() instanceof NodeType.AttributeNodeType);
  }

  @Test
  public void testGetAttribute() {
    mGeometry.setVertexDataBuffer("testKey", new float[]{0, 1, 2}, 1);

    Real attribute = mGeometry.getVertexData1("testKey");
    assertTrue(attribute.getNodeType() instanceof NodeType.AttributeNodeType);
  }
}
