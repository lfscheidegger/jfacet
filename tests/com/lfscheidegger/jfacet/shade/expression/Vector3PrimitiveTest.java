package com.lfscheidegger.jfacet.shade.expression;

import com.lfscheidegger.jfacet.shade.expression.BVector3;
import com.lfscheidegger.jfacet.shade.expression.Vector2;
import com.lfscheidegger.jfacet.shade.expression.Vector3;
import com.lfscheidegger.jfacet.shade.expression.Vector4;
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
    assertTrue(vec.x().get() == 1);
    assertTrue(vec.y().get() == 2);
    assertTrue(vec.z().get() == 3);

    assertEquals(vec.x().x().get(), new Vector2.Primitive(1, 1));
    assertEquals(vec.x().y().get(), new Vector2.Primitive(1, 2));
    assertEquals(vec.x().z().get(), new Vector2.Primitive(1, 3));
    assertEquals(vec.y().x().get(), new Vector2.Primitive(2, 1));
    assertEquals(vec.y().y().get(), new Vector2.Primitive(2, 2));
    assertEquals(vec.y().z().get(), new Vector2.Primitive(2, 3));
    assertEquals(vec.z().x().get(), new Vector2.Primitive(3, 1));
    assertEquals(vec.z().y().get(), new Vector2.Primitive(3, 2));
    assertEquals(vec.z().z().get(), new Vector2.Primitive(3, 3));

    assertEquals(vec.x().x().x().get(), new Vector3.Primitive(1, 1, 1));
    assertEquals(vec.x().x().y().get(), new Vector3.Primitive(1, 1, 2));
    assertEquals(vec.x().x().z().get(), new Vector3.Primitive(1, 1, 3));
    assertEquals(vec.x().y().x().get(), new Vector3.Primitive(1, 2, 1));
    assertEquals(vec.x().y().y().get(), new Vector3.Primitive(1, 2, 2));
    assertEquals(vec.x().y().z().get(), new Vector3.Primitive(1, 2, 3));
    assertEquals(vec.x().z().x().get(), new Vector3.Primitive(1, 3, 1));
    assertEquals(vec.x().z().y().get(), new Vector3.Primitive(1, 3, 2));
    assertEquals(vec.x().z().z().get(), new Vector3.Primitive(1, 3, 3));
    assertEquals(vec.y().x().x().get(), new Vector3.Primitive(2, 1, 1));
    assertEquals(vec.y().x().y().get(), new Vector3.Primitive(2, 1, 2));
    assertEquals(vec.y().x().z().get(), new Vector3.Primitive(2, 1, 3));
    assertEquals(vec.y().y().x().get(), new Vector3.Primitive(2, 2, 1));
    assertEquals(vec.y().y().y().get(), new Vector3.Primitive(2, 2, 2));
    assertEquals(vec.y().y().z().get(), new Vector3.Primitive(2, 2, 3));
    assertEquals(vec.y().z().x().get(), new Vector3.Primitive(2, 3, 1));
    assertEquals(vec.y().z().y().get(), new Vector3.Primitive(2, 3, 2));
    assertEquals(vec.y().z().z().get(), new Vector3.Primitive(2, 3, 3));
    assertEquals(vec.z().x().x().get(), new Vector3.Primitive(3, 1, 1));
    assertEquals(vec.z().x().y().get(), new Vector3.Primitive(3, 1, 2));
    assertEquals(vec.z().x().z().get(), new Vector3.Primitive(3, 1, 3));
    assertEquals(vec.z().y().x().get(), new Vector3.Primitive(3, 2, 1));
    assertEquals(vec.z().y().y().get(), new Vector3.Primitive(3, 2, 2));
    assertEquals(vec.z().y().z().get(), new Vector3.Primitive(3, 2, 3));
    assertEquals(vec.z().z().x().get(), new Vector3.Primitive(3, 3, 1));
    assertEquals(vec.z().z().y().get(), new Vector3.Primitive(3, 3, 2));
    assertEquals(vec.z().z().z().get(), new Vector3.Primitive(3, 3, 3));

    assertEquals(vec.x().x().x().x(), new Vector4.Primitive(1, 1, 1, 1));
    assertEquals(vec.x().x().x().y(), new Vector4.Primitive(1, 1, 1, 2));
    assertEquals(vec.x().x().x().z(), new Vector4.Primitive(1, 1, 1, 3));
    assertEquals(vec.x().x().y().x(), new Vector4.Primitive(1, 1, 2, 1));
    assertEquals(vec.x().x().y().y(), new Vector4.Primitive(1, 1, 2, 2));
    assertEquals(vec.x().x().y().z(), new Vector4.Primitive(1, 1, 2, 3));
    assertEquals(vec.x().x().z().x(), new Vector4.Primitive(1, 1, 3, 1));
    assertEquals(vec.x().x().z().y(), new Vector4.Primitive(1, 1, 3, 2));
    assertEquals(vec.x().x().z().z(), new Vector4.Primitive(1, 1, 3, 3));
    assertEquals(vec.x().y().x().x(), new Vector4.Primitive(1, 2, 1, 1));
    assertEquals(vec.x().y().x().y(), new Vector4.Primitive(1, 2, 1, 2));
    assertEquals(vec.x().y().x().z(), new Vector4.Primitive(1, 2, 1, 3));
    assertEquals(vec.x().y().y().x(), new Vector4.Primitive(1, 2, 2, 1));
    assertEquals(vec.x().y().y().y(), new Vector4.Primitive(1, 2, 2, 2));
    assertEquals(vec.x().y().y().z(), new Vector4.Primitive(1, 2, 2, 3));
    assertEquals(vec.x().y().z().x(), new Vector4.Primitive(1, 2, 3, 1));
    assertEquals(vec.x().y().z().y(), new Vector4.Primitive(1, 2, 3, 2));
    assertEquals(vec.x().y().z().z(), new Vector4.Primitive(1, 2, 3, 3));
    assertEquals(vec.x().z().x().x(), new Vector4.Primitive(1, 3, 1, 1));
    assertEquals(vec.x().z().x().y(), new Vector4.Primitive(1, 3, 1, 2));
    assertEquals(vec.x().z().x().z(), new Vector4.Primitive(1, 3, 1, 3));
    assertEquals(vec.x().z().y().x(), new Vector4.Primitive(1, 3, 2, 1));
    assertEquals(vec.x().z().y().y(), new Vector4.Primitive(1, 3, 2, 2));
    assertEquals(vec.x().z().y().z(), new Vector4.Primitive(1, 3, 2, 3));
    assertEquals(vec.x().z().z().x(), new Vector4.Primitive(1, 3, 3, 1));
    assertEquals(vec.x().z().z().y(), new Vector4.Primitive(1, 3, 3, 2));
    assertEquals(vec.x().z().z().z(), new Vector4.Primitive(1, 3, 3, 3));
    assertEquals(vec.y().x().x().x(), new Vector4.Primitive(2, 1, 1, 1));
    assertEquals(vec.y().x().x().y(), new Vector4.Primitive(2, 1, 1, 2));
    assertEquals(vec.y().x().x().z(), new Vector4.Primitive(2, 1, 1, 3));
    assertEquals(vec.y().x().y().x(), new Vector4.Primitive(2, 1, 2, 1));
    assertEquals(vec.y().x().y().y(), new Vector4.Primitive(2, 1, 2, 2));
    assertEquals(vec.y().x().y().z(), new Vector4.Primitive(2, 1, 2, 3));
    assertEquals(vec.y().x().z().x(), new Vector4.Primitive(2, 1, 3, 1));
    assertEquals(vec.y().x().z().y(), new Vector4.Primitive(2, 1, 3, 2));
    assertEquals(vec.y().x().z().z(), new Vector4.Primitive(2, 1, 3, 3));
    assertEquals(vec.y().y().x().x(), new Vector4.Primitive(2, 2, 1, 1));
    assertEquals(vec.y().y().x().y(), new Vector4.Primitive(2, 2, 1, 2));
    assertEquals(vec.y().y().x().z(), new Vector4.Primitive(2, 2, 1, 3));
    assertEquals(vec.y().y().y().x(), new Vector4.Primitive(2, 2, 2, 1));
    assertEquals(vec.y().y().y().y(), new Vector4.Primitive(2, 2, 2, 2));
    assertEquals(vec.y().y().y().z(), new Vector4.Primitive(2, 2, 2, 3));
    assertEquals(vec.y().y().z().x(), new Vector4.Primitive(2, 2, 3, 1));
    assertEquals(vec.y().y().z().y(), new Vector4.Primitive(2, 2, 3, 2));
    assertEquals(vec.y().y().z().z(), new Vector4.Primitive(2, 2, 3, 3));
    assertEquals(vec.y().z().x().x(), new Vector4.Primitive(2, 3, 1, 1));
    assertEquals(vec.y().z().x().y(), new Vector4.Primitive(2, 3, 1, 2));
    assertEquals(vec.y().z().x().z(), new Vector4.Primitive(2, 3, 1, 3));
    assertEquals(vec.y().z().y().x(), new Vector4.Primitive(2, 3, 2, 1));
    assertEquals(vec.y().z().y().y(), new Vector4.Primitive(2, 3, 2, 2));
    assertEquals(vec.y().z().y().z(), new Vector4.Primitive(2, 3, 2, 3));
    assertEquals(vec.y().z().z().x(), new Vector4.Primitive(2, 3, 3, 1));
    assertEquals(vec.y().z().z().y(), new Vector4.Primitive(2, 3, 3, 2));
    assertEquals(vec.y().z().z().z(), new Vector4.Primitive(2, 3, 3, 3));
    assertEquals(vec.z().x().x().x(), new Vector4.Primitive(3, 1, 1, 1));
    assertEquals(vec.z().x().x().y(), new Vector4.Primitive(3, 1, 1, 2));
    assertEquals(vec.z().x().x().z(), new Vector4.Primitive(3, 1, 1, 3));
    assertEquals(vec.z().x().y().x(), new Vector4.Primitive(3, 1, 2, 1));
    assertEquals(vec.z().x().y().y(), new Vector4.Primitive(3, 1, 2, 2));
    assertEquals(vec.z().x().y().z(), new Vector4.Primitive(3, 1, 2, 3));
    assertEquals(vec.z().x().z().x(), new Vector4.Primitive(3, 1, 3, 1));
    assertEquals(vec.z().x().z().y(), new Vector4.Primitive(3, 1, 3, 2));
    assertEquals(vec.z().x().z().z(), new Vector4.Primitive(3, 1, 3, 3));
    assertEquals(vec.z().y().x().x(), new Vector4.Primitive(3, 2, 1, 1));
    assertEquals(vec.z().y().x().y(), new Vector4.Primitive(3, 2, 1, 2));
    assertEquals(vec.z().y().x().z(), new Vector4.Primitive(3, 2, 1, 3));
    assertEquals(vec.z().y().y().x(), new Vector4.Primitive(3, 2, 2, 1));
    assertEquals(vec.z().y().y().y(), new Vector4.Primitive(3, 2, 2, 2));
    assertEquals(vec.z().y().y().z(), new Vector4.Primitive(3, 2, 2, 3));
    assertEquals(vec.z().y().z().x(), new Vector4.Primitive(3, 2, 3, 1));
    assertEquals(vec.z().y().z().y(), new Vector4.Primitive(3, 2, 3, 2));
    assertEquals(vec.z().y().z().z(), new Vector4.Primitive(3, 2, 3, 3));
    assertEquals(vec.z().z().x().x(), new Vector4.Primitive(3, 3, 1, 1));
    assertEquals(vec.z().z().x().y(), new Vector4.Primitive(3, 3, 1, 2));
    assertEquals(vec.z().z().x().z(), new Vector4.Primitive(3, 3, 1, 3));
    assertEquals(vec.z().z().y().x(), new Vector4.Primitive(3, 3, 2, 1));
    assertEquals(vec.z().z().y().y(), new Vector4.Primitive(3, 3, 2, 2));
    assertEquals(vec.z().z().y().z(), new Vector4.Primitive(3, 3, 2, 3));
    assertEquals(vec.z().z().z().x(), new Vector4.Primitive(3, 3, 3, 1));
    assertEquals(vec.z().z().z().y(), new Vector4.Primitive(3, 3, 3, 2));
    assertEquals(vec.z().z().z().z(), new Vector4.Primitive(3, 3, 3, 3));

  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleRGBA() {
    assertTrue(vec.r().get() == 1);
    assertTrue(vec.g().get() == 2);
    assertTrue(vec.b().get() == 3);

    assertEquals(vec.r().r().get(), new Vector2.Primitive(1, 1));
    assertEquals(vec.r().g().get(), new Vector2.Primitive(1, 2));
    assertEquals(vec.r().b().get(), new Vector2.Primitive(1, 3));
    assertEquals(vec.g().r().get(), new Vector2.Primitive(2, 1));
    assertEquals(vec.g().g().get(), new Vector2.Primitive(2, 2));
    assertEquals(vec.g().b().get(), new Vector2.Primitive(2, 3));
    assertEquals(vec.b().r().get(), new Vector2.Primitive(3, 1));
    assertEquals(vec.b().g().get(), new Vector2.Primitive(3, 2));
    assertEquals(vec.b().b().get(), new Vector2.Primitive(3, 3));

    assertEquals(vec.r().r().r().get(), new Vector3.Primitive(1, 1, 1));
    assertEquals(vec.r().r().g().get(), new Vector3.Primitive(1, 1, 2));
    assertEquals(vec.r().r().b().get(), new Vector3.Primitive(1, 1, 3));
    assertEquals(vec.r().g().r().get(), new Vector3.Primitive(1, 2, 1));
    assertEquals(vec.r().g().g().get(), new Vector3.Primitive(1, 2, 2));
    assertEquals(vec.r().g().b().get(), new Vector3.Primitive(1, 2, 3));
    assertEquals(vec.r().b().r().get(), new Vector3.Primitive(1, 3, 1));
    assertEquals(vec.r().b().g().get(), new Vector3.Primitive(1, 3, 2));
    assertEquals(vec.r().b().b().get(), new Vector3.Primitive(1, 3, 3));
    assertEquals(vec.g().r().r().get(), new Vector3.Primitive(2, 1, 1));
    assertEquals(vec.g().r().g().get(), new Vector3.Primitive(2, 1, 2));
    assertEquals(vec.g().r().b().get(), new Vector3.Primitive(2, 1, 3));
    assertEquals(vec.g().g().r().get(), new Vector3.Primitive(2, 2, 1));
    assertEquals(vec.g().g().g().get(), new Vector3.Primitive(2, 2, 2));
    assertEquals(vec.g().g().b().get(), new Vector3.Primitive(2, 2, 3));
    assertEquals(vec.g().b().r().get(), new Vector3.Primitive(2, 3, 1));
    assertEquals(vec.g().b().g().get(), new Vector3.Primitive(2, 3, 2));
    assertEquals(vec.g().b().b().get(), new Vector3.Primitive(2, 3, 3));
    assertEquals(vec.b().r().r().get(), new Vector3.Primitive(3, 1, 1));
    assertEquals(vec.b().r().g().get(), new Vector3.Primitive(3, 1, 2));
    assertEquals(vec.b().r().b().get(), new Vector3.Primitive(3, 1, 3));
    assertEquals(vec.b().g().r().get(), new Vector3.Primitive(3, 2, 1));
    assertEquals(vec.b().g().g().get(), new Vector3.Primitive(3, 2, 2));
    assertEquals(vec.b().g().b().get(), new Vector3.Primitive(3, 2, 3));
    assertEquals(vec.b().b().r().get(), new Vector3.Primitive(3, 3, 1));
    assertEquals(vec.b().b().g().get(), new Vector3.Primitive(3, 3, 2));
    assertEquals(vec.b().b().b().get(), new Vector3.Primitive(3, 3, 3));

    assertEquals(vec.r().r().r().r(), new Vector4.Primitive(1, 1, 1, 1));
    assertEquals(vec.r().r().r().g(), new Vector4.Primitive(1, 1, 1, 2));
    assertEquals(vec.r().r().r().b(), new Vector4.Primitive(1, 1, 1, 3));
    assertEquals(vec.r().r().g().r(), new Vector4.Primitive(1, 1, 2, 1));
    assertEquals(vec.r().r().g().g(), new Vector4.Primitive(1, 1, 2, 2));
    assertEquals(vec.r().r().g().b(), new Vector4.Primitive(1, 1, 2, 3));
    assertEquals(vec.r().r().b().r(), new Vector4.Primitive(1, 1, 3, 1));
    assertEquals(vec.r().r().b().g(), new Vector4.Primitive(1, 1, 3, 2));
    assertEquals(vec.r().r().b().b(), new Vector4.Primitive(1, 1, 3, 3));
    assertEquals(vec.r().g().r().r(), new Vector4.Primitive(1, 2, 1, 1));
    assertEquals(vec.r().g().r().g(), new Vector4.Primitive(1, 2, 1, 2));
    assertEquals(vec.r().g().r().b(), new Vector4.Primitive(1, 2, 1, 3));
    assertEquals(vec.r().g().g().r(), new Vector4.Primitive(1, 2, 2, 1));
    assertEquals(vec.r().g().g().g(), new Vector4.Primitive(1, 2, 2, 2));
    assertEquals(vec.r().g().g().b(), new Vector4.Primitive(1, 2, 2, 3));
    assertEquals(vec.r().g().b().r(), new Vector4.Primitive(1, 2, 3, 1));
    assertEquals(vec.r().g().b().g(), new Vector4.Primitive(1, 2, 3, 2));
    assertEquals(vec.r().g().b().b(), new Vector4.Primitive(1, 2, 3, 3));
    assertEquals(vec.r().b().r().r(), new Vector4.Primitive(1, 3, 1, 1));
    assertEquals(vec.r().b().r().g(), new Vector4.Primitive(1, 3, 1, 2));
    assertEquals(vec.r().b().r().b(), new Vector4.Primitive(1, 3, 1, 3));
    assertEquals(vec.r().b().g().r(), new Vector4.Primitive(1, 3, 2, 1));
    assertEquals(vec.r().b().g().g(), new Vector4.Primitive(1, 3, 2, 2));
    assertEquals(vec.r().b().g().b(), new Vector4.Primitive(1, 3, 2, 3));
    assertEquals(vec.r().b().b().r(), new Vector4.Primitive(1, 3, 3, 1));
    assertEquals(vec.r().b().b().g(), new Vector4.Primitive(1, 3, 3, 2));
    assertEquals(vec.r().b().b().b(), new Vector4.Primitive(1, 3, 3, 3));
    assertEquals(vec.g().r().r().r(), new Vector4.Primitive(2, 1, 1, 1));
    assertEquals(vec.g().r().r().g(), new Vector4.Primitive(2, 1, 1, 2));
    assertEquals(vec.g().r().r().b(), new Vector4.Primitive(2, 1, 1, 3));
    assertEquals(vec.g().r().g().r(), new Vector4.Primitive(2, 1, 2, 1));
    assertEquals(vec.g().r().g().g(), new Vector4.Primitive(2, 1, 2, 2));
    assertEquals(vec.g().r().g().b(), new Vector4.Primitive(2, 1, 2, 3));
    assertEquals(vec.g().r().b().r(), new Vector4.Primitive(2, 1, 3, 1));
    assertEquals(vec.g().r().b().g(), new Vector4.Primitive(2, 1, 3, 2));
    assertEquals(vec.g().r().b().b(), new Vector4.Primitive(2, 1, 3, 3));
    assertEquals(vec.g().g().r().r(), new Vector4.Primitive(2, 2, 1, 1));
    assertEquals(vec.g().g().r().g(), new Vector4.Primitive(2, 2, 1, 2));
    assertEquals(vec.g().g().r().b(), new Vector4.Primitive(2, 2, 1, 3));
    assertEquals(vec.g().g().g().r(), new Vector4.Primitive(2, 2, 2, 1));
    assertEquals(vec.g().g().g().g(), new Vector4.Primitive(2, 2, 2, 2));
    assertEquals(vec.g().g().g().b(), new Vector4.Primitive(2, 2, 2, 3));
    assertEquals(vec.g().g().b().r(), new Vector4.Primitive(2, 2, 3, 1));
    assertEquals(vec.g().g().b().g(), new Vector4.Primitive(2, 2, 3, 2));
    assertEquals(vec.g().g().b().b(), new Vector4.Primitive(2, 2, 3, 3));
    assertEquals(vec.g().b().r().r(), new Vector4.Primitive(2, 3, 1, 1));
    assertEquals(vec.g().b().r().g(), new Vector4.Primitive(2, 3, 1, 2));
    assertEquals(vec.g().b().r().b(), new Vector4.Primitive(2, 3, 1, 3));
    assertEquals(vec.g().b().g().r(), new Vector4.Primitive(2, 3, 2, 1));
    assertEquals(vec.g().b().g().g(), new Vector4.Primitive(2, 3, 2, 2));
    assertEquals(vec.g().b().g().b(), new Vector4.Primitive(2, 3, 2, 3));
    assertEquals(vec.g().b().b().r(), new Vector4.Primitive(2, 3, 3, 1));
    assertEquals(vec.g().b().b().g(), new Vector4.Primitive(2, 3, 3, 2));
    assertEquals(vec.g().b().b().b(), new Vector4.Primitive(2, 3, 3, 3));
    assertEquals(vec.b().r().r().r(), new Vector4.Primitive(3, 1, 1, 1));
    assertEquals(vec.b().r().r().g(), new Vector4.Primitive(3, 1, 1, 2));
    assertEquals(vec.b().r().r().b(), new Vector4.Primitive(3, 1, 1, 3));
    assertEquals(vec.b().r().g().r(), new Vector4.Primitive(3, 1, 2, 1));
    assertEquals(vec.b().r().g().g(), new Vector4.Primitive(3, 1, 2, 2));
    assertEquals(vec.b().r().g().b(), new Vector4.Primitive(3, 1, 2, 3));
    assertEquals(vec.b().r().b().r(), new Vector4.Primitive(3, 1, 3, 1));
    assertEquals(vec.b().r().b().g(), new Vector4.Primitive(3, 1, 3, 2));
    assertEquals(vec.b().r().b().b(), new Vector4.Primitive(3, 1, 3, 3));
    assertEquals(vec.b().g().r().r(), new Vector4.Primitive(3, 2, 1, 1));
    assertEquals(vec.b().g().r().g(), new Vector4.Primitive(3, 2, 1, 2));
    assertEquals(vec.b().g().r().b(), new Vector4.Primitive(3, 2, 1, 3));
    assertEquals(vec.b().g().g().r(), new Vector4.Primitive(3, 2, 2, 1));
    assertEquals(vec.b().g().g().g(), new Vector4.Primitive(3, 2, 2, 2));
    assertEquals(vec.b().g().g().b(), new Vector4.Primitive(3, 2, 2, 3));
    assertEquals(vec.b().g().b().r(), new Vector4.Primitive(3, 2, 3, 1));
    assertEquals(vec.b().g().b().g(), new Vector4.Primitive(3, 2, 3, 2));
    assertEquals(vec.b().g().b().b(), new Vector4.Primitive(3, 2, 3, 3));
    assertEquals(vec.b().b().r().r(), new Vector4.Primitive(3, 3, 1, 1));
    assertEquals(vec.b().b().r().g(), new Vector4.Primitive(3, 3, 1, 2));
    assertEquals(vec.b().b().r().b(), new Vector4.Primitive(3, 3, 1, 3));
    assertEquals(vec.b().b().g().r(), new Vector4.Primitive(3, 3, 2, 1));
    assertEquals(vec.b().b().g().g(), new Vector4.Primitive(3, 3, 2, 2));
    assertEquals(vec.b().b().g().b(), new Vector4.Primitive(3, 3, 2, 3));
    assertEquals(vec.b().b().b().r(), new Vector4.Primitive(3, 3, 3, 1));
    assertEquals(vec.b().b().b().g(), new Vector4.Primitive(3, 3, 3, 2));
    assertEquals(vec.b().b().b().b(), new Vector4.Primitive(3, 3, 3, 3));

  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleSTPQ() {
    assertTrue(vec.s().get() == 1);
    assertTrue(vec.t().get() == 2);
    assertTrue(vec.p().get() == 3);

    assertEquals(vec.s().s().get(), new Vector2.Primitive(1, 1));
    assertEquals(vec.s().t().get(), new Vector2.Primitive(1, 2));
    assertEquals(vec.s().p().get(), new Vector2.Primitive(1, 3));
    assertEquals(vec.t().s().get(), new Vector2.Primitive(2, 1));
    assertEquals(vec.t().t().get(), new Vector2.Primitive(2, 2));
    assertEquals(vec.t().p().get(), new Vector2.Primitive(2, 3));
    assertEquals(vec.p().s().get(), new Vector2.Primitive(3, 1));
    assertEquals(vec.p().t().get(), new Vector2.Primitive(3, 2));
    assertEquals(vec.p().p().get(), new Vector2.Primitive(3, 3));

    assertEquals(vec.s().s().s().get(), new Vector3.Primitive(1, 1, 1));
    assertEquals(vec.s().s().t().get(), new Vector3.Primitive(1, 1, 2));
    assertEquals(vec.s().s().p().get(), new Vector3.Primitive(1, 1, 3));
    assertEquals(vec.s().t().s().get(), new Vector3.Primitive(1, 2, 1));
    assertEquals(vec.s().t().t().get(), new Vector3.Primitive(1, 2, 2));
    assertEquals(vec.s().t().p().get(), new Vector3.Primitive(1, 2, 3));
    assertEquals(vec.s().p().s().get(), new Vector3.Primitive(1, 3, 1));
    assertEquals(vec.s().p().t().get(), new Vector3.Primitive(1, 3, 2));
    assertEquals(vec.s().p().p().get(), new Vector3.Primitive(1, 3, 3));
    assertEquals(vec.t().s().s().get(), new Vector3.Primitive(2, 1, 1));
    assertEquals(vec.t().s().t().get(), new Vector3.Primitive(2, 1, 2));
    assertEquals(vec.t().s().p().get(), new Vector3.Primitive(2, 1, 3));
    assertEquals(vec.t().t().s().get(), new Vector3.Primitive(2, 2, 1));
    assertEquals(vec.t().t().t().get(), new Vector3.Primitive(2, 2, 2));
    assertEquals(vec.t().t().p().get(), new Vector3.Primitive(2, 2, 3));
    assertEquals(vec.t().p().s().get(), new Vector3.Primitive(2, 3, 1));
    assertEquals(vec.t().p().t().get(), new Vector3.Primitive(2, 3, 2));
    assertEquals(vec.t().p().p().get(), new Vector3.Primitive(2, 3, 3));
    assertEquals(vec.p().s().s().get(), new Vector3.Primitive(3, 1, 1));
    assertEquals(vec.p().s().t().get(), new Vector3.Primitive(3, 1, 2));
    assertEquals(vec.p().s().p().get(), new Vector3.Primitive(3, 1, 3));
    assertEquals(vec.p().t().s().get(), new Vector3.Primitive(3, 2, 1));
    assertEquals(vec.p().t().t().get(), new Vector3.Primitive(3, 2, 2));
    assertEquals(vec.p().t().p().get(), new Vector3.Primitive(3, 2, 3));
    assertEquals(vec.p().p().s().get(), new Vector3.Primitive(3, 3, 1));
    assertEquals(vec.p().p().t().get(), new Vector3.Primitive(3, 3, 2));
    assertEquals(vec.p().p().p().get(), new Vector3.Primitive(3, 3, 3));

    assertEquals(vec.s().s().s().s(), new Vector4.Primitive(1, 1, 1, 1));
    assertEquals(vec.s().s().s().t(), new Vector4.Primitive(1, 1, 1, 2));
    assertEquals(vec.s().s().s().p(), new Vector4.Primitive(1, 1, 1, 3));
    assertEquals(vec.s().s().t().s(), new Vector4.Primitive(1, 1, 2, 1));
    assertEquals(vec.s().s().t().t(), new Vector4.Primitive(1, 1, 2, 2));
    assertEquals(vec.s().s().t().p(), new Vector4.Primitive(1, 1, 2, 3));
    assertEquals(vec.s().s().p().s(), new Vector4.Primitive(1, 1, 3, 1));
    assertEquals(vec.s().s().p().t(), new Vector4.Primitive(1, 1, 3, 2));
    assertEquals(vec.s().s().p().p(), new Vector4.Primitive(1, 1, 3, 3));
    assertEquals(vec.s().t().s().s(), new Vector4.Primitive(1, 2, 1, 1));
    assertEquals(vec.s().t().s().t(), new Vector4.Primitive(1, 2, 1, 2));
    assertEquals(vec.s().t().s().p(), new Vector4.Primitive(1, 2, 1, 3));
    assertEquals(vec.s().t().t().s(), new Vector4.Primitive(1, 2, 2, 1));
    assertEquals(vec.s().t().t().t(), new Vector4.Primitive(1, 2, 2, 2));
    assertEquals(vec.s().t().t().p(), new Vector4.Primitive(1, 2, 2, 3));
    assertEquals(vec.s().t().p().s(), new Vector4.Primitive(1, 2, 3, 1));
    assertEquals(vec.s().t().p().t(), new Vector4.Primitive(1, 2, 3, 2));
    assertEquals(vec.s().t().p().p(), new Vector4.Primitive(1, 2, 3, 3));
    assertEquals(vec.s().p().s().s(), new Vector4.Primitive(1, 3, 1, 1));
    assertEquals(vec.s().p().s().t(), new Vector4.Primitive(1, 3, 1, 2));
    assertEquals(vec.s().p().s().p(), new Vector4.Primitive(1, 3, 1, 3));
    assertEquals(vec.s().p().t().s(), new Vector4.Primitive(1, 3, 2, 1));
    assertEquals(vec.s().p().t().t(), new Vector4.Primitive(1, 3, 2, 2));
    assertEquals(vec.s().p().t().p(), new Vector4.Primitive(1, 3, 2, 3));
    assertEquals(vec.s().p().p().s(), new Vector4.Primitive(1, 3, 3, 1));
    assertEquals(vec.s().p().p().t(), new Vector4.Primitive(1, 3, 3, 2));
    assertEquals(vec.s().p().p().p(), new Vector4.Primitive(1, 3, 3, 3));
    assertEquals(vec.t().s().s().s(), new Vector4.Primitive(2, 1, 1, 1));
    assertEquals(vec.t().s().s().t(), new Vector4.Primitive(2, 1, 1, 2));
    assertEquals(vec.t().s().s().p(), new Vector4.Primitive(2, 1, 1, 3));
    assertEquals(vec.t().s().t().s(), new Vector4.Primitive(2, 1, 2, 1));
    assertEquals(vec.t().s().t().t(), new Vector4.Primitive(2, 1, 2, 2));
    assertEquals(vec.t().s().t().p(), new Vector4.Primitive(2, 1, 2, 3));
    assertEquals(vec.t().s().p().s(), new Vector4.Primitive(2, 1, 3, 1));
    assertEquals(vec.t().s().p().t(), new Vector4.Primitive(2, 1, 3, 2));
    assertEquals(vec.t().s().p().p(), new Vector4.Primitive(2, 1, 3, 3));
    assertEquals(vec.t().t().s().s(), new Vector4.Primitive(2, 2, 1, 1));
    assertEquals(vec.t().t().s().t(), new Vector4.Primitive(2, 2, 1, 2));
    assertEquals(vec.t().t().s().p(), new Vector4.Primitive(2, 2, 1, 3));
    assertEquals(vec.t().t().t().s(), new Vector4.Primitive(2, 2, 2, 1));
    assertEquals(vec.t().t().t().t(), new Vector4.Primitive(2, 2, 2, 2));
    assertEquals(vec.t().t().t().p(), new Vector4.Primitive(2, 2, 2, 3));
    assertEquals(vec.t().t().p().s(), new Vector4.Primitive(2, 2, 3, 1));
    assertEquals(vec.t().t().p().t(), new Vector4.Primitive(2, 2, 3, 2));
    assertEquals(vec.t().t().p().p(), new Vector4.Primitive(2, 2, 3, 3));
    assertEquals(vec.t().p().s().s(), new Vector4.Primitive(2, 3, 1, 1));
    assertEquals(vec.t().p().s().t(), new Vector4.Primitive(2, 3, 1, 2));
    assertEquals(vec.t().p().s().p(), new Vector4.Primitive(2, 3, 1, 3));
    assertEquals(vec.t().p().t().s(), new Vector4.Primitive(2, 3, 2, 1));
    assertEquals(vec.t().p().t().t(), new Vector4.Primitive(2, 3, 2, 2));
    assertEquals(vec.t().p().t().p(), new Vector4.Primitive(2, 3, 2, 3));
    assertEquals(vec.t().p().p().s(), new Vector4.Primitive(2, 3, 3, 1));
    assertEquals(vec.t().p().p().t(), new Vector4.Primitive(2, 3, 3, 2));
    assertEquals(vec.t().p().p().p(), new Vector4.Primitive(2, 3, 3, 3));
    assertEquals(vec.p().s().s().s(), new Vector4.Primitive(3, 1, 1, 1));
    assertEquals(vec.p().s().s().t(), new Vector4.Primitive(3, 1, 1, 2));
    assertEquals(vec.p().s().s().p(), new Vector4.Primitive(3, 1, 1, 3));
    assertEquals(vec.p().s().t().s(), new Vector4.Primitive(3, 1, 2, 1));
    assertEquals(vec.p().s().t().t(), new Vector4.Primitive(3, 1, 2, 2));
    assertEquals(vec.p().s().t().p(), new Vector4.Primitive(3, 1, 2, 3));
    assertEquals(vec.p().s().p().s(), new Vector4.Primitive(3, 1, 3, 1));
    assertEquals(vec.p().s().p().t(), new Vector4.Primitive(3, 1, 3, 2));
    assertEquals(vec.p().s().p().p(), new Vector4.Primitive(3, 1, 3, 3));
    assertEquals(vec.p().t().s().s(), new Vector4.Primitive(3, 2, 1, 1));
    assertEquals(vec.p().t().s().t(), new Vector4.Primitive(3, 2, 1, 2));
    assertEquals(vec.p().t().s().p(), new Vector4.Primitive(3, 2, 1, 3));
    assertEquals(vec.p().t().t().s(), new Vector4.Primitive(3, 2, 2, 1));
    assertEquals(vec.p().t().t().t(), new Vector4.Primitive(3, 2, 2, 2));
    assertEquals(vec.p().t().t().p(), new Vector4.Primitive(3, 2, 2, 3));
    assertEquals(vec.p().t().p().s(), new Vector4.Primitive(3, 2, 3, 1));
    assertEquals(vec.p().t().p().t(), new Vector4.Primitive(3, 2, 3, 2));
    assertEquals(vec.p().t().p().p(), new Vector4.Primitive(3, 2, 3, 3));
    assertEquals(vec.p().p().s().s(), new Vector4.Primitive(3, 3, 1, 1));
    assertEquals(vec.p().p().s().t(), new Vector4.Primitive(3, 3, 1, 2));
    assertEquals(vec.p().p().s().p(), new Vector4.Primitive(3, 3, 1, 3));
    assertEquals(vec.p().p().t().s(), new Vector4.Primitive(3, 3, 2, 1));
    assertEquals(vec.p().p().t().t(), new Vector4.Primitive(3, 3, 2, 2));
    assertEquals(vec.p().p().t().p(), new Vector4.Primitive(3, 3, 2, 3));
    assertEquals(vec.p().p().p().s(), new Vector4.Primitive(3, 3, 3, 1));
    assertEquals(vec.p().p().p().t(), new Vector4.Primitive(3, 3, 3, 2));
    assertEquals(vec.p().p().p().p(), new Vector4.Primitive(3, 3, 3, 3));

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
