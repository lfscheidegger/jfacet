package com.lfscheidegger.jfacet.shade.primitives.types;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Vec3}.
 */
public class Vec3Test {

  private Vec3 vec;

  @Before
  public void setUp() {
    vec = new Vec3(1, 2, 3);
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
  public void testConstructors() {
    Vec3 other = new Vec3();

    assertTrue(other.getX() == 0);
    assertTrue(other.getY() == 0);
    assertTrue(other.getZ() == 0);

    other = new Vec3(vec);

    assertTrue(other.getX() == vec.getX());
    assertTrue(other.getY() == vec.getY());
    assertTrue(other.getZ() == vec.getZ());

    other = new Vec3(new Vec2(1, 2), 3);

    assertTrue(other.getX() == vec.getX());
    assertTrue(other.getY() == vec.getY());
    assertTrue(other.getZ() == vec.getZ());

    other = new Vec3(1, new Vec2(2, 3));

    assertTrue(other.getX() == vec.getX());
    assertTrue(other.getY() == vec.getY());
    assertTrue(other.getZ() == vec.getZ());
  }

  @Test
  public void testEquals() {
    Vec3 other = new Vec3(1, 2, 3);
    Vec3 unequal = new Vec3(2, 3, 4);

    assertFalse(vec.equals(null));
    assertFalse(vec.equals(""));
    assertFalse(vec.equals(unequal));

    assertTrue(vec.equals(other));
  }

  @Test
  public void testHashCode() {
    Vec3 other = new Vec3(1, 2, 3);
    Vec3 unequal = new Vec3(2, 3, 4);

    assertTrue(vec.hashCode() == other.hashCode());
    assertFalse(vec.hashCode() == unequal.hashCode());
  }

  @Test
  public void testToString() {
    Vec3 vec = new Vec3(1, 2, 3);
    assertEquals(vec.toString(), "Vec3{1.0, 2.0, 3.0}");
  }

  @Test
  public void testAdd() {
    Vec3 v1 = new Vec3(1, 2, 3);
    Vec3 v2 = new Vec3(3, 4, 5);

    assertEquals(v1.add(v2), new Vec3(4, 6, 8));
    assertEquals(v2.add(v1), new Vec3(4, 6, 8));
    assertEquals(v1.add(2), new Vec3(3, 4, 5));
  }

  @Test
  public void testSub() {
    Vec3 v1 = new Vec3(1, 2, 3);
    Vec3 v2 = new Vec3(3, 4, 5);

    assertEquals(v1.sub(v2), new Vec3(-2, -2, -2));
    assertEquals(v2.sub(v1), new Vec3(2, 2, 2));
    assertEquals(v1.sub(2), new Vec3(-1, 0, 1));
  }

  @Test
  public void testNeg() {
    Vec3 v = new Vec3(1, 2, 3);
    assertEquals(v.neg(), new Vec3(-1, -2, -3));
  }

  @Test
  public void testMul() {
    Vec3 v1 = new Vec3(1, 2, 3);
    Vec3 v2 = new Vec3(3, 4, 5);

    assertEquals(v1.mul(2), new Vec3(2, 4, 6));
    assertEquals(v1.mul(v2), new Vec3(3, 8, 15));
  }

  @Test
  public void testDiv() {
    Vec3 v1 = new Vec3(1, 2, 3);
    Vec3 v2 = new Vec3(2, 5, 6);

    assertEquals(v1.div(2), new Vec3(0.5f, 1, 1.5f));
    assertEquals(v1.div(v2), new Vec3(0.5f, 0.4f, 0.5f));
  }

  @Test
  public void testDot() {
    Vec3 v1 = new Vec3(1, 2, 3);
    Vec3 v2 = new Vec3(3, 4, 5);

    assertTrue(v1.dot(v2) == 26);
    assertTrue(v1.dot(v2) == v2.dot(v1));
  }
}
