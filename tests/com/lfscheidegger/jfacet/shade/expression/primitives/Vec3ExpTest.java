package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Vec3Exp}
 */
public class Vec3ExpTest {

  @Test
  public void testConstructors() {
    assertEquals(Shade.vec(1, 2, 3).evaluate(), new Vec3(1, 2, 3));
    assertEquals(Shade.vec(new FloatExp(1), 2, 3).evaluate(), new Vec3(1, 2, 3));
    assertEquals(Shade.vec(1, new FloatExp(2), 3).evaluate(), new Vec3(1, 2, 3));
    assertEquals(Shade.vec(new FloatExp(1), new FloatExp(2), 3).evaluate(), new Vec3(1, 2, 3));
    assertEquals(Shade.vec(1, 2, new FloatExp(3)).evaluate(), new Vec3(1, 2, 3));
    assertEquals(Shade.vec(new FloatExp(1), 2, new FloatExp(3)).evaluate(), new Vec3(1, 2, 3));
    assertEquals(Shade.vec(1, new FloatExp(2), new FloatExp(3)).evaluate(), new Vec3(1, 2, 3));
    assertEquals(Shade.vec(new FloatExp(1), new FloatExp(2), new FloatExp(3)).evaluate(), new Vec3(1, 2, 3));
  }

  @Test
  public void testAddition() {
    assertEquals(Shade.add(new Vec3(1, 2, 3), 3).evaluate(), new Vec3(4, 5, 6));
    assertEquals(Shade.add(Shade.vec(1, 2, 3), 3).evaluate(), new Vec3(4, 5, 6));
    assertEquals(Shade.add(new Vec3(1, 2, 3), Shade.constant(3)).evaluate(), new Vec3(4, 5, 6));
    assertEquals(Shade.add(Shade.vec(1, 2, 3), Shade.constant(3)).evaluate(), new Vec3(4, 5, 6));

    assertEquals(Shade.add(Shade.vec(1, 2, 3), Shade.vec(1, 2, 3)).evaluate(), new Vec3(2, 4, 6));
  }

  @Test
  public void testSubtraction() {
    assertEquals(Shade.sub(new Vec3(1, 2, 3), 3).evaluate(), new Vec3(-2, -1, 0));
    assertEquals(Shade.sub(Shade.vec(1, 2, 3), 3).evaluate(), new Vec3(-2, -1, 0));
    assertEquals(Shade.sub(new Vec3(1, 2, 3), Shade.constant(3)).evaluate(), new Vec3(-2, -1, 0));
    assertEquals(Shade.sub(Shade.vec(1, 2, 3), Shade.constant(3)).evaluate(), new Vec3(-2, -1, 0));

    assertEquals(Shade.sub(Shade.vec(1, 2,3 ), Shade.vec(1, 2, 3)).evaluate(), new Vec3(0, 0, 0));
  }

  @Test
  public void testMultiplication() {
    assertEquals(Shade.mul(new Vec3(1, 2, 3), 3).evaluate(), new Vec3(3, 6, 9));
    assertEquals(Shade.mul(Shade.vec(1, 2, 3), 3).evaluate(), new Vec3(3, 6, 9));
    assertEquals(Shade.mul(new Vec3(1, 2, 3), Shade.constant(3)).evaluate(), new Vec3(3, 6, 9));
    assertEquals(Shade.mul(Shade.vec(1, 2, 3), Shade.constant(3)).evaluate(), new Vec3(3, 6, 9));

    assertEquals(Shade.mul(Shade.vec(1, 2, 3), Shade.vec(1, 2, 3)).evaluate(), new Vec3(1, 4, 9));
  }

  @Test
  public void testDivision() {
    assertEquals(Shade.div(new Vec3(1, 2, 3), 2).evaluate(), new Vec3(0.5f, 1, 1.5f));
    assertEquals(Shade.div(Shade.vec(1, 2, 3), 2).evaluate(), new Vec3(0.5f, 1, 1.5f));
    assertEquals(Shade.div(new Vec3(1, 2, 3), Shade.constant(2)).evaluate(), new Vec3(0.5f, 1, 1.5f));
    assertEquals(Shade.div(Shade.vec(1, 2, 3), Shade.constant(2)).evaluate(), new Vec3(0.5f, 1, 1.5f));

    assertEquals(Shade.div(Shade.vec(1, 2, 3), Shade.vec(1, 2, 3)).evaluate(), new Vec3(1, 1, 1));
  }
}
