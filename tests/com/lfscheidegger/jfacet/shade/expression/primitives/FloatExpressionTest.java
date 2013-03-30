package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.lfscheidegger.jfacet.shade.expression.Expression;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code FloatExpression}.
 */
public class FloatExpressionTest {

  @Test
  public void testConstructors() {
    FloatExpression exp = new FloatExpression(3);

    Expression child = new FloatExpression(3);
    assertTrue((Float) child.evaluate() == 3);
    assertEquals(child.getGlSlExpression(), "float(3.0)");

    child = new FloatExpression(exp);
    assertTrue((Float) child.evaluate() == 3);
  }
}
