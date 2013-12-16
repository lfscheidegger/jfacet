package com.lfscheidegger.jfacet.facet;

import com.lfscheidegger.jfacet.shade.GlSlQualifier;
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
@Ignore
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
    assertTrue(vertices.getGlSlQualifier().isPresent());
    assertEquals(vertices.getGlSlQualifier().get(), GlSlQualifier.ATTRIBUTE_T);
  }

  @Test(expected = NullPointerException.class)
  public void testGetVerticesWithWrongDimension1() {
    mGeometry.getVertices1();
  }

  @Test(expected = NullPointerException.class)
  public void testGetVerticesWithWrongDimension3() {
    mGeometry.getVertices3();
  }

  @Test(expected = NullPointerException.class)
  public void testGetVerticesWithWrongDimension4() {
    mGeometry.getVertices4();
  }

  @Test
  public void testGetColors() {
    mGeometry.setColors(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1}, 3);

    Vector3 colors = mGeometry.getColors3();
    assertTrue(colors.getGlSlQualifier().isPresent());
    assertEquals(colors.getGlSlQualifier().get(), GlSlQualifier.ATTRIBUTE_T);
  }

  @Test(expected = NullPointerException.class)
  public void testGetColorsWithNull1() {
    mGeometry.getColors1();
  }

  @Test(expected = NullPointerException.class)
  public void testGetColorsWithNull2() {
    mGeometry.getColors2();
  }

  @Test(expected = NullPointerException.class)
  public void testGetColorsWithNull3() {
    mGeometry.getColors3();
  }

  @Test(expected = NullPointerException.class)
  public void testGetColorsWithNull4() {
    mGeometry.getColors4();
  }

  @Test(expected = NullPointerException.class)
  public void testGetColorsWithWrongDimension1() {
    mGeometry.setColors(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1}, 3);

    mGeometry.getColors1();
  }

  @Test(expected = NullPointerException.class)
  public void testGetColorsWithWrongDimension2() {
    mGeometry.setColors(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1}, 3);

    mGeometry.getColors2();
  }

  @Test(expected = NullPointerException.class)
  public void testGetColorsWithWrongDimension4() {
    mGeometry.setColors(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1}, 3);

    mGeometry.getColors4();
  }

  @Test
  public void testGetTexCoords() {
    mGeometry.setTexCoords(new float[]{0, 0, 1, 0, 1, 1}, 2);

    Vector2 texCoords = mGeometry.getTexCoords2();
    assertTrue(texCoords.getGlSlQualifier().isPresent());
    assertEquals(texCoords.getGlSlQualifier().get(), GlSlQualifier.ATTRIBUTE_T);
  }

  @Test(expected = NullPointerException.class)
  public void testGetTexCoordsWithNull1() {
    mGeometry.getTexCoords1();
  }

  @Test(expected = NullPointerException.class)
  public void testGetTexCoordsWithNull2() {
    mGeometry.getTexCoords2();
  }

  @Test(expected = NullPointerException.class)
  public void testGetTexCoordsWithNull3() {
    mGeometry.getTexCoords3();
  }

  @Test(expected = NullPointerException.class)
  public void testGetTexCoordsWithNull4() {
    mGeometry.getTexCoords4();
  }

  @Test(expected = NullPointerException.class)
  public void testGetTexCoordsWithWrongDimension1() {
    mGeometry.setTexCoords(new float[]{0, 0, 1, 0, 1, 1}, 2);

    mGeometry.getTexCoords1();
  }

  @Test(expected = NullPointerException.class)
  public void testGetTexCoordsWithWrongDimension3() {
    mGeometry.setTexCoords(new float[]{0, 0, 1, 0, 1, 1}, 2);

    mGeometry.getTexCoords3();
  }

  @Test(expected = NullPointerException.class)
  public void testGetTexCoordsWithWrongDimension4() {
    mGeometry.setTexCoords(new float[]{0, 0, 1, 0, 1, 1}, 2);

    mGeometry.getTexCoords4();
  }

  @Test
  public void testGetNormals() {
    mGeometry.setNormals(new float[]{0, 0, -1, 0, 0, -1, 0, 0, -1}, 3);

    Vector3 normals = mGeometry.getNormals3();
    assertTrue(normals.getGlSlQualifier().isPresent());
    assertEquals(normals.getGlSlQualifier().get(), GlSlQualifier.ATTRIBUTE_T);
  }

  @Test(expected = NullPointerException.class)
  public void testGetNormalsWithNull1() {
    mGeometry.getNormals1();
  }

  @Test(expected = NullPointerException.class)
  public void testGetNormalsWithNull2() {
    mGeometry.getNormals2();
  }

  @Test(expected = NullPointerException.class)
  public void testGetNormalsWithNull3() {
    mGeometry.getNormals3();
  }

  @Test(expected = NullPointerException.class)
  public void testGetNormalsWithNull4() {
    mGeometry.getNormals4();
  }

  @Test(expected = NullPointerException.class)
  public void testGetNormalsWithWrongDimension1() {
    mGeometry.setNormals(new float[]{0, 0, -1, 0, 0, -1, 0, 0, -1}, 3);

    mGeometry.getNormals1();
  }

  @Test(expected = NullPointerException.class)
  public void testGetNormalsWithWrongDimension2() {
    mGeometry.setNormals(new float[]{0, 0, -1, 0, 0, -1, 0, 0, -1}, 3);

    mGeometry.getNormals2();
  }

  @Test(expected = NullPointerException.class)
  public void testGetNormalsWithWrongDimension4() {
    mGeometry.setNormals(new float[]{0, 0, -1, 0, 0, -1, 0, 0, -1}, 3);

    mGeometry.getNormals4();
  }

  @Test
  public void testGetAttribute() {
    mGeometry.setAttributeValues(new float[]{0, 1, 2}, 1, "testKey");

    Real attribute = mGeometry.getAttribute1("testKey");
    assertTrue(attribute.getGlSlQualifier().isPresent());
    assertEquals(attribute.getGlSlQualifier().get(), GlSlQualifier.ATTRIBUTE_T);
  }

  @Test(expected = NullPointerException.class)
  public void testGetAttributeWithMissingKey() {

    mGeometry.getAttribute1("missingKey");
  }

  @Test(expected = NullPointerException.class)
  public void testGetAttributeWithWrongDimension2() {
    mGeometry.setAttributeValues(new float[]{0, 1, 2}, 1, "testKey");

    mGeometry.getAttribute2("testKey");
  }

  @Test(expected = NullPointerException.class)
  public void testGetAttributeWithWrongDimension3() {
    mGeometry.setAttributeValues(new float[]{0, 1, 2}, 1, "testKey");

    mGeometry.getAttribute3("testKey");
  }

  @Test(expected = NullPointerException.class)
  public void testGetAttributeWithWrongDimension4() {
    mGeometry.setAttributeValues(new float[]{0, 1, 2}, 1, "testKey");

    mGeometry.getAttribute4("testKey");
  }
}
