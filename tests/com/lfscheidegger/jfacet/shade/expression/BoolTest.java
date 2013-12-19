package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Bool}
 */
public class BoolTest {

  Bool mBool = new Bool(false);

  @Test
  public void testConstructors() {
    Bool bool = new Bool(false);

    assertEquals(
        ((Expression.NodeType.PrimitiveNodeType) bool.getNodeType()).getPrimitive(), false);
    assertEquals(bool.getParents(), ImmutableList.of());

    Bool true_ = new Bool(true), false_ = new Bool(false);
    bool = new Bool(ImmutableList.<Expression>of(true_, false_), Expression.NodeType.AND);
    assertEquals(bool.getNodeType(), Expression.NodeType.AND);
    assertEquals(bool.getParents(), ImmutableList.<Expression>of(true_, false_));
  }

  @Test
  public void testAnd() {
    Bool and = mBool.and(new Bool(false));
    assertEquals(and.getNodeType(), Expression.NodeType.AND);

    and = mBool.and(false);
    assertEquals(and.getNodeType(), Expression.NodeType.AND);
  }

  @Test
  public void testOr() {
    Bool or = mBool.or(new Bool(false));
    assertEquals(or.getNodeType(), Expression.NodeType.OR);

    or = mBool.or(false);
    assertEquals(or.getNodeType(), Expression.NodeType.OR);
  }

  @Test
  public void testXor() {
    Bool xor = mBool.xor(new Bool(false));
    assertEquals(xor.getNodeType(), Expression.NodeType.XOR);

    xor = mBool.xor(false);
    assertEquals(xor.getNodeType(), Expression.NodeType.XOR);
  }
}
