package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.FakeCompilationContext;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.operators.BasicArithmeticOperators;
import com.lfscheidegger.jfacet.shade.primitives.Matrix;
import com.lfscheidegger.jfacet.shade.primitives.Vector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Evaluator} objects for {@code Matrix} objects.
 */
public class Mat3EvaluatorsTest {

  private CompilationContext mContext = new FakeCompilationContext();

  @Test
  public void testForConstant() {
    Matrix mat = new Matrix(3);
    Evaluator<Matrix> eval = new ConstantEvaluator<Matrix>(mat);

    assertEquals(eval.getGlSlString(Shade.mat(mat), mContext),
        "mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0)))");
  }

  @Test
  public void testForComponents() {
    Evaluator<Matrix> eval = new ConstructorEvaluator<Matrix>();
    assertEquals(
        eval.getGlSlString(Shade.mat(Shade.vec(1, 0, 0), Shade.vec(0, 1, 0), Shade.vec(0, 0, 1)), mContext),
        "mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0)))");
  }

  @Test
  public void testForAdditionWithFloat() {
    Evaluator<Matrix> eval = new BinaryOperationEvaluator<Matrix, Float, Matrix>(BasicArithmeticOperators.<Matrix>forAdditionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.add(Shade.mat(new Matrix(3)), 3), mContext),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) + float(3.0))");
  }

  @Test
  public void testForSubtractionWithFloat() {
    Evaluator<Matrix> eval = new BinaryOperationEvaluator<Matrix, Float, Matrix>(BasicArithmeticOperators.<Matrix>forSubtractionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.sub(Shade.mat(new Matrix(3)), 3), mContext),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) - float(3.0))");
  }

  @Test
  public void testForMultiplicationWithFloat() {
    Evaluator<Matrix> eval = new BinaryOperationEvaluator<Matrix, Float, Matrix>(BasicArithmeticOperators.<Matrix>forMultiplicationWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.mul(Shade.mat(new Matrix(3)), 3), mContext),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) * float(3.0))");
  }

  @Test
  public void testForDivisionWithFloat() {
    Evaluator<Matrix> eval = new BinaryOperationEvaluator<Matrix, Float, Matrix>(BasicArithmeticOperators.<Matrix>forDivisionWithFloat());
    assertEquals(
        eval.getGlSlString(Shade.div(Shade.mat(new Matrix(3)), 3), mContext),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) / float(3.0))");
  }

  @Test
  public void testForAdditionWithMatrix() {
    Evaluator<Matrix> eval = new BinaryOperationEvaluator<Matrix, Matrix, Matrix>(BasicArithmeticOperators.<Matrix>forAdditionWithSame());
    assertEquals(
        eval.getGlSlString(Shade.add(new Matrix(3), new Matrix(3)), mContext),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) + mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))))");
  }

  @Test
  public void testForSubtractionWithMatrix() {
    Evaluator<Matrix> eval = new BinaryOperationEvaluator<Matrix, Matrix, Matrix>(BasicArithmeticOperators.<Matrix>forSubtractionWithSame());
    assertEquals(
        eval.getGlSlString(Shade.sub(new Matrix(3), new Matrix(3)), mContext),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) - mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))))");
  }

  @Test
  public void testForMultiplicationWithMatrix() {
    Evaluator<Matrix> eval = new BinaryOperationEvaluator<Matrix, Matrix, Matrix>(BasicArithmeticOperators.<Matrix>forMultiplicationWithSame());
    assertEquals(
        eval.getGlSlString(Shade.mul(new Matrix(3), new Matrix(3)), mContext),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) * mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))))");
  }

  @Test
  public void testForDivisionWithMatrix() {
    Evaluator<Matrix> eval = new BinaryOperationEvaluator<Matrix, Matrix, Matrix>(BasicArithmeticOperators.<Matrix>forDivisionWithSame());
    assertEquals(
        eval.getGlSlString(Shade.div(new Matrix(3), new Matrix(3)), mContext),
        "mat3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))) / mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0))))");
  }

  @Test
  public void testForNegation() {
    Evaluator<Matrix> eval = new NegationEvaluator<Matrix>();

    assertEquals(eval.evaluate(Shade.neg(Shade.mat(new Matrix(3)))), new Matrix(3).neg());
  }

  @Test
  public void testForMultiplicationWithVector() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Matrix, Vector, Vector>(BasicArithmeticOperators.forLinearTransform());

    assertEquals(eval.evaluate(Shade.mul(Shade.mat(new Matrix(3).mul(2)), Shade.vec(1, 1, 1))), new Vector(2, 2, 2));
  }
}
