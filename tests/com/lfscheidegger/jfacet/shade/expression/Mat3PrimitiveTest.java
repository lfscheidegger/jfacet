package com.lfscheidegger.jfacet.shade.expression;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Mat3.Primitive}
 */
public class Mat3PrimitiveTest {

  private final Mat3.Primitive mat;

  public Mat3PrimitiveTest() {
    mat = new Mat3.Primitive(
        new Vec3.Primitive(1, 2, 3),
        new Vec3.Primitive(4, 5, 6),
        new Vec3.Primitive(7, 8, 9));
  }

  @Test
  public void testConstructors() {
    assertEquals(mat.getC0(), new Vec3.Primitive(1, 2, 3));
    assertEquals(mat.getC1(), new Vec3.Primitive(4, 5, 6));
    assertEquals(mat.getC2(), new Vec3.Primitive(7, 8, 9));

    Mat3.Primitive identity = new Mat3.Primitive();
    assertEquals(identity.getC0(), new Vec3.Primitive(1, 0, 0));
    assertEquals(identity.getC1(), new Vec3.Primitive(0, 1, 0));
    assertEquals(identity.getC2(), new Vec3.Primitive(0, 0, 1));
  }

  @Test
  public void testGetters() {
    assertEquals(mat.getC0(), new Vec3.Primitive(1, 2, 3));
    assertEquals(mat.getC1(), new Vec3.Primitive(4, 5, 6));
    assertEquals(mat.getC2(), new Vec3.Primitive(7, 8, 9));

    assertEquals(mat.getC0(), mat.get(0));
    assertEquals(mat.getC1(), mat.get(1));
    assertEquals(mat.getC2(), mat.get(2));
  }

  @Test
  public void testAdd() {
    Mat3.Primitive added = mat.plus(1);
    assertEquals(added, new Mat3.Primitive(
        new Vec3.Primitive(2, 3, 4),
        new Vec3.Primitive(5, 6, 7),
        new Vec3.Primitive(8, 9, 10)));

    added = mat.plus(mat);
    assertEquals(added, new Mat3.Primitive(
        new Vec3.Primitive(2, 4, 6),
        new Vec3.Primitive(8, 10, 12),
        new Vec3.Primitive(14, 16, 18)));
  }

  @Test
  public void testSub() {
    Mat3.Primitive subtracted = mat.minus(1);
    assertEquals(subtracted, new Mat3.Primitive(
        new Vec3.Primitive(0, 1, 2),
        new Vec3.Primitive(3, 4, 5),
        new Vec3.Primitive(6, 7, 8)));

    subtracted = mat.minus(mat);
    assertEquals(subtracted, new Mat3.Primitive(
        new Vec3.Primitive(0, 0, 0),
        new Vec3.Primitive(0, 0, 0),
        new Vec3.Primitive(0, 0, 0)));
  }

  @Test
  public void testMul() {
    Mat3.Primitive multiplied = mat.times(2);
    assertEquals(multiplied, new Mat3.Primitive(
        new Vec3.Primitive(2, 4, 6),
        new Vec3.Primitive(8, 10, 12),
        new Vec3.Primitive(14, 16, 18)));

    multiplied = mat.times(mat);
    assertEquals(multiplied, new Mat3.Primitive(
        new Vec3.Primitive(30, 36, 42),
        new Vec3.Primitive(66, 81, 96),
        new Vec3.Primitive(102, 126, 150)));

    Vec3.Primitive transformed = mat.times(new Vec3.Primitive(1, 2, 3));
    assertEquals(transformed, new Vec3.Primitive(30, 36, 42));
  }

  @Test
  public void testDiv() {
    Mat3.Primitive divided = mat.div(2);
    assertEquals(divided, new Mat3.Primitive(
        new Vec3.Primitive(0.5f, 1, 1.5f),
        new Vec3.Primitive(2, 2.5f, 3),
        new Vec3.Primitive(3.5f, 4, 4.5f)));

    divided = mat.div(mat);
    assertEquals(divided, new Mat3.Primitive(
        new Vec3.Primitive(1, 1, 1),
        new Vec3.Primitive(1, 1, 1),
        new Vec3.Primitive(1, 1, 1)));
  }

  @Test
  public void testNeg() {
    Mat3.Primitive negated = mat.negative();
    assertEquals(negated, new Mat3.Primitive(
        new Vec3.Primitive(-1, -2, -3),
        new Vec3.Primitive(-4, -5, -6),
        new Vec3.Primitive(-7, -8, -9)));
  }

  @Test
  public void testDeterminant() {
    float determinant = mat.determinant();

    assertTrue(determinant == 0);
  }

  @Test
  public void testTranspose() {
    Mat3.Primitive transpose = mat.transpose();

    assertEquals(transpose, new Mat3.Primitive(
        new Vec3.Primitive(1, 4, 7),
        new Vec3.Primitive(2, 5, 8),
        new Vec3.Primitive(3, 6, 9)));
  }

  @Test
  public void testInverse() {
    Mat3.Primitive invertible = new Mat3.Primitive(
        new Vec3.Primitive(1, 2, 3),
        new Vec3.Primitive(4, 5, 6),
        new Vec3.Primitive(7, 8, 10));

    Mat3.Primitive inverse = invertible.inverse();

    assertEquals(invertible.times(inverse), new Mat3.Primitive());
    assertEquals(inverse.times(invertible), new Mat3.Primitive());
  }

  @Test
  public void testMatrixCompMult() {
    Mat3.Primitive matrixCompMult = mat.matrixCompMult(mat);
    assertEquals(matrixCompMult, new Mat3.Primitive(
        new Vec3.Primitive(1, 4, 9),
        new Vec3.Primitive(16, 25, 36),
        new Vec3.Primitive(49, 64, 81)));
  }

  @Test
  @SuppressWarnings("all")
  public void testEquals() {
    Mat3.Primitive equal = new Mat3.Primitive(
        new Vec3.Primitive(1, 2, 3),
        new Vec3.Primitive(4, 5, 6),
        new Vec3.Primitive(7, 8, 9));
    assertEquals(mat, equal);

    Mat3.Primitive unequal = new Mat3.Primitive(
        new Vec3.Primitive(2, 3, 4),
        new Vec3.Primitive(5, 6, 7),
        new Vec3.Primitive(8, 9, 10));
    assertFalse(mat.equals(unequal));

    assertFalse(mat.equals(null));

    assertFalse(mat.equals("aString"));
  }

  @Test
  public void testHashCode() {
    Mat3.Primitive equal = new Mat3.Primitive(
        new Vec3.Primitive(1, 2, 3),
        new Vec3.Primitive(4, 5, 6),
        new Vec3.Primitive(7, 8, 9));

    assertEquals(mat, equal);
    assertEquals(mat.hashCode(), equal.hashCode());
  }

  @Test
  public void testToString() {
    String toString = mat.toString();

    assertEquals(toString, "mat3(vec3(1.0, 2.0, 3.0), vec3(4.0, 5.0, 6.0), vec3(7.0, 8.0, 9.0))");
  }
}
