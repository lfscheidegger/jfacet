package com.lfscheidegger.jfacet.shade;

import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.FloatExpression;
import com.lfscheidegger.jfacet.shade.expression.VectorExpression;
import com.lfscheidegger.jfacet.shade.primitives.Vector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@code Math}
 */
public class TestMath {

  private CompilationContext mContext = new FakeCompilationContext();

  @Test
  public void testSqrt() {
    FloatExpression f = Shade.constant(4);

    assertEquals(Math.sqrt(f).getGlSlString(mContext), "float(sqrt(float(4.0)))");
    assertTrue(Math.sqrt(f).evaluate() == 2);
  }

  @Test
  public void testNormalizeVec2() {
    VectorExpression vec = Shade.vec(2, 0);

    assertEquals(Math.normalize(vec).getGlSlString(mContext), "vec2(normalize(vec2(float(2.0), float(0.0))))");
    assertTrue(Math.normalize(vec).evaluate().equals(new Vector(1, 0)));
  }

  @Test
  public void testNormalizeVec3() {
    VectorExpression vec = Shade.vec(2, 0, 0);

    assertEquals(Math.normalize(vec).getGlSlString(mContext), "vec3(normalize(vec3(float(2.0), float(0.0), float(0.0))))");
    assertTrue(Math.normalize(vec).evaluate().equals(new Vector(1, 0, 0)));
  }

  @Test
  public void testNormalizeVec4() {
    VectorExpression vec = Shade.vec(2, 0, 0, 0);

    assertEquals(Math.normalize(vec).getGlSlString(mContext), "vec4(normalize(vec4(float(2.0), float(0.0), float(0.0), float(0.0))))");
    assertTrue(Math.normalize(vec).evaluate().equals(new Vector(1, 0, 0, 0)));
  }
}
