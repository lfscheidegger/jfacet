package com.lfscheidegger.jfacet.shade;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.evaluators.*;
import com.lfscheidegger.jfacet.shade.expression.operators.*;
import com.lfscheidegger.jfacet.shade.expression.primitives.*;
import com.lfscheidegger.jfacet.shade.primitives.*;

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

  // ===================================================================================================================
  // Stuff for Mat2
  // ===================================================================================================================

  public static Mat2Exp mat(Mat2 val) {
    return new Mat2Exp(val);
  }
  public static Mat2Exp mat(Vec2 x, Vec2 y) {
    return mat(new Vec2Exp(x), new Vec2Exp(y));
  }
  public static Mat2Exp mat(Vec2Exp x, Vec2 y) {
    return mat(x, new Vec2Exp(y));
  }
  public static Mat2Exp mat(Vec2 x, Vec2Exp y) {
    return mat(new Vec2Exp(x), y);
  }
  public static Mat2Exp mat(Vec2Exp x, Vec2Exp y) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(x, y),
        Mat2Evaluators.forComponents());
  }

  public static Mat2Exp add(Mat2 left, float right) {
    return add(mat(left), constant(right));
  }
  public static Mat2Exp add(Mat2Exp left, float right) {
    return add(left, constant(right));
  }
  public static Mat2Exp add(Mat2 left, FloatExp right) {
    return add(mat(left), right);
  }
  public static Mat2Exp add(Mat2Exp left, FloatExp right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(left, right),
        Mat2Evaluators.forOperationWithFloat(Mat2Operators.forAdditionWithFloat()));
  }
  public static Mat2Exp add(Mat2 left, Mat2 right) {
    return add(mat(left), mat(right));
  }
  public static Mat2Exp add(Mat2Exp left, Mat2 right) {
    return add(left, mat(right));
  }
  public static Mat2Exp add(Mat2 left, Mat2Exp right) {
    return add(mat(left), right);
  }
  public static Mat2Exp add(Mat2Exp left, Mat2Exp right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(left, right),
        Mat2Evaluators.forOperationWithMat2(Mat2Operators.forAdditionWithMat2()));
  }

  public static Mat2Exp sub(Mat2 left, float right) {
    return sub(mat(left), constant(right));
  }
  public static Mat2Exp sub(Mat2Exp left, float right) {
    return sub(left, constant(right));
  }
  public static Mat2Exp sub(Mat2 left, FloatExp right) {
    return sub(mat(left), right);
  }
  public static Mat2Exp sub(Mat2Exp left, FloatExp right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(left, right),
        Mat2Evaluators.forOperationWithFloat(Mat2Operators.forSubtractionWithFloat()));
  }
  public static Mat2Exp sub(Mat2 left, Mat2 right) {
    return sub(mat(left), mat(right));
  }
  public static Mat2Exp sub(Mat2Exp left, Mat2 right) {
    return sub(left, mat(right));
  }
  public static Mat2Exp sub(Mat2 left, Mat2Exp right) {
    return sub(mat(left), right);
  }
  public static Mat2Exp sub(Mat2Exp left, Mat2Exp right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(left, right),
        Mat2Evaluators.forOperationWithMat2(Mat2Operators.forSubtractionWithMat2()));
  }

  public static Mat2Exp mul(Mat2 left, float right) {
    return mul(mat(left), constant(right));
  }
  public static Mat2Exp mul(Mat2Exp left, float right) {
    return mul(left, constant(right));
  }
  public static Mat2Exp mul(Mat2 left, FloatExp right) {
    return mul(mat(left), right);
  }
  public static Mat2Exp mul(Mat2Exp left, FloatExp right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(left, right),
        Mat2Evaluators.forOperationWithFloat(Mat2Operators.forMultiplicationWithFloat()));
  }
  public static Mat2Exp mul(Mat2 left, Mat2 right) {
    return mul(mat(left), mat(right));
  }
  public static Mat2Exp mul(Mat2Exp left, Mat2 right) {
    return mul(left, mat(right));
  }
  public static Mat2Exp mul(Mat2 left, Mat2Exp right) {
    return mul(mat(left), right);
  }
  public static Mat2Exp mul(Mat2Exp left, Mat2Exp right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(left, right),
        Mat2Evaluators.forOperationWithMat2(Mat2Operators.forMultiplicationWithMat2()));
  }

  public static Mat2Exp div(Mat2 left, float right) {
    return div(mat(left), constant(right));
  }
  public static Mat2Exp div(Mat2Exp left, float right) {
    return div(left, constant(right));
  }
  public static Mat2Exp div(Mat2 left, FloatExp right) {
    return div(mat(left), right);
  }
  public static Mat2Exp div(Mat2Exp left, FloatExp right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(left, right),
        Mat2Evaluators.forOperationWithFloat(Mat2Operators.forDivisionWithFloat()));
  }
  public static Mat2Exp div(Mat2 left, Mat2 right) {
    return div(mat(left), mat(right));
  }
  public static Mat2Exp div(Mat2Exp left, Mat2 right) {
    return div(left, mat(right));
  }
  public static Mat2Exp div(Mat2 left, Mat2Exp right) {
    return div(mat(left), right);
  }
  public static Mat2Exp div(Mat2Exp left, Mat2Exp right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(left, right),
        Mat2Evaluators.forOperationWithMat2(Mat2Operators.forDivisionWithMat2()));
  }

  // ===================================================================================================================
  // Stuff for Mat3
  // ===================================================================================================================

  public static Mat3Exp mat(Mat3 val) {
    return new Mat3Exp(val);
  }
  public static Mat3Exp mat(Vec3 x, Vec3 y, Vec3 z) {
    return mat(new Vec3Exp(x), new Vec3Exp(y), new Vec3Exp(z));
  }
  public static Mat3Exp mat(Vec3Exp x, Vec3 y, Vec3 z) {
    return mat(x, new Vec3Exp(y), new Vec3Exp(z));
  }
  public static Mat3Exp mat(Vec3 x, Vec3Exp y, Vec3 z) {
    return mat(new Vec3Exp(x), y, new Vec3Exp(z));
  }
  public static Mat3Exp mat(Vec3Exp x, Vec3Exp y, Vec3 z) {
    return mat(x, y, new Vec3Exp(z));
  }
  public static Mat3Exp mat(Vec3 x, Vec3 y, Vec3Exp z) {
    return mat(new Vec3Exp(x), new Vec3Exp(y), z);
  }
  public static Mat3Exp mat(Vec3Exp x, Vec3 y, Vec3Exp z) {
    return mat(x, new Vec3Exp(y), z);
  }
  public static Mat3Exp mat(Vec3 x, Vec3Exp y, Vec3Exp z) {
    return mat(new Vec3Exp(x), y, z);
  }
  public static Mat3Exp mat(Vec3Exp x, Vec3Exp y, Vec3Exp z) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(x, y, z),
        Mat3Evaluators.forComponents());
  }

  public static Mat3Exp add(Mat3 left, float right) {
    return add(mat(left), constant(right));
  }
  public static Mat3Exp add(Mat3Exp left, float right) {
    return add(left, constant(right));
  }
  public static Mat3Exp add(Mat3 left, FloatExp right) {
    return add(mat(left), right);
  }
  public static Mat3Exp add(Mat3Exp left, FloatExp right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(left, right),
        Mat3Evaluators.forOperationWithFloat(Mat3Operators.forAdditionWithFloat()));
  }
  public static Mat3Exp add(Mat3 left, Mat3 right) {
    return add(mat(left), mat(right));
  }
  public static Mat3Exp add(Mat3Exp left, Mat3 right) {
    return add(left, mat(right));
  }
  public static Mat3Exp add(Mat3 left, Mat3Exp right) {
    return add(mat(left), right);
  }
  public static Mat3Exp add(Mat3Exp left, Mat3Exp right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(left, right),
        Mat3Evaluators.forOperationWithMat3(Mat3Operators.forAdditionWithMat3()));
  }

  public static Mat3Exp sub(Mat3 left, float right) {
    return sub(mat(left), constant(right));
  }
  public static Mat3Exp sub(Mat3Exp left, float right) {
    return sub(left, constant(right));
  }
  public static Mat3Exp sub(Mat3 left, FloatExp right) {
    return sub(mat(left), right);
  }
  public static Mat3Exp sub(Mat3Exp left, FloatExp right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(left, right),
        Mat3Evaluators.forOperationWithFloat(Mat3Operators.forSubtractionWithFloat()));
  }
  public static Mat3Exp sub(Mat3 left, Mat3 right) {
    return sub(mat(left), mat(right));
  }
  public static Mat3Exp sub(Mat3Exp left, Mat3 right) {
    return sub(left, mat(right));
  }
  public static Mat3Exp sub(Mat3 left, Mat3Exp right) {
    return sub(mat(left), right);
  }
  public static Mat3Exp sub(Mat3Exp left, Mat3Exp right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(left, right),
        Mat3Evaluators.forOperationWithMat3(Mat3Operators.forSubtractionWithMat3()));
  }

  public static Mat3Exp mul(Mat3 left, float right) {
    return mul(mat(left), constant(right));
  }
  public static Mat3Exp mul(Mat3Exp left, float right) {
    return mul(left, constant(right));
  }
  public static Mat3Exp mul(Mat3 left, FloatExp right) {
    return mul(mat(left), right);
  }
  public static Mat3Exp mul(Mat3Exp left, FloatExp right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(left, right),
        Mat3Evaluators.forOperationWithFloat(Mat3Operators.forMultiplicationWithFloat()));
  }
  public static Mat3Exp mul(Mat3 left, Mat3 right) {
    return mul(mat(left), mat(right));
  }
  public static Mat3Exp mul(Mat3Exp left, Mat3 right) {
    return mul(left, mat(right));
  }
  public static Mat3Exp mul(Mat3 left, Mat3Exp right) {
    return mul(mat(left), right);
  }
  public static Mat3Exp mul(Mat3Exp left, Mat3Exp right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(left, right),
        Mat3Evaluators.forOperationWithMat3(Mat3Operators.forMultiplicationWithMat3()));
  }

  public static Mat3Exp div(Mat3 left, float right) {
    return div(mat(left), constant(right));
  }
  public static Mat3Exp div(Mat3Exp left, float right) {
    return div(left, constant(right));
  }
  public static Mat3Exp div(Mat3 left, FloatExp right) {
    return div(mat(left), right);
  }
  public static Mat3Exp div(Mat3Exp left, FloatExp right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(left, right),
        Mat3Evaluators.forOperationWithFloat(Mat3Operators.forDivisionWithFloat()));
  }
  public static Mat3Exp div(Mat3 left, Mat3 right) {
    return div(mat(left), mat(right));
  }
  public static Mat3Exp div(Mat3Exp left, Mat3 right) {
    return div(left, mat(right));
  }
  public static Mat3Exp div(Mat3 left, Mat3Exp right) {
    return div(mat(left), right);
  }
  public static Mat3Exp div(Mat3Exp left, Mat3Exp right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(left, right),
        Mat3Evaluators.forOperationWithMat3(Mat3Operators.forDivisionWithMat3()));
  }

  // ===================================================================================================================
  // Stuff for Mat4
  // ===================================================================================================================

  public static Mat4Exp mat(Mat4 val) {
    return new Mat4Exp(val);
  }
  public static Mat4Exp mat(Vec4 x, Vec4 y, Vec4 z, Vec4 w) {
    return mat(new Vec4Exp(x), new Vec4Exp(y), new Vec4Exp(z), new Vec4Exp(w));
  }
  public static Mat4Exp mat(Vec4Exp x, Vec4 y, Vec4 z, Vec4 w) {
    return mat(x, new Vec4Exp(y), new Vec4Exp(z), new Vec4Exp(w));
  }
  public static Mat4Exp mat(Vec4 x, Vec4Exp y, Vec4 z, Vec4 w) {
    return mat(new Vec4Exp(x), y, new Vec4Exp(z), new Vec4Exp(w));
  }
  public static Mat4Exp mat(Vec4Exp x, Vec4Exp y, Vec4 z, Vec4 w) {
    return mat(x, y, new Vec4Exp(z), new Vec4Exp(w));
  }
  public static Mat4Exp mat(Vec4 x, Vec4 y, Vec4Exp z, Vec4 w) {
    return mat(new Vec4Exp(x), new Vec4Exp(y), z, new Vec4Exp(w));
  }
  public static Mat4Exp mat(Vec4Exp x, Vec4 y, Vec4Exp z, Vec4 w) {
    return mat(x, new Vec4Exp(y), z, new Vec4Exp(w));
  }
  public static Mat4Exp mat(Vec4 x, Vec4Exp y, Vec4Exp z, Vec4 w) {
    return mat(new Vec4Exp(x), y, z, new Vec4Exp(w));
  }
  public static Mat4Exp mat(Vec4 x, Vec4 y, Vec4 z, Vec4Exp w) {
    return mat(new Vec4Exp(x), new Vec4Exp(y), new Vec4Exp(z), w);
  }
  public static Mat4Exp mat(Vec4Exp x, Vec4 y, Vec4 z, Vec4Exp w) {
    return mat(x, new Vec4Exp(y), new Vec4Exp(z), w);
  }
  public static Mat4Exp mat(Vec4 x, Vec4Exp y, Vec4 z, Vec4Exp w) {
    return mat(new Vec4Exp(x), y, new Vec4Exp(z), w);
  }
  public static Mat4Exp mat(Vec4Exp x, Vec4Exp y, Vec4 z, Vec4Exp w) {
    return mat(x, y, new Vec4Exp(z), w);
  }
  public static Mat4Exp mat(Vec4 x, Vec4 y, Vec4Exp z, Vec4Exp w) {
    return mat(new Vec4Exp(x), new Vec4Exp(y), z, w);
  }
  public static Mat4Exp mat(Vec4Exp x, Vec4 y, Vec4Exp z, Vec4Exp w) {
    return mat(x, new Vec4Exp(y), z, w);
  }
  public static Mat4Exp mat(Vec4 x, Vec4Exp y, Vec4Exp z, Vec4Exp w) {
    return mat(new Vec4Exp(x), y, z, w);
  }
  public static Mat4Exp mat(Vec4Exp x, Vec4Exp y, Vec4Exp z, Vec4 w) {
    return mat(x, y, z, new Vec4Exp(w));
  }
  public static Mat4Exp mat(Vec4Exp x, Vec4Exp y, Vec4Exp z, Vec4Exp w) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(x, y, z, w),
        Mat4Evaluators.forComponents());
  }

  public static Mat4Exp add(Mat4 left, float right) {
    return add(mat(left), constant(right));
  }
  public static Mat4Exp add(Mat4Exp left, float right) {
    return add(left, constant(right));
  }
  public static Mat4Exp add(Mat4 left, FloatExp right) {
    return add(mat(left), right);
  }
  public static Mat4Exp add(Mat4Exp left, FloatExp right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(left, right),
        Mat4Evaluators.forOperationWithFloat(Mat4Operators.forAdditionWithFloat()));
  }
  public static Mat4Exp add(Mat4 left, Mat4 right) {
    return add(mat(left), mat(right));
  }
  public static Mat4Exp add(Mat4Exp left, Mat4 right) {
    return add(left, mat(right));
  }
  public static Mat4Exp add(Mat4 left, Mat4Exp right) {
    return add(mat(left), right);
  }
  public static Mat4Exp add(Mat4Exp left, Mat4Exp right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(left, right),
        Mat4Evaluators.forOperationWithMat4(Mat4Operators.forAdditionWithMat4()));
  }

  public static Mat4Exp sub(Mat4 left, float right) {
    return sub(mat(left), constant(right));
  }
  public static Mat4Exp sub(Mat4Exp left, float right) {
    return sub(left, constant(right));
  }
  public static Mat4Exp sub(Mat4 left, FloatExp right) {
    return sub(mat(left), right);
  }
  public static Mat4Exp sub(Mat4Exp left, FloatExp right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(left, right),
        Mat4Evaluators.forOperationWithFloat(Mat4Operators.forSubtractionWithFloat()));
  }
  public static Mat4Exp sub(Mat4 left, Mat4 right) {
    return sub(mat(left), mat(right));
  }
  public static Mat4Exp sub(Mat4Exp left, Mat4 right) {
    return sub(left, mat(right));
  }
  public static Mat4Exp sub(Mat4 left, Mat4Exp right) {
    return sub(mat(left), right);
  }
  public static Mat4Exp sub(Mat4Exp left, Mat4Exp right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(left, right),
        Mat4Evaluators.forOperationWithMat4(Mat4Operators.forSubtractionWithMat4()));
  }

  public static Mat4Exp mul(Mat4 left, float right) {
    return mul(mat(left), constant(right));
  }
  public static Mat4Exp mul(Mat4Exp left, float right) {
    return mul(left, constant(right));
  }
  public static Mat4Exp mul(Mat4 left, FloatExp right) {
    return mul(mat(left), right);
  }
  public static Mat4Exp mul(Mat4Exp left, FloatExp right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(left, right),
        Mat4Evaluators.forOperationWithFloat(Mat4Operators.forMultiplicationWithFloat()));
  }
  public static Mat4Exp mul(Mat4 left, Mat4 right) {
    return mul(mat(left), mat(right));
  }
  public static Mat4Exp mul(Mat4Exp left, Mat4 right) {
    return mul(left, mat(right));
  }
  public static Mat4Exp mul(Mat4 left, Mat4Exp right) {
    return mul(mat(left), right);
  }
  public static Mat4Exp mul(Mat4Exp left, Mat4Exp right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(left, right),
        Mat4Evaluators.forOperationWithMat4(Mat4Operators.forMultiplicationWithMat4()));
  }

  public static Mat4Exp div(Mat4 left, float right) {
    return div(mat(left), constant(right));
  }
  public static Mat4Exp div(Mat4Exp left, float right) {
    return div(left, constant(right));
  }
  public static Mat4Exp div(Mat4 left, FloatExp right) {
    return div(mat(left), right);
  }
  public static Mat4Exp div(Mat4Exp left, FloatExp right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(left, right),
        Mat4Evaluators.forOperationWithFloat(Mat4Operators.forDivisionWithFloat()));
  }
  public static Mat4Exp div(Mat4 left, Mat4 right) {
    return div(mat(left), mat(right));
  }
  public static Mat4Exp div(Mat4Exp left, Mat4 right) {
    return div(left, mat(right));
  }
  public static Mat4Exp div(Mat4 left, Mat4Exp right) {
    return div(mat(left), right);
  }
  public static Mat4Exp div(Mat4Exp left, Mat4Exp right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(left, right),
        Mat4Evaluators.forOperationWithMat4(Mat4Operators.forDivisionWithMat4()));
  }
}
