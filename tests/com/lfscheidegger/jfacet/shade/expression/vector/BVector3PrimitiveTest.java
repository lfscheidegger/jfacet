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
