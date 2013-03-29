package com.lfscheidegger.jfacet.shade;

import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExpression;
import com.lfscheidegger.jfacet.shade.primitives.*;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Unit tests for {@code Shade}
 */
public class ShadeTest {

  @Test
  public void testVec2() {
    Expression vec2Exp = Shade.vec(new Vec2(1, 2));
    assertEquals(vec2Exp.evaluate(), new Vec2(1, 2));

    vec2Exp = Shade.vec(1f, 2f);
    assertEquals(vec2Exp.evaluate(), new Vec2(1, 2));

    FloatExpression f = new FloatExpression(1);
    vec2Exp = Shade.vec(f, 2f);
    assertEquals(vec2Exp.evaluate(), new Vec2(1, 2));
  }

  @Test
  public void testVec3() {
    Expression vec3Exp = Shade.vec(new Vec3(1, 2, 3));
    assertEquals(vec3Exp.evaluate(), new Vec3(1, 2, 3));

    vec3Exp = Shade.vec(1f, 2f, 3f);
    assertEquals(vec3Exp.evaluate(), new Vec3(1, 2, 3));

    FloatExpression f = new FloatExpression(1);
    vec3Exp = Shade.vec(f, 2f, 3f);
    assertEquals(vec3Exp.evaluate(), new Vec3(1, 2, 3));
  }

  @Test
  public void testVec4() {
    Expression vec4Exp = Shade.vec(new Vec4(1, 2, 3, 4));
    assertEquals(vec4Exp.evaluate(), new Vec4(1, 2, 3, 4));

    vec4Exp = Shade.vec(1f, 2f, 3f, 4f);
    assertEquals(vec4Exp.evaluate(), new Vec4(1, 2, 3, 4));

    FloatExpression f = new FloatExpression(1);
    vec4Exp = Shade.vec(f, 2f, 3f, 4f);
    assertEquals(vec4Exp.evaluate(), new Vec4(1, 2, 3, 4));
  }

  @Test
  public void testMat2() {
    Expression mat2Exp = Shade.mat(new Mat2());
    assertEquals(mat2Exp.evaluate(), new Mat2());

    mat2Exp = Shade.mat(new Vec2(1, 2), new Vec2(3, 4));
    assertEquals(mat2Exp.evaluate(), new Mat2(new Vec2(1, 2), new Vec2(3, 4)));
  }

  @Test
  public void testMat3() {
    Expression mat3Exp = Shade.mat(new Mat3());
    assertEquals(mat3Exp.evaluate(), new Mat3());

    mat3Exp = Shade.mat(new Vec3(1, 2, 3), new Vec3(4, 5, 6), new Vec3(7, 8, 9));
    assertEquals(mat3Exp.evaluate(), new Mat3(new Vec3(1, 2, 3), new Vec3(4, 5, 6), new Vec3(7, 8, 9)));
  }

  @Test
  public void testMat4() {
    Expression mat4Exp = Shade.mat(new Mat4());
    assertEquals(mat4Exp.evaluate(), new Mat4());

    mat4Exp = Shade.mat(new Vec4(1, 2, 3, 4), new Vec4(5, 6, 7, 8), new Vec4(9, 10, 11, 12), new Vec4(13, 14, 15, 16));
    assertEquals(
        mat4Exp.evaluate(),
        new Mat4(new Vec4(1, 2, 3, 4), new Vec4(5, 6, 7, 8), new Vec4(9, 10, 11, 12), new Vec4(13, 14, 15, 16)));
  }
}
