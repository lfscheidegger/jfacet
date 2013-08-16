package com.lfscheidegger.jfacet.shade.expression.matrix;

import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.evaluators.BinaryOperationEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ConstructorEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.NegationEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.glsl.UniformEvaluator;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Matrix3}
 */
public class Matrix3Test {

  private Matrix3 mat;
  private Matrix3.Primitive primitive;

  @Before
  public void setUp() {
    mat = new Matrix3(
        new Vector3(1, 2, 3),
        new Vector3(3, 4, 5),
        new Vector3(5, 6, 7));

    primitive = new Matrix3.Primitive(
        new Vector3.Primitive(1, 2, 3),
        new Vector3.Primitive(3, 4, 5),
        new Vector3.Primitive(5, 6, 7));
  }

  @Test
  public void testConstructors() {
    assertTrue(mat.getEvaluator() instanceof ConstructorEvaluator);
    assertEquals(mat.getParents().size(), 3);
    assertEquals(mat.evaluate(), primitive);

    mat = new Matrix3();
    assertTrue(mat.getEvaluator() instanceof ConstructorEvaluator);
    assertEquals(mat.evaluate(), new Matrix3.Primitive());

    mat = new Matrix3(GlSlType.UNIFORM_T, new UniformEvaluator<Matrix3.Primitive>());
    assertEquals(mat.getGlSlType(), GlSlType.UNIFORM_T);
    assertTrue(mat.getEvaluator() instanceof UniformEvaluator);
  }

  @Test
  public void testGetters() {
    assertEquals(mat.getC0().evaluate(), new Vector3.Primitive(1, 2, 3));
    assertEquals(mat.getC1().evaluate(), new Vector3.Primitive(3, 4, 5));
    assertEquals(mat.getC2().evaluate(), new Vector3.Primitive(5, 6, 7));

    assertEquals(mat.getC0().evaluate(), mat.get(0).evaluate());
    assertEquals(mat.getC1().evaluate(), mat.get(1).evaluate());
    assertEquals(mat.getC2().evaluate(), mat.get(2).evaluate());
  }

  private void testBinOpCommon(Matrix3 added, Matrix3.Primitive expected, String expectedOperatorSymbol) {
    assertEquals(added.getParents().size(), 2);
    assertEquals(added.evaluate(),  expected);
    assertTrue(added.getEvaluator() instanceof BinaryOperationEvaluator);
    Operator op = ((BinaryOperationEvaluator)added.getEvaluator()).getOperator();
    assertEquals(op.getOperatorSymbol(), expectedOperatorSymbol);
  }

  @Test
  public void testAdd() {
    Matrix3 added = mat.add(1);
    testBinOpCommon(added, primitive.add(1), "+");
    assertSame(added.getParents().get(0), mat);
    assertEquals(added.getParents().get(1).evaluate(), 1.0f);

    added = mat.add(new Real(1));
    testBinOpCommon(added, primitive.add(1), "+");
    assertSame(added.getParents().get(0), mat);
    assertEquals(added.getParents().get(1).evaluate(), 1.0f);

    added = mat.add(mat);
    testBinOpCommon(added, primitive.add(primitive), "+");
    assertSame(added.getParents().get(0), mat);
    assertSame(added.getParents().get(1), mat);
  }

  @Test
  public void testSub() {
    Matrix3 subtracted = mat.sub(1);
    testBinOpCommon(subtracted, primitive.sub(1), "-");
    assertSame(subtracted.getParents().get(0), mat);
    assertEquals(subtracted.getParents().get(1).evaluate(), 1.0f);

    subtracted = mat.sub(new Real(1));
    testBinOpCommon(subtracted, primitive.sub(1), "-");
    assertSame(subtracted.getParents().get(0), mat);
    assertEquals(subtracted.getParents().get(1).evaluate(), 1.0f);

    subtracted = mat.sub(mat);
    testBinOpCommon(subtracted, primitive.sub(primitive), "-");
    assertSame(subtracted.getParents().get(0), mat);
    assertSame(subtracted.getParents().get(1), mat);
  }

  @Test
  public void testMul() {
    Matrix3 multiplied = mat.mul(2);
    testBinOpCommon(multiplied, primitive.mul(2), "*");
    assertSame(multiplied.getParents().get(0), mat);
    assertEquals(multiplied.getParents().get(1).evaluate(), 2.0f);

    multiplied = mat.mul(new Real(2));
    testBinOpCommon(multiplied, primitive.mul(2), "*");
    assertSame(multiplied.getParents().get(0), mat);
    assertEquals(multiplied.getParents().get(1).evaluate(), 2.0f);

    multiplied = mat.mul(mat);
    testBinOpCommon(multiplied, primitive.mul(primitive), "*");
    assertSame(multiplied.getParents().get(0), mat);
    assertSame(multiplied.getParents().get(1), mat);
  }

  @Test
  public void testDiv() {
    Matrix3 divided = mat.div(2);
    testBinOpCommon(divided, primitive.div(2), "/");
    assertSame(divided.getParents().get(0), mat);
    assertEquals(divided.getParents().get(1).evaluate(), 2.0f);

    divided = mat.div(new Real(2));
    testBinOpCommon(divided, primitive.div(2), "/");
    assertSame(divided.getParents().get(0), mat);
    assertEquals(divided.getParents().get(1).evaluate(), 2.0f);

    divided = mat.div(mat);
    testBinOpCommon(divided, primitive.div(primitive), "/");
    assertSame(divided.getParents().get(0), mat);
    assertSame(divided.getParents().get(1), mat);
  }

  @Test
  public void testNeg() {
    Matrix3 negated = mat.neg();
    assertEquals(negated.getParents().size(), 1);
    assertSame(negated.getParents().get(0), mat);
    assertTrue(negated.getEvaluator() instanceof NegationEvaluator);
    assertEquals(negated.evaluate(), mat.evaluate().neg());
  }

  @Test
  public void testTransform() {
    Vector3 transformed = mat.transform(new Vector3(1, 2, 3));
    assertEquals(transformed.getParents().size(), 2);
    assertSame(transformed.getParents().get(0), mat);
    assertEquals(transformed.getParents().get(1).evaluate(), new Vector3.Primitive(1, 2, 3));
    assertTrue(transformed.getEvaluator() instanceof BinaryOperationEvaluator);

    assertEquals(transformed.evaluate(), mat.evaluate().transform(new Vector3.Primitive(1, 2, 3)));
  }
}
