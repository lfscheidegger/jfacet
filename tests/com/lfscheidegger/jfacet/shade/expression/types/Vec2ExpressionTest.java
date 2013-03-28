package com.lfscheidegger.jfacet.shade.expression.types;

import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.types.Vec2;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Vec2Expression}
 */
public class Vec2ExpressionTest {

  FloatExpression x = new FloatExpression(1);
  FloatExpression y = new FloatExpression(2);

  Vec2Expression xy = new Vec2Expression(new Vec2(1, 2));

  @Test
  public void testConstructors() {
    assertEquals(xy.evaluate(), new Vec2(1, 2));

    Expression child = new Vec2Expression(xy);
    assertEquals(child.evaluate(), new Vec2(1, 2));

    child = new Vec2Expression(x, y);
    assertEquals(child.evaluate(), new Vec2(1, 2));
  }

  private void assertGetters(Vec2Expression xy) {
    assertTrue(xy.getX().evaluate().equals(1f));
    assertTrue(xy.getY().evaluate().equals(2f));
    assertTrue(xy.get(0).evaluate().equals(1f));
    assertTrue(xy.get(1).evaluate().equals(2f));
  }

  @Test
  public void testGetters() {
    assertGetters(xy);

    Vec2Expression child = new Vec2Expression(xy);
    assertGetters(child);

    child = new Vec2Expression(x, y);
    assertGetters(child);
  }
}
