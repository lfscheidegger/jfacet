package com.lfscheidegger.jfacet.shade.primitives.types;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Mat2}.
 */
public class TestMat3 {

  private Mat3 mat;

  @Before
  public void setUp() {
    mat = new Mat3(new Vec3(1, 2, 3), new Vec3(4, 5, 6), new Vec3(7, 8, 9));
  }

  @Test
  public void testGetC0() {
    assertEquals(mat.getC0(), new Vec3(1, 2, 3));
    assertEquals(mat.get(0), new Vec3(1, 2, 3));
  }

  @Test
  public void testGetC1() {
    assertEquals(mat.getC1(), new Vec3(4, 5, 6));
    assertEquals(mat.get(1), new Vec3(4, 5, 6));
  }

  @Test
  public void testGetC2() {
    assertEquals(mat.getC2(), new Vec3(7, 8, 9));
    assertEquals(mat.get(2), new Vec3(7, 8, 9));
  }

  @Test
  public void testSetC0() {
    mat.setC0(new Vec3(4, 5, 6));
    assertEquals(mat.getC0(), new Vec3(4, 5, 6));

    mat.set(new Vec3(5, 6, 7), 0);
    assertEquals(mat.getC0(), new Vec3(5, 6, 7));
  }

  @Test
  public void testSetC1() {
    mat.setC1(new Vec3(5, 6, 7));
    assertEquals(mat.getC1(), new Vec3(5, 6, 7));

    mat.set(new Vec3(6, 7, 8), 1);
    assertEquals(mat.getC1(), new Vec3(6, 7, 8));
  }

  @Test
  public void testSetC2() {
    mat.setC2(new Vec3(5, 6, 7));
    assertEquals(mat.getC2(), new Vec3(5, 6, 7));

    mat.set(new Vec3(6, 7, 8), 2);
    assertEquals(mat.getC2(), new Vec3(6, 7, 8));
  }

  @Test
  public void testConstructors() {
    Mat3 other = new Mat3();
    assertEquals(other.getC0(), new Vec3(1, 0, 0));
    assertEquals(other.getC1(), new Vec3(0, 1, 0));
    assertEquals(other.getC2(), new Vec3(0, 0, 1));

    other = new Mat3(mat);
    assertEquals(other.getC0(), new Vec3(1, 2, 3));
    assertEquals(other.getC1(), new Vec3(4, 5, 6));
    assertEquals(other.getC2(), new Vec3(7, 8, 9));
  }

  @Test
  public void testEquals() {
    Mat3 other = new Mat3(new Vec3(1, 2, 3), new Vec3(4, 5, 6), new Vec3(7, 8, 9));
    Mat3 unequal = new Mat3(new Vec3(2, 3, 4), new Vec3(5, 6, 7), new Vec3(8, 9, 0));

    assertFalse(mat.equals(null));
    assertFalse(mat.equals(""));
    assertFalse(mat.equals(unequal));

    assertTrue(mat.equals(other));
  }

  @Test
  public void testHashCode() {
    Mat3 other = new Mat3(new Vec3(1, 2, 3), new Vec3(4, 5, 6), new Vec3(7, 8, 9));
    Mat3 unequal = new Mat3(new Vec3(2, 3, 4), new Vec3(5, 6, 7), new Vec3(8, 9, 0));

    assertTrue(mat.hashCode() == other.hashCode());
    assertFalse(mat.hashCode() == unequal.hashCode());
  }

  @Test
  public void testToString() {
    Mat3 other = new Mat3(new Vec3(1, 2, 3), new Vec3(4, 5, 6), new Vec3(7, 8, 9));
    assertEquals(other.toString(), "Mat3{Vec3{1.0, 2.0, 3.0}, Vec3{4.0, 5.0, 6.0}, Vec3{7.0, 8.0, 9.0}}");
  }
}
