package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.primitives.Mat4;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code Mat4Exp}
 */
public class Mat4ExpTest {

  @Test
  public void testConstructors() {
    assertEquals(Shade.mat(new Mat4()).evaluate(), new Mat4());
    assertEquals(Shade.mat(new Vec4Exp(new Vec4(1, 0, 0, 0)), new Vec4(0, 1, 0, 0), new Vec4Exp(new Vec4(0, 0, 1, 0)), new Vec4Exp(new Vec4(0, 0, 0, 1))).evaluate(), new Mat4());
    assertEquals(Shade.mat(new Vec4(1, 0, 0, 0), new Vec4Exp(new Vec4(0, 1, 0, 0)), new Vec4Exp(new Vec4(0, 0, 1, 0)), new Vec4Exp(new Vec4(0, 0, 0, 1))).evaluate(), new Mat4());
    assertEquals(Shade.mat(new Vec4(1, 0, 0, 0), new Vec4(0, 1, 0, 0), new Vec4Exp(new Vec4(0, 0, 1, 0)), new Vec4Exp(new Vec4(0, 0, 0, 1))).evaluate(), new Mat4());
    assertEquals(Shade.mat(new Vec4Exp(new Vec4(1, 0, 0, 0)), new Vec4Exp(new Vec4(0, 1, 0, 0)), new Vec4Exp(new Vec4(0, 0, 1, 0)), new Vec4Exp(new Vec4(0, 0, 0, 1))).evaluate(), new Mat4());
    assertEquals(Shade.mat(new Vec4Exp(new Vec4(1, 0, 0, 0)), new Vec4(0, 1, 0, 0), new Vec4(0, 0, 1, 0), new Vec4Exp(new Vec4(0, 0, 0, 1))).evaluate(), new Mat4());
    assertEquals(Shade.mat(new Vec4(1, 0, 0, 0), new Vec4Exp(new Vec4(0, 1, 0, 0)), new Vec4(0, 0, 1, 0), new Vec4Exp(new Vec4(0, 0, 0, 1))).evaluate(), new Mat4());
    assertEquals(Shade.mat(new Vec4(1, 0, 0, 0), new Vec4(0, 1, 0, 0), new Vec4(0, 0, 1, 0), new Vec4Exp(new Vec4(0, 0, 0, 1))).evaluate(), new Mat4());
    assertEquals(Shade.mat(new Vec4Exp(new Vec4(1, 0, 0, 0)), new Vec4Exp(new Vec4(0, 1, 0, 0)), new Vec4(0, 0, 1, 0), new Vec4Exp(new Vec4(0, 0, 0, 1))).evaluate(), new Mat4());
    assertEquals(Shade.mat(new Vec4Exp(new Vec4(1, 0, 0, 0)), new Vec4(0, 1, 0, 0), new Vec4Exp(new Vec4(0, 0, 1, 0)), new Vec4(0, 0, 0, 1)).evaluate(), new Mat4());
    assertEquals(Shade.mat(new Vec4(1, 0, 0, 0), new Vec4Exp(new Vec4(0, 1, 0, 0)), new Vec4Exp(new Vec4(0, 0, 1, 0)), new Vec4(0, 0, 0, 1)).evaluate(), new Mat4());
    assertEquals(Shade.mat(new Vec4(1, 0, 0, 0), new Vec4(0, 1, 0, 0), new Vec4Exp(new Vec4(0, 0, 1, 0)), new Vec4(0, 0, 0, 1)).evaluate(), new Mat4());
    assertEquals(Shade.mat(new Vec4Exp(new Vec4(1, 0, 0, 0)), new Vec4Exp(new Vec4(0, 1, 0, 0)), new Vec4Exp(new Vec4(0, 0, 1, 0)), new Vec4(0, 0, 0, 1)).evaluate(), new Mat4());
    assertEquals(Shade.mat(new Vec4Exp(new Vec4(1, 0, 0, 0)), new Vec4(0, 1, 0, 0), new Vec4(0, 0, 1, 0), new Vec4(0, 0, 0, 1)).evaluate(), new Mat4());
    assertEquals(Shade.mat(new Vec4(1, 0, 0, 0), new Vec4Exp(new Vec4(0, 1, 0, 0)), new Vec4(0, 0, 1, 0), new Vec4(0, 0, 0, 1)).evaluate(), new Mat4());
    assertEquals(Shade.mat(new Vec4(1, 0, 0, 0), new Vec4(0, 1, 0, 0), new Vec4(0, 0, 1, 0), new Vec4(0, 0, 0, 1)).evaluate(), new Mat4());
    assertEquals(Shade.mat(new Vec4Exp(new Vec4(1, 0, 0, 0)), new Vec4Exp(new Vec4(0, 1, 0, 0)), new Vec4(0, 0, 1, 0), new Vec4(0, 0, 0, 1)).evaluate(), new Mat4());
  }

  @Test
  public void testAddition() {
    assertEquals(Shade.add(new Mat4(), 3).evaluate(), new Mat4().add(3));
    assertEquals(Shade.add(Shade.mat(new Mat4()), 3).evaluate(), new Mat4().add(3));
    assertEquals(Shade.add(new Mat4(), Shade.constant(3)).evaluate(), new Mat4().add(3));
    assertEquals(Shade.add(Shade.mat(new Mat4()), Shade.constant(3)).evaluate(), new Mat4().add(3));

    assertEquals(Shade.add(Shade.mat(new Mat4()), Shade.mat(new Mat4())).evaluate(), new Mat4().add(new Mat4()));
  }

