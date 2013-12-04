package com.lfscheidegger.jfacet.shade.expression.vector;

import com.lfscheidegger.jfacet.shade.expression.vector.swizzle.Swizzle;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Unit tests for {@code Vector4.Primitive}
 */
public class Vector4PrimitiveTest {

  private final Vector4.Primitive vec;

  public Vector4PrimitiveTest() {
    vec = new Vector4.Primitive(1, 2, 3, 4);
  }

  @Test
  public void testConstructors() {
    assertTrue(vec.getX() == 1);
    assertTrue(vec.getY() == 2);
    assertTrue(vec.getZ() == 3);
    assertTrue(vec.getW() == 4);
  }

  @Test
  public void testGetters() {
    float x = vec.getX();
    float y = vec.getY();
    float z = vec.getZ();
    float w = vec.getW();

    assertTrue(x == 1);
    assertTrue(y == 2);
    assertTrue(z == 3);
    assertTrue(w == 4);

    assertTrue(vec.get(0) == vec.getX());
    assertTrue(vec.get(1) == vec.getY());
    assertTrue(vec.get(2) == vec.getZ());
    assertTrue(vec.get(3) == vec.getW());
  }

  @Test
  public void testAdd() {
    Vector4.Primitive added = vec.add(1);
    assertTrue(added.getX() == 2);
    assertTrue(added.getY() == 3);
    assertTrue(added.getZ() == 4);
    assertTrue(added.getW() == 5);

    added = vec.add(vec);
    assertTrue(added.getX() == 2);
    assertTrue(added.getY() == 4);
    assertTrue(added.getZ() == 6);
    assertTrue(added.getW() == 8);
  }

  @Test
  public void testSub() {
    Vector4.Primitive subtracted = vec.sub(1);
    assertTrue(subtracted.getX() == 0);
    assertTrue(subtracted.getY() == 1);
    assertTrue(subtracted.getZ() == 2);
    assertTrue(subtracted.getW() == 3);

    subtracted = vec.sub(vec);
    assertTrue(subtracted.getX() == 0);
    assertTrue(subtracted.getY() == 0);
    assertTrue(subtracted.getZ() == 0);
    assertTrue(subtracted.getW() == 0);
  }

  @Test
  public void testMul() {
    Vector4.Primitive scaled = vec.mul(3);
    assertTrue(scaled.getX() == 3);
    assertTrue(scaled.getY() == 6);
    assertTrue(scaled.getZ() == 9);
    assertTrue(scaled.getW() == 12);

    scaled = vec.mul(vec);
    assertTrue(scaled.getX() == 1);
    assertTrue(scaled.getY() == 4);
    assertTrue(scaled.getZ() == 9);
    assertTrue(scaled.getW() == 16);
  }

  @Test
  public void testDiv() {
    Vector4.Primitive divided = vec.div(2);
    assertTrue(divided.getX() == 0.5);
    assertTrue(divided.getY() == 1);
    assertTrue(divided.getZ() == 1.5);
    assertTrue(divided.getW() == 2);

    divided = vec.div(vec);
    assertTrue(divided.getX() == 1);
    assertTrue(divided.getY() == 1);
    assertTrue(divided.getZ() == 1);
    assertTrue(divided.getW() == 1);
  }

  @Test
  public void testNeg() {
    Vector4.Primitive negated = vec.neg();

    assertTrue(negated.getX() == -1);
    assertTrue(negated.getY() == -2);
    assertTrue(negated.getZ() == -3);
    assertTrue(negated.getW() == -4);
  }

  @Test
  public void testNormalize() {
    Vector4.Primitive normalized = vec.normalize();

    assertEquals(normalized.getX(), 0.18257418583505536, 10e-8);
    assertEquals(normalized.getY(), 0.3651483716701107, 10e-8);
    assertEquals(normalized.getZ(), 0.5477225575051661, 10e-8);
    assertEquals(normalized.getW(), 0.7302967433402214, 10e-8);
  }

