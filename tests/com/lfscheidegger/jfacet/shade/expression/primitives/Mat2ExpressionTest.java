package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.Mat2;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * Unit tests for {@code Mat2Expression}
 */
public class Mat2ExpressionTest {

  Vec2Expression c0 = new Vec2Expression(new Vec2(1, 0));
  Vec2Expression c1 = new Vec2Expression(new Vec2(0, 1));

  Mat2Expression c0c1 = new Mat2Expression(new Mat2(new Vec2(1, 0), new Vec2(0, 1)));

  @Test
  public void testConstructors() {
    assertEquals(c0c1.evaluate(), new Mat2(new Vec2(1, 0), new Vec2(0, 1)));

    Expression child = new Mat2Expression(c0c1);
    assertEquals(child.evaluate(), new Mat2(new Vec2(1, 0), new Vec2(0, 1)));
    assertEquals(child.getGlSlExpression(), "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))))");

    child = new Mat2Expression(c0, c1);
    assertEquals(child.evaluate(), new Mat2(new Vec2(1, 0), new Vec2(0, 1)));
    assertEquals(child.getGlSlExpression(), "mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0)))");
  }

  private void assertGetters(Mat2Expression c0c1) {
    assertTrue(c0c1.getC0().evaluate().equals(new Vec2(1, 0)));
    assertTrue(c0c1.getC1().evaluate().equals(new Vec2(0, 1)));
    assertTrue(c0c1.get(0).evaluate().equals(new Vec2(1, 0)));
    assertTrue(c0c1.get(1).evaluate().equals(new Vec2(0, 1)));
  }

  @Test
  public void testGetters() {
    assertGetters(c0c1);

    Mat2Expression child = new Mat2Expression(c0c1);
    assertGetters(child);

    child = new Mat2Expression(c0, c1);
    assertGetters(child);
  }
}
