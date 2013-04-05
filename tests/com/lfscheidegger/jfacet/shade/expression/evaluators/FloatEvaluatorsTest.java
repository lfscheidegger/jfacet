package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.operators.FloatOperators;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;
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

  @Test
  public void testForVec2Dot() {
    Evaluator<Float> eval = FloatEvaluators.forVec2Dot();

    assertTrue(eval.evaluate(Shade.dot(new Vec2(1, 2), new Vec2(3, 4))) == 11);
    assertEquals(eval.getGlSlString(Shade.dot(Shade.vec(1, 2), Shade.vec(3, 4))), "float(dot(vec2(float(1.0), float(2.0)), vec2(float(3.0), float(4.0))))");
    assertEquals(eval.getGlSlString(Shade.dot(Shade.vec(1, 2), Shade.vec(3, 4)), mContext), "float(dot(a, a))");
  }

  @Test
  public void testForVec3Dot() {
    Evaluator<Float> eval = FloatEvaluators.forVec3Dot();

    assertTrue(eval.evaluate(Shade.dot(new Vec3(1, 2, 3), new Vec3(4, 5, 6))) == 32);
    assertEquals(eval.getGlSlString(Shade.dot(Shade.vec(1, 2, 3), Shade.vec(4, 5, 6))), "float(dot(vec3(float(1.0), float(2.0), float(3.0)), vec3(float(4.0), float(5.0), float(6.0))))");
    assertEquals(eval.getGlSlString(Shade.dot(Shade.vec(1, 2, 3), Shade.vec(4, 5, 6)), mContext), "float(dot(a, a))");
  }

  @Test
  public void testForVec4Dot() {
    Evaluator<Float> eval = FloatEvaluators.forVec4Dot();

    assertTrue(eval.evaluate(Shade.dot(new Vec4(1, 2, 3, 4), new Vec4(1, 2, 3, 4))) == 30);
    assertEquals(eval.getGlSlString(Shade.dot(Shade.vec(1, 2, 3, 4), Shade.vec(1, 2, 3, 4))), "float(dot(vec4(float(1.0), float(2.0), float(3.0), float(4.0)), vec4(float(1.0), float(2.0), float(3.0), float(4.0))))");
    assertEquals(eval.getGlSlString(Shade.dot(Shade.vec(1, 2, 3, 4), Shade.vec(1, 2, 3, 4)), mContext), "float(dot(a, a))");
  }
}
