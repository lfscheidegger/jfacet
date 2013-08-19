package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.evaluators.*;
import com.lfscheidegger.jfacet.shade.expression.evaluators.glsl.UniformEvaluator;
import com.lfscheidegger.jfacet.shade.expression.operators.Operator;
import com.lfscheidegger.jfacet.shade.expression.vector.swizzle.S;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Vector3}
 */
public class Vector3Test {

  private Vector3 vec;

  @Before
  public void setUp() {
    vec = new Vector3(1, 2, 3);
  }

  @Test
  public void testConstructors() {
    assertTrue(vec.getEvaluator() instanceof ConstantEvaluator);
    assertEquals(vec.getParents().size(), 0);
    assertEquals(vec.evaluate(), new Vector3.Primitive(1, 2, 3));

    vec = new Vector3(new Real(1), new Real(2), new Real(3));
    assertTrue(vec.getEvaluator() instanceof ConstructorEvaluator);
    assertEquals(vec.evaluate(), new Vector3.Primitive(1, 2, 3));

    vec = new Vector3(
        ImmutableList.<Expression>of(new Real(1), new Real(2), new Real(3)),
        new ConstructorEvaluator<Vector3.Primitive>());
    assertTrue(vec.getEvaluator() instanceof ConstructorEvaluator);
    assertEquals(vec.evaluate(), new Vector3.Primitive(1, 2, 3));

    vec = new Vector3(GlSlType.UNIFORM_T, new UniformEvaluator<Vector3.Primitive>());
    assertEquals(vec.getGlSlType(), GlSlType.UNIFORM_T);
    assertTrue(vec.getEvaluator() instanceof UniformEvaluator);
  }

  @Test
  public void testGetters() {
    assertTrue(vec.getX().evaluate() == 1);
    assertTrue(vec.getY().evaluate() == 2);
    assertTrue(vec.getZ().evaluate() == 3);

    assertTrue(vec.getX().evaluate().equals(vec.get(0).evaluate()));
    assertTrue(vec.getY().evaluate().equals(vec.get(1).evaluate()));
    assertTrue(vec.getZ().evaluate().equals(vec.get(2).evaluate()));
  }

  private void testBinOpCommon(Vector3 added, Vector3.Primitive expected, String expectedOperatorSymbol) {
    assertEquals(added.getParents().size(), 2);
    assertEquals(added.evaluate(), expected);
    assertTrue(added.getEvaluator() instanceof BinaryOperationEvaluator);
    Operator op = ((BinaryOperationEvaluator)added.getEvaluator()).getOperator();
    assertEquals(op.getOperatorSymbol(), expectedOperatorSymbol);
  }

  @Test
  public void testAdd() {
    Vector3 added = vec.add(1);
    testBinOpCommon(added, new Vector3.Primitive(2, 3, 4), "+");
    assertSame(added.getParents().get(0), vec);
    assertEquals(added.getParents().get(1).evaluate(), 1.0f);

    added = vec.add(new Real(1));
    testBinOpCommon(added, new Vector3.Primitive(2, 3, 4), "+");
    assertSame(added.getParents().get(0), vec);
    assertEquals(added.getParents().get(1).evaluate(), 1.0f);

    added = vec.add(vec);
    testBinOpCommon(added, new Vector3.Primitive(2, 4, 6), "+");
    assertSame(added.getParents().get(0), vec);
    assertSame(added.getParents().get(1), vec);
  }

  @Test
  public void testSub() {
    Vector3 subtracted = vec.sub(1);
    testBinOpCommon(subtracted, new Vector3.Primitive(0, 1, 2), "-");
    assertSame(subtracted.getParents().get(0), vec);
    assertEquals(subtracted.getParents().get(1).evaluate(), 1.0f);

    subtracted = vec.sub(new Real(1));
    testBinOpCommon(subtracted, new Vector3.Primitive(0, 1, 2), "-");
    assertSame(subtracted.getParents().get(0), vec);
    assertEquals(subtracted.getParents().get(1).evaluate(), 1.0f);

    subtracted = vec.sub(vec);
    testBinOpCommon(subtracted, new Vector3.Primitive(0, 0, 0), "-");
    assertSame(subtracted.getParents().get(0), vec);
    assertSame(subtracted.getParents().get(1), vec);
  }

  @Test
  public void testMul() {
    Vector3 multiplied = vec.mul(2);
    testBinOpCommon(multiplied, new Vector3.Primitive(2, 4, 6), "*");
    assertSame(multiplied.getParents().get(0), vec);
    assertEquals(multiplied.getParents().get(1).evaluate(), 2.0f);

    multiplied = vec.mul(new Real(2));
    testBinOpCommon(multiplied, new Vector3.Primitive(2, 4, 6), "*");
    assertSame(multiplied.getParents().get(0), vec);
    assertEquals(multiplied.getParents().get(1).evaluate(), 2.0f);

    multiplied = vec.mul(vec);
    testBinOpCommon(multiplied, new Vector3.Primitive(1, 4, 9), "*");
    assertSame(multiplied.getParents().get(0), vec);
    assertSame(multiplied.getParents().get(1), vec);
  }

