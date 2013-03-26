package com.lfscheidegger.jfacet.shade;

import com.lfscheidegger.jfacet.shade.internal.types.Mat2;
import com.lfscheidegger.jfacet.shade.internal.types.Mat3;
import com.lfscheidegger.jfacet.shade.internal.types.Mat4;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Shade.constant}
 */
public class TestShadeConstant {

  @Test
  public void testConstantFloat() {
    assertEquals(((ContainsGlSlExpression)Shade.constant(3.0f)).getGlSlExpression(), "float(3.0)");
  }

  @Test
  public void testConstantVec2() {
    assertEquals(((ContainsGlSlExpression) Shade.constant(1, 2)).getGlSlExpression(), "vec2(1.0, 2.0)");
    assertEquals(((ContainsGlSlExpression)Shade.constant(Shade.vec2(1, 2))).getGlSlExpression(), "vec2(1.0, 2.0)");
  }

  @Test
  public void testConstantVec3() {
    assertEquals(((ContainsGlSlExpression)Shade.constant(1, 2, 3)).getGlSlExpression(), "vec3(1.0, 2.0, 3.0)");
    assertEquals(
        ((ContainsGlSlExpression)Shade.constant(Shade.vec3(1, 2, 3))).getGlSlExpression(),
        "vec3(1.0, 2.0, 3.0)");
  }

  @Test
  public void testConstantVec4() {
    assertEquals(((ContainsGlSlExpression)Shade.constant(1, 2, 3, 4)).getGlSlExpression(), "vec4(1.0, 2.0, 3.0, 4.0)");
    assertEquals(
        ((ContainsGlSlExpression)Shade.constant(Shade.vec4(1, 2, 3, 4))).getGlSlExpression(),
        "vec4(1.0, 2.0, 3.0, 4.0)");
  }

  @Test
  public void testConstantMat2() {
    Mat2 mat = new Mat2();
    assertEquals(((ContainsGlSlExpression)Shade.constant(mat)).getGlSlExpression(),
        "mat2(vec2(1.0, 0.0), vec2(0.0, 1.0))");

    mat = Shade.mat2();
    assertEquals(((ContainsGlSlExpression)Shade.constant(mat)).getGlSlExpression(),
        "mat2(vec2(1.0, 0.0), vec2(0.0, 1.0))");
  }

  @Test
  public void testConstantMat3() {
    Mat3 mat = new Mat3();
    assertEquals(((ContainsGlSlExpression)Shade.constant(mat)).getGlSlExpression(),
        "mat3(vec3(1.0, 0.0, 0.0), vec3(0.0, 1.0, 0.0), vec3(0.0, 0.0, 1.0))");

    mat = Shade.mat3();
    assertEquals(((ContainsGlSlExpression)Shade.constant(mat)).getGlSlExpression(),
        "mat3(vec3(1.0, 0.0, 0.0), vec3(0.0, 1.0, 0.0), vec3(0.0, 0.0, 1.0))");
  }

  @Test
  public void testConstantMat4() {
    Mat4 mat = new Mat4();
    assertEquals(((ContainsGlSlExpression)Shade.constant(mat)).getGlSlExpression(),
        "mat4(vec4(1.0, 0.0, 0.0, 0.0), vec4(0.0, 1.0, 0.0, 0.0), vec4(0.0, 0.0, 1.0, 0.0), vec4(0.0, 0.0, 0.0, 1.0))");

    mat = Shade.mat4();
    assertEquals(((ContainsGlSlExpression)Shade.constant(mat)).getGlSlExpression(),
        "mat4(vec4(1.0, 0.0, 0.0, 0.0), vec4(0.0, 1.0, 0.0, 0.0), vec4(0.0, 0.0, 1.0, 0.0), vec4(0.0, 0.0, 0.0, 1.0))");
  }
}
