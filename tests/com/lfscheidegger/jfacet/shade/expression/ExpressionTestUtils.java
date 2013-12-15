package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;

import static org.junit.Assert.*;

/**
 * This class contains some static utilities for testing {@code Expression} objects
 */
public class ExpressionTestUtils {

  public static void testNonLeafExpression(
      Expression expression,
      ImmutableList<Expression> expectedParents) {
    testNonLeafExpression(expression);

    assertEquals(expression.getParents(), expectedParents);
  }

  public static void testNonLeafExpression(Expression expression) {
    assertFalse(expression.getPrimitive().isPresent());
    assertFalse(expression.getGlSlQualifier().isPresent());
    assertTrue(expression.getNodeType().isPresent());
  }
}
