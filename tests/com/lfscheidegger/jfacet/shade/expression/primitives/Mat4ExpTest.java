package com.lfscheidegger.jfacet.shade.expression.primitives;

import com.lfscheidegger.jfacet.shade.FakeCompilationContext;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.VectorExpression;
import com.lfscheidegger.jfacet.shade.primitives.Matrix;
import com.lfscheidegger.jfacet.shade.primitives.Vector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@code MatrixExp}
 */
public class Mat4ExpTest {

  private final CompilationContext mContext = new FakeCompilationContext();

  @Test
  public void testConstructors() {
    assertEquals(Shade.mat(new Matrix(4)).evaluate(), new Matrix(4));
    assertEquals(Shade.mat(new VectorExpression(new Vector(1, 0, 0, 0)), new Vector(0, 1, 0, 0), new VectorExpression(new Vector(0, 0, 1, 0)), new VectorExpression(new Vector(0, 0, 0, 1))).evaluate(), new Matrix(4));
    assertEquals(Shade.mat(new Vector(1, 0, 0, 0), new VectorExpression(new Vector(0, 1, 0, 0)), new VectorExpression(new Vector(0, 0, 1, 0)), new VectorExpression(new Vector(0, 0, 0, 1))).evaluate(), new Matrix(4));
    assertEquals(Shade.mat(new Vector(1, 0, 0, 0), new Vector(0, 1, 0, 0), new VectorExpression(new Vector(0, 0, 1, 0)), new VectorExpression(new Vector(0, 0, 0, 1))).evaluate(), new Matrix(4));
    assertEquals(Shade.mat(new VectorExpression(new Vector(1, 0, 0, 0)), new VectorExpression(new Vector(0, 1, 0, 0)), new VectorExpression(new Vector(0, 0, 1, 0)), new VectorExpression(new Vector(0, 0, 0, 1))).evaluate(), new Matrix(4));
    assertEquals(Shade.mat(new VectorExpression(new Vector(1, 0, 0, 0)), new Vector(0, 1, 0, 0), new Vector(0, 0, 1, 0), new VectorExpression(new Vector(0, 0, 0, 1))).evaluate(), new Matrix(4));
    assertEquals(Shade.mat(new Vector(1, 0, 0, 0), new VectorExpression(new Vector(0, 1, 0, 0)), new Vector(0, 0, 1, 0), new VectorExpression(new Vector(0, 0, 0, 1))).evaluate(), new Matrix(4));
    assertEquals(Shade.mat(new Vector(1, 0, 0, 0), new Vector(0, 1, 0, 0), new Vector(0, 0, 1, 0), new VectorExpression(new Vector(0, 0, 0, 1))).evaluate(), new Matrix(4));
    assertEquals(Shade.mat(new VectorExpression(new Vector(1, 0, 0, 0)), new VectorExpression(new Vector(0, 1, 0, 0)), new Vector(0, 0, 1, 0), new VectorExpression(new Vector(0, 0, 0, 1))).evaluate(), new Matrix(4));
    assertEquals(Shade.mat(new VectorExpression(new Vector(1, 0, 0, 0)), new Vector(0, 1, 0, 0), new VectorExpression(new Vector(0, 0, 1, 0)), new Vector(0, 0, 0, 1)).evaluate(), new Matrix(4));
    assertEquals(Shade.mat(new Vector(1, 0, 0, 0), new VectorExpression(new Vector(0, 1, 0, 0)), new VectorExpression(new Vector(0, 0, 1, 0)), new Vector(0, 0, 0, 1)).evaluate(), new Matrix(4));
    assertEquals(Shade.mat(new Vector(1, 0, 0, 0), new Vector(0, 1, 0, 0), new VectorExpression(new Vector(0, 0, 1, 0)), new Vector(0, 0, 0, 1)).evaluate(), new Matrix(4));
    assertEquals(Shade.mat(new VectorExpression(new Vector(1, 0, 0, 0)), new VectorExpression(new Vector(0, 1, 0, 0)), new VectorExpression(new Vector(0, 0, 1, 0)), new Vector(0, 0, 0, 1)).evaluate(), new Matrix(4));
    assertEquals(Shade.mat(new VectorExpression(new Vector(1, 0, 0, 0)), new Vector(0, 1, 0, 0), new Vector(0, 0, 1, 0), new Vector(0, 0, 0, 1)).evaluate(), new Matrix(4));
    assertEquals(Shade.mat(new Vector(1, 0, 0, 0), new VectorExpression(new Vector(0, 1, 0, 0)), new Vector(0, 0, 1, 0), new Vector(0, 0, 0, 1)).evaluate(), new Matrix(4));
    assertEquals(Shade.mat(new Vector(1, 0, 0, 0), new Vector(0, 1, 0, 0), new Vector(0, 0, 1, 0), new Vector(0, 0, 0, 1)).evaluate(), new Matrix(4));
    assertEquals(Shade.mat(new VectorExpression(new Vector(1, 0, 0, 0)), new VectorExpression(new Vector(0, 1, 0, 0)), new Vector(0, 0, 1, 0), new Vector(0, 0, 0, 1)).evaluate(), new Matrix(4));
  }

