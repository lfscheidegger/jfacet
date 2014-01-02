package com.lfscheidegger.jfacet.shade.expression;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Mat4.Primitive}
 */
public class Mat4PrimitiveTest {

  private final Mat4.Primitive mat;

  public Mat4PrimitiveTest() {
    mat = new Mat4.Primitive(
        new Vec4.Primitive(1, 2, 3, 4),
        new Vec4.Primitive(4, 5, 6, 7),
        new Vec4.Primitive(7, 8, 9, 10),
        new Vec4.Primitive(10, 11, 12, 13));
  }

  @Test
  public void testConstructors() {
    assertEquals(mat.getC0(), new Vec4.Primitive(1, 2, 3, 4));
    assertEquals(mat.getC1(), new Vec4.Primitive(4, 5, 6, 7));
    assertEquals(mat.getC2(), new Vec4.Primitive(7, 8, 9, 10));
    assertEquals(mat.getC3(), new Vec4.Primitive(10, 11, 12, 13));

    Mat4.Primitive identity = new Mat4.Primitive();
    assertEquals(identity.getC0(), new Vec4.Primitive(1, 0, 0, 0));
    assertEquals(identity.getC1(), new Vec4.Primitive(0, 1, 0, 0));
    assertEquals(identity.getC2(), new Vec4.Primitive(0, 0, 1, 0));
    assertEquals(identity.getC3(), new Vec4.Primitive(0, 0, 0, 1));
  }

  @Test
  public void testGetters() {
    assertEquals(mat.getC0(), new Vec4.Primitive(1, 2, 3, 4));
    assertEquals(mat.getC1(), new Vec4.Primitive(4, 5, 6, 7));
    assertEquals(mat.getC2(), new Vec4.Primitive(7, 8, 9, 10));
    assertEquals(mat.getC3(), new Vec4.Primitive(10, 11, 12, 13));

    assertEquals(mat.getC0(), mat.get(0));
    assertEquals(mat.getC1(), mat.get(1));
    assertEquals(mat.getC2(), mat.get(2));
    assertEquals(mat.getC3(), mat.get(3));
  }

  @Test
  public void testAdd() {
    Mat4.Primitive added = mat.add(1);
    assertEquals(added, new Mat4.Primitive(
        new Vec4.Primitive(2, 3, 4, 5),
        new Vec4.Primitive(5, 6, 7, 8),
        new Vec4.Primitive(8, 9, 10, 11),
        new Vec4.Primitive(11, 12, 13, 14)));

    added = mat.add(mat);
    assertEquals(added, new Mat4.Primitive(
        new Vec4.Primitive(2, 4, 6, 8),
        new Vec4.Primitive(8, 10, 12, 14),
        new Vec4.Primitive(14, 16, 18, 20),
        new Vec4.Primitive(20, 22, 24, 26)));
  }

  @Test
  public void testSub() {
    Mat4.Primitive subtracted = mat.sub(1);
    assertEquals(subtracted, new Mat4.Primitive(
        new Vec4.Primitive(0, 1, 2, 3),
        new Vec4.Primitive(3, 4, 5, 6),
        new Vec4.Primitive(6, 7, 8, 9),
        new Vec4.Primitive(9, 10, 11, 12)));

    subtracted = mat.sub(mat);
    assertEquals(subtracted, new Mat4.Primitive(
        new Vec4.Primitive(0, 0, 0, 0),
        new Vec4.Primitive(0, 0, 0, 0),
        new Vec4.Primitive(0, 0, 0, 0),
        new Vec4.Primitive(0, 0, 0, 0)));
  }

  @Test
  public void testMul() {
    Mat4.Primitive multiplied = mat.mul(2);
    assertEquals(multiplied, new Mat4.Primitive(
        new Vec4.Primitive(2, 4, 6, 8),
        new Vec4.Primitive(8, 10, 12, 14),
        new Vec4.Primitive(14, 16, 18, 20),
        new Vec4.Primitive(20, 22, 24, 26)));

    multiplied = mat.mul(mat);
    assertEquals(multiplied, new Mat4.Primitive(
        new Vec4.Primitive(70,  80,  90, 100),
        new Vec4.Primitive(136, 158, 180, 202),
        new Vec4.Primitive(202, 236, 270, 304),
        new Vec4.Primitive(268, 314, 360, 406)));

    Vec4.Primitive transformed = mat.mul(new Vec4.Primitive(1, 2, 3, 4));
    assertEquals(transformed, new Vec4.Primitive(70, 80, 90, 100));
  }

