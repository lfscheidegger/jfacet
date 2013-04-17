package com.lfscheidegger.jfacet.shade.primitives;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for 4-dimensional {@code Vector} objects.
 */
public class Vec4Test {

  private Vector vec;

  @Before
  public void setUp() {
    vec = new Vector(1, 2, 3, 4);
  }

  @Test
  public void testGetX() {
    assertTrue(vec.getX() == 1);
    assertTrue(vec.get(0) == 1);
  }

  @Test
  public void testGetY() {
    assertTrue(vec.getY() == 2);
    assertTrue(vec.get(1) == 2);
  }

  @Test
  public void testGetZ() {
    assertTrue(vec.getZ() == 3);
    assertTrue(vec.get(2) == 3);
  }

  @Test
  public void testGetW() {
    assertTrue(vec.getW() == 4);
    assertTrue(vec.get(3) == 4);
  }

  @Test
  public void testSetX() {
    vec.setX(3);
    assertTrue(vec.getX() == 3);
    assertTrue(vec.get(0) == 3);

    vec.set(3, 0);
    assertTrue(vec.getX() == 3);
    assertTrue(vec.get(0) == 3);
  }

  @Test
  public void testSetY() {
    vec.setY(3);
    assertTrue(vec.getY() == 3);
    assertTrue(vec.get(1) == 3);

    vec.set(3, 1);
    assertTrue(vec.getY() == 3);
    assertTrue(vec.get(1) == 3);
  }

  @Test
  public void testSetZ() {
    vec.setZ(4);
    assertTrue(vec.getZ() == 4);
    assertTrue(vec.get(2) == 4);

    vec.set(4, 2);
    assertTrue(vec.getZ() == 4);
    assertTrue(vec.get(2) == 4);
  }

  @Test
  public void testSetW() {
    vec.setW(5);
    assertTrue(vec.getW() == 5);
    assertTrue(vec.get(3) == 5);

    vec.set(5, 3);
    assertTrue(vec.getW() == 5);
    assertTrue(vec.get(3) == 5);
  }

  @Test
  public void testEquals() {
    Vector other = new Vector(1, 2, 3, 4);
    Vector unequal = new Vector(2, 3, 4, 5);

    assertFalse(vec.equals(null));
    assertFalse(vec.equals(""));
    assertFalse(vec.equals(unequal));

    assertTrue(vec.equals(other));
  }

  @Test
  public void testHashCode() {
    Vector other = new Vector(1, 2, 3, 4);
    Vector unequal = new Vector(2, 3, 4, 5);

    assertTrue(vec.hashCode() == other.hashCode());
    assertFalse(vec.hashCode() == unequal.hashCode());
  }

  @Test
  public void testToString() {
    Vector vec = new Vector(1, 2, 3, 4);
    assertEquals(vec.toString(), "vec4(float(1.0), float(2.0), float(3.0), float(4.0))");
  }

  @Test
  public void testAdd() {
    Vector v1 = new Vector(1, 2, 3, 4);
    Vector v2 = new Vector(3, 4, 5, 6);

    assertEquals(v1.add(v2), new Vector(4, 6, 8, 10));
    assertEquals(v2.add(v1), new Vector(4, 6, 8, 10));
    assertEquals(v1.add(2), new Vector(3, 4, 5, 6));
  }

  @Test
  public void testSub() {
    Vector v1 = new Vector(1, 2, 3, 4);
    Vector v2 = new Vector(3, 4, 5, 6);

    assertEquals(v1.sub(v2), new Vector(-2, -2, -2, -2));
    assertEquals(v2.sub(v1), new Vector(2, 2, 2, 2));
    assertEquals(v1.sub(2), new Vector(-1, 0, 1, 2));
  }

  @Test
  public void testNeg() {
    Vector v = new Vector(1, 2, 3, 4);
    assertEquals(v.neg(), new Vector(-1, -2, -3, -4));
  }

  @Test
  public void testMul() {
    Vector v1 = new Vector(1, 2, 3, 4);
    Vector v2 = new Vector(3, 4, 5, 6);

    assertEquals(v1.mul(2), new Vector(2, 4, 6, 8));
    assertEquals(v1.mul(v2), new Vector(3, 8, 15, 24));
  }

  @Test
  public void testDiv() {
    Vector v1 = new Vector(1, 2, 3, 4);
    Vector v2 = new Vector(2, 5, 6, 8);

    assertEquals(v1.div(2), new Vector(0.5f, 1, 1.5f, 2));
    assertEquals(v1.div(v2), new Vector(0.5f, 0.4f, 0.5f, 0.5f));
  }

  @Test
  public void testDot() {
    Vector v1 = new Vector(1, 2, 3, 4);
    Vector v2 = new Vector(3, 4, 5, 6);

    assertTrue(v1.dot(v2) == 50);
    assertTrue(v1.dot(v2) == v2.dot(v1));
  }
}
