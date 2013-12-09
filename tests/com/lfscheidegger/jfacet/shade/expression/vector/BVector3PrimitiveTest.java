package com.lfscheidegger.jfacet.shade.expression.vector;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code BVector3.Primitive}
 */
public class BVector3PrimitiveTest {

  private final BVector3.Primitive vec;

  public BVector3PrimitiveTest() {
    vec = new BVector3.Primitive(true, false, false);
  }

  @Test
  public void testConstructors() {
    assertTrue(vec.getX());
    assertFalse(vec.getY());
    assertFalse(vec.getZ());
  }

  @Test
  public void testGetters() {
    boolean x = vec.getX();
    boolean y = vec.getY();
    boolean z = vec.getZ();

    assertTrue(x);
    assertFalse(y);
    assertFalse(z);

    assertTrue(vec.get(0) == vec.getX());
    assertTrue(vec.get(1) == vec.getY());
    assertTrue(vec.get(2) == vec.getZ());
  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleXYZW() {
    assertTrue(vec.x().build() == true);
    assertTrue(vec.y().build() == false);
    assertTrue(vec.z().build() == false);

    assertEquals(vec.x().x().build(), new BVector2.Primitive(true, true));
    assertEquals(vec.x().y().build(), new BVector2.Primitive(true, false));
    assertEquals(vec.x().z().build(), new BVector2.Primitive(true, false));
    assertEquals(vec.y().x().build(), new BVector2.Primitive(false, true));
    assertEquals(vec.y().y().build(), new BVector2.Primitive(false, false));
    assertEquals(vec.y().z().build(), new BVector2.Primitive(false, false));
    assertEquals(vec.z().x().build(), new BVector2.Primitive(false, true));
    assertEquals(vec.z().y().build(), new BVector2.Primitive(false, false));
    assertEquals(vec.z().z().build(), new BVector2.Primitive(false, false));

    assertEquals(vec.x().x().x().build(), new BVector3.Primitive(true, true, true));
    assertEquals(vec.x().x().y().build(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.x().x().z().build(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.x().y().x().build(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.x().y().y().build(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.x().y().z().build(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.x().z().x().build(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.x().z().y().build(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.x().z().z().build(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.y().x().x().build(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.y().x().y().build(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.y().x().z().build(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.y().y().x().build(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.y().y().y().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.y().y().z().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.y().z().x().build(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.y().z().y().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.y().z().z().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.z().x().x().build(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.z().x().y().build(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.z().x().z().build(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.z().y().x().build(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.z().y().y().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.z().y().z().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.z().z().x().build(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.z().z().y().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.z().z().z().build(), new BVector3.Primitive(false, false, false));

    assertEquals(vec.x().x().x().x().build(), new BVector4.Primitive(true, true, true, true));
    assertEquals(vec.x().x().x().y().build(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.x().x().x().z().build(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.x().x().y().x().build(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.x().x().y().y().build(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.x().x().y().z().build(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.x().x().z().x().build(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.x().x().z().y().build(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.x().x().z().z().build(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.x().y().x().x().build(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.x().y().x().y().build(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.x().y().x().z().build(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.x().y().y().x().build(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.x().y().y().y().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.x().y().y().z().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.x().y().z().x().build(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.x().y().z().y().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.x().y().z().z().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.x().z().x().x().build(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.x().z().x().y().build(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.x().z().x().z().build(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.x().z().y().x().build(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.x().z().y().y().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.x().z().y().z().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.x().z().z().x().build(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.x().z().z().y().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.x().z().z().z().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.y().x().x().x().build(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.y().x().x().y().build(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.y().x().x().z().build(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.y().x().y().x().build(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.y().x().y().y().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.y().x().y().z().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.y().x().z().x().build(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.y().x().z().y().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.y().x().z().z().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.y().y().x().x().build(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.y().y().x().y().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.y().y().x().z().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.y().y().y().x().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.y().y().y().y().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.y().y().y().z().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.y().y().z().x().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.y().y().z().y().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.y().y().z().z().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.y().z().x().x().build(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.y().z().x().y().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.y().z().x().z().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.y().z().y().x().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.y().z().y().y().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.y().z().y().z().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.y().z().z().x().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.y().z().z().y().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.y().z().z().z().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.z().x().x().x().build(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.z().x().x().y().build(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.z().x().x().z().build(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.z().x().y().x().build(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.z().x().y().y().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.z().x().y().z().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.z().x().z().x().build(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.z().x().z().y().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.z().x().z().z().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.z().y().x().x().build(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.z().y().x().y().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.z().y().x().z().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.z().y().y().x().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.z().y().y().y().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.z().y().y().z().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.z().y().z().x().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.z().y().z().y().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.z().y().z().z().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.z().z().x().x().build(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.z().z().x().y().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.z().z().x().z().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.z().z().y().x().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.z().z().y().y().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.z().z().y().z().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.z().z().z().x().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.z().z().z().y().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.z().z().z().z().build(), new BVector4.Primitive(false, false, false, false));
  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleRGBA() {
    assertTrue(vec.r().build() == true);
    assertTrue(vec.g().build() == false);
    assertTrue(vec.b().build() == false);

    assertEquals(vec.r().r().build(), new BVector2.Primitive(true, true));
    assertEquals(vec.r().g().build(), new BVector2.Primitive(true, false));
    assertEquals(vec.r().b().build(), new BVector2.Primitive(true, false));
    assertEquals(vec.g().r().build(), new BVector2.Primitive(false, true));
    assertEquals(vec.g().g().build(), new BVector2.Primitive(false, false));
    assertEquals(vec.g().b().build(), new BVector2.Primitive(false, false));
    assertEquals(vec.b().r().build(), new BVector2.Primitive(false, true));
    assertEquals(vec.b().g().build(), new BVector2.Primitive(false, false));
    assertEquals(vec.b().b().build(), new BVector2.Primitive(false, false));

    assertEquals(vec.r().r().r().build(), new BVector3.Primitive(true, true, true));
    assertEquals(vec.r().r().g().build(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.r().r().b().build(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.r().g().r().build(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.r().g().g().build(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.r().g().b().build(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.r().b().r().build(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.r().b().g().build(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.r().b().b().build(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.g().r().r().build(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.g().r().g().build(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.g().r().b().build(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.g().g().r().build(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.g().g().g().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.g().g().b().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.g().b().r().build(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.g().b().g().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.g().b().b().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.b().r().r().build(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.b().r().g().build(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.b().r().b().build(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.b().g().r().build(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.b().g().g().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.b().g().b().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.b().b().r().build(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.b().b().g().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.b().b().b().build(), new BVector3.Primitive(false, false, false));

    assertEquals(vec.r().r().r().r().build(), new BVector4.Primitive(true, true, true, true));
    assertEquals(vec.r().r().r().g().build(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.r().r().r().b().build(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.r().r().g().r().build(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.r().r().g().g().build(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.r().r().g().b().build(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.r().r().b().r().build(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.r().r().b().g().build(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.r().r().b().b().build(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.r().g().r().r().build(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.r().g().r().g().build(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.r().g().r().b().build(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.r().g().g().r().build(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.r().g().g().g().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.r().g().g().b().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.r().g().b().r().build(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.r().g().b().g().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.r().g().b().b().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.r().b().r().r().build(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.r().b().r().g().build(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.r().b().r().b().build(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.r().b().g().r().build(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.r().b().g().g().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.r().b().g().b().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.r().b().b().r().build(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.r().b().b().g().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.r().b().b().b().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.g().r().r().r().build(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.g().r().r().g().build(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.g().r().r().b().build(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.g().r().g().r().build(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.g().r().g().g().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.g().r().g().b().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.g().r().b().r().build(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.g().r().b().g().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.g().r().b().b().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.g().g().r().r().build(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.g().g().r().g().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.g().g().r().b().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.g().g().g().r().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.g().g().g().g().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.g().g().g().b().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.g().g().b().r().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.g().g().b().g().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.g().g().b().b().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.g().b().r().r().build(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.g().b().r().g().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.g().b().r().b().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.g().b().g().r().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.g().b().g().g().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.g().b().g().b().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.g().b().b().r().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.g().b().b().g().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.g().b().b().b().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.b().r().r().r().build(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.b().r().r().g().build(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.b().r().r().b().build(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.b().r().g().r().build(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.b().r().g().g().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.b().r().g().b().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.b().r().b().r().build(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.b().r().b().g().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.b().r().b().b().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.b().g().r().r().build(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.b().g().r().g().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.b().g().r().b().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.b().g().g().r().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.b().g().g().g().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.b().g().g().b().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.b().g().b().r().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.b().g().b().g().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.b().g().b().b().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.b().b().r().r().build(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.b().b().r().g().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.b().b().r().b().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.b().b().g().r().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.b().b().g().g().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.b().b().g().b().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.b().b().b().r().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.b().b().b().g().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.b().b().b().b().build(), new BVector4.Primitive(false, false, false, false));
  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleSTPQ() {
    assertTrue(vec.s().build() == true);
    assertTrue(vec.t().build() == false);
    assertTrue(vec.p().build() == false);

    assertEquals(vec.s().s().build(), new BVector2.Primitive(true, true));
    assertEquals(vec.s().t().build(), new BVector2.Primitive(true, false));
    assertEquals(vec.s().p().build(), new BVector2.Primitive(true, false));
    assertEquals(vec.t().s().build(), new BVector2.Primitive(false, true));
    assertEquals(vec.t().t().build(), new BVector2.Primitive(false, false));
    assertEquals(vec.t().p().build(), new BVector2.Primitive(false, false));
    assertEquals(vec.p().s().build(), new BVector2.Primitive(false, true));
    assertEquals(vec.p().t().build(), new BVector2.Primitive(false, false));
    assertEquals(vec.p().p().build(), new BVector2.Primitive(false, false));

    assertEquals(vec.s().s().s().build(), new BVector3.Primitive(true, true, true));
    assertEquals(vec.s().s().t().build(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.s().s().p().build(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.s().t().s().build(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.s().t().t().build(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.s().t().p().build(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.s().p().s().build(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.s().p().t().build(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.s().p().p().build(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.t().s().s().build(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.t().s().t().build(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.t().s().p().build(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.t().t().s().build(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.t().t().t().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.t().t().p().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.t().p().s().build(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.t().p().t().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.t().p().p().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.p().s().s().build(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.p().s().t().build(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.p().s().p().build(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.p().t().s().build(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.p().t().t().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.p().t().p().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.p().p().s().build(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.p().p().t().build(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.p().p().p().build(), new BVector3.Primitive(false, false, false));

    assertEquals(vec.s().s().s().s().build(), new BVector4.Primitive(true, true, true, true));
    assertEquals(vec.s().s().s().t().build(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.s().s().s().p().build(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.s().s().t().s().build(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.s().s().t().t().build(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.s().s().t().p().build(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.s().s().p().s().build(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.s().s().p().t().build(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.s().s().p().p().build(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.s().t().s().s().build(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.s().t().s().t().build(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.s().t().s().p().build(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.s().t().t().s().build(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.s().t().t().t().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.s().t().t().p().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.s().t().p().s().build(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.s().t().p().t().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.s().t().p().p().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.s().p().s().s().build(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.s().p().s().t().build(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.s().p().s().p().build(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.s().p().t().s().build(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.s().p().t().t().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.s().p().t().p().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.s().p().p().s().build(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.s().p().p().t().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.s().p().p().p().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.t().s().s().s().build(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.t().s().s().t().build(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.t().s().s().p().build(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.t().s().t().s().build(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.t().s().t().t().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.t().s().t().p().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.t().s().p().s().build(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.t().s().p().t().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.t().s().p().p().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.t().t().s().s().build(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.t().t().s().t().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.t().t().s().p().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.t().t().t().s().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.t().t().t().t().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.t().t().t().p().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.t().t().p().s().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.t().t().p().t().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.t().t().p().p().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.t().p().s().s().build(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.t().p().s().t().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.t().p().s().p().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.t().p().t().s().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.t().p().t().t().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.t().p().t().p().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.t().p().p().s().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.t().p().p().t().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.t().p().p().p().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.p().s().s().s().build(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.p().s().s().t().build(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.p().s().s().p().build(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.p().s().t().s().build(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.p().s().t().t().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.p().s().t().p().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.p().s().p().s().build(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.p().s().p().t().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.p().s().p().p().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.p().t().s().s().build(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.p().t().s().t().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.p().t().s().p().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.p().t().t().s().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.p().t().t().t().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.p().t().t().p().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.p().t().p().s().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.p().t().p().t().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.p().t().p().p().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.p().p().s().s().build(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.p().p().s().t().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.p().p().s().p().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.p().p().t().s().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.p().p().t().t().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.p().p().t().p().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.p().p().p().s().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.p().p().p().t().build(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.p().p().p().p().build(), new BVector4.Primitive(false, false, false, false));
  }

  @Test
  public void testAny() {
    assertTrue(vec.any());

    BVector3.Primitive other = new BVector3.Primitive(false, false, false);
    assertFalse(other.any());
  }

  @Test
  public void testAll() {
    assertFalse(vec.all());

    BVector3.Primitive other = new BVector3.Primitive(true, true, true);
    assertTrue(other.all());
  }

  @Test
  public void testNot() {
    BVector3.Primitive not = vec.not();

    assertFalse(not.getX());
    assertTrue(not.getY());
    assertTrue(not.getZ());
  }

  @Test
  @SuppressWarnings("all")
  public void testEquals() {
    BVector3.Primitive equal = new BVector3.Primitive(true, false, false);
    assertEquals(vec, equal);

    BVector3.Primitive unequal = new BVector3.Primitive(false, false, false);
    assertFalse(vec.equals(unequal));

    assertFalse(vec.equals(null));

    assertFalse(vec.equals("A string"));
  }

  @Test
  public void testHashCode() {
    BVector3.Primitive equal = new BVector3.Primitive(true, false, false);

    assertEquals(vec, equal);
    assertEquals(vec.hashCode(), equal.hashCode());
  }

  @Test
  public void testToString() {
    String toString = vec.toString();
    assertEquals(toString, "bvec3(true, false, false)");
  }
}
