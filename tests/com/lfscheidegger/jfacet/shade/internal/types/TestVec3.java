package com.lfscheidegger.jfacet.shade.internal.types;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Vec3}.
 */
public class TestVec3 {

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
}
