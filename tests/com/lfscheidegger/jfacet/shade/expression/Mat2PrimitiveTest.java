// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.shade.expression;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Mat2.Primitive}
 */
public class Mat2PrimitiveTest {

  private final Mat2.Primitive mat;

  public Mat2PrimitiveTest() {
    mat = new Mat2.Primitive(
        new Vec2.Primitive(1, 2),
        new Vec2.Primitive(3, 4));
  }

  @Test
  public void testConstructors() {
    assertEquals(mat.getC0(), new Vec2.Primitive(1, 2));
    assertEquals(mat.getC1(), new Vec2.Primitive(3, 4));

    Mat2.Primitive identity = new Mat2.Primitive();
    assertEquals(identity.getC0(), new Vec2.Primitive(1, 0));
    assertEquals(identity.getC1(), new Vec2.Primitive(0, 1));
  }

  @Test
  public void testGetters() {
    assertEquals(mat.getC0(), new Vec2.Primitive(1, 2));
    assertEquals(mat.getC1(), new Vec2.Primitive(3, 4));

    assertEquals(mat.getC0(), mat.get(0));
    assertEquals(mat.getC1(), mat.get(1));
  }

  @Test
  public void testAdd() {
    Mat2.Primitive added = mat.plus(1);
    assertEquals(added, new Mat2.Primitive(
        new Vec2.Primitive(2, 3),
        new Vec2.Primitive(4, 5)));

    added = mat.plus(mat);
    assertEquals(added, new Mat2.Primitive(
        new Vec2.Primitive(2, 4),
        new Vec2.Primitive(6, 8)));
  }

  @Test
  public void testSub() {
    Mat2.Primitive subtracted = mat.minus(1);
    assertEquals(subtracted, new Mat2.Primitive(
        new Vec2.Primitive(0, 1),
        new Vec2.Primitive(2, 3)));

    subtracted = mat.minus(mat);
    assertEquals(subtracted, new Mat2.Primitive(
        new Vec2.Primitive(0, 0),
        new Vec2.Primitive(0, 0)));
  }

  @Test
  public void testMul() {
    Mat2.Primitive multiplied = mat.times(2);
    assertEquals(multiplied, new Mat2.Primitive(
        new Vec2.Primitive(2, 4),
        new Vec2.Primitive(6, 8)));

    multiplied = mat.times(mat);
    assertEquals(multiplied, new Mat2.Primitive(
        new Vec2.Primitive(7, 10),
        new Vec2.Primitive(15, 22)));

    Vec2.Primitive transformed = mat.times(new Vec2.Primitive(1, 2));
    assertEquals(transformed, new Vec2.Primitive(7, 10));
  }

  @Test
  public void testDiv() {
    Mat2.Primitive divided = mat.div(2);
    assertEquals(divided, new Mat2.Primitive(
        new Vec2.Primitive(0.5f, 1),
        new Vec2.Primitive(1.5f, 2)));

    divided = mat.div(mat);
    assertEquals(divided, new Mat2.Primitive(
        new Vec2.Primitive(1, 1),
        new Vec2.Primitive(1, 1)));
  }

  @Test
  public void testNeg() {
    Mat2.Primitive negated = mat.negative();
    assertEquals(negated, new Mat2.Primitive(
        new Vec2.Primitive(-1, -2),
        new Vec2.Primitive(-3, -4)));
  }

  @Test
  public void testDeterminant() {
    float determinant = mat.determinant();

    assertTrue(determinant == -2);
  }

  @Test
  public void testTranspose() {
    Mat2.Primitive transpose = mat.transpose();

    assertEquals(transpose, new Mat2.Primitive(
        new Vec2.Primitive(1, 3),
        new Vec2.Primitive(2, 4)));
  }

  @Test
  public void testInverse() {
    Mat2.Primitive inverse = mat.inverse();

    assertEquals(mat.times(inverse), new Mat2.Primitive());
    assertEquals(inverse.times(mat), new Mat2.Primitive());
  }

  @Test
  public void testMatrixCompMult() {
    Mat2.Primitive matrixCompMult = mat.matrixCompMult(mat);
    assertEquals(matrixCompMult, new Mat2.Primitive(
        new Vec2.Primitive(1, 4),
        new Vec2.Primitive(9, 16)));
  }

  @Test
  @SuppressWarnings("all")
  public void testEquals() {
    Mat2.Primitive equal = new Mat2.Primitive(
        new Vec2.Primitive(1, 2),
        new Vec2.Primitive(3, 4));
    assertEquals(mat, equal);

    Mat2.Primitive unequal = new Mat2.Primitive(
        new Vec2.Primitive(2, 3),
        new Vec2.Primitive(4, 5));
    assertFalse(mat.equals(unequal));

    assertFalse(mat.equals(null));

    assertFalse(mat.equals("aString"));
  }

  @Test
  public void testHashCode() {
    Mat2.Primitive equal = new Mat2.Primitive(
        new Vec2.Primitive(1, 2),
        new Vec2.Primitive(3, 4));

    assertEquals(mat, equal);
    assertEquals(mat.hashCode(), equal.hashCode());
  }

  @Test
  public void testToString() {
    String toString = mat.toString();

    assertEquals(toString, "mat2(vec2(1.0, 2.0), vec2(3.0, 4.0))");
  }
}
