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

  public static void testGetter(
      Expression component,
      int expectedComponent,
      ImmutableList<Expression> expectedParents) {
    testNonLeafExpression(component, expectedParents);
    assertTrue(component.getNodeType().get() instanceof Expression.NodeType.ComponentNodeType);
    assertEquals(((Expression.NodeType.ComponentNodeType)
        component.getNodeType().get()).getComponent(), expectedComponent);
  }

  public static void testArithmetic(
      Expression lhs,
      Expression withFloat,
      Expression withReal,
      Real real,
      Expression withSame,
      Expression  rhs,
      Expression.NodeType nodeType) {
    testNonLeafExpression(withFloat);
    assertEquals(withFloat.getNodeType().get(), nodeType);
    assertEquals(withFloat.getParents().size(), 2);
    assertEquals(withFloat.getParents().get(0), lhs);

    testNonLeafExpression(withReal);
    assertEquals(withReal.getNodeType().get(), nodeType);
    assertEquals(withReal.getParents(), ImmutableList.<Expression>of(lhs, real));

    testNonLeafExpression(withSame);
    assertEquals(withSame.getNodeType().get(), nodeType);
    assertEquals(withSame.getParents(), ImmutableList.<Expression>of(lhs, rhs));
  }

  public static void testSwizzle(Expression parent, Expression swizzled, String expectedString) {
    testNonLeafExpression(swizzled);
    assertEquals(swizzled.getParents(), ImmutableList.<Expression>of(parent));
    assertTrue(swizzled.getNodeType().get() instanceof Expression.NodeType.SwizzleNodeType);
    Expression.NodeType.SwizzleNodeType nodeType = (Expression.NodeType.SwizzleNodeType)
        swizzled.getNodeType().get();
    assertEquals(nodeType.getSwizzleString(), expectedString);
  }
}
