package com.lfscheidegger.jfacet.shade.expression;

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
}
