package com.lfscheidegger.jfacet.shade.expression;

import com.lfscheidegger.jfacet.shade.Shade;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code FloatExp}
 */
public class FloatExpTest {

  @Test
  public void testLeftOnly() {
    assertTrue(Shade.add(1f, Shade.constant(2f)).evaluate() == 3f);
    assertTrue(Shade.sub(1f, Shade.constant(2f)).evaluate() == -1f);
    assertTrue(Shade.mul(1f, Shade.constant(2f)).evaluate() == 2f);
    assertTrue(Shade.div(1f, Shade.constant(2f)).evaluate() == 0.5f);
  }

  @Test
  public void testRightOnly() {
    assertTrue(Shade.add(Shade.constant(1f), 2f).evaluate() == 3f);
    assertTrue(Shade.sub(Shade.constant(1f), 2f).evaluate() == -1f);
    assertTrue(Shade.mul(Shade.constant(1f), 2f).evaluate() == 2f);
    assertTrue(Shade.div(Shade.constant(1f), 2f).evaluate() == 0.5f);
  }

  @Test
  public void testLeftAndRight() {
    assertTrue(Shade.add(1f, 2f).evaluate() == 3f);
    assertTrue(Shade.sub(1f, 2f).evaluate() == -1f);
    assertTrue(Shade.mul(1f, 2f).evaluate() == 2f);
    assertTrue(Shade.div(1f, 2f).evaluate() == 0.5f);
  }
}
