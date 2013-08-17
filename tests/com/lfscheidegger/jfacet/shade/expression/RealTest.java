package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.expression.evaluators.BinaryOperationEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ComponentEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ConstantEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.NegationEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.glsl.UniformEvaluator;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Real}
 */
public class RealTest {

  private Real real;

  @Before
  public void setUp() {
    real = new Real(3);
  }

  @Test
  public void testConstructors() {
    assertTrue(real.getEvaluator() instanceof ConstantEvaluator);
    assertEquals(real.getParents().size(), 0);
    assertTrue(real.evaluate() == 3);

    real = new Real(
        ImmutableList.<Expression>of(new Vector2(1, 2)),
        new ComponentEvaluator<Float>(0));
    assertTrue(real.getEvaluator() instanceof ComponentEvaluator);
    assertEquals(real.getParents().size(), 1);
    assertTrue(real.evaluate() == 1);

    real = new Real(GlSlType.UNIFORM_T, new UniformEvaluator<Float>());
    assertEquals(real.getGlSlType(), GlSlType.UNIFORM_T);
    assertTrue(real.getEvaluator() instanceof UniformEvaluator);
  }

  private void testBinOpCommon(Real result, float expected, String expectedOperatorSymbol) {
    assertEquals(result.getParents().size(), 2);
    assertTrue(result.evaluate() == expected);
    assertTrue(result.getEvaluator() instanceof BinaryOperationEvaluator);
    Operator op = ((BinaryOperationEvaluator)result.getEvaluator()).getOperator();
    assertEquals(op.getOperatorSymbol(), expectedOperatorSymbol);
  }

  @Test
  public void testAdd() {
    Real added = real.add(3);
    testBinOpCommon(added, 6, "+");
    assertSame(added.getParents().get(0), real);
    assertEquals(added.getParents().get(1).evaluate(), 3.0f);

    added = real.add(new Real(3));
    testBinOpCommon(added, 6, "+");
    assertSame(added.getParents().get(0), real);
    assertEquals(added.getParents().get(1).evaluate(), 3.0f);
  }

  @Test
  public void testSub() {
    Real subtracted = real.sub(3);
    testBinOpCommon(subtracted, 0, "-");
    assertSame(subtracted.getParents().get(0), real);
    assertEquals(subtracted.getParents().get(1).evaluate(), 3.0f);

    subtracted = real.sub(new Real(3));
    testBinOpCommon(subtracted, 0, "-");
    assertSame(subtracted.getParents().get(0), real);
    assertEquals(subtracted.getParents().get(1).evaluate(), 3.0f);
  }

  @Test
  public void testMul() {
    Real multiplied = real.mul(3);
    testBinOpCommon(multiplied, 9, "*");
    assertSame(multiplied.getParents().get(0), real);
    assertEquals(multiplied.getParents().get(1).evaluate(), 3.0f);

    multiplied = real.mul(new Real(3));
    testBinOpCommon(multiplied, 9, "*");
    assertSame(multiplied.getParents().get(0), real);
    assertEquals(multiplied.getParents().get(1).evaluate(), 3.0f);
  }

  @Test
  public void testDiv() {
    Real divided = real.div(2);
    testBinOpCommon(divided, 1.5f, "/");
    assertSame(divided.getParents().get(0), real);
    assertEquals(divided.getParents().get(1).evaluate(), 2.0f);

    divided = real.div(new Real(2));
    testBinOpCommon(divided, 1.5f, "/");
    assertSame(divided.getParents().get(0), real);
    assertEquals(divided.getParents().get(1).evaluate(), 2.0f);
  }

  @Test
  public void testNeg() {
    Real negated = real.neg();
    assertEquals(negated.getParents().size(), 1);
    assertSame(negated.getParents().get(0), real);
    assertTrue(negated.getEvaluator() instanceof NegationEvaluator);
    assertTrue(negated.evaluate() == -real.evaluate());
  }

  private void testBooleanOpCommon(Bool result, boolean expected, String expectedOperatorSymbol) {
    assertEquals(result.getParents().size(), 2);
    assertTrue(result.evaluate() == expected);
    assertTrue(result.getEvaluator() instanceof BinaryOperationEvaluator);
    Operator op = ((BinaryOperationEvaluator)result.getEvaluator()).getOperator();
    assertEquals(op.getOperatorSymbol(), expectedOperatorSymbol);
  }

  @Test
  public void testGreaterThan() {
    Bool gt = real.isGreaterThan(3);
    testBooleanOpCommon(gt, false, ">");
    assertSame(gt.getParents().get(0), real);
    assertEquals(gt.getParents().get(1).evaluate(), 3.0f);

    gt = real.isGreaterThan(new Real(3));
    testBooleanOpCommon(gt, false, ">");
    assertSame(gt.getParents().get(0), real);
    assertEquals(gt.getParents().get(1).evaluate(), 3.0f);
  }

  @Test
  public void testGreaterThanOrEqual() {
    Bool geq = real.isGreaterThanOrEqual(3);
    testBooleanOpCommon(geq, true, ">=");
    assertSame(geq.getParents().get(0), real);
    assertEquals(geq.getParents().get(1).evaluate(), 3.0f);

    geq = real.isGreaterThanOrEqual(new Real(3));
    testBooleanOpCommon(geq, true, ">=");
    assertSame(geq.getParents().get(0), real);
    assertEquals(geq.getParents().get(1).evaluate(), 3.0f);
  }

  @Test
  public void testLessThan() {
    Bool gt = real.isLessThan(3);
    testBooleanOpCommon(gt, false, "<");
    assertSame(gt.getParents().get(0), real);
    assertEquals(gt.getParents().get(1).evaluate(), 3.0f);

    gt = real.isLessThan(new Real(3));
    testBooleanOpCommon(gt, false, "<");
    assertSame(gt.getParents().get(0), real);
    assertEquals(gt.getParents().get(1).evaluate(), 3.0f);
  }

  @Test
  public void testLessThanOrEqual() {
    Bool geq = real.isLessThanOrEqual(3);
    testBooleanOpCommon(geq, true, "<=");
    assertSame(geq.getParents().get(0), real);
    assertEquals(geq.getParents().get(1).evaluate(), 3.0f);

    geq = real.isLessThanOrEqual(new Real(3));
    testBooleanOpCommon(geq, true, "<=");
    assertSame(geq.getParents().get(0), real);
    assertEquals(geq.getParents().get(1).evaluate(), 3.0f);
  }

  @Test
  public void testIsEqual() {
    Bool gt = real.isEqual(3);
    testBooleanOpCommon(gt, true, "==");
    assertSame(gt.getParents().get(0), real);
    assertEquals(gt.getParents().get(1).evaluate(), 3.0f);

    gt = real.isEqual(new Real(3));
    testBooleanOpCommon(gt, true, "==");
    assertSame(gt.getParents().get(0), real);
    assertEquals(gt.getParents().get(1).evaluate(), 3.0f);
  }

  @Test
  public void testIsNotEqual() {
    Bool geq = real.isNotEqual(3);
    testBooleanOpCommon(geq, false, "!=");
    assertSame(geq.getParents().get(0), real);
    assertEquals(geq.getParents().get(1).evaluate(), 3.0f);

    geq = real.isNotEqual(new Real(3));
    testBooleanOpCommon(geq, false, "!=");
    assertSame(geq.getParents().get(0), real);
    assertEquals(geq.getParents().get(1).evaluate(), 3.0f);
  }
}
