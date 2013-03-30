package com.lfscheidegger.jfacet.shade.primitives;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Mat4}.
 */
public class Mat4Test {

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

  @Test
  public void testToString() {
    Mat4 other = new Mat4(
        new Vec4(1, 2, 3, 4), new Vec4(5, 6, 7, 8), new Vec4(9, 10, 11, 12), new Vec4(13, 14, 15, 16));
    assertEquals(other.toString(),
        "mat4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)), vec4(float(5.0), float(6.0), float(7.0), float(8.0)), vec4(float(9.0), float(10.0), float(11.0), float(12.0)), vec4(float(13.0), float(14.0), float(15.0), float(16.0)))");
  }

  @Test
  public void testAdd() {
    Mat4 mat = new Mat4();
    Mat4 other = new Mat4();
    float t = 3;

    assertEquals(mat.add(other), new Mat4(new Vec4(2, 0, 0, 0), new Vec4(0, 2, 0, 0), new Vec4(0, 0, 2, 0), new Vec4(0, 0, 0, 2)));
    assertEquals(mat.add(other), other.add(mat));
    assertEquals(mat.add(t), new Mat4(new Vec4(4, 3, 3, 3), new Vec4(3, 4, 3, 3), new Vec4(3, 3, 4, 3), new Vec4(3, 3, 3, 4)));
  }

  @Test
  public void testSub() {
    Mat4 mat = new Mat4(new Vec4(2, 0, 0, 0), new Vec4(0, 2, 0, 0), new Vec4(0, 0, 2, 0), new Vec4(0, 0, 0, 2));
    Mat4 other = new Mat4();
    float t = 1;

    assertEquals(mat.sub(other), new Mat4());
    assertEquals(mat.sub(t), new Mat4(new Vec4(1, -1, -1, -1), new Vec4(-1, 1, -1, -1), new Vec4(-1, -1, 1, -1), new Vec4(-1, -1, -1, 1)));
  }

  @Test
  public void testNeg() {
    Mat4 mat = new Mat4(new Vec4(1, 2, 3, 4), new Vec4(5, 6, 7, 8), new Vec4(9, 10, 11, 12), new Vec4(13, 14, 15, 16));
    assertEquals(mat.neg(), new Mat4(
        new Vec4(-1, -2, -3, -4),
        new Vec4(-5, -6, -7, -8),
        new Vec4(-9, -10, -11, -12),
        new Vec4(-13, -14, -15, -16)));
  }

  @Test
  public void testMul() {
    Mat4 mat = new Mat4();
    Mat4 otherMat = new Mat4(
        new Vec4(2, 0, 0, 0),
        new Vec4(0, 2, 0, 0),
        new Vec4(0, 0, 2, 0),
        new Vec4(0, 0, 0, 2));
    Mat4 thirdMat = new Mat4(
        new Vec4(1, 2, 3, 4),
        new Vec4(5, 6, 7, 8),
        new Vec4(9, 10, 11, 12),
        new Vec4(13, 14, 15, 16));
    Mat4 fourthMat = new Mat4(
        new Vec4(3, 4, 5, 6),
        new Vec4(7, 8, 9, 10),
        new Vec4(11, 12, 13, 14),
        new Vec4(15, 16, 17, 18));
    Vec4 vec = new Vec4(1, 1, 1, 1);

    assertEquals(mat.mul(vec), vec);
    assertEquals(thirdMat.mul(vec), new Vec4(28, 32, 36, 40));
    assertEquals(new Mat4().mul(new Mat4()), new Mat4());
    assertEquals(otherMat.mul(new Mat4()), otherMat);
    assertEquals(mat.mul(2), otherMat);

    assertEquals(thirdMat.mul(fourthMat), new Mat4(
        new Vec4(146, 164, 182, 200),
        new Vec4(258, 292, 326, 360),
        new Vec4(370, 420, 470, 520),
        new Vec4(482, 548, 614, 680)));

    assertEquals(fourthMat.mul(thirdMat), new Mat4(
        new Vec4(110, 120, 130, 140),
        new Vec4(254, 280, 306, 332),
        new Vec4(398, 440, 482, 524),
        new Vec4(542, 600, 658, 716)));
  }

  @Test
  public void testDiv() {
    Mat4 mat = new Mat4();
    Mat4 other = new Mat4(new Vec4(2, 1, 1, 1), new Vec4(1, 2, 1, 1), new Vec4(1, 1, 2, 1), new Vec4(1, 1, 1, 2));
    assertEquals(mat.div(2), new Mat4(
        new Vec4(0.5f, 0, 0, 0),
        new Vec4(0, 0.5f, 0, 0),
        new Vec4(0, 0, 0.5f, 0),
        new Vec4(0, 0, 0, 0.5f)));
    assertEquals(mat.div(other), new Mat4(
        new Vec4(0.5f, 0, 0, 0),
        new Vec4(0, 0.5f, 0, 0),
        new Vec4(0, 0, 0.5f, 0),
        new Vec4(0, 0, 0, 0.5f)));
  }
}