  @Test
  public void testDiv() {
    Mat4.Primitive divided = mat.div(2);
    assertEquals(divided, new Mat4.Primitive(
        new Vec4.Primitive(0.5f, 1, 1.5f, 2),
        new Vec4.Primitive(2, 2.5f, 3, 3.5f),
        new Vec4.Primitive(3.5f, 4, 4.5f, 5),
        new Vec4.Primitive(5, 5.5f, 6, 6.5f)));

    divided = mat.div(mat);
    assertEquals(divided, new Mat4.Primitive(
        new Vec4.Primitive(1, 1, 1, 1),
        new Vec4.Primitive(1, 1, 1, 1),
        new Vec4.Primitive(1, 1, 1, 1),
        new Vec4.Primitive(1, 1, 1, 1)));
  }

  @Test
  public void testNeg() {
    Mat4.Primitive negated = mat.neg();
    assertEquals(negated, new Mat4.Primitive(
        new Vec4.Primitive(-1, -2, -3, -4),
        new Vec4.Primitive(-4, -5, -6, -7),
        new Vec4.Primitive(-7, -8, -9, -10),
        new Vec4.Primitive(-10, -11, -12, -13)));
  }

  @Test
  public void testDeterminant() {
    float determinant = mat.determinant();

    assertTrue(determinant == 0);
  }

  @Test
  public void testTranspose() {
    Mat4.Primitive transpose = mat.transpose();

    assertEquals(transpose, new Mat4.Primitive(
        new Vec4.Primitive(1, 4, 7, 10),
        new Vec4.Primitive(2, 5, 8, 11),
        new Vec4.Primitive(3, 6, 9, 12),
        new Vec4.Primitive(4, 7, 10, 13)));
  }

  @Test
  public void testInverse() {
    Mat4.Primitive invertible = new Mat4.Primitive(
        new Vec4.Primitive(2, 0, 0, 0),
        new Vec4.Primitive(0, 2, 0, 1),
        new Vec4.Primitive(0, 0, 2, 1),
        new Vec4.Primitive(0, 0, 0, 1));

    Mat4.Primitive inverse = invertible.inverse();

    assertEquals(invertible.mul(inverse), new Mat4.Primitive());
    assertEquals(inverse.mul(invertible), new Mat4.Primitive());
  }

  @Test
  public void testMatrixCompMult() {
    Mat4.Primitive matrixCompMult = mat.matrixCompMult(mat);
    assertEquals(matrixCompMult, new Mat4.Primitive(
        new Vec4.Primitive(1, 4, 9, 16),
        new Vec4.Primitive(16, 25, 36, 49),
        new Vec4.Primitive(49, 64, 81, 100),
        new Vec4.Primitive(100, 121, 144, 169)));
  }


  @Test
  @SuppressWarnings("all")
  public void testEquals() {
    Mat4.Primitive equal = new Mat4.Primitive(
        new Vec4.Primitive(1, 2, 3, 4),
        new Vec4.Primitive(4, 5, 6, 7),
        new Vec4.Primitive(7, 8, 9, 10),
        new Vec4.Primitive(10, 11, 12, 13));
    assertEquals(mat, equal);

    Mat4.Primitive unequal = new Mat4.Primitive(
        new Vec4.Primitive(2, 3, 4, 5),
        new Vec4.Primitive(5, 6, 7, 8),
        new Vec4.Primitive(8, 9, 10, 11),
        new Vec4.Primitive(11, 12, 13, 14));
    assertFalse(mat.equals(unequal));

    assertFalse(mat.equals(null));

    assertFalse(mat.equals("aString"));
  }

  @Test
  public void testHashCode() {
    Mat4.Primitive equal = new Mat4.Primitive(
        new Vec4.Primitive(1, 2, 3, 4),
        new Vec4.Primitive(4, 5, 6, 7),
        new Vec4.Primitive(7, 8, 9, 10),
        new Vec4.Primitive(10, 11, 12, 13));

    assertEquals(mat, equal);
    assertEquals(mat.hashCode(), equal.hashCode());
  }

  @Test
  public void testToString() {
    String toString = mat.toString();

    assertEquals(toString, "mat4(vec4(1.0, 2.0, 3.0, 4.0), vec4(4.0, 5.0, 6.0, 7.0), vec4(7.0, 8.0, 9.0, 10.0), vec4(10.0, 11.0, 12.0, 13.0))");
  }
}