  @Test
  public void testAddition() {
    assertEquals(Shade.add(new Matrix(4), 3).evaluate(), new Matrix(4).add(3));
    assertEquals(Shade.add(Shade.mat(new Matrix(4)), 3).evaluate(), new Matrix(4).add(3));
    assertEquals(Shade.add(new Matrix(4), Shade.constant(3)).evaluate(), new Matrix(4).add(3));
    assertEquals(Shade.add(Shade.mat(new Matrix(4)), Shade.constant(3)).evaluate(), new Matrix(4).add(3));

    assertEquals(Shade.add(Shade.mat(new Matrix(4)), Shade.mat(new Matrix(4))).evaluate(), new Matrix(4).add(new Matrix(4)));
  }

  @Test
  public void testSubtraction() {
    assertEquals(Shade.sub(new Matrix(4), 3).evaluate(), new Matrix(4).sub(3));
    assertEquals(Shade.sub(Shade.mat(new Matrix(4)), 3).evaluate(), new Matrix(4).sub(3));
    assertEquals(Shade.sub(new Matrix(4), Shade.constant(3)).evaluate(), new Matrix(4).sub(3));
    assertEquals(Shade.sub(Shade.mat(new Matrix(4)), Shade.constant(3)).evaluate(), new Matrix(4).sub(3));

    assertEquals(Shade.sub(Shade.mat(new Matrix(4)), Shade.mat(new Matrix(4))).evaluate(), new Matrix(4).sub(new Matrix(4)));
  }

  @Test
  public void testMultiplication() {
    assertEquals(Shade.mul(new Matrix(4), 3).evaluate(), new Matrix(4).mul(3));
    assertEquals(Shade.mul(Shade.mat(new Matrix(4)), 3).evaluate(), new Matrix(4).mul(3));
    assertEquals(Shade.mul(new Matrix(4), Shade.constant(3)).evaluate(), new Matrix(4).mul(3));
    assertEquals(Shade.mul(Shade.mat(new Matrix(4)), Shade.constant(3)).evaluate(), new Matrix(4).mul(3));

    assertEquals(Shade.mul(Shade.mat(new Matrix(4)), Shade.mat(new Matrix(4))).evaluate(), new Matrix(4).mul(new Matrix(4)));
  }

  @Test
  public void testDivision() {
    assertEquals(Shade.div(new Matrix(4), 3).evaluate(), new Matrix(4).div(3));
    assertEquals(Shade.div(Shade.mat(new Matrix(4)), 3).evaluate(), new Matrix(4).div(3));
    assertEquals(Shade.div(new Matrix(4), Shade.constant(3)).evaluate(), new Matrix(4).div(3));
    assertEquals(Shade.div(Shade.mat(new Matrix(4)), Shade.constant(3)).evaluate(), new Matrix(4).div(3));

    assertEquals(Shade.div(Shade.mat(new Matrix(4)),
        Shade.mat(new Matrix(new Vector(1, 1, 1, 1), new Vector(1, 1, 1, 1), new Vector(1, 1, 1, 1), new Vector(1, 1, 1, 1)))).evaluate(),
        new Matrix(4).div(new Matrix(new Vector(1, 1, 1, 1), new Vector(1, 1, 1, 1), new Vector(1, 1, 1, 1), new Vector(1, 1, 1, 1))));
  }

