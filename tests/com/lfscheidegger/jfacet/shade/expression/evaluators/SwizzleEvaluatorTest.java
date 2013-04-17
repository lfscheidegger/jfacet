package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.primitives.Vector;
import org.junit.Test;
import static org.junit.Assert.*;

public class SwizzleEvaluatorTest {

  @Test
  public void testSwizzleForFloat() {
    Evaluator<Float> evaluator = new SwizzleEvaluator<Float>('x');
    assertTrue(evaluator.evaluate(Shade.swizzle(Shade.vec(1, 2), 'x')) == 1);

    evaluator = new SwizzleEvaluator<Float>('y');
    assertTrue(evaluator.evaluate(Shade.swizzle(Shade.vec(1, 2), 'y')) == 2);
  }

  @Test
  public void testSwizzleForVec2() {
    Evaluator<Vector> evaluator = new SwizzleEvaluator<Vector>('x', 'x');
    assertEquals(evaluator.evaluate(Shade.swizzle(Shade.vec(1, 2), 'x', 'x')), new Vector(1, 1));
  }

  @Test
  public void testSwizzleForVec3() {
    Evaluator<Vector> evaluator = new SwizzleEvaluator<Vector>('x', 'x', 'x');
    assertEquals(evaluator.evaluate(Shade.swizzle(Shade.vec(1, 2, 3), 'x', 'x', 'x')), new Vector(1, 1, 1));
  }

  @Test
  public void testSwizzleForVec4() {
    Evaluator<Vector> evaluator = new SwizzleEvaluator<Vector>('x', 'x', 'x', 'x');
    assertEquals(evaluator.evaluate(Shade.swizzle(Shade.vec(1, 2, 3, 4), 'x', 'x', 'x', 'x')), new Vector(1, 1, 1, 1));
  }
}
