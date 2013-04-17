package com.lfscheidegger.jfacet.shade.primitives;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for 2-dimensional {@code Matrix} objects.
 */
public class Mat2Test {

  private Matrix mat;

  @Before
  public void setUp() {
    mat = new Matrix(new Vector(1, 2), new Vector(3, 4));
  }

  @Test
  public void testGetC0() {
    assertEquals(mat.getC0(), new Vector(1, 2));
    assertEquals(mat.get(0), new Vector(1, 2));
  }

  @Test
  public void testGetC1() {
    assertEquals(mat.getC1(), new Vector(3, 4));
    assertEquals(mat.get(1), new Vector(3, 4));
  }

  @Test
  public void testSetC0() {
    mat.setC0(new Vector(4, 5));
    assertEquals(mat.getC0(), new Vector(4, 5));

    mat.set(new Vector(6, 7), 0);
    assertEquals(mat.getC0(), new Vector(6, 7));
  }

  @Test
  public void testSetC1() {
    mat.setC1(new Vector(4, 5));
    assertEquals(mat.getC1(), new Vector(4, 5));

    mat.set(new Vector(6, 7), 1);
    assertEquals(mat.getC1(), new Vector(6, 7));
  }

  @Test
  public void testConstructors() {
    Matrix other = new Matrix(2);
    assertEquals(other.getC0(), new Vector(1, 0));
    assertEquals(other.getC1(), new Vector(0, 1));
  }

  @Test
  public void testEquals() {
    Matrix other = new Matrix(new Vector(1, 2), new Vector(3, 4));
    Matrix unequal = new Matrix(new Vector(2, 3), new Vector(4, 5));

    assertFalse(mat.equals(null));
    assertFalse(mat.equals(""));
    assertFalse(mat.equals(unequal));

    assertTrue(mat.equals(other));
  }

  @Test
  public void testHashCode() {
    Matrix other = new Matrix(new Vector(1, 2), new Vector(3, 4));
    Matrix unequal = new Matrix(new Vector(2, 3), new Vector(4, 5));

    assertTrue(mat.hashCode() == other.hashCode());
    assertFalse(mat.hashCode() == unequal.hashCode());
  }

  @Test
  public void testToString() {
    Matrix other = new Matrix(new Vector(1, 2), new Vector(3, 4));
    assertEquals(other.toString(), "mat2(vec2(float(1.0), float(2.0)), vec2(float(3.0), float(4.0)))");
  }

  @Test
  public void testAdd() {
    Matrix mat = new Matrix(2);
    Matrix other = new Matrix(2);
    float t = 3;

    assertEquals(mat.add(other), new Matrix(new Vector(2, 0), new Vector(0, 2)));
    assertEquals(mat.add(other), other.add(mat));
    assertEquals(mat.add(t), new Matrix(new Vector(4, 3), new Vector(3, 4)));
  }

  @Test
  public void testSub() {
    Matrix mat = new Matrix(new Vector(2, 0), new Vector(0, 2));
    Matrix other = new Matrix(2);
    float t = 1;

    assertEquals(mat.sub(other), new Matrix(2));
    assertEquals(mat.sub(t), new Matrix(new Vector(1, -1), new Vector(-1, 1)));
  }

  @Test
  public void testNeg() {
    Matrix mat = new Matrix(new Vector(1, 2), new Vector(3, 4));
    assertEquals(mat.neg(), new Matrix(new Vector(-1, -2), new Vector(-3, -4)));
  }

  @Test
  public void testMul() {
    Matrix mat = new Matrix(2);
    Matrix otherMat = new Matrix(new Vector(2, 0), new Vector(0, 2));
    Matrix thirdMat = new Matrix(new Vector(1, 2), new Vector(3, 4));
    Matrix fourthMat = new Matrix(new Vector(3, 4), new Vector(5, 6));
    Vector vec = new Vector(2, 3);

    assertEquals(mat.mul(vec), vec);
    assertEquals(otherMat.mul(vec), new Vector(4, 6));
    assertEquals(new Matrix(2).mul(new Matrix(2)), new Matrix(2));
    assertEquals(otherMat.mul(new Matrix(2)), otherMat);
    assertEquals(mat.mul(2), otherMat);

    assertEquals(thirdMat.mul(fourthMat), new Matrix(new Vector(15, 22), new Vector(23, 34)));
    assertEquals(fourthMat.mul(thirdMat), new Matrix(new Vector(13, 16), new Vector(29, 36)));
  }

  @Test
  public void testDiv() {
    Matrix mat = new Matrix(2);
    Matrix other = new Matrix(new Vector(2, 1), new Vector(1, 2));
    assertEquals(mat.div(2), new Matrix(new Vector(0.5f, 0), new Vector(0, 0.5f)));
    assertEquals(mat.div(other), new Matrix(new Vector(0.5f, 0), new Vector(0, 0.5f)));
  }

  @Test
  public void testDeterminant() {
    Matrix mat = new Matrix(new Vector(1, 3), new Vector(2, 5));

    assertTrue(mat.determinant() == -1);
  }

  @Test
  public void testInverse() {
    Matrix mat = new Matrix(new Vector(1, 3), new Vector(2, 5));

    assertEquals(mat.inverse(), new Matrix(new Vector(-5, 3), new Vector(2, -1)));
    assertEquals(mat.mul(mat.inverse()), new Matrix(2));
  }

  @Test
  public void testTranspose() {
    Matrix mat = new Matrix(new Vector(1, 2), new Vector(3, 4));

    assertEquals(mat.transpose(), new Matrix(new Vector(1, 3), new Vector(2, 4)));
  }
}
