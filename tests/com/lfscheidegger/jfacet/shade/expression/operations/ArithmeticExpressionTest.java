package com.lfscheidegger.jfacet.shade.expression.operations;

import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.*;
import com.lfscheidegger.jfacet.shade.primitives.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@code ArithmeticExpression}.
 */
public class ArithmeticExpressionTest {

  @Test
  public void testAddFloatWithFloat() {
    Expression left = new FloatExpression(3);
    Expression right = new FloatExpression(4);

    additionHelper(left, right, Float.valueOf(7));
  }

  @Test
  public void testAddVec2WithFloat() {
    Expression left = new Vec2Expression(new Vec2(1, 2));
    Expression right = new FloatExpression(1);

    additionHelper(left, right, new Vec2(2, 3));
  }

  @Test
  public void testAddVec2WithVec2() {
    Expression left = new Vec2Expression(new Vec2(1, 2));
    Expression right = new Vec2Expression(new Vec2(1, 2));

    additionHelper(left, right, new Vec2(2, 4));
  }

  @Test
  public void testAddVec3WithFloat() {
    Expression left = new Vec3Expression(new Vec3(1, 2, 3));
    Expression right = new FloatExpression(1);

    additionHelper(left, right, new Vec3(2, 3, 4));
  }

  @Test
  public void testAddVec3WithVec3() {
    Expression left = new Vec3Expression(new Vec3(1, 2, 3));
    Expression right = new Vec3Expression(new Vec3(1, 2, 3));

    additionHelper(left, right, new Vec3(2, 4, 6));
  }

  @Test
  public void testAddVec4WithFloat() {
    Expression left = new Vec4Expression(new Vec4(1, 2, 3, 4));
    Expression right = new FloatExpression(1);

    additionHelper(left, right, new Vec4(2, 3, 4, 5));
  }

  @Test
  public void testAddVec4WithVec4() {
    Expression left = new Vec4Expression(new Vec4(1, 2, 3, 4));
    Expression right = new Vec4Expression(new Vec4(1, 2, 3, 4));

    additionHelper(left, right, new Vec4(2, 4, 6, 8));
  }

  @Test
  public void testAddMat2WithFloat() {
    Expression left = new Mat2Expression(new Mat2());
    Expression right = new FloatExpression(1);

    additionHelper(left, right, new Mat2(new Vec2(2, 1), new Vec2(1, 2)));
  }

  @Test
  public void testAddMat2WithMat2() {
    Expression left = new Mat2Expression(new Mat2());
    Expression right = new Mat2Expression(new Mat2());

    additionHelper(left, right, new Mat2().mul(2));
  }

  @Test
  public void testAddMat3WithFloat() {
    Expression left = new Mat3Expression(new Mat3());
    Expression right = new FloatExpression(1);

    additionHelper(left, right, new Mat3(new Vec3(2, 1, 1), new Vec3(1, 2, 1), new Vec3(1, 1, 2)));
  }

  @Test
  public void testAddMat3WithMat3() {
    Expression left = new Mat3Expression(new Mat3());
    Expression right = new Mat3Expression(new Mat3());

    additionHelper(left, right, new Mat3().mul(2));
  }

  @Test
  public void testAddMat4WithFloat() {
    Expression left = new Mat4Expression(new Mat4());
    Expression right = new FloatExpression(1);

    additionHelper(left, right, new Mat4(
        new Vec4(2, 1, 1, 1), new Vec4(1, 2, 1, 1), new Vec4(1, 1, 2, 1), new Vec4(1, 1, 1, 2)));
  }

  @Test
  public void testAddMat4WithMat4() {
    Expression left = new Mat4Expression(new Mat4());
    Expression right = new Mat4Expression(new Mat4());

    additionHelper(left, right, new Mat4().mul(2));
  }

  @Test
  public void testDivFloatWithFloat() {
    Expression left = new FloatExpression(4);
    Expression right = new FloatExpression(2);

    divisionHelper(left, right, Float.valueOf(2));
  }

  @Test
  public void testDivVec2WithFloat() {
    Vec2 vec = new Vec2(1, 2);
    Expression left = new Vec2Expression(vec);
    Expression right = new FloatExpression(2);

    divisionHelper(left, right, vec.div(2));
  }

  @Test
  public void testDivVec2WithVec2() {
    Expression left = new Vec2Expression(new Vec2(1, 2));
    Expression right = new Vec2Expression(new Vec2(1, 2));

    divisionHelper(left, right, new Vec2(1, 2).div(new Vec2(1, 2)));
  }

  @Test
  public void testDivVec3WithFloat() {
    Expression left = new Vec3Expression(new Vec3(1, 2, 3));
    Expression right = new FloatExpression(2);

    divisionHelper(left, right, new Vec3(1, 2, 3).div(2));
  }

  @Test
  public void testDivVec3WithVec3() {
    Expression left = new Vec3Expression(new Vec3(1, 2, 3));
    Expression right = new Vec3Expression(new Vec3(1, 2, 3));

    divisionHelper(left, right, new Vec3(1, 1, 1));
  }

