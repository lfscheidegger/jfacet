package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.FakeCompilationContext;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.operators.BasicArithmeticOperators;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Evaluator} objects for {@code Vector} objects.
 */
public class Vec2EvaluatorsTest {

  private final CompilationContext mContext = new FakeCompilationContext();

  @Test
  public void testForConstant() {
    Vector vec = new Vector(1, 2);
    Evaluator<Vector> eval = new ConstantEvaluator<Vector>(vec);

    assertEquals(eval.getGlSlString(Shade.vec(vec), mContext), "vec2(float(1.0), float(2.0))");
  }

  @Test
  public void testForComponents() {
    Evaluator<Vector> eval = new ConstructorEvaluator<Vector>();
    assertEquals(eval.getGlSlString(
        Shade.vec(Shade.add(1, 2), Shade.add(3, 4)), mContext),
        "vec2(float(float(1.0) + float(2.0)), float(float(3.0) + float(4.0)))");
  }

  @Test
  public void testForMat2Components() {
    Evaluator<Vector> eval = new ComponentEvaluator<Vector>(0);
    assertEquals(
        eval.getGlSlString(Shade.mat(new Matrix(2)).getC0(), mContext),
        "vec2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0)))[0])");
  }

  @Test
  public void testForAdditionWithFloat() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Vector, Float, Vector>(BasicArithmeticOperators.<Vector>forAdditionWithFloat());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2), 3), mContext), "vec2(vec2(float(1.0), float(2.0)) + float(3.0))");
  }

  @Test
  public void testForSubtractionWithFloat() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Vector, Float, Vector>(BasicArithmeticOperators.<Vector>forSubtractionWithFloat());
    assertEquals(eval.getGlSlString(Shade.sub(Shade.vec(1, 2), 3), mContext), "vec2(vec2(float(1.0), float(2.0)) - float(3.0))");
  }

  @Test
  public void testForMultiplicationWithFloat() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Vector, Float, Vector>(BasicArithmeticOperators.<Vector>forMultiplicationWithFloat());
    assertEquals(eval.getGlSlString(Shade.mul(Shade.vec(1, 2), 3), mContext), "vec2(vec2(float(1.0), float(2.0)) * float(3.0))");
  }

  @Test
  public void testForDivisionWithFloat() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Vector, Float, Vector>(BasicArithmeticOperators.<Vector>forDivisionWithFloat());
    assertEquals(eval.getGlSlString(Shade.div(Shade.vec(1, 2), 3), mContext), "vec2(vec2(float(1.0), float(2.0)) / float(3.0))");
  }

  @Test
  public void testForAdditionWithVector() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Vector, Vector, Vector>(BasicArithmeticOperators.<Vector>forAdditionWithSame());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2), Shade.vec(3, 4)), mContext),
        "vec2(vec2(float(1.0), float(2.0)) + vec2(float(3.0), float(4.0)))");
  }

  @Test
  public void testForSubtractionWithVector() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Vector, Vector, Vector>(BasicArithmeticOperators.<Vector>forSubtractionWithSame());
    assertEquals(eval.getGlSlString(Shade.sub(Shade.vec(1, 2), Shade.vec(3, 4)), mContext),
        "vec2(vec2(float(1.0), float(2.0)) - vec2(float(3.0), float(4.0)))");
  }

  @Test
  public void testForMultiplicationWithVector() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Vector, Vector, Vector>(BasicArithmeticOperators.<Vector>forMultiplicationWithSame());
    assertEquals(eval.getGlSlString(Shade.mul(Shade.vec(1, 2), Shade.vec(3, 4)), mContext),
        "vec2(vec2(float(1.0), float(2.0)) * vec2(float(3.0), float(4.0)))");
  }

  @Test
  public void testForDivisionWithVector() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Vector, Vector, Vector>(BasicArithmeticOperators.<Vector>forDivisionWithSame());
    assertEquals(eval.getGlSlString(Shade.div(Shade.vec(1, 2), Shade.vec(3, 4)), mContext),
        "vec2(vec2(float(1.0), float(2.0)) / vec2(float(3.0), float(4.0)))");
  }

  @Test
  public void testForNegation() {
    Evaluator<Vector> eval = new NegationEvaluator<Vector>();

    assertEquals(eval.evaluate(Shade.neg(Shade.vec(1, 2))), new Vector(1, 2).neg());
  }
}
