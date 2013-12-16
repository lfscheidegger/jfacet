package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.junit.Assert.*;

import static com.lfscheidegger.jfacet.shade.expression.ExpressionTestUtils.*;

/**
 * Unit tests for {@code Real}
 */
public class RealTest {

  private final Real mReal = new Real(1);

  @Test
  public void testConstructors() {
    Real real = new Real(1);

    assertTrue(real.getPrimitive().isPresent());
    assertTrue(real.getPrimitive().get() == 1);
    assertFalse(real.getGlSlQualifier().isPresent());
    assertFalse(real.getNodeType().isPresent());
    assertEquals(real.getParents(), ImmutableList.of());

    Real one = new Real(1), two = new Real(2);
    real = new Real(ImmutableList.<Expression>of(one, two), Expression.NodeType.ADD);
    assertFalse(real.getPrimitive().isPresent());
    assertFalse(real.getGlSlQualifier().isPresent());
    assertTrue(real.getNodeType().isPresent());
    assertEquals(real.getNodeType().get(), Expression.NodeType.ADD);
    assertEquals(real.getParents(), ImmutableList.<Expression>of(one, two));
  }

  @Test
  public void testAdd() {
    Real rhs = new Real(2);
    Real add = mReal.add(rhs);
    testNonLeafExpression(add, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(add.getNodeType().get(), Expression.NodeType.ADD);

    add = mReal.add(2);
    testNonLeafExpression(add);
    assertEquals(add.getNodeType().get(), Expression.NodeType.ADD);
  }

  @Test
  public void testSub() {
    Real rhs = new Real(2);
    Real sub = mReal.sub(rhs);
    testNonLeafExpression(sub, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(sub.getNodeType().get(), Expression.NodeType.SUB);

    sub = mReal.sub(2);
    testNonLeafExpression(sub);
    assertEquals(sub.getNodeType().get(), Expression.NodeType.SUB);
  }

  @Test
  public void testMul() {
    Real rhs = new Real(2);
    Real mul = mReal.mul(rhs);
    testNonLeafExpression(mul, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(mul.getNodeType().get(), Expression.NodeType.MUL);

    mul = mReal.mul(2);
    testNonLeafExpression(mul);
    assertEquals(mul.getNodeType().get(), Expression.NodeType.MUL);
  }

  @Test
  public void testDiv() {
    Real rhs = new Real(2);
    Real div = mReal.div(rhs);
    testNonLeafExpression(div, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(div.getNodeType().get(), Expression.NodeType.DIV);

    div = mReal.div(2);
    testNonLeafExpression(div);
    assertEquals(div.getNodeType().get(), Expression.NodeType.DIV);
  }

  @Test
  public void testNeg() {
    Real neg = mReal.neg();
    testNonLeafExpression(neg, ImmutableList.<Expression>of(mReal));
    assertEquals(neg.getNodeType().get(), Expression.NodeType.NEG);
  }

  @Test
  public void testGreaterThan() {
    Real rhs = new Real(0);
    Bool gt = mReal.isGreaterThan(rhs);
    testNonLeafExpression(gt, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(gt.getNodeType().get(), Expression.NodeType.GT);

    gt = mReal.isGreaterThan(0);
    testNonLeafExpression(gt);
    assertEquals(gt.getNodeType().get(), Expression.NodeType.GT);
  }

  @Test
  public void testGreaterThanOrEqual() {
    Real rhs = new Real(0);
    Bool geq = mReal.isGreaterThanOrEqual(rhs);
    testNonLeafExpression(geq, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(geq.getNodeType().get(), Expression.NodeType.GEQ);

    geq = mReal.isGreaterThanOrEqual(0);
    testNonLeafExpression(geq);
    assertEquals(geq.getNodeType().get(), Expression.NodeType.GEQ);
  }

  @Test
  public void testLessThan() {
    Real rhs = new Real(0);
    Bool geq = mReal.isLessThan(rhs);
    testNonLeafExpression(geq, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(geq.getNodeType().get(), Expression.NodeType.LT);

    geq = mReal.isLessThan(0);
    testNonLeafExpression(geq);
    assertEquals(geq.getNodeType().get(), Expression.NodeType.LT);
  }

  @Test
  public void testLessThanOrEqual() {
    Real rhs = new Real(0);
    Bool leq = mReal.isLessThanOrEqual(rhs);
    testNonLeafExpression(leq, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(leq.getNodeType().get(), Expression.NodeType.LEQ);

    leq = mReal.isLessThanOrEqual(0);
    testNonLeafExpression(leq);
    assertEquals(leq.getNodeType().get(), Expression.NodeType.LEQ);
  }

  @Test
  public void testIsEqual() {
    Real rhs = new Real(0);
    Bool eq = mReal.isEqual(rhs);
    testNonLeafExpression(eq, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(eq.getNodeType().get(), Expression.NodeType.EQ);

    eq = mReal.isEqual(0);
    testNonLeafExpression(eq);
    assertEquals(eq.getNodeType().get(), Expression.NodeType.EQ);
  }

  @Test
  public void testIsNotEqual() {
    Real rhs = new Real(0);
    Bool neq = mReal.isNotEqual(rhs);
    testNonLeafExpression(neq, ImmutableList.<Expression>of(mReal, rhs));
    assertEquals(neq.getNodeType().get(), Expression.NodeType.NEQ);

    neq = mReal.isNotEqual(0);
    testNonLeafExpression(neq);
    assertEquals(neq.getNodeType().get(), Expression.NodeType.NEQ);
  }
}
