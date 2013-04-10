package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
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
    FloatExp a = Shade.constant(2);
    FloatExp b = Shade.neg(a);
    FloatExp c = Shade.neg(b);
    FloatExp d = Shade.neg(c);
    assertEquals(TopologicalSorter.forVertexShaderCompiler().sort(
        ImmutableList.<Expression>of(d)), ImmutableList.<Expression>of(a, b, c, d));
  }

  @Test
  public void testDiamondSort() {
    FloatExp a = Shade.constant(2);
    FloatExp b = Shade.neg(a);
    FloatExp c = Shade.neg(a);
    FloatExp d = Shade.add(b, c);
    assertEquals(TopologicalSorter.forVertexShaderCompiler().sort(
        ImmutableList.<Expression>of(d)), ImmutableList.of(a, b, c, d));
  }
}
