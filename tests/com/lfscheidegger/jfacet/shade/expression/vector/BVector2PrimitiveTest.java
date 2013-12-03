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
