package com.lfscheidegger.jfacet.shade.expression.operations;

import com.lfscheidegger.jfacet.shade.expression.types.*;
import com.lfscheidegger.jfacet.shade.primitives.types.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code NegExpression}
 */
public class NegExpressionTest {

  @Test
  public void testNegFloat() {
    assertEquals(new NegExpression(new FloatExpression(1)).evaluate(), -1f);
  }

  @Test
  public void testNegVec2() {
    assertEquals(new NegExpression(new Vec2Expression(new Vec2(1, 2))).evaluate(), new Vec2(1, 2).neg());
  }

  @Test
  public void testNegVec3() {
    assertEquals(new NegExpression(new Vec3Expression(new Vec3(1, 2, 3))).evaluate(), new Vec3(1, 2, 3).neg());
  }

  @Test
  public void testNegVec4() {
    assertEquals(new NegExpression(new Vec4Expression(new Vec4(1, 2, 3, 4))).evaluate(), new Vec4(1, 2, 3, 4).neg());
  }

  @Test
  public void testNegMat2() {
    assertEquals(new NegExpression(new Mat2Expression(new Mat2())).evaluate(), new Mat2().neg());
  }

  @Test
  public void testNegMat3() {
    assertEquals(new NegExpression(new Mat3Expression(new Mat3())).evaluate(), new Mat3().neg());
  }

  @Test
  public void testNegMat4() {
    assertEquals(new NegExpression(new Mat4Expression(new Mat4())).evaluate(), new Mat4().neg());
  }
}
