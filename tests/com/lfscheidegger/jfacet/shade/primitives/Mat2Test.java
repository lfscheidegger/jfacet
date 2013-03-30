package com.lfscheidegger.jfacet.shade.primitives;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Mat2}.
 */
public class Mat2Test {

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

  @Test
  public void testToString() {
    Mat2 other = new Mat2(new Vec2(1, 2), new Vec2(3, 4));
    assertEquals(other.toString(), "mat2(vec2(float(1.0), float(2.0)), vec2(float(3.0), float(4.0)))");
  }

  @Test
  public void testAdd() {
    Mat2 mat = new Mat2();
    Mat2 other = new Mat2();
    float t = 3;

    assertEquals(mat.add(other), new Mat2(new Vec2(2, 0), new Vec2(0, 2)));
    assertEquals(mat.add(other), other.add(mat));
    assertEquals(mat.add(t), new Mat2(new Vec2(4, 3), new Vec2(3, 4)));
  }

  @Test
  public void testSub() {
    Mat2 mat = new Mat2(new Vec2(2, 0), new Vec2(0, 2));
    Mat2 other = new Mat2();
    float t = 1;

    assertEquals(mat.sub(other), new Mat2());
    assertEquals(mat.sub(t), new Mat2(new Vec2(1, -1), new Vec2(-1, 1)));
  }

  @Test
  public void testNeg() {
    Mat2 mat = new Mat2(new Vec2(1, 2), new Vec2(3, 4));
    assertEquals(mat.neg(), new Mat2(new Vec2(-1, -2), new Vec2(-3, -4)));
  }

  @Test
  public void testMul() {
    Mat2 mat = new Mat2();
    Mat2 otherMat = new Mat2(new Vec2(2, 0), new Vec2(0, 2));
    Mat2 thirdMat = new Mat2(new Vec2(1, 2), new Vec2(3, 4));
    Mat2 fourthMat = new Mat2(new Vec2(3, 4), new Vec2(5, 6));
    Vec2 vec = new Vec2(2, 3);

    assertEquals(mat.mul(vec), vec);
    assertEquals(otherMat.mul(vec), new Vec2(4, 6));
    assertEquals(new Mat2().mul(new Mat2()), new Mat2());
    assertEquals(otherMat.mul(new Mat2()), otherMat);
    assertEquals(mat.mul(2), otherMat);

    assertEquals(thirdMat.mul(fourthMat), new Mat2(new Vec2(15, 22), new Vec2(23, 34)));
    assertEquals(fourthMat.mul(thirdMat), new Mat2(new Vec2(13, 16), new Vec2(29, 36)));
  }

  @Test
  public void testDiv() {
    Mat2 mat = new Mat2();
    Mat2 other = new Mat2(new Vec2(2, 1), new Vec2(1, 2));
    assertEquals(mat.div(2), new Mat2(new Vec2(0.5f, 0), new Vec2(0, 0.5f)));
    assertEquals(mat.div(other), new Mat2(new Vec2(0.5f, 0), new Vec2(0, 0.5f)));
  }
}