  @Test
  public void testSubtraction() {
    assertEquals(Shade.sub(new Mat4(), 3).evaluate(), new Mat4().sub(3));
    assertEquals(Shade.sub(Shade.mat(new Mat4()), 3).evaluate(), new Mat4().sub(3));
    assertEquals(Shade.sub(new Mat4(), Shade.constant(3)).evaluate(), new Mat4().sub(3));
    assertEquals(Shade.sub(Shade.mat(new Mat4()), Shade.constant(3)).evaluate(), new Mat4().sub(3));

    assertEquals(Shade.sub(Shade.mat(new Mat4()), Shade.mat(new Mat4())).evaluate(), new Mat4().sub(new Mat4()));
  }

  @Test
  public void testMultiplication() {
    assertEquals(Shade.mul(new Mat4(), 3).evaluate(), new Mat4().mul(3));
    assertEquals(Shade.mul(Shade.mat(new Mat4()), 3).evaluate(), new Mat4().mul(3));
    assertEquals(Shade.mul(new Mat4(), Shade.constant(3)).evaluate(), new Mat4().mul(3));
    assertEquals(Shade.mul(Shade.mat(new Mat4()), Shade.constant(3)).evaluate(), new Mat4().mul(3));

    assertEquals(Shade.mul(Shade.mat(new Mat4()), Shade.mat(new Mat4())).evaluate(), new Mat4().mul(new Mat4()));
  }

  @Test
  public void testDivision() {
    assertEquals(Shade.div(new Mat4(), 3).evaluate(), new Mat4().div(3));
    assertEquals(Shade.div(Shade.mat(new Mat4()), 3).evaluate(), new Mat4().div(3));
    assertEquals(Shade.div(new Mat4(), Shade.constant(3)).evaluate(), new Mat4().div(3));
    assertEquals(Shade.div(Shade.mat(new Mat4()), Shade.constant(3)).evaluate(), new Mat4().div(3));

    assertEquals(Shade.div(Shade.mat(new Mat4()),
        Shade.mat(new Mat4(new Vec4(1, 1, 1, 1), new Vec4(1, 1, 1, 1), new Vec4(1, 1, 1, 1), new Vec4(1, 1, 1, 1)))).evaluate(),
        new Mat4().div(new Mat4(new Vec4(1, 1, 1, 1), new Vec4(1, 1, 1, 1), new Vec4(1, 1, 1, 1), new Vec4(1, 1, 1, 1))));
  }

  @Test
  public void testComponents() {
    assertEquals(Shade.mat(new Mat4()).getC0().evaluate(), new Vec4(1, 0, 0, 0));
    assertEquals(Shade.mat(new Mat4()).get(0).evaluate(), new Vec4(1, 0, 0, 0));
    assertEquals(Shade.add(Shade.mat(new Mat4()), Shade.mat(new Mat4())).getC0().evaluate(), new Vec4(2, 0, 0, 0));
    assertEquals(Shade.add(Shade.mat(new Mat4()), Shade.mat(new Mat4())).get(0).evaluate(), new Vec4(2, 0, 0, 0));

    assertEquals(Shade.mat(new Mat4()).getC0().getGlSlString(), "vec4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))[0])");
    assertEquals(Shade.mat(new Mat4()).get(0).getGlSlString(), "vec4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))[0])");

    assertEquals(Shade.mat(new Mat4()).getC1().evaluate(), new Vec4(0, 1, 0, 0));
    assertEquals(Shade.mat(new Mat4()).get(1).evaluate(), new Vec4(0, 1, 0, 0));
    assertEquals(Shade.add(Shade.mat(new Mat4()), Shade.mat(new Mat4())).getC1().evaluate(), new Vec4(0, 2, 0, 0));
    assertEquals(Shade.add(Shade.mat(new Mat4()), Shade.mat(new Mat4())).get(1).evaluate(), new Vec4(0, 2, 0, 0));

    assertEquals(Shade.mat(new Mat4()).getC1().getGlSlString(), "vec4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))[1])");
    assertEquals(Shade.mat(new Mat4()).get(1).getGlSlString(), "vec4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))[1])");

    assertEquals(Shade.mat(new Mat4()).getC2().evaluate(), new Vec4(0, 0, 1, 0));
    assertEquals(Shade.mat(new Mat4()).get(2).evaluate(), new Vec4(0, 0, 1, 0));
    assertEquals(Shade.add(Shade.mat(new Mat4()), Shade.mat(new Mat4())).getC2().evaluate(), new Vec4(0, 0, 2, 0));
    assertEquals(Shade.add(Shade.mat(new Mat4()), Shade.mat(new Mat4())).get(2).evaluate(), new Vec4(0, 0, 2, 0));

    assertEquals(Shade.mat(new Mat4()).getC2().getGlSlString(), "vec4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))[2])");
    assertEquals(Shade.mat(new Mat4()).get(2).getGlSlString(), "vec4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))[2])");

    assertEquals(Shade.mat(new Mat4()).getC3().evaluate(), new Vec4(0, 0, 0, 1));
    assertEquals(Shade.mat(new Mat4()).get(3).evaluate(), new Vec4(0, 0, 0, 1));
    assertEquals(Shade.add(Shade.mat(new Mat4()), Shade.mat(new Mat4())).getC3().evaluate(), new Vec4(0, 0, 0, 2));
    assertEquals(Shade.add(Shade.mat(new Mat4()), Shade.mat(new Mat4())).get(3).evaluate(), new Vec4(0, 0, 0, 2));

    assertEquals(Shade.mat(new Mat4()).getC3().getGlSlString(), "vec4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))[3])");
    assertEquals(Shade.mat(new Mat4()).get(3).getGlSlString(), "vec4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))[3])");
  }
}
