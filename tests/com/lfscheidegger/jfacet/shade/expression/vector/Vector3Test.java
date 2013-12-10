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
 * Unit tests for {@code Vector3}
 */
public class Vector3Test {

  private final Vector3 mVec = new Vector3(1, 2, 3);

  @Test
  public void testConstructors() {
    Vector3 vec = new Vector3(1, 2, 3);

    assertTrue(vec.getPrimitive().isPresent());
    assertEquals(vec.getPrimitive().get(), new Vector3.Primitive(1, 2, 3));
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertFalse(vec.getNodeType().isPresent());
    assertEquals(vec.getParents(), ImmutableList.of());

    Real x = new Real(1), y = new Real(2), z = new Real(3);
    vec = new Vector3(x, y, z);

    assertFalse(vec.getPrimitive().isPresent());
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertTrue(vec.getNodeType().isPresent());
    assertEquals(vec.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(vec.getParents(), ImmutableList.of(x, y, z));

    vec = new Vector3(ImmutableList.<Expression>of(x, y, z), Expression.NodeType.CONS);

    assertFalse(vec.getPrimitive().isPresent());
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertTrue(vec.getNodeType().isPresent());
    assertEquals(vec.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(vec.getParents(), ImmutableList.of(x, y, z));

    vec = new Vector3(new AttribBuffer(new float[] {0, 0, 1, 0, 1, 1}, 2));

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
    Vector3 vec = new Vector3(1, 2, 3);
    Real x = vec.getX();
    testGetter(x, 0, ImmutableList.<Expression>of(vec));

    Real y = vec.getY();
    testGetter(y, 1, ImmutableList.<Expression>of(vec));

    Real z = vec.getZ();
    testGetter(z, 2, ImmutableList.<Expression>of(vec));

    x = vec.get(0);
    testGetter(x, 0, ImmutableList.<Expression>of(vec));

    y = vec.get(1);
    testGetter(y, 1, ImmutableList.<Expression>of(vec));

    z = vec.get(2);
    testGetter(z, 2, ImmutableList.<Expression>of(vec));
  }

  private void testNonLeafVector(Vector3 vec) {
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
      Vector3 vecWithFloat,
      Vector3 vecWithReal,
      Real real,
      Vector3 vecWithVec,
      Vector3 rhs,
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
    Vector3 vec = mVec.add(1);
    Real real = new Real(1);
    Vector3 rhs = new Vector3(1, 2, 3);

    testArithmetic(vec, mVec.add(real), real, mVec.add(rhs), rhs, Expression.NodeType.ADD);
  }

  @Test
  public void testSub() {
    Vector3 vec = mVec.sub(1);
    Real real = new Real(1);
    Vector3 rhs = new Vector3(1, 2, 3);

    testArithmetic(vec, mVec.sub(real), real, mVec.sub(rhs), rhs, Expression.NodeType.SUB);
  }

  @Test
  public void testMul() {
    Vector3 vec = mVec.mul(1);
    Real real = new Real(1);
    Vector3 rhs = new Vector3(1, 2, 3);

    testArithmetic(vec, mVec.mul(real), real, mVec.mul(rhs), rhs, Expression.NodeType.MUL);
  }

  @Test
  public void testDiv() {
    Vector3 vec = mVec.div(1);
    Real real = new Real(1);
    Vector3 rhs = new Vector3(1, 2, 3);

    testArithmetic(vec, mVec.div(real), real, mVec.div(rhs), rhs, Expression.NodeType.DIV);
  }

  @Test
  public void testNeg() {
    Vector3 neg = mVec.neg();

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
    Vector3 vec = new Vector3(1, 2, 3);
    Real dot = mVec.dot(vec);

    testNonLeafExpression(dot);
    testFunction(dot, "dot");
    assertEquals(dot.getParents(), ImmutableList.<Expression>of(mVec, vec));
  }

  @Test
  public void testCross() {
    Vector3 vec = new Vector3(2, 3, 4);
    Vector3 cross = mVec.cross(vec);

    testNonLeafVector(cross);
    testFunction(cross, "cross");
    assertEquals(cross.getParents(), ImmutableList.<Expression>of(mVec, vec));
  }

  @Test
  public void testNormalize() {
    Vector3 normalized = mVec.normalize();

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
    Vector3 rhs = new Vector3(1, 2, 3);
    BVector3 lessThan = mVec.isLessThan(rhs);

    testNonLeafExpression(lessThan);
    testFunction(lessThan, "lessThan");
    assertEquals(lessThan.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsLessThanOrEqual() {
    Vector3 rhs = new Vector3(1, 2, 3);
    BVector3 lessThanOrEqual = mVec.isLessThanOrEqual(rhs);

    testNonLeafExpression(lessThanOrEqual);
    testFunction(lessThanOrEqual, "lessThanEqual");
    assertEquals(lessThanOrEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsGreaterThan() {
    Vector3 rhs = new Vector3(1, 2, 3);
    BVector3 greaterThan = mVec.isGreaterThan(rhs);

    testNonLeafExpression(greaterThan);
    testFunction(greaterThan, "greaterThan");
    assertEquals(greaterThan.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsGreaterThanOrEqual() {
    Vector3 rhs = new Vector3(1, 2, 3);
    BVector3 greaterThanOrEqual = mVec.isGreaterThanOrEqual(rhs);

    testNonLeafExpression(greaterThanOrEqual);
    testFunction(greaterThanOrEqual, "greaterThanEqual");
    assertEquals(greaterThanOrEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsEqualComponentwise() {
    Vector3 rhs = new Vector3(1, 2, 3);
    BVector3 equalComponentwise = mVec.isEqualComponentwise(rhs);

    testNonLeafExpression(equalComponentwise);
    testFunction(equalComponentwise, "equal");
    assertEquals(equalComponentwise.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsNotEqualComponentwise() {
    Vector3 rhs = new Vector3(1, 2, 3);
    BVector3 notEqualComponentwise = mVec.isNotEqualComponentwise(rhs);

    testNonLeafExpression(notEqualComponentwise);
    testFunction(notEqualComponentwise, "notEqual");
    assertEquals(notEqualComponentwise.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsEqual() {
    Vector3 rhs = new Vector3(1, 2, 3);
    Bool equal = mVec.isEqual(rhs);

    testNonLeafExpression(equal);
    assertTrue(equal.getNodeType().isPresent());
    assertEquals(equal.getNodeType().get(), Expression.NodeType.EQ);
    assertEquals(equal.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsNotEqual() {
    Vector3 rhs = new Vector3(1, 2, 3);
    Bool notEqual = mVec.isNotEqual(rhs);

    testNonLeafExpression(notEqual);
    assertTrue(notEqual.getNodeType().isPresent());
    assertEquals(notEqual.getNodeType().get(), Expression.NodeType.NEQ);
    assertEquals(notEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testFill() {
    Vector4 defaultValues = new Vector4(1, 2, 3, 4);
    Vector4 fill = mVec.fill(defaultValues);

    testNonLeafExpression(fill);
    assertEquals(fill.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(fill.getParents().size(), 4);
  }
}