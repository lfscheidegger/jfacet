package com.lfscheidegger.jfacet.shade.expression.vector;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code BVector3.Primitive}
 */
public class BVector4PrimitiveTest {

  private final BVector4.Primitive vec;

  public BVector4PrimitiveTest() {
    vec = new BVector4.Primitive(true, false, false, false);
  }

  @Test
  public void testConstructors() {
    assertTrue(vec.getX());
    assertFalse(vec.getY());
    assertFalse(vec.getZ());
    assertFalse(vec.getW());
  }

  @Test
  public void testGetters() {
    boolean x = vec.getX();
    boolean y = vec.getY();
    boolean z = vec.getZ();
    boolean w = vec.getW();

    assertTrue(x);
    assertFalse(y);
    assertFalse(z);
    assertFalse(w);

    assertTrue(vec.get(0) == vec.getX());
    assertTrue(vec.get(1) == vec.getY());
    assertTrue(vec.get(2) == vec.getZ());
    assertTrue(vec.get(3) == vec.getW());
  }

  @Test
  public void testAny() {
    assertTrue(vec.any());

    BVector4.Primitive other = new BVector4.Primitive(false, false, false, false);
    assertFalse(other.any());
  }

  @Test
  public void testAll() {
    assertFalse(vec.all());

    BVector4.Primitive other = new BVector4.Primitive(true, true, true, true);
    assertTrue(other.all());
  }

  @Test
  public void testNot() {
    BVector4.Primitive not = vec.not();

    assertFalse(not.getX());
    assertTrue(not.getY());
    assertTrue(not.getZ());
    assertTrue(not.getW());
  }

  @Test
  @SuppressWarnings("all")
  public void testEquals() {
    BVector4.Primitive equal = new BVector4.Primitive(true, false, false, false);
    assertEquals(vec, equal);

    BVector4.Primitive unequal = new BVector4.Primitive(false, false, false, false);
    assertFalse(vec.equals(unequal));

    assertFalse(vec.equals(null));

    assertFalse(vec.equals("A string"));
  }

  @Test
  public void testHashCode() {
    BVector4.Primitive equal = new BVector4.Primitive(true, false, false, false);

    assertEquals(vec, equal);
    assertEquals(vec.hashCode(), equal.hashCode());
  }

  @Test
  public void testToString() {
    String toString = vec.toString();
    assertEquals(toString, "bvec4(true, false, false, false)");
  }
}
