package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.operators.Vec2Operators;
import com.lfscheidegger.jfacet.shade.primitives.Mat2;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Evaluator} objects for {@code Vec2} objects.
 */
public class Vec2EvaluatorsTest {

  @Test
  public void testForConstant() {
    Vec2 vec = new Vec2(1, 2);
    Evaluator<Vec2> eval = Vec2Evaluators.forConstant(vec);

    assertEquals(eval.getGlSlString(Shade.vec(vec)), "vec2(float(1.0), float(2.0))");
  }

  @Test
  public void testForComponents() {
    Evaluator<Vec2> eval = Vec2Evaluators.forComponents();
    assertEquals(eval.getGlSlString(
        Shade.vec(Shade.add(1, 2), Shade.add(3, 4))),
        "vec2(float(float(1.0) + float(2.0)), float(float(3.0) + float(4.0)))");
  }

  @Test
  public void testForMat2Components() {
    Evaluator<Vec2> eval = Vec2Evaluators.forMat2Component(0);
    assertEquals(
        eval.getGlSlString(Shade.mat(new Mat2()).getC0()),
        "vec2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0)))[0])");
  }

  @Test
  public void testForAdditionWithFloat() {
    Evaluator<Vec2> eval = Vec2Evaluators.forOperationWithFloat(Vec2Operators.forAdditionWithFloat());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2), 3)), "vec2(vec2(float(1.0), float(2.0)) + float(3.0))");
  }

  @Test
  public void testForSubtractionWithFloat() {
    Evaluator<Vec2> eval = Vec2Evaluators.forOperationWithFloat(Vec2Operators.forSubtractionWithFloat());
    assertEquals(eval.getGlSlString(Shade.sub(Shade.vec(1, 2), 3)), "vec2(vec2(float(1.0), float(2.0)) - float(3.0))");
  }

  @Test
  public void testForMultiplicationWithFloat() {
    Evaluator<Vec2> eval = Vec2Evaluators.forOperationWithFloat(Vec2Operators.forMultiplicationWithFloat());
    assertEquals(eval.getGlSlString(Shade.mul(Shade.vec(1, 2), 3)), "vec2(vec2(float(1.0), float(2.0)) * float(3.0))");
  }

  @Test
  public void testForDivisionWithFloat() {
    Evaluator<Vec2> eval = Vec2Evaluators.forOperationWithFloat(Vec2Operators.forDivisionWithFloat());
    assertEquals(eval.getGlSlString(Shade.div(Shade.vec(1, 2), 3)), "vec2(vec2(float(1.0), float(2.0)) / float(3.0))");
  }

  @Test
     public void testForAdditionWithVec2() {
    Evaluator<Vec2> eval = Vec2Evaluators.forOperationWithVec2(Vec2Operators.forAdditionWithVec2());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2), Shade.vec(3, 4))),
        "vec2(vec2(float(1.0), float(2.0)) + vec2(float(3.0), float(4.0)))");
  }

  @Test
  public void testForSubtractionWithVec2() {
    Evaluator<Vec2> eval = Vec2Evaluators.forOperationWithVec2(Vec2Operators.forSubtractionWithVec2());
    assertEquals(eval.getGlSlString(Shade.sub(Shade.vec(1, 2), Shade.vec(3, 4))),
        "vec2(vec2(float(1.0), float(2.0)) - vec2(float(3.0), float(4.0)))");
  }

  @Test
  public void testForMultiplicationWithVec2() {
    Evaluator<Vec2> eval = Vec2Evaluators.forOperationWithVec2(Vec2Operators.forMultiplicationWithVec2());
    assertEquals(eval.getGlSlString(Shade.mul(Shade.vec(1, 2), Shade.vec(3, 4))),
        "vec2(vec2(float(1.0), float(2.0)) * vec2(float(3.0), float(4.0)))");
  }

  @Test
  public void testForDivisionWithVec2() {
    Evaluator<Vec2> eval = Vec2Evaluators.forOperationWithVec2(Vec2Operators.forDivisionWithVec2());
    assertEquals(eval.getGlSlString(Shade.div(Shade.vec(1, 2), Shade.vec(3, 4))),
        "vec2(vec2(float(1.0), float(2.0)) / vec2(float(3.0), float(4.0)))");
  }
}
