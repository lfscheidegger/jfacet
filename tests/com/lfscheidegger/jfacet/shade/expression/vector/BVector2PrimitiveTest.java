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
    assertTrue(vec.x().get() == true);
    assertTrue(vec.y().get() == false);

    assertEquals(vec.x().x().get(), new BVector2.Primitive(true, true));
    assertEquals(vec.x().y().get(), new BVector2.Primitive(true, false));
    assertEquals(vec.y().x().get(), new BVector2.Primitive(false, true));
    assertEquals(vec.y().y().get(), new BVector2.Primitive(false, false));

    assertEquals(vec.x().x().x().get(), new BVector3.Primitive(true, true, true));
    assertEquals(vec.x().x().y().get(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.x().y().x().get(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.x().y().y().get(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.y().x().x().get(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.y().x().y().get(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.y().y().x().get(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.y().y().y().get(), new BVector3.Primitive(false, false, false));

    assertEquals(vec.x().x().x().x().get(), new BVector4.Primitive(true, true, true, true));
    assertEquals(vec.x().x().x().y().get(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.x().x().y().x().get(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.x().x().y().y().get(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.x().y().x().x().get(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.x().y().x().y().get(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.x().y().y().x().get(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.x().y().y().y().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.y().x().x().x().get(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.y().x().x().y().get(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.y().x().y().x().get(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.y().x().y().y().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.y().y().x().x().get(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.y().y().x().y().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.y().y().y().x().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.y().y().y().y().get(), new BVector4.Primitive(false, false, false, false));
  }

  @Test
  public void testSwizzleRGBA() {
    assertTrue(vec.r().get() == true);
    assertTrue(vec.g().get() == false);

    assertEquals(vec.r().r().get(), new BVector2.Primitive(true, true));
    assertEquals(vec.r().g().get(), new BVector2.Primitive(true, false));
    assertEquals(vec.g().r().get(), new BVector2.Primitive(false, true));
    assertEquals(vec.g().g().get(), new BVector2.Primitive(false, false));

    assertEquals(vec.r().r().r().get(), new BVector3.Primitive(true, true, true));
    assertEquals(vec.r().r().g().get(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.r().g().r().get(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.r().g().g().get(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.g().r().r().get(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.g().r().g().get(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.g().g().r().get(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.g().g().g().get(), new BVector3.Primitive(false, false, false));

    assertEquals(vec.r().r().r().r().get(), new BVector4.Primitive(true, true, true, true));
    assertEquals(vec.r().r().r().g().get(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.r().r().g().r().get(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.r().r().g().g().get(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.r().g().r().r().get(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.r().g().r().g().get(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.r().g().g().r().get(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.r().g().g().g().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.g().r().r().r().get(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.g().r().r().g().get(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.g().r().g().r().get(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.g().r().g().g().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.g().g().r().r().get(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.g().g().r().g().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.g().g().g().r().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.g().g().g().g().get(), new BVector4.Primitive(false, false, false, false));
  }

  @Test
  public void testSwizzleSTPQ() {
    assertTrue(vec.s().get() == true);
    assertTrue(vec.t().get() == false);

    assertEquals(vec.s().s().get(), new BVector2.Primitive(true, true));
    assertEquals(vec.s().t().get(), new BVector2.Primitive(true, false));
    assertEquals(vec.t().s().get(), new BVector2.Primitive(false, true));
    assertEquals(vec.t().t().get(), new BVector2.Primitive(false, false));

    assertEquals(vec.s().s().s().get(), new BVector3.Primitive(true, true, true));
    assertEquals(vec.s().s().t().get(), new BVector3.Primitive(true, true, false));
    assertEquals(vec.s().t().s().get(), new BVector3.Primitive(true, false, true));
    assertEquals(vec.s().t().t().get(), new BVector3.Primitive(true, false, false));
    assertEquals(vec.t().s().s().get(), new BVector3.Primitive(false, true, true));
    assertEquals(vec.t().s().t().get(), new BVector3.Primitive(false, true, false));
    assertEquals(vec.t().t().s().get(), new BVector3.Primitive(false, false, true));
    assertEquals(vec.t().t().t().get(), new BVector3.Primitive(false, false, false));

    assertEquals(vec.s().s().s().s().get(), new BVector4.Primitive(true, true, true, true));
    assertEquals(vec.s().s().s().t().get(), new BVector4.Primitive(true, true, true, false));
    assertEquals(vec.s().s().t().s().get(), new BVector4.Primitive(true, true, false, true));
    assertEquals(vec.s().s().t().t().get(), new BVector4.Primitive(true, true, false, false));
    assertEquals(vec.s().t().s().s().get(), new BVector4.Primitive(true, false, true, true));
    assertEquals(vec.s().t().s().t().get(), new BVector4.Primitive(true, false, true, false));
    assertEquals(vec.s().t().t().s().get(), new BVector4.Primitive(true, false, false, true));
    assertEquals(vec.s().t().t().t().get(), new BVector4.Primitive(true, false, false, false));
    assertEquals(vec.t().s().s().s().get(), new BVector4.Primitive(false, true, true, true));
    assertEquals(vec.t().s().s().t().get(), new BVector4.Primitive(false, true, true, false));
    assertEquals(vec.t().s().t().s().get(), new BVector4.Primitive(false, true, false, true));
    assertEquals(vec.t().s().t().t().get(), new BVector4.Primitive(false, true, false, false));
    assertEquals(vec.t().t().s().s().get(), new BVector4.Primitive(false, false, true, true));
    assertEquals(vec.t().t().s().t().get(), new BVector4.Primitive(false, false, true, false));
    assertEquals(vec.t().t().t().s().get(), new BVector4.Primitive(false, false, false, true));
    assertEquals(vec.t().t().t().t().get(), new BVector4.Primitive(false, false, false, false));
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
