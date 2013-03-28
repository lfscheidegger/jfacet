package com.lfscheidegger.jfacet.shade.expression.types;

import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.types.Vec4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@code Vec2Expression}
 */
public class Vec4ExpressionTest {

  FloatExpression x = new FloatExpression(1);
  FloatExpression y = new FloatExpression(2);
  FloatExpression z = new FloatExpression(3);
  FloatExpression w = new FloatExpression(4);

  Vec4Expression xyzw = new Vec4Expression(new Vec4(1, 2, 3, 4));

  @Test
  public void testConstructors() {
    assertEquals(xyzw.evaluate(), new Vec4(1, 2, 3, 4));

    Expression child = new Vec4Expression(xyzw);
    assertEquals(child.evaluate(), new Vec4(1, 2, 3, 4));

    child = new Vec4Expression(x, y, z, w);
    assertEquals(child.evaluate(), new Vec4(1, 2, 3, 4));
  }

  private void assertGetters(Vec4Expression xyzw) {
    assertTrue(xyzw.getX().evaluate().equals(1f));
    assertTrue(xyzw.getY().evaluate().equals(2f));
    assertTrue(xyzw.getZ().evaluate().equals(3f));
    assertTrue(xyzw.getW().evaluate().equals(4f));
    assertTrue(xyzw.get(0).evaluate().equals(1f));
    assertTrue(xyzw.get(1).evaluate().equals(2f));
    assertTrue(xyzw.get(2).evaluate().equals(3f));
    assertTrue(xyzw.get(3).evaluate().equals(4f));
  }

  @Test
  public void testGetters() {
    assertGetters(xyzw);

    Vec4Expression child = new Vec4Expression(xyzw);
    assertGetters(child);

    child = new Vec4Expression(x, y, z, w);
    assertGetters(child);
  }
}
