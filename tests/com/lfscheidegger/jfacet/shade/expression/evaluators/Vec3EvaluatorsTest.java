package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.operators.Vec3Operators;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Evaluator} objects for {@code Vec3} objects.
 */
public class Vec3EvaluatorsTest {

  @Test
  public void testForConstant() {
    Vec3 vec = new Vec3(1, 2, 3);
    Evaluator<Vec3> eval = Vec3Evaluators.forConstant(vec);

    assertEquals(eval.getGlSlString(Shade.vec(vec)), "vec3(float(1.0), float(2.0), float(3.0))");
  }

  @Test
  public void testForComponents() {
    Evaluator<Vec3> eval = Vec3Evaluators.forComponents();
    assertEquals(eval.getGlSlString(
        Shade.vec(Shade.add(1, 2), Shade.add(3, 4), Shade.add(5, 6))),
        "vec3(float(float(1.0) + float(2.0)), float(float(3.0) + float(4.0)), float(float(5.0) + float(6.0)))");
  }

  @Test
  public void testForAdditionWithFloat() {
    Evaluator<Vec3> eval = Vec3Evaluators.forOperationWithFloat(Vec3Operators.forAdditionWithFloat());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3), 3)),
        "vec3(vec3(float(1.0), float(2.0), float(3.0)) + float(3.0))");
  }

  @Test
  public void testForSubtractionWithFloat() {
    Evaluator<Vec3> eval = Vec3Evaluators.forOperationWithFloat(Vec3Operators.forSubtractionWithFloat());
    assertEquals(eval.getGlSlString(Shade.sub(Shade.vec(1, 2, 3), 3)),
        "vec3(vec3(float(1.0), float(2.0), float(3.0)) - float(3.0))");
  }

  @Test
  public void testForMultiplicationWithFloat() {
    Evaluator<Vec3> eval = Vec3Evaluators.forOperationWithFloat(Vec3Operators.forMultiplicationWithFloat());
    assertEquals(eval.getGlSlString(Shade.mul(Shade.vec(1, 2, 3), 3)),
        "vec3(vec3(float(1.0), float(2.0), float(3.0)) * float(3.0))");
  }

  @Test
  public void testForDivisionWithFloat() {
    Evaluator<Vec3> eval = Vec3Evaluators.forOperationWithFloat(Vec3Operators.forDivisionWithFloat());
    assertEquals(eval.getGlSlString(Shade.div(Shade.vec(1, 2, 3), 3)),
        "vec3(vec3(float(1.0), float(2.0), float(3.0)) / float(3.0))");
  }

  @Test
     public void testForAdditionWithVec3() {
    Evaluator<Vec3> eval = Vec3Evaluators.forOperationWithVec3(Vec3Operators.forAdditionWithVec3());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3), Shade.vec(3, 4, 5))),
        "vec3(vec3(float(1.0), float(2.0), float(3.0)) + vec3(float(3.0), float(4.0), float(5.0)))");
  }

  @Test
  public void testForSubtractionWithVec3() {
    Evaluator<Vec3> eval = Vec3Evaluators.forOperationWithVec3(Vec3Operators.forSubtractionWithVec3());
    assertEquals(eval.getGlSlString(Shade.sub(Shade.vec(1, 2, 3), Shade.vec(3, 4, 5))),
        "vec3(vec3(float(1.0), float(2.0), float(3.0)) - vec3(float(3.0), float(4.0), float(5.0)))");
  }

  @Test
  public void testForMultiplicationWithVec3() {
    Evaluator<Vec3> eval = Vec3Evaluators.forOperationWithVec3(Vec3Operators.forMultiplicationWithVec3());
    assertEquals(eval.getGlSlString(Shade.mul(Shade.vec(1, 2, 3), Shade.vec(3, 4, 5))),
        "vec3(vec3(float(1.0), float(2.0), float(3.0)) * vec3(float(3.0), float(4.0), float(5.0)))");
  }

  @Test
  public void testForDivisionWithVec3() {
    Evaluator<Vec3> eval = Vec3Evaluators.forOperationWithVec3(Vec3Operators.forDivisionWithVec3());
    assertEquals(eval.getGlSlString(Shade.div(Shade.vec(1, 2, 3), Shade.vec(3, 4, 5))),
        "vec3(vec3(float(1.0), float(2.0), float(3.0)) / vec3(float(3.0), float(4.0), float(5.0)))");
  }
}
