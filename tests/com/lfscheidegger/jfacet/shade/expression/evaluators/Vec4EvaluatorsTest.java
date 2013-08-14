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
public class Vec4EvaluatorsTest {

  private final CompilationContext mContext = new FakeCompilationContext();

  @Test
  public void testForConstant() {
    Vector vec = new Vector(1, 2, 3, 4);
    Evaluator<Vector> eval = new ConstantEvaluator<Vector>(vec);

    assertEquals(eval.getGlSlString(Shade.vec(vec), mContext), "vec4(float(1.0), float(2.0), float(3.0), float(4.0))");
  }

  @Test
  public void testForComponents() {
    Evaluator<Vector> eval = new ConstructorEvaluator<Vector>();
    assertEquals(eval.getGlSlString(
        Shade.vec(Shade.add(1, 2), Shade.add(3, 4), Shade.add(5, 6), Shade.add(7, 8)), mContext),
        "vec4(float(float(1.0) + float(2.0)), float(float(3.0) + float(4.0)), float(float(5.0) + float(6.0)), float(float(7.0) + float(8.0)))");
  }

  @Test
  public void testForAdditionWithFloat() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Vector, Float, Vector>(BasicArithmeticOperators.<Vector>forAdditionWithFloat());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3, 4), 3), mContext),
        "vec4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)) + float(3.0))");
  }

  @Test
  public void testForSubtractionWithFloat() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Vector, Float, Vector>(BasicArithmeticOperators.<Vector>forSubtractionWithFloat());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3, 4), 3), mContext),
        "vec4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)) - float(3.0))");
  }

  @Test
  public void testForMultiplicationWithFloat() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Vector, Float, Vector>(BasicArithmeticOperators.<Vector>forMultiplicationWithFloat());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3, 4), 3), mContext),
        "vec4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)) * float(3.0))");
  }

  @Test
  public void testForDivisionWithFloat() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Vector, Float, Vector>(BasicArithmeticOperators.<Vector>forDivisionWithFloat());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3, 4), 3), mContext),
        "vec4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)) / float(3.0))");
  }

  @Test
  public void testForAdditionWithVector() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Vector, Vector, Vector>(BasicArithmeticOperators.<Vector>forAdditionWithSame());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(3, 4, 5, 6)), mContext),
        "vec4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)) + vec4(float(3.0), float(4.0), float(5.0), float(6.0)))");
  }

  @Test
  public void testForSubtractionWithVector() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Vector, Vector, Vector>(BasicArithmeticOperators.<Vector>forSubtractionWithSame());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(3, 4, 5, 6)), mContext),
        "vec4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)) - vec4(float(3.0), float(4.0), float(5.0), float(6.0)))");
  }

  @Test
  public void testForMultiplicationWithVector() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Vector, Vector, Vector>(BasicArithmeticOperators.<Vector>forMultiplicationWithSame());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(3, 4, 5, 6)), mContext),
        "vec4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)) * vec4(float(3.0), float(4.0), float(5.0), float(6.0)))");
  }

  @Test
  public void testForDivisionWithVector() {
    Evaluator<Vector> eval = new BinaryOperationEvaluator<Vector, Vector, Vector>(BasicArithmeticOperators.<Vector>forDivisionWithSame());
    assertEquals(eval.getGlSlString(Shade.add(Shade.vec(1, 2, 3, 4), Shade.vec(3, 4, 5, 6)), mContext),
        "vec4(vec4(float(1.0), float(2.0), float(3.0), float(4.0)) / vec4(float(3.0), float(4.0), float(5.0), float(6.0)))");
  }

  @Test
  public void testForNegation() {
    Evaluator<Vector> eval = new NegationEvaluator<Vector>();

    assertEquals(eval.evaluate(Shade.neg(Shade.vec(1, 2, 3, 4))), new Vector(1, 2, 3, 4).neg());
  }
}
