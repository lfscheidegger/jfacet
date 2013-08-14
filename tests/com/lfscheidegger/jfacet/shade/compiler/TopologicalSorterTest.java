package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.Real;
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
    Real a = Shade.constant(2);
    Real b = Shade.neg(a);
    Real c = Shade.neg(b);
    Real d = Shade.neg(c);
    assertEquals(TopologicalSorter.forVertexShaderCompiler().sort(
        ImmutableList.<Expression>of(d)), ImmutableList.<Expression>of(a, b, c, d));
  }

  @Test
  public void testDiamondSort() {
    Real a = Shade.constant(2);
    Real b = Shade.neg(a);
    Real c = Shade.neg(a);
    Real d = Shade.add(b, c);
    assertEquals(TopologicalSorter.forVertexShaderCompiler().sort(
        ImmutableList.<Expression>of(d)), ImmutableList.of(a, b, c, d));
  }
}
