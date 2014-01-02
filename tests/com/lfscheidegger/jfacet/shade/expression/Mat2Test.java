package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static com.lfscheidegger.jfacet.shade.expression.ExpressionTestUtils.*;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Mat2}
 */
public class Mat2Test {

  private final Mat2 mMatrix = new Mat2();

  @Test
  public void testConstructors() {
    Mat2 matrix = new Mat2();

    assertEquals(
        ((NodeType.PrimitiveNodeType) matrix.getNodeType()).getPrimitive(),
        new Mat2.Primitive());

    assertEquals(matrix.getParents(), ImmutableList.of());

    Vec2 c0 = new Vec2(1, 2), c1 = new Vec2(3, 4);
    matrix = new Mat2(c0, c1);

    assertEquals(matrix.getNodeType(), NodeType.CONS);
    assertEquals(matrix.getParents(), ImmutableList.<Expression>of(c0, c1));

    matrix = new Mat2(ImmutableList.<Expression>of(c0, c1), NodeType.CONS);

    assertEquals(matrix.getNodeType(), NodeType.CONS);
    assertEquals(matrix.getParents(), ImmutableList.<Expression>of(c0, c1));
  }

  @Test
  public void testGetters() {
    Vec2 c0 = mMatrix.getC0();
    testGetter(c0, 0, ImmutableList.<Expression>of(mMatrix));

    Vec2 c1 = mMatrix.getC1();
    testGetter(c1, 1, ImmutableList.<Expression>of(mMatrix));

    c0 = mMatrix.get(0);
    testGetter(c0, 0, ImmutableList.<Expression>of(mMatrix));

    c1 = mMatrix.get(1);
    testGetter(c1, 1, ImmutableList.<Expression>of(mMatrix));
  }

  @Test
  public void testAdd() {
    Mat2 mat = mMatrix.add(1);
    Real real = new Real(1);
    Mat2 rhs = new Mat2();

    testArithmetic(mMatrix, mat, mMatrix.add(real), real, mMatrix.add(rhs), rhs, NodeType.ADD);
  }

  @Test
  public void testSub() {
    Mat2 mat = mMatrix.sub(1);
    Real real = new Real(1);
    Mat2 rhs = new Mat2();

    testArithmetic(mMatrix, mat, mMatrix.sub(real), real, mMatrix.sub(rhs), rhs, NodeType.SUB);
  }

  @Test
  public void testMul() {
    Mat2 mat = mMatrix.mul(1);
    Real real = new Real(1);
    Mat2 rhs = new Mat2();

    testArithmetic(mMatrix, mat, mMatrix.mul(real), real, mMatrix.mul(rhs), rhs, NodeType.MUL);

    Vec2 input = new Vec2(1, 2);
    Vec2 transformed = mMatrix.mul(input);
    assertEquals(transformed.getNodeType(), NodeType.MUL);
    assertEquals(transformed.getParents(), ImmutableList.<Expression>of(mMatrix, input));
  }

  @Test
  public void testDiv() {
    Mat2 mat = mMatrix.div(1);
    Real real = new Real(1);
    Mat2 rhs = new Mat2();

    testArithmetic(mMatrix, mat, mMatrix.div(real), real, mMatrix.div(rhs), rhs, NodeType.DIV);
  }

  @Test
  public void testNeg() {
    Mat2 neg = mMatrix.neg();

    assertEquals(neg.getNodeType(), NodeType.NEG);
    assertEquals(neg.getParents(), ImmutableList.<Expression>of(mMatrix));
  }

  @Test
  public void testIsEqual() {
    Mat2 rhs = new Mat2();
    Bool isEqual = mMatrix.isEqual(rhs);

    assertEquals(isEqual.getNodeType(), NodeType.EQ);
    assertEquals(isEqual.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }

  @Test
  public void testIsNotEqual() {
    Mat2 rhs = new Mat2();
    Bool isNotEqual = mMatrix.isNotEqual(rhs);

    assertEquals(isNotEqual.getNodeType(), NodeType.NEQ);
    assertEquals(isNotEqual.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }

  @Test
  public void testMatrixCompMult() {
    Mat2 rhs = new Mat2();
    Mat2 matrixCompMult = mMatrix.matrixCompMult(rhs);

    testFunction(matrixCompMult, "matrixCompMult");
    assertEquals(matrixCompMult.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }
}
