package com.lfscheidegger.jfacet.shade.primitives;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for 3-dimensional {@code Matrix} objects.
 */
public class Mat3Test {

  private Matrix mat;

  @Before
  public void setUp() {
    mat = new Matrix(new Vector(1, 2, 3), new Vector(4, 5, 6), new Vector(7, 8, 9));
  }

  @Test
  public void testGetC0() {
    assertEquals(mat.getC0(), new Vector(1, 2, 3));
    assertEquals(mat.get(0), new Vector(1, 2, 3));
  }

  @Test
  public void testGetC1() {
    assertEquals(mat.getC1(), new Vector(4, 5, 6));
    assertEquals(mat.get(1), new Vector(4, 5, 6));
  }

  @Test
  public void testGetC2() {
    assertEquals(mat.getC2(), new Vector(7, 8, 9));
    assertEquals(mat.get(2), new Vector(7, 8, 9));
  }

  @Test
  public void testSetC0() {
    mat.setC0(new Vector(4, 5, 6));
    assertEquals(mat.getC0(), new Vector(4, 5, 6));

    mat.set(new Vector(5, 6, 7), 0);
    assertEquals(mat.getC0(), new Vector(5, 6, 7));
  }

  @Test
  public void testSetC1() {
    mat.setC1(new Vector(5, 6, 7));
    assertEquals(mat.getC1(), new Vector(5, 6, 7));

    mat.set(new Vector(6, 7, 8), 1);
    assertEquals(mat.getC1(), new Vector(6, 7, 8));
  }

  @Test
  public void testSetC2() {
    mat.setC2(new Vector(5, 6, 7));
    assertEquals(mat.getC2(), new Vector(5, 6, 7));

    mat.set(new Vector(6, 7, 8), 2);
    assertEquals(mat.getC2(), new Vector(6, 7, 8));
  }

  @Test
  public void testConstructors() {
    Matrix other = new Matrix(3);
    assertEquals(other.getC0(), new Vector(1, 0, 0));
    assertEquals(other.getC1(), new Vector(0, 1, 0));
    assertEquals(other.getC2(), new Vector(0, 0, 1));
  }

  @Test
  public void testEquals() {
    Matrix other = new Matrix(new Vector(1, 2, 3), new Vector(4, 5, 6), new Vector(7, 8, 9));
    Matrix unequal = new Matrix(new Vector(2, 3, 4), new Vector(5, 6, 7), new Vector(8, 9, 0));

    assertFalse(mat.equals(null));
    assertFalse(mat.equals(""));
    assertFalse(mat.equals(unequal));

    assertTrue(mat.equals(other));
  }

  @Test
  public void testHashCode() {
    Matrix other = new Matrix(new Vector(1, 2, 3), new Vector(4, 5, 6), new Vector(7, 8, 9));
    Matrix unequal = new Matrix(new Vector(2, 3, 4), new Vector(5, 6, 7), new Vector(8, 9, 0));

    assertTrue(mat.hashCode() == other.hashCode());
    assertFalse(mat.hashCode() == unequal.hashCode());
  }

  @Test
  public void testToString() {
    Matrix other = new Matrix(new Vector(1, 2, 3), new Vector(4, 5, 6), new Vector(7, 8, 9));
    assertEquals(other.toString(), "mat3(vec3(float(1.0), float(2.0), float(3.0)), vec3(float(4.0), float(5.0), float(6.0)), vec3(float(7.0), float(8.0), float(9.0)))");
  }

  @Test
  public void testAdd() {
    Matrix mat = new Matrix(3);
    Matrix other = new Matrix(3);
    float t = 3;

    assertEquals(mat.add(other), new Matrix(new Vector(2, 0, 0), new Vector(0, 2, 0), new Vector(0, 0, 2)));
    assertEquals(mat.add(other), other.add(mat));
    assertEquals(mat.add(t), new Matrix(new Vector(4, 3, 3), new Vector(3, 4, 3), new Vector(3, 3, 4)));
  }

