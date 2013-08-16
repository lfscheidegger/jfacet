package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ConstantEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ConstructorEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.glsl.UniformEvaluator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Vector2}
 */
public class Vector2Test {

  private Vector2 vec;

  @Before
  public void setUp() {
    vec = new Vector2(1, 2);
  }

  @Test
  public void testConstructors() {
    assertTrue(vec.getEvaluator() instanceof ConstantEvaluator);
    assertEquals(vec.evaluate(), new Vector2.Primitive(1, 2));

    vec = new Vector2(new Real(1), new Real(2));
    assertTrue(vec.getEvaluator() instanceof ConstructorEvaluator);
    assertEquals(vec.evaluate(), new Vector2.Primitive(1, 2));

    vec = new Vector2(
        ImmutableList.<Expression>of(new Real(1), new Real(2)),
        new ConstructorEvaluator<Vector2.Primitive>());
    assertTrue(vec.getEvaluator() instanceof ConstructorEvaluator);
    assertEquals(vec.evaluate(), new Vector2.Primitive(1, 2));

    vec = new Vector2(GlSlType.UNIFORM_T, new UniformEvaluator<Vector2.Primitive>());
    assertEquals(vec.getGlSlType(), GlSlType.UNIFORM_T);
    assertTrue(vec.getEvaluator() instanceof UniformEvaluator);
  }

  @Test
  public void testGetters() {
    assertTrue(vec.getX().evaluate() == 1);
    assertTrue(vec.getY().evaluate() == 2);

    assertTrue(vec.getX().evaluate().equals(vec.get(0).evaluate()));
    assertTrue(vec.getY().evaluate().equals(vec.get(1).evaluate()));
  }
}
