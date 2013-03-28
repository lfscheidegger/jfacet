package com.lfscheidegger.jfacet.shade.primitives;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Mat3}.
 */
public class Mat3Test {

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

  @Test
  public void testAdd() {
    Mat3 mat = new Mat3();
    Mat3 other = new Mat3();
    float t = 3;

    assertEquals(mat.add(other), new Mat3(new Vec3(2, 0, 0), new Vec3(0, 2, 0), new Vec3(0, 0, 2)));
    assertEquals(mat.add(other), other.add(mat));
    assertEquals(mat.add(t), new Mat3(new Vec3(4, 3, 3), new Vec3(3, 4, 3), new Vec3(3, 3, 4)));
  }

  @Test
  public void testSub() {
    Mat3 mat = new Mat3(new Vec3(2, 0, 0), new Vec3(0, 2, 0), new Vec3(0, 0, 2));
    Mat3 other = new Mat3();
    float t = 1;

    assertEquals(mat.sub(other), new Mat3());
    assertEquals(mat.sub(t), new Mat3(new Vec3(1, -1, -1), new Vec3(-1, 1, -1), new Vec3(-1, -1, 1)));
  }

  @Test
  public void testNeg() {
    Mat3 mat = new Mat3(new Vec3(1, 2, 3), new Vec3(4, 5, 6), new Vec3(7, 8, 9));
    assertEquals(mat.neg(), new Mat3(new Vec3(-1, -2, -3), new Vec3(-4, -5, -6), new Vec3(-7, -8, -9)));
  }

  @Test
  public void testMul() {
    Mat3 mat = new Mat3();
    Mat3 otherMat = new Mat3(new Vec3(2, 0, 0), new Vec3(0, 2, 0), new Vec3(0, 0, 2));
    Mat3 thirdMat = new Mat3(new Vec3(1, 2, 3), new Vec3(4, 5, 6), new Vec3(7, 8, 9));
    Mat3 fourthMat = new Mat3(new Vec3(3, 4, 5), new Vec3(6, 7, 8), new Vec3(9, 10, 11));
    Vec3 vec = new Vec3(2, 3, 4);

    assertEquals(mat.mul(vec), vec);
    assertEquals(thirdMat.mul(vec), new Vec3(42, 51, 60));
    assertEquals(new Mat3().mul(new Mat3()), new Mat3());
    assertEquals(otherMat.mul(new Mat3()), otherMat);
    assertEquals(mat.mul(2), otherMat);

    assertEquals(thirdMat.mul(fourthMat), new Mat3(
        new Vec3(54, 66, 78),
        new Vec3(90, 111, 132),
        new Vec3(126, 156, 186)));
    assertEquals(fourthMat.mul(thirdMat), new Mat3(
        new Vec3(42, 48, 54),
        new Vec3(96, 111, 126),
        new Vec3(150, 174, 198)));

  }

  @Test
  public void testDiv() {
    Mat3 mat = new Mat3();
    Mat3 other = new Mat3(new Vec3(2, 1, 1), new Vec3(1, 2, 1), new Vec3(1, 1, 2));
    assertEquals(mat.div(2), new Mat3(new Vec3(0.5f, 0, 0), new Vec3(0, 0.5f, 0), new Vec3(0, 0, 0.5f)));
    assertEquals(mat.div(other), new Mat3(new Vec3(0.5f, 0, 0), new Vec3(0, 0.5f, 0), new Vec3(0, 0, 0.5f)));
  }
}
