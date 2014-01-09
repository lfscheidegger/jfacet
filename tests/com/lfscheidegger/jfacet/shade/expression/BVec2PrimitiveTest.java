// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.shade.expression;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code BVec2.Primitive}
 */
public class BVec2PrimitiveTest {

  private final BVec2.Primitive vec;

  public BVec2PrimitiveTest() {
    vec = new BVec2.Primitive(true, false);
  }

  @Test
  public void testConstructors() {
    assertTrue(vec.getX());
    assertFalse(vec.getY());
  }

  @Test
  public void testGetters() {
    boolean x = vec.getX();
    boolean y = vec.getY();

    assertTrue(x);
    assertFalse(y);

    assertTrue(vec.get(0) == vec.getX());
    assertTrue(vec.get(1) == vec.getY());
  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleXYZW() {
    assertTrue(vec.x().get() == true);
    assertTrue(vec.y().get() == false);

    assertEquals(vec.x().x().get(), new BVec2.Primitive(true, true));
    assertEquals(vec.x().y().get(), new BVec2.Primitive(true, false));
    assertEquals(vec.y().x().get(), new BVec2.Primitive(false, true));
    assertEquals(vec.y().y().get(), new BVec2.Primitive(false, false));

    assertEquals(vec.x().x().x().get(), new BVec3.Primitive(true, true, true));
    assertEquals(vec.x().x().y().get(), new BVec3.Primitive(true, true, false));
    assertEquals(vec.x().y().x().get(), new BVec3.Primitive(true, false, true));
    assertEquals(vec.x().y().y().get(), new BVec3.Primitive(true, false, false));
    assertEquals(vec.y().x().x().get(), new BVec3.Primitive(false, true, true));
    assertEquals(vec.y().x().y().get(), new BVec3.Primitive(false, true, false));
    assertEquals(vec.y().y().x().get(), new BVec3.Primitive(false, false, true));
    assertEquals(vec.y().y().y().get(), new BVec3.Primitive(false, false, false));

    assertEquals(vec.x().x().x().x(), new BVec4.Primitive(true, true, true, true));
    assertEquals(vec.x().x().x().y(), new BVec4.Primitive(true, true, true, false));
    assertEquals(vec.x().x().y().x(), new BVec4.Primitive(true, true, false, true));
    assertEquals(vec.x().x().y().y(), new BVec4.Primitive(true, true, false, false));
    assertEquals(vec.x().y().x().x(), new BVec4.Primitive(true, false, true, true));
    assertEquals(vec.x().y().x().y(), new BVec4.Primitive(true, false, true, false));
    assertEquals(vec.x().y().y().x(), new BVec4.Primitive(true, false, false, true));
    assertEquals(vec.x().y().y().y(), new BVec4.Primitive(true, false, false, false));
    assertEquals(vec.y().x().x().x(), new BVec4.Primitive(false, true, true, true));
    assertEquals(vec.y().x().x().y(), new BVec4.Primitive(false, true, true, false));
    assertEquals(vec.y().x().y().x(), new BVec4.Primitive(false, true, false, true));
    assertEquals(vec.y().x().y().y(), new BVec4.Primitive(false, true, false, false));
    assertEquals(vec.y().y().x().x(), new BVec4.Primitive(false, false, true, true));
    assertEquals(vec.y().y().x().y(), new BVec4.Primitive(false, false, true, false));
    assertEquals(vec.y().y().y().x(), new BVec4.Primitive(false, false, false, true));
    assertEquals(vec.y().y().y().y(), new BVec4.Primitive(false, false, false, false));

  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleRGBA() {
    assertTrue(vec.r().get() == true);
    assertTrue(vec.g().get() == false);

    assertEquals(vec.r().r().get(), new BVec2.Primitive(true, true));
    assertEquals(vec.r().g().get(), new BVec2.Primitive(true, false));
    assertEquals(vec.g().r().get(), new BVec2.Primitive(false, true));
    assertEquals(vec.g().g().get(), new BVec2.Primitive(false, false));

    assertEquals(vec.r().r().r().get(), new BVec3.Primitive(true, true, true));
    assertEquals(vec.r().r().g().get(), new BVec3.Primitive(true, true, false));
    assertEquals(vec.r().g().r().get(), new BVec3.Primitive(true, false, true));
    assertEquals(vec.r().g().g().get(), new BVec3.Primitive(true, false, false));
    assertEquals(vec.g().r().r().get(), new BVec3.Primitive(false, true, true));
    assertEquals(vec.g().r().g().get(), new BVec3.Primitive(false, true, false));
    assertEquals(vec.g().g().r().get(), new BVec3.Primitive(false, false, true));
    assertEquals(vec.g().g().g().get(), new BVec3.Primitive(false, false, false));

    assertEquals(vec.r().r().r().r(), new BVec4.Primitive(true, true, true, true));
    assertEquals(vec.r().r().r().g(), new BVec4.Primitive(true, true, true, false));
    assertEquals(vec.r().r().g().r(), new BVec4.Primitive(true, true, false, true));
    assertEquals(vec.r().r().g().g(), new BVec4.Primitive(true, true, false, false));
    assertEquals(vec.r().g().r().r(), new BVec4.Primitive(true, false, true, true));
    assertEquals(vec.r().g().r().g(), new BVec4.Primitive(true, false, true, false));
    assertEquals(vec.r().g().g().r(), new BVec4.Primitive(true, false, false, true));
    assertEquals(vec.r().g().g().g(), new BVec4.Primitive(true, false, false, false));
    assertEquals(vec.g().r().r().r(), new BVec4.Primitive(false, true, true, true));
    assertEquals(vec.g().r().r().g(), new BVec4.Primitive(false, true, true, false));
    assertEquals(vec.g().r().g().r(), new BVec4.Primitive(false, true, false, true));
    assertEquals(vec.g().r().g().g(), new BVec4.Primitive(false, true, false, false));
    assertEquals(vec.g().g().r().r(), new BVec4.Primitive(false, false, true, true));
    assertEquals(vec.g().g().r().g(), new BVec4.Primitive(false, false, true, false));
    assertEquals(vec.g().g().g().r(), new BVec4.Primitive(false, false, false, true));
    assertEquals(vec.g().g().g().g(), new BVec4.Primitive(false, false, false, false));

  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleSTPQ() {
    assertTrue(vec.s().get() == true);
    assertTrue(vec.t().get() == false);

    assertEquals(vec.s().s().get(), new BVec2.Primitive(true, true));
    assertEquals(vec.s().t().get(), new BVec2.Primitive(true, false));
    assertEquals(vec.t().s().get(), new BVec2.Primitive(false, true));
    assertEquals(vec.t().t().get(), new BVec2.Primitive(false, false));

    assertEquals(vec.s().s().s().get(), new BVec3.Primitive(true, true, true));
    assertEquals(vec.s().s().t().get(), new BVec3.Primitive(true, true, false));
    assertEquals(vec.s().t().s().get(), new BVec3.Primitive(true, false, true));
    assertEquals(vec.s().t().t().get(), new BVec3.Primitive(true, false, false));
    assertEquals(vec.t().s().s().get(), new BVec3.Primitive(false, true, true));
    assertEquals(vec.t().s().t().get(), new BVec3.Primitive(false, true, false));
    assertEquals(vec.t().t().s().get(), new BVec3.Primitive(false, false, true));
    assertEquals(vec.t().t().t().get(), new BVec3.Primitive(false, false, false));

    assertEquals(vec.s().s().s().s(), new BVec4.Primitive(true, true, true, true));
    assertEquals(vec.s().s().s().t(), new BVec4.Primitive(true, true, true, false));
    assertEquals(vec.s().s().t().s(), new BVec4.Primitive(true, true, false, true));
    assertEquals(vec.s().s().t().t(), new BVec4.Primitive(true, true, false, false));
    assertEquals(vec.s().t().s().s(), new BVec4.Primitive(true, false, true, true));
    assertEquals(vec.s().t().s().t(), new BVec4.Primitive(true, false, true, false));
    assertEquals(vec.s().t().t().s(), new BVec4.Primitive(true, false, false, true));
    assertEquals(vec.s().t().t().t(), new BVec4.Primitive(true, false, false, false));
    assertEquals(vec.t().s().s().s(), new BVec4.Primitive(false, true, true, true));
    assertEquals(vec.t().s().s().t(), new BVec4.Primitive(false, true, true, false));
    assertEquals(vec.t().s().t().s(), new BVec4.Primitive(false, true, false, true));
    assertEquals(vec.t().s().t().t(), new BVec4.Primitive(false, true, false, false));
    assertEquals(vec.t().t().s().s(), new BVec4.Primitive(false, false, true, true));
    assertEquals(vec.t().t().s().t(), new BVec4.Primitive(false, false, true, false));
    assertEquals(vec.t().t().t().s(), new BVec4.Primitive(false, false, false, true));
    assertEquals(vec.t().t().t().t(), new BVec4.Primitive(false, false, false, false));

  }

  @Test
  public void testAny() {
    assertTrue(vec.any());

    BVec2.Primitive other = new BVec2.Primitive(false, false);
    assertFalse(other.any());
  }

  @Test
  public void testAll() {
    assertFalse(vec.all());

    BVec2.Primitive other = new BVec2.Primitive(true, true);
    assertTrue(other.all());
  }

  @Test
  public void testNot() {
    BVec2.Primitive not = vec.not();

    assertFalse(not.getX());
    assertTrue(not.getY());
  }

  @Test
  @SuppressWarnings("all")
  public void testEquals() {
    BVec2.Primitive equal = new BVec2.Primitive(true, false);
    assertEquals(vec, equal);

    BVec2.Primitive unequal = new BVec2.Primitive(false, false);
    assertFalse(vec.equals(unequal));

    assertFalse(vec.equals(null));

    assertFalse(vec.equals("A string"));
  }

  @Test
  public void testHashCode() {
    BVec2.Primitive equal = new BVec2.Primitive(true, false);

    assertEquals(vec, equal);
    assertEquals(vec.hashCode(), equal.hashCode());
  }

  @Test
  public void testToString() {
    String toString = vec.toString();
    assertEquals(toString, "bvec2(true, false)");
  }
}
