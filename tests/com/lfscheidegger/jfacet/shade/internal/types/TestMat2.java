package com.lfscheidegger.jfacet.shade.internal.types;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Mat2}.
 */
public class TestMat2 {

  private Mat2 mat;

  @Before
  public void setUp() {
    mat = new Mat2(new Vec2(1, 2), new Vec2(3, 4));
  }

  @Test
  public void testGetC0() {
    assertEquals(mat.getC0(), new Vec2(1, 2));
    assertEquals(mat.get(0), new Vec2(1, 2));
  }

  @Test
  public void testGetC1() {
    assertEquals(mat.getC1(), new Vec2(3, 4));
    assertEquals(mat.get(1), new Vec2(3, 4));
  }

  @Test
  public void testSetC0() {
    mat.setC0(new Vec2(4, 5));
    assertEquals(mat.getC0(), new Vec2(4, 5));

    mat.set(new Vec2(6, 7), 0);
    assertEquals(mat.getC0(), new Vec2(6, 7));
  }

  @Test
  public void testSetC1() {
    mat.setC1(new Vec2(4, 5));
    assertEquals(mat.getC1(), new Vec2(4, 5));

    mat.set(new Vec2(6, 7), 1);
    assertEquals(mat.getC1(), new Vec2(6, 7));
  }

  @Test
  public void testConstructors() {
    Mat2 other = new Mat2();
    assertEquals(other.getC0(), new Vec2(1, 0));
    assertEquals(other.getC1(), new Vec2(0, 1));

    other = new Mat2(mat);
    assertEquals(other.getC0(), new Vec2(1, 2));
    assertEquals(other.getC1(), new Vec2(3, 4));
  }

  @Test
  public void testEquals() {
    Mat2 other = new Mat2(new Vec2(1, 2), new Vec2(3, 4));
    Mat2 unequal = new Mat2(new Vec2(2, 3), new Vec2(4, 5));

    assertFalse(mat.equals(null));
    assertFalse(mat.equals(""));
    assertFalse(mat.equals(unequal));

    assertTrue(mat.equals(other));
  }

  @Test
  public void testHashCode() {
    Mat2 other = new Mat2(new Vec2(1, 2), new Vec2(3, 4));
    Mat2 unequal = new Mat2(new Vec2(2, 3), new Vec2(4, 5));

    assertTrue(mat.hashCode() == other.hashCode());
    assertFalse(mat.hashCode() == unequal.hashCode());
  }
}
