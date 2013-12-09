package com.lfscheidegger.jfacet.shade.expression.vector;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Unit tests for {@code Vector3.Primitive}
 */
public class Vector3PrimitiveTest {

  private final Vector3.Primitive vec;

  public Vector3PrimitiveTest() {
    vec = new Vector3.Primitive(1, 2, 3);
  }

  @Test
  public void testConstructors() {
    assertTrue(vec.getX() == 1);
    assertTrue(vec.getY() == 2);
    assertTrue(vec.getZ() == 3);
  }

  @Test
  public void testGetters() {
    float x = vec.getX();
    float y = vec.getY();
    float z = vec.getZ();

    assertTrue(x == 1);
    assertTrue(y == 2);
    assertTrue(z == 3);

    assertTrue(vec.get(0) == vec.getX());
    assertTrue(vec.get(1) == vec.getY());
    assertTrue(vec.get(2) == vec.getZ());
  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleXYZW() {
    assertTrue(vec.x().build() == 1);
    assertTrue(vec.y().build() == 2);
    assertTrue(vec.z().build() == 3);

    assertEquals(vec.x().x().build(), new Vector2.Primitive(1, 1));
    assertEquals(vec.x().y().build(), new Vector2.Primitive(1, 2));
    assertEquals(vec.x().z().build(), new Vector2.Primitive(1, 3));
    assertEquals(vec.y().x().build(), new Vector2.Primitive(2, 1));
    assertEquals(vec.y().y().build(), new Vector2.Primitive(2, 2));
    assertEquals(vec.y().z().build(), new Vector2.Primitive(2, 3));
    assertEquals(vec.z().x().build(), new Vector2.Primitive(3, 1));
    assertEquals(vec.z().y().build(), new Vector2.Primitive(3, 2));
    assertEquals(vec.z().z().build(), new Vector2.Primitive(3, 3));

    assertEquals(vec.x().x().x().build(), new Vector3.Primitive(1, 1, 1));
    assertEquals(vec.x().x().y().build(), new Vector3.Primitive(1, 1, 2));
    assertEquals(vec.x().x().z().build(), new Vector3.Primitive(1, 1, 3));
    assertEquals(vec.x().y().x().build(), new Vector3.Primitive(1, 2, 1));
    assertEquals(vec.x().y().y().build(), new Vector3.Primitive(1, 2, 2));
    assertEquals(vec.x().y().z().build(), new Vector3.Primitive(1, 2, 3));
    assertEquals(vec.x().z().x().build(), new Vector3.Primitive(1, 3, 1));
    assertEquals(vec.x().z().y().build(), new Vector3.Primitive(1, 3, 2));
    assertEquals(vec.x().z().z().build(), new Vector3.Primitive(1, 3, 3));
    assertEquals(vec.y().x().x().build(), new Vector3.Primitive(2, 1, 1));
    assertEquals(vec.y().x().y().build(), new Vector3.Primitive(2, 1, 2));
    assertEquals(vec.y().x().z().build(), new Vector3.Primitive(2, 1, 3));
    assertEquals(vec.y().y().x().build(), new Vector3.Primitive(2, 2, 1));
    assertEquals(vec.y().y().y().build(), new Vector3.Primitive(2, 2, 2));
    assertEquals(vec.y().y().z().build(), new Vector3.Primitive(2, 2, 3));
    assertEquals(vec.y().z().x().build(), new Vector3.Primitive(2, 3, 1));
    assertEquals(vec.y().z().y().build(), new Vector3.Primitive(2, 3, 2));
    assertEquals(vec.y().z().z().build(), new Vector3.Primitive(2, 3, 3));
    assertEquals(vec.z().x().x().build(), new Vector3.Primitive(3, 1, 1));
    assertEquals(vec.z().x().y().build(), new Vector3.Primitive(3, 1, 2));
    assertEquals(vec.z().x().z().build(), new Vector3.Primitive(3, 1, 3));
    assertEquals(vec.z().y().x().build(), new Vector3.Primitive(3, 2, 1));
    assertEquals(vec.z().y().y().build(), new Vector3.Primitive(3, 2, 2));
    assertEquals(vec.z().y().z().build(), new Vector3.Primitive(3, 2, 3));
    assertEquals(vec.z().z().x().build(), new Vector3.Primitive(3, 3, 1));
    assertEquals(vec.z().z().y().build(), new Vector3.Primitive(3, 3, 2));
    assertEquals(vec.z().z().z().build(), new Vector3.Primitive(3, 3, 3));

    assertEquals(vec.x().x().x().x().build(), new Vector4.Primitive(1, 1, 1, 1));
    assertEquals(vec.x().x().x().y().build(), new Vector4.Primitive(1, 1, 1, 2));
    assertEquals(vec.x().x().x().z().build(), new Vector4.Primitive(1, 1, 1, 3));
    assertEquals(vec.x().x().y().x().build(), new Vector4.Primitive(1, 1, 2, 1));
    assertEquals(vec.x().x().y().y().build(), new Vector4.Primitive(1, 1, 2, 2));
    assertEquals(vec.x().x().y().z().build(), new Vector4.Primitive(1, 1, 2, 3));
    assertEquals(vec.x().x().z().x().build(), new Vector4.Primitive(1, 1, 3, 1));
    assertEquals(vec.x().x().z().y().build(), new Vector4.Primitive(1, 1, 3, 2));
    assertEquals(vec.x().x().z().z().build(), new Vector4.Primitive(1, 1, 3, 3));
    assertEquals(vec.x().y().x().x().build(), new Vector4.Primitive(1, 2, 1, 1));
    assertEquals(vec.x().y().x().y().build(), new Vector4.Primitive(1, 2, 1, 2));
    assertEquals(vec.x().y().x().z().build(), new Vector4.Primitive(1, 2, 1, 3));
    assertEquals(vec.x().y().y().x().build(), new Vector4.Primitive(1, 2, 2, 1));
    assertEquals(vec.x().y().y().y().build(), new Vector4.Primitive(1, 2, 2, 2));
    assertEquals(vec.x().y().y().z().build(), new Vector4.Primitive(1, 2, 2, 3));
    assertEquals(vec.x().y().z().x().build(), new Vector4.Primitive(1, 2, 3, 1));
    assertEquals(vec.x().y().z().y().build(), new Vector4.Primitive(1, 2, 3, 2));
    assertEquals(vec.x().y().z().z().build(), new Vector4.Primitive(1, 2, 3, 3));
    assertEquals(vec.x().z().x().x().build(), new Vector4.Primitive(1, 3, 1, 1));
    assertEquals(vec.x().z().x().y().build(), new Vector4.Primitive(1, 3, 1, 2));
    assertEquals(vec.x().z().x().z().build(), new Vector4.Primitive(1, 3, 1, 3));
    assertEquals(vec.x().z().y().x().build(), new Vector4.Primitive(1, 3, 2, 1));
    assertEquals(vec.x().z().y().y().build(), new Vector4.Primitive(1, 3, 2, 2));
    assertEquals(vec.x().z().y().z().build(), new Vector4.Primitive(1, 3, 2, 3));
    assertEquals(vec.x().z().z().x().build(), new Vector4.Primitive(1, 3, 3, 1));
    assertEquals(vec.x().z().z().y().build(), new Vector4.Primitive(1, 3, 3, 2));
    assertEquals(vec.x().z().z().z().build(), new Vector4.Primitive(1, 3, 3, 3));
    assertEquals(vec.y().x().x().x().build(), new Vector4.Primitive(2, 1, 1, 1));
    assertEquals(vec.y().x().x().y().build(), new Vector4.Primitive(2, 1, 1, 2));
    assertEquals(vec.y().x().x().z().build(), new Vector4.Primitive(2, 1, 1, 3));
    assertEquals(vec.y().x().y().x().build(), new Vector4.Primitive(2, 1, 2, 1));
    assertEquals(vec.y().x().y().y().build(), new Vector4.Primitive(2, 1, 2, 2));
    assertEquals(vec.y().x().y().z().build(), new Vector4.Primitive(2, 1, 2, 3));
    assertEquals(vec.y().x().z().x().build(), new Vector4.Primitive(2, 1, 3, 1));
    assertEquals(vec.y().x().z().y().build(), new Vector4.Primitive(2, 1, 3, 2));
    assertEquals(vec.y().x().z().z().build(), new Vector4.Primitive(2, 1, 3, 3));
    assertEquals(vec.y().y().x().x().build(), new Vector4.Primitive(2, 2, 1, 1));
    assertEquals(vec.y().y().x().y().build(), new Vector4.Primitive(2, 2, 1, 2));
    assertEquals(vec.y().y().x().z().build(), new Vector4.Primitive(2, 2, 1, 3));
    assertEquals(vec.y().y().y().x().build(), new Vector4.Primitive(2, 2, 2, 1));
    assertEquals(vec.y().y().y().y().build(), new Vector4.Primitive(2, 2, 2, 2));
    assertEquals(vec.y().y().y().z().build(), new Vector4.Primitive(2, 2, 2, 3));
    assertEquals(vec.y().y().z().x().build(), new Vector4.Primitive(2, 2, 3, 1));
    assertEquals(vec.y().y().z().y().build(), new Vector4.Primitive(2, 2, 3, 2));
    assertEquals(vec.y().y().z().z().build(), new Vector4.Primitive(2, 2, 3, 3));
    assertEquals(vec.y().z().x().x().build(), new Vector4.Primitive(2, 3, 1, 1));
    assertEquals(vec.y().z().x().y().build(), new Vector4.Primitive(2, 3, 1, 2));
    assertEquals(vec.y().z().x().z().build(), new Vector4.Primitive(2, 3, 1, 3));
    assertEquals(vec.y().z().y().x().build(), new Vector4.Primitive(2, 3, 2, 1));
    assertEquals(vec.y().z().y().y().build(), new Vector4.Primitive(2, 3, 2, 2));
    assertEquals(vec.y().z().y().z().build(), new Vector4.Primitive(2, 3, 2, 3));
    assertEquals(vec.y().z().z().x().build(), new Vector4.Primitive(2, 3, 3, 1));
    assertEquals(vec.y().z().z().y().build(), new Vector4.Primitive(2, 3, 3, 2));
    assertEquals(vec.y().z().z().z().build(), new Vector4.Primitive(2, 3, 3, 3));
    assertEquals(vec.z().x().x().x().build(), new Vector4.Primitive(3, 1, 1, 1));
    assertEquals(vec.z().x().x().y().build(), new Vector4.Primitive(3, 1, 1, 2));
    assertEquals(vec.z().x().x().z().build(), new Vector4.Primitive(3, 1, 1, 3));
    assertEquals(vec.z().x().y().x().build(), new Vector4.Primitive(3, 1, 2, 1));
    assertEquals(vec.z().x().y().y().build(), new Vector4.Primitive(3, 1, 2, 2));
    assertEquals(vec.z().x().y().z().build(), new Vector4.Primitive(3, 1, 2, 3));
    assertEquals(vec.z().x().z().x().build(), new Vector4.Primitive(3, 1, 3, 1));
    assertEquals(vec.z().x().z().y().build(), new Vector4.Primitive(3, 1, 3, 2));
    assertEquals(vec.z().x().z().z().build(), new Vector4.Primitive(3, 1, 3, 3));
    assertEquals(vec.z().y().x().x().build(), new Vector4.Primitive(3, 2, 1, 1));
    assertEquals(vec.z().y().x().y().build(), new Vector4.Primitive(3, 2, 1, 2));
    assertEquals(vec.z().y().x().z().build(), new Vector4.Primitive(3, 2, 1, 3));
    assertEquals(vec.z().y().y().x().build(), new Vector4.Primitive(3, 2, 2, 1));
    assertEquals(vec.z().y().y().y().build(), new Vector4.Primitive(3, 2, 2, 2));
    assertEquals(vec.z().y().y().z().build(), new Vector4.Primitive(3, 2, 2, 3));
    assertEquals(vec.z().y().z().x().build(), new Vector4.Primitive(3, 2, 3, 1));
    assertEquals(vec.z().y().z().y().build(), new Vector4.Primitive(3, 2, 3, 2));
    assertEquals(vec.z().y().z().z().build(), new Vector4.Primitive(3, 2, 3, 3));
    assertEquals(vec.z().z().x().x().build(), new Vector4.Primitive(3, 3, 1, 1));
    assertEquals(vec.z().z().x().y().build(), new Vector4.Primitive(3, 3, 1, 2));
    assertEquals(vec.z().z().x().z().build(), new Vector4.Primitive(3, 3, 1, 3));
    assertEquals(vec.z().z().y().x().build(), new Vector4.Primitive(3, 3, 2, 1));
    assertEquals(vec.z().z().y().y().build(), new Vector4.Primitive(3, 3, 2, 2));
    assertEquals(vec.z().z().y().z().build(), new Vector4.Primitive(3, 3, 2, 3));
    assertEquals(vec.z().z().z().x().build(), new Vector4.Primitive(3, 3, 3, 1));
    assertEquals(vec.z().z().z().y().build(), new Vector4.Primitive(3, 3, 3, 2));
    assertEquals(vec.z().z().z().z().build(), new Vector4.Primitive(3, 3, 3, 3));
  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleRGBA() {
    assertTrue(vec.r().build() == 1);
    assertTrue(vec.g().build() == 2);
    assertTrue(vec.b().build() == 3);

    assertEquals(vec.r().r().build(), new Vector2.Primitive(1, 1));
    assertEquals(vec.r().g().build(), new Vector2.Primitive(1, 2));
    assertEquals(vec.r().b().build(), new Vector2.Primitive(1, 3));
    assertEquals(vec.g().r().build(), new Vector2.Primitive(2, 1));
    assertEquals(vec.g().g().build(), new Vector2.Primitive(2, 2));
    assertEquals(vec.g().b().build(), new Vector2.Primitive(2, 3));
    assertEquals(vec.b().r().build(), new Vector2.Primitive(3, 1));
    assertEquals(vec.b().g().build(), new Vector2.Primitive(3, 2));
    assertEquals(vec.b().b().build(), new Vector2.Primitive(3, 3));

    assertEquals(vec.r().r().r().build(), new Vector3.Primitive(1, 1, 1));
    assertEquals(vec.r().r().g().build(), new Vector3.Primitive(1, 1, 2));
    assertEquals(vec.r().r().b().build(), new Vector3.Primitive(1, 1, 3));
    assertEquals(vec.r().g().r().build(), new Vector3.Primitive(1, 2, 1));
    assertEquals(vec.r().g().g().build(), new Vector3.Primitive(1, 2, 2));
    assertEquals(vec.r().g().b().build(), new Vector3.Primitive(1, 2, 3));
    assertEquals(vec.r().b().r().build(), new Vector3.Primitive(1, 3, 1));
    assertEquals(vec.r().b().g().build(), new Vector3.Primitive(1, 3, 2));
    assertEquals(vec.r().b().b().build(), new Vector3.Primitive(1, 3, 3));
    assertEquals(vec.g().r().r().build(), new Vector3.Primitive(2, 1, 1));
    assertEquals(vec.g().r().g().build(), new Vector3.Primitive(2, 1, 2));
    assertEquals(vec.g().r().b().build(), new Vector3.Primitive(2, 1, 3));
    assertEquals(vec.g().g().r().build(), new Vector3.Primitive(2, 2, 1));
    assertEquals(vec.g().g().g().build(), new Vector3.Primitive(2, 2, 2));
    assertEquals(vec.g().g().b().build(), new Vector3.Primitive(2, 2, 3));
    assertEquals(vec.g().b().r().build(), new Vector3.Primitive(2, 3, 1));
    assertEquals(vec.g().b().g().build(), new Vector3.Primitive(2, 3, 2));
    assertEquals(vec.g().b().b().build(), new Vector3.Primitive(2, 3, 3));
    assertEquals(vec.b().r().r().build(), new Vector3.Primitive(3, 1, 1));
    assertEquals(vec.b().r().g().build(), new Vector3.Primitive(3, 1, 2));
    assertEquals(vec.b().r().b().build(), new Vector3.Primitive(3, 1, 3));
    assertEquals(vec.b().g().r().build(), new Vector3.Primitive(3, 2, 1));
    assertEquals(vec.b().g().g().build(), new Vector3.Primitive(3, 2, 2));
    assertEquals(vec.b().g().b().build(), new Vector3.Primitive(3, 2, 3));
    assertEquals(vec.b().b().r().build(), new Vector3.Primitive(3, 3, 1));
    assertEquals(vec.b().b().g().build(), new Vector3.Primitive(3, 3, 2));
    assertEquals(vec.b().b().b().build(), new Vector3.Primitive(3, 3, 3));

    assertEquals(vec.r().r().r().r().build(), new Vector4.Primitive(1, 1, 1, 1));
    assertEquals(vec.r().r().r().g().build(), new Vector4.Primitive(1, 1, 1, 2));
    assertEquals(vec.r().r().r().b().build(), new Vector4.Primitive(1, 1, 1, 3));
    assertEquals(vec.r().r().g().r().build(), new Vector4.Primitive(1, 1, 2, 1));
    assertEquals(vec.r().r().g().g().build(), new Vector4.Primitive(1, 1, 2, 2));
    assertEquals(vec.r().r().g().b().build(), new Vector4.Primitive(1, 1, 2, 3));
    assertEquals(vec.r().r().b().r().build(), new Vector4.Primitive(1, 1, 3, 1));
    assertEquals(vec.r().r().b().g().build(), new Vector4.Primitive(1, 1, 3, 2));
    assertEquals(vec.r().r().b().b().build(), new Vector4.Primitive(1, 1, 3, 3));
    assertEquals(vec.r().g().r().r().build(), new Vector4.Primitive(1, 2, 1, 1));
    assertEquals(vec.r().g().r().g().build(), new Vector4.Primitive(1, 2, 1, 2));
    assertEquals(vec.r().g().r().b().build(), new Vector4.Primitive(1, 2, 1, 3));
    assertEquals(vec.r().g().g().r().build(), new Vector4.Primitive(1, 2, 2, 1));
    assertEquals(vec.r().g().g().g().build(), new Vector4.Primitive(1, 2, 2, 2));
    assertEquals(vec.r().g().g().b().build(), new Vector4.Primitive(1, 2, 2, 3));
    assertEquals(vec.r().g().b().r().build(), new Vector4.Primitive(1, 2, 3, 1));
    assertEquals(vec.r().g().b().g().build(), new Vector4.Primitive(1, 2, 3, 2));
    assertEquals(vec.r().g().b().b().build(), new Vector4.Primitive(1, 2, 3, 3));
    assertEquals(vec.r().b().r().r().build(), new Vector4.Primitive(1, 3, 1, 1));
    assertEquals(vec.r().b().r().g().build(), new Vector4.Primitive(1, 3, 1, 2));
    assertEquals(vec.r().b().r().b().build(), new Vector4.Primitive(1, 3, 1, 3));
    assertEquals(vec.r().b().g().r().build(), new Vector4.Primitive(1, 3, 2, 1));
    assertEquals(vec.r().b().g().g().build(), new Vector4.Primitive(1, 3, 2, 2));
    assertEquals(vec.r().b().g().b().build(), new Vector4.Primitive(1, 3, 2, 3));
    assertEquals(vec.r().b().b().r().build(), new Vector4.Primitive(1, 3, 3, 1));
    assertEquals(vec.r().b().b().g().build(), new Vector4.Primitive(1, 3, 3, 2));
    assertEquals(vec.r().b().b().b().build(), new Vector4.Primitive(1, 3, 3, 3));
    assertEquals(vec.g().r().r().r().build(), new Vector4.Primitive(2, 1, 1, 1));
    assertEquals(vec.g().r().r().g().build(), new Vector4.Primitive(2, 1, 1, 2));
    assertEquals(vec.g().r().r().b().build(), new Vector4.Primitive(2, 1, 1, 3));
    assertEquals(vec.g().r().g().r().build(), new Vector4.Primitive(2, 1, 2, 1));
    assertEquals(vec.g().r().g().g().build(), new Vector4.Primitive(2, 1, 2, 2));
    assertEquals(vec.g().r().g().b().build(), new Vector4.Primitive(2, 1, 2, 3));
    assertEquals(vec.g().r().b().r().build(), new Vector4.Primitive(2, 1, 3, 1));
    assertEquals(vec.g().r().b().g().build(), new Vector4.Primitive(2, 1, 3, 2));
    assertEquals(vec.g().r().b().b().build(), new Vector4.Primitive(2, 1, 3, 3));
    assertEquals(vec.g().g().r().r().build(), new Vector4.Primitive(2, 2, 1, 1));
    assertEquals(vec.g().g().r().g().build(), new Vector4.Primitive(2, 2, 1, 2));
    assertEquals(vec.g().g().r().b().build(), new Vector4.Primitive(2, 2, 1, 3));
    assertEquals(vec.g().g().g().r().build(), new Vector4.Primitive(2, 2, 2, 1));
    assertEquals(vec.g().g().g().g().build(), new Vector4.Primitive(2, 2, 2, 2));
    assertEquals(vec.g().g().g().b().build(), new Vector4.Primitive(2, 2, 2, 3));
    assertEquals(vec.g().g().b().r().build(), new Vector4.Primitive(2, 2, 3, 1));
    assertEquals(vec.g().g().b().g().build(), new Vector4.Primitive(2, 2, 3, 2));
    assertEquals(vec.g().g().b().b().build(), new Vector4.Primitive(2, 2, 3, 3));
    assertEquals(vec.g().b().r().r().build(), new Vector4.Primitive(2, 3, 1, 1));
    assertEquals(vec.g().b().r().g().build(), new Vector4.Primitive(2, 3, 1, 2));
    assertEquals(vec.g().b().r().b().build(), new Vector4.Primitive(2, 3, 1, 3));
    assertEquals(vec.g().b().g().r().build(), new Vector4.Primitive(2, 3, 2, 1));
    assertEquals(vec.g().b().g().g().build(), new Vector4.Primitive(2, 3, 2, 2));
    assertEquals(vec.g().b().g().b().build(), new Vector4.Primitive(2, 3, 2, 3));
    assertEquals(vec.g().b().b().r().build(), new Vector4.Primitive(2, 3, 3, 1));
    assertEquals(vec.g().b().b().g().build(), new Vector4.Primitive(2, 3, 3, 2));
    assertEquals(vec.g().b().b().b().build(), new Vector4.Primitive(2, 3, 3, 3));
    assertEquals(vec.b().r().r().r().build(), new Vector4.Primitive(3, 1, 1, 1));
    assertEquals(vec.b().r().r().g().build(), new Vector4.Primitive(3, 1, 1, 2));
    assertEquals(vec.b().r().r().b().build(), new Vector4.Primitive(3, 1, 1, 3));
    assertEquals(vec.b().r().g().r().build(), new Vector4.Primitive(3, 1, 2, 1));
    assertEquals(vec.b().r().g().g().build(), new Vector4.Primitive(3, 1, 2, 2));
    assertEquals(vec.b().r().g().b().build(), new Vector4.Primitive(3, 1, 2, 3));
    assertEquals(vec.b().r().b().r().build(), new Vector4.Primitive(3, 1, 3, 1));
    assertEquals(vec.b().r().b().g().build(), new Vector4.Primitive(3, 1, 3, 2));
    assertEquals(vec.b().r().b().b().build(), new Vector4.Primitive(3, 1, 3, 3));
    assertEquals(vec.b().g().r().r().build(), new Vector4.Primitive(3, 2, 1, 1));
    assertEquals(vec.b().g().r().g().build(), new Vector4.Primitive(3, 2, 1, 2));
    assertEquals(vec.b().g().r().b().build(), new Vector4.Primitive(3, 2, 1, 3));
    assertEquals(vec.b().g().g().r().build(), new Vector4.Primitive(3, 2, 2, 1));
    assertEquals(vec.b().g().g().g().build(), new Vector4.Primitive(3, 2, 2, 2));
    assertEquals(vec.b().g().g().b().build(), new Vector4.Primitive(3, 2, 2, 3));
    assertEquals(vec.b().g().b().r().build(), new Vector4.Primitive(3, 2, 3, 1));
    assertEquals(vec.b().g().b().g().build(), new Vector4.Primitive(3, 2, 3, 2));
    assertEquals(vec.b().g().b().b().build(), new Vector4.Primitive(3, 2, 3, 3));
    assertEquals(vec.b().b().r().r().build(), new Vector4.Primitive(3, 3, 1, 1));
    assertEquals(vec.b().b().r().g().build(), new Vector4.Primitive(3, 3, 1, 2));
    assertEquals(vec.b().b().r().b().build(), new Vector4.Primitive(3, 3, 1, 3));
    assertEquals(vec.b().b().g().r().build(), new Vector4.Primitive(3, 3, 2, 1));
    assertEquals(vec.b().b().g().g().build(), new Vector4.Primitive(3, 3, 2, 2));
    assertEquals(vec.b().b().g().b().build(), new Vector4.Primitive(3, 3, 2, 3));
    assertEquals(vec.b().b().b().r().build(), new Vector4.Primitive(3, 3, 3, 1));
    assertEquals(vec.b().b().b().g().build(), new Vector4.Primitive(3, 3, 3, 2));
    assertEquals(vec.b().b().b().b().build(), new Vector4.Primitive(3, 3, 3, 3));

  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleSTPQ() {
    assertTrue(vec.s().build() == 1);
    assertTrue(vec.t().build() == 2);
    assertTrue(vec.p().build() == 3);

    assertEquals(vec.s().s().build(), new Vector2.Primitive(1, 1));
    assertEquals(vec.s().t().build(), new Vector2.Primitive(1, 2));
    assertEquals(vec.s().p().build(), new Vector2.Primitive(1, 3));
    assertEquals(vec.t().s().build(), new Vector2.Primitive(2, 1));
    assertEquals(vec.t().t().build(), new Vector2.Primitive(2, 2));
    assertEquals(vec.t().p().build(), new Vector2.Primitive(2, 3));
    assertEquals(vec.p().s().build(), new Vector2.Primitive(3, 1));
    assertEquals(vec.p().t().build(), new Vector2.Primitive(3, 2));
    assertEquals(vec.p().p().build(), new Vector2.Primitive(3, 3));

    assertEquals(vec.s().s().s().build(), new Vector3.Primitive(1, 1, 1));
    assertEquals(vec.s().s().t().build(), new Vector3.Primitive(1, 1, 2));
    assertEquals(vec.s().s().p().build(), new Vector3.Primitive(1, 1, 3));
    assertEquals(vec.s().t().s().build(), new Vector3.Primitive(1, 2, 1));
    assertEquals(vec.s().t().t().build(), new Vector3.Primitive(1, 2, 2));
    assertEquals(vec.s().t().p().build(), new Vector3.Primitive(1, 2, 3));
    assertEquals(vec.s().p().s().build(), new Vector3.Primitive(1, 3, 1));
    assertEquals(vec.s().p().t().build(), new Vector3.Primitive(1, 3, 2));
    assertEquals(vec.s().p().p().build(), new Vector3.Primitive(1, 3, 3));
    assertEquals(vec.t().s().s().build(), new Vector3.Primitive(2, 1, 1));
    assertEquals(vec.t().s().t().build(), new Vector3.Primitive(2, 1, 2));
    assertEquals(vec.t().s().p().build(), new Vector3.Primitive(2, 1, 3));
    assertEquals(vec.t().t().s().build(), new Vector3.Primitive(2, 2, 1));
    assertEquals(vec.t().t().t().build(), new Vector3.Primitive(2, 2, 2));
    assertEquals(vec.t().t().p().build(), new Vector3.Primitive(2, 2, 3));
    assertEquals(vec.t().p().s().build(), new Vector3.Primitive(2, 3, 1));
    assertEquals(vec.t().p().t().build(), new Vector3.Primitive(2, 3, 2));
    assertEquals(vec.t().p().p().build(), new Vector3.Primitive(2, 3, 3));
    assertEquals(vec.p().s().s().build(), new Vector3.Primitive(3, 1, 1));
    assertEquals(vec.p().s().t().build(), new Vector3.Primitive(3, 1, 2));
    assertEquals(vec.p().s().p().build(), new Vector3.Primitive(3, 1, 3));
    assertEquals(vec.p().t().s().build(), new Vector3.Primitive(3, 2, 1));
    assertEquals(vec.p().t().t().build(), new Vector3.Primitive(3, 2, 2));
    assertEquals(vec.p().t().p().build(), new Vector3.Primitive(3, 2, 3));
    assertEquals(vec.p().p().s().build(), new Vector3.Primitive(3, 3, 1));
    assertEquals(vec.p().p().t().build(), new Vector3.Primitive(3, 3, 2));
    assertEquals(vec.p().p().p().build(), new Vector3.Primitive(3, 3, 3));

    assertEquals(vec.s().s().s().s().build(), new Vector4.Primitive(1, 1, 1, 1));
    assertEquals(vec.s().s().s().t().build(), new Vector4.Primitive(1, 1, 1, 2));
    assertEquals(vec.s().s().s().p().build(), new Vector4.Primitive(1, 1, 1, 3));
    assertEquals(vec.s().s().t().s().build(), new Vector4.Primitive(1, 1, 2, 1));
    assertEquals(vec.s().s().t().t().build(), new Vector4.Primitive(1, 1, 2, 2));
    assertEquals(vec.s().s().t().p().build(), new Vector4.Primitive(1, 1, 2, 3));
    assertEquals(vec.s().s().p().s().build(), new Vector4.Primitive(1, 1, 3, 1));
    assertEquals(vec.s().s().p().t().build(), new Vector4.Primitive(1, 1, 3, 2));
    assertEquals(vec.s().s().p().p().build(), new Vector4.Primitive(1, 1, 3, 3));
    assertEquals(vec.s().t().s().s().build(), new Vector4.Primitive(1, 2, 1, 1));
    assertEquals(vec.s().t().s().t().build(), new Vector4.Primitive(1, 2, 1, 2));
    assertEquals(vec.s().t().s().p().build(), new Vector4.Primitive(1, 2, 1, 3));
    assertEquals(vec.s().t().t().s().build(), new Vector4.Primitive(1, 2, 2, 1));
    assertEquals(vec.s().t().t().t().build(), new Vector4.Primitive(1, 2, 2, 2));
    assertEquals(vec.s().t().t().p().build(), new Vector4.Primitive(1, 2, 2, 3));
    assertEquals(vec.s().t().p().s().build(), new Vector4.Primitive(1, 2, 3, 1));
    assertEquals(vec.s().t().p().t().build(), new Vector4.Primitive(1, 2, 3, 2));
    assertEquals(vec.s().t().p().p().build(), new Vector4.Primitive(1, 2, 3, 3));
    assertEquals(vec.s().p().s().s().build(), new Vector4.Primitive(1, 3, 1, 1));
    assertEquals(vec.s().p().s().t().build(), new Vector4.Primitive(1, 3, 1, 2));
    assertEquals(vec.s().p().s().p().build(), new Vector4.Primitive(1, 3, 1, 3));
    assertEquals(vec.s().p().t().s().build(), new Vector4.Primitive(1, 3, 2, 1));
    assertEquals(vec.s().p().t().t().build(), new Vector4.Primitive(1, 3, 2, 2));
    assertEquals(vec.s().p().t().p().build(), new Vector4.Primitive(1, 3, 2, 3));
    assertEquals(vec.s().p().p().s().build(), new Vector4.Primitive(1, 3, 3, 1));
    assertEquals(vec.s().p().p().t().build(), new Vector4.Primitive(1, 3, 3, 2));
    assertEquals(vec.s().p().p().p().build(), new Vector4.Primitive(1, 3, 3, 3));
    assertEquals(vec.t().s().s().s().build(), new Vector4.Primitive(2, 1, 1, 1));
    assertEquals(vec.t().s().s().t().build(), new Vector4.Primitive(2, 1, 1, 2));
    assertEquals(vec.t().s().s().p().build(), new Vector4.Primitive(2, 1, 1, 3));
    assertEquals(vec.t().s().t().s().build(), new Vector4.Primitive(2, 1, 2, 1));
    assertEquals(vec.t().s().t().t().build(), new Vector4.Primitive(2, 1, 2, 2));
    assertEquals(vec.t().s().t().p().build(), new Vector4.Primitive(2, 1, 2, 3));
    assertEquals(vec.t().s().p().s().build(), new Vector4.Primitive(2, 1, 3, 1));
    assertEquals(vec.t().s().p().t().build(), new Vector4.Primitive(2, 1, 3, 2));
    assertEquals(vec.t().s().p().p().build(), new Vector4.Primitive(2, 1, 3, 3));
    assertEquals(vec.t().t().s().s().build(), new Vector4.Primitive(2, 2, 1, 1));
    assertEquals(vec.t().t().s().t().build(), new Vector4.Primitive(2, 2, 1, 2));
    assertEquals(vec.t().t().s().p().build(), new Vector4.Primitive(2, 2, 1, 3));
    assertEquals(vec.t().t().t().s().build(), new Vector4.Primitive(2, 2, 2, 1));
    assertEquals(vec.t().t().t().t().build(), new Vector4.Primitive(2, 2, 2, 2));
    assertEquals(vec.t().t().t().p().build(), new Vector4.Primitive(2, 2, 2, 3));
    assertEquals(vec.t().t().p().s().build(), new Vector4.Primitive(2, 2, 3, 1));
    assertEquals(vec.t().t().p().t().build(), new Vector4.Primitive(2, 2, 3, 2));
    assertEquals(vec.t().t().p().p().build(), new Vector4.Primitive(2, 2, 3, 3));
    assertEquals(vec.t().p().s().s().build(), new Vector4.Primitive(2, 3, 1, 1));
    assertEquals(vec.t().p().s().t().build(), new Vector4.Primitive(2, 3, 1, 2));
    assertEquals(vec.t().p().s().p().build(), new Vector4.Primitive(2, 3, 1, 3));
    assertEquals(vec.t().p().t().s().build(), new Vector4.Primitive(2, 3, 2, 1));
    assertEquals(vec.t().p().t().t().build(), new Vector4.Primitive(2, 3, 2, 2));
    assertEquals(vec.t().p().t().p().build(), new Vector4.Primitive(2, 3, 2, 3));
    assertEquals(vec.t().p().p().s().build(), new Vector4.Primitive(2, 3, 3, 1));
    assertEquals(vec.t().p().p().t().build(), new Vector4.Primitive(2, 3, 3, 2));
    assertEquals(vec.t().p().p().p().build(), new Vector4.Primitive(2, 3, 3, 3));
    assertEquals(vec.p().s().s().s().build(), new Vector4.Primitive(3, 1, 1, 1));
    assertEquals(vec.p().s().s().t().build(), new Vector4.Primitive(3, 1, 1, 2));
    assertEquals(vec.p().s().s().p().build(), new Vector4.Primitive(3, 1, 1, 3));
    assertEquals(vec.p().s().t().s().build(), new Vector4.Primitive(3, 1, 2, 1));
    assertEquals(vec.p().s().t().t().build(), new Vector4.Primitive(3, 1, 2, 2));
    assertEquals(vec.p().s().t().p().build(), new Vector4.Primitive(3, 1, 2, 3));
    assertEquals(vec.p().s().p().s().build(), new Vector4.Primitive(3, 1, 3, 1));
    assertEquals(vec.p().s().p().t().build(), new Vector4.Primitive(3, 1, 3, 2));
    assertEquals(vec.p().s().p().p().build(), new Vector4.Primitive(3, 1, 3, 3));
    assertEquals(vec.p().t().s().s().build(), new Vector4.Primitive(3, 2, 1, 1));
    assertEquals(vec.p().t().s().t().build(), new Vector4.Primitive(3, 2, 1, 2));
    assertEquals(vec.p().t().s().p().build(), new Vector4.Primitive(3, 2, 1, 3));
    assertEquals(vec.p().t().t().s().build(), new Vector4.Primitive(3, 2, 2, 1));
    assertEquals(vec.p().t().t().t().build(), new Vector4.Primitive(3, 2, 2, 2));
    assertEquals(vec.p().t().t().p().build(), new Vector4.Primitive(3, 2, 2, 3));
    assertEquals(vec.p().t().p().s().build(), new Vector4.Primitive(3, 2, 3, 1));
    assertEquals(vec.p().t().p().t().build(), new Vector4.Primitive(3, 2, 3, 2));
    assertEquals(vec.p().t().p().p().build(), new Vector4.Primitive(3, 2, 3, 3));
    assertEquals(vec.p().p().s().s().build(), new Vector4.Primitive(3, 3, 1, 1));
    assertEquals(vec.p().p().s().t().build(), new Vector4.Primitive(3, 3, 1, 2));
    assertEquals(vec.p().p().s().p().build(), new Vector4.Primitive(3, 3, 1, 3));
    assertEquals(vec.p().p().t().s().build(), new Vector4.Primitive(3, 3, 2, 1));
    assertEquals(vec.p().p().t().t().build(), new Vector4.Primitive(3, 3, 2, 2));
    assertEquals(vec.p().p().t().p().build(), new Vector4.Primitive(3, 3, 2, 3));
    assertEquals(vec.p().p().p().s().build(), new Vector4.Primitive(3, 3, 3, 1));
    assertEquals(vec.p().p().p().t().build(), new Vector4.Primitive(3, 3, 3, 2));
    assertEquals(vec.p().p().p().p().build(), new Vector4.Primitive(3, 3, 3, 3));
  }

  @Test
  public void testAdd() {
    Vector3.Primitive added = vec.add(1);
    assertTrue(added.getX() == 2);
    assertTrue(added.getY() == 3);
    assertTrue(added.getZ() == 4);

    added = vec.add(vec);
    assertTrue(added.getX() == 2);
    assertTrue(added.getY() == 4);
    assertTrue(added.getZ() == 6);
  }

  @Test
  public void testSub() {
    Vector3.Primitive subtracted = vec.sub(1);
    assertTrue(subtracted.getX() == 0);
    assertTrue(subtracted.getY() == 1);
    assertTrue(subtracted.getZ() == 2);

    subtracted = vec.sub(vec);
    assertTrue(subtracted.getX() == 0);
    assertTrue(subtracted.getY() == 0);
    assertTrue(subtracted.getZ() == 0);
  }

  @Test
  public void testMul() {
    Vector3.Primitive scaled = vec.mul(3);
    assertTrue(scaled.getX() == 3);
    assertTrue(scaled.getY() == 6);
    assertTrue(scaled.getZ() == 9);

    scaled = vec.mul(vec);
    assertTrue(scaled.getX() == 1);
    assertTrue(scaled.getY() == 4);
    assertTrue(scaled.getZ() == 9);
  }

  @Test
  public void testDiv() {
    Vector3.Primitive divided = vec.div(2);
    assertTrue(divided.getX() == 0.5);
    assertTrue(divided.getY() == 1);
    assertTrue(divided.getZ() == 1.5);

    divided = vec.div(vec);
    assertTrue(divided.getX() == 1);
    assertTrue(divided.getY() == 1);
    assertTrue(divided.getZ() == 1);
  }

  @Test
  public void testNeg() {
    Vector3.Primitive negated = vec.neg();

    assertTrue(negated.getX() == -1);
    assertTrue(negated.getY() == -2);
    assertTrue(negated.getZ() == -3);
  }

  @Test
  public void testNormalize() {
    Vector3.Primitive normalized = vec.normalize();

    assertEquals(normalized.getX(), 0.2672612419124244, 10e-8);
    assertEquals(normalized.getY(), 0.5345224838248488, 10e-8);
    assertEquals(normalized.getZ(), 0.8017837257372732, 10e-8);
  }

  @Test
  public void testLength() {
    float length = vec.length();

    assertEquals(length, 3.7416573867739413, 10e-7);
  }

  @Test
  public void testDot() {
    float dot = vec.dot(vec);

    assertTrue(dot == 14);
  }

  @Test
  public void testCross() {
    Vector3.Primitive cross = vec.cross(new Vector3.Primitive(4, 5, 6));

    assertEquals(cross, new Vector3.Primitive(-3, 6, -3));
  }

  @Test
  public void testBooleanOperations() {
    BVector3.Primitive lessThan = vec.isLessThan(new Vector3.Primitive(1, 3, 3));
    assertFalse(lessThan.getX());
    assertTrue(lessThan.getY());
    assertFalse(lessThan.getZ());

    BVector3.Primitive lessThanOrEqual = vec.isLessThanOrEqual(new Vector3.Primitive(1, 3, 3));
    assertTrue(lessThanOrEqual.getX());
    assertTrue(lessThanOrEqual.getY());
    assertTrue(lessThanOrEqual.getZ());

    BVector3.Primitive greaterThan = vec.isGreaterThan(new Vector3.Primitive(1, 1, 1));
    assertFalse(greaterThan.getX());
    assertTrue(greaterThan.getY());
    assertTrue(greaterThan.getZ());

    BVector3.Primitive greaterThanOrEqual = vec.isGreaterThanOrEqual(new Vector3.Primitive(1, 1, 1));
    assertTrue(greaterThanOrEqual.getX());
    assertTrue(greaterThanOrEqual.getY());
    assertTrue(greaterThanOrEqual.getZ());

    BVector3.Primitive equalComponentwise = vec.isEqualComponentwise(new Vector3.Primitive(1, 1, 1));
    assertTrue(equalComponentwise.getX());
    assertFalse(equalComponentwise.getY());
    assertFalse(equalComponentwise.getZ());

    BVector3.Primitive notEqualComponentwise = vec.isNotEqualComponentwise(new Vector3.Primitive(1, 1, 1));
    assertFalse(notEqualComponentwise.getX());
    assertTrue(notEqualComponentwise.getY());
    assertTrue(notEqualComponentwise.getZ());
  }

  @Test
  @SuppressWarnings("all")
  public void testEquals() {
    Vector3.Primitive equal = new Vector3.Primitive(1, 2, 3);
    assertEquals(vec, equal);

    Vector3.Primitive unequal = new Vector3.Primitive(3, 4, 5);
    assertFalse(vec.equals(unequal));

    assertFalse(vec.equals(null));

    assertFalse(vec.equals("A string"));
  }

  @Test
  public void testHashCode() {
    Vector3.Primitive equal = new Vector3.Primitive(1, 2, 3);

    assertEquals(vec, equal);
    assertEquals(vec.hashCode(), equal.hashCode());
  }

  @Test
  public void testToString() {
    String toString = vec.toString();
    assertEquals(toString, "vec3(1.0, 2.0, 3.0)");
  }
}
