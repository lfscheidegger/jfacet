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
public class Mat2ExpTest {

  private final CompilationContext mContext = new FakeCompilationContext();

  @Test
  public void testConstructors() {
    assertEquals(Shade.mat(new Matrix(2)).evaluate(), new Matrix(2));
    assertEquals(Shade.mat(new VectorExpression(new Vector(1, 0)), new Vector(0, 1)).evaluate(), new Matrix(2));
    assertEquals(Shade.mat(new Vector(1, 0), new VectorExpression(new Vector(0, 1))).evaluate(), new Matrix(2));
    assertEquals(Shade.mat(new Vector(1, 0), new Vector(0, 1)).evaluate(), new Matrix(2));
    assertEquals(Shade.mat(new VectorExpression(new Vector(1, 0)), new VectorExpression(new Vector(0, 1))).evaluate(), new Matrix(2));
  }

  @Test
  public void testAddition() {
    assertEquals(Shade.add(new Matrix(2), 3).evaluate(), new Matrix(2).add(3));
    assertEquals(Shade.add(Shade.mat(new Matrix(2)), 3).evaluate(), new Matrix(2).add(3));
    assertEquals(Shade.add(new Matrix(2), Shade.constant(3)).evaluate(), new Matrix(2).add(3));
    assertEquals(Shade.add(Shade.mat(new Matrix(2)), Shade.constant(3)).evaluate(), new Matrix(2).add(3));

    assertEquals(Shade.add(Shade.mat(new Matrix(2)), Shade.mat(new Matrix(2))).evaluate(), new Matrix(2).add(new Matrix(2)));
  }

  @Test
  public void testSubtraction() {
    assertEquals(Shade.sub(new Matrix(2), 3).evaluate(), new Matrix(2).sub(3));
    assertEquals(Shade.sub(Shade.mat(new Matrix(2)), 3).evaluate(), new Matrix(2).sub(3));
    assertEquals(Shade.sub(new Matrix(2), Shade.constant(3)).evaluate(), new Matrix(2).sub(3));
    assertEquals(Shade.sub(Shade.mat(new Matrix(2)), Shade.constant(3)).evaluate(), new Matrix(2).sub(3));

    assertEquals(Shade.sub(Shade.mat(new Matrix(2)), Shade.mat(new Matrix(2))).evaluate(), new Matrix(2).sub(new Matrix(2)));
  }

  @Test
  public void testMultiplication() {
    assertEquals(Shade.mul(new Matrix(2), 3).evaluate(), new Matrix(2).mul(3));
    assertEquals(Shade.mul(Shade.mat(new Matrix(2)), 3).evaluate(), new Matrix(2).mul(3));
    assertEquals(Shade.mul(new Matrix(2), Shade.constant(3)).evaluate(), new Matrix(2).mul(3));
    assertEquals(Shade.mul(Shade.mat(new Matrix(2)), Shade.constant(3)).evaluate(), new Matrix(2).mul(3));

    assertEquals(Shade.mul(Shade.mat(new Matrix(2)), Shade.mat(new Matrix(2))).evaluate(), new Matrix(2).mul(new Matrix(2)));
  }

  @Test
  public void testDivision() {
    assertEquals(Shade.div(new Matrix(2), 3).evaluate(), new Matrix(2).div(3));
    assertEquals(Shade.div(Shade.mat(new Matrix(2)), 3).evaluate(), new Matrix(2).div(3));
    assertEquals(Shade.div(new Matrix(2), Shade.constant(3)).evaluate(), new Matrix(2).div(3));
    assertEquals(Shade.div(Shade.mat(new Matrix(2)), Shade.constant(3)).evaluate(), new Matrix(2).div(3));

    assertEquals(Shade.div(Shade.mat(new Matrix(2)), Shade.mat(new Matrix(new Vector(1, 1), new Vector(1, 1)))).evaluate(),
        new Matrix(2).div(new Matrix(new Vector(1, 1), new Vector(1, 1))));
  }

  @Test
  public void testComponents() {
    assertEquals(Shade.mat(new Matrix(2)).getC0().evaluate(), new Vector(1, 0));
    assertEquals(Shade.mat(new Matrix(2)).get(0).evaluate(), new Vector(1, 0));
    assertEquals(Shade.add(Shade.mat(new Matrix(2)), Shade.mat(new Matrix(2))).getC0().evaluate(), new Vector(2, 0));
    assertEquals(Shade.add(Shade.mat(new Matrix(2)), Shade.mat(new Matrix(2))).get(0).evaluate(), new Vector(2, 0));

    assertEquals(Shade.mat(new Matrix(2)).getC0().getGlSlString(mContext), "vec2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0)))[0])");
    assertEquals(Shade.mat(new Matrix(2)).get(0).getGlSlString(mContext), "vec2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0)))[0])");

    assertEquals(Shade.mat(new Matrix(2)).getC1().evaluate(), new Vector(0, 1));
    assertEquals(Shade.mat(new Matrix(2)).get(1).evaluate(), new Vector(0, 1));
    assertEquals(Shade.add(Shade.mat(new Matrix(2)), Shade.mat(new Matrix(2))).getC1().evaluate(), new Vector(0, 2));
    assertEquals(Shade.add(Shade.mat(new Matrix(2)), Shade.mat(new Matrix(2))).get(1).evaluate(), new Vector(0, 2));

    assertEquals(Shade.mat(new Matrix(2)).getC1().getGlSlString(mContext), "vec2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0)))[1])");
    assertEquals(Shade.mat(new Matrix(2)).get(1).getGlSlString(mContext), "vec2(mat2(vec2(float(1.0), float(0.0)), vec2(float(0.0), float(1.0)))[1])");
  }
}
