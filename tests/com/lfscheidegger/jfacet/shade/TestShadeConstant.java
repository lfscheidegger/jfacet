package com.lfscheidegger.jfacet.shade;

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
}
