package com.lfscheidegger.jfacet.shade;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.Vec2Exp;
import com.lfscheidegger.jfacet.shade.expression.evaluators.FloatEvaluators;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Vec2Evaluators;
import com.lfscheidegger.jfacet.shade.expression.operators.FloatOperators;
import com.lfscheidegger.jfacet.shade.expression.operators.Vec2Operators;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;

/**
 * Convenience methods to promote primitive types into expressions
 */
public class Shade {

  public static FloatExp constant(float c) {
    return new FloatExp(c);
  }

  public static FloatExp add(FloatExp left, FloatExp right) {
    return new FloatExp(
        ImmutableList.<Expression>of(left, right),
        FloatEvaluators.forOperation(FloatOperators.forAddition()));
  }

  public static FloatExp add(FloatExp left, float right) {
    return add(left, new FloatExp(right));
  }

  public static FloatExp add(float left, FloatExp right) {
    return add(new FloatExp(left), right);
  }

  public static FloatExp add(float left, float right) {
    return add(new FloatExp(left), new FloatExp(right));
  }

  public static FloatExp sub(FloatExp left, FloatExp right) {
    return new FloatExp(
        ImmutableList.<Expression>of(left, right),
        FloatEvaluators.forOperation(FloatOperators.forSubtraction()));
  }

  public static FloatExp sub(FloatExp left, float right) {
    return sub(left, new FloatExp(right));
  }

  public static FloatExp sub(float left, FloatExp right) {
    return sub(new FloatExp(left), right);
  }

  public static FloatExp sub(float left, float right) {
    return sub(new FloatExp(left), new FloatExp(right));
  }

  public static FloatExp mul(FloatExp left, FloatExp right) {
    return new FloatExp(
        ImmutableList.<Expression>of(left, right),
        FloatEvaluators.forOperation(FloatOperators.forMultiplication()));
  }

  public static FloatExp mul(FloatExp left, float right) {
    return mul(left, new FloatExp(right));
  }

  public static FloatExp mul(float left, FloatExp right) {
    return mul(new FloatExp(left), right);
  }

  public static FloatExp mul(float left, float right) {
    return mul(new FloatExp(left), new FloatExp(right));
  }

  public static FloatExp div(FloatExp left, FloatExp right) {
    return new FloatExp(
        ImmutableList.<Expression>of(left, right),
        FloatEvaluators.forOperation(FloatOperators.forDivision()));
  }

  public static FloatExp div(FloatExp left, float right) {
    return div(left, new FloatExp(right));
  }

  public static FloatExp div(float left, FloatExp right) {
    return div(new FloatExp(left), right);
  }

  public static FloatExp div(float left, float right) {
    return div(new FloatExp(left), new FloatExp(right));
  }

  public static Vec2Exp vec(Vec2 val) {
    return new Vec2Exp(val);
  }
  public static Vec2Exp vec(float x, float y) {
    return vec(new FloatExp(x), new FloatExp(y));
  }
  public static Vec2Exp vec(FloatExp x, float y) {
    return vec(x, new FloatExp(y));
  }
  public static Vec2Exp vec(float x, FloatExp y) {
    return vec(new FloatExp(x), y);
  }
  public static Vec2Exp vec(FloatExp x, FloatExp y) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(x, y),
        Vec2Evaluators.forComponents());
  }

  public static Vec2Exp add(Vec2 left, float right) {
    return add(vec(left), constant(right));
  }
  public static Vec2Exp add(Vec2Exp left, float right) {
    return add(left, constant(right));
  }
  public static Vec2Exp add(Vec2 left, FloatExp right) {
    return add(vec(left), right);
  }
  public static Vec2Exp add(Vec2Exp left, FloatExp right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(left, right),
        Vec2Evaluators.forOperationWithFloat(Vec2Operators.forAdditionWithFloat()));
  }

  public static Vec2Exp sub(Vec2 left, float right) {
    return sub(vec(left), constant(right));
  }
  public static Vec2Exp sub(Vec2Exp left, float right) {
    return sub(left, constant(right));
  }
  public static Vec2Exp sub(Vec2 left, FloatExp right) {
    return sub(vec(left), right);
  }
  public static Vec2Exp sub(Vec2Exp left, FloatExp right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(left, right),
        Vec2Evaluators.forOperationWithFloat(Vec2Operators.forSubtractionWithFloat()));
  }

  public static Vec2Exp mul(Vec2 left, float right) {
    return mul(vec(left), constant(right));
  }
  public static Vec2Exp mul(Vec2Exp left, float right) {
    return mul(left, constant(right));
  }
  public static Vec2Exp mul(Vec2 left, FloatExp right) {
    return mul(vec(left), right);
  }
  public static Vec2Exp mul(Vec2Exp left, FloatExp right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(left, right),
        Vec2Evaluators.forOperationWithFloat(Vec2Operators.forMultiplicationWithFloat()));
  }

  public static Vec2Exp div(Vec2 left, float right) {
    return div(vec(left), constant(right));
  }
  public static Vec2Exp div(Vec2Exp left, float right) {
    return div(left, constant(right));
  }
  public static Vec2Exp div(Vec2 left, FloatExp right) {
    return div(vec(left), right);
  }
  public static Vec2Exp div(Vec2Exp left, FloatExp right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(left, right),
        Vec2Evaluators.forOperationWithFloat(Vec2Operators.forDivisionWithFloat()));
  }

  public static Vec2Exp add(Vec2 left, Vec2 right) {
    return add(vec(left), vec(right));
  }
  public static Vec2Exp add(Vec2Exp left, Vec2 right) {
    return add(left, vec(right));
  }
  public static Vec2Exp add(Vec2 left, Vec2Exp right) {
    return add(vec(left), right);
  }
  public static Vec2Exp add(Vec2Exp left, Vec2Exp right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(left, right),
        Vec2Evaluators.forOperationWithVec2(Vec2Operators.forAdditionWithVec2()));
  }

  public static Vec2Exp sub(Vec2 left, Vec2 right) {
    return sub(vec(left), vec(right));
  }
  public static Vec2Exp sub(Vec2Exp left, Vec2 right) {
    return sub(left, vec(right));
  }
  public static Vec2Exp sub(Vec2 left, Vec2Exp right) {
    return sub(vec(left), right);
  }
  public static Vec2Exp sub(Vec2Exp left, Vec2Exp right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(left, right),
        Vec2Evaluators.forOperationWithVec2(Vec2Operators.forSubtractionWithVec2()));
  }

  public static Vec2Exp mul(Vec2 left, Vec2 right) {
    return mul(vec(left), vec(right));
  }
  public static Vec2Exp mul(Vec2Exp left, Vec2 right) {
    return mul(left, vec(right));
  }
  public static Vec2Exp mul(Vec2 left, Vec2Exp right) {
    return mul(vec(left), right);
  }
  public static Vec2Exp mul(Vec2Exp left, Vec2Exp right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(left, right),
        Vec2Evaluators.forOperationWithVec2(Vec2Operators.forMultiplicationWithVec2()));
  }

  public static Vec2Exp div(Vec2 left, Vec2 right) {
    return div(vec(left), vec(right));
  }
  public static Vec2Exp div(Vec2Exp left, Vec2 right) {
    return div(left, vec(right));
  }
  public static Vec2Exp div(Vec2 left, Vec2Exp right) {
    return div(vec(left), right);
  }
  public static Vec2Exp div(Vec2Exp left, Vec2Exp right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(left, right),
        Vec2Evaluators.forOperationWithVec2(Vec2Operators.forDivisionWithVec2()));
  }
}
