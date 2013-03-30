package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.operators.Mat3Operators;
import com.lfscheidegger.jfacet.shade.primitives.Mat3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Evaluator} objects for {@code Mat3} objects.
 */
public class Mat3EvaluatorsTest {

  @Test
  public void testForConstant() {
    Mat3 mat = new Mat3();
    Evaluator<Mat3> eval = Mat3Evaluators.forConstant(mat);

    assertEquals(eval.getGlSlString(Shade.mat(mat)),
        "mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0)))");
  }

  @Test
  public void testForComponents() {
    Evaluator<Mat3> eval = Mat3Evaluators.forComponents();
    assertEquals(
        eval.getGlSlString(Shade.mat(Shade.vec(1, 0, 0), Shade.vec(0, 1, 0), Shade.vec(0, 0, 1))),
        "mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0)))");
  }

  @Test
  public void testForAdditionWithFloat() {
    Evaluator<Mat3> eval = Mat3Evaluators.forOperationWithFloat(Mat3Operators.forAdditionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.add(Shade.mat(new Mat3()), 3)),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) + float(3.0))");
  }

  @Test
  public void testForSubtractionWithFloat() {
    Evaluator<Mat3> eval = Mat3Evaluators.forOperationWithFloat(Mat3Operators.forSubtractionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.add(Shade.mat(new Mat3()), 3)),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) - float(3.0))");
  }

  @Test
  public void testForMultiplicationWithFloat() {
    Evaluator<Mat3> eval = Mat3Evaluators.forOperationWithFloat(Mat3Operators.forMultiplicationWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.add(Shade.mat(new Mat3()), 3)),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) * float(3.0))");
  }

  @Test
  public void testForDivisionWithFloat() {
    Evaluator<Mat3> eval = Mat3Evaluators.forOperationWithFloat(Mat3Operators.forDivisionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.add(Shade.mat(new Mat3()), 3)),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) / float(3.0))");
  }

  @Test
  public void testForAdditionWithMat3() {
    Evaluator<Mat3> eval = Mat3Evaluators.forOperationWithMat3(Mat3Operators.forAdditionWithMat3());
    assertEquals(
        eval.getGlSlString(Shade.add(new Mat3(), new Mat3())),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) + mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))))");
  }

  @Test
  public void testForSubtractionWithMat3() {
    Evaluator<Mat3> eval = Mat3Evaluators.forOperationWithMat3(Mat3Operators.forSubtractionWithMat3());
    assertEquals(
        eval.getGlSlString(Shade.add(new Mat3(), new Mat3())),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) - mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))))");
  }

  @Test
  public void testForMultiplicationWithMat3() {
    Evaluator<Mat3> eval = Mat3Evaluators.forOperationWithMat3(Mat3Operators.forMultiplicationWithMat3());
    assertEquals(
        eval.getGlSlString(Shade.add(new Mat3(), new Mat3())),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) * mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))))");
  }

  @Test
  public void testForDivisionWithMat3() {
    Evaluator<Mat3> eval = Mat3Evaluators.forOperationWithMat3(Mat3Operators.forDivisionWithMat3());
    assertEquals(
        eval.getGlSlString(Shade.add(new Mat3(), new Mat3())),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) / mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))))");
  }
}
