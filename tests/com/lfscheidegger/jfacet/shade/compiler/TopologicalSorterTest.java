package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.FloatExpression;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code TopologicalSorter}
 */
public class TopologicalSorterTest {

  @Test
  public void testSimpleSort() {
    Expression exp = Shade.constant(2);

    assertEquals(TopologicalSorter.forVertexShaderCompiler().sort(
        ImmutableList.of(exp)), ImmutableList.<Expression>of(exp));
  }

  @Test
  public void testLineSort() {
    FloatExpression a = Shade.constant(2);
    FloatExpression b = Shade.neg(a);
    FloatExpression c = Shade.neg(b);
    FloatExpression d = Shade.neg(c);
    assertEquals(TopologicalSorter.forVertexShaderCompiler().sort(
        ImmutableList.<Expression>of(d)), ImmutableList.<Expression>of(a, b, c, d));
  }

  @Test
  public void testDiamondSort() {
    FloatExpression a = Shade.constant(2);
    FloatExpression b = Shade.neg(a);
    FloatExpression c = Shade.neg(a);
    FloatExpression d = Shade.add(b, c);
    assertEquals(TopologicalSorter.forVertexShaderCompiler().sort(
        ImmutableList.<Expression>of(d)), ImmutableList.of(a, b, c, d));
  }
}
