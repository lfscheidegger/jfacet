package com.lfscheidegger.jfacet.shade.expression.types;

import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.types.Mat3;
import com.lfscheidegger.jfacet.shade.types.Mat4;
import com.lfscheidegger.jfacet.shade.types.Vec3;
import com.lfscheidegger.jfacet.shade.types.Vec4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@code Mat3Expression}
 */
public class Mat4ExpressionTest {

  Vec4Expression c0 = new Vec4Expression(new Vec4(1, 0, 0, 0));
  Vec4Expression c1 = new Vec4Expression(new Vec4(0, 1, 0, 0));
  Vec4Expression c2 = new Vec4Expression(new Vec4(0, 0, 1, 0));
  Vec4Expression c3 = new Vec4Expression(new Vec4(0, 0, 0, 1));

  Mat4Expression c0c1c2c3 = new Mat4Expression(
      new Mat4(
          new Vec4(1, 0, 0, 0),
          new Vec4(0, 1, 0, 0),
          new Vec4(0, 0, 1, 0),
          new Vec4(0, 0, 0, 1)));

  @Test
  public void testConstructors() {
    assertEquals(c0c1c2c3.evaluate(),
        new Mat4(
            new Vec4(1, 0, 0, 0),
            new Vec4(0, 1, 0, 0),
            new Vec4(0, 0, 1, 0),
            new Vec4(0, 0, 0, 1)));

    Expression child = new Mat4Expression(c0c1c2c3);
    assertEquals(child.evaluate(), new Mat4(
        new Vec4(1, 0, 0, 0), new Vec4(0, 1, 0, 0), new Vec4(0, 0, 1, 0), new Vec4(0, 0, 0, 1)));

    child = new Mat4Expression(c0, c1, c2, c3);
    assertEquals(child.evaluate(), new Mat4(
        new Vec4(1, 0, 0, 0), new Vec4(0, 1, 0, 0), new Vec4(0, 0, 1, 0), new Vec4(0, 0, 0, 1)));
  }

  private void assertGetters(Mat4Expression c0c1c2c3) {
    assertTrue(c0c1c2c3.getC0().evaluate().equals(new Vec4(1, 0, 0, 0)));
    assertTrue(c0c1c2c3.getC1().evaluate().equals(new Vec4(0, 1, 0, 0)));
    assertTrue(c0c1c2c3.getC2().evaluate().equals(new Vec4(0, 0, 1, 0)));
    assertTrue(c0c1c2c3.getC3().evaluate().equals(new Vec4(0, 0, 0, 1)));
    assertTrue(c0c1c2c3.get(0).evaluate().equals(new Vec4(1, 0, 0, 0)));
    assertTrue(c0c1c2c3.get(1).evaluate().equals(new Vec4(0, 1, 0, 0)));
    assertTrue(c0c1c2c3.get(2).evaluate().equals(new Vec4(0, 0, 1, 0)));
    assertTrue(c0c1c2c3.get(3).evaluate().equals(new Vec4(0, 0, 0, 1)));
  }

  @Test
  public void testGetters() {
    assertGetters(c0c1c2c3);

    Mat4Expression child = new Mat4Expression(c0c1c2c3);
    assertGetters(child);

    child = new Mat4Expression(c0, c1, c2, c3);
    assertGetters(child);
  }
}
