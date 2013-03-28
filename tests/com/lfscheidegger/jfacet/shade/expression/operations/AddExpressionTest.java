package com.lfscheidegger.jfacet.shade.expression.operations;

import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.*;
import com.lfscheidegger.jfacet.shade.primitives.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code AddExpression}.
 */
public class AddExpressionTest {

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

  private <T> void additionHelper(Expression left, Expression right, T expectedResult) {
    assertTrue((new AddExpression(left, right).evaluate()).equals(expectedResult));
  }
}
