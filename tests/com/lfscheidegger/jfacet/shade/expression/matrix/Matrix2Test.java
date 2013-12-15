package com.lfscheidegger.jfacet.shade.expression.matrix;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import org.junit.Test;

import static org.junit.Assert.*;

import static com.lfscheidegger.jfacet.shade.expression.ExpressionTestUtils.*;

/**
 * Unit tests for {@code Matrix2}
 */
public class Matrix2Test {

  private final Matrix2 mMatrix = new Matrix2();

  @Test
  public void testConstructors() {
    Matrix2 matrix = new Matrix2();

    assertTrue(matrix.getPrimitive().isPresent());
    assertEquals(matrix.getPrimitive().get(), new Matrix2.Primitive());
    assertFalse(matrix.getGlSlQualifier().isPresent());
    assertFalse(matrix.getNodeType().isPresent());
    assertEquals(matrix.getParents(), ImmutableList.of());

    Vector2 c0 = new Vector2(1, 2), c1 = new Vector2(3, 4);
    matrix = new Matrix2(c0, c1);

    assertFalse(matrix.getPrimitive().isPresent());
    assertFalse(matrix.getGlSlQualifier().isPresent());
    assertTrue(matrix.getNodeType().isPresent());
    assertEquals(matrix.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(matrix.getParents(), ImmutableList.<Expression>of(c0, c1));

    matrix = new Matrix2(ImmutableList.<Expression>of(c0, c1), Expression.NodeType.CONS);

    assertFalse(matrix.getPrimitive().isPresent());
    assertFalse(matrix.getGlSlQualifier().isPresent());
    assertTrue(matrix.getNodeType().isPresent());
    assertEquals(matrix.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(matrix.getParents(), ImmutableList.<Expression>of(c0, c1));
  }

  @Test
  public void testGetters() {
    Vector2 c0 = mMatrix.getC0();
    testGetter(c0, 0, ImmutableList.<Expression>of(mMatrix));

    Vector2 c1 = mMatrix.getC1();
    testGetter(c1, 1, ImmutableList.<Expression>of(mMatrix));

    c0 = mMatrix.get(0);
    testGetter(c0, 0, ImmutableList.<Expression>of(mMatrix));

    c1 = mMatrix.get(1);
    testGetter(c1, 1, ImmutableList.<Expression>of(mMatrix));
  }

  private void testArithmetic(
      Matrix2 matWithFloat,
      Matrix2 matWithReal,
      Real real,
      Matrix2 matWithMat,
      Matrix2 rhs,
      Expression.NodeType nodeType) {
    testNonLeafExpression(matWithFloat);
    assertEquals(matWithFloat.getNodeType().get(), nodeType);
    assertEquals(matWithFloat.getParents().size(), 2);
    assertEquals(matWithFloat.getParents().get(0), mMatrix);

    testNonLeafExpression(matWithReal);
    assertEquals(matWithReal.getNodeType().get(), nodeType);
    assertEquals(matWithReal.getParents(), ImmutableList.<Expression>of(mMatrix, real));

    testNonLeafExpression(matWithMat);
    assertEquals(matWithMat.getNodeType().get(), nodeType);
    assertEquals(matWithMat.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }

  @Test
  public void testAdd() {
    Matrix2 mat = mMatrix.add(1);
    Real real = new Real(1);
    Matrix2 rhs = new Matrix2();

    testArithmetic(mat, mMatrix.add(real), real, mMatrix.add(rhs), rhs, Expression.NodeType.ADD);
  }

  @Test
  public void testSub() {
    Matrix2 mat = mMatrix.sub(1);
    Real real = new Real(1);
    Matrix2 rhs = new Matrix2();

    testArithmetic(mat, mMatrix.sub(real), real, mMatrix.sub(rhs), rhs, Expression.NodeType.SUB);
  }

  @Test
  public void testMul() {
    Matrix2 mat = mMatrix.mul(1);
    Real real = new Real(1);
    Matrix2 rhs = new Matrix2();

    testArithmetic(mat, mMatrix.mul(real), real, mMatrix.mul(rhs), rhs, Expression.NodeType.MUL);

    Vector2 input = new Vector2(1, 2);
    Vector2 transformed = mMatrix.mul(input);
    testNonLeafExpression(transformed);
    assertEquals(transformed.getNodeType().get(), Expression.NodeType.MUL);
    assertEquals(transformed.getParents(), ImmutableList.<Expression>of(mMatrix, input));
  }

  @Test
  public void testDiv() {
    Matrix2 mat = mMatrix.div(1);
    Real real = new Real(1);
    Matrix2 rhs = new Matrix2();

    testArithmetic(mat, mMatrix.div(real), real, mMatrix.div(rhs), rhs, Expression.NodeType.DIV);
  }

  @Test
  public void testNeg() {
    Matrix2 neg = mMatrix.neg();

    testNonLeafExpression(neg);
    assertEquals(neg.getNodeType().get(), Expression.NodeType.NEG);
    assertEquals(neg.getParents(), ImmutableList.<Expression>of(mMatrix));
  }

  @Test
  public void testIsEqual() {
    Matrix2 rhs = new Matrix2();
    Bool isEqual = mMatrix.isEqual(rhs);

    testNonLeafExpression(isEqual);
    assertEquals(isEqual.getNodeType().get(), Expression.NodeType.EQ);
    assertEquals(isEqual.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }

  @Test
  public void testIsNotEqual() {
    Matrix2 rhs = new Matrix2();
    Bool isNotEqual = mMatrix.isNotEqual(rhs);

    testNonLeafExpression(isNotEqual);
    assertEquals(isNotEqual.getNodeType().get(), Expression.NodeType.NEQ);
    assertEquals(isNotEqual.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }

  @Test
  public void testMatrixCompMult() {
    Matrix2 rhs = new Matrix2();
    Matrix2 matrixCompMult = mMatrix.matrixCompMult(rhs);

    testNonLeafExpression(matrixCompMult);
    assertTrue(matrixCompMult.getNodeType().isPresent());
    assertTrue(matrixCompMult.getNodeType().get() instanceof Expression.NodeType.FunctionNodeType);

    Expression.NodeType.FunctionNodeType nodeType =
        (Expression.NodeType.FunctionNodeType)matrixCompMult.getNodeType().get();
    assertEquals(nodeType.getFunctionName(), "matrixCompMult");

    assertEquals(matrixCompMult.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }
}
