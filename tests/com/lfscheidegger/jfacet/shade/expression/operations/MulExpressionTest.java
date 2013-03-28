package com.lfscheidegger.jfacet.shade.expression.operations;

import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.*;
import com.lfscheidegger.jfacet.shade.primitives.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@code AddExpression}.
 */
public class MulExpressionTest {

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

  private <T> void multiplicationHelper(Expression left, Expression right, T expectedResult) {
    assertTrue((new MulExpression(left, right).evaluate()).equals(expectedResult));
  }
}
