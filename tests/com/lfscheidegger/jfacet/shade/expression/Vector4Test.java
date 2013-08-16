package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.*;
import com.lfscheidegger.jfacet.shade.expression.evaluators.glsl.UniformEvaluator;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Vector4}
 */
public class Vector4Test {

  private Vector4 vec;

  @Before
  public void setUp() {
    vec = new Vector4(1, 2, 3, 4);
  }

  @Test
  public void testConstructors() {
    assertTrue(vec.getEvaluator() instanceof ConstantEvaluator);
    assertEquals(vec.evaluate(), new Vector4.Primitive(1, 2, 3, 4));

    vec = new Vector4(new Real(1), new Real(2), new Real(3), new Real(4));
    assertTrue(vec.getEvaluator() instanceof ConstructorEvaluator);
    assertEquals(vec.evaluate(), new Vector4.Primitive(1, 2, 3, 4));

    vec = new Vector4(
        ImmutableList.<Expression>of(new Real(1), new Real(2), new Real(3), new Real(4)),
        new ConstructorEvaluator<Vector4.Primitive>());
    assertTrue(vec.getEvaluator() instanceof ConstructorEvaluator);
    assertEquals(vec.evaluate(), new Vector4.Primitive(1, 2, 3, 4));

    vec = new Vector4(GlSlType.UNIFORM_T, new UniformEvaluator<Vector4.Primitive>());
    assertEquals(vec.getGlSlType(), GlSlType.UNIFORM_T);
    assertTrue(vec.getEvaluator() instanceof UniformEvaluator);
  }

  @Test
  public void testGetters() {
    assertTrue(vec.getX().evaluate() == 1);
    assertTrue(vec.getY().evaluate() == 2);
    assertTrue(vec.getZ().evaluate() == 3);
    assertTrue(vec.getW().evaluate() == 4);

    assertTrue(vec.getX().evaluate().equals(vec.get(0).evaluate()));
    assertTrue(vec.getY().evaluate().equals(vec.get(1).evaluate()));
    assertTrue(vec.getZ().evaluate().equals(vec.get(2).evaluate()));
    assertTrue(vec.getW().evaluate().equals(vec.get(3).evaluate()));
  }

  private void testAddCommon(Vector4 added, Vector4.Primitive expected) {
    assertEquals(added.getParents().size(), 2);
    assertEquals(added.evaluate(),  expected);
    assertTrue(added.getEvaluator() instanceof BinaryOperationEvaluator);
    Operator op = ((BinaryOperationEvaluator)added.getEvaluator()).getOperator();
    assertEquals(op.getOperatorSymbol(), "+");
  }

  @Test
  public void testAdd() {
    Vector4 added = vec.add(1);
    testAddCommon(added, new Vector4.Primitive(2, 3, 4, 5));
    assertSame(added.getParents().get(0), vec);
    assertEquals(added.getParents().get(1).evaluate(), 1.0f);

    added = vec.add(new Real(1));
    testAddCommon(added, new Vector4.Primitive(2, 3, 4, 5));
    assertSame(added.getParents().get(0), vec);
    assertEquals(added.getParents().get(1).evaluate(), 1.0f);

    added = vec.add(vec);
    testAddCommon(added, new Vector4.Primitive(2, 4, 6, 8));
    assertSame(added.getParents().get(0), vec);
    assertSame(added.getParents().get(1), vec);
  }

  public void testSubCommon(Vector4 subtracted, Vector4.Primitive expected) {
    assertEquals(subtracted.getParents().size(), 2);
    assertEquals(subtracted.evaluate(), expected);
    assertTrue(subtracted.getEvaluator() instanceof BinaryOperationEvaluator);
    Operator op = ((BinaryOperationEvaluator)subtracted.getEvaluator()).getOperator();
    assertEquals(op.getOperatorSymbol(), "-");
  }

  @Test
  public void testSub() {
    Vector4 subtracted = vec.sub(1);
    testSubCommon(subtracted, new Vector4.Primitive(0, 1, 2, 3));
    assertSame(subtracted.getParents().get(0), vec);
    assertEquals(subtracted.getParents().get(1).evaluate(), 1.0f);

    subtracted = vec.sub(new Real(1));
    testSubCommon(subtracted, new Vector4.Primitive(0, 1, 2, 3));
    assertSame(subtracted.getParents().get(0), vec);
    assertEquals(subtracted.getParents().get(1).evaluate(), 1.0f);

    subtracted = vec.sub(vec);
    testSubCommon(subtracted, new Vector4.Primitive(0, 0, 0, 0));
    assertSame(subtracted.getParents().get(0), vec);
    assertSame(subtracted.getParents().get(1), vec);
  }

  private void testMulCommon(Vector4 multiplied, Vector4.Primitive expected) {
    assertEquals(multiplied.getParents().size(), 2);
    assertEquals(multiplied.evaluate(), expected);
    assertTrue(multiplied.getEvaluator() instanceof BinaryOperationEvaluator);
    Operator op = ((BinaryOperationEvaluator)multiplied.getEvaluator()).getOperator();
    assertEquals(op.getOperatorSymbol(), "*");
  }

  @Test
  public void testMul() {
    Vector4 multiplied = vec.mul(2);
    testMulCommon(multiplied, new Vector4.Primitive(2, 4, 6, 8));
    assertSame(multiplied.getParents().get(0), vec);
    assertEquals(multiplied.getParents().get(1).evaluate(), 2.0f);

    multiplied = vec.mul(new Real(2));
    testMulCommon(multiplied, new Vector4.Primitive(2, 4, 6, 8));
    assertSame(multiplied.getParents().get(0), vec);
    assertEquals(multiplied.getParents().get(1).evaluate(), 2.0f);

    multiplied = vec.mul(vec);
    testMulCommon(multiplied, new Vector4.Primitive(1, 4, 9, 16));
    assertSame(multiplied.getParents().get(0), vec);
    assertSame(multiplied.getParents().get(1), vec);
  }

