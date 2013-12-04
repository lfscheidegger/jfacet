package com.lfscheidegger.jfacet.shade.expression.vector;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Unit tests for {@code Vector2.Primitive}
 */
public class Vector2PrimitiveTest {

  private final Vector2.Primitive vec;

  public Vector2PrimitiveTest() {
    vec = new Vector2.Primitive(1, 2);
  }

  @Test
  public void testConstructors() {
    assertTrue(vec.getX() == 1);
    assertTrue(vec.getY() == 2);
  }

  @Test
  public void testGetters() {
    float x = vec.getX();
    float y = vec.getY();

    assertTrue(x == 1);
    assertTrue(y == 2);

    assertTrue(vec.get(0) == vec.getX());
    assertTrue(vec.get(1) == vec.getY());
  }

  @Test
  public void testAdd() {
    Vector2.Primitive added = vec.add(1);
    assertTrue(added.getX() == 2);
    assertTrue(added.getY() == 3);

    added = vec.add(vec);
    assertTrue(added.getX() == 2);
    assertTrue(added.getY() == 4);
  }

  @Test
  public void testSub() {
    Vector2.Primitive subtracted = vec.sub(1);
    assertTrue(subtracted.getX() == 0);
    assertTrue(subtracted.getY() == 1);

    subtracted = vec.sub(vec);
    assertTrue(subtracted.getX() == 0);
    assertTrue(subtracted.getY() == 0);
  }

  @Test
  public void testMul() {
    Vector2.Primitive scaled = vec.mul(3);
    assertTrue(scaled.getX() == 3);
    assertTrue(scaled.getY() == 6);

    scaled = vec.mul(vec);
    assertTrue(scaled.getX() == 1);
    assertTrue(scaled.getY() == 4);
  }

  @Test
  public void testDiv() {
    Vector2.Primitive divided = vec.div(2);
    assertTrue(divided.getX() == 0.5);
    assertTrue(divided.getY() == 1);

    divided = vec.div(vec);
    assertTrue(divided.getX() == 1);
    assertTrue(divided.getY() == 1);
  }

  @Test
  public void testNeg() {
    Vector2.Primitive negated = vec.neg();

    assertTrue(negated.getX() == -1);
    assertTrue(negated.getY() == -2);
  }

  @Test
  public void testNormalize() {
    Vector2.Primitive normalized = vec.normalize();

    assertEquals(normalized.getX(), 0.4472135954999579, 10e-8);
    assertEquals(normalized.getY(), 0.8944271909999159, 10e-8);
  }

  @Test
  public void testLength() {
    float length = vec.length();

    assertEquals(length, 2.23606797749979, 10e-8);
  }

  @Test
  public void testDot() {
    float dot = vec.dot(vec);

    assertTrue(dot == 5);
  }

  @Test
  public void testBooleanOperations() {
    BVector2.Primitive lessThan = vec.isLessThan(new Vector2.Primitive(1, 3));
    assertFalse(lessThan.getX());
    assertTrue(lessThan.getY());

    BVector2.Primitive lessThanOrEqual = vec.isLessThanOrEqual(new Vector2.Primitive(1, 3));
    assertTrue(lessThanOrEqual.getX());
    assertTrue(lessThanOrEqual.getY());

    BVector2.Primitive greaterThan = vec.isGreaterThan(new Vector2.Primitive(1, 1));
    assertFalse(greaterThan.getX());
    assertTrue(greaterThan.getY());

    BVector2.Primitive greaterThanOrEqual = vec.isGreaterThanOrEqual(new Vector2.Primitive(1, 1));
    assertTrue(greaterThanOrEqual.getX());
    assertTrue(greaterThanOrEqual.getY());

    BVector2.Primitive equalComponentwise = vec.isEqualComponentwise(new Vector2.Primitive(1, 1));
    assertTrue(equalComponentwise.getX());
    assertFalse(equalComponentwise.getY());

    BVector2.Primitive notEqualComponentwise = vec.isNotEqualComponentwise(new Vector2.Primitive(1, 1));
    assertFalse(notEqualComponentwise.getX());
    assertTrue(notEqualComponentwise.getY());
  }

  @Test
  @SuppressWarnings("all")
  public void testEquals() {
    Vector2.Primitive equal = new Vector2.Primitive(1, 2);
    assertEquals(vec, equal);

    Vector2.Primitive unequal = new Vector2.Primitive(3, 4);
    assertFalse(vec.equals(unequal));

    assertFalse(vec.equals(null));

    assertFalse(vec.equals("A string"));
  }

  @Test
  public void testHashCode() {
    Vector2.Primitive equal = new Vector2.Primitive(1, 2);

    assertEquals(vec, equal);
    assertEquals(vec.hashCode(), equal.hashCode());
  }

  @Test
  public void testToString() {
    String toString = vec.toString();
    assertEquals(toString, "vec2(1.0, 2.0)");
  }
}
