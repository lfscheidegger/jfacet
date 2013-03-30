package com.lfscheidegger.jfacet.shade.expression;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Vec4Exp}
 */
public class Vec4ExpTest {

  @Test
  public void testConstructors() {
    assertEquals(Shade.vec(1, 2, 3, 4).evaluate(), new Vec4(1, 2, 3, 4));
    assertEquals(Shade.vec(new FloatExp(1), 2, 3, 4).evaluate(), new Vec4(1, 2, 3, 4));
    assertEquals(Shade.vec(1, new FloatExp(2), 3, 4).evaluate(), new Vec4(1, 2, 3, 4));
    assertEquals(Shade.vec(new FloatExp(1), new FloatExp(2), 3, 4).evaluate(), new Vec4(1, 2, 3, 4));
    assertEquals(Shade.vec(1, 2, new FloatExp(3), 4).evaluate(), new Vec4(1, 2, 3, 4));
    assertEquals(Shade.vec(new FloatExp(1), 2, new FloatExp(3), 4).evaluate(), new Vec4(1, 2, 3, 4));
    assertEquals(Shade.vec(1, new FloatExp(2), new FloatExp(3), 4).evaluate(), new Vec4(1, 2, 3, 4));
    assertEquals(Shade.vec(new FloatExp(1), new FloatExp(2), new FloatExp(3), 4).evaluate(), new Vec4(1, 2, 3, 4));
    assertEquals(Shade.vec(1, 2, 3, new FloatExp(4)).evaluate(), new Vec4(1, 2, 3, 4));
    assertEquals(Shade.vec(new FloatExp(1), 2, 3, new FloatExp(4)).evaluate(), new Vec4(1, 2, 3, 4));
    assertEquals(Shade.vec(1, new FloatExp(2), 3, new FloatExp(4)).evaluate(), new Vec4(1, 2, 3, 4));
    assertEquals(Shade.vec(new FloatExp(1), new FloatExp(2), 3, new FloatExp(4)).evaluate(), new Vec4(1, 2, 3, 4));
    assertEquals(Shade.vec(1, 2, new FloatExp(3), new FloatExp(4)).evaluate(), new Vec4(1, 2, 3, 4));
    assertEquals(Shade.vec(new FloatExp(1), 2, new FloatExp(3), new FloatExp(4)).evaluate(), new Vec4(1, 2, 3, 4));
    assertEquals(Shade.vec(1, new FloatExp(2), new FloatExp(3), new FloatExp(4)).evaluate(), new Vec4(1, 2, 3, 4));
    assertEquals(Shade.vec(new FloatExp(1), new FloatExp(2), new FloatExp(3), new FloatExp(4)).evaluate(), new Vec4(1, 2, 3, 4));
  }

  @Test
  public void testAddition() {
    assertEquals(Shade.add(new Vec4(1, 2, 3, 4), 3).evaluate(), new Vec4(4, 5, 6, 7));
    assertEquals(Shade.add(Shade.vec(1, 2, 3, 4), 3).evaluate(), new Vec4(4, 5, 6, 7));
    assertEquals(Shade.add(new Vec4(1, 2, 3, 4), Shade.constant(3)).evaluate(), new Vec4(4, 5, 6, 7));
    assertEquals(Shade.add(Shade.vec(1, 2, 3, 4), Shade.constant(3)).evaluate(), new Vec4(4, 5, 6, 7));

    assertEquals(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(1, 2, 3, 4)).evaluate(), new Vec4(2, 4, 6, 8));
  }

  @Test
  public void testSubtraction() {
    assertEquals(Shade.sub(new Vec4(1, 2, 3, 4), 3).evaluate(), new Vec4(-2, -1, 0, 1));
    assertEquals(Shade.sub(Shade.vec(1, 2, 3, 4), 3).evaluate(), new Vec4(-2, -1, 0, 1));
    assertEquals(Shade.sub(new Vec4(1, 2, 3, 4), Shade.constant(3)).evaluate(), new Vec4(-2, -1, 0, 1));
    assertEquals(Shade.sub(Shade.vec(1, 2, 3, 4), Shade.constant(3)).evaluate(), new Vec4(-2, -1, 0, 1));

    assertEquals(Shade.sub(Shade.vec(1, 2, 3, 4), Shade.vec(1, 2, 3, 4)).evaluate(), new Vec4(0, 0, 0, 0));
  }

  @Test
  public void testMultiplication() {
    assertEquals(Shade.mul(new Vec4(1, 2, 3, 4), 3).evaluate(), new Vec4(3, 6, 9, 12));
    assertEquals(Shade.mul(Shade.vec(1, 2, 3, 4), 3).evaluate(), new Vec4(3, 6, 9, 12));
    assertEquals(Shade.mul(new Vec4(1, 2, 3, 4), Shade.constant(3)).evaluate(), new Vec4(3, 6, 9, 12));
    assertEquals(Shade.mul(Shade.vec(1, 2, 3, 4), Shade.constant(3)).evaluate(), new Vec4(3, 6, 9, 12));

    assertEquals(Shade.mul(Shade.vec(1, 2, 3, 4), Shade.vec(1, 2, 3, 4)).evaluate(), new Vec4(1, 4, 9, 16));
  }

  @Test
  public void testDivision() {
    assertEquals(Shade.div(new Vec4(1, 2, 3, 4), 2).evaluate(), new Vec4(0.5f, 1, 1.5f, 2));
    assertEquals(Shade.div(Shade.vec(1, 2, 3, 4), 2).evaluate(), new Vec4(0.5f, 1, 1.5f, 2));
    assertEquals(Shade.div(new Vec4(1, 2, 3, 4), Shade.constant(2)).evaluate(), new Vec4(0.5f, 1, 1.5f, 2));
    assertEquals(Shade.div(Shade.vec(1, 2, 3, 4), Shade.constant(2)).evaluate(), new Vec4(0.5f, 1, 1.5f, 2));

    assertEquals(Shade.div(Shade.vec(1, 2, 3, 4), Shade.vec(1, 2, 3, 4)).evaluate(), new Vec4(1, 1, 1, 1));
  }
}