  private void testDivCommon(Vector4 divided, Vector4.Primitive expected) {
    assertEquals(divided.getParents().size(), 2);
    assertEquals(divided.evaluate(), expected);
    assertTrue(divided.getEvaluator() instanceof BinaryOperationEvaluator);
    Operator op = ((BinaryOperationEvaluator)divided.getEvaluator()).getOperator();
    assertEquals(op.getOperatorSymbol(), "/");
  }

  @Test
  public void testDiv() {
    Vector4 divided = vec.div(2);
    testDivCommon(divided, new Vector4.Primitive(0.5f, 1, 1.5f, 2.0f));
    assertSame(divided.getParents().get(0), vec);
    assertEquals(divided.getParents().get(1).evaluate(), 2.0f);

    divided = vec.div(new Real(2));
    testDivCommon(divided, new Vector4.Primitive(0.5f, 1, 1.5f, 2.0f));
    assertSame(divided.getParents().get(0), vec);
    assertEquals(divided.getParents().get(1).evaluate(), 2.0f);

    divided = vec.div(vec);
    testDivCommon(divided, new Vector4.Primitive(1, 1, 1, 1));
    assertSame(divided.getParents().get(0), vec);
    assertSame(divided.getParents().get(1), vec);
  }

  @Test
  public void testNeg() {
    Vector4 negated = vec.neg();
    assertEquals(negated.getParents().size(), 1);
    assertSame(negated.getParents().get(0), vec);
    assertTrue(negated.getEvaluator() instanceof NegationEvaluator);
    assertEquals(negated.evaluate(), vec.evaluate().neg());
  }

  @Test
  public void testDot() {
    Real dot = vec.dot(new Vector4(1, 2, 3, 4));
    assertEquals(dot.getParents().size(), 2);
    assertSame(dot.getParents().get(0), vec);
    assertEquals(dot.getParents().get(1).evaluate(), new Vector4.Primitive(1, 2, 3, 4));
    assertTrue(dot.getEvaluator() instanceof FunctionEvaluator);

    FunctionEvaluator evaluator = ((FunctionEvaluator)dot.getEvaluator());
    assertEquals(evaluator.getFunctionName(), "dot");
    assertEquals(evaluator.getType(), Type.FLOAT_T);

    assertTrue(dot.evaluate() == vec.evaluate().dot(new Vector4.Primitive(1, 2, 3, 4)));
  }

  @Test
  public void testNormalize() {
    Vector4 normalized = vec.normalize();
    assertEquals(normalized.getParents().size(), 1);
    assertSame(normalized.getParents().get(0), vec);
    assertTrue(normalized.getEvaluator() instanceof FunctionEvaluator);

    FunctionEvaluator evaluator = ((FunctionEvaluator)normalized.getEvaluator());
    assertEquals(evaluator.getFunctionName(), "normalize");
    assertEquals(evaluator.getType(), Type.VEC4_T);

    assertEquals(normalized.evaluate(), vec.evaluate().normalize());
  }

  @Test
  public void testLength() {
    Real length = vec.length();
    assertEquals(length.getParents().size(), 1);
    assertSame(length.getParents().get(0), vec);
    assertTrue(length.getEvaluator() instanceof FunctionEvaluator);

    FunctionEvaluator evaluator = ((FunctionEvaluator)length.getEvaluator());
    assertEquals(evaluator.getFunctionName(), "length");
    assertEquals(evaluator.getType(), Type.FLOAT_T);
  }

  @Test
  public void testSwizzle() {
    Real swizzle = vec.swizzle('x');
    assertEquals(swizzle.getParents().size(), 1);
    assertSame(swizzle.getParents().get(0), vec);
    assertTrue(swizzle.getEvaluator() instanceof SwizzleEvaluator);
    assertTrue(swizzle.evaluate() == vec.evaluate().swizzle('x'));

    Vector2 swizzle2 = vec.swizzle('y', 'x');
    assertEquals(swizzle2.getParents().size(), 1);
    assertSame(swizzle2.getParents().get(0), vec);
    assertTrue(swizzle2.getEvaluator() instanceof SwizzleEvaluator);
    assertEquals(swizzle2.evaluate(), vec.evaluate().swizzle('y', 'x'));

    Vector3 swizzle3 = vec.swizzle('y', 'x', 'x');
    assertEquals(swizzle3.getParents().size(), 1);
    assertSame(swizzle3.getParents().get(0), vec);
    assertTrue(swizzle3.getEvaluator() instanceof SwizzleEvaluator);
    assertEquals(swizzle3.evaluate(), vec.evaluate().swizzle('y', 'x', 'x'));

    Vector4 swizzle4 = vec.swizzle('y', 'x', 'x', 'y');
    assertEquals(swizzle4.getParents().size(), 1);
    assertSame(swizzle4.getParents().get(0), vec);
    assertTrue(swizzle4.getEvaluator() instanceof SwizzleEvaluator);
    assertEquals(swizzle4.evaluate(), vec.evaluate().swizzle('y', 'x', 'x', 'y'));
  }

  @Test
  public void testFill() {
    Vector4 filled = vec.fill(new Vector4(1, 2, 3, 4));
    assertSame(vec, filled);
  }
}
