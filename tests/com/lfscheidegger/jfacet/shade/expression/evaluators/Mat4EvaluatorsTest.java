package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.FakeCompilationContext;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.operators.Mat4Operators;
import com.lfscheidegger.jfacet.shade.primitives.Mat4;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Evaluator} objects for {@code Mat4} objects.
 */
public class Mat4EvaluatorsTest {

  private CompilationContext mContext = new FakeCompilationContext();

  @Test
  public void testForConstant() {
    Mat4 mat = new Mat4();
    Evaluator<Mat4> eval = Mat4Evaluators.forConstant(mat);

    assertEquals(eval.getGlSlString(Shade.mat(mat), mContext),
        "mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))");
  }

  @Test
  public void testForComponents() {
    Evaluator<Mat4> eval = Mat4Evaluators.forComponents();
    assertEquals(
        eval.getGlSlString(Shade.mat(Shade.vec(1, 0, 0, 0), Shade.vec(0, 1, 0, 0), Shade.vec(0, 0, 1, 0), Shade.vec(0, 0, 0, 1)), mContext),
        "mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))");
  }

  @Test
  public void testForAdditionWithFloat() {
    Evaluator<Mat4> eval = Mat4Evaluators.forOperationWithFloat(Mat4Operators.forAdditionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.add(Shade.mat(new Mat4()), 3), mContext),
        "mat4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0))) + float(3.0))");
  }

  @Test
  public void testForSubtractionWithFloat() {
    Evaluator<Mat4> eval = Mat4Evaluators.forOperationWithFloat(Mat4Operators.forSubtractionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.sub(Shade.mat(new Mat4()), 3), mContext),
        "mat4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0))) - float(3.0))");
  }

  @Test
  public void testForMultiplicationWithFloat() {
    Evaluator<Mat4> eval = Mat4Evaluators.forOperationWithFloat(Mat4Operators.forMultiplicationWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.mul(Shade.mat(new Mat4()), 3), mContext),
        "mat4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0))) * float(3.0))");
  }

  @Test
  public void testForDivisionWithFloat() {
    Evaluator<Mat4> eval = Mat4Evaluators.forOperationWithFloat(Mat4Operators.forDivisionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.div(Shade.mat(new Mat4()), 3), mContext),
        "mat4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0))) / float(3.0))");
  }

  @Test
  public void testForAdditionWithMat4() {
    Evaluator<Mat4> eval = Mat4Evaluators.forOperationWithMat4(Mat4Operators.forAdditionWithMat4());
    assertEquals(
        eval.getGlSlString(Shade.add(new Mat4(), new Mat4()), mContext),
        "mat4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0))) + mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0))))");
  }

  @Test
  public void testForSubtractionWithMat4() {
    Evaluator<Mat4> eval = Mat4Evaluators.forOperationWithMat4(Mat4Operators.forSubtractionWithMat4());
    assertEquals(
        eval.getGlSlString(Shade.sub(new Mat4(), new Mat4()), mContext),
        "mat4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0))) - mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0))))");
  }

  @Test
  public void testForMultiplicationWithMat4() {
    Evaluator<Mat4> eval = Mat4Evaluators.forOperationWithMat4(Mat4Operators.forMultiplicationWithMat4());
    assertEquals(
        eval.getGlSlString(Shade.mul(new Mat4(), new Mat4()), mContext),
        "mat4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0))) * mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0))))");
  }

  @Test
  public void testForDivisionWithMat4() {
    Evaluator<Mat4> eval = Mat4Evaluators.forOperationWithMat4(Mat4Operators.forDivisionWithMat4());
    assertEquals(
        eval.getGlSlString(Shade.div(new Mat4(), new Mat4()), mContext),
        "mat4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0))) / mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0))))");
  }

  @Test
  public void testForNegation() {
    Evaluator<Mat4> eval = Mat4Evaluators.forNegation();

    assertEquals(eval.evaluate(Shade.neg(Shade.mat(new Mat4()))), new Mat4().neg());
  }

  @Test
  public void testForMultiplicationWithVec4() {
    Evaluator<Vec4> eval = Mat4Evaluators.forOperationWithVec4(Mat4Operators.forMultiplicationWithVec4());

    assertEquals(eval.evaluate(Shade.mul(Shade.mat(new Mat4().mul(2)), Shade.vec(1, 1, 1, 1))), new Vec4(2, 2, 2, 2));
  }
}
