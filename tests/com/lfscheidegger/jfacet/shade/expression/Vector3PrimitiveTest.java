package com.lfscheidegger.jfacet.shade.expression;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Unit tests for {@code Vector3.Primitive}
 */
public class Vector3PrimitiveTest {

  private final Vector3.Primitive vec;

  public Vector3PrimitiveTest() {
    vec = new Vector3.Primitive(1, 2, 3);
  }

  @Test
  public void testConstructors() {
    assertTrue(vec.getX() == 1);
    assertTrue(vec.getY() == 2);
    assertTrue(vec.getZ() == 3);
  }

  @Test
  public void testGetters() {
    float x = vec.getX();
    float y = vec.getY();
    float z = vec.getZ();

    assertTrue(x == 1);
    assertTrue(y == 2);
    assertTrue(z == 3);

    assertTrue(vec.get(0) == vec.getX());
    assertTrue(vec.get(1) == vec.getY());
    assertTrue(vec.get(2) == vec.getZ());
  }

  @Test
  public void testAdd() {
    Vector3.Primitive added = vec.add(1);
    assertTrue(added.getX() == 2);
    assertTrue(added.getY() == 3);
    assertTrue(added.getZ() == 4);

    added = vec.add(vec);
    assertTrue(added.getX() == 2);
    assertTrue(added.getY() == 4);
    assertTrue(added.getZ() == 6);
  }

  @Test
  public void testSub() {
    Vector3.Primitive subtracted = vec.sub(1);
    assertTrue(subtracted.getX() == 0);
    assertTrue(subtracted.getY() == 1);
    assertTrue(subtracted.getZ() == 2);

    subtracted = vec.sub(vec);
    assertTrue(subtracted.getX() == 0);
    assertTrue(subtracted.getY() == 0);
    assertTrue(subtracted.getZ() == 0);
  }

  @Test
  public void testMul() {
    Vector3.Primitive scaled = vec.mul(3);
    assertTrue(scaled.getX() == 3);
    assertTrue(scaled.getY() == 6);
    assertTrue(scaled.getZ() == 9);

    scaled = vec.mul(vec);
    assertTrue(scaled.getX() == 1);
    assertTrue(scaled.getY() == 4);
    assertTrue(scaled.getZ() == 9);
  }

  @Test
  public void testDiv() {
    Vector3.Primitive divided = vec.div(2);
    assertTrue(divided.getX() == 0.5);
    assertTrue(divided.getY() == 1);
    assertTrue(divided.getZ() == 1.5);

    divided = vec.div(vec);
    assertTrue(divided.getX() == 1);
    assertTrue(divided.getY() == 1);
    assertTrue(divided.getZ() == 1);
  }

  @Test
  public void testNeg() {
    Vector3.Primitive negated = vec.neg();

    assertTrue(negated.getX() == -1);
    assertTrue(negated.getY() == -2);
    assertTrue(negated.getZ() == -3);
  }

  @Test
  public void testNormalize() {
    Vector3.Primitive normalized = vec.normalize();

    assertEquals(normalized.getX(), 0.2672612419124244, 10e-8);
    assertEquals(normalized.getY(), 0.5345224838248488, 10e-8);
    assertEquals(normalized.getZ(), 0.8017837257372732, 10e-8);
  }

  @Test
  public void testLength() {
    float length = vec.length();

    assertEquals(length, 3.7416573867739413, 10e-7);
  }

  @Test
  public void testDot() {
    float dot = vec.dot(vec);

    assertTrue(dot == 14);
  }

  @Test
  public void testSwizzle() {
    assertTrue(vec.swizzle('x') == 1);
    assertTrue(vec.swizzle('y') == 2);
    assertTrue(vec.swizzle('z') == 3);

    assertEquals(vec.swizzle('x', 'x'), new Vector2.Primitive(1, 1));
    assertEquals(vec.swizzle('x', 'x', 'x'), new Vector3.Primitive(1, 1, 1));
    assertEquals(vec.swizzle('x', 'x', 'x', 'x'), new Vector4.Primitive(1, 1, 1, 1));
  }

  @Test
  @SuppressWarnings("all")
  public void testEquals() {
    Vector3.Primitive equal = new Vector3.Primitive(1, 2, 3);
    assertEquals(vec, equal);

    Vector3.Primitive unequal = new Vector3.Primitive(3, 4, 5);
    assertFalse(vec.equals(unequal));

    assertFalse(vec.equals(null));

    assertFalse(vec.equals("A string"));
  }

  @Test
  public void testHashCode() {
    Vector3.Primitive equal = new Vector3.Primitive(1, 2, 3);

    assertEquals(vec, equal);
    assertEquals(vec.hashCode(), equal.hashCode());
  }

  @Test
  public void testToString() {
    String toString = vec.toString();
    assertEquals(toString, "vec3(1.0, 2.0, 3.0)");
  }
}
