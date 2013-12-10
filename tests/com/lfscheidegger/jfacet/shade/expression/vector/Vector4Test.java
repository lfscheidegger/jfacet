package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.facet.AttribBuffer;
import com.lfscheidegger.jfacet.shade.GlSlQualifier;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.Real;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Vector4}
 */
public class Vector4Test {

  private final Vector4 mVec = new Vector4(1, 2, 3, 4);

  @Test
  public void testConstructors() {
    Vector4 vec = new Vector4(1, 2, 3, 4);

    assertTrue(vec.getPrimitive().isPresent());
    assertEquals(vec.getPrimitive().get(), new Vector4.Primitive(1, 2, 3, 4));
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertFalse(vec.getNodeType().isPresent());
    assertEquals(vec.getParents(), ImmutableList.of());

    Real x = new Real(1), y = new Real(2), z = new Real(3), w = new Real(4);
    vec = new Vector4(x, y, z, w);

    assertFalse(vec.getPrimitive().isPresent());
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertTrue(vec.getNodeType().isPresent());
    assertEquals(vec.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(vec.getParents(), ImmutableList.of(x, y, z, w));

    vec = new Vector4(ImmutableList.<Expression>of(x, y, z, w), Expression.NodeType.CONS);

    assertFalse(vec.getPrimitive().isPresent());
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertTrue(vec.getNodeType().isPresent());
    assertEquals(vec.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(vec.getParents(), ImmutableList.of(x, y, z, w));

    vec = new Vector4(new AttribBuffer(new float[] {0, 0, 1, 0, 1, 1}, 2));

    assertFalse(vec.getPrimitive().isPresent());
    assertTrue(vec.getAttribBuffer().isPresent());
    assertTrue(vec.getGlSlQualifier().isPresent());
    assertEquals(vec.getGlSlQualifier().get(), GlSlQualifier.ATTRIBUTE_T);
    assertFalse(vec.getNodeType().isPresent());
    assertEquals(vec.getParents(), ImmutableList.<Expression>of());
  }

  private void testGetter(
      Real component,
      int expectedComponent,
      ImmutableList<Expression> expectedParents) {

    assertFalse(component.getPrimitive().isPresent());
    assertFalse(component.getAttribBuffer().isPresent());
    assertFalse(component.getGlSlQualifier().isPresent());
    assertTrue(component.getNodeType().isPresent());
    assertTrue(component.getNodeType().get() instanceof Expression.NodeType.ComponentNodeType);

    assertEquals(((Expression.NodeType.ComponentNodeType)
        component.getNodeType().get()).getComponent(), expectedComponent);
    assertEquals(component.getParents(), expectedParents);
  }

  @Test
  public void testGetters() {
    Vector4 vec = new Vector4(1, 2, 3, 4);
    Real x = vec.getX();
    testGetter(x, 0, ImmutableList.<Expression>of(vec));

    Real y = vec.getY();
    testGetter(y, 1, ImmutableList.<Expression>of(vec));

    x = vec.get(0);
    testGetter(x, 0, ImmutableList.<Expression>of(vec));

    y = vec.get(1);
    testGetter(y, 1, ImmutableList.<Expression>of(vec));
  }

  private void testNonLeafVector(Vector4 vec) {
    assertFalse(vec.getPrimitive().isPresent());
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertTrue(vec.getNodeType().isPresent());
  }

  private void testNonLeafExpression(Expression exp) {
    assertFalse(exp.getPrimitive().isPresent());
    assertFalse(exp.getGlSlQualifier().isPresent());
    assertTrue(exp.getNodeType().isPresent());
  }

  private void testArithmetic(
      Vector4 vecWithFloat,
      Vector4 vecWithReal,
      Real real,
      Vector4 vecWithVec,
      Vector4 rhs,
      Expression.NodeType nodeType) {
    testNonLeafVector(vecWithFloat);
    assertEquals(vecWithFloat.getNodeType().get(), nodeType);
    assertEquals(vecWithFloat.getParents().size(), 2);
    assertEquals(vecWithFloat.getParents().get(0), mVec);

    testNonLeafVector(vecWithReal);
    assertEquals(vecWithReal.getNodeType().get(), nodeType);
    assertEquals(vecWithReal.getParents(), ImmutableList.<Expression>of(mVec, real));

    testNonLeafVector(vecWithVec);
    assertEquals(vecWithVec.getNodeType().get(), nodeType);
    assertEquals(vecWithVec.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testAdd() {
    Vector4 vec = mVec.add(1);
    Real real = new Real(1);
    Vector4 rhs = new Vector4(1, 2, 3, 4);

    testArithmetic(vec, mVec.add(real), real, mVec.add(rhs), rhs, Expression.NodeType.ADD);
  }

  @Test
  public void testSub() {
    Vector4 vec = mVec.sub(1);
    Real real = new Real(1);
    Vector4 rhs = new Vector4(1, 2, 3, 4);

    testArithmetic(vec, mVec.sub(real), real, mVec.sub(rhs), rhs, Expression.NodeType.SUB);
  }

  @Test
  public void testMul() {
    Vector4 vec = mVec.mul(1);
    Real real = new Real(1);
    Vector4 rhs = new Vector4(1, 2, 3, 4);

    testArithmetic(vec, mVec.mul(real), real, mVec.mul(rhs), rhs, Expression.NodeType.MUL);
  }

  @Test
  public void testDiv() {
    Vector4 vec = mVec.div(1);
    Real real = new Real(1);
    Vector4 rhs = new Vector4(1, 2, 3, 4);

    testArithmetic(vec, mVec.div(real), real, mVec.div(rhs), rhs, Expression.NodeType.DIV);
  }

  @Test
  public void testNeg() {
    Vector4 neg = mVec.neg();

    testNonLeafVector(neg);
    assertEquals(neg.getNodeType().get(), Expression.NodeType.NEG);
    assertEquals(neg.getParents(), ImmutableList.<Expression>of(mVec));
  }

  private void testFunction(
      Expression expression,
      String functionName) {
    assertTrue(expression.getNodeType().isPresent());
    assertTrue(expression.getNodeType().get() instanceof Expression.NodeType.FunctionNodeType);

    Expression.NodeType.FunctionNodeType nodeType =
        (Expression.NodeType.FunctionNodeType)expression.getNodeType().get();
    assertEquals(nodeType.getFunctionName(), functionName);
  }

  @Test
  public void testDot() {
    Vector4 vec = new Vector4(1, 2, 3, 4);
    Real dot = mVec.dot(vec);

    testNonLeafExpression(dot);
    testFunction(dot, "dot");
    assertEquals(dot.getParents(), ImmutableList.<Expression>of(mVec, vec));
  }

  @Test
  public void testNormalize() {
    Vector4 normalized = mVec.normalize();

    testNonLeafVector(normalized);
    testFunction(normalized, "normalize");
    assertEquals(normalized.getParents(), ImmutableList.<Expression>of(mVec));
  }

  @Test
  public void testLength() {
    Real length = mVec.length();

    testNonLeafExpression(length);
    testFunction(length, "length");
    assertEquals(length.getParents(), ImmutableList.<Expression>of(mVec));
  }

  @Test
  public void testIsLessThan() {
    Vector4 rhs = new Vector4(1, 2, 3, 4);
    BVector4 lessThan = mVec.isLessThan(rhs);

    testNonLeafExpression(lessThan);
    testFunction(lessThan, "lessThan");
    assertEquals(lessThan.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsLessThanOrEqual() {
    Vector4 rhs = new Vector4(1, 2, 3, 4);
    BVector4 lessThanOrEqual = mVec.isLessThanOrEqual(rhs);

    testNonLeafExpression(lessThanOrEqual);
    testFunction(lessThanOrEqual, "lessThanEqual");
    assertEquals(lessThanOrEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsGreaterThan() {
    Vector4 rhs = new Vector4(1, 2, 3, 4);
    BVector4 greaterThan = mVec.isGreaterThan(rhs);

    testNonLeafExpression(greaterThan);
    testFunction(greaterThan, "greaterThan");
    assertEquals(greaterThan.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsGreaterThanOrEqual() {
    Vector4 rhs = new Vector4(1, 2, 3, 4);
    BVector4 greaterThanOrEqual = mVec.isGreaterThanOrEqual(rhs);

    testNonLeafExpression(greaterThanOrEqual);
    testFunction(greaterThanOrEqual, "greaterThanEqual");
    assertEquals(greaterThanOrEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsEqualComponentwise() {
    Vector4 rhs = new Vector4(1, 2, 3, 4);
    BVector4 equalComponentwise = mVec.isEqualComponentwise(rhs);

    testNonLeafExpression(equalComponentwise);
    testFunction(equalComponentwise, "equal");
    assertEquals(equalComponentwise.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsNotEqualComponentwise() {
    Vector4 rhs = new Vector4(1, 2, 3, 4);
    BVector4 notEqualComponentwise = mVec.isNotEqualComponentwise(rhs);

    testNonLeafExpression(notEqualComponentwise);
    testFunction(notEqualComponentwise, "notEqual");
    assertEquals(notEqualComponentwise.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsEqual() {
    Vector4 rhs = new Vector4(1, 2, 3, 4);
    Bool equal = mVec.isEqual(rhs);

    testNonLeafExpression(equal);
    assertTrue(equal.getNodeType().isPresent());
    assertEquals(equal.getNodeType().get(), Expression.NodeType.EQ);
    assertEquals(equal.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsNotEqual() {
    Vector4 rhs = new Vector4(1, 2, 3, 4);
    Bool notEqual = mVec.isNotEqual(rhs);

    testNonLeafExpression(notEqual);
    assertTrue(notEqual.getNodeType().isPresent());
    assertEquals(notEqual.getNodeType().get(), Expression.NodeType.NEQ);
    assertEquals(notEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }
}