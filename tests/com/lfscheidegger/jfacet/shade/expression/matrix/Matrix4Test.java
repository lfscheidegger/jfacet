package com.lfscheidegger.jfacet.shade.expression.matrix;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;
import org.junit.Test;

import static org.junit.Assert.*;

import static com.lfscheidegger.jfacet.shade.expression.ExpressionTestUtils.*;

/**
 * Unit tests for {@code Matrix4}
 */
public class Matrix4Test {

  private final Matrix4 mMatrix = new Matrix4();

  @Test
  public void testConstructors() {
    Matrix4 matrix = new Matrix4();

    assertTrue(matrix.getPrimitive().isPresent());
    assertEquals(matrix.getPrimitive().get(), new Matrix4.Primitive());
    assertFalse(matrix.getGlSlQualifier().isPresent());
    assertFalse(matrix.getNodeType().isPresent());
    assertEquals(matrix.getParents(), ImmutableList.of());

    Vector4 c0 = new Vector4(1, 2, 3, 4),
        c1 = new Vector4(3, 4, 5, 6),
        c2 = new Vector4(4, 5, 6, 7),
        c3 = new Vector4(5, 6, 7, 8);
    matrix = new Matrix4(c0, c1, c2, c3);

    assertFalse(matrix.getPrimitive().isPresent());
    assertFalse(matrix.getGlSlQualifier().isPresent());
    assertTrue(matrix.getNodeType().isPresent());
    assertEquals(matrix.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(matrix.getParents(), ImmutableList.<Expression>of(c0, c1, c2, c3));

    matrix = new Matrix4(ImmutableList.<Expression>of(c0, c1, c2, c3), Expression.NodeType.CONS);

    assertFalse(matrix.getPrimitive().isPresent());
    assertFalse(matrix.getGlSlQualifier().isPresent());
    assertTrue(matrix.getNodeType().isPresent());
    assertEquals(matrix.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(matrix.getParents(), ImmutableList.<Expression>of(c0, c1, c2, c3));
  }

  @Test
  public void testGetters() {
    Vector4 c0 = mMatrix.getC0();
    testGetter(c0, 0, ImmutableList.<Expression>of(mMatrix));

    Vector4 c1 = mMatrix.getC1();
    testGetter(c1, 1, ImmutableList.<Expression>of(mMatrix));

    Vector4 c2 = mMatrix.getC2();
    testGetter(c2, 2, ImmutableList.<Expression>of(mMatrix));

    Vector4 c3 = mMatrix.getC3();
    testGetter(c3, 3, ImmutableList.<Expression>of(mMatrix));

    c0 = mMatrix.get(0);
    testGetter(c0, 0, ImmutableList.<Expression>of(mMatrix));

    c1 = mMatrix.get(1);
    testGetter(c1, 1, ImmutableList.<Expression>of(mMatrix));

    c2 = mMatrix.get(2);
    testGetter(c2, 2, ImmutableList.<Expression>of(mMatrix));

    c3 = mMatrix.get(3);
    testGetter(c3, 3, ImmutableList.<Expression>of(mMatrix));
  }

  @Test
  public void testAdd() {
    Matrix4 mat = mMatrix.add(1);
    Real real = new Real(1);
    Matrix4 rhs = new Matrix4();

    testArithmetic(mMatrix, mat, mMatrix.add(real), real, mMatrix.add(rhs), rhs, Expression.NodeType.ADD);
  }

  @Test
  public void testSub() {
    Matrix4 mat = mMatrix.sub(1);
    Real real = new Real(1);
    Matrix4 rhs = new Matrix4();

    testArithmetic(mMatrix, mat, mMatrix.sub(real), real, mMatrix.sub(rhs), rhs, Expression.NodeType.SUB);
  }

  @Test
  public void testMul() {
    Matrix4 mat = mMatrix.mul(1);
    Real real = new Real(1);
    Matrix4 rhs = new Matrix4();

    testArithmetic(mMatrix, mat, mMatrix.mul(real), real, mMatrix.mul(rhs), rhs, Expression.NodeType.MUL);

    Vector4 input = new Vector4(1, 2, 3, 4);
    Vector4 transformed = mMatrix.mul(input);
    testNonLeafExpression(transformed);
    assertEquals(transformed.getNodeType().get(), Expression.NodeType.MUL);
    assertEquals(transformed.getParents(), ImmutableList.<Expression>of(mMatrix, input));
  }

  @Test
  public void testDiv() {
    Matrix4 mat = mMatrix.div(1);
    Real real = new Real(1);
    Matrix4 rhs = new Matrix4();

    testArithmetic(mMatrix, mat, mMatrix.div(real), real, mMatrix.div(rhs), rhs, Expression.NodeType.DIV);
  }

  @Test
  public void testNeg() {
    Matrix4 neg = mMatrix.neg();

    testNonLeafExpression(neg);
    assertEquals(neg.getNodeType().get(), Expression.NodeType.NEG);
    assertEquals(neg.getParents(), ImmutableList.<Expression>of(mMatrix));
  }

  @Test
  public void testIsEqual() {
    Matrix4 rhs = new Matrix4();
    Bool isEqual = mMatrix.isEqual(rhs);

    testNonLeafExpression(isEqual);
    assertEquals(isEqual.getNodeType().get(), Expression.NodeType.EQ);
    assertEquals(isEqual.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }

  @Test
  public void testIsNotEqual() {
    Matrix4 rhs = new Matrix4();
    Bool isNotEqual = mMatrix.isNotEqual(rhs);

    testNonLeafExpression(isNotEqual);
    assertEquals(isNotEqual.getNodeType().get(), Expression.NodeType.NEQ);
    assertEquals(isNotEqual.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }

  @Test
  public void testMatrixCompMult() {
    Matrix4 rhs = new Matrix4();
    Matrix4 matrixCompMult = mMatrix.matrixCompMult(rhs);

    testNonLeafExpression(matrixCompMult);
    assertTrue(matrixCompMult.getNodeType().isPresent());
    assertTrue(matrixCompMult.getNodeType().get() instanceof Expression.NodeType.FunctionNodeType);

    Expression.NodeType.FunctionNodeType nodeType =
        (Expression.NodeType.FunctionNodeType)matrixCompMult.getNodeType().get();
    assertEquals(nodeType.getFunctionName(), "matrixCompMult");

    assertEquals(matrixCompMult.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }
}
