package com.lfscheidegger.jfacet.shade;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.evaluators.FloatEvaluators;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Vec2Evaluators;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Vec3Evaluators;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Vec4Evaluators;
import com.lfscheidegger.jfacet.shade.expression.operators.FloatOperators;
import com.lfscheidegger.jfacet.shade.expression.operators.Vec2Operators;
import com.lfscheidegger.jfacet.shade.expression.operators.Vec3Operators;
import com.lfscheidegger.jfacet.shade.expression.operators.Vec4Operators;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

/**
 * Convenience methods to promote primitive types into expressions
 */
public class Shade {

  // ===================================================================================================================
  // Stuff for float
  // ===================================================================================================================

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

  // ===================================================================================================================
  // Stuff for Vec2
  // ===================================================================================================================

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

  // ===================================================================================================================
  // Stuff for Vec3
  // ===================================================================================================================

  public static Vec3Exp vec(Vec3 val) {
    return new Vec3Exp(val);
  }

  public static Vec3Exp vec(float x, float y, float z) {
    return vec(new FloatExp(x), new FloatExp(y), new FloatExp(z));
  }
  public static Vec3Exp vec(float x, float y, FloatExp z) {
    return vec(new FloatExp(x), new FloatExp(y), z);
  }
  public static Vec3Exp vec(float x, FloatExp y, float z) {
    return vec(new FloatExp(x), y, new FloatExp(z));
  }
  public static Vec3Exp vec(float x, FloatExp y, FloatExp z) {
    return vec(new FloatExp(x), y, z);
  }
  public static Vec3Exp vec(FloatExp x, float y, float z) {
    return vec(x, new FloatExp(y), new FloatExp(z));
  }
  public static Vec3Exp vec(FloatExp x, float y, FloatExp z) {
    return vec(x, new FloatExp(y), z);
  }
  public static Vec3Exp vec(FloatExp x, FloatExp y, float z) {
    return vec(x, y, new FloatExp(z));
  }
  public static Vec3Exp vec(FloatExp x, FloatExp y, FloatExp z) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(x, y, z),
        Vec3Evaluators.forComponents());
  }

  public static Vec3Exp add(Vec3 left, float right) {
    return add(vec(left), constant(right));
  }
  public static Vec3Exp add(Vec3Exp left, float right) {
    return add(left, constant(right));
  }
  public static Vec3Exp add(Vec3 left, FloatExp right) {
    return add(vec(left), right);
  }
  public static Vec3Exp add(Vec3Exp left, FloatExp right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(left, right),
        Vec3Evaluators.forOperationWithFloat(Vec3Operators.forAdditionWithFloat()));
  }

  public static Vec3Exp sub(Vec3 left, float right) {
    return sub(vec(left), constant(right));
  }
  public static Vec3Exp sub(Vec3Exp left, float right) {
    return sub(left, constant(right));
  }
  public static Vec3Exp sub(Vec3 left, FloatExp right) {
    return sub(vec(left), right);
  }
  public static Vec3Exp sub(Vec3Exp left, FloatExp right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(left, right),
        Vec3Evaluators.forOperationWithFloat(Vec3Operators.forSubtractionWithFloat()));
  }

  public static Vec3Exp mul(Vec3 left, float right) {
    return mul(vec(left), constant(right));
  }
  public static Vec3Exp mul(Vec3Exp left, float right) {
    return mul(left, constant(right));
  }
  public static Vec3Exp mul(Vec3 left, FloatExp right) {
    return mul(vec(left), right);
  }
  public static Vec3Exp mul(Vec3Exp left, FloatExp right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(left, right),
        Vec3Evaluators.forOperationWithFloat(Vec3Operators.forMultiplicationWithFloat()));
  }

  public static Vec3Exp div(Vec3 left, float right) {
    return div(vec(left), constant(right));
  }
  public static Vec3Exp div(Vec3Exp left, float right) {
    return div(left, constant(right));
  }
  public static Vec3Exp div(Vec3 left, FloatExp right) {
    return div(vec(left), right);
  }
  public static Vec3Exp div(Vec3Exp left, FloatExp right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(left, right),
        Vec3Evaluators.forOperationWithFloat(Vec3Operators.forDivisionWithFloat()));
  }

  public static Vec3Exp add(Vec3 left, Vec3 right) {
    return add(vec(left), vec(right));
  }
  public static Vec3Exp add(Vec3Exp left, Vec3 right) {
    return add(left, vec(right));
  }
  public static Vec3Exp add(Vec3 left, Vec3Exp right) {
    return add(vec(left), right);
  }
  public static Vec3Exp add(Vec3Exp left, Vec3Exp right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(left, right),
        Vec3Evaluators.forOperationWithVec3(Vec3Operators.forAdditionWithVec3()));
  }

  public static Vec3Exp sub(Vec3 left, Vec3 right) {
    return sub(vec(left), vec(right));
  }
  public static Vec3Exp sub(Vec3Exp left, Vec3 right) {
    return sub(left, vec(right));
  }
  public static Vec3Exp sub(Vec3 left, Vec3Exp right) {
    return sub(vec(left), right);
  }
  public static Vec3Exp sub(Vec3Exp left, Vec3Exp right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(left, right),
        Vec3Evaluators.forOperationWithVec3(Vec3Operators.forSubtractionWithVec3()));
  }

  public static Vec3Exp mul(Vec3 left, Vec3 right) {
    return mul(vec(left), vec(right));
  }
  public static Vec3Exp mul(Vec3Exp left, Vec3 right) {
    return mul(left, vec(right));
  }
  public static Vec3Exp mul(Vec3 left, Vec3Exp right) {
    return mul(vec(left), right);
  }
  public static Vec3Exp mul(Vec3Exp left, Vec3Exp right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(left, right),
        Vec3Evaluators.forOperationWithVec3(Vec3Operators.forMultiplicationWithVec3()));
  }

  public static Vec3Exp div(Vec3 left, Vec3 right) {
    return div(vec(left), vec(right));
  }
  public static Vec3Exp div(Vec3Exp left, Vec3 right) {
    return div(left, vec(right));
  }
  public static Vec3Exp div(Vec3 left, Vec3Exp right) {
    return div(vec(left), right);
  }
  public static Vec3Exp div(Vec3Exp left, Vec3Exp right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(left, right),
        Vec3Evaluators.forOperationWithVec3(Vec3Operators.forDivisionWithVec3()));
  }

  // ===================================================================================================================
  // Stuff for Vec4
  // ===================================================================================================================

  public static Vec4Exp vec(Vec4 val) {
    return new Vec4Exp(val);
  }

  public static Vec4Exp vec(float x, float y, float z, float w) {
    return vec(new FloatExp(x), new FloatExp(y), new FloatExp(z), new FloatExp(w));
  }
  public static Vec4Exp vec(float x, float y, float z, FloatExp w) {
    return vec(new FloatExp(x), new FloatExp(y), new FloatExp(z), w);
  }
  public static Vec4Exp vec(float x, float y, FloatExp z, float w) {
    return vec(new FloatExp(x), new FloatExp(y), z, new FloatExp(w));
  }
  public static Vec4Exp vec(float x, float y, FloatExp z, FloatExp w) {
    return vec(new FloatExp(x), new FloatExp(y), z, w);
  }
  public static Vec4Exp vec(float x, FloatExp y, float z, float w) {
    return vec(new FloatExp(x), y, new FloatExp(z), new FloatExp(w));
  }
  public static Vec4Exp vec(float x, FloatExp y, float z, FloatExp w) {
    return vec(new FloatExp(x), y, new FloatExp(z), w);
  }
  public static Vec4Exp vec(float x, FloatExp y, FloatExp z, float w) {
    return vec(new FloatExp(x), y, z, new FloatExp(w));
  }
  public static Vec4Exp vec(float x, FloatExp y, FloatExp z, FloatExp w) {
    return vec(new FloatExp(x), y, z, w);
  }
  public static Vec4Exp vec(FloatExp x, float y, float z, float w) {
    return vec(x, new FloatExp(y), new FloatExp(z), new FloatExp(w));
  }
  public static Vec4Exp vec(FloatExp x, float y, float z, FloatExp w) {
    return vec(x, new FloatExp(y), new FloatExp(z), w);
  }
  public static Vec4Exp vec(FloatExp x, float y, FloatExp z, float w) {
    return vec(x, new FloatExp(y), z, new FloatExp(w));
  }
  public static Vec4Exp vec(FloatExp x, float y, FloatExp z, FloatExp w) {
    return vec(x, new FloatExp(y), z, w);
  }
  public static Vec4Exp vec(FloatExp x, FloatExp y, float z, float w) {
    return vec(x, y, new FloatExp(z), new FloatExp(w));
  }
  public static Vec4Exp vec(FloatExp x, FloatExp y, float z, FloatExp w) {
    return vec(x, y, new FloatExp(z), w);
  }
  public static Vec4Exp vec(FloatExp x, FloatExp y, FloatExp z, float w) {
    return vec(x, y, z, new FloatExp(w));
  }
  public static Vec4Exp vec(FloatExp x, FloatExp y, FloatExp z, FloatExp w) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(x, y, z, w),
        Vec4Evaluators.forComponents());
  }

  public static Vec4Exp add(Vec4 left, float right) {
    return add(vec(left), constant(right));
  }
  public static Vec4Exp add(Vec4Exp left, float right) {
    return add(left, constant(right));
  }
  public static Vec4Exp add(Vec4 left, FloatExp right) {
    return add(vec(left), right);
  }
  public static Vec4Exp add(Vec4Exp left, FloatExp right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(left, right),
        Vec4Evaluators.forOperationWithFloat(Vec4Operators.forAdditionWithFloat()));
  }

  public static Vec4Exp sub(Vec4 left, float right) {
    return sub(vec(left), constant(right));
  }
  public static Vec4Exp sub(Vec4Exp left, float right) {
    return sub(left, constant(right));
  }
  public static Vec4Exp sub(Vec4 left, FloatExp right) {
    return sub(vec(left), right);
  }
  public static Vec4Exp sub(Vec4Exp left, FloatExp right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(left, right),
        Vec4Evaluators.forOperationWithFloat(Vec4Operators.forSubtractionWithFloat()));
  }

  public static Vec4Exp mul(Vec4 left, float right) {
    return mul(vec(left), constant(right));
  }
  public static Vec4Exp mul(Vec4Exp left, float right) {
    return mul(left, constant(right));
  }
  public static Vec4Exp mul(Vec4 left, FloatExp right) {
    return mul(vec(left), right);
  }
  public static Vec4Exp mul(Vec4Exp left, FloatExp right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(left, right),
        Vec4Evaluators.forOperationWithFloat(Vec4Operators.forMultiplicationWithFloat()));
  }

  public static Vec4Exp div(Vec4 left, float right) {
    return div(vec(left), constant(right));
  }
  public static Vec4Exp div(Vec4Exp left, float right) {
    return div(left, constant(right));
  }
  public static Vec4Exp div(Vec4 left, FloatExp right) {
    return div(vec(left), right);
  }
  public static Vec4Exp div(Vec4Exp left, FloatExp right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(left, right),
        Vec4Evaluators.forOperationWithFloat(Vec4Operators.forDivisionWithFloat()));
  }

  public static Vec4Exp add(Vec4 left, Vec4 right) {
    return add(vec(left), vec(right));
  }
  public static Vec4Exp add(Vec4Exp left, Vec4 right) {
    return add(left, vec(right));
  }
  public static Vec4Exp add(Vec4 left, Vec4Exp right) {
    return add(vec(left), right);
  }
  public static Vec4Exp add(Vec4Exp left, Vec4Exp right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(left, right),
        Vec4Evaluators.forOperationWithVec4(Vec4Operators.forAdditionWithVec4()));
  }

  public static Vec4Exp sub(Vec4 left, Vec4 right) {
    return sub(vec(left), vec(right));
  }
  public static Vec4Exp sub(Vec4Exp left, Vec4 right) {
    return sub(left, vec(right));
  }
  public static Vec4Exp sub(Vec4 left, Vec4Exp right) {
    return sub(vec(left), right);
  }
  public static Vec4Exp sub(Vec4Exp left, Vec4Exp right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(left, right),
        Vec4Evaluators.forOperationWithVec4(Vec4Operators.forSubtractionWithVec4()));
  }

  public static Vec4Exp mul(Vec4 left, Vec4 right) {
    return mul(vec(left), vec(right));
  }
  public static Vec4Exp mul(Vec4Exp left, Vec4 right) {
    return mul(left, vec(right));
  }
  public static Vec4Exp mul(Vec4 left, Vec4Exp right) {
    return mul(vec(left), right);
  }
  public static Vec4Exp mul(Vec4Exp left, Vec4Exp right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(left, right),
        Vec4Evaluators.forOperationWithVec4(Vec4Operators.forMultiplicationWithVec4()));
  }

  public static Vec4Exp div(Vec4 left, Vec4 right) {
    return div(vec(left), vec(right));
  }
  public static Vec4Exp div(Vec4Exp left, Vec4 right) {
    return div(left, vec(right));
  }
  public static Vec4Exp div(Vec4 left, Vec4Exp right) {
    return div(vec(left), right);
  }
  public static Vec4Exp div(Vec4Exp left, Vec4Exp right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(left, right),
        Vec4Evaluators.forOperationWithVec4(Vec4Operators.forDivisionWithVec4()));
  }
}