  @Test
  public void testDiv() {
    Vector3 divided = vec.div(2);
    testBinOpCommon(divided, new Vector3.Primitive(0.5f, 1, 1.5f), "/");
    assertSame(divided.getParents().get(0), vec);
    assertEquals(divided.getParents().get(1).evaluate(), 2.0f);

    divided = vec.div(new Real(2));
    testBinOpCommon(divided, new Vector3.Primitive(0.5f, 1, 1.5f), "/");
    assertSame(divided.getParents().get(0), vec);
    assertEquals(divided.getParents().get(1).evaluate(), 2.0f);

    divided = vec.div(vec);
    testBinOpCommon(divided, new Vector3.Primitive(1, 1, 1), "/");
    assertSame(divided.getParents().get(0), vec);
    assertSame(divided.getParents().get(1), vec);
  }

  @Test
  public void testNeg() {
    Vector3 negated = vec.neg();
    assertEquals(negated.getParents().size(), 1);
    assertSame(negated.getParents().get(0), vec);
    assertTrue(negated.getEvaluator() instanceof NegationEvaluator);
    assertEquals(negated.evaluate(), vec.evaluate().neg());
  }

  @Test
  public void testDot() {
    Real dot = vec.dot(new Vector3(1, 2, 3));
    assertEquals(dot.getParents().size(), 2);
    assertSame(dot.getParents().get(0), vec);
    assertEquals(dot.getParents().get(1).evaluate(), new Vector3.Primitive(1, 2, 3));
    assertTrue(dot.getEvaluator() instanceof FunctionEvaluator);

    FunctionEvaluator evaluator = ((FunctionEvaluator)dot.getEvaluator());
    assertEquals(evaluator.getFunctionName(), "dot");
    assertEquals(evaluator.getType(), Type.FLOAT_T);

    assertTrue(dot.evaluate() == vec.evaluate().dot(new Vector3.Primitive(1, 2, 3)));
  }