  @Test
  public void testSub() {
    Matrix mat = new Matrix(new Vector(2, 0, 0), new Vector(0, 2, 0), new Vector(0, 0, 2));
    Matrix other = new Matrix(3);
    float t = 1;

    assertEquals(mat.sub(other), new Matrix(3));
    assertEquals(mat.sub(t), new Matrix(new Vector(1, -1, -1), new Vector(-1, 1, -1), new Vector(-1, -1, 1)));
  }

  @Test
  public void testNeg() {
    Matrix mat = new Matrix(new Vector(1, 2, 3), new Vector(4, 5, 6), new Vector(7, 8, 9));
    assertEquals(mat.neg(), new Matrix(new Vector(-1, -2, -3), new Vector(-4, -5, -6), new Vector(-7, -8, -9)));
  }

  @Test
  public void testMul() {
    Matrix mat = new Matrix(3);
    Matrix otherMat = new Matrix(new Vector(2, 0, 0), new Vector(0, 2, 0), new Vector(0, 0, 2));
    Matrix thirdMat = new Matrix(new Vector(1, 2, 3), new Vector(4, 5, 6), new Vector(7, 8, 9));
    Matrix fourthMat = new Matrix(new Vector(3, 4, 5), new Vector(6, 7, 8), new Vector(9, 10, 11));
    Vector vec = new Vector(2, 3, 4);

    assertEquals(mat.mul(vec), vec);
    assertEquals(thirdMat.mul(vec), new Vector(42, 51, 60));
    assertEquals(new Matrix(3).mul(new Matrix(3)), new Matrix(3));
    assertEquals(otherMat.mul(new Matrix(3)), otherMat);
    assertEquals(mat.mul(2), otherMat);

    assertEquals(thirdMat.mul(fourthMat), new Matrix(
        new Vector(54, 66, 78),
        new Vector(90, 111, 132),
        new Vector(126, 156, 186)));
    assertEquals(fourthMat.mul(thirdMat), new Matrix(
        new Vector(42, 48, 54),
        new Vector(96, 111, 126),
        new Vector(150, 174, 198)));

  }

  @Test
  public void testDiv() {
    Matrix mat = new Matrix(3);
    Matrix other = new Matrix(new Vector(2, 1, 1), new Vector(1, 2, 1), new Vector(1, 1, 2));
    assertEquals(mat.div(2), new Matrix(new Vector(0.5f, 0, 0), new Vector(0, 0.5f, 0), new Vector(0, 0, 0.5f)));
    assertEquals(mat.div(other), new Matrix(new Vector(0.5f, 0, 0), new Vector(0, 0.5f, 0), new Vector(0, 0, 0.5f)));
  }

  @Test
  public void testDeterminant() {
    Matrix mat = new Matrix(new Vector(1, 4, 7), new Vector(2, 5, 8), new Vector(3, 6, 10));
    assertTrue(mat.determinant() == -3);
  }

  @Test
  public void testInverse() {
    Matrix mat = new Matrix(new Vector(1, 4, 7), new Vector(2, 5, 8), new Vector(3, 6, 10));
    Matrix inverse = new Matrix(
        new Vector(-0.66666667f, -0.66666667f, 1), new Vector(-1.33333333334f, 3.666666666667f, -2), new Vector(1, -2, 1));
    assertEquals(mat.inverse(), inverse);
    assertEquals(mat.mul(mat.inverse()), new Matrix(3));
  }

  @Test
  public void testTranspose() {
    Matrix mat = new Matrix(new Vector(1, 2, 3), new Vector(4, 5, 6), new Vector(7, 8, 9));

    assertEquals(mat.transpose(), new Matrix(
        new Vector(1, 4, 7),
        new Vector(2, 5, 8),
        new Vector(3, 6, 9)));
  }
}
