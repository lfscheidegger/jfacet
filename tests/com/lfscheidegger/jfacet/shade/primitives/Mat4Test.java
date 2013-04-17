package com.lfscheidegger.jfacet.shade.primitives;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for 4-dimensional {@code Matrix} object.
 */
public class Mat4Test {

  private Matrix mat;

  @Before
  public void setUp() {
    mat = new Matrix(
        new Vector(1, 2, 3, 4),
        new Vector(5, 6, 7, 8),
        new Vector(9, 10, 11, 12),
        new Vector(13, 14, 15, 16));
  }

  @Test
  public void testGetC0() {
    assertEquals(mat.getC0(), new Vector(1, 2, 3, 4));
    assertEquals(mat.get(0), new Vector(1, 2, 3, 4));
  }

  @Test
  public void testGetC1() {
    assertEquals(mat.getC1(), new Vector(5, 6, 7, 8));
    assertEquals(mat.get(1), new Vector(5, 6, 7, 8));
  }

  @Test
  public void testGetC2() {
    assertEquals(mat.getC2(), new Vector(9, 10, 11, 12));
    assertEquals(mat.get(2), new Vector(9, 10, 11, 12));
  }


  @Test
  public void testGetC3() {
    assertEquals(mat.getC3(), new Vector(13, 14, 15, 16));
    assertEquals(mat.get(3), new Vector(13, 14, 15, 16));
  }

  @Test
  public void testSetC0() {
    mat.setC0(new Vector(4, 5, 6, 7));
    assertEquals(mat.getC0(), new Vector(4, 5, 6, 7));

    mat.set(new Vector(5, 6, 7, 8), 0);
    assertEquals(mat.getC0(), new Vector(5, 6, 7, 8));
  }

  @Test
  public void testSetC1() {
    mat.setC1(new Vector(4, 5, 6, 7));
    assertEquals(mat.getC1(), new Vector(4, 5, 6, 7));

    mat.set(new Vector(3, 4, 5, 6), 1);
    assertEquals(mat.getC1(), new Vector(3, 4, 5, 6));
  }

  @Test
  public void testSetC2() {
    mat.setC2(new Vector(5, 6, 7, 8));
    assertEquals(mat.getC2(), new Vector(5, 6, 7, 8));

    mat.set(new Vector(6, 7, 8, 9), 2);
    assertEquals(mat.getC2(), new Vector(6, 7, 8, 9));
  }

  @Test
  public void testSetC3() {
    mat.setC3(new Vector(5, 6, 7, 8));
    assertEquals(mat.getC3(), new Vector(5, 6, 7, 8));

    mat.set(new Vector(6, 7, 8, 9), 3);
    assertEquals(mat.getC3(), new Vector(6, 7, 8, 9));
  }

  @Test
  public void testConstructors() {
    Matrix other = new Matrix(4);
    assertEquals(other.getC0(), new Vector(1, 0, 0, 0));
    assertEquals(other.getC1(), new Vector(0, 1, 0, 0));
    assertEquals(other.getC2(), new Vector(0, 0, 1, 0));
    assertEquals(other.getC3(), new Vector(0, 0, 0, 1));
  }

  @Test
  public void testEquals() {
    Matrix other = new Matrix(
        new Vector(1, 2, 3, 4),
        new Vector(5, 6, 7, 8),
        new Vector(9, 10, 11, 12),
        new Vector(13, 14, 15, 16));
    Matrix unequal = new Matrix(
        new Vector(2, 3, 4, 5),
        new Vector(6, 7, 8, 9),
        new Vector(10, 11, 12, 13),
        new Vector(14, 15, 16, 17));

    assertFalse(mat.equals(null));
    assertFalse(mat.equals(""));
    assertFalse(mat.equals(unequal));

    assertTrue(mat.equals(other));
  }

  @Test
  public void testHashCode() {
    Matrix other = new Matrix(
        new Vector(1, 2, 3, 4),
        new Vector(5, 6, 7, 8),
        new Vector(9, 10, 11, 12),
        new Vector(13, 14, 15, 16));
    Matrix unequal = new Matrix(
        new Vector(2, 3, 4, 5),
        new Vector(6, 7, 8, 9),
        new Vector(10, 11, 12, 13),
        new Vector(14, 15, 16, 17));

    assertTrue(mat.hashCode() == other.hashCode());
    assertFalse(mat.hashCode() == unequal.hashCode());
  }

  @Test
  public void testToString() {
    Matrix other = new Matrix(
        new Vector(1, 2, 3, 4), new Vector(5, 6, 7, 8), new Vector(9, 10, 11, 12), new Vector(13, 14, 15, 16));
    assertEquals(other.toString(),
        "mat4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)), vec4(float(5.0), float(6.0), float(7.0), float(8.0)), vec4(float(9.0), float(10.0), float(11.0), float(12.0)), vec4(float(13.0), float(14.0), float(15.0), float(16.0)))");
  }

  @Test
  public void testAdd() {
    Matrix mat = new Matrix(4);
    Matrix other = new Matrix(4);
    float t = 3;

    assertEquals(mat.add(other), new Matrix(new Vector(2, 0, 0, 0), new Vector(0, 2, 0, 0), new Vector(0, 0, 2, 0), new Vector(0, 0, 0, 2)));
    assertEquals(mat.add(other), other.add(mat));
    assertEquals(mat.add(t), new Matrix(new Vector(4, 3, 3, 3), new Vector(3, 4, 3, 3), new Vector(3, 3, 4, 3), new Vector(3, 3, 3, 4)));
  }

