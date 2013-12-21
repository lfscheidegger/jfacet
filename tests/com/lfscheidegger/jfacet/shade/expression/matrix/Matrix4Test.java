package com.lfscheidegger.jfacet.shade.expression.matrix;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.NodeType;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;
import org.junit.Test;

import static com.lfscheidegger.jfacet.shade.expression.ExpressionTestUtils.*;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Matrix4}
 */
public class Matrix4Test {

  private final Matrix4 mMatrix = new Matrix4();

  @Test
  public void testConstructors() {
    Matrix4 matrix = new Matrix4();

    assertEquals(
        ((NodeType.PrimitiveNodeType) matrix.getNodeType()).getPrimitive(),
        new Matrix4.Primitive());
    assertEquals(matrix.getParents(), ImmutableList.of());

    Vector4 c0 = new Vector4(1, 2, 3, 4),
        c1 = new Vector4(3, 4, 5, 6),
        c2 = new Vector4(4, 5, 6, 7),
        c3 = new Vector4(5, 6, 7, 8);
    matrix = new Matrix4(c0, c1, c2, c3);

    assertEquals(matrix.getNodeType(), NodeType.CONS);
    assertEquals(matrix.getParents(), ImmutableList.<Expression>of(c0, c1, c2, c3));

    matrix = new Matrix4(ImmutableList.<Expression>of(c0, c1, c2, c3), NodeType.CONS);

    assertEquals(matrix.getNodeType(), NodeType.CONS);
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

    testArithmetic(mMatrix, mat, mMatrix.add(real), real, mMatrix.add(rhs), rhs, NodeType.ADD);
  }

  @Test
  public void testSub() {
    Matrix4 mat = mMatrix.sub(1);
    Real real = new Real(1);
    Matrix4 rhs = new Matrix4();

    testArithmetic(mMatrix, mat, mMatrix.sub(real), real, mMatrix.sub(rhs), rhs, NodeType.SUB);
  }

  @Test
  public void testMul() {
    Matrix4 mat = mMatrix.mul(1);
    Real real = new Real(1);
    Matrix4 rhs = new Matrix4();

    testArithmetic(mMatrix, mat, mMatrix.mul(real), real, mMatrix.mul(rhs), rhs, NodeType.MUL);

    Vector4 input = new Vector4(1, 2, 3, 4);
    Vector4 transformed = mMatrix.mul(input);
    assertEquals(transformed.getNodeType(), NodeType.MUL);
    assertEquals(transformed.getParents(), ImmutableList.<Expression>of(mMatrix, input));
  }

  @Test
  public void testDiv() {
    Matrix4 mat = mMatrix.div(1);
    Real real = new Real(1);
    Matrix4 rhs = new Matrix4();

    testArithmetic(mMatrix, mat, mMatrix.div(real), real, mMatrix.div(rhs), rhs, NodeType.DIV);
  }

  @Test
  public void testNeg() {
    Matrix4 neg = mMatrix.neg();

    assertEquals(neg.getNodeType(), NodeType.NEG);
    assertEquals(neg.getParents(), ImmutableList.<Expression>of(mMatrix));
  }

  @Test
  public void testIsEqual() {
    Matrix4 rhs = new Matrix4();
    Bool isEqual = mMatrix.isEqual(rhs);

    assertEquals(isEqual.getNodeType(), NodeType.EQ);
    assertEquals(isEqual.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }

  @Test
  public void testIsNotEqual() {
    Matrix4 rhs = new Matrix4();
    Bool isNotEqual = mMatrix.isNotEqual(rhs);

    assertEquals(isNotEqual.getNodeType(), NodeType.NEQ);
    assertEquals(isNotEqual.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }

  @Test
  public void testMatrixCompMult() {
    Matrix4 rhs = new Matrix4();
    Matrix4 matrixCompMult = mMatrix.matrixCompMult(rhs);

    testFunction(matrixCompMult, "matrixCompMult");
    assertEquals(matrixCompMult.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }
}
