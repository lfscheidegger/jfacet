package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.operators.FloatOperators;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Evaluators} for {@code FloatExp} objects.
 */
public class FloatEvaluatorsTest {

  private CompilationContext mContext = new CompilationContext() {
    @Override
    public String getExpressionName(Expression exp) {
      return "a";
    }
  };

  @Test
  public void testForConstant() {
    Evaluator<Float> eval = FloatEvaluators.forConstant(2);
    FloatExp exp = Shade.constant(2);

    assertEquals(eval.getGlSlString(exp), "float(2.0)");
    assertEquals(eval.getGlSlString(exp, mContext), "float(2.0)");
  }

  @Test
  public void testForAddition() {
    Evaluator<Float> eval = FloatEvaluators.forOperation(FloatOperators.forAddition());

    assertEquals(eval.getGlSlString(Shade.add(2, 3)), "float(float(2.0) + float(3.0))");
    assertEquals(eval.getGlSlString(Shade.add(2, 3), mContext), "float(a + a)");
  }

  @Test
  public void testForSubtraction() {
    Evaluator<Float> eval = FloatEvaluators.forOperation(FloatOperators.forSubtraction());

    assertEquals(eval.getGlSlString(Shade.sub(2, 3)), "float(float(2.0) - float(3.0))");
    assertEquals(eval.getGlSlString(Shade.sub(2, 3), mContext), "float(a - a)");
  }

  @Test
  public void testForMultiplication() {
    Evaluator<Float> eval = FloatEvaluators.forOperation(FloatOperators.forMultiplication());

    assertEquals(eval.getGlSlString(Shade.mul(2, 3)), "float(float(2.0) * float(3.0))");
    assertEquals(eval.getGlSlString(Shade.mul(2, 3), mContext), "float(a * a)");
  }

  @Test
  public void testForDivision() {
    Evaluator<Float> eval = FloatEvaluators.forOperation(FloatOperators.forDivision());

    assertEquals(eval.getGlSlString(Shade.div(2, 3)), "float(float(2.0) / float(3.0))");
    assertEquals(eval.getGlSlString(Shade.div(2, 3), mContext), "float(a / a)");
  }

  @Test
  public void testForNegation() {
    Evaluator<Float> eval = FloatEvaluators.forNegation();

    assertTrue(eval.evaluate(Shade.neg(Shade.constant(2))) == -2);
    assertEquals(eval.getGlSlString(Shade.neg(Shade.constant(2))), "float(-float(2.0))");
    assertEquals(eval.getGlSlString(Shade.neg(Shade.constant(2)), mContext), "float(-a)");
  }
}
