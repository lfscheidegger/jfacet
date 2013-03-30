package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Vec2Exp}
 */
public class Vec2ExpTest {

  @Test
  public void testConstructors() {
    assertEquals(Shade.vec(1, 2).evaluate(), new Vec2(1, 2));
    assertEquals(Shade.vec(new FloatExp(1), 2).evaluate(), new Vec2(1, 2));
    assertEquals(Shade.vec(1, new FloatExp(2)).evaluate(), new Vec2(1, 2));
    assertEquals(Shade.vec(new FloatExp(1), new FloatExp(2)).evaluate(), new Vec2(1, 2));
  }

  @Test
  public void testAddition() {
    assertEquals(Shade.add(new Vec2(1, 2), 3).evaluate(), new Vec2(4, 5));
    assertEquals(Shade.add(Shade.vec(1, 2), 3).evaluate(), new Vec2(4, 5));
    assertEquals(Shade.add(new Vec2(1, 2), Shade.constant(3)).evaluate(), new Vec2(4, 5));
    assertEquals(Shade.add(Shade.vec(1, 2), Shade.constant(3)).evaluate(), new Vec2(4, 5));

    assertEquals(Shade.add(Shade.vec(1, 2), Shade.vec(1, 2)).evaluate(), new Vec2(2, 4));
  }

  @Test
  public void testSubtraction() {
    assertEquals(Shade.sub(new Vec2(1, 2), 3).evaluate(), new Vec2(-2, -1));
    assertEquals(Shade.sub(Shade.vec(1, 2), 3).evaluate(), new Vec2(-2, -1));
    assertEquals(Shade.sub(new Vec2(1, 2), Shade.constant(3)).evaluate(), new Vec2(-2, -1));
    assertEquals(Shade.sub(Shade.vec(1, 2), Shade.constant(3)).evaluate(), new Vec2(-2, -1));

    assertEquals(Shade.sub(Shade.vec(1, 2), Shade.vec(1, 2)).evaluate(), new Vec2(0, 0));
  }

  @Test
  public void testMultiplication() {
    assertEquals(Shade.mul(new Vec2(1, 2), 3).evaluate(), new Vec2(3, 6));
    assertEquals(Shade.mul(Shade.vec(1, 2), 3).evaluate(), new Vec2(3, 6));
    assertEquals(Shade.mul(new Vec2(1, 2), Shade.constant(3)).evaluate(), new Vec2(3, 6));
    assertEquals(Shade.mul(Shade.vec(1, 2), Shade.constant(3)).evaluate(), new Vec2(3, 6));

    assertEquals(Shade.mul(Shade.vec(1, 2), Shade.vec(1, 2)).evaluate(), new Vec2(1, 4));
  }

  @Test
  public void testDivision() {
    assertEquals(Shade.div(new Vec2(1, 2), 2).evaluate(), new Vec2(0.5f, 1));
    assertEquals(Shade.div(Shade.vec(1, 2), 2).evaluate(), new Vec2(0.5f, 1));
    assertEquals(Shade.div(new Vec2(1, 2), Shade.constant(2)).evaluate(), new Vec2(0.5f, 1));
    assertEquals(Shade.div(Shade.vec(1, 2), Shade.constant(2)).evaluate(), new Vec2(0.5f, 1));

    assertEquals(Shade.div(Shade.vec(1, 2), Shade.vec(1, 2)).evaluate(), new Vec2(1, 1));
  }

  @Test
  public void testForVec2Component() {
    assertTrue(Shade.add(Shade.vec(1, 2), Shade.vec(3, 4)).getX().evaluate() == 4);
    assertTrue(Shade.add(Shade.vec(1, 2), Shade.vec(3, 4)).get(0).evaluate() == 4);

    assertTrue(Shade.add(Shade.vec(1, 2), Shade.vec(3, 4)).getY().evaluate() == 6);
    assertTrue(Shade.add(Shade.vec(1, 2), Shade.vec(3, 4)).get(1).evaluate() == 6);
  }
}
