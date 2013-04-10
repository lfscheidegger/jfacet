package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.FakeCompilationContext;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.operators.BasicArithmeticOperators;
import com.lfscheidegger.jfacet.shade.expression.operators.Mat2Operators;
import com.lfscheidegger.jfacet.shade.primitives.Mat2;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Evaluator} objects for {@code Mat2} objects.
 */
public class Mat2EvaluatorsTest {

  private CompilationContext mContext = new FakeCompilationContext();

  @Test
  public void testForConstant() {
    Mat2 mat = new Mat2();
    Evaluator<Mat2> eval = new ConstantEvaluator<Mat2>(mat);

    assertEquals(eval.getGlSlString(Shade.mat(mat), mContext), "mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0)))");
  }

  @Test
  public void testForComponents() {
    Evaluator<Mat2> eval = new ConstructorEvaluator<Mat2>(Type.MAT2_T);
    assertEquals(
        eval.getGlSlString(Shade.mat(Shade.vec(1, 0), Shade.vec(0, 1)), mContext),
        "mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0)))");
  }

  @Test
  public void testForAdditionWithFloat() {
    Evaluator<Mat2> eval = new BinaryOperationEvaluator<Mat2, Float, Mat2>(Type.MAT2_T, BasicArithmeticOperators.<Mat2>forAdditionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.add(Shade.mat(new Mat2()), 3), mContext),
        "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))) + float(3.0))");
  }

  @Test
  public void testForSubtractionWithFloat() {
    Evaluator<Mat2> eval = new BinaryOperationEvaluator<Mat2, Float, Mat2>(Type.MAT2_T, BasicArithmeticOperators.<Mat2>forSubtractionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.sub(Shade.mat(new Mat2()), 3), mContext),
        "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))) - float(3.0))");
  }

  @Test
  public void testForMultiplicationWithFloat() {
    Evaluator<Mat2> eval = new BinaryOperationEvaluator<Mat2, Float, Mat2>(Type.MAT2_T, BasicArithmeticOperators.<Mat2>forMultiplicationWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.mul(Shade.mat(new Mat2()), 3), mContext),
        "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))) * float(3.0))");
  }

  @Test
  public void testForDivisionWithFloat() {
    Evaluator<Mat2> eval = new BinaryOperationEvaluator<Mat2, Float, Mat2>(Type.MAT2_T, BasicArithmeticOperators.<Mat2>forDivisionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.div(Shade.mat(new Mat2()), 3), mContext),
        "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))) / float(3.0))");
  }

  @Test
  public void testForAdditionWithMat2() {
    Evaluator<Mat2> eval = new BinaryOperationEvaluator<Mat2, Mat2, Mat2>(Type.MAT2_T, BasicArithmeticOperators.<Mat2>forAdditionWithSame());
    assertEquals(
        eval.getGlSlString(Shade.add(new Mat2(), new Mat2()), mContext),
        "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))) + mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))))");
  }

  @Test
  public void testForSubtractionWithMat2() {
    Evaluator<Mat2> eval = new BinaryOperationEvaluator<Mat2, Mat2, Mat2>(Type.MAT2_T, BasicArithmeticOperators.<Mat2>forSubtractionWithSame());
    assertEquals(
        eval.getGlSlString(Shade.sub(new Mat2(), new Mat2()), mContext),
        "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))) - mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))))");
  }

  @Test
  public void testForMultiplicationWithMat2() {
    Evaluator<Mat2> eval = new BinaryOperationEvaluator<Mat2, Mat2, Mat2>(Type.MAT2_T, BasicArithmeticOperators.<Mat2>forMultiplicationWithSame());
    assertEquals(
        eval.getGlSlString(Shade.mul(new Mat2(), new Mat2()), mContext),
        "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))) * mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))))");
  }

  @Test
  public void testForDivisionWithMat2() {
    Evaluator<Mat2> eval = new BinaryOperationEvaluator<Mat2, Mat2, Mat2>(Type.MAT2_T, BasicArithmeticOperators.<Mat2>forDivisionWithSame());
    assertEquals(
        eval.getGlSlString(Shade.div(new Mat2(), new Mat2()), mContext),
        "mat2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))) / mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0))))");
  }

  @Test
  public void testForNegation() {
    Evaluator<Mat2> eval = new NegationEvaluator<Mat2>();

    assertEquals(eval.evaluate(Shade.neg(Shade.mat(new Mat2()))), new Mat2().neg());
  }

  @Test
  public void testForMultiplicationWithVec2() {
    Evaluator<Vec2> eval = new BinaryOperationEvaluator<Mat2, Vec2, Vec2>(Type.VEC2_T, Mat2Operators.forMultiplicationWithVec2());

    assertEquals(eval.evaluate(Shade.mul(Shade.mat(new Mat2().mul(2)), Shade.vec(1, 1))), new Vec2(2, 2));
  }
}
