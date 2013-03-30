package com.lfscheidegger.jfacet.shade.primitives;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Unit tests for {@code Vec2}.
 */
public class Vec2Test {

  private Vec2 vec;

  @Before
  public void setUp() {
    vec = new Vec2(1, 2);
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
  public void testConstructors() {
    Vec2 other = new Vec2();

    assertTrue(other.getX() == 0);
    assertTrue(other.getY() == 0);

    other = new Vec2(vec);

    assertTrue(other.getX() == vec.getX());
    assertTrue(other.getY() == vec.getY());
  }

  @Test
  public void testEquals() {
    Vec2 other = new Vec2(1, 2);
    Vec2 unequal = new Vec2(2, 3);

    assertFalse(vec.equals(null));
    assertFalse(vec.equals(""));
    assertFalse(vec.equals(unequal));

    assertTrue(vec.equals(other));
  }

  @Test
  public void testHashCode() {
    Vec2 other = new Vec2(1, 2);
    Vec2 unequal = new Vec2(2, 3);

    assertTrue(vec.hashCode() == other.hashCode());
    assertFalse(vec.hashCode() == unequal.hashCode());
  }

  @Test
  public void testToString() {
    Vec2 vec = new Vec2(1, 2);
    assertEquals(vec.toString(), "vec2(float(1.0), float(2.0))");
  }

  @Test
  public void testAdd() {
    Vec2 v1 = new Vec2(1, 2);
    Vec2 v2 = new Vec2(3, 4);

    assertEquals(v1.add(v2), new Vec2(4, 6));
    assertEquals(v2.add(v1), new Vec2(4, 6));
    assertEquals(v1.add(2), new Vec2(3, 4));
  }

  @Test
  public void testSub() {
    Vec2 v1 = new Vec2(1, 2);
    Vec2 v2 = new Vec2(3, 4);

    assertEquals(v1.sub(v2), new Vec2(-2, -2));
    assertEquals(v2.sub(v1), new Vec2(2, 2));
    assertEquals(v1.sub(2), new Vec2(-1, 0));
  }

  @Test
  public void testNeg() {
    Vec2 v = new Vec2(1, 2);
    assertEquals(v.neg(), new Vec2(-1, -2));
  }

  @Test
  public void testMul() {
    Vec2 v1 = new Vec2(1, 2);
    Vec2 v2 = new Vec2(3, 4);

    assertEquals(v1.mul(2), new Vec2(2, 4));
    assertEquals(v1.mul(v2), new Vec2(3, 8));
  }

  @Test
  public void testDiv() {
    Vec2 v1 = new Vec2(1, 2);
    Vec2 v2 = new Vec2(2, 5);

    assertEquals(v1.div(2), new Vec2(0.5f, 1));
    assertEquals(v1.div(v2), new Vec2(0.5f, 0.4f));
  }

  @Test
  public void testDot() {
    Vec2 v1 = new Vec2(1, 2);
    Vec2 v2 = new Vec2(3, 4);

    assertTrue(v1.dot(v2) == 11);
    assertTrue(v1.dot(v2) == v2.dot(v1));
  }
}
