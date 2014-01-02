package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static com.lfscheidegger.jfacet.shade.expression.ExpressionTestUtils.*;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Mat4}
 */
public class Mat4Test {

  private final Mat4 mMatrix = new Mat4();

  @Test
  public void testConstructors() {
    Mat4 matrix = new Mat4();

    assertEquals(
        ((NodeType.PrimitiveNodeType) matrix.getNodeType()).getPrimitive(),
        new Mat4.Primitive());
    assertEquals(matrix.getParents(), ImmutableList.of());

    Vec4 c0 = new Vec4(1, 2, 3, 4),
        c1 = new Vec4(3, 4, 5, 6),
        c2 = new Vec4(4, 5, 6, 7),
        c3 = new Vec4(5, 6, 7, 8);
    matrix = new Mat4(c0, c1, c2, c3);

    assertEquals(matrix.getNodeType(), NodeType.CONS);
    assertEquals(matrix.getParents(), ImmutableList.<Expression>of(c0, c1, c2, c3));

    matrix = new Mat4(ImmutableList.<Expression>of(c0, c1, c2, c3), NodeType.CONS);

    assertEquals(matrix.getNodeType(), NodeType.CONS);
    assertEquals(matrix.getParents(), ImmutableList.<Expression>of(c0, c1, c2, c3));
  }

  @Test
  public void testGetters() {
    Vec4 c0 = mMatrix.getC0();
    testGetter(c0, 0, ImmutableList.<Expression>of(mMatrix));

    Vec4 c1 = mMatrix.getC1();
    testGetter(c1, 1, ImmutableList.<Expression>of(mMatrix));

    Vec4 c2 = mMatrix.getC2();
    testGetter(c2, 2, ImmutableList.<Expression>of(mMatrix));

    Vec4 c3 = mMatrix.getC3();
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
    Mat4 mat = mMatrix.plus(1);
    Real real = new Real(1);
    Mat4 rhs = new Mat4();

    testArithmetic(mMatrix, mat, mMatrix.plus(real), real, mMatrix.plus(rhs), rhs, NodeType.ADD);
  }

  @Test
  public void testSub() {
    Mat4 mat = mMatrix.minus(1);
    Real real = new Real(1);
    Mat4 rhs = new Mat4();

    testArithmetic(mMatrix, mat, mMatrix.minus(real), real, mMatrix.minus(rhs), rhs, NodeType.SUB);
  }

  @Test
  public void testMul() {
    Mat4 mat = mMatrix.times(1);
    Real real = new Real(1);
    Mat4 rhs = new Mat4();

    testArithmetic(mMatrix, mat, mMatrix.times(real), real, mMatrix.times(rhs), rhs, NodeType.MUL);

    Vec4 input = new Vec4(1, 2, 3, 4);
    Vec4 transformed = mMatrix.times(input);
    assertEquals(transformed.getNodeType(), NodeType.MUL);
    assertEquals(transformed.getParents(), ImmutableList.<Expression>of(mMatrix, input));
  }

  @Test
  public void testDiv() {
    Mat4 mat = mMatrix.div(1);
    Real real = new Real(1);
    Mat4 rhs = new Mat4();

    testArithmetic(mMatrix, mat, mMatrix.div(real), real, mMatrix.div(rhs), rhs, NodeType.DIV);
  }

  @Test
  public void testNeg() {
    Mat4 neg = mMatrix.negative();

    assertEquals(neg.getNodeType(), NodeType.NEG);
    assertEquals(neg.getParents(), ImmutableList.<Expression>of(mMatrix));
  }

  @Test
  public void testIsEqual() {
    Mat4 rhs = new Mat4();
    Bool isEqual = mMatrix.isEqual(rhs);

    assertEquals(isEqual.getNodeType(), NodeType.EQ);
    assertEquals(isEqual.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }

  @Test
  public void testIsNotEqual() {
    Mat4 rhs = new Mat4();
    Bool isNotEqual = mMatrix.isNotEqual(rhs);

    assertEquals(isNotEqual.getNodeType(), NodeType.NEQ);
    assertEquals(isNotEqual.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }

  @Test
  public void testMatrixCompMult() {
    Mat4 rhs = new Mat4();
    Mat4 matrixCompMult = mMatrix.matrixCompMult(rhs);

    testFunction(matrixCompMult, "matrixCompMult");
    assertEquals(matrixCompMult.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }
}