  @Test
  public void testDivVec4WithFloat() {
    Expression left = new Vec4Expression(new Vec4(1, 2, 3, 4));
    Expression right = new FloatExpression(2);

    divisionHelper(left, right, new Vec4(1, 2, 3, 4).div(2));
  }

  @Test
  public void testDivVec4WithVec4() {
    Expression left = new Vec4Expression(new Vec4(1, 2, 3, 4));
    Expression right = new Vec4Expression(new Vec4(1, 2, 3, 4));

    divisionHelper(left, right, new Vec4(1, 1, 1, 1));
  }

  @Test
  public void testDivMat2WithFloat() {
    Expression left = new Mat2Expression(new Mat2());
    Expression right = new FloatExpression(2);

    divisionHelper(left, right, new Mat2().div(2));
  }

  @Test
  public void testDivMat2WithMat2() {
    Expression left = new Mat2Expression(new Mat2());
    Expression right = new Mat2Expression(new Mat2(new Vec2(1, 2), new Vec2(3, 4)));

    divisionHelper(left, right, new Mat2().div(new Mat2(new Vec2(1, 2), new Vec2(3, 4))));
  }

  @Test
  public void testDivMat3WithFloat() {
    Expression left = new Mat3Expression(new Mat3());
    Expression right = new FloatExpression(2);

    divisionHelper(left, right, new Mat3().div(2));
  }

  @Test
  public void testDivMat3WithMat3() {
    Expression left = new Mat3Expression(new Mat3());
    Expression right = new Mat3Expression(new Mat3(new Vec3(1, 2, 3), new Vec3(4, 5, 6), new Vec3(7, 8, 9)));

    divisionHelper(left, right,
        new Mat3().div(new Mat3(new Vec3(1, 2, 3), new Vec3(4, 5, 6), new Vec3(7, 8, 9))));
  }

  @Test
  public void testDivMat4WithFloat() {
    Expression left = new Mat4Expression(new Mat4());
    Expression right = new FloatExpression(2);

    divisionHelper(left, right, new Mat4().div(2));
  }

  @Test
  public void testDivMat4WithMat4() {
    Expression left = new Mat4Expression(new Mat4());
    Expression right = new Mat4Expression(
        new Mat4(new Vec4(1, 2, 3, 4), new Vec4(5, 6, 7, 8), new Vec4(9, 10, 11, 12), new Vec4(13, 14, 15, 16)));

    divisionHelper(left, right, new Mat4().div(
        new Mat4(new Vec4(1, 2, 3, 4), new Vec4(5, 6, 7, 8), new Vec4(9, 10, 11, 12), new Vec4(13, 14, 15, 16))));
  }

  @Test
  public void testMulFloatWithFloat() {
    Expression left = new FloatExpression(3);
    Expression right = new FloatExpression(4);

    multiplicationHelper(left, right, Float.valueOf(12));
  }

  @Test
  public void testMulVec2WithFloat() {
    Vec2 vec = new Vec2(1, 2);
    Expression left = new Vec2Expression(vec);
    Expression right = new FloatExpression(2);

    multiplicationHelper(left, right, vec.mul(2));
  }

  @Test
  public void testMulVec2WithVec2() {
    Expression left = new Vec2Expression(new Vec2(1, 2));
    Expression right = new Vec2Expression(new Vec2(1, 2));

    multiplicationHelper(left, right, new Vec2(1, 2).mul(new Vec2(1, 2)));
  }

  @Test
  public void testMulVec3WithFloat() {
    Expression left = new Vec3Expression(new Vec3(1, 2, 3));
    Expression right = new FloatExpression(2);

    multiplicationHelper(left, right, new Vec3(1, 2, 3).mul(2));
  }

  @Test
  public void testMulVec3WithVec3() {
    Expression left = new Vec3Expression(new Vec3(1, 2, 3));
    Expression right = new Vec3Expression(new Vec3(1, 2, 3));

    multiplicationHelper(left, right, new Vec3(1, 4, 9));
  }

  @Test
  public void testMulVec4WithFloat() {
    Expression left = new Vec4Expression(new Vec4(1, 2, 3, 4));
    Expression right = new FloatExpression(2);

    multiplicationHelper(left, right, new Vec4(2, 4, 6, 8));
  }

  @Test
  public void testMulVec4WithVec4() {
    Expression left = new Vec4Expression(new Vec4(1, 2, 3, 4));
    Expression right = new Vec4Expression(new Vec4(1, 2, 3, 4));

    multiplicationHelper(left, right, new Vec4(1, 4, 9, 16));
  }

  @Test
  public void testMulMat2WithFloat() {
    Expression left = new Mat2Expression(new Mat2());
    Expression right = new FloatExpression(2);

    multiplicationHelper(left, right, new Mat2().mul(2));
  }

  @Test
  public void testMulMat2WithMat2() {
    Expression left = new Mat2Expression(new Mat2().mul(2));
    Expression right = new Mat2Expression(new Mat2().mul(2));

    multiplicationHelper(left, right, new Mat2().mul(new Mat2().mul(4)));
  }

  @Test
  public void testMulMat3WithFloat() {
    Expression left = new Mat3Expression(new Mat3());
    Expression right = new FloatExpression(2);

    multiplicationHelper(left, right, new Mat3().mul(2));
  }

