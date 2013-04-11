package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.lfscheidegger.jfacet.shade.FakeCompilationContext;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.Vec3Exp;
import com.lfscheidegger.jfacet.shade.primitives.Mat3;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Mat3Exp}
 */
public class Mat3ExpTest {

  private final CompilationContext mContext = new FakeCompilationContext();

  @Test
  public void testConstructors() {
    assertEquals(Shade.mat(new Mat3()).evaluate(), new Mat3());
    assertEquals(Shade.mat(new Vec3Exp(new Vec3(1, 0, 0)), new Vec3(0, 1, 0), new Vec3Exp(new Vec3(0, 0, 1))).evaluate(), new Mat3());
    assertEquals(Shade.mat(new Vec3(1, 0, 0), new Vec3Exp(new Vec3(0, 1, 0)), new Vec3Exp(new Vec3(0, 0, 1))).evaluate(), new Mat3());
    assertEquals(Shade.mat(new Vec3(1, 0, 0), new Vec3(0, 1, 0), new Vec3Exp(new Vec3(0, 0, 1))).evaluate(), new Mat3());
    assertEquals(Shade.mat(new Vec3Exp(new Vec3(1, 0, 0)), new Vec3Exp(new Vec3(0, 1, 0)), new Vec3Exp(new Vec3(0, 0, 1))).evaluate(), new Mat3());
    assertEquals(Shade.mat(new Vec3Exp(new Vec3(1, 0, 0)), new Vec3(0, 1, 0), new Vec3(0, 0, 1)).evaluate(), new Mat3());
    assertEquals(Shade.mat(new Vec3(1, 0, 0), new Vec3Exp(new Vec3(0, 1, 0)), new Vec3(0, 0, 1)).evaluate(), new Mat3());
    assertEquals(Shade.mat(new Vec3(1, 0, 0), new Vec3(0, 1, 0), new Vec3(0, 0, 1)).evaluate(), new Mat3());
    assertEquals(Shade.mat(new Vec3Exp(new Vec3(1, 0, 0)), new Vec3Exp(new Vec3(0, 1, 0)), new Vec3(0, 0, 1)).evaluate(), new Mat3());
  }

  @Test
  public void testAddition() {
    assertEquals(Shade.add(new Mat3(), 3).evaluate(), new Mat3().add(3));
    assertEquals(Shade.add(Shade.mat(new Mat3()), 3).evaluate(), new Mat3().add(3));
    assertEquals(Shade.add(new Mat3(), Shade.constant(3)).evaluate(), new Mat3().add(3));
    assertEquals(Shade.add(Shade.mat(new Mat3()), Shade.constant(3)).evaluate(), new Mat3().add(3));

    assertEquals(Shade.add(Shade.mat(new Mat3()), Shade.mat(new Mat3())).evaluate(), new Mat3().add(new Mat3()));
  }

  @Test
  public void testSubtraction() {
    assertEquals(Shade.sub(new Mat3(), 3).evaluate(), new Mat3().sub(3));
    assertEquals(Shade.sub(Shade.mat(new Mat3()), 3).evaluate(), new Mat3().sub(3));
    assertEquals(Shade.sub(new Mat3(), Shade.constant(3)).evaluate(), new Mat3().sub(3));
    assertEquals(Shade.sub(Shade.mat(new Mat3()), Shade.constant(3)).evaluate(), new Mat3().sub(3));

    assertEquals(Shade.sub(Shade.mat(new Mat3()), Shade.mat(new Mat3())).evaluate(), new Mat3().sub(new Mat3()));
  }

  @Test
  public void testMultiplication() {
    assertEquals(Shade.mul(new Mat3(), 3).evaluate(), new Mat3().mul(3));
    assertEquals(Shade.mul(Shade.mat(new Mat3()), 3).evaluate(), new Mat3().mul(3));
    assertEquals(Shade.mul(new Mat3(), Shade.constant(3)).evaluate(), new Mat3().mul(3));
    assertEquals(Shade.mul(Shade.mat(new Mat3()), Shade.constant(3)).evaluate(), new Mat3().mul(3));

    assertEquals(Shade.mul(Shade.mat(new Mat3()), Shade.mat(new Mat3())).evaluate(), new Mat3().mul(new Mat3()));
  }

