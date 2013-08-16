package com.lfscheidegger.jfacet.shade.expression.matrix;

import com.lfscheidegger.jfacet.shade.expression.matrix.Matrix3;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Matrix3.Primitive}
 */
public class Matrix3PrimitiveTest {

  private final Matrix3.Primitive mat;

  public Matrix3PrimitiveTest() {
    mat = new Matrix3.Primitive(
        new Vector3.Primitive(1, 2, 3),
        new Vector3.Primitive(4, 5, 6),
        new Vector3.Primitive(7, 8, 9));
  }

  @Test
  public void testConstructors() {
    assertEquals(mat.getC0(), new Vector3.Primitive(1, 2, 3));
    assertEquals(mat.getC1(), new Vector3.Primitive(4, 5, 6));
    assertEquals(mat.getC2(), new Vector3.Primitive(7, 8, 9));

    Matrix3.Primitive identity = new Matrix3.Primitive();
    assertEquals(identity.getC0(), new Vector3.Primitive(1, 0, 0));
    assertEquals(identity.getC1(), new Vector3.Primitive(0, 1, 0));
    assertEquals(identity.getC2(), new Vector3.Primitive(0, 0, 1));
  }

  @Test
  public void testGetters() {
    assertEquals(mat.getC0(), new Vector3.Primitive(1, 2, 3));
    assertEquals(mat.getC1(), new Vector3.Primitive(4, 5, 6));
    assertEquals(mat.getC2(), new Vector3.Primitive(7, 8, 9));

    assertEquals(mat.getC0(), mat.get(0));
    assertEquals(mat.getC1(), mat.get(1));
    assertEquals(mat.getC2(), mat.get(2));
  }

  @Test
  public void testAdd() {
    Matrix3.Primitive added = mat.add(1);
    assertEquals(added, new Matrix3.Primitive(
        new Vector3.Primitive(2, 3, 4),
        new Vector3.Primitive(5, 6, 7),
        new Vector3.Primitive(8, 9, 10)));

    added = mat.add(mat);
    assertEquals(added, new Matrix3.Primitive(
        new Vector3.Primitive(2, 4, 6),
        new Vector3.Primitive(8, 10, 12),
        new Vector3.Primitive(14, 16, 18)));
  }

  @Test
  public void testSub() {
    Matrix3.Primitive subtracted = mat.sub(1);
    assertEquals(subtracted, new Matrix3.Primitive(
        new Vector3.Primitive(0, 1, 2),
        new Vector3.Primitive(3, 4, 5),
        new Vector3.Primitive(6, 7, 8)));

    subtracted = mat.sub(mat);
    assertEquals(subtracted, new Matrix3.Primitive(
        new Vector3.Primitive(0, 0, 0),
        new Vector3.Primitive(0, 0, 0),
        new Vector3.Primitive(0, 0, 0)));
  }

  @Test
  public void testMul() {
    Matrix3.Primitive multiplied = mat.mul(2);
    assertEquals(multiplied, new Matrix3.Primitive(
        new Vector3.Primitive(2, 4, 6),
        new Vector3.Primitive(8, 10, 12),
        new Vector3.Primitive(14, 16, 18)));

    multiplied = mat.mul(mat);
    assertEquals(multiplied, new Matrix3.Primitive(
        new Vector3.Primitive(30, 36, 42),
        new Vector3.Primitive(66, 81, 96),
        new Vector3.Primitive(102, 126, 150)));
  }

  @Test
  public void testDiv() {
    Matrix3.Primitive divided = mat.div(2);
    assertEquals(divided, new Matrix3.Primitive(
        new Vector3.Primitive(0.5f, 1, 1.5f),
        new Vector3.Primitive(2, 2.5f, 3),
        new Vector3.Primitive(3.5f, 4, 4.5f)));

    divided = mat.div(mat);
    assertEquals(divided, new Matrix3.Primitive(
        new Vector3.Primitive(1, 1, 1),
        new Vector3.Primitive(1, 1, 1),
        new Vector3.Primitive(1, 1, 1)));
  }

  @Test
  public void testNeg() {
    Matrix3.Primitive negated = mat.neg();
    assertEquals(negated, new Matrix3.Primitive(
        new Vector3.Primitive(-1, -2, -3),
        new Vector3.Primitive(-4, -5, -6),
        new Vector3.Primitive(-7, -8, -9)));
  }

  @Test
  public void testTransform() {
    Vector3.Primitive transformed = mat.transform(new Vector3.Primitive(1, 2, 3));

    assertEquals(transformed, new Vector3.Primitive(30, 36, 42));
  }

  @Test
  public void testDeterminant() {
    float determinant = mat.determinant();

    assertTrue(determinant == 0);
  }

  @Test
  public void testTranspose() {
    Matrix3.Primitive transpose = mat.transpose();

    assertEquals(transpose, new Matrix3.Primitive(
        new Vector3.Primitive(1, 4, 7),
        new Vector3.Primitive(2, 5, 8),
        new Vector3.Primitive(3, 6, 9)));
  }

  @Test
  public void testInverse() {
    Matrix3.Primitive invertible = new Matrix3.Primitive(
        new Vector3.Primitive(1, 2, 3),
        new Vector3.Primitive(4, 5, 6),
        new Vector3.Primitive(7, 8, 10));

    Matrix3.Primitive inverse = invertible.inverse();

    assertEquals(invertible.mul(inverse), new Matrix3.Primitive());
    assertEquals(inverse.mul(invertible), new Matrix3.Primitive());
  }

  @Test
  @SuppressWarnings("all")
  public void testEquals() {
    Matrix3.Primitive equal = new Matrix3.Primitive(
        new Vector3.Primitive(1, 2, 3),
        new Vector3.Primitive(4, 5, 6),
        new Vector3.Primitive(7, 8, 9));
    assertEquals(mat, equal);

    Matrix3.Primitive unequal = new Matrix3.Primitive(
        new Vector3.Primitive(2, 3, 4),
        new Vector3.Primitive(5, 6, 7),
        new Vector3.Primitive(8, 9, 10));
    assertFalse(mat.equals(unequal));

    assertFalse(mat.equals(null));

    assertFalse(mat.equals("aString"));
  }

  @Test
  public void testHashCode() {
    Matrix3.Primitive equal = new Matrix3.Primitive(
        new Vector3.Primitive(1, 2, 3),
        new Vector3.Primitive(4, 5, 6),
        new Vector3.Primitive(7, 8, 9));

    assertEquals(mat, equal);
    assertEquals(mat.hashCode(), equal.hashCode());
  }

  @Test
  public void testToString() {
    String toString = mat.toString();

    assertEquals(toString, "mat3(vec3(1.0, 2.0, 3.0), vec3(4.0, 5.0, 6.0), vec3(7.0, 8.0, 9.0))");
  }
}
