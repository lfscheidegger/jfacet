package com.lfscheidegger.jfacet.shade.compiler;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExpression;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code TopologicalSorter}.
 */
public class TopologicalSorterTest {

  @Test
  public void testEmptySorting() {
    TopologicalSorter sorter = new TopologicalSorter();
    assertEquals(sorter.sort(), ImmutableList.<Expression>of());
  }

  @Test
  public void testSingleRowSorting() {
    Expression a = new FloatExpression(3);
    Expression b = new FloatExpression(a);
    Expression c = new FloatExpression(b);
    Expression d = new FloatExpression(c);
    Expression e = new FloatExpression(d);

    TopologicalSorter sorter = new TopologicalSorter(e);
    assertEquals(sorter.sort(), ImmutableList.of(a, b, c, d, e));
  }

  @Test
  public void testDoubleParentSorting() {
    Expression a = new FloatExpression(3);
    Expression b = new FloatExpression(a);
    Expression c = new FloatExpression(a);
    Expression d = Shade.add(b, c);

    TopologicalSorter sorter = new TopologicalSorter(d);
    assertEquals(sorter.sort(), ImmutableList.of(a, b, c, d));
  }
}