  @Test
  public void testDivision() {
    assertEquals(Shade.div(new Mat3(), 3).evaluate(), new Mat3().div(3));
    assertEquals(Shade.div(Shade.mat(new Mat3()), 3).evaluate(), new Mat3().div(3));
    assertEquals(Shade.div(new Mat3(), Shade.constant(3)).evaluate(), new Mat3().div(3));
    assertEquals(Shade.div(Shade.mat(new Mat3()), Shade.constant(3)).evaluate(), new Mat3().div(3));

    assertEquals(Shade.div(Shade.mat(new Mat3()),
        Shade.mat(new Mat3(new Vec3(1, 1, 1), new Vec3(1, 1, 1), new Vec3(1, 1, 1)))).evaluate(),
        new Mat3().div(new Mat3(new Vec3(1, 1, 1), new Vec3(1, 1, 1), new Vec3(1, 1, 1))));
  }

  @Test
  public void testComponents() {
    assertEquals(Shade.mat(new Mat3()).getC0().evaluate(), new Vec3(1, 0, 0));
    assertEquals(Shade.mat(new Mat3()).get(0).evaluate(), new Vec3(1, 0, 0));
    assertEquals(Shade.add(Shade.mat(new Mat3()), Shade.mat(new Mat3())).getC0().evaluate(), new Vec3(2, 0, 0));
    assertEquals(Shade.add(Shade.mat(new Mat3()), Shade.mat(new Mat3())).get(0).evaluate(), new Vec3(2, 0, 0));

    assertEquals(Shade.mat(new Mat3()).getC0().getGlSlString(mContext), "vec3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0)))[0])");
    assertEquals(Shade.mat(new Mat3()).get(0).getGlSlString(mContext), "vec3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0)))[0])");

    assertEquals(Shade.mat(new Mat3()).getC1().evaluate(), new Vec3(0, 1, 0));
    assertEquals(Shade.mat(new Mat3()).get(1).evaluate(), new Vec3(0, 1, 0));
    assertEquals(Shade.add(Shade.mat(new Mat3()), Shade.mat(new Mat3())).getC1().evaluate(), new Vec3(0, 2, 0));
    assertEquals(Shade.add(Shade.mat(new Mat3()), Shade.mat(new Mat3())).get(1).evaluate(), new Vec3(0, 2, 0));

    assertEquals(Shade.mat(new Mat3()).getC1().getGlSlString(mContext), "vec3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0)))[1])");
    assertEquals(Shade.mat(new Mat3()).get(1).getGlSlString(mContext), "vec3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0)))[1])");

    assertEquals(Shade.mat(new Mat3()).getC2().evaluate(), new Vec3(0, 0, 1));
    assertEquals(Shade.mat(new Mat3()).get(2).evaluate(), new Vec3(0, 0, 1));
    assertEquals(Shade.add(Shade.mat(new Mat3()), Shade.mat(new Mat3())).getC2().evaluate(), new Vec3(0, 0, 2));
    assertEquals(Shade.add(Shade.mat(new Mat3()), Shade.mat(new Mat3())).get(2).evaluate(), new Vec3(0, 0, 2));

    assertEquals(Shade.mat(new Mat3()).getC2().getGlSlString(mContext), "vec3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0)))[2])");
    assertEquals(Shade.mat(new Mat3()).get(2).getGlSlString(mContext), "vec3(mat3(vec3(float(1.0), float(0.0), float(0.0)), vec3(float(0.0), float(1.0), float(0.0)), vec3(float(0.0), float(0.0), float(1.0)))[2])");
  }
}
