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
    assertTrue(vec.x().get() == true);
    assertTrue(vec.y().get() == false);
    assertTrue(vec.z().get() == false);

    assertEquals(vec.x().x().get(), new BVector2.Primitive(true, true));
    assertEquals(vec.x().y().get(), new BVector2.Primitive(true, false));
    assertEquals(vec.x().z().get(), new BVector2.Primitive(true, false));
    assertEquals(vec.y().x().get(), new BVector2.Primitive(false, true));
    assertEquals(vec.y().y().get(), new BVector2.Primitive(false, false));
    assertEquals(vec.y().z().get(), new BVector2.Primitive(false, false));
    assertEquals(vec.z().x().get(), new BVector2.Primitive(false, true));
    assertEquals(vec.z().y().get(), new BVector2.Primitive(false, false));
    assertEquals(vec.z().z().get(), new BVector2.Primitive(false, false));

    assertEquals(vec.x().x().x().get(), new BVector3.Primitive(true, true, true));
    assertEquals(vec.x().x().y().get(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.x().x().z().get(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.x().y().x().get(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.x().y().y().get(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.x().y().z().get(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.x().z().x().get(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.x().z().y().get(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.x().z().z().get(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.y().x().x().get(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.y().x().y().get(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.y().x().z().get(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.y().y().x().get(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.y().y().y().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.y().y().z().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.y().z().x().get(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.y().z().y().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.y().z().z().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.z().x().x().get(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.z().x().y().get(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.z().x().z().get(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.z().y().x().get(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.z().y().y().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.z().y().z().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.z().z().x().get(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.z().z().y().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.z().z().z().get(), new BVector3.Primitive(false, false, false));

    assertEquals(vec.x().x().x().x().get(), new BVector4.Primitive(true, true, true, true));
    assertEquals(vec.x().x().x().y().get(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.x().x().x().z().get(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.x().x().y().x().get(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.x().x().y().y().get(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.x().x().y().z().get(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.x().x().z().x().get(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.x().x().z().y().get(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.x().x().z().z().get(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.x().y().x().x().get(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.x().y().x().y().get(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.x().y().x().z().get(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.x().y().y().x().get(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.x().y().y().y().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.x().y().y().z().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.x().y().z().x().get(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.x().y().z().y().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.x().y().z().z().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.x().z().x().x().get(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.x().z().x().y().get(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.x().z().x().z().get(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.x().z().y().x().get(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.x().z().y().y().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.x().z().y().z().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.x().z().z().x().get(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.x().z().z().y().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.x().z().z().z().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.y().x().x().x().get(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.y().x().x().y().get(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.y().x().x().z().get(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.y().x().y().x().get(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.y().x().y().y().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.y().x().y().z().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.y().x().z().x().get(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.y().x().z().y().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.y().x().z().z().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.y().y().x().x().get(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.y().y().x().y().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.y().y().x().z().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.y().y().y().x().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.y().y().y().y().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.y().y().y().z().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.y().y().z().x().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.y().y().z().y().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.y().y().z().z().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.y().z().x().x().get(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.y().z().x().y().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.y().z().x().z().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.y().z().y().x().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.y().z().y().y().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.y().z().y().z().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.y().z().z().x().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.y().z().z().y().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.y().z().z().z().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.z().x().x().x().get(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.z().x().x().y().get(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.z().x().x().z().get(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.z().x().y().x().get(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.z().x().y().y().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.z().x().y().z().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.z().x().z().x().get(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.z().x().z().y().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.z().x().z().z().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.z().y().x().x().get(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.z().y().x().y().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.z().y().x().z().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.z().y().y().x().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.z().y().y().y().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.z().y().y().z().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.z().y().z().x().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.z().y().z().y().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.z().y().z().z().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.z().z().x().x().get(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.z().z().x().y().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.z().z().x().z().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.z().z().y().x().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.z().z().y().y().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.z().z().y().z().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.z().z().z().x().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.z().z().z().y().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.z().z().z().z().get(), new BVector4.Primitive(false, false, false, false));
  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleRGBA() {
    assertTrue(vec.r().get() == true);
    assertTrue(vec.g().get() == false);
    assertTrue(vec.b().get() == false);

    assertEquals(vec.r().r().get(), new BVector2.Primitive(true, true));
    assertEquals(vec.r().g().get(), new BVector2.Primitive(true, false));
    assertEquals(vec.r().b().get(), new BVector2.Primitive(true, false));
    assertEquals(vec.g().r().get(), new BVector2.Primitive(false, true));
    assertEquals(vec.g().g().get(), new BVector2.Primitive(false, false));
    assertEquals(vec.g().b().get(), new BVector2.Primitive(false, false));
    assertEquals(vec.b().r().get(), new BVector2.Primitive(false, true));
    assertEquals(vec.b().g().get(), new BVector2.Primitive(false, false));
    assertEquals(vec.b().b().get(), new BVector2.Primitive(false, false));

    assertEquals(vec.r().r().r().get(), new BVector3.Primitive(true, true, true));
    assertEquals(vec.r().r().g().get(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.r().r().b().get(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.r().g().r().get(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.r().g().g().get(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.r().g().b().get(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.r().b().r().get(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.r().b().g().get(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.r().b().b().get(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.g().r().r().get(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.g().r().g().get(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.g().r().b().get(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.g().g().r().get(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.g().g().g().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.g().g().b().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.g().b().r().get(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.g().b().g().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.g().b().b().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.b().r().r().get(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.b().r().g().get(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.b().r().b().get(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.b().g().r().get(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.b().g().g().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.b().g().b().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.b().b().r().get(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.b().b().g().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.b().b().b().get(), new BVector3.Primitive(false, false, false));

    assertEquals(vec.r().r().r().r().get(), new BVector4.Primitive(true, true, true, true));
    assertEquals(vec.r().r().r().g().get(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.r().r().r().b().get(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.r().r().g().r().get(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.r().r().g().g().get(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.r().r().g().b().get(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.r().r().b().r().get(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.r().r().b().g().get(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.r().r().b().b().get(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.r().g().r().r().get(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.r().g().r().g().get(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.r().g().r().b().get(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.r().g().g().r().get(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.r().g().g().g().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.r().g().g().b().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.r().g().b().r().get(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.r().g().b().g().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.r().g().b().b().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.r().b().r().r().get(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.r().b().r().g().get(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.r().b().r().b().get(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.r().b().g().r().get(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.r().b().g().g().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.r().b().g().b().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.r().b().b().r().get(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.r().b().b().g().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.r().b().b().b().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.g().r().r().r().get(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.g().r().r().g().get(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.g().r().r().b().get(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.g().r().g().r().get(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.g().r().g().g().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.g().r().g().b().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.g().r().b().r().get(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.g().r().b().g().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.g().r().b().b().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.g().g().r().r().get(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.g().g().r().g().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.g().g().r().b().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.g().g().g().r().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.g().g().g().g().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.g().g().g().b().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.g().g().b().r().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.g().g().b().g().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.g().g().b().b().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.g().b().r().r().get(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.g().b().r().g().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.g().b().r().b().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.g().b().g().r().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.g().b().g().g().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.g().b().g().b().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.g().b().b().r().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.g().b().b().g().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.g().b().b().b().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.b().r().r().r().get(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.b().r().r().g().get(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.b().r().r().b().get(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.b().r().g().r().get(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.b().r().g().g().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.b().r().g().b().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.b().r().b().r().get(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.b().r().b().g().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.b().r().b().b().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.b().g().r().r().get(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.b().g().r().g().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.b().g().r().b().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.b().g().g().r().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.b().g().g().g().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.b().g().g().b().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.b().g().b().r().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.b().g().b().g().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.b().g().b().b().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.b().b().r().r().get(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.b().b().r().g().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.b().b().r().b().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.b().b().g().r().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.b().b().g().g().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.b().b().g().b().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.b().b().b().r().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.b().b().b().g().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.b().b().b().b().get(), new BVector4.Primitive(false, false, false, false));
  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleSTPQ() {
    assertTrue(vec.s().get() == true);
    assertTrue(vec.t().get() == false);
    assertTrue(vec.p().get() == false);

    assertEquals(vec.s().s().get(), new BVector2.Primitive(true, true));
    assertEquals(vec.s().t().get(), new BVector2.Primitive(true, false));
    assertEquals(vec.s().p().get(), new BVector2.Primitive(true, false));
    assertEquals(vec.t().s().get(), new BVector2.Primitive(false, true));
    assertEquals(vec.t().t().get(), new BVector2.Primitive(false, false));
    assertEquals(vec.t().p().get(), new BVector2.Primitive(false, false));
    assertEquals(vec.p().s().get(), new BVector2.Primitive(false, true));
    assertEquals(vec.p().t().get(), new BVector2.Primitive(false, false));
    assertEquals(vec.p().p().get(), new BVector2.Primitive(false, false));

    assertEquals(vec.s().s().s().get(), new BVector3.Primitive(true, true, true));
    assertEquals(vec.s().s().t().get(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.s().s().p().get(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.s().t().s().get(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.s().t().t().get(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.s().t().p().get(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.s().p().s().get(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.s().p().t().get(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.s().p().p().get(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.t().s().s().get(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.t().s().t().get(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.t().s().p().get(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.t().t().s().get(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.t().t().t().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.t().t().p().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.t().p().s().get(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.t().p().t().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.t().p().p().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.p().s().s().get(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.p().s().t().get(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.p().s().p().get(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.p().t().s().get(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.p().t().t().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.p().t().p().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.p().p().s().get(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.p().p().t().get(), new BVector3.Primitive(false, false, false));
    assertEquals(vec.p().p().p().get(), new BVector3.Primitive(false, false, false));

    assertEquals(vec.s().s().s().s().get(), new BVector4.Primitive(true, true, true, true));
    assertEquals(vec.s().s().s().t().get(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.s().s().s().p().get(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.s().s().t().s().get(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.s().s().t().t().get(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.s().s().t().p().get(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.s().s().p().s().get(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.s().s().p().t().get(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.s().s().p().p().get(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.s().t().s().s().get(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.s().t().s().t().get(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.s().t().s().p().get(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.s().t().t().s().get(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.s().t().t().t().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.s().t().t().p().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.s().t().p().s().get(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.s().t().p().t().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.s().t().p().p().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.s().p().s().s().get(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.s().p().s().t().get(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.s().p().s().p().get(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.s().p().t().s().get(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.s().p().t().t().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.s().p().t().p().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.s().p().p().s().get(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.s().p().p().t().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.s().p().p().p().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.t().s().s().s().get(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.t().s().s().t().get(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.t().s().s().p().get(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.t().s().t().s().get(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.t().s().t().t().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.t().s().t().p().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.t().s().p().s().get(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.t().s().p().t().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.t().s().p().p().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.t().t().s().s().get(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.t().t().s().t().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.t().t().s().p().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.t().t().t().s().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.t().t().t().t().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.t().t().t().p().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.t().t().p().s().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.t().t().p().t().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.t().t().p().p().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.t().p().s().s().get(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.t().p().s().t().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.t().p().s().p().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.t().p().t().s().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.t().p().t().t().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.t().p().t().p().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.t().p().p().s().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.t().p().p().t().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.t().p().p().p().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.p().s().s().s().get(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.p().s().s().t().get(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.p().s().s().p().get(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.p().s().t().s().get(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.p().s().t().t().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.p().s().t().p().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.p().s().p().s().get(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.p().s().p().t().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.p().s().p().p().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.p().t().s().s().get(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.p().t().s().t().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.p().t().s().p().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.p().t().t().s().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.p().t().t().t().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.p().t().t().p().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.p().t().p().s().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.p().t().p().t().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.p().t().p().p().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.p().p().s().s().get(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.p().p().s().t().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.p().p().s().p().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.p().p().t().s().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.p().p().t().t().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.p().p().t().p().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.p().p().p().s().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.p().p().p().t().get(), new BVector4.Primitive(false, false, false, false));
    assertEquals(vec.p().p().p().p().get(), new BVector4.Primitive(false, false, false, false));
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