  @Test
  public void testMulMat3WithMat3() {
    Expression left = new Mat3Expression(new Mat3().mul(2));
    Expression right = new Mat3Expression(new Mat3().mul(2));

    multiplicationHelper(left, right, new Mat3().mul(4));
  }

  @Test
  public void testMulMat4WithFloat() {
    Expression left = new Mat4Expression(new Mat4());
    Expression right = new FloatExpression(2);

    multiplicationHelper(left, right, new Mat4().mul(2));
  }

  @Test
  public void testMulMat4WithMat4() {
    Expression left = new Mat4Expression(new Mat4().mul(2));
    Expression right = new Mat4Expression(new Mat4().mul(2));

    multiplicationHelper(left, right, new Mat4().mul(4));
  }

  @Test
  public void testSubFloatWithFloat() {
    Expression left = new FloatExpression(3);
    Expression right = new FloatExpression(4);

    subtractionHelper(left, right, Float.valueOf(-1));
  }

  @Test
  public void testSubVec2WithFloat() {
    Expression left = new Vec2Expression(new Vec2(1, 2));
    Expression right = new FloatExpression(1);

    subtractionHelper(left, right, new Vec2(0, 1));
  }

  @Test
  public void testSubVec2WithVec2() {
    Expression left = new Vec2Expression(new Vec2(1, 2));
    Expression right = new Vec2Expression(new Vec2(1, 2));

    subtractionHelper(left, right, new Vec2(0, 0));
  }

  @Test
  public void testSubVec3WithFloat() {
    Expression left = new Vec3Expression(new Vec3(1, 2, 3));
    Expression right = new FloatExpression(1);

    subtractionHelper(left, right, new Vec3(0, 1, 2));
  }

  @Test
  public void testSubVec3WithVec3() {
    Expression left = new Vec3Expression(new Vec3(1, 2, 3));
    Expression right = new Vec3Expression(new Vec3(1, 2, 3));

    subtractionHelper(left, right, new Vec3(0, 0, 0));
  }

  @Test
  public void testSubVec4WithFloat() {
    Expression left = new Vec4Expression(new Vec4(1, 2, 3, 4));
    Expression right = new FloatExpression(1);

    subtractionHelper(left, right, new Vec4(0, 1, 2, 3));
  }

  @Test
  public void testSubVec4WithVec4() {
    Expression left = new Vec4Expression(new Vec4(1, 2, 3, 4));
    Expression right = new Vec4Expression(new Vec4(1, 2, 3, 4));

    subtractionHelper(left, right, new Vec4(0, 0, 0, 0));
  }

  @Test
  public void testSubMat2WithFloat() {
    Expression left = new Mat2Expression(new Mat2());
    Expression right = new FloatExpression(1);

    subtractionHelper(left, right, new Mat2(new Vec2(0, -1), new Vec2(-1, 0)));
  }

  @Test
  public void testSubMat2WithMat2() {
    Expression left = new Mat2Expression(new Mat2());
    Expression right = new Mat2Expression(new Mat2());

    subtractionHelper(left, right, new Mat2().mul(0));
  }

  @Test
  public void testSubMat3WithFloat() {
    Expression left = new Mat3Expression(new Mat3());
    Expression right = new FloatExpression(1);

    subtractionHelper(left, right, new Mat3(new Vec3(0, -1, -1), new Vec3(-1, 0, -1), new Vec3(-1, -1, 0)));
  }

  @Test
  public void testSubMat3WithMat3() {
    Expression left = new Mat3Expression(new Mat3());
    Expression right = new Mat3Expression(new Mat3());

    subtractionHelper(left, right, new Mat3().mul(0));
  }

  @Test
  public void testSubMat4WithFloat() {
    Expression left = new Mat4Expression(new Mat4());
    Expression right = new FloatExpression(1);

    subtractionHelper(left, right, new Mat4(
        new Vec4(0, -1, -1, -1), new Vec4(-1, 0, -1, -1), new Vec4(-1, -1, 0, -1), new Vec4(-1, -1, -1, 0)));
  }

  @Test
  public void testSubMat4WithMat4() {
    Expression left = new Mat4Expression(new Mat4());
    Expression right = new Mat4Expression(new Mat4());

    subtractionHelper(left, right, new Mat4().mul(0));
  }

  private <T> void additionHelper(Expression left, Expression right, T expectedResult) {
    assertTrue(ArithmeticExpression.getAddExpression(left, right).evaluate().equals(expectedResult));
  }

  private <T> void divisionHelper(Expression left, Expression right, T expectedResult) {
    assertTrue(ArithmeticExpression.getDivExpression(left, right).evaluate().equals(expectedResult));
  }

  private <T> void multiplicationHelper(Expression left, Expression right, T expectedResult) {
    assertTrue(ArithmeticExpression.getMulExpression(left, right).evaluate().equals(expectedResult));
  }

  private <T> void subtractionHelper(Expression left, Expression right, T expectedResult) {
    assertTrue(ArithmeticExpression.getSubExpression(left, right).evaluate().equals(expectedResult));
  }
}
