package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.primitives.Mat2;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Mat2Exp}
 */
public class Mat2ExpTest {

  @Test
  public void testConstructors() {
    assertEquals(Shade.mat(new Mat2()).evaluate(), new Mat2());
    assertEquals(Shade.mat(new Vec2Exp(new Vec2(1, 0)), new Vec2(0, 1)).evaluate(), new Mat2());
    assertEquals(Shade.mat(new Vec2(1, 0), new Vec2Exp(new Vec2(0, 1))).evaluate(), new Mat2());
    assertEquals(Shade.mat(new Vec2(1, 0), new Vec2(0, 1)).evaluate(), new Mat2());
    assertEquals(Shade.mat(new Vec2Exp(new Vec2(1, 0)), new Vec2Exp(new Vec2(0, 1))).evaluate(), new Mat2());
  }

  @Test
  public void testAddition() {
    assertEquals(Shade.add(new Mat2(), 3).evaluate(), new Mat2().add(3));
    assertEquals(Shade.add(Shade.mat(new Mat2()), 3).evaluate(), new Mat2().add(3));
    assertEquals(Shade.add(new Mat2(), Shade.constant(3)).evaluate(), new Mat2().add(3));
    assertEquals(Shade.add(Shade.mat(new Mat2()), Shade.constant(3)).evaluate(), new Mat2().add(3));

    assertEquals(Shade.add(Shade.mat(new Mat2()), Shade.mat(new Mat2())).evaluate(), new Mat2().add(new Mat2()));
  }

  @Test
  public void testSubtraction() {
    assertEquals(Shade.sub(new Mat2(), 3).evaluate(), new Mat2().sub(3));
    assertEquals(Shade.sub(Shade.mat(new Mat2()), 3).evaluate(), new Mat2().sub(3));
    assertEquals(Shade.sub(new Mat2(), Shade.constant(3)).evaluate(), new Mat2().sub(3));
    assertEquals(Shade.sub(Shade.mat(new Mat2()), Shade.constant(3)).evaluate(), new Mat2().sub(3));

    assertEquals(Shade.sub(Shade.mat(new Mat2()), Shade.mat(new Mat2())).evaluate(), new Mat2().sub(new Mat2()));
  }

  @Test
  public void testMultiplication() {
    assertEquals(Shade.mul(new Mat2(), 3).evaluate(), new Mat2().mul(3));
    assertEquals(Shade.mul(Shade.mat(new Mat2()), 3).evaluate(), new Mat2().mul(3));
    assertEquals(Shade.mul(new Mat2(), Shade.constant(3)).evaluate(), new Mat2().mul(3));
    assertEquals(Shade.mul(Shade.mat(new Mat2()), Shade.constant(3)).evaluate(), new Mat2().mul(3));

    assertEquals(Shade.mul(Shade.mat(new Mat2()), Shade.mat(new Mat2())).evaluate(), new Mat2().mul(new Mat2()));
  }

  @Test
  public void testDivision() {
    assertEquals(Shade.div(new Mat2(), 3).evaluate(), new Mat2().div(3));
    assertEquals(Shade.div(Shade.mat(new Mat2()), 3).evaluate(), new Mat2().div(3));
    assertEquals(Shade.div(new Mat2(), Shade.constant(3)).evaluate(), new Mat2().div(3));
    assertEquals(Shade.div(Shade.mat(new Mat2()), Shade.constant(3)).evaluate(), new Mat2().div(3));

    assertEquals(Shade.div(Shade.mat(new Mat2()), Shade.mat(new Mat2(new Vec2(1, 1), new Vec2(1, 1)))).evaluate(),
        new Mat2().div(new Mat2(new Vec2(1, 1), new Vec2(1, 1))));
  }
}
