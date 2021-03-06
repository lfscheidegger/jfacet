// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static com.lfscheidegger.jfacet.shade.expression.ExpressionTestUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@code BVec2}
 */
public class BVec2Test {

  private final BVec2 mVec = new BVec2(true, false);

  @Test
  public void testConstructors() {
    assertEquals(
        ((NodeType.PrimitiveNodeType) mVec.getNodeType()).getPrimitive(),
        new BVec2.Primitive(true, false));

    assertEquals(mVec.getParents(), ImmutableList.of());

    Bool x = new Bool(true), y = new Bool(false);
    BVec2 vec = new BVec2(x, y);
    testNonLeafExpression(vec, ImmutableList.<Expression>of(x, y));
    assertEquals(vec.getNodeType(), NodeType.CONS);

    vec = new BVec2(ImmutableList.<Expression>of(x, y), NodeType.CONS);
    testNonLeafExpression(vec, ImmutableList.<Expression>of(x, y));
    assertEquals(vec.getNodeType(), NodeType.CONS);
  }

  @Test
  public void testGetters() {
    testGetter(mVec.getX(), 0, ImmutableList.<Expression>of(mVec));
    testGetter(mVec.getY(), 1, ImmutableList.<Expression>of(mVec));
    testGetter(mVec.get(0), 0, ImmutableList.<Expression>of(mVec));
    testGetter(mVec.get(1), 1, ImmutableList.<Expression>of(mVec));
  }

  private void testSwizzle1(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof Bool);
    testSwizzle(mVec, swizzled, expectedString);
  }

  private void testSwizzle2(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof BVec2);
    testSwizzle(mVec, swizzled, expectedString);
  }

  private void testSwizzle3(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof BVec3);
    testSwizzle(mVec, swizzled, expectedString);
  }

  private void testSwizzle4(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof BVec4);
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
  public void testAny() {
    Bool any = mVec.any();
    testNonLeafExpression(any, ImmutableList.<Expression>of(mVec));
    assertTrue(any.getNodeType() instanceof NodeType.FunctionNodeType);
    NodeType.FunctionNodeType nodeType =
        (NodeType.FunctionNodeType)any.getNodeType();
    assertEquals(nodeType.getFunctionName(), "any");
  }

  @Test
  public void testAll() {
    Bool all = mVec.all();
    testNonLeafExpression(all, ImmutableList.<Expression>of(mVec));
    assertTrue(all.getNodeType() instanceof NodeType.FunctionNodeType);
    NodeType.FunctionNodeType nodeType =
        (NodeType.FunctionNodeType)all.getNodeType();
    assertEquals(nodeType.getFunctionName(), "all");
  }

  @Test
  public void testNot() {
    BVec2 not = mVec.not();
    testNonLeafExpression(not, ImmutableList.<Expression>of(mVec));
    assertTrue(not.getNodeType() instanceof NodeType.FunctionNodeType);
    NodeType.FunctionNodeType nodeType =
        (NodeType.FunctionNodeType)not.getNodeType();
    assertEquals(nodeType.getFunctionName(), "not");
  }
}