  @Test
  public void testComponents() {
    assertEquals(Shade.mat(new Matrix(4)).getC0().evaluate(), new Vector(1, 0, 0, 0));
    assertEquals(Shade.mat(new Matrix(4)).get(0).evaluate(), new Vector(1, 0, 0, 0));
    assertEquals(Shade.add(Shade.mat(new Matrix(4)), Shade.mat(new Matrix(4))).getC0().evaluate(), new Vector(2, 0, 0, 0));
    assertEquals(Shade.add(Shade.mat(new Matrix(4)), Shade.mat(new Matrix(4))).get(0).evaluate(), new Vector(2, 0, 0, 0));

    assertEquals(Shade.mat(new Matrix(4)).getC0().getGlSlString(mContext), "vec4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))[0])");
    assertEquals(Shade.mat(new Matrix(4)).get(0).getGlSlString(mContext), "vec4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))[0])");

    assertEquals(Shade.mat(new Matrix(4)).getC1().evaluate(), new Vector(0, 1, 0, 0));
    assertEquals(Shade.mat(new Matrix(4)).get(1).evaluate(), new Vector(0, 1, 0, 0));
    assertEquals(Shade.add(Shade.mat(new Matrix(4)), Shade.mat(new Matrix(4))).getC1().evaluate(), new Vector(0, 2, 0, 0));
    assertEquals(Shade.add(Shade.mat(new Matrix(4)), Shade.mat(new Matrix(4))).get(1).evaluate(), new Vector(0, 2, 0, 0));

    assertEquals(Shade.mat(new Matrix(4)).getC1().getGlSlString(mContext), "vec4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))[1])");
    assertEquals(Shade.mat(new Matrix(4)).get(1).getGlSlString(mContext), "vec4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))[1])");

    assertEquals(Shade.mat(new Matrix(4)).getC2().evaluate(), new Vector(0, 0, 1, 0));
    assertEquals(Shade.mat(new Matrix(4)).get(2).evaluate(), new Vector(0, 0, 1, 0));
    assertEquals(Shade.add(Shade.mat(new Matrix(4)), Shade.mat(new Matrix(4))).getC2().evaluate(), new Vector(0, 0, 2, 0));
    assertEquals(Shade.add(Shade.mat(new Matrix(4)), Shade.mat(new Matrix(4))).get(2).evaluate(), new Vector(0, 0, 2, 0));

    assertEquals(Shade.mat(new Matrix(4)).getC2().getGlSlString(mContext), "vec4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))[2])");
    assertEquals(Shade.mat(new Matrix(4)).get(2).getGlSlString(mContext), "vec4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))[2])");

    assertEquals(Shade.mat(new Matrix(4)).getC3().evaluate(), new Vector(0, 0, 0, 1));
    assertEquals(Shade.mat(new Matrix(4)).get(3).evaluate(), new Vector(0, 0, 0, 1));
    assertEquals(Shade.add(Shade.mat(new Matrix(4)), Shade.mat(new Matrix(4))).getC3().evaluate(), new Vector(0, 0, 0, 2));
    assertEquals(Shade.add(Shade.mat(new Matrix(4)), Shade.mat(new Matrix(4))).get(3).evaluate(), new Vector(0, 0, 0, 2));

    assertEquals(Shade.mat(new Matrix(4)).getC3().getGlSlString(mContext), "vec4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))[3])");
    assertEquals(Shade.mat(new Matrix(4)).get(3).getGlSlString(mContext), "vec4(mat4(vec4(float(1.0), float(0.0), float(0.0), float(0.0)), vec4(float(0.0), float(1.0), float(0.0), float(0.0)), vec4(float(0.0), float(0.0), float(1.0), float(0.0)), vec4(float(0.0), float(0.0), float(0.0), float(1.0)))[3])");
  }
}
