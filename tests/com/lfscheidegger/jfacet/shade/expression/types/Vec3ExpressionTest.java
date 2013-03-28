package com.lfscheidegger.jfacet.shade.expression.types;

import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.types.Vec2;
import com.lfscheidegger.jfacet.shade.types.Vec3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@code Vec2Expression}
 */
public class Vec3ExpressionTest {

  FloatExpression x = new FloatExpression(1);
  FloatExpression y = new FloatExpression(2);
  FloatExpression z = new FloatExpression(3);

  Vec3Expression xyz = new Vec3Expression(new Vec3(1, 2, 3));

  @Test
  public void testConstructors() {
    assertEquals(xyz.evaluate(), new Vec3(1, 2, 3));

    Expression child = new Vec3Expression(xyz);
    assertEquals(child.evaluate(), new Vec3(1,2 , 3));

    child = new Vec3Expression(x, y, z);
    assertEquals(child.evaluate(), new Vec3(1, 2, 3));
  }

  private void assertGetters(Vec3Expression xyz) {
    assertTrue(xyz.getX().evaluate().equals(1f));
    assertTrue(xyz.getY().evaluate().equals(2f));
    assertTrue(xyz.getZ().evaluate().equals(3f));
    assertTrue(xyz.get(0).evaluate().equals(1f));
    assertTrue(xyz.get(1).evaluate().equals(2f));
    assertTrue(xyz.get(2).evaluate().equals(3f));
  }

  @Test
  public void testGetters() {
    Vec3Expression child = new Vec3Expression(new Vec3(1, 2, 3));
    assertGetters(child);

    child = new Vec3Expression(xyz);
    assertGetters(child);

    child = new Vec3Expression(x, y, z);
    assertGetters(child);
  }
}
