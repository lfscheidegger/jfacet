package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static com.lfscheidegger.jfacet.shade.expression.ExpressionTestUtils.testNonLeafExpression;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Real}
 */
public class RealTest {

  private final Real mReal = new Real(1);

  @Test
  public void testConstructors() {
    Real real = new Real(1);

    assertEquals(
        ((NodeType.PrimitiveNodeType) real.getNodeType()).getPrimitive(), 1.0f);

    assertEquals(real.getParents(), ImmutableList.of());

    Real one = new Real(1), two = new Real(2);
    real = new Real(ImmutableList.<Expression>of(one, two), NodeType.ADD);
    assertEquals(real.getNodeType(), NodeType.ADD);
    assertEquals(real.getParents(), ImmutableList.<Expression>of(one, two));
  }

  @Test
  public void testAdd() {
    Real rhs = new Real(2);
    Real add = mReal.plus(rhs);
    testNonLeafExpression(add, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(add.getNodeType(), NodeType.ADD);

    add = mReal.plus(2);
    assertEquals(add.getNodeType(), NodeType.ADD);
  }

  @Test
  public void testSub() {
    Real rhs = new Real(2);
    Real sub = mReal.minus(rhs);
    testNonLeafExpression(sub, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(sub.getNodeType(), NodeType.SUB);

    sub = mReal.minus(2);
    assertEquals(sub.getNodeType(), NodeType.SUB);
  }

  @Test
  public void testMul() {
    Real rhs = new Real(2);
    Real mul = mReal.times(rhs);
    testNonLeafExpression(mul, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(mul.getNodeType(), NodeType.MUL);

    mul = mReal.times(2);
    assertEquals(mul.getNodeType(), NodeType.MUL);
  }

  @Test
  public void testDiv() {
    Real rhs = new Real(2);
    Real div = mReal.div(rhs);
    testNonLeafExpression(div, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(div.getNodeType(), NodeType.DIV);

    div = mReal.div(2);
    assertEquals(div.getNodeType(), NodeType.DIV);
  }

  @Test
  public void testNeg() {
    Real neg = mReal.negative();
    testNonLeafExpression(neg, ImmutableList.<Expression>of(mReal));
    assertEquals(neg.getNodeType(), NodeType.NEG);
  }

  @Test
  public void testGreaterThan() {
    Real rhs = new Real(0);
    Bool gt = mReal.isGreaterThan(rhs);
    testNonLeafExpression(gt, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(gt.getNodeType(), NodeType.GT);

    gt = mReal.isGreaterThan(0);
    assertEquals(gt.getNodeType(), NodeType.GT);
  }

  @Test
  public void testGreaterThanOrEqual() {
    Real rhs = new Real(0);
    Bool geq = mReal.isGreaterThanOrEqual(rhs);
    testNonLeafExpression(geq, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(geq.getNodeType(), NodeType.GEQ);

    geq = mReal.isGreaterThanOrEqual(0);
    assertEquals(geq.getNodeType(), NodeType.GEQ);
  }

  @Test
  public void testLessThan() {
    Real rhs = new Real(0);
    Bool geq = mReal.isLessThan(rhs);
    testNonLeafExpression(geq, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(geq.getNodeType(), NodeType.LT);

    geq = mReal.isLessThan(0);
    assertEquals(geq.getNodeType(), NodeType.LT);
  }

  @Test
  public void testLessThanOrEqual() {
    Real rhs = new Real(0);
    Bool leq = mReal.isLessThanOrEqual(rhs);
    testNonLeafExpression(leq, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(leq.getNodeType(), NodeType.LEQ);

    leq = mReal.isLessThanOrEqual(0);
    assertEquals(leq.getNodeType(), NodeType.LEQ);
  }

  @Test
  public void testIsEqual() {
    Real rhs = new Real(0);
    Bool eq = mReal.isEqual(rhs);
    testNonLeafExpression(eq, ImmutableList.<Expression>of(mReal, rhs));

    eq = mReal.isEqual(0);
    assertEquals(eq.getNodeType(), NodeType.EQ);
  }

  @Test
  public void testIsNotEqual() {
    Real rhs = new Real(0);
    Bool neq = mReal.isNotEqual(rhs);
    testNonLeafExpression(neq, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(neq.getNodeType(), NodeType.NEQ);

    neq = mReal.isNotEqual(0);
    assertEquals(neq.getNodeType(), NodeType.NEQ);
  }
}
