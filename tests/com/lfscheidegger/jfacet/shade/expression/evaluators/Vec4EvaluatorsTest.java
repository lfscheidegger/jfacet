package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.operators.Vec4Operators;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Evaluator} objects for {@code Vec4} objects.
 */
public class Vec4EvaluatorsTest {

  @Test
  public void testForConstant() {
    Vec4 vec = new Vec4(1, 2, 3, 4);
    Evaluator<Vec4> eval = Vec4Evaluators.forConstant(vec);

    assertEquals(eval.getGlSlString(Shade.vec(vec)), "vec4(float(1.0), float(2.0), float(3.0), float(4.0))");
  }

  @Test
  public void testForComponents() {
    Evaluator<Vec4> eval = Vec4Evaluators.forComponents();
    assertEquals(eval.getGlSlString(
        Shade.vec(Shade.add(1, 2), Shade.add(3, 4), Shade.add(5, 6), Shade.add(7, 8))),
        "vec4(float(float(1.0) + float(2.0)), float(float(3.0) + float(4.0)), float(float(5.0) + float(6.0)), float(float(7.0) + float(8.0)))");
  }

  @Test
  public void testForAdditionWithFloat() {
    Evaluator<Vec4> eval = Vec4Evaluators.forOperationWithFloat(Vec4Operators.forAdditionWithFloat());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3, 4), 3)),
        "vec4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)) + float(3.0))");
  }

  @Test
  public void testForSubtractionWithFloat() {
    Evaluator<Vec4> eval = Vec4Evaluators.forOperationWithFloat(Vec4Operators.forSubtractionWithFloat());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3, 4), 3)),
        "vec4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)) - float(3.0))");
  }

  @Test
  public void testForMultiplicationWithFloat() {
    Evaluator<Vec4> eval = Vec4Evaluators.forOperationWithFloat(Vec4Operators.forMultiplicationWithFloat());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3, 4), 3)),
        "vec4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)) * float(3.0))");
  }

  @Test
  public void testForDivisionWithFloat() {
    Evaluator<Vec4> eval = Vec4Evaluators.forOperationWithFloat(Vec4Operators.forDivisionWithFloat());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3, 4), 3)),
        "vec4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)) / float(3.0))");
  }

  @Test
     public void testForAdditionWithVec4() {
    Evaluator<Vec4> eval = Vec4Evaluators.forOperationWithVec4(Vec4Operators.forAdditionWithVec4());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(3, 4, 5, 6))),
        "vec4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)) + vec4(float(3.0), float(4.0), float(5.0), float(6.0)))");
  }

  @Test
  public void testForSubtractionWithVec4() {
    Evaluator<Vec4> eval = Vec4Evaluators.forOperationWithVec4(Vec4Operators.forSubtractionWithVec4());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(3, 4, 5, 6))),
        "vec4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)) - vec4(float(3.0), float(4.0), float(5.0), float(6.0)))");
  }

  @Test
  public void testForMultiplicationWithVec4() {
    Evaluator<Vec4> eval = Vec4Evaluators.forOperationWithVec4(Vec4Operators.forMultiplicationWithVec4());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(3, 4, 5, 6))),
        "vec4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)) * vec4(float(3.0), float(4.0), float(5.0), float(6.0)))");
  }

  @Test
  public void testForDivisionWithVec4() {
    Evaluator<Vec4> eval = Vec4Evaluators.forOperationWithVec4(Vec4Operators.forDivisionWithVec4());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(3, 4, 5, 6))),
        "vec4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)) / vec4(float(3.0), float(4.0), float(5.0), float(6.0)))");
  }
}