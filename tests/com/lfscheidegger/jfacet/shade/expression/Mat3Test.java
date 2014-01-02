package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static com.lfscheidegger.jfacet.shade.expression.ExpressionTestUtils.*;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Mat3}
 */
public class Mat3Test {

  private final Mat3 mMatrix = new Mat3();

  @Test
  public void testConstructors() {
    Mat3 matrix = new Mat3();

    assertEquals(
        ((NodeType.PrimitiveNodeType) matrix.getNodeType()).getPrimitive(),
        new Mat3.Primitive());

    assertEquals(matrix.getParents(), ImmutableList.of());

    Vec3 c0 = new Vec3(1, 2, 3), c1 = new Vec3(3, 4, 5), c2 = new Vec3(4, 5, 6);
    matrix = new Mat3(c0, c1, c2);

    assertEquals(matrix.getNodeType(), NodeType.CONS);
    assertEquals(matrix.getParents(), ImmutableList.<Expression>of(c0, c1, c2));

    matrix = new Mat3(ImmutableList.<Expression>of(c0, c1, c2), NodeType.CONS);

    assertEquals(matrix.getNodeType(), NodeType.CONS);
    assertEquals(matrix.getParents(), ImmutableList.<Expression>of(c0, c1, c2));
  }

  @Test
  public void testGetters() {
    Vec3 c0 = mMatrix.getC0();
    testGetter(c0, 0, ImmutableList.<Expression>of(mMatrix));

    Vec3 c1 = mMatrix.getC1();
    testGetter(c1, 1, ImmutableList.<Expression>of(mMatrix));

    Vec3 c2 = mMatrix.getC2();
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
    Mat3 mat = mMatrix.add(1);
    Real real = new Real(1);
    Mat3 rhs = new Mat3();

    testArithmetic(mMatrix, mat, mMatrix.add(real), real, mMatrix.add(rhs), rhs, NodeType.ADD);
  }

  @Test
  public void testSub() {
    Mat3 mat = mMatrix.sub(1);
    Real real = new Real(1);
    Mat3 rhs = new Mat3();

    testArithmetic(mMatrix, mat, mMatrix.sub(real), real, mMatrix.sub(rhs), rhs, NodeType.SUB);
  }

  @Test
  public void testMul() {
    Mat3 mat = mMatrix.mul(1);
    Real real = new Real(1);
    Mat3 rhs = new Mat3();

    testArithmetic(mMatrix, mat, mMatrix.mul(real), real, mMatrix.mul(rhs), rhs, NodeType.MUL);

    Vec3 input = new Vec3(1, 2, 3);
    Vec3 transformed = mMatrix.mul(input);
    assertEquals(transformed.getNodeType(), NodeType.MUL);
    assertEquals(transformed.getParents(), ImmutableList.<Expression>of(mMatrix, input));
  }

  @Test
  public void testDiv() {
    Mat3 mat = mMatrix.div(1);
    Real real = new Real(1);
    Mat3 rhs = new Mat3();

    testArithmetic(mMatrix, mat, mMatrix.div(real), real, mMatrix.div(rhs), rhs, NodeType.DIV);
  }

  @Test
  public void testNeg() {
    Mat3 neg = mMatrix.neg();

    assertEquals(neg.getNodeType(), NodeType.NEG);
    assertEquals(neg.getParents(), ImmutableList.<Expression>of(mMatrix));
  }

  @Test
  public void testIsEqual() {
    Mat3 rhs = new Mat3();
    Bool isEqual = mMatrix.isEqual(rhs);

    assertEquals(isEqual.getNodeType(), NodeType.EQ);
    assertEquals(isEqual.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }

  @Test
  public void testIsNotEqual() {
    Mat3 rhs = new Mat3();
    Bool isNotEqual = mMatrix.isNotEqual(rhs);

    assertEquals(isNotEqual.getNodeType(), NodeType.NEQ);
    assertEquals(isNotEqual.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }

  @Test
  public void testMatrixCompMult() {
    Mat3 rhs = new Mat3();
    Mat3 matrixCompMult = mMatrix.matrixCompMult(rhs);

    testFunction(matrixCompMult, "matrixCompMult");
    assertEquals(matrixCompMult.getParents(), ImmutableList.<Expression>of(mMatrix, rhs));
  }
}
