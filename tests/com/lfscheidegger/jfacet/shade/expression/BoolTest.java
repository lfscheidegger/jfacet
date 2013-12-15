package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import org.junit.Test;

import static org.junit.Assert.*;

import static com.lfscheidegger.jfacet.shade.expression.ExpressionTestUtils.*;

/**
 * Unit tests for {@code Bool}
 */
public class BoolTest {

  Bool mBool = new Bool(false);

  @Test
  public void testConstructors() {
    Bool bool = new Bool(false);

    assertTrue(bool.getPrimitive().isPresent());
    assertEquals(bool.getPrimitive().get(), false);
    assertFalse(bool.getGlSlQualifier().isPresent());
    assertFalse(bool.getNodeType().isPresent());
    assertEquals(bool.getParents(), ImmutableList.of());

    Bool true_ = new Bool(true), false_ = new Bool(false);
    bool = new Bool(ImmutableList.<Expression>of(true_, false_), Expression.NodeType.AND);
    assertFalse(bool.getPrimitive().isPresent());
    assertFalse(bool.getGlSlQualifier().isPresent());
    assertTrue(bool.getNodeType().isPresent());
    assertEquals(bool.getNodeType().get(), Expression.NodeType.AND);
    assertEquals(bool.getParents(), ImmutableList.<Expression>of(true_, false_));
  }

  @Test
  public void testAnd() {
    Bool and = mBool.and(new Bool(false));
    testNonLeafExpression(and);
    assertEquals(and.getNodeType().get(), Expression.NodeType.AND);

    and = mBool.and(false);
    testNonLeafExpression(and);
    assertEquals(and.getNodeType().get(), Expression.NodeType.AND);
  }

  @Test
  public void testOr() {
    Bool or = mBool.or(new Bool(false));
    testNonLeafExpression(or);
    assertEquals(or.getNodeType().get(), Expression.NodeType.OR);

    or = mBool.or(false);
    testNonLeafExpression(or);
    assertEquals(or.getNodeType().get(), Expression.NodeType.OR);
  }

  @Test
  public void testXor() {
    Bool xor = mBool.xor(new Bool(false));
    testNonLeafExpression(xor);
    assertEquals(xor.getNodeType().get(), Expression.NodeType.XOR);

    xor = mBool.xor(false);
    testNonLeafExpression(xor);
    assertEquals(xor.getNodeType().get(), Expression.NodeType.XOR);
  }
}
