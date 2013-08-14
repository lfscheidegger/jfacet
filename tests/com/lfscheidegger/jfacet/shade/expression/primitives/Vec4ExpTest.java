package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Real;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@code VectorExp}
 */
public class Vec4ExpTest {

  @Test
  public void testConstructors() {
    assertEquals(Shade.vec(1, 2, 3, 4).evaluate(), new Vector(1, 2, 3, 4));
    assertEquals(Shade.vec(new Real(1), 2, 3, 4).evaluate(), new Vector(1, 2, 3, 4));
    assertEquals(Shade.vec(1, new Real(2), 3, 4).evaluate(), new Vector(1, 2, 3, 4));
    assertEquals(Shade.vec(new Real(1), new Real(2), 3, 4).evaluate(), new Vector(1, 2, 3, 4));
    assertEquals(Shade.vec(1, 2, new Real(3), 4).evaluate(), new Vector(1, 2, 3, 4));
    assertEquals(Shade.vec(new Real(1), 2, new Real(3), 4).evaluate(), new Vector(1, 2, 3, 4));
    assertEquals(Shade.vec(1, new Real(2), new Real(3), 4).evaluate(), new Vector(1, 2, 3, 4));
    assertEquals(Shade.vec(new Real(1), new Real(2), new Real(3), 4).evaluate(), new Vector(1, 2, 3, 4));
    assertEquals(Shade.vec(1, 2, 3, new Real(4)).evaluate(), new Vector(1, 2, 3, 4));
    assertEquals(Shade.vec(new Real(1), 2, 3, new Real(4)).evaluate(), new Vector(1, 2, 3, 4));
    assertEquals(Shade.vec(1, new Real(2), 3, new Real(4)).evaluate(), new Vector(1, 2, 3, 4));
    assertEquals(Shade.vec(new Real(1), new Real(2), 3, new Real(4)).evaluate(), new Vector(1, 2, 3, 4));
    assertEquals(Shade.vec(1, 2, new Real(3), new Real(4)).evaluate(), new Vector(1, 2, 3, 4));
    assertEquals(Shade.vec(new Real(1), 2, new Real(3), new Real(4)).evaluate(), new Vector(1, 2, 3, 4));
    assertEquals(Shade.vec(1, new Real(2), new Real(3), new Real(4)).evaluate(), new Vector(1, 2, 3, 4));
    assertEquals(Shade.vec(new Real(1), new Real(2), new Real(3), new Real(4)).evaluate(), new Vector(1, 2, 3, 4));
  }

  @Test
  public void testAddition() {
    assertEquals(Shade.add(new Vector(1, 2, 3, 4), 3).evaluate(), new Vector(4, 5, 6, 7));
    assertEquals(Shade.add(Shade.vec(1, 2, 3, 4), 3).evaluate(), new Vector(4, 5, 6, 7));
    assertEquals(Shade.add(new Vector(1, 2, 3, 4), Shade.constant(3)).evaluate(), new Vector(4, 5, 6, 7));
    assertEquals(Shade.add(Shade.vec(1, 2, 3, 4), Shade.constant(3)).evaluate(), new Vector(4, 5, 6, 7));

    assertEquals(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(1, 2, 3, 4)).evaluate(), new Vector(2, 4, 6, 8));
  }

  @Test
  public void testSubtraction() {
    assertEquals(Shade.sub(new Vector(1, 2, 3, 4), 3).evaluate(), new Vector(-2, -1, 0, 1));
    assertEquals(Shade.sub(Shade.vec(1, 2, 3, 4), 3).evaluate(), new Vector(-2, -1, 0, 1));
    assertEquals(Shade.sub(new Vector(1, 2, 3, 4), Shade.constant(3)).evaluate(), new Vector(-2, -1, 0, 1));
    assertEquals(Shade.sub(Shade.vec(1, 2, 3, 4), Shade.constant(3)).evaluate(), new Vector(-2, -1, 0, 1));

    assertEquals(Shade.sub(Shade.vec(1, 2, 3, 4), Shade.vec(1, 2, 3, 4)).evaluate(), new Vector(0, 0, 0, 0));
  }

  @Test
  public void testMultiplication() {
    assertEquals(Shade.mul(new Vector(1, 2, 3, 4), 3).evaluate(), new Vector(3, 6, 9, 12));
    assertEquals(Shade.mul(Shade.vec(1, 2, 3, 4), 3).evaluate(), new Vector(3, 6, 9, 12));
    assertEquals(Shade.mul(new Vector(1, 2, 3, 4), Shade.constant(3)).evaluate(), new Vector(3, 6, 9, 12));
    assertEquals(Shade.mul(Shade.vec(1, 2, 3, 4), Shade.constant(3)).evaluate(), new Vector(3, 6, 9, 12));

    assertEquals(Shade.mul(Shade.vec(1, 2, 3, 4), Shade.vec(1, 2, 3, 4)).evaluate(), new Vector(1, 4, 9, 16));
  }

  @Test
  public void testDivision() {
    assertEquals(Shade.div(new Vector(1, 2, 3, 4), 2).evaluate(), new Vector(0.5f, 1, 1.5f, 2));
    assertEquals(Shade.div(Shade.vec(1, 2, 3, 4), 2).evaluate(), new Vector(0.5f, 1, 1.5f, 2));
    assertEquals(Shade.div(new Vector(1, 2, 3, 4), Shade.constant(2)).evaluate(), new Vector(0.5f, 1, 1.5f, 2));
    assertEquals(Shade.div(Shade.vec(1, 2, 3, 4), Shade.constant(2)).evaluate(), new Vector(0.5f, 1, 1.5f, 2));

    assertEquals(Shade.div(Shade.vec(1, 2, 3, 4), Shade.vec(1, 2, 3, 4)).evaluate(), new Vector(1, 1, 1, 1));
  }

  @Test
  public void testForVectorComponent() {
    assertTrue(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(3, 4, 5, 6)).getX().evaluate() == 4);
    assertTrue(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(3, 4, 5, 6)).get(0).evaluate() == 4);

    assertTrue(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(3, 4, 5, 6)).getY().evaluate() == 6);
    assertTrue(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(3, 4, 5, 6)).get(1).evaluate() == 6);

    assertTrue(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(3, 4, 5, 6)).getZ().evaluate() == 8);
    assertTrue(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(3, 4, 5, 6)).get(2).evaluate() == 8);

    assertTrue(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(3, 4, 5, 6)).getW().evaluate() == 10);
    assertTrue(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(3, 4, 5, 6)).get(3).evaluate() == 10);
  }
}
