package com.lfscheidegger.jfacet.shade.expression;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Unit tests for {@code Vec2.Primitive}
 */
public class Vec2PrimitiveTest {

  private final Vec2.Primitive vec;

  public Vec2PrimitiveTest() {
    vec = new Vec2.Primitive(1, 2);
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
  @SuppressWarnings("all")
  public void testSwizzleXYZW() {
    assertTrue(vec.x().get() == 1);
    assertTrue(vec.y().get() == 2);

    assertEquals(vec.x().x().get(), new Vec2.Primitive(1, 1));
    assertEquals(vec.x().y().get(), new Vec2.Primitive(1, 2));
    assertEquals(vec.y().x().get(), new Vec2.Primitive(2, 1));
    assertEquals(vec.y().y().get(), new Vec2.Primitive(2, 2));

    assertEquals(vec.x().x().x().get(), new Vec3.Primitive(1, 1, 1));
    assertEquals(vec.x().x().y().get(), new Vec3.Primitive(1, 1, 2));
    assertEquals(vec.x().y().x().get(), new Vec3.Primitive(1, 2, 1));
    assertEquals(vec.x().y().y().get(), new Vec3.Primitive(1, 2, 2));
    assertEquals(vec.y().x().x().get(), new Vec3.Primitive(2, 1, 1));
    assertEquals(vec.y().x().y().get(), new Vec3.Primitive(2, 1, 2));
    assertEquals(vec.y().y().x().get(), new Vec3.Primitive(2, 2, 1));
    assertEquals(vec.y().y().y().get(), new Vec3.Primitive(2, 2, 2));

    assertEquals(vec.x().x().x().x(), new Vec4.Primitive(1, 1, 1, 1));
    assertEquals(vec.x().x().x().y(), new Vec4.Primitive(1, 1, 1, 2));
    assertEquals(vec.x().x().y().x(), new Vec4.Primitive(1, 1, 2, 1));
    assertEquals(vec.x().x().y().y(), new Vec4.Primitive(1, 1, 2, 2));
    assertEquals(vec.x().y().x().x(), new Vec4.Primitive(1, 2, 1, 1));
    assertEquals(vec.x().y().x().y(), new Vec4.Primitive(1, 2, 1, 2));
    assertEquals(vec.x().y().y().x(), new Vec4.Primitive(1, 2, 2, 1));
    assertEquals(vec.x().y().y().y(), new Vec4.Primitive(1, 2, 2, 2));
    assertEquals(vec.y().x().x().x(), new Vec4.Primitive(2, 1, 1, 1));
    assertEquals(vec.y().x().x().y(), new Vec4.Primitive(2, 1, 1, 2));
    assertEquals(vec.y().x().y().x(), new Vec4.Primitive(2, 1, 2, 1));
    assertEquals(vec.y().x().y().y(), new Vec4.Primitive(2, 1, 2, 2));
    assertEquals(vec.y().y().x().x(), new Vec4.Primitive(2, 2, 1, 1));
    assertEquals(vec.y().y().x().y(), new Vec4.Primitive(2, 2, 1, 2));
    assertEquals(vec.y().y().y().x(), new Vec4.Primitive(2, 2, 2, 1));
    assertEquals(vec.y().y().y().y(), new Vec4.Primitive(2, 2, 2, 2));

  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleRGBA() {
    assertTrue(vec.r().get() == 1);
    assertTrue(vec.g().get() == 2);

    assertEquals(vec.r().r().get(), new Vec2.Primitive(1, 1));
    assertEquals(vec.r().g().get(), new Vec2.Primitive(1, 2));
    assertEquals(vec.g().r().get(), new Vec2.Primitive(2, 1));
    assertEquals(vec.g().g().get(), new Vec2.Primitive(2, 2));

    assertEquals(vec.r().r().r().get(), new Vec3.Primitive(1, 1, 1));
    assertEquals(vec.r().r().g().get(), new Vec3.Primitive(1, 1, 2));
    assertEquals(vec.r().g().r().get(), new Vec3.Primitive(1, 2, 1));
    assertEquals(vec.r().g().g().get(), new Vec3.Primitive(1, 2, 2));
    assertEquals(vec.g().r().r().get(), new Vec3.Primitive(2, 1, 1));
    assertEquals(vec.g().r().g().get(), new Vec3.Primitive(2, 1, 2));
    assertEquals(vec.g().g().r().get(), new Vec3.Primitive(2, 2, 1));
    assertEquals(vec.g().g().g().get(), new Vec3.Primitive(2, 2, 2));

    assertEquals(vec.r().r().r().r(), new Vec4.Primitive(1, 1, 1, 1));
    assertEquals(vec.r().r().r().g(), new Vec4.Primitive(1, 1, 1, 2));
    assertEquals(vec.r().r().g().r(), new Vec4.Primitive(1, 1, 2, 1));
    assertEquals(vec.r().r().g().g(), new Vec4.Primitive(1, 1, 2, 2));
    assertEquals(vec.r().g().r().r(), new Vec4.Primitive(1, 2, 1, 1));
    assertEquals(vec.r().g().r().g(), new Vec4.Primitive(1, 2, 1, 2));
    assertEquals(vec.r().g().g().r(), new Vec4.Primitive(1, 2, 2, 1));
    assertEquals(vec.r().g().g().g(), new Vec4.Primitive(1, 2, 2, 2));
    assertEquals(vec.g().r().r().r(), new Vec4.Primitive(2, 1, 1, 1));
    assertEquals(vec.g().r().r().g(), new Vec4.Primitive(2, 1, 1, 2));
    assertEquals(vec.g().r().g().r(), new Vec4.Primitive(2, 1, 2, 1));
    assertEquals(vec.g().r().g().g(), new Vec4.Primitive(2, 1, 2, 2));
    assertEquals(vec.g().g().r().r(), new Vec4.Primitive(2, 2, 1, 1));
    assertEquals(vec.g().g().r().g(), new Vec4.Primitive(2, 2, 1, 2));
    assertEquals(vec.g().g().g().r(), new Vec4.Primitive(2, 2, 2, 1));
    assertEquals(vec.g().g().g().g(), new Vec4.Primitive(2, 2, 2, 2));

  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleSTPQ() {
    assertTrue(vec.s().get() == 1);
    assertTrue(vec.t().get() == 2);

    assertEquals(vec.s().s().get(), new Vec2.Primitive(1, 1));
    assertEquals(vec.s().t().get(), new Vec2.Primitive(1, 2));
    assertEquals(vec.t().s().get(), new Vec2.Primitive(2, 1));
    assertEquals(vec.t().t().get(), new Vec2.Primitive(2, 2));

    assertEquals(vec.s().s().s().get(), new Vec3.Primitive(1, 1, 1));
    assertEquals(vec.s().s().t().get(), new Vec3.Primitive(1, 1, 2));
    assertEquals(vec.s().t().s().get(), new Vec3.Primitive(1, 2, 1));
    assertEquals(vec.s().t().t().get(), new Vec3.Primitive(1, 2, 2));
    assertEquals(vec.t().s().s().get(), new Vec3.Primitive(2, 1, 1));
    assertEquals(vec.t().s().t().get(), new Vec3.Primitive(2, 1, 2));
    assertEquals(vec.t().t().s().get(), new Vec3.Primitive(2, 2, 1));
    assertEquals(vec.t().t().t().get(), new Vec3.Primitive(2, 2, 2));

    assertEquals(vec.s().s().s().s(), new Vec4.Primitive(1, 1, 1, 1));
    assertEquals(vec.s().s().s().t(), new Vec4.Primitive(1, 1, 1, 2));
    assertEquals(vec.s().s().t().s(), new Vec4.Primitive(1, 1, 2, 1));
    assertEquals(vec.s().s().t().t(), new Vec4.Primitive(1, 1, 2, 2));
    assertEquals(vec.s().t().s().s(), new Vec4.Primitive(1, 2, 1, 1));
    assertEquals(vec.s().t().s().t(), new Vec4.Primitive(1, 2, 1, 2));
    assertEquals(vec.s().t().t().s(), new Vec4.Primitive(1, 2, 2, 1));
    assertEquals(vec.s().t().t().t(), new Vec4.Primitive(1, 2, 2, 2));
    assertEquals(vec.t().s().s().s(), new Vec4.Primitive(2, 1, 1, 1));
    assertEquals(vec.t().s().s().t(), new Vec4.Primitive(2, 1, 1, 2));
    assertEquals(vec.t().s().t().s(), new Vec4.Primitive(2, 1, 2, 1));
    assertEquals(vec.t().s().t().t(), new Vec4.Primitive(2, 1, 2, 2));
    assertEquals(vec.t().t().s().s(), new Vec4.Primitive(2, 2, 1, 1));
    assertEquals(vec.t().t().s().t(), new Vec4.Primitive(2, 2, 1, 2));
    assertEquals(vec.t().t().t().s(), new Vec4.Primitive(2, 2, 2, 1));
    assertEquals(vec.t().t().t().t(), new Vec4.Primitive(2, 2, 2, 2));

  }

  @Test
  public void testAdd() {
    Vec2.Primitive added = vec.plus(1);
    assertTrue(added.getX() == 2);
    assertTrue(added.getY() == 3);

    added = vec.plus(vec);
    assertTrue(added.getX() == 2);
    assertTrue(added.getY() == 4);
  }

  @Test
  public void testSub() {
    Vec2.Primitive subtracted = vec.minus(1);
    assertTrue(subtracted.getX() == 0);
    assertTrue(subtracted.getY() == 1);

    subtracted = vec.minus(vec);
    assertTrue(subtracted.getX() == 0);
    assertTrue(subtracted.getY() == 0);
  }

  @Test
  public void testMul() {
    Vec2.Primitive scaled = vec.times(3);
    assertTrue(scaled.getX() == 3);
    assertTrue(scaled.getY() == 6);

    scaled = vec.times(vec);
    assertTrue(scaled.getX() == 1);
    assertTrue(scaled.getY() == 4);
  }

  @Test
  public void testDiv() {
    Vec2.Primitive divided = vec.div(2);
    assertTrue(divided.getX() == 0.5);
    assertTrue(divided.getY() == 1);

    divided = vec.div(vec);
    assertTrue(divided.getX() == 1);
    assertTrue(divided.getY() == 1);
  }

  @Test
  public void testNeg() {
    Vec2.Primitive negated = vec.negative();

    assertTrue(negated.getX() == -1);
    assertTrue(negated.getY() == -2);
  }

  @Test
  public void testNormalize() {
    Vec2.Primitive normalized = vec.normalize();

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
    BVec2.Primitive lessThan = vec.isLessThan(new Vec2.Primitive(1, 3));
    assertFalse(lessThan.getX());
    assertTrue(lessThan.getY());

    BVec2.Primitive lessThanOrEqual = vec.isLessThanOrEqual(new Vec2.Primitive(1, 3));
    assertTrue(lessThanOrEqual.getX());
    assertTrue(lessThanOrEqual.getY());

    BVec2.Primitive greaterThan = vec.isGreaterThan(new Vec2.Primitive(1, 1));
    assertFalse(greaterThan.getX());
    assertTrue(greaterThan.getY());

    BVec2.Primitive greaterThanOrEqual = vec.isGreaterThanOrEqual(new Vec2.Primitive(1, 1));
    assertTrue(greaterThanOrEqual.getX());
    assertTrue(greaterThanOrEqual.getY());

    BVec2.Primitive equalComponentwise = vec.isEqualComponentwise(new Vec2.Primitive(1, 1));
    assertTrue(equalComponentwise.getX());
    assertFalse(equalComponentwise.getY());

    BVec2.Primitive notEqualComponentwise = vec.isNotEqualComponentwise(new Vec2.Primitive(1, 1));
    assertFalse(notEqualComponentwise.getX());
    assertTrue(notEqualComponentwise.getY());
  }

  @Test
  @SuppressWarnings("all")
  public void testEquals() {
    Vec2.Primitive equal = new Vec2.Primitive(1, 2);
    assertEquals(vec, equal);

    Vec2.Primitive unequal = new Vec2.Primitive(3, 4);
    assertFalse(vec.equals(unequal));

    assertFalse(vec.equals(null));

    assertFalse(vec.equals("A string"));
  }

  @Test
  public void testHashCode() {
    Vec2.Primitive equal = new Vec2.Primitive(1, 2);

    assertEquals(vec, equal);
    assertEquals(vec.hashCode(), equal.hashCode());
  }

  @Test
  public void testToString() {
    String toString = vec.toString();
    assertEquals(toString, "vec2(1.0, 2.0)");
  }
}
