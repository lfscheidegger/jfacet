package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.operators.Mat2Operators;
import com.lfscheidegger.jfacet.shade.primitives.Mat2;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Evaluator} objects for {@code Mat2} objects.
 */
public class Mat2EvaluatorsTest {

  private CompilationContext mContext = new CompilationContext() {
    @Override
    public String getExpressionName(Expression exp) {
      return "a";
    }

    @Override
    public int getScopeLevel() {
      return 0;
    }

    @Override
    public void pushScope() {}
    @Override
    public void popScope() {}
  };

  @Test
  public void testForConstant() {
    Mat2 mat = new Mat2();
    Evaluator<Mat2> eval = Mat2Evaluators.forConstant(mat);

    assertEquals(eval.getGlSlString(Shade.mat(mat)), "mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0)))");
    assertEquals(eval.getGlSlString(Shade.mat(mat), mContext), "mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0)))");
  }

  @Test
  public void testForComponents() {
    Evaluator<Mat2> eval = Mat2Evaluators.forComponents();
    assertEquals(
        eval.getGlSlString(Shade.mat(Shade.vec(1, 0), Shade.vec(0, 1))),
        "mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0)))");
    assertEquals(
        eval.getGlSlString(Shade.mat(Shade.vec(1, 0), Shade.vec(0, 1)), mContext), "mat2(a, a)");
  }

  @Test
  public void testForAdditionWithFloat() {
    Evaluator<Mat2> eval = Mat2Evaluators.forOperationWithFloat(Mat2Operators.forAdditionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.add(Shade.mat(new Mat2()), 3)),
        "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))) + float(3.0))");
    assertEquals(eval.getGlSlString(Shade.add(Shade.mat(new Mat2()), 3), mContext), "mat2(a + a)");
  }

  @Test
  public void testForSubtractionWithFloat() {
    Evaluator<Mat2> eval = Mat2Evaluators.forOperationWithFloat(Mat2Operators.forSubtractionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.sub(Shade.mat(new Mat2()), 3)),
        "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))) - float(3.0))");
    assertEquals(eval.getGlSlString(Shade.sub(Shade.mat(new Mat2()), 3), mContext), "mat2(a - a)");
  }

  @Test
  public void testForMultiplicationWithFloat() {
    Evaluator<Mat2> eval = Mat2Evaluators.forOperationWithFloat(Mat2Operators.forMultiplicationWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.mul(Shade.mat(new Mat2()), 3)),
        "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))) * float(3.0))");
    assertEquals(eval.getGlSlString(Shade.mul(Shade.mat(new Mat2()), 3), mContext), "mat2(a * a)");
  }

  @Test
  public void testForDivisionWithFloat() {
    Evaluator<Mat2> eval = Mat2Evaluators.forOperationWithFloat(Mat2Operators.forDivisionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.div(Shade.mat(new Mat2()), 3)),
        "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))) / float(3.0))");
    assertEquals(eval.getGlSlString(Shade.div(Shade.mat(new Mat2()), 3), mContext), "mat2(a / a)");
  }

  @Test
  public void testForAdditionWithMat2() {
    Evaluator<Mat2> eval = Mat2Evaluators.forOperationWithMat2(Mat2Operators.forAdditionWithMat2());
    assertEquals(
        eval.getGlSlString(Shade.add(new Mat2(), new Mat2())),
        "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))) + mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))))");
    assertEquals(eval.getGlSlString(Shade.add(Shade.mat(new Mat2()), Shade.mat(new Mat2())), mContext), "mat2(a + a)");
  }

  @Test
  public void testForSubtractionWithMat2() {
    Evaluator<Mat2> eval = Mat2Evaluators.forOperationWithMat2(Mat2Operators.forSubtractionWithMat2());
    assertEquals(
        eval.getGlSlString(Shade.sub(new Mat2(), new Mat2())),
        "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))) - mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))))");
    assertEquals(eval.getGlSlString(Shade.sub(Shade.mat(new Mat2()), Shade.mat(new Mat2())), mContext), "mat2(a - a)");
  }

  @Test
  public void testForMultiplicationWithMat2() {
    Evaluator<Mat2> eval = Mat2Evaluators.forOperationWithMat2(Mat2Operators.forMultiplicationWithMat2());
    assertEquals(
        eval.getGlSlString(Shade.mul(new Mat2(), new Mat2())),
        "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))) * mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))))");
    assertEquals(eval.getGlSlString(Shade.mul(Shade.mat(new Mat2()), Shade.mat(new Mat2())), mContext), "mat2(a * a)");
  }

  @Test
  public void testForDivisionWithMat2() {
    Evaluator<Mat2> eval = Mat2Evaluators.forOperationWithMat2(Mat2Operators.forDivisionWithMat2());
    assertEquals(
        eval.getGlSlString(Shade.div(new Mat2(), new Mat2())),
        "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))) / mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))))");
    assertEquals(eval.getGlSlString(Shade.div(Shade.mat(new Mat2()), Shade.mat(new Mat2())), mContext), "mat2(a / a)");
  }

  @Test
  public void testForNegation() {
    Evaluator<Mat2> eval = Mat2Evaluators.forNegation();

    assertEquals(eval.evaluate(Shade.neg(Shade.mat(new Mat2()))), new Mat2().neg());
  }

  @Test
  public void testForMultiplicationWithVec2() {
    Evaluator<Vec2> eval = Mat2Evaluators.forOperationWithVec2(Mat2Operators.forMultiplicationWithVec2());

    assertEquals(eval.evaluate(Shade.mul(Shade.mat(new Mat2().mul(2)), Shade.vec(1, 1))), new Vec2(2, 2));
  }
}
