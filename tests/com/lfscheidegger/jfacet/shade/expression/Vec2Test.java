package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.AttributeBuffer;
import org.junit.Test;

import static com.lfscheidegger.jfacet.shade.expression.ExpressionTestUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@code Vec2}
 */
public class Vec2Test {

  private final Vec2 mVec = new Vec2(1, 2);

  @Test
  public void testConstructors() {
    Vec2 vec = new Vec2(1, 2);

    assertEquals(
        ((NodeType.PrimitiveNodeType) vec.getNodeType()).getPrimitive(),
        new Vec2.Primitive(1, 2));

    assertEquals(vec.getParents(), ImmutableList.of());

    Real x = new Real(1), y = new Real(2);
    vec = new Vec2(x, y);

    assertEquals(vec.getNodeType(), NodeType.CONS);
    assertEquals(vec.getParents(), ImmutableList.of(x, y));

    vec = new Vec2(ImmutableList.<Expression>of(x, y), NodeType.CONS);

    assertEquals(vec.getNodeType(), NodeType.CONS);
    assertEquals(vec.getParents(), ImmutableList.of(x, y));

    vec = new Vec2(new AttributeBuffer(new float[] {0, 0, 1, 0, 1, 1}, 2));

    assertTrue(vec.getNodeType() instanceof NodeType.AttributeNodeType);
    assertEquals(vec.getParents(), ImmutableList.<Expression>of());
  }

  @Test
  public void testGetters() {
    Vec2 vec = new Vec2(1, 2);
    Real x = vec.getX();
    testGetter(x, 0, ImmutableList.<Expression>of(vec));

    Real y = vec.getY();
    testGetter(y, 1, ImmutableList.<Expression>of(vec));

    x = vec.get(0);
    testGetter(x, 0, ImmutableList.<Expression>of(vec));

    y = vec.get(1);
    testGetter(y, 1, ImmutableList.<Expression>of(vec));
  }

