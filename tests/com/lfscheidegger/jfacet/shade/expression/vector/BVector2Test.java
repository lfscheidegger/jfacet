package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import org.junit.Test;

import javax.annotation.Nullable;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code BVector2}
 */
public class BVector2Test {

  private final BVector2 mVec = new BVector2(true, false);

  @Test
  public void testConstructors() {
    assertTrue(mVec.getPrimitive().isPresent());
    assertEquals(mVec.getPrimitive().get(), new BVector2.Primitive(true, false));
    assertFalse(mVec.getGlSlQualifier().isPresent());
    assertFalse(mVec.getNodeType().isPresent());
    assertEquals(mVec.getParents(), ImmutableList.of());

    Bool x = new Bool(true), y = new Bool(false);
    BVector2 vec = new BVector2(x, y);
    testNonLeafExpression(vec, ImmutableList.<Expression>of(x, y));
    assertEquals(vec.getNodeType().get(), Expression.NodeType.CONS);

    vec = new BVector2(ImmutableList.<Expression>of(x, y), Expression.NodeType.CONS);
    testNonLeafExpression(vec, ImmutableList.<Expression>of(x, y));
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
    testGetter(mVec.get(0), 0, ImmutableList.<Expression>of(mVec));
    testGetter(mVec.get(1), 1, ImmutableList.<Expression>of(mVec));
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
    BVector2 not = mVec.not();
    testNonLeafExpression(not, ImmutableList.<Expression>of(mVec));
    assertTrue(not.getNodeType().get() instanceof Expression.NodeType.FunctionNodeType);
    Expression.NodeType.FunctionNodeType nodeType =
        (Expression.NodeType.FunctionNodeType)not.getNodeType().get();
    assertEquals(nodeType.getFunctionName(), "not");
  }

  @Test
  public void testFill() {
    BVector4 defaultExpression = new BVector4(true, true, true, true);
    BVector4 fill = mVec.fill(defaultExpression);
    testNonLeafExpression(fill, null);
    assertEquals(fill.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(fill.getParents().size(), 4);
  }
}
