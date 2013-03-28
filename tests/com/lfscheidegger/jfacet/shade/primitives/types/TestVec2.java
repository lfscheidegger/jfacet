package com.lfscheidegger.jfacet.shade.primitives.types;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Unit tests for {@code Vec2}.
 */
public class TestVec2 {

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
}
