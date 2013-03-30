package com.lfscheidegger.jfacet.shade.primitives;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Vec4}.
 */
public class Vec4Test {

  private Vec4 vec;

  @Before
  public void setUp() {
    vec = new Vec4(1, 2, 3, 4);
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
  public void testConstructors() {
    Vec4 other = new Vec4();

    assertTrue(other.getX() == 0);
    assertTrue(other.getY() == 0);
    assertTrue(other.getZ() == 0);
    assertTrue(other.getW() == 0);

    other = new Vec4(vec);

    assertTrue(other.getX() == vec.getX());
    assertTrue(other.getY() == vec.getY());
    assertTrue(other.getZ() == vec.getZ());
    assertTrue(other.getW() == vec.getW());

    other = new Vec4(new Vec2(1, 2), 3, 4);

    assertTrue(other.getX() == vec.getX());
    assertTrue(other.getY() == vec.getY());
    assertTrue(other.getZ() == vec.getZ());
    assertTrue(other.getW() == vec.getW());

    other = new Vec4(1, new Vec2(2, 3), 4);

    assertTrue(other.getX() == vec.getX());
    assertTrue(other.getY() == vec.getY());
    assertTrue(other.getZ() == vec.getZ());
    assertTrue(other.getW() == vec.getW());

    other = new Vec4(1, 2, new Vec2(3, 4));

    assertTrue(other.getX() == vec.getX());
    assertTrue(other.getY() == vec.getY());
    assertTrue(other.getZ() == vec.getZ());
    assertTrue(other.getW() == vec.getW());

    other = new Vec4(new Vec2(1, 2), new Vec2(3, 4));

    assertTrue(other.getX() == vec.getX());
    assertTrue(other.getY() == vec.getY());
    assertTrue(other.getZ() == vec.getZ());
    assertTrue(other.getW() == vec.getW());

    other = new Vec4(1, new Vec3(2, 3, 4));

    assertTrue(other.getX() == vec.getX());
    assertTrue(other.getY() == vec.getY());
    assertTrue(other.getZ() == vec.getZ());
    assertTrue(other.getW() == vec.getW());

    other = new Vec4(new Vec3(1, 2, 3), 4);

    assertTrue(other.getX() == vec.getX());
    assertTrue(other.getY() == vec.getY());
    assertTrue(other.getZ() == vec.getZ());
    assertTrue(other.getW() == vec.getW());
  }

  @Test
  public void testEquals() {
    Vec4 other = new Vec4(1, 2, 3, 4);
    Vec4 unequal = new Vec4(2, 3, 4, 5);

    assertFalse(vec.equals(null));
    assertFalse(vec.equals(""));
    assertFalse(vec.equals(unequal));

    assertTrue(vec.equals(other));
  }

  @Test
  public void testHashCode() {
    Vec4 other = new Vec4(1, 2, 3, 4);
    Vec4 unequal = new Vec4(2, 3, 4, 5);

    assertTrue(vec.hashCode() == other.hashCode());
    assertFalse(vec.hashCode() == unequal.hashCode());
  }

  @Test
  public void testToString() {
    Vec4 vec = new Vec4(1, 2, 3, 4);
    assertEquals(vec.toString(), "vec4(float(1.0), float(2.0), float(3.0), float(4.0))");
  }

  @Test
  public void testAdd() {
    Vec4 v1 = new Vec4(1, 2, 3, 4);
    Vec4 v2 = new Vec4(3, 4, 5, 6);

    assertEquals(v1.add(v2), new Vec4(4, 6, 8, 10));
    assertEquals(v2.add(v1), new Vec4(4, 6, 8, 10));
    assertEquals(v1.add(2), new Vec4(3, 4, 5, 6));
  }

  @Test
  public void testSub() {
    Vec4 v1 = new Vec4(1, 2, 3, 4);
    Vec4 v2 = new Vec4(3, 4, 5, 6);

    assertEquals(v1.sub(v2), new Vec4(-2, -2, -2, -2));
    assertEquals(v2.sub(v1), new Vec4(2, 2, 2, 2));
    assertEquals(v1.sub(2), new Vec4(-1, 0, 1, 2));
  }

  @Test
  public void testNeg() {
    Vec4 v = new Vec4(1, 2, 3, 4);
    assertEquals(v.neg(), new Vec4(-1, -2, -3, -4));
  }

  @Test
  public void testMul() {
    Vec4 v1 = new Vec4(1, 2, 3, 4);
    Vec4 v2 = new Vec4(3, 4, 5, 6);

    assertEquals(v1.mul(2), new Vec4(2, 4, 6, 8));
    assertEquals(v1.mul(v2), new Vec4(3, 8, 15, 24));
  }

  @Test
  public void testDiv() {
    Vec4 v1 = new Vec4(1, 2, 3, 4);
    Vec4 v2 = new Vec4(2, 5, 6, 8);

    assertEquals(v1.div(2), new Vec4(0.5f, 1, 1.5f, 2));
    assertEquals(v1.div(v2), new Vec4(0.5f, 0.4f, 0.5f, 0.5f));
  }

  @Test
  public void testDot() {
    Vec4 v1 = new Vec4(1, 2, 3, 4);
    Vec4 v2 = new Vec4(3, 4, 5, 6);

    assertTrue(v1.dot(v2) == 50);
    assertTrue(v1.dot(v2) == v2.dot(v1));
  }
}