  @Test
  public void testSub() {
    Matrix mat = new Matrix(new Vector(2, 0, 0, 0), new Vector(0, 2, 0, 0), new Vector(0, 0, 2, 0), new Vector(0, 0, 0, 2));
    Matrix other = new Matrix(4);
    float t = 1;

    assertEquals(mat.sub(other), new Matrix(4));
    assertEquals(mat.sub(t), new Matrix(new Vector(1, -1, -1, -1), new Vector(-1, 1, -1, -1), new Vector(-1, -1, 1, -1), new Vector(-1, -1, -1, 1)));
  }

  @Test
  public void testNeg() {
    Matrix mat = new Matrix(new Vector(1, 2, 3, 4), new Vector(5, 6, 7, 8), new Vector(9, 10, 11, 12), new Vector(13, 14, 15, 16));
    assertEquals(mat.neg(), new Matrix(
        new Vector(-1, -2, -3, -4),
        new Vector(-5, -6, -7, -8),
        new Vector(-9, -10, -11, -12),
        new Vector(-13, -14, -15, -16)));
  }

  @Test
  public void testMul() {
    Matrix mat = new Matrix(4);
    Matrix otherMat = new Matrix(
        new Vector(2, 0, 0, 0),
        new Vector(0, 2, 0, 0),
        new Vector(0, 0, 2, 0),
        new Vector(0, 0, 0, 2));
    Matrix thirdMat = new Matrix(
        new Vector(1, 2, 3, 4),
        new Vector(5, 6, 7, 8),
        new Vector(9, 10, 11, 12),
        new Vector(13, 14, 15, 16));
    Matrix fourthMat = new Matrix(
        new Vector(3, 4, 5, 6),
        new Vector(7, 8, 9, 10),
        new Vector(11, 12, 13, 14),
        new Vector(15, 16, 17, 18));
    Vector vec = new Vector(1, 1, 1, 1);

    assertEquals(mat.mul(vec), vec);
    assertEquals(thirdMat.mul(vec), new Vector(28, 32, 36, 40));
    assertEquals(new Matrix(4).mul(new Matrix(4)), new Matrix(4));
    assertEquals(otherMat.mul(new Matrix(4)), otherMat);
    assertEquals(mat.mul(2), otherMat);

    assertEquals(thirdMat.mul(fourthMat), new Matrix(
        new Vector(146, 164, 182, 200),
        new Vector(258, 292, 326, 360),
        new Vector(370, 420, 470, 520),
        new Vector(482, 548, 614, 680)));

    assertEquals(fourthMat.mul(thirdMat), new Matrix(
        new Vector(110, 120, 130, 140),
        new Vector(254, 280, 306, 332),
        new Vector(398, 440, 482, 524),
        new Vector(542, 600, 658, 716)));
  }

  @Test
  public void testDiv() {
    Matrix mat = new Matrix(4);
    Matrix other = new Matrix(new Vector(2, 1, 1, 1), new Vector(1, 2, 1, 1), new Vector(1, 1, 2, 1), new Vector(1, 1, 1, 2));
    assertEquals(mat.div(2), new Matrix(
        new Vector(0.5f, 0, 0, 0),
        new Vector(0, 0.5f, 0, 0),
        new Vector(0, 0, 0.5f, 0),
        new Vector(0, 0, 0, 0.5f)));
    assertEquals(mat.div(other), new Matrix(
        new Vector(0.5f, 0, 0, 0),
        new Vector(0, 0.5f, 0, 0),
        new Vector(0, 0, 0.5f, 0),
        new Vector(0, 0, 0, 0.5f)));
  }

  @Test
  public void testDeterminant() {
    Matrix mat = new Matrix(
        new Vector(1, 2, 3, 4),
        new Vector(2, 3, 2, 5),
        new Vector(1, 4, 5, 9),
        new Vector(4, 3, 6, 8));
    assertTrue(mat.determinant() == 28);
  }

  @Test
  public void testInverse() {
    Matrix mat = new Matrix(
        new Vector(1, 2, 3, 4),
        new Vector(2, 3, 2, 5),
        new Vector(1, 4, 5, 9),
        new Vector(4, 3, 6, 8));

    Matrix inverse = new Matrix(
        new Vector(     -0.25f,        0.25f, -0.25f,         0.25f),
        new Vector(       1.5f,         0.5f,  -0.5f,         -0.5f),
        new Vector( 1.0357143f, -0.32142857f, -0.25f, -0.035714287f),
        new Vector(-1.2142857f, -0.071428575f,  0.5f,   0.21428572f));
    assertEquals(mat.inverse(), inverse);
  }

  @Test
  public void testTranspose() {
    Matrix mat = new Matrix(
        new Vector( 1,  2,  3,  4),
        new Vector( 5,  6,  7,  8),
        new Vector( 9, 10, 11, 12),
        new Vector(13, 14, 15, 16));

    assertEquals(mat.transpose(), new Matrix(
        new Vector(1, 5, 9, 13),
        new Vector(2, 6, 10, 14),
        new Vector(3, 7, 11, 15),
        new Vector(4, 8, 12, 16)));
  }
}
