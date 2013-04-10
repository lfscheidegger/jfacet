package com.lfscheidegger.jfacet.shade.compiler;

import com.lfscheidegger.jfacet.shade.Type;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code GlSlExpressionHelper}.
 */
public class GlSlExpressionHelperTest {

  @Test
  public void testWrappedExpression() {
    assertEquals(GlSlExpressionHelper.getWrappedExpression(Type.FLOAT_T, "test"), "float(test)");
  }

  @Test
  public void testCommaExpression() {
    assertEquals(
        GlSlExpressionHelper.getCommaExpression(Type.VEC2_T, "aTest", "anotherTest"),
        "vec2(aTest, anotherTest)");

    assertEquals(
        GlSlExpressionHelper.getCommaExpression(Type.VEC2_T, "aTest", "anotherTest", "andThird"),
        "vec2(aTest, anotherTest, andThird)");
  }

  @Test
  public void testBinOpExpression() {
    assertEquals(
        GlSlExpressionHelper.getBinOpExpression(Type.VEC2_T, "+", "aTest", "anotherTest"),
        "vec2(aTest + anotherTest)");
  }

  @Test
  public void testComponentExpression() {
    assertEquals(GlSlExpressionHelper.getComponentExpression(Type.FLOAT_T, "aTest", 0), "float(aTest[0])");
  }
}
