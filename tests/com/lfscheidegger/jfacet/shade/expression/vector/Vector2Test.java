package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.facet.AttribBuffer;
import com.lfscheidegger.jfacet.shade.GlSlQualifier;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.Real;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.lfscheidegger.jfacet.shade.expression.ExpressionTestUtils.*;

/**
 * Unit tests for {@code Vector2}
 */
public class Vector2Test {

  private final Vector2 mVec = new Vector2(1, 2);

  @Test
  public void testConstructors() {
    Vector2 vec = new Vector2(1, 2);

    assertTrue(vec.getPrimitive().isPresent());
    assertEquals(vec.getPrimitive().get(), new Vector2.Primitive(1, 2));
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertFalse(vec.getNodeType().isPresent());
    assertEquals(vec.getParents(), ImmutableList.of());

    Real x = new Real(1), y = new Real(2);
    vec = new Vector2(x, y);

    assertFalse(vec.getPrimitive().isPresent());
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertTrue(vec.getNodeType().isPresent());
    assertEquals(vec.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(vec.getParents(), ImmutableList.of(x, y));

    vec = new Vector2(ImmutableList.<Expression>of(x, y), Expression.NodeType.CONS);

    assertFalse(vec.getPrimitive().isPresent());
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertTrue(vec.getNodeType().isPresent());
    assertEquals(vec.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(vec.getParents(), ImmutableList.of(x, y));

    vec = new Vector2(new AttribBuffer(new float[] {0, 0, 1, 0, 1, 1}, 2));

    assertFalse(vec.getPrimitive().isPresent());
    assertTrue(vec.getAttribBuffer().isPresent());
    assertTrue(vec.getGlSlQualifier().isPresent());
    assertEquals(vec.getGlSlQualifier().get(), GlSlQualifier.ATTRIBUTE_T);
    assertFalse(vec.getNodeType().isPresent());
    assertEquals(vec.getParents(), ImmutableList.<Expression>of());
  }

  private void testGetter(
      Real component,
      int expectedComponent,
      ImmutableList<Expression> expectedParents) {

    assertFalse(component.getPrimitive().isPresent());
    assertFalse(component.getAttribBuffer().isPresent());
    assertFalse(component.getGlSlQualifier().isPresent());
    assertTrue(component.getNodeType().isPresent());
    assertTrue(component.getNodeType().get() instanceof Expression.NodeType.ComponentNodeType);

    assertEquals(((Expression.NodeType.ComponentNodeType)
        component.getNodeType().get()).getComponent(), expectedComponent);
    assertEquals(component.getParents(), expectedParents);
  }

  @Test
  public void testGetters() {
    Vector2 vec = new Vector2(1, 2);
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
    testSwizzle(swizzled, expectedString);
  }

  private void testSwizzle2(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof Vector2);
    testSwizzle(swizzled, expectedString);
  }

  private void testSwizzle3(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof Vector3);
    testSwizzle(swizzled, expectedString);
  }

