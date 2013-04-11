package com.lfscheidegger.jfacet.shade;

import com.lfscheidegger.jfacet.shade.expression.Vec4Exp;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for some of the static methods in {@code Shade}. We only test here methods
 * that aren't being indirectly tested in other places.
 */
public class TestShade {

  @Test
  public void testFill() {
    Vec4Exp exp = Shade.fill(Shade.constant(0), new Vec4(1, 2, 3, 4));
    assertEquals(exp.evaluate(), new Vec4(0, 2, 3, 4));

    exp = Shade.fill(Shade.vec(0, 1), new Vec4(1, 2, 3, 4));
    assertEquals(exp.evaluate(), new Vec4(0, 1, 3, 4));

    exp = Shade.fill(Shade.vec(0, 1, 2), new Vec4(1, 2, 3, 4));
    assertEquals(exp.evaluate(), new Vec4(0, 1, 2, 4));

    exp = Shade.fill(Shade.vec(0, 1, 2, 3), new Vec4(1, 2, 3, 4));
    assertEquals(exp.evaluate(), new Vec4(0, 1, 2, 3));
  }
}
