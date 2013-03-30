package com.lfscheidegger.jfacet.shade.expression;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.primitives.Mat3;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Mat3Exp}
 */
public class Mat3ExpTest {

  @Test
  public void testConstructors() {
    assertEquals(Shade.mat(new Mat3()).evaluate(), new Mat3());
    assertEquals(Shade.mat(new Vec3Exp(new Vec3(1, 0, 0)), new Vec3(0, 1, 0), new Vec3Exp(new Vec3(0, 0, 1))).evaluate(), new Mat3());
    assertEquals(Shade.mat(new Vec3(1, 0, 0), new Vec3Exp(new Vec3(0, 1, 0)), new Vec3Exp(new Vec3(0, 0, 1))).evaluate(), new Mat3());
    assertEquals(Shade.mat(new Vec3(1, 0, 0), new Vec3(0, 1, 0), new Vec3Exp(new Vec3(0, 0, 1))).evaluate(), new Mat3());
    assertEquals(Shade.mat(new Vec3Exp(new Vec3(1, 0, 0)), new Vec3Exp(new Vec3(0, 1, 0)), new Vec3Exp(new Vec3(0, 0, 1))).evaluate(), new Mat3());
    assertEquals(Shade.mat(new Vec3Exp(new Vec3(1, 0, 0)), new Vec3(0, 1, 0), new Vec3(0, 0, 1)).evaluate(), new Mat3());
    assertEquals(Shade.mat(new Vec3(1, 0, 0), new Vec3Exp(new Vec3(0, 1, 0)), new Vec3(0, 0, 1)).evaluate(), new Mat3());
    assertEquals(Shade.mat(new Vec3(1, 0, 0), new Vec3(0, 1, 0), new Vec3(0, 0, 1)).evaluate(), new Mat3());
    assertEquals(Shade.mat(new Vec3Exp(new Vec3(1, 0, 0)), new Vec3Exp(new Vec3(0, 1, 0)), new Vec3(0, 0, 1)).evaluate(), new Mat3());
  }

  @Test
  public void testAddition() {
    assertEquals(Shade.add(new Mat3(), 3).evaluate(), new Mat3().add(3));
    assertEquals(Shade.add(Shade.mat(new Mat3()), 3).evaluate(), new Mat3().add(3));
    assertEquals(Shade.add(new Mat3(), Shade.constant(3)).evaluate(), new Mat3().add(3));
    assertEquals(Shade.add(Shade.mat(new Mat3()), Shade.constant(3)).evaluate(), new Mat3().add(3));

    assertEquals(Shade.add(Shade.mat(new Mat3()), Shade.mat(new Mat3())).evaluate(), new Mat3().add(new Mat3()));
  }

  @Test
  public void testSubtraction() {
    assertEquals(Shade.sub(new Mat3(), 3).evaluate(), new Mat3().sub(3));
    assertEquals(Shade.sub(Shade.mat(new Mat3()), 3).evaluate(), new Mat3().sub(3));
    assertEquals(Shade.sub(new Mat3(), Shade.constant(3)).evaluate(), new Mat3().sub(3));
    assertEquals(Shade.sub(Shade.mat(new Mat3()), Shade.constant(3)).evaluate(), new Mat3().sub(3));

    assertEquals(Shade.sub(Shade.mat(new Mat3()), Shade.mat(new Mat3())).evaluate(), new Mat3().sub(new Mat3()));
  }

  @Test
  public void testMultiplication() {
    assertEquals(Shade.mul(new Mat3(), 3).evaluate(), new Mat3().mul(3));
    assertEquals(Shade.mul(Shade.mat(new Mat3()), 3).evaluate(), new Mat3().mul(3));
    assertEquals(Shade.mul(new Mat3(), Shade.constant(3)).evaluate(), new Mat3().mul(3));
    assertEquals(Shade.mul(Shade.mat(new Mat3()), Shade.constant(3)).evaluate(), new Mat3().mul(3));

    assertEquals(Shade.mul(Shade.mat(new Mat3()), Shade.mat(new Mat3())).evaluate(), new Mat3().mul(new Mat3()));
  }

  @Test
  public void testDivision() {
    assertEquals(Shade.div(new Mat3(), 3).evaluate(), new Mat3().div(3));
    assertEquals(Shade.div(Shade.mat(new Mat3()), 3).evaluate(), new Mat3().div(3));
    assertEquals(Shade.div(new Mat3(), Shade.constant(3)).evaluate(), new Mat3().div(3));
    assertEquals(Shade.div(Shade.mat(new Mat3()), Shade.constant(3)).evaluate(), new Mat3().div(3));

    assertEquals(Shade.div(Shade.mat(new Mat3()),
        Shade.mat(new Mat3(new Vec3(1, 1, 1), new Vec3(1, 1, 1), new Vec3(1, 1, 1)))).evaluate(),
        new Mat3().div(new Mat3(new Vec3(1, 1, 1), new Vec3(1, 1, 1), new Vec3(1, 1, 1))));
  }
}