  private void testSwizzle1(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof Real);
    testSwizzle(mVec, swizzled, expectedString);
  }

  private void testSwizzle2(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof Vec2);
    testSwizzle(mVec, swizzled, expectedString);
  }

  private void testSwizzle3(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof Vec3);
    testSwizzle(mVec, swizzled, expectedString);
  }

  private void testSwizzle4(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof Vec4);
    testSwizzle(mVec, swizzled, expectedString);
  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleXYZW() {
    testSwizzle1(mVec.x().get(), "x");
    testSwizzle1(mVec.y().get(), "y");

    testSwizzle2(mVec.x().x().get(), "xx");
    testSwizzle2(mVec.x().y().get(), "xy");
    testSwizzle2(mVec.y().x().get(), "yx");
    testSwizzle2(mVec.y().y().get(), "yy");

    testSwizzle3(mVec.x().x().x().get(), "xxx");
    testSwizzle3(mVec.x().x().y().get(), "xxy");
    testSwizzle3(mVec.x().y().x().get(), "xyx");
    testSwizzle3(mVec.x().y().y().get(), "xyy");
    testSwizzle3(mVec.y().x().x().get(), "yxx");
    testSwizzle3(mVec.y().x().y().get(), "yxy");
    testSwizzle3(mVec.y().y().x().get(), "yyx");
    testSwizzle3(mVec.y().y().y().get(), "yyy");

    testSwizzle4(mVec.x().x().x().x(), "xxxx");
    testSwizzle4(mVec.x().x().x().y(), "xxxy");
    testSwizzle4(mVec.x().x().y().x(), "xxyx");
    testSwizzle4(mVec.x().x().y().y(), "xxyy");
    testSwizzle4(mVec.x().y().x().x(), "xyxx");
    testSwizzle4(mVec.x().y().x().y(), "xyxy");
    testSwizzle4(mVec.x().y().y().x(), "xyyx");
    testSwizzle4(mVec.x().y().y().y(), "xyyy");
    testSwizzle4(mVec.y().x().x().x(), "yxxx");
    testSwizzle4(mVec.y().x().x().y(), "yxxy");
    testSwizzle4(mVec.y().x().y().x(), "yxyx");
    testSwizzle4(mVec.y().x().y().y(), "yxyy");
    testSwizzle4(mVec.y().y().x().x(), "yyxx");
    testSwizzle4(mVec.y().y().x().y(), "yyxy");
    testSwizzle4(mVec.y().y().y().x(), "yyyx");
    testSwizzle4(mVec.y().y().y().y(), "yyyy");

  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleRGBA() {
    testSwizzle1(mVec.r().get(), "r");
    testSwizzle1(mVec.g().get(), "g");

    testSwizzle2(mVec.r().r().get(), "rr");
    testSwizzle2(mVec.r().g().get(), "rg");
    testSwizzle2(mVec.g().r().get(), "gr");
    testSwizzle2(mVec.g().g().get(), "gg");

    testSwizzle3(mVec.r().r().r().get(), "rrr");
    testSwizzle3(mVec.r().r().g().get(), "rrg");
    testSwizzle3(mVec.r().g().r().get(), "rgr");
    testSwizzle3(mVec.r().g().g().get(), "rgg");
    testSwizzle3(mVec.g().r().r().get(), "grr");
    testSwizzle3(mVec.g().r().g().get(), "grg");
    testSwizzle3(mVec.g().g().r().get(), "ggr");
    testSwizzle3(mVec.g().g().g().get(), "ggg");

    testSwizzle4(mVec.r().r().r().r(), "rrrr");
    testSwizzle4(mVec.r().r().r().g(), "rrrg");
    testSwizzle4(mVec.r().r().g().r(), "rrgr");
    testSwizzle4(mVec.r().r().g().g(), "rrgg");
    testSwizzle4(mVec.r().g().r().r(), "rgrr");
    testSwizzle4(mVec.r().g().r().g(), "rgrg");
    testSwizzle4(mVec.r().g().g().r(), "rggr");
    testSwizzle4(mVec.r().g().g().g(), "rggg");
    testSwizzle4(mVec.g().r().r().r(), "grrr");
    testSwizzle4(mVec.g().r().r().g(), "grrg");
    testSwizzle4(mVec.g().r().g().r(), "grgr");
    testSwizzle4(mVec.g().r().g().g(), "grgg");
    testSwizzle4(mVec.g().g().r().r(), "ggrr");
    testSwizzle4(mVec.g().g().r().g(), "ggrg");
    testSwizzle4(mVec.g().g().g().r(), "gggr");
    testSwizzle4(mVec.g().g().g().g(), "gggg");

  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleSTPQ() {
    testSwizzle1(mVec.s().get(), "s");
    testSwizzle1(mVec.t().get(), "t");

    testSwizzle2(mVec.s().s().get(), "ss");
    testSwizzle2(mVec.s().t().get(), "st");
    testSwizzle2(mVec.t().s().get(), "ts");
    testSwizzle2(mVec.t().t().get(), "tt");

    testSwizzle3(mVec.s().s().s().get(), "sss");
    testSwizzle3(mVec.s().s().t().get(), "sst");
    testSwizzle3(mVec.s().t().s().get(), "sts");
    testSwizzle3(mVec.s().t().t().get(), "stt");
    testSwizzle3(mVec.t().s().s().get(), "tss");
    testSwizzle3(mVec.t().s().t().get(), "tst");
    testSwizzle3(mVec.t().t().s().get(), "tts");
    testSwizzle3(mVec.t().t().t().get(), "ttt");

    testSwizzle4(mVec.s().s().s().s(), "ssss");
    testSwizzle4(mVec.s().s().s().t(), "ssst");
    testSwizzle4(mVec.s().s().t().s(), "ssts");
    testSwizzle4(mVec.s().s().t().t(), "sstt");
    testSwizzle4(mVec.s().t().s().s(), "stss");
    testSwizzle4(mVec.s().t().s().t(), "stst");
    testSwizzle4(mVec.s().t().t().s(), "stts");
    testSwizzle4(mVec.s().t().t().t(), "sttt");
    testSwizzle4(mVec.t().s().s().s(), "tsss");
    testSwizzle4(mVec.t().s().s().t(), "tsst");
    testSwizzle4(mVec.t().s().t().s(), "tsts");
    testSwizzle4(mVec.t().s().t().t(), "tstt");
    testSwizzle4(mVec.t().t().s().s(), "ttss");
    testSwizzle4(mVec.t().t().s().t(), "ttst");
    testSwizzle4(mVec.t().t().t().s(), "ttts");
    testSwizzle4(mVec.t().t().t().t(), "tttt");

  }

  @Test
  public void testAdd() {
    Vec2 vec = mVec.plus(1);
    Real real = new Real(1);
    Vec2 rhs = new Vec2(1, 2);

    testArithmetic(mVec, vec, mVec.plus(real), real, mVec.plus(rhs), rhs, NodeType.ADD);
  }

  @Test
  public void testSub() {
    Vec2 vec = mVec.minus(1);
    Real real = new Real(1);
    Vec2 rhs = new Vec2(1, 2);

    testArithmetic(mVec, vec, mVec.minus(real), real, mVec.minus(rhs), rhs, NodeType.SUB);
  }

  @Test
  public void testMul() {
    Vec2 vec = mVec.times(1);
    Real real = new Real(1);
    Vec2 rhs = new Vec2(1, 2);

    testArithmetic(mVec, vec, mVec.times(real), real, mVec.times(rhs), rhs, NodeType.MUL);
  }

  @Test
  public void testDiv() {
    Vec2 vec = mVec.div(1);
    Real real = new Real(1);
    Vec2 rhs = new Vec2(1, 2);

    testArithmetic(mVec, vec, mVec.div(real), real, mVec.div(rhs), rhs, NodeType.DIV);
  }

  @Test
  public void testNeg() {
    Vec2 neg = mVec.negative();

    assertEquals(neg.getNodeType(), NodeType.NEG);
    assertEquals(neg.getParents(), ImmutableList.<Expression>of(mVec));
  }

  @Test
  public void testDot() {
    Vec2 vec = new Vec2(1, 2);
    Real dot = mVec.dot(vec);

    testFunction(dot, "dot");
    assertEquals(dot.getParents(), ImmutableList.<Expression>of(mVec, vec));
  }

  @Test
  public void testNormalize() {
    Vec2 normalized = mVec.normalize();

    testFunction(normalized, "normalize");
    assertEquals(normalized.getParents(), ImmutableList.<Expression>of(mVec));
  }

  @Test
  public void testLength() {
    Real length = mVec.length();

    testFunction(length, "length");
    assertEquals(length.getParents(), ImmutableList.<Expression>of(mVec));
  }

  @Test
  public void testIsLessThan() {
    Vec2 rhs = new Vec2(1, 2);
    BVec2 lessThan = mVec.isLessThan(rhs);

    testFunction(lessThan, "lessThan");
    assertEquals(lessThan.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsLessThanOrEqual() {
    Vec2 rhs = new Vec2(1, 2);
    BVec2 lessThanOrEqual = mVec.isLessThanOrEqual(rhs);

    testFunction(lessThanOrEqual, "lessThanEqual");
    assertEquals(lessThanOrEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsGreaterThan() {
    Vec2 rhs = new Vec2(1, 2);
    BVec2 greaterThan = mVec.isGreaterThan(rhs);

    testFunction(greaterThan, "greaterThan");
    assertEquals(greaterThan.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsGreaterThanOrEqual() {
    Vec2 rhs = new Vec2(1, 2);
    BVec2 greaterThanOrEqual = mVec.isGreaterThanOrEqual(rhs);

    testFunction(greaterThanOrEqual, "greaterThanEqual");
    assertEquals(greaterThanOrEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsEqualComponentwise() {
    Vec2 rhs = new Vec2(1, 2);
    BVec2 equalComponentwise = mVec.isEqualComponentwise(rhs);

    testFunction(equalComponentwise, "equal");
    assertEquals(equalComponentwise.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsNotEqualComponentwise() {
    Vec2 rhs = new Vec2(1, 2);
    BVec2 notEqualComponentwise = mVec.isNotEqualComponentwise(rhs);

    testFunction(notEqualComponentwise, "notEqual");
    assertEquals(notEqualComponentwise.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsEqual() {
    Vec2 rhs = new Vec2(1, 2);
    Bool equal = mVec.isEqual(rhs);

    assertEquals(equal.getNodeType(), NodeType.EQ);
    assertEquals(equal.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsNotEqual() {
    Vec2 rhs = new Vec2(1, 2);
    Bool notEqual = mVec.isNotEqual(rhs);

    assertEquals(notEqual.getNodeType(), NodeType.NEQ);
    assertEquals(notEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }
}