  @Test
  public void testLength() {
    float length = vec.length();

    assertEquals(length, 5.477225575051661, 10e-7);
  }

  @Test
  public void testDot() {
    float dot = vec.dot(vec);

    assertTrue(dot == 30);
  }

  @Test
  public void testBooleanOperations() {
    BVector4.Primitive lessThan = vec.isLessThan(new Vector4.Primitive(1, 3, 3, 3));
    assertFalse(lessThan.getX());
    assertTrue(lessThan.getY());
    assertFalse(lessThan.getZ());
    assertFalse(lessThan.getW());

    BVector4.Primitive lessThanOrEqual = vec.isLessThanOrEqual(new Vector4.Primitive(1, 3, 3, 3));
    assertTrue(lessThanOrEqual.getX());
    assertTrue(lessThanOrEqual.getY());
    assertTrue(lessThanOrEqual.getZ());
    assertFalse(lessThanOrEqual.getW());

    BVector4.Primitive greaterThan = vec.isGreaterThan(new Vector4.Primitive(1, 1, 1, 1));
    assertFalse(greaterThan.getX());
    assertTrue(greaterThan.getY());
    assertTrue(greaterThan.getZ());
    assertTrue(greaterThan.getW());

    BVector4.Primitive greaterThanOrEqual = vec.isGreaterThanOrEqual(new Vector4.Primitive(1, 1, 1, 1));
    assertTrue(greaterThanOrEqual.getX());
    assertTrue(greaterThanOrEqual.getY());
    assertTrue(greaterThanOrEqual.getZ());
    assertTrue(greaterThanOrEqual.getW());

    BVector4.Primitive equalComponentwise = vec.isEqualComponentwise(new Vector4.Primitive(1, 1, 1, 1));
    assertTrue(equalComponentwise.getX());
    assertFalse(equalComponentwise.getY());
    assertFalse(equalComponentwise.getZ());
    assertFalse(equalComponentwise.getW());

    BVector4.Primitive notEqualComponentwise = vec.isNotEqualComponentwise(new Vector4.Primitive(1, 1, 1, 1));
    assertFalse(notEqualComponentwise.getX());
    assertTrue(notEqualComponentwise.getY());
    assertTrue(notEqualComponentwise.getZ());
    assertTrue(notEqualComponentwise.getW());
  }

  @Test
  public void testSwizzle() {
    assertTrue(vec.swizzle(Swizzle.D41.X) == 1);
    assertTrue(vec.swizzle(Swizzle.D41.Y) == 2);
    assertTrue(vec.swizzle(Swizzle.D41.Z) == 3);
    assertTrue(vec.swizzle(Swizzle.D41.W) == 4);

    assertEquals(vec.swizzle(Swizzle.D42.XX), new Vector2.Primitive(1, 1));
    assertEquals(vec.swizzle(Swizzle.D43.XXX), new Vector3.Primitive(1, 1, 1));
    assertEquals(vec.swizzle(Swizzle.D44.XXXX), new Vector4.Primitive(1, 1, 1, 1));
  }

  @Test
  @SuppressWarnings("all")
  public void testEquals() {
    Vector4.Primitive equal = new Vector4.Primitive(1, 2, 3, 4);
    assertEquals(vec, equal);

    Vector4.Primitive unequal = new Vector4.Primitive(3, 4, 5, 6);
    assertFalse(vec.equals(unequal));

    assertFalse(vec.equals(null));

    assertFalse(vec.equals("A string"));
  }

  @Test
  public void testHashCode() {
    Vector4.Primitive equal = new Vector4.Primitive(1, 2, 3, 4);

    assertEquals(vec, equal);
    assertEquals(vec.hashCode(), equal.hashCode());
  }

  @Test
  public void testToString() {
    String toString = vec.toString();
    assertEquals(toString, "vec4(1.0, 2.0, 3.0, 4.0)");
  }
}