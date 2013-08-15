package com.lfscheidegger.jfacet.shade.expression;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Matrix2.Primitive}
 */
public class Matrix2PrimitiveTest {

  private final Matrix2.Primitive mat;

  public Matrix2PrimitiveTest() {
    mat = new Matrix2.Primitive(
        new Vector2.Primitive(1, 2),
        new Vector2.Primitive(3, 4));
  }

  @Test
  public void testConstructors() {
    assertEquals(mat.getC0(), new Vector2.Primitive(1, 2));
    assertEquals(mat.getC1(), new Vector2.Primitive(3, 4));

    Matrix2.Primitive identity = new Matrix2.Primitive();
    assertEquals(identity.getC0(), new Vector2.Primitive(1, 0));
    assertEquals(identity.getC1(), new Vector2.Primitive(0, 1));
  }

  @Test
  public void testGetters() {
    assertEquals(mat.getC0(), new Vector2.Primitive(1, 2));
    assertEquals(mat.getC1(), new Vector2.Primitive(3, 4));

    assertEquals(mat.getC0(), mat.get(0));
    assertEquals(mat.getC1(), mat.get(1));
  }

  @Test
  public void testAdd() {
    Matrix2.Primitive added = mat.add(1);
    assertEquals(added, new Matrix2.Primitive(
        new Vector2.Primitive(2, 3),
        new Vector2.Primitive(4, 5)));

    added = mat.add(mat);
    assertEquals(added, new Matrix2.Primitive(
        new Vector2.Primitive(2, 4),
        new Vector2.Primitive(6, 8)));
  }

  @Test
  public void testSub() {
    Matrix2.Primitive subtracted = mat.sub(1);
    assertEquals(subtracted, new Matrix2.Primitive(
        new Vector2.Primitive(0, 1),
        new Vector2.Primitive(2, 3)));

    subtracted = mat.sub(mat);
    assertEquals(subtracted, new Matrix2.Primitive(
        new Vector2.Primitive(0, 0),
        new Vector2.Primitive(0, 0)));
  }

  @Test
  public void testMul() {
    Matrix2.Primitive multiplied = mat.mul(2);
    assertEquals(multiplied, new Matrix2.Primitive(
        new Vector2.Primitive(2, 4),
        new Vector2.Primitive(6, 8)));

    multiplied = mat.mul(mat);
    assertEquals(multiplied, new Matrix2.Primitive(
        new Vector2.Primitive(7, 10),
        new Vector2.Primitive(15, 22)));
  }

  @Test
  public void testDiv() {
    Matrix2.Primitive divided = mat.div(2);
    assertEquals(divided, new Matrix2.Primitive(
        new Vector2.Primitive(0.5f, 1),
        new Vector2.Primitive(1.5f, 2)));

    divided = mat.div(mat);
    assertEquals(divided, new Matrix2.Primitive(
        new Vector2.Primitive(1, 1),
        new Vector2.Primitive(1, 1)));
  }

  @Test
  public void testNeg() {
    Matrix2.Primitive negated = mat.neg();
    assertEquals(negated, new Matrix2.Primitive(
        new Vector2.Primitive(-1, -2),
        new Vector2.Primitive(-3, -4)));
  }

  @Test
  public void testTransform() {
    Vector2.Primitive transformed = mat.transform(new Vector2.Primitive(1, 2));

    assertEquals(transformed, new Vector2.Primitive(7, 10));
  }

  @Test
  public void testDeterminant() {
    float determinant = mat.determinant();

    assertTrue(determinant == -2);
  }

  @Test
  public void testTranspose() {
    Matrix2.Primitive transpose = mat.transpose();

    assertEquals(transpose, new Matrix2.Primitive(
        new Vector2.Primitive(1, 3),
        new Vector2.Primitive(2, 4)));
  }

  @Test
  public void testInverse() {
    Matrix2.Primitive inverse = mat.inverse();

    assertEquals(mat.mul(inverse), new Matrix2.Primitive());
    assertEquals(inverse.mul(mat), new Matrix2.Primitive());
  }

  @Test
  public void testEquals() {
    Matrix2.Primitive equal = new Matrix2.Primitive(
        new Vector2.Primitive(1, 2),
        new Vector2.Primitive(3, 4));
    assertEquals(mat, equal);

    Matrix2.Primitive unequal = new Matrix2.Primitive(
        new Vector2.Primitive(2, 3),
        new Vector2.Primitive(4, 5));
    assertFalse(mat.equals(unequal));

    assertFalse(mat.equals(null));

    assertFalse(mat.equals("aString"));
  }

  @Test
  public void testHashCode() {
    Matrix2.Primitive equal = new Matrix2.Primitive(
        new Vector2.Primitive(1, 2),
        new Vector2.Primitive(3, 4));

    assertEquals(mat, equal);
    assertEquals(mat.hashCode(), equal.hashCode());
  }

  @Test
  public void testToString() {
    String toString = mat.toString();

    assertEquals(toString, "mat2(vec2(1.0, 2.0), vec2(3.0, 4.0))");
  }
}