  @Test
  public void testNormalize() {
    Vector3 normalized = vec.normalize();
    assertEquals(normalized.getParents().size(), 1);
    assertSame(normalized.getParents().get(0), vec);
    assertTrue(normalized.getEvaluator() instanceof FunctionEvaluator);

    FunctionEvaluator evaluator = ((FunctionEvaluator)normalized.getEvaluator());
    assertEquals(evaluator.getFunctionName(), "normalize");
    assertEquals(evaluator.getType(), Type.VEC3_T);

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
  public void testCross() {
    Vector3 cross = vec.cross(new Vector3(4, 5, 6));
    assertEquals(cross.getParents().size(), 2);
    assertTrue(cross.getEvaluator() instanceof FunctionEvaluator);

    FunctionEvaluator evaluator = ((FunctionEvaluator)cross.getEvaluator());
    assertEquals(evaluator.getFunctionName(), "cross");
    assertEquals(evaluator.getType(), Type.VEC3_T);
  }

  private void testBooleanOpCommon(BVector3 result, BVector3.Primitive expected, String functionName) {
    assertEquals(result.getParents().size(), 2);
    assertTrue(result.getEvaluator() instanceof FunctionEvaluator);
    assertEquals(result.evaluate(), expected);

    FunctionEvaluator evaluator = ((FunctionEvaluator)result.getEvaluator());
    assertEquals(evaluator.getFunctionName(), functionName);
    assertEquals(evaluator.getType(), Type.BVEC3_T);
  }

  @Test
  public void testIsLessThan() {
    BVector3 lessThan = vec.isLessThan(new Vector3(1, 1, 1));
    testBooleanOpCommon(lessThan, new BVector3.Primitive(false, false, false), "lessThan");
    assertSame(lessThan.getParents().get(0), vec);
    assertEquals(lessThan.getParents().get(1).evaluate(), new Vector3.Primitive(1, 1, 1));
  }

  @Test
  public void testIsLessThanOrEqual() {
    BVector3 lessThanOrEqual = vec.isLessThanOrEqual(new Vector3(1, 1, 1));
    testBooleanOpCommon(lessThanOrEqual, new BVector3.Primitive(true, false, false), "lessThanEqual");
    assertSame(lessThanOrEqual.getParents().get(0), vec);
    assertEquals(lessThanOrEqual.getParents().get(1).evaluate(), new Vector3.Primitive(1, 1, 1));
  }

  @Test
  public void testIsGreaterThan() {
    BVector3 greaterThan = vec.isGreaterThan(new Vector3(2, 2, 2));
    testBooleanOpCommon(greaterThan, new BVector3.Primitive(false, false, true), "greaterThan");
    assertSame(greaterThan.getParents().get(0), vec);
    assertEquals(greaterThan.getParents().get(1).evaluate(), new Vector3.Primitive(2, 2, 2));
  }

  @Test
  public void testIsGreaterThanOrEqual() {
    BVector3 greaterThanOrEqual = vec.isGreaterThanOrEqual(new Vector3(2, 2, 2));
    testBooleanOpCommon(greaterThanOrEqual, new BVector3.Primitive(false, true, true), "greaterThanEqual");
    assertSame(greaterThanOrEqual.getParents().get(0), vec);
    assertEquals(greaterThanOrEqual.getParents().get(1).evaluate(), new Vector3.Primitive(2, 2, 2));
  }

  @Test
  public void testIsEqualComponentwise() {
    BVector3 equalComponentwise = vec.isEqualComponentwise(new Vector3(2, 2, 2));
    testBooleanOpCommon(equalComponentwise, new BVector3.Primitive(false, true, false), "equal");
    assertSame(equalComponentwise.getParents().get(0), vec);
    assertEquals(equalComponentwise.getParents().get(1).evaluate(), new Vector3.Primitive(2, 2, 2));
  }

  @Test
  public void testIsNotEqualComponentwise() {
    BVector3 notEqualComponentwise = vec.isNotEqualComponentwise(new Vector3(2, 2, 2));
    testBooleanOpCommon(notEqualComponentwise, new BVector3.Primitive(true, false, true), "notEqual");
    assertSame(notEqualComponentwise.getParents().get(0), vec);
    assertEquals(notEqualComponentwise.getParents().get(1).evaluate(), new Vector3.Primitive(2, 2, 2));
  }

  @Test
  public void testIsEqual() {
    Bool isEqual = vec.isEqual(new Vector3(1, 2, 3));
    assertEquals(isEqual.getParents().size(), 2);
    assertSame(isEqual.getParents().get(0), vec);
    assertEquals(isEqual.getParents().get(1).evaluate(), new Vector3.Primitive(1, 2, 3));

    assertTrue(isEqual.getEvaluator() instanceof BinaryOperationEvaluator);

    BinaryOperationEvaluator evaluator = ((BinaryOperationEvaluator)isEqual.getEvaluator());
    assertEquals(evaluator.getOperator().getOperatorSymbol(), "==");

    assertTrue(isEqual.evaluate());

    isEqual = vec.isEqual(new Vector3(1, 1, 1));
    assertFalse(isEqual.evaluate());
  }

  @Test
  public void testIsNotEqual() {
    Bool isNotEqual = vec.isNotEqual(new Vector3(1, 2, 3));
    assertEquals(isNotEqual.getParents().size(), 2);
    assertSame(isNotEqual.getParents().get(0), vec);
    assertEquals(isNotEqual.getParents().get(1).evaluate(), new Vector3.Primitive(1, 2, 3));

    assertTrue(isNotEqual.getEvaluator() instanceof BinaryOperationEvaluator);

    BinaryOperationEvaluator evaluator = ((BinaryOperationEvaluator)isNotEqual.getEvaluator());
    assertEquals(evaluator.getOperator().getOperatorSymbol(), "!=");

    assertFalse(isNotEqual.evaluate());

    isNotEqual = vec.isNotEqual(new Vector3(1, 1, 1));
    assertTrue(isNotEqual.evaluate());
  }

  @Test
  public void testSwizzle() {
    Real swizzle = vec.swizzle(S.D31.X);
    assertEquals(swizzle.getParents().size(), 1);
    assertSame(swizzle.getParents().get(0), vec);
    assertTrue(swizzle.getEvaluator() instanceof SwizzleEvaluator);
    assertTrue(swizzle.evaluate().equals(vec.evaluate().swizzle(S.D31.X)));

    Vector2 swizzle2 = vec.swizzle(S.D32.YX);
    assertEquals(swizzle2.getParents().size(), 1);
    assertSame(swizzle2.getParents().get(0), vec);
    assertTrue(swizzle2.getEvaluator() instanceof SwizzleEvaluator);
    assertEquals(swizzle2.evaluate(), vec.evaluate().swizzle(S.D32.YX));

    Vector3 swizzle3 = vec.swizzle(S.D33.YXX);
    assertEquals(swizzle3.getParents().size(), 1);
    assertSame(swizzle3.getParents().get(0), vec);
    assertTrue(swizzle3.getEvaluator() instanceof SwizzleEvaluator);
    assertEquals(swizzle3.evaluate(), vec.evaluate().swizzle(S.D33.YXX));

    Vector4 swizzle4 = vec.swizzle(S.D34.YXXY);
    assertEquals(swizzle4.getParents().size(), 1);
    assertSame(swizzle4.getParents().get(0), vec);
    assertTrue(swizzle4.getEvaluator() instanceof SwizzleEvaluator);
    assertEquals(swizzle4.evaluate(), vec.evaluate().swizzle(S.D34.YXXY));
  }

  @Test
  @SuppressWarnings("all")
  public void testFill() {
    Vector4 filled = vec.fill(new Vector4(1, 2, 3, 4));
    assertEquals(filled.getParents().size(), 4);
    assertEquals(filled.getParents().get(0).evaluate(), 1.0f);
    assertEquals(filled.getParents().get(1).evaluate(), 2.0f);
    assertEquals(filled.getParents().get(2).evaluate(), 3.0f);
    assertEquals(filled.getParents().get(3).evaluate(), 4.0f);

    assertTrue(filled.getEvaluator() instanceof ConstructorEvaluator);
    assertEquals(filled.evaluate(), new Vector4.Primitive(1, 2, 3, 4));
  }
}
