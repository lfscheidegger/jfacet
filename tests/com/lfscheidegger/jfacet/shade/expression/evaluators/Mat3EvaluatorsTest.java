package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.FakeCompilationContext;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.operators.BasicArithmeticOperators;
import com.lfscheidegger.jfacet.shade.expression.operators.Mat3Operators;
import com.lfscheidegger.jfacet.shade.primitives.Mat3;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Evaluator} objects for {@code Mat3} objects.
 */
public class Mat3EvaluatorsTest {

  private CompilationContext mContext = new FakeCompilationContext();

  @Test
  public void testForConstant() {
    Mat3 mat = new Mat3();
    Evaluator<Mat3> eval = new ConstantEvaluator<Mat3>(mat);

    assertEquals(eval.getGlSlString(Shade.mat(mat), mContext),
        "mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0)))");
  }

  @Test
  public void testForComponents() {
    Evaluator<Mat3> eval = new ConstructorEvaluator<Mat3>(Type.MAT3_T);
    assertEquals(
        eval.getGlSlString(Shade.mat(Shade.vec(1, 0, 0), Shade.vec(0, 1, 0), Shade.vec(0, 0, 1)), mContext),
        "mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0)))");
  }

  @Test
  public void testForAdditionWithFloat() {
    Evaluator<Mat3> eval = new BinaryOperationEvaluator<Mat3, Float, Mat3>(Type.MAT3_T, BasicArithmeticOperators.<Mat3>forAdditionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.add(Shade.mat(new Mat3()), 3), mContext),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) + float(3.0))");
  }

  @Test
  public void testForSubtractionWithFloat() {
    Evaluator<Mat3> eval = new BinaryOperationEvaluator<Mat3, Float, Mat3>(Type.MAT3_T, BasicArithmeticOperators.<Mat3>forSubtractionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.sub(Shade.mat(new Mat3()), 3), mContext),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) - float(3.0))");
  }

  @Test
  public void testForMultiplicationWithFloat() {
    Evaluator<Mat3> eval = new BinaryOperationEvaluator<Mat3, Float, Mat3>(Type.MAT3_T, BasicArithmeticOperators.<Mat3>forMultiplicationWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.mul(Shade.mat(new Mat3()), 3), mContext),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) * float(3.0))");
  }

  @Test
  public void testForDivisionWithFloat() {
    Evaluator<Mat3> eval = new BinaryOperationEvaluator<Mat3, Float, Mat3>(Type.MAT3_T, BasicArithmeticOperators.<Mat3>forDivisionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.div(Shade.mat(new Mat3()), 3), mContext),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) / float(3.0))");
  }

  @Test
  public void testForAdditionWithMat3() {
    Evaluator<Mat3> eval = new BinaryOperationEvaluator<Mat3, Mat3, Mat3>(Type.MAT3_T, BasicArithmeticOperators.<Mat3>forAdditionWithSame());
    assertEquals(
        eval.getGlSlString(Shade.add(new Mat3(), new Mat3()), mContext),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) + mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))))");
  }

  @Test
  public void testForSubtractionWithMat3() {
    Evaluator<Mat3> eval = new BinaryOperationEvaluator<Mat3, Mat3, Mat3>(Type.MAT3_T, BasicArithmeticOperators.<Mat3>forSubtractionWithSame());
    assertEquals(
        eval.getGlSlString(Shade.sub(new Mat3(), new Mat3()), mContext),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) - mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))))");
  }

  @Test
  public void testForMultiplicationWithMat3() {
    Evaluator<Mat3> eval = new BinaryOperationEvaluator<Mat3, Mat3, Mat3>(Type.MAT3_T, BasicArithmeticOperators.<Mat3>forMultiplicationWithSame());
    assertEquals(
        eval.getGlSlString(Shade.mul(new Mat3(), new Mat3()), mContext),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) * mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))))");
  }

  @Test
  public void testForDivisionWithMat3() {
    Evaluator<Mat3> eval = new BinaryOperationEvaluator<Mat3, Mat3, Mat3>(Type.MAT3_T, BasicArithmeticOperators.<Mat3>forDivisionWithSame());
    assertEquals(
        eval.getGlSlString(Shade.div(new Mat3(), new Mat3()), mContext),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) / mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))))");
  }

  @Test
  public void testForNegation() {
    Evaluator<Mat3> eval = new NegationEvaluator<Mat3>();

    assertEquals(eval.evaluate(Shade.neg(Shade.mat(new Mat3()))), new Mat3().neg());
  }

  @Test
  public void testForMultiplicationWithVec3() {
    Evaluator<Vec3> eval = new BinaryOperationEvaluator<Mat3, Vec3, Vec3>(Type.VEC3_T, Mat3Operators.forMultiplicationWithVec3());

    assertEquals(eval.evaluate(Shade.mul(Shade.mat(new Mat3().mul(2)), Shade.vec(1, 1, 1))), new Vec3(2, 2, 2));
  }
}
