package com.lfscheidegger.jfacet.shade.expression.vector;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code BVector2.Primitive}
 */
public class BVector2PrimitiveTest {

  private final BVector2.Primitive vec;

  public BVector2PrimitiveTest() {
    vec = new BVector2.Primitive(true, false);
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
  public void testSwizzleXYZW() {
    assertTrue(vec.x().build() == true);
    assertTrue(vec.y().build() == false);

    assertEquals(vec.x().x().build(), new BVector2.Primitive(true, true));
    assertEquals(vec.x().y().build(), new BVector2.Primitive(true, false));
    assertEquals(vec.y().x().build(), new BVector2.Primitive(false, true));
    assertEquals(vec.y().y().build(), new BVector2.Primitive(false, false));

    assertEquals(vec.x().x().x().build(), new BVector3.Primitive(true, true, true));
    assertEquals(vec.x().x().y().build(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.x().y().x().build(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.x().y().y().build(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.y().x().x().build(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.y().x().y().build(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.y().y().x().build(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.y().y().y().build(), new BVector3.Primitive(false, false, false));

    assertEquals(vec.x().x().x().x().build(), new BVector4.Primitive(true, true, true, true));
    assertEquals(vec.x().x().x().y().build(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.x().x().y().x().build(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.x().x().y().y().build(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.x().y().x().x().build(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.x().y().x().y().build(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.x().y().y().x().build(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.x().y().y().y().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.y().x().x().x().build(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.y().x().x().y().build(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.y().x().y().x().build(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.y().x().y().y().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.y().y().x().x().build(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.y().y().x().y().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.y().y().y().x().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.y().y().y().y().build(), new BVector4.Primitive(false, false, false, false));
  }

  @Test
  public void testSwizzleRGBA() {
    assertTrue(vec.r().build() == true);
    assertTrue(vec.g().build() == false);

    assertEquals(vec.r().r().build(), new BVector2.Primitive(true, true));
    assertEquals(vec.r().g().build(), new BVector2.Primitive(true, false));
    assertEquals(vec.g().r().build(), new BVector2.Primitive(false, true));
    assertEquals(vec.g().g().build(), new BVector2.Primitive(false, false));

    assertEquals(vec.r().r().r().build(), new BVector3.Primitive(true, true, true));
    assertEquals(vec.r().r().g().build(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.r().g().r().build(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.r().g().g().build(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.g().r().r().build(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.g().r().g().build(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.g().g().r().build(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.g().g().g().build(), new BVector3.Primitive(false, false, false));

    assertEquals(vec.r().r().r().r().build(), new BVector4.Primitive(true, true, true, true));
    assertEquals(vec.r().r().r().g().build(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.r().r().g().r().build(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.r().r().g().g().build(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.r().g().r().r().build(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.r().g().r().g().build(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.r().g().g().r().build(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.r().g().g().g().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.g().r().r().r().build(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.g().r().r().g().build(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.g().r().g().r().build(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.g().r().g().g().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.g().g().r().r().build(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.g().g().r().g().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.g().g().g().r().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.g().g().g().g().build(), new BVector4.Primitive(false, false, false, false));
  }

  @Test
  public void testSwizzleSTPQ() {
    assertTrue(vec.s().build() == true);
    assertTrue(vec.t().build() == false);

    assertEquals(vec.s().s().build(), new BVector2.Primitive(true, true));
    assertEquals(vec.s().t().build(), new BVector2.Primitive(true, false));
    assertEquals(vec.t().s().build(), new BVector2.Primitive(false, true));
    assertEquals(vec.t().t().build(), new BVector2.Primitive(false, false));

    assertEquals(vec.s().s().s().build(), new BVector3.Primitive(true, true, true));
    assertEquals(vec.s().s().t().build(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.s().t().s().build(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.s().t().t().build(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.t().s().s().build(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.t().s().t().build(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.t().t().s().build(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.t().t().t().build(), new BVector3.Primitive(false, false, false));

    assertEquals(vec.s().s().s().s().build(), new BVector4.Primitive(true, true, true, true));
    assertEquals(vec.s().s().s().t().build(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.s().s().t().s().build(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.s().s().t().t().build(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.s().t().s().s().build(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.s().t().s().t().build(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.s().t().t().s().build(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.s().t().t().t().build(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.t().s().s().s().build(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.t().s().s().t().build(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.t().s().t().s().build(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.t().s().t().t().build(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.t().t().s().s().build(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.t().t().s().t().build(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.t().t().t().s().build(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.t().t().t().t().build(), new BVector4.Primitive(false, false, false, false));
  }

  @Test
  public void testAny() {
    assertTrue(vec.any());

    BVector2.Primitive other = new BVector2.Primitive(false, false);
    assertFalse(other.any());
  }

  @Test
  public void testAll() {
    assertFalse(vec.all());

    BVector2.Primitive other = new BVector2.Primitive(true, true);
    assertTrue(other.all());
  }

  @Test
  public void testNot() {
    BVector2.Primitive not = vec.not();

    assertFalse(not.getX());
    assertTrue(not.getY());
  }

  @Test
  @SuppressWarnings("all")
  public void testEquals() {
    BVector2.Primitive equal = new BVector2.Primitive(true, false);
    assertEquals(vec, equal);

    BVector2.Primitive unequal = new BVector2.Primitive(false, false);
    assertFalse(vec.equals(unequal));

    assertFalse(vec.equals(null));

    assertFalse(vec.equals("A string"));
  }

  @Test
  public void testHashCode() {
    BVector2.Primitive equal = new BVector2.Primitive(true, false);

    assertEquals(vec, equal);
    assertEquals(vec.hashCode(), equal.hashCode());
  }

  @Test
  public void testToString() {
    String toString = vec.toString();
    assertEquals(toString, "bvec2(true, false)");
  }
}
