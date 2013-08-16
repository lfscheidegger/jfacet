package com.lfscheidegger.jfacet.shade.expression;

import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.expression.evaluators.BinaryOperationEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ConstructorEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.NegationEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.glsl.UniformEvaluator;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Matrix4}
 */
public class Matrix4Test {

  private Matrix4 mat;
  private Matrix4.Primitive primitive;

  @Before
  public void setUp() {
    mat = new Matrix4(
        new Vector4(1, 2, 3, 4),
        new Vector4(3, 4, 5, 6),
        new Vector4(5, 6, 7, 8),
        new Vector4(8, 9, 10, 11));

    primitive = new Matrix4.Primitive(
        new Vector4.Primitive(1, 2, 3, 4),
        new Vector4.Primitive(3, 4, 5, 6),
        new Vector4.Primitive(5, 6, 7, 8),
        new Vector4.Primitive(8, 9, 10, 11));
  }

  @Test
  public void testConstructors() {
    assertTrue(mat.getEvaluator() instanceof ConstructorEvaluator);
    assertEquals(mat.evaluate(), primitive);

    mat = new Matrix4();
    assertTrue(mat.getEvaluator() instanceof ConstructorEvaluator);
    assertEquals(mat.evaluate(), new Matrix4.Primitive());

    mat = new Matrix4(GlSlType.UNIFORM_T, new UniformEvaluator<Matrix4.Primitive>());
    assertEquals(mat.getGlSlType(), GlSlType.UNIFORM_T);
    assertTrue(mat.getEvaluator() instanceof UniformEvaluator);
  }

  @Test
  public void testGetters() {
    assertEquals(mat.getC0().evaluate(), new Vector4.Primitive(1, 2, 3, 4));
    assertEquals(mat.getC1().evaluate(), new Vector4.Primitive(3, 4, 5, 6));
    assertEquals(mat.getC2().evaluate(), new Vector4.Primitive(5, 6, 7, 8));
    assertEquals(mat.getC3().evaluate(), new Vector4.Primitive(8, 9, 10, 11));

    assertEquals(mat.getC0().evaluate(), mat.get(0).evaluate());
    assertEquals(mat.getC1().evaluate(), mat.get(1).evaluate());
    assertEquals(mat.getC2().evaluate(), mat.get(2).evaluate());
    assertEquals(mat.getC3().evaluate(), mat.get(3).evaluate());
  }

  private void testAddCommon(Matrix4 added, Matrix4.Primitive expected) {
    assertEquals(added.getParents().size(), 2);
    assertEquals(added.evaluate(),  expected);
    assertTrue(added.getEvaluator() instanceof BinaryOperationEvaluator);
    Operator op = ((BinaryOperationEvaluator)added.getEvaluator()).getOperator();
    assertEquals(op.getOperatorSymbol(), "+");
  }

  @Test
  public void testAdd() {
    Matrix4 added = mat.add(1);
    testAddCommon(added, primitive.add(1));
    assertSame(added.getParents().get(0), mat);
    assertEquals(added.getParents().get(1).evaluate(), 1.0f);

    added = mat.add(new Real(1));
    testAddCommon(added, primitive.add(1));
    assertSame(added.getParents().get(0), mat);
    assertEquals(added.getParents().get(1).evaluate(), 1.0f);

    added = mat.add(mat);
    testAddCommon(added, primitive.add(primitive));
    assertSame(added.getParents().get(0), mat);
    assertSame(added.getParents().get(1), mat);
  }

  private void testSubCommon(Matrix4 subtracted, Matrix4.Primitive expected) {
    assertEquals(subtracted.getParents().size(), 2);
    assertEquals(subtracted.evaluate(),  expected);
    assertTrue(subtracted.getEvaluator() instanceof BinaryOperationEvaluator);
    Operator op = ((BinaryOperationEvaluator)subtracted.getEvaluator()).getOperator();
    assertEquals(op.getOperatorSymbol(), "-");
  }