  private void testSwizzle4(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof Vector4);
    testSwizzle(swizzled, expectedString);
  }

  private void testSwizzle(Expression swizzled, String expectedString) {
    testNonLeafExpression(swizzled);
    assertEquals(swizzled.getParents(), ImmutableList.<Expression>of(mVec));
    assertTrue(swizzled.getNodeType().get() instanceof Expression.NodeType.SwizzleNodeType);
    Expression.NodeType.SwizzleNodeType nodeType = (Expression.NodeType.SwizzleNodeType)
        swizzled.getNodeType().get();
    assertEquals(nodeType.getSwizzleString(), expectedString);
  }

  private void testNonLeafVector(Vector2 vec) {
    assertFalse(vec.getPrimitive().isPresent());
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertTrue(vec.getNodeType().isPresent());
  }

  private void testArithmetic(
      Vector2 vecWithFloat,
      Vector2 vecWithReal,
      Real real,
      Vector2 vecWithVec,
      Vector2 rhs,
      Expression.NodeType nodeType) {
    testNonLeafVector(vecWithFloat);
    assertEquals(vecWithFloat.getNodeType().get(), nodeType);
    assertEquals(vecWithFloat.getParents().size(), 2);
    assertEquals(vecWithFloat.getParents().get(0), mVec);

    testNonLeafVector(vecWithReal);
    assertEquals(vecWithReal.getNodeType().get(), nodeType);
    assertEquals(vecWithReal.getParents(), ImmutableList.<Expression>of(mVec, real));

    testNonLeafVector(vecWithVec);
    assertEquals(vecWithVec.getNodeType().get(), nodeType);
    assertEquals(vecWithVec.getParents(), ImmutableList.<Expression>of(mVec, rhs));
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
    Vector2 vec = mVec.add(1);
    Real real = new Real(1);
    Vector2 rhs = new Vector2(1, 2);

    testArithmetic(vec, mVec.add(real), real, mVec.add(rhs), rhs, Expression.NodeType.ADD);
  }

  @Test
  public void testSub() {
    Vector2 vec = mVec.sub(1);
    Real real = new Real(1);
    Vector2 rhs = new Vector2(1, 2);

    testArithmetic(vec, mVec.sub(real), real, mVec.sub(rhs), rhs, Expression.NodeType.SUB);
  }

  @Test
  public void testMul() {
    Vector2 vec = mVec.mul(1);
    Real real = new Real(1);
    Vector2 rhs = new Vector2(1, 2);

    testArithmetic(vec, mVec.mul(real), real, mVec.mul(rhs), rhs, Expression.NodeType.MUL);
  }

  @Test
  public void testDiv() {
    Vector2 vec = mVec.div(1);
    Real real = new Real(1);
    Vector2 rhs = new Vector2(1, 2);

    testArithmetic(vec, mVec.div(real), real, mVec.div(rhs), rhs, Expression.NodeType.DIV);
  }

  @Test
  public void testNeg() {
    Vector2 neg = mVec.neg();

    testNonLeafVector(neg);
    assertEquals(neg.getNodeType().get(), Expression.NodeType.NEG);
    assertEquals(neg.getParents(), ImmutableList.<Expression>of(mVec));
  }

  private void testFunction(
      Expression expression,
      String functionName) {
    assertTrue(expression.getNodeType().isPresent());
    assertTrue(expression.getNodeType().get() instanceof Expression.NodeType.FunctionNodeType);

    Expression.NodeType.FunctionNodeType nodeType =
        (Expression.NodeType.FunctionNodeType)expression.getNodeType().get();
    assertEquals(nodeType.getFunctionName(), functionName);
  }

  @Test
  public void testDot() {
    Vector2 vec = new Vector2(1, 2);
    Real dot = mVec.dot(vec);

    testNonLeafExpression(dot);
    testFunction(dot, "dot");
    assertEquals(dot.getParents(), ImmutableList.<Expression>of(mVec, vec));
  }

  @Test
  public void testNormalize() {
    Vector2 normalized = mVec.normalize();

    testNonLeafVector(normalized);
    testFunction(normalized, "normalize");
    assertEquals(normalized.getParents(), ImmutableList.<Expression>of(mVec));
  }

  @Test
  public void testLength() {
    Real length = mVec.length();

    testNonLeafExpression(length);
    testFunction(length, "length");
    assertEquals(length.getParents(), ImmutableList.<Expression>of(mVec));
  }

  @Test
  public void testIsLessThan() {
    Vector2 rhs = new Vector2(1, 2);
    BVector2 lessThan = mVec.isLessThan(rhs);

    testNonLeafExpression(lessThan);
    testFunction(lessThan, "lessThan");
    assertEquals(lessThan.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsLessThanOrEqual() {
    Vector2 rhs = new Vector2(1, 2);
    BVector2 lessThanOrEqual = mVec.isLessThanOrEqual(rhs);

    testNonLeafExpression(lessThanOrEqual);
    testFunction(lessThanOrEqual, "lessThanEqual");
    assertEquals(lessThanOrEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsGreaterThan() {
    Vector2 rhs = new Vector2(1, 2);
    BVector2 greaterThan = mVec.isGreaterThan(rhs);

    testNonLeafExpression(greaterThan);
    testFunction(greaterThan, "greaterThan");
    assertEquals(greaterThan.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsGreaterThanOrEqual() {
    Vector2 rhs = new Vector2(1, 2);
    BVector2 greaterThanOrEqual = mVec.isGreaterThanOrEqual(rhs);

    testNonLeafExpression(greaterThanOrEqual);
    testFunction(greaterThanOrEqual, "greaterThanEqual");
    assertEquals(greaterThanOrEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsEqualComponentwise() {
    Vector2 rhs = new Vector2(1, 2);
    BVector2 equalComponentwise = mVec.isEqualComponentwise(rhs);

    testNonLeafExpression(equalComponentwise);
    testFunction(equalComponentwise, "equal");
    assertEquals(equalComponentwise.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsNotEqualComponentwise() {
    Vector2 rhs = new Vector2(1, 2);
    BVector2 notEqualComponentwise = mVec.isNotEqualComponentwise(rhs);

    testNonLeafExpression(notEqualComponentwise);
    testFunction(notEqualComponentwise, "notEqual");
    assertEquals(notEqualComponentwise.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsEqual() {
    Vector2 rhs = new Vector2(1, 2);
    Bool equal = mVec.isEqual(rhs);

    testNonLeafExpression(equal);
    assertTrue(equal.getNodeType().isPresent());
    assertEquals(equal.getNodeType().get(), Expression.NodeType.EQ);
    assertEquals(equal.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsNotEqual() {
    Vector2 rhs = new Vector2(1, 2);
    Bool notEqual = mVec.isNotEqual(rhs);

    testNonLeafExpression(notEqual);
    assertTrue(notEqual.getNodeType().isPresent());
    assertEquals(notEqual.getNodeType().get(), Expression.NodeType.NEQ);
    assertEquals(notEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testFill() {
    Vector4 defaultValues = new Vector4(1, 2, 3, 4);
    Vector4 fill = mVec.fill(defaultValues);

    testNonLeafExpression(fill);
    assertEquals(fill.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(fill.getParents().size(), 4);
  }
}