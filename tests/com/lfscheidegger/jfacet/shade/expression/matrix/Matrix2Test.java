package com.lfscheidegger.jfacet.shade.expression.matrix;

import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.evaluators.BinaryOperationEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ConstructorEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.FunctionEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.NegationEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.glsl.UniformEvaluator;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Matrix2}
 */
public class Matrix2Test {

  private Matrix2 mat;
  private Matrix2.Primitive primitive;

  @Before
  public void setUp() {
    mat = new Matrix2(
        new Vector2(1, 2),
        new Vector2(3, 4));

    primitive = new Matrix2.Primitive(
        new Vector2.Primitive(1, 2),
        new Vector2.Primitive(3, 4));
  }

  @Test
  public void testConstructors() {
    assertTrue(mat.getEvaluator() instanceof ConstructorEvaluator);
    assertEquals(mat.getParents().size(), 2);
    assertEquals(mat.evaluate(), primitive);

    mat = new Matrix2();
    assertTrue(mat.getEvaluator() instanceof ConstructorEvaluator);
    assertEquals(mat.evaluate(), new Matrix2.Primitive());

    mat = new Matrix2(GlSlType.UNIFORM_T, new UniformEvaluator<Matrix2.Primitive>());
    assertEquals(mat.getGlSlType(), GlSlType.UNIFORM_T);
    assertTrue(mat.getEvaluator() instanceof UniformEvaluator);
  }

  @Test
  public void testGetters() {
    assertEquals(mat.getC0().evaluate(), new Vector2.Primitive(1, 2));
    assertEquals(mat.getC1().evaluate(), new Vector2.Primitive(3, 4));

    assertEquals(mat.getC0().evaluate(), mat.get(0).evaluate());
    assertEquals(mat.getC1().evaluate(), mat.get(1).evaluate());
  }

  private void testBinOpCommon(Matrix2 added, Matrix2.Primitive expected, String expectedOperatorSymbol) {
    assertEquals(added.getParents().size(), 2);
    assertEquals(added.evaluate(),  expected);
    assertTrue(added.getEvaluator() instanceof BinaryOperationEvaluator);
    Operator op = ((BinaryOperationEvaluator)added.getEvaluator()).getOperator();
    assertEquals(op.getOperatorSymbol(), expectedOperatorSymbol);
  }

  @Test
  public void testAdd() {
    Matrix2 added = mat.add(1);
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
    Matrix2 subtracted = mat.sub(1);
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
    Matrix2 multiplied = mat.mul(2);
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
    Matrix2 divided = mat.div(2);
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
    Matrix2 negated = mat.neg();
    assertEquals(negated.getParents().size(), 1);
    assertSame(negated.getParents().get(0), mat);
    assertTrue(negated.getEvaluator() instanceof NegationEvaluator);
    assertEquals(negated.evaluate(), mat.evaluate().neg());
  }

  @Test
  public void testTransform() {
    Vector2 transformed = mat.transform(new Vector2(1, 2));
    assertEquals(transformed.getParents().size(), 2);
    assertSame(transformed.getParents().get(0), mat);
    assertEquals(transformed.getParents().get(1).evaluate(), new Vector2.Primitive(1, 2));
    assertTrue(transformed.getEvaluator() instanceof BinaryOperationEvaluator);

    assertEquals(transformed.evaluate(), mat.evaluate().transform(new Vector2.Primitive(1, 2)));
  }

  @Test
  public void testIsEqual() {
    Bool isEqual = mat.isEqual(new Matrix2(
        new Vector2(1, 2),
        new Vector2(3, 5)));
    assertEquals(isEqual.getParents().size(), 2);
    assertSame(isEqual.getParents().get(0), mat);
    assertEquals(isEqual.getParents().get(1).evaluate(), new Matrix2.Primitive(
        new Vector2.Primitive(1, 2),
        new Vector2.Primitive(3, 5)));
    assertFalse(isEqual.evaluate());

    isEqual = mat.isEqual(mat);
    assertTrue(isEqual.evaluate());
  }

  @Test
  public void testIsNotEqual() {
    Bool isNotEqual = mat.isNotEqual(new Matrix2(
        new Vector2(1, 2),
        new Vector2(3, 5)));
    assertEquals(isNotEqual.getParents().size(), 2);
    assertSame(isNotEqual.getParents().get(0), mat);
    assertEquals(isNotEqual.getParents().get(1).evaluate(), new Matrix2.Primitive(
        new Vector2.Primitive(1, 2),
        new Vector2.Primitive(3, 5)));
    assertTrue(isNotEqual.evaluate());

    isNotEqual = mat.isNotEqual(mat);
    assertFalse(isNotEqual.evaluate());
  }

  @Test
  public void testMatrixCompMult() {
    Matrix2 matrixCompMult = mat.matrixCompMult(mat);
    assertEquals(matrixCompMult.getParents().size(), 2);
    assertSame(matrixCompMult.getParents().get(0), mat);
    assertSame(matrixCompMult.getParents().get(1), mat);
    assertTrue(matrixCompMult.getEvaluator() instanceof FunctionEvaluator);

    assertEquals(matrixCompMult.evaluate(), mat.evaluate().matrixCompMult(mat.evaluate()));
  }
}
