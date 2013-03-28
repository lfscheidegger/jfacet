package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.Mat3;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@code Mat3Expression}
 */
public class Mat3ExpressionTest {

  Vec3Expression c0 = new Vec3Expression(new Vec3(1, 0, 0));
  Vec3Expression c1 = new Vec3Expression(new Vec3(0, 1, 0));
  Vec3Expression c2 = new Vec3Expression(new Vec3(0, 0, 1));

  Mat3Expression c0c1c2 = new Mat3Expression(
      new Mat3(
          new Vec3(1, 0, 0),
          new Vec3(0, 1, 0),
          new Vec3(0, 0, 1)));

  @Test
  public void testConstructors() {
    assertEquals(c0c1c2.evaluate(), new Mat3(new Vec3(1, 0, 0), new Vec3(0, 1, 0), new Vec3(0, 0, 1)));

    Expression child = new Mat3Expression(c0c1c2);
    assertEquals(child.evaluate(), new Mat3(new Vec3(1, 0, 0), new Vec3(0, 1, 0), new Vec3(0, 0, 1)));

    child = new Mat3Expression(c0, c1, c2);
    assertEquals(child.evaluate(), new Mat3(new Vec3(1, 0, 0), new Vec3(0, 1, 0), new Vec3(0, 0, 1)));
  }

  private void assertGetters(Mat3Expression c0c1c2) {
    assertTrue(c0c1c2.getC0().evaluate().equals(new Vec3(1, 0, 0)));
    assertTrue(c0c1c2.getC1().evaluate().equals(new Vec3(0, 1, 0)));
    assertTrue(c0c1c2.getC2().evaluate().equals(new Vec3(0, 0, 1)));
    assertTrue(c0c1c2.get(0).evaluate().equals(new Vec3(1, 0, 0)));
    assertTrue(c0c1c2.get(1).evaluate().equals(new Vec3(0, 1, 0)));
    assertTrue(c0c1c2.get(2).evaluate().equals(new Vec3(0, 0, 1)));
  }

  @Test
  public void testGetters() {
    assertGetters(c0c1c2);

    Mat3Expression child = new Mat3Expression(c0c1c2);
    assertGetters(child);

    child = new Mat3Expression(c0, c1, c2);
    assertGetters(child);
  }
}
