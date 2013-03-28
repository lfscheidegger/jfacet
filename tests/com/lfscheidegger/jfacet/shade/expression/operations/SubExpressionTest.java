package com.lfscheidegger.jfacet.shade.expression.operations;

import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.types.*;
import com.lfscheidegger.jfacet.shade.primitives.types.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@code AddExpression}.
 */
public class SubExpressionTest {

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

  private <T> void subtractionHelper(Expression left, Expression right, T expectedResult) {
    assertTrue((new SubExpression(left, right).evaluate()).equals(expectedResult));
  }
}
