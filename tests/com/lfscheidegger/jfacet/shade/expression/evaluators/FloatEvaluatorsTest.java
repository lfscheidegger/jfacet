package com.lfscheidegger.jfacet.shade.expression.evaluators;

import com.lfscheidegger.jfacet.shade.FakeCompilationContext;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.operators.FloatOperators;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@code Evaluators} for {@code FloatExp} objects.
 */
public class FloatEvaluatorsTest {

  private CompilationContext mContext = new FakeCompilationContext();

  @Test
  public void testForConstant() {
    Evaluator<Float> eval = new ConstantEvaluator<Float>(2.0f);
    FloatExp exp = Shade.constant(2);

    assertEquals(eval.getGlSlString(exp, mContext), "float(2.0)");
  }

  @Test
  public void testForAddition() {
    Evaluator<Float> eval = new FloatOperationEvaluator<Float, Float, Float>(Type.FLOAT_T, FloatOperators.forAddition());

    assertEquals(eval.getGlSlString(Shade.add(2, 3), mContext), "float(float(2.0) + float(3.0))");
  }

  @Test
  public void testForSubtraction() {
    Evaluator<Float> eval = new FloatOperationEvaluator<Float, Float, Float>(Type.FLOAT_T, FloatOperators.forSubtraction());

    assertEquals(eval.getGlSlString(Shade.sub(2, 3), mContext), "float(float(2.0) - float(3.0))");
  }

  @Test
  public void testForMultiplication() {
    Evaluator<Float> eval = new FloatOperationEvaluator<Float, Float, Float>(Type.FLOAT_T, FloatOperators.forMultiplication());

    assertEquals(eval.getGlSlString(Shade.mul(2, 3), mContext), "float(float(2.0) * float(3.0))");
  }

  @Test
  public void testForDivision() {
    Evaluator<Float> eval = new FloatOperationEvaluator<Float, Float, Float>(Type.FLOAT_T, FloatOperators.forDivision());

    assertEquals(eval.getGlSlString(Shade.div(2, 3), mContext), "float(float(2.0) / float(3.0))");
  }

  @Test
  public void testForNegation() {
    Evaluator<Float> eval = new NegationEvaluator<Float>();

    assertTrue(eval.evaluate(Shade.neg(Shade.constant(2))) == -2);
    assertEquals(eval.getGlSlString(Shade.neg(Shade.constant(2)), mContext), "float(-float(2.0))");
  }

  @Test
  public void testForVec2Dot() {
    assertTrue(Shade.dot(new Vec2(1, 2), new Vec2(3, 4)).evaluate() == 11);
    assertEquals(Shade.dot(Shade.vec(1, 2), Shade.vec(3, 4)).getGlSlString(mContext), "float(dot(vec2(float(1.0), float(2.0)), vec2(float(3.0), float(4.0))))");
  }

  @Test
  public void testForVec3Dot() {
    assertTrue(Shade.dot(new Vec3(1, 2, 3), new Vec3(4, 5, 6)).evaluate() == 32);
    assertEquals(Shade.dot(Shade.vec(1, 2, 3), Shade.vec(4, 5, 6)).getGlSlString(mContext), "float(dot(vec3(float(1.0), float(2.0), float(3.0)), vec3(float(4.0), float(5.0), float(6.0))))");
  }

  @Test
  public void testForVec4Dot() {
    assertTrue(Shade.dot(new Vec4(1, 2, 3, 4), new Vec4(1, 2, 3, 4)).evaluate() == 30);
    assertEquals(Shade.dot(Shade.vec(1, 2, 3, 4), Shade.vec(1, 2, 3, 4)).getGlSlString(mContext), "float(dot(vec4(float(1.0), float(2.0), float(3.0), float(4.0)), vec4(float(1.0), float(2.0), float(3.0), float(4.0))))");
  }
}
