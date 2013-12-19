package com.lfscheidegger.jfacet.shade.expression.matrix;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import org.junit.Test;

import static org.junit.Assert.*;

import static com.lfscheidegger.jfacet.shade.expression.ExpressionTestUtils.*;

/**
 * Unit tests for {@code Matrix3}
 */
public class Matrix3Test {

  private final Matrix3 mMatrix = new Matrix3();

  @Test
  public void testConstructors() {
    Matrix3 matrix = new Matrix3();

    assertEquals(
        ((Expression.NodeType.PrimitiveNodeType) matrix.getNodeType().get()).getPrimitive(),
        new Matrix3.Primitive());

    assertEquals(matrix.getParents(), ImmutableList.of());

    Vector3 c0 = new Vector3(1, 2, 3), c1 = new Vector3(3, 4, 5), c2 = new Vector3(4, 5, 6);
    matrix = new Matrix3(c0, c1, c2);

    assertTrue(matrix.getNodeType().isPresent());
    assertEquals(matrix.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(matrix.getParents(), ImmutableList.<Expression>of(c0, c1, c2));

    matrix = new Matrix3(ImmutableList.<Expression>of(c0, c1, c2), Expression.NodeType.CONS);

    assertTrue(matrix.getNodeType().isPresent());
    assertEquals(matrix.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(matrix.getParents(), ImmutableList.<Expression>of(c0, c1, c2));
  }

  @Test
  public void testGetters() {
    Vector3 c0 = mMatrix.getC0();
    testGetter(c0, 0, ImmutableList.<Expression>of(mMatrix));

    Vector3 c1 = mMatrix.getC1();
    testGetter(c1, 1, ImmutableList.<Expression>of(mMatrix));

    Vector3 c2 = mMatrix.getC2();
    testGetter(c2, 2, ImmutableList.<Expression>of(mMatrix));

    c0 = mMatrix.get(0);
    testGetter(c0, 0, ImmutableList.<Expression>of(mMatrix));

    c1 = mMatrix.get(1);
    testGetter(c1, 1, ImmutableList.<Expression>of(mMatrix));

    c2 = mMatrix.get(2);
    testGetter(c2, 2, ImmutableList.<Expression>of(mMatrix));
  }

  @Test
  public void testAdd() {
    Matrix3 mat = mMatrix.add(1);
    Real real = new Real(1);
    Matrix3 rhs = new Matrix3();

    testArithmetic(mMatrix, mat, mMatrix.add(real), real, mMatrix.add(rhs), rhs, Expression.NodeType.ADD);
  }

  @Test
  public void testSub() {
    Matrix3 mat = mMatrix.sub(1);
    Real real = new Real(1);
    Matrix3 rhs = new Matrix3();

    testArithmetic(mMatrix, mat, mMatrix.sub(real), real, mMatrix.sub(rhs), rhs, Expression.NodeType.SUB);
  }

  @Test
  public void testMul() {
    Matrix3 mat = mMatrix.mul(1);
    Real real = new Real(1);
    Matrix3 rhs = new Matrix3();

    testArithmetic(mMatrix, mat, mMatrix.mul(real), real, mMatrix.mul(rhs), rhs, Expression.NodeType.MUL);

    Vector3 input = new Vector3(1, 2, 3);
    Vector3 transformed = mMatrix.mul(input);
    testNonLeafExpression(transformed);
    assertEquals(transformed.getNodeType().get(), Expression.NodeType.MUL);
    assertEquals(transformed.getParents(), ImmutableList.<Expression>of(mMatrix, input));
  }

  @Test
  public void testDiv() {
    Matrix3 mat = mMatrix.div(1);
    Real real = new Real(1);
    Matrix3 rhs = new Matrix3();

    testArithmetic(mMatrix, mat, mMatrix.div(real), real, mMatrix.div(rhs), rhs, Expression.NodeType.DIV);
  }

  @Test
  public void testNeg() {
    Matrix3 neg = mMatrix.neg();

    testNonLeafExpression(neg);
    assertEquals(neg.getNodeType().get(), Expression.NodeType.NEG);
    assertEquals(neg.getParents(), ImmutableList.<Expression>of(mMatrix));
  }

  @Test
  public void testIsEqual() {
    Matrix3 rhs = new Matrix3();
    Bool isEqual = mMatrix.isEqual(rhs);

    testNonLeafExpression(isEqual);
    assertEquals(isEqual.getNodeType().get(), Expression.NodeType.EQ);
    assertEquals(isEqual.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }

  @Test
  public void testIsNotEqual() {
    Matrix3 rhs = new Matrix3();
    Bool isNotEqual = mMatrix.isNotEqual(rhs);

    testNonLeafExpression(isNotEqual);
    assertEquals(isNotEqual.getNodeType().get(), Expression.NodeType.NEQ);
    assertEquals(isNotEqual.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }

  @Test
  public void testMatrixCompMult() {
    Matrix3 rhs = new Matrix3();
    Matrix3 matrixCompMult = mMatrix.matrixCompMult(rhs);

    testFunction(matrixCompMult, "matrixCompMult");
    assertEquals(matrixCompMult.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }
}
