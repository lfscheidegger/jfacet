package com.lfscheidegger.jfacet.facet;

import com.lfscheidegger.jfacet.shade.GlSlQualifier;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

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
    Vector2 vertices = mGeometry.getVertices2();
    assertTrue(vertices.getNodeType().get() instanceof Expression.NodeType.AttributeNodeType);
  }

  @Test
  public void testGetColors() {
    mGeometry.setColors(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1}, 3);

    Vector3 colors = mGeometry.getColors3();
    assertTrue(colors.getNodeType().get() instanceof Expression.NodeType.AttributeNodeType);
  }

  @Test
  public void testGetTexCoords() {
    mGeometry.setTexCoords(new float[]{0, 0, 1, 0, 1, 1}, 2);

    Vector2 texCoords = mGeometry.getTexCoords2();
    assertTrue(texCoords.getNodeType().get() instanceof Expression.NodeType.AttributeNodeType);
  }

  @Test
  public void testGetNormals() {
    mGeometry.setNormals(new float[]{0, 0, -1, 0, 0, -1, 0, 0, -1}, 3);

    Vector3 normals = mGeometry.getNormals3();
    assertTrue(normals.getNodeType().get() instanceof Expression.NodeType.AttributeNodeType);
  }

  @Test
  public void testGetAttribute() {
    mGeometry.setAttributeValues(new float[]{0, 1, 2}, 1, "testKey");

    Real attribute = mGeometry.getAttribute1("testKey");
    assertTrue(attribute.getNodeType().get() instanceof Expression.NodeType.AttributeNodeType);
  }
}
