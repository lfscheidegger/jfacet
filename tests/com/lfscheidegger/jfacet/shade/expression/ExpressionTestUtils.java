package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains some static utilities for testing {@code Expression} objects
 */
public class ExpressionTestUtils {

  public static void testNonLeafExpression(
      Expression expression,
      ImmutableList<Expression> expectedParents) {
    assertEquals(expression.getParents(), expectedParents);
  }

  public static void testGetter(
      Expression component,
      int expectedComponent,
      ImmutableList<Expression> expectedParents) {
    testNonLeafExpression(component, expectedParents);
    assertTrue(component.getNodeType() instanceof NodeType.ComponentNodeType);
    assertEquals(((NodeType.ComponentNodeType)
        component.getNodeType()).getComponent(), expectedComponent);
  }

  public static void testArithmetic(
      Expression lhs,
      Expression withFloat,
      Expression withReal,
      Real real,
      Expression withSame,
      Expression  rhs,
      NodeType nodeType) {
    assertEquals(withFloat.getNodeType(), nodeType);
    assertEquals(withFloat.getParents().size(), 2);
    assertEquals(withFloat.getParents().get(0), lhs);

    assertEquals(withReal.getNodeType(), nodeType);
    assertEquals(withReal.getParents(), ImmutableList.<Expression>of(lhs, real));

    assertEquals(withSame.getNodeType(), nodeType);
    assertEquals(withSame.getParents(), ImmutableList.<Expression>of(lhs, rhs));
  }

  public static void testSwizzle(Expression parent, Expression swizzled, String expectedString) {
    assertEquals(swizzled.getParents(), ImmutableList.<Expression>of(parent));
    assertTrue(swizzled.getNodeType() instanceof NodeType.SwizzleNodeType);
    NodeType.SwizzleNodeType nodeType = (NodeType.SwizzleNodeType)
        swizzled.getNodeType();
    assertEquals(nodeType.getSwizzleString(), expectedString);
  }

  public static void testFunction(Expression expression, String functionName) {
    assertTrue(expression.getNodeType() instanceof NodeType.FunctionNodeType);

    NodeType.FunctionNodeType nodeType =
        (NodeType.FunctionNodeType)expression.getNodeType();
    assertEquals(nodeType.getFunctionName(), functionName);
  }
}
