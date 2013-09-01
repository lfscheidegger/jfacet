package com.lfscheidegger.jfacet.shade.expression;

import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Bool}
 */
public class BoolTest {

  private final Bool val;

  public BoolTest() {
    val = new Bool(true);
  }

  @Test
  public void testAnd() {
    Bool and = val.and(new Bool(false));
    assertEquals(and.getParents().size(), 2);
    assertSame(and.getParents().get(0), val);
    assertEquals(and.getParents().get(1).evaluate(), false);
    assertFalse(and.evaluate());

    and = val.and(false);
    assertEquals(and.getParents().size(), 2);
    assertSame(and.getParents().get(0), val);
    assertEquals(and.getParents().get(1).evaluate(), false);
    assertFalse(and.evaluate());

    and = val.and(true);
    assertTrue(and.evaluate());
  }

  @Test
  public void testOr() {
    Bool or = val.or(new Bool(false));
    assertEquals(or.getParents().size(), 2);
    assertSame(or.getParents().get(0), val);
    assertEquals(or.getParents().get(1).evaluate(), false);
    assertTrue(or.evaluate());

    or = val.or(false);
    assertEquals(or.getParents().size(), 2);
    assertSame(or.getParents().get(0), val);
    assertEquals(or.getParents().get(1).evaluate(), false);
    assertTrue(or.evaluate());

    assertFalse(new Bool(false).or(false).evaluate());
  }

  @Test
  public void testXor() {
    Bool xor = val.xor(new Bool(true));
    assertEquals(xor.getParents().size(), 2);
    assertSame(xor.getParents().get(0), val);
    assertEquals(xor.getParents().get(1).evaluate(), true);
    assertFalse(xor.evaluate());

    xor = val.xor(true);
    assertEquals(xor.getParents().size(), 2);
    assertSame(xor.getParents().get(0), val);
    assertEquals(xor.getParents().get(1).evaluate(), true);
    assertFalse(xor.evaluate());

    xor = val.xor(false);
    assertTrue(xor.evaluate());
  }

  @Test
  public void testTernaryOperator() {
    Vector2 ifExpression = new Vector2(1, 2);
    Vector2 elseExpression = new Vector2(2, 3);

    Vector2 ternaryExpression = val.if_(ifExpression).else_(elseExpression);
    assertEquals(ternaryExpression.getParents().size(), 3);
    assertSame(ternaryExpression.getParents().get(0), val);
    assertSame(ternaryExpression.getParents().get(1), ifExpression);
    assertSame(ternaryExpression.getParents().get(2), elseExpression);

    assertEquals(ternaryExpression.evaluate(), ifExpression.evaluate());
  }
}
