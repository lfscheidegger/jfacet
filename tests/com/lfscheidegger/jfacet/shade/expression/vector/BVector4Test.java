package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import org.junit.Test;

import javax.annotation.Nullable;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code BVector4}
 */
public class BVector4Test {

  private final BVector4 mVec = new BVector4(true, false, false, false);

  @Test
  public void testConstructors() {
    assertTrue(mVec.getPrimitive().isPresent());
    assertEquals(mVec.getPrimitive().get(), new BVector4.Primitive(true, false, false, false));
    assertFalse(mVec.getGlSlQualifier().isPresent());
    assertFalse(mVec.getNodeType().isPresent());
    assertEquals(mVec.getParents(), ImmutableList.of());

    Bool x = new Bool(true), y = new Bool(false), z = new Bool(false), w = new Bool(false);
    BVector4 vec = new BVector4(x, y, z, w);
    testNonLeafExpression(vec, ImmutableList.<Expression>of(x, y, z, w));
    assertEquals(vec.getNodeType().get(), Expression.NodeType.CONS);

    vec = new BVector4(ImmutableList.<Expression>of(x, y, z, w), Expression.NodeType.CONS);
    testNonLeafExpression(vec, ImmutableList.<Expression>of(x, y, z, w));
    assertEquals(vec.getNodeType().get(), Expression.NodeType.CONS);
  }

  private void testNonLeafExpression(
      Expression exp,
      @Nullable ImmutableList<Expression> expectedParents) {
    assertFalse(exp.getPrimitive().isPresent());
    assertFalse(exp.getGlSlQualifier().isPresent());
    assertTrue(exp.getNodeType().isPresent());

    if (expectedParents != null) {
      assertEquals(exp.getParents(), expectedParents);
    }
  }

  @Test
  public void testGetters() {
    testGetter(mVec.getX(), 0, ImmutableList.<Expression>of(mVec));
    testGetter(mVec.getY(), 1, ImmutableList.<Expression>of(mVec));
    testGetter(mVec.getZ(), 2, ImmutableList.<Expression>of(mVec));
    testGetter(mVec.getW(), 3, ImmutableList.<Expression>of(mVec));
    testGetter(mVec.get(0), 0, ImmutableList.<Expression>of(mVec));
    testGetter(mVec.get(1), 1, ImmutableList.<Expression>of(mVec));
    testGetter(mVec.get(2), 2, ImmutableList.<Expression>of(mVec));
    testGetter(mVec.get(3), 3, ImmutableList.<Expression>of(mVec));
  }

  private void testGetter(
      Bool component,
      int expectedComponent,
      ImmutableList<Expression> expectedParents) {

    testNonLeafExpression(component, expectedParents);
    assertTrue(component.getNodeType().get() instanceof Expression.NodeType.ComponentNodeType);
    assertEquals(((Expression.NodeType.ComponentNodeType)
        component.getNodeType().get()).getComponent(), expectedComponent);
  }

  @Test
  public void testAny() {
    Bool any = mVec.any();
    testNonLeafExpression(any, ImmutableList.<Expression>of(mVec));
    assertTrue(any.getNodeType().get() instanceof Expression.NodeType.FunctionNodeType);
    Expression.NodeType.FunctionNodeType nodeType =
        (Expression.NodeType.FunctionNodeType)any.getNodeType().get();
    assertEquals(nodeType.getFunctionName(), "any");
  }

  @Test
  public void testAll() {
    Bool all = mVec.all();
    testNonLeafExpression(all, ImmutableList.<Expression>of(mVec));
    assertTrue(all.getNodeType().get() instanceof Expression.NodeType.FunctionNodeType);
    Expression.NodeType.FunctionNodeType nodeType =
        (Expression.NodeType.FunctionNodeType)all.getNodeType().get();
    assertEquals(nodeType.getFunctionName(), "all");
  }

  @Test
  public void testNot() {
    BVector4 not = mVec.not();
    testNonLeafExpression(not, ImmutableList.<Expression>of(mVec));
    assertTrue(not.getNodeType().get() instanceof Expression.NodeType.FunctionNodeType);
    Expression.NodeType.FunctionNodeType nodeType =
        (Expression.NodeType.FunctionNodeType)not.getNodeType().get();
    assertEquals(nodeType.getFunctionName(), "not");
  }

  @Test
  public void testFill() {
    assertSame(mVec.fill(null), mVec);
  }
}
