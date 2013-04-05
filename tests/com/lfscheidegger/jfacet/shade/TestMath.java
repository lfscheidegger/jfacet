package com.lfscheidegger.jfacet.shade;

import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec2Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec3Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec4Exp;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Math}
 */
public class TestMath {

  @Test
  public void testSqrt() {
    FloatExp f = Shade.constant(4);

    assertEquals(Math.sqrt(f).getGlSlString(), "float(sqrt(float(4.0)))");
    assertTrue(Math.sqrt(f).evaluate() == 2);
  }

  @Test
  public void testNormalizeVec2() {
    Vec2Exp vec = Shade.vec(2, 0);

    assertEquals(Math.normalize(vec).getGlSlString(), "vec2(normalize(vec2(float(2.0), float(0.0))))");
    assertTrue(Math.normalize(vec).evaluate().equals(new Vec2(1, 0)));
  }

  @Test
  public void testNormalizeVec3() {
    Vec3Exp vec = Shade.vec(2, 0, 0);

    assertEquals(Math.normalize(vec).getGlSlString(), "vec3(normalize(vec3(float(2.0), float(0.0), float(0.0))))");
    assertTrue(Math.normalize(vec).evaluate().equals(new Vec3(1, 0, 0)));
  }

  @Test
  public void testNormalizeVec4() {
    Vec4Exp vec = Shade.vec(2, 0, 0, 0);

    assertEquals(Math.normalize(vec).getGlSlString(), "vec4(normalize(vec4(float(2.0), float(0.0), float(0.0), float(0.0))))");
    assertTrue(Math.normalize(vec).evaluate().equals(new Vec4(1, 0, 0, 0)));
  }
}
