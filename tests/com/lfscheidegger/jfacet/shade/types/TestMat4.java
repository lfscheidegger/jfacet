package com.lfscheidegger.jfacet.shade.types;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Mat2}.
 */
public class TestMat4 {

  private Mat4 mat;

  @Before
  public void setUp() {
    mat = new Mat4(
        new Vec4(1, 2, 3, 4),
        new Vec4(5, 6, 7, 8),
        new Vec4(9, 10, 11, 12),
        new Vec4(13, 14, 15, 16));
  }

  @Test
  public void testGetC0() {
    assertEquals(mat.getC0(), new Vec4(1, 2, 3, 4));
    assertEquals(mat.get(0), new Vec4(1, 2, 3, 4));
  }

  @Test
  public void testGetC1() {
    assertEquals(mat.getC1(), new Vec4(5, 6, 7, 8));
    assertEquals(mat.get(1), new Vec4(5, 6, 7, 8));
  }

  @Test
  public void testGetC2() {
    assertEquals(mat.getC2(), new Vec4(9, 10, 11, 12));
    assertEquals(mat.get(2), new Vec4(9, 10, 11, 12));
  }


  @Test
  public void testGetC3() {
    assertEquals(mat.getC3(), new Vec4(13, 14, 15, 16));
    assertEquals(mat.get(3), new Vec4(13, 14, 15, 16));
  }

  @Test
  public void testSetC0() {
    mat.setC0(new Vec4(4, 5, 6, 7));
    assertEquals(mat.getC0(), new Vec4(4, 5, 6, 7));

    mat.set(new Vec4(5, 6, 7, 8), 0);
    assertEquals(mat.getC0(), new Vec4(5, 6, 7, 8));
  }

  @Test
  public void testSetC1() {
    mat.setC1(new Vec4(4, 5, 6, 7));
    assertEquals(mat.getC1(), new Vec4(4, 5, 6, 7));

    mat.set(new Vec4(3, 4, 5, 6), 1);
    assertEquals(mat.getC1(), new Vec4(3, 4, 5, 6));
  }

  @Test
  public void testSetC2() {
    mat.setC2(new Vec4(5, 6, 7, 8));
    assertEquals(mat.getC2(), new Vec4(5, 6, 7, 8));

    mat.set(new Vec4(6, 7, 8, 9), 2);
    assertEquals(mat.getC2(), new Vec4(6, 7, 8, 9));
  }

  @Test
  public void testSetC3() {
    mat.setC3(new Vec4(5, 6, 7, 8));
    assertEquals(mat.getC3(), new Vec4(5, 6, 7, 8));

    mat.set(new Vec4(6, 7, 8, 9), 3);
    assertEquals(mat.getC3(), new Vec4(6, 7, 8, 9));
  }

  @Test
  public void testConstructors() {
    Mat4 other = new Mat4();
    assertEquals(other.getC0(), new Vec4(1, 0, 0, 0));
    assertEquals(other.getC1(), new Vec4(0, 1, 0, 0));
    assertEquals(other.getC2(), new Vec4(0, 0, 1, 0));
    assertEquals(other.getC3(), new Vec4(0, 0, 0, 1));

    other = new Mat4(mat);
    assertEquals(other.getC0(), new Vec4(1, 2, 3, 4));
    assertEquals(other.getC1(), new Vec4(5, 6, 7, 8));
    assertEquals(other.getC2(), new Vec4(9, 10, 11, 12));
    assertEquals(other.getC3(), new Vec4(13, 14, 15, 16));
  }

  @Test
  public void testEquals() {
    Mat4 other = new Mat4(
        new Vec4(1, 2, 3, 4),
        new Vec4(5, 6, 7, 8),
        new Vec4(9, 10, 11, 12),
        new Vec4(13, 14, 15, 16));
    Mat4 unequal = new Mat4(
        new Vec4(2, 3, 4, 5),
        new Vec4(6, 7, 8, 9),
        new Vec4(10, 11, 12, 13),
        new Vec4(14, 15, 16, 17));

    assertFalse(mat.equals(null));
    assertFalse(mat.equals(""));
    assertFalse(mat.equals(unequal));

    assertTrue(mat.equals(other));
  }

  @Test
  public void testHashCode() {
    Mat4 other = new Mat4(
        new Vec4(1, 2, 3, 4),
        new Vec4(5, 6, 7, 8),
        new Vec4(9, 10, 11, 12),
        new Vec4(13, 14, 15, 16));
    Mat4 unequal = new Mat4(
        new Vec4(2, 3, 4, 5),
        new Vec4(6, 7, 8, 9),
        new Vec4(10, 11, 12, 13),
        new Vec4(14, 15, 16, 17));

    assertTrue(mat.hashCode() == other.hashCode());
    assertFalse(mat.hashCode() == unequal.hashCode());
  }
}