  @Test
  public void testSub() {
    Matrix4 subtracted = mat.sub(1);
    testSubCommon(subtracted, primitive.sub(1));
    assertSame(subtracted.getParents().get(0), mat);
    assertEquals(subtracted.getParents().get(1).evaluate(), 1.0f);

    subtracted = mat.sub(new Real(1));
    testSubCommon(subtracted, primitive.sub(1));
    assertSame(subtracted.getParents().get(0), mat);
    assertEquals(subtracted.getParents().get(1).evaluate(), 1.0f);

    subtracted = mat.sub(mat);
    testSubCommon(subtracted, primitive.sub(primitive));
    assertSame(subtracted.getParents().get(0), mat);
    assertSame(subtracted.getParents().get(1), mat);
  }

  private void testMulCommon(Matrix4 multiplied, Matrix4.Primitive expected) {
    assertEquals(multiplied.getParents().size(), 2);
    assertEquals(multiplied.evaluate(),  expected);
    assertTrue(multiplied.getEvaluator() instanceof BinaryOperationEvaluator);
    Operator op = ((BinaryOperationEvaluator)multiplied.getEvaluator()).getOperator();
    assertEquals(op.getOperatorSymbol(), "*");
  }

  @Test
  public void testMul() {
    Matrix4 multiplied = mat.mul(2);
    testMulCommon(multiplied, primitive.mul(2));
    assertSame(multiplied.getParents().get(0), mat);
    assertEquals(multiplied.getParents().get(1).evaluate(), 2.0f);

    multiplied = mat.mul(new Real(2));
    testMulCommon(multiplied, primitive.mul(2));
    assertSame(multiplied.getParents().get(0), mat);
    assertEquals(multiplied.getParents().get(1).evaluate(), 2.0f);

    multiplied = mat.mul(mat);
    testMulCommon(multiplied, primitive.mul(primitive));
    assertSame(multiplied.getParents().get(0), mat);
    assertSame(multiplied.getParents().get(1), mat);
  }

  private void testDivCommon(Matrix4 divided, Matrix4.Primitive expected) {
    assertEquals(divided.getParents().size(), 2);
    assertEquals(divided.evaluate(),  expected);
    assertTrue(divided.getEvaluator() instanceof BinaryOperationEvaluator);
    Operator op = ((BinaryOperationEvaluator)divided.getEvaluator()).getOperator();
    assertEquals(op.getOperatorSymbol(), "/");
  }

  @Test
  public void testDiv() {
    Matrix4 divided = mat.div(2);
    testDivCommon(divided, primitive.div(2));
    assertSame(divided.getParents().get(0), mat);
    assertEquals(divided.getParents().get(1).evaluate(), 2.0f);

    divided = mat.div(new Real(2));
    testDivCommon(divided, primitive.div(2));
    assertSame(divided.getParents().get(0), mat);
    assertEquals(divided.getParents().get(1).evaluate(), 2.0f);

    divided = mat.div(mat);
    testDivCommon(divided, primitive.div(primitive));
    assertSame(divided.getParents().get(0), mat);
    assertSame(divided.getParents().get(1), mat);
  }

  @Test
  public void testNeg() {
    Matrix4 negated = mat.neg();
    assertEquals(negated.getParents().size(), 1);
    assertSame(negated.getParents().get(0), mat);
    assertTrue(negated.getEvaluator() instanceof NegationEvaluator);
    assertEquals(negated.evaluate(), mat.evaluate().neg());
  }

  @Test
  public void testTransform() {
    Vector4 transformed = mat.transform(new Vector4(1, 2, 3, 4));
    assertEquals(transformed.getParents().size(), 2);
    assertSame(transformed.getParents().get(0), mat);
    assertEquals(transformed.getParents().get(1).evaluate(), new Vector4.Primitive(1, 2, 3, 4));
    assertTrue(transformed.getEvaluator() instanceof BinaryOperationEvaluator);

    assertEquals(transformed.evaluate(), mat.evaluate().transform(new Vector4.Primitive(1, 2, 3, 4)));
  }
}
