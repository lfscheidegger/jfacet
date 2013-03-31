package com.lfscheidegger.jfacet.shade;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.evaluators.*;
import com.lfscheidegger.jfacet.shade.expression.operators.*;
import com.lfscheidegger.jfacet.shade.expression.primitives.*;
import com.lfscheidegger.jfacet.shade.primitives.*;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.*;

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

  public static FloatExp neg(FloatExp exp) {
    return new FloatExp(ImmutableList.<Expression>of(exp), FloatEvaluators.forNegation());
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
    return new Vec2Exp(ImmutableList.<Expression>of(x, y), Vec2Evaluators.forComponents());
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
  public static Vec2Exp add(Vec2Like left, Vec2Like right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
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
  public static Vec2Exp sub(Vec2Like left, Vec2Like right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
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
  public static Vec2Exp mul(Vec2Like left, Vec2Like right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
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
  public static Vec2Exp div(Vec2Like left, Vec2Like right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Vec2Evaluators.forOperationWithVec2(Vec2Operators.forDivisionWithVec2()));
  }

  public static Vec2Exp neg(Vec2Like exp) {
    return new Vec2Exp(ImmutableList.<Expression>of(promote(exp)), Vec2Evaluators.forNegation());
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
    return new Vec3Exp(ImmutableList.<Expression>of(x, y, z), Vec3Evaluators.forComponents());
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
  public static Vec3Exp add(Vec3Like left, Vec3Like right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Vec3Evaluators.forOperationWithVec3(Vec3Operators.forAdditionWithVec3()));
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
  public static Vec3Exp sub(Vec3Like left, Vec3Like right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Vec3Evaluators.forOperationWithVec3(Vec3Operators.forSubtractionWithVec3()));
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
  public static Vec3Exp mul(Vec3Like left, Vec3Like right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Vec3Evaluators.forOperationWithVec3(Vec3Operators.forMultiplicationWithVec3()));
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
  public static Vec3Exp div(Vec3Like left, Vec3Like right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Vec3Evaluators.forOperationWithVec3(Vec3Operators.forDivisionWithVec3()));
  }

  public static Vec3Exp neg(Vec3Like exp) {
    return new Vec3Exp(ImmutableList.<Expression>of(promote(exp)), Vec3Evaluators.forNegation());
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
    return new Vec4Exp(ImmutableList.<Expression>of(x, y, z, w), Vec4Evaluators.forComponents());
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
  public static Vec4Exp add(Vec4Like left, Vec4Like right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Vec4Evaluators.forOperationWithVec4(Vec4Operators.forAdditionWithVec4()));
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
  public static Vec4Exp sub(Vec4Like left, Vec4Like right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Vec4Evaluators.forOperationWithVec4(Vec4Operators.forSubtractionWithVec4()));
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
  public static Vec4Exp mul(Vec4Like left, Vec4Like right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Vec4Evaluators.forOperationWithVec4(Vec4Operators.forMultiplicationWithVec4()));
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
  public static Vec4Exp div(Vec4Like left, Vec4Like right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Vec4Evaluators.forOperationWithVec4(Vec4Operators.forDivisionWithVec4()));
  }

  public static Vec4Exp neg(Vec4Like exp) {
    return new Vec4Exp(ImmutableList.<Expression>of(promote(exp)), Vec4Evaluators.forNegation());
  }

  // ===================================================================================================================
  // Stuff for Mat2
  // ===================================================================================================================
  public static Mat2Exp mat(Mat2 val) {
    return new Mat2Exp(val);
  }
  public static Mat2Exp mat(Vec2Like x, Vec2Like y) {
    return new Mat2Exp(ImmutableList.<Expression>of(promote(x), promote(y)), Mat2Evaluators.forComponents());
  }

  public static Mat2Exp add(Mat2Like left, float right) {
    return add(left, constant(right));
  }
  public static Mat2Exp add(Mat2Like left, FloatExp right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(left), right),
        Mat2Evaluators.forOperationWithFloat(Mat2Operators.forAdditionWithFloat()));
  }
  public static Mat2Exp add(Mat2Like left, Mat2Like right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Mat2Evaluators.forOperationWithMat2(Mat2Operators.forAdditionWithMat2()));
  }

  public static Mat2Exp sub(Mat2Like left, float right) {
    return sub(left, constant(right));
  }
  public static Mat2Exp sub(Mat2Like left, FloatExp right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(left), right),
        Mat2Evaluators.forOperationWithFloat(Mat2Operators.forSubtractionWithFloat()));
  }
  public static Mat2Exp sub(Mat2Like left, Mat2Like right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Mat2Evaluators.forOperationWithMat2(Mat2Operators.forSubtractionWithMat2()));
  }

  public static Mat2Exp mul(Mat2Like left, float right) {
    return mul(left, constant(right));
  }
  public static Mat2Exp mul(Mat2Like left, FloatExp right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(left), right),
        Mat2Evaluators.forOperationWithFloat(Mat2Operators.forMultiplicationWithFloat()));
  }
  public static Mat2Exp mul(Mat2Like left, Mat2Like right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Mat2Evaluators.forOperationWithMat2(Mat2Operators.forMultiplicationWithMat2()));
  }

  public static Mat2Exp div(Mat2Like left, float right) {
    return div(left, constant(right));
  }
  public static Mat2Exp div(Mat2Like left, FloatExp right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(left), right),
        Mat2Evaluators.forOperationWithFloat(Mat2Operators.forDivisionWithFloat()));
  }
  public static Mat2Exp div(Mat2Like left, Mat2Like right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Mat2Evaluators.forOperationWithMat2(Mat2Operators.forDivisionWithMat2()));
  }

  public static Mat2Exp neg(Mat2Like exp) {
    return new Mat2Exp(ImmutableList.<Expression>of(promote(exp)), Mat2Evaluators.forNegation());
  }

  public static Vec2Exp mul(Mat2Like left, Vec2Like right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Mat2Evaluators.forOperationWithVec2(Mat2Operators.forMultiplicationWithVec2()));
  }

  // ===================================================================================================================
  // Stuff for Mat3
  // ===================================================================================================================
  public static Mat3Exp mat(Mat3 val) {
    return new Mat3Exp(val);
  }
  public static Mat3Exp mat(Vec3Like x, Vec3Like y, Vec3Like z) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(x), promote(y), promote(z)),
        Mat3Evaluators.forComponents());
  }

  public static Mat3Exp add(Mat3Like left, float right) {
    return add(left, constant(right));
  }
  public static Mat3Exp add(Mat3Like left, FloatExp right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(left), right),
        Mat3Evaluators.forOperationWithFloat(Mat3Operators.forAdditionWithFloat()));
  }
  public static Mat3Exp add(Mat3Like left, Mat3Like right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Mat3Evaluators.forOperationWithMat3(Mat3Operators.forAdditionWithMat3()));
  }

  public static Mat3Exp sub(Mat3Like left, float right) {
    return sub(left, constant(right));
  }
  public static Mat3Exp sub(Mat3Like left, FloatExp right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(left), right),
        Mat3Evaluators.forOperationWithFloat(Mat3Operators.forSubtractionWithFloat()));
  }
  public static Mat3Exp sub(Mat3Like left, Mat3Like right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Mat3Evaluators.forOperationWithMat3(Mat3Operators.forSubtractionWithMat3()));
  }

  public static Mat3Exp mul(Mat3Like left, float right) {
    return mul(left, constant(right));
  }
  public static Mat3Exp mul(Mat3Like left, FloatExp right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(left), right),
        Mat3Evaluators.forOperationWithFloat(Mat3Operators.forMultiplicationWithFloat()));
  }
  public static Mat3Exp mul(Mat3Like left, Mat3Like right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Mat3Evaluators.forOperationWithMat3(Mat3Operators.forMultiplicationWithMat3()));
  }

  public static Mat3Exp div(Mat3Like left, float right) {
    return div(left, constant(right));
  }
  public static Mat3Exp div(Mat3Like left, FloatExp right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(left), right),
        Mat3Evaluators.forOperationWithFloat(Mat3Operators.forDivisionWithFloat()));
  }
  public static Mat3Exp div(Mat3Like left, Mat3Like right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Mat3Evaluators.forOperationWithMat3(Mat3Operators.forDivisionWithMat3()));
  }

  public static Mat3Exp neg(Mat3Like exp) {
    return new Mat3Exp(ImmutableList.<Expression>of(promote(exp)), Mat3Evaluators.forNegation());
  }

  public static Vec3Exp mul(Mat3Like left, Vec3Like right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Mat3Evaluators.forOperationWithVec3(Mat3Operators.forMultiplicationWithVec3()));
  }

  // ===================================================================================================================
  // Stuff for Mat4
  // ===================================================================================================================
  public static Mat4Exp mat(Mat4 val) {
    return new Mat4Exp(val);
  }
  public static Mat4Exp mat(Vec4Like x, Vec4Like y, Vec4Like z, Vec4Like w) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(x), promote(y), promote(z), promote(w)),
        Mat4Evaluators.forComponents());
  }

  public static Mat4Exp add(Mat4Like left, float right) {
    return add(left, constant(right));
  }
  public static Mat4Exp add(Mat4Like left, FloatExp right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(left), right),
        Mat4Evaluators.forOperationWithFloat(Mat4Operators.forAdditionWithFloat()));
  }
  public static Mat4Exp add(Mat4Like left, Mat4Like right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Mat4Evaluators.forOperationWithMat4(Mat4Operators.forAdditionWithMat4()));
  }

  public static Mat4Exp sub(Mat4Like left, float right) {
    return sub(left, constant(right));
  }
  public static Mat4Exp sub(Mat4Like left, FloatExp right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(left), right),
        Mat4Evaluators.forOperationWithFloat(Mat4Operators.forSubtractionWithFloat()));
  }
  public static Mat4Exp sub(Mat4Like left, Mat4Like right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Mat4Evaluators.forOperationWithMat4(Mat4Operators.forSubtractionWithMat4()));
  }

  public static Mat4Exp mul(Mat4Like left, float right) {
    return mul(left, constant(right));
  }
  public static Mat4Exp mul(Mat4Like left, FloatExp right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(left), right),
        Mat4Evaluators.forOperationWithFloat(Mat4Operators.forMultiplicationWithFloat()));
  }
  public static Mat4Exp mul(Mat4Like left, Mat4Like right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Mat4Evaluators.forOperationWithMat4(Mat4Operators.forMultiplicationWithMat4()));
  }

  public static Mat4Exp div(Mat4Like left, float right) {
    return div(left, constant(right));
  }
  public static Mat4Exp div(Mat4Like left, FloatExp right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(left), right),
        Mat4Evaluators.forOperationWithFloat(Mat4Operators.forDivisionWithFloat()));
  }
  public static Mat4Exp div(Mat4Like left, Mat4Like right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Mat4Evaluators.forOperationWithMat4(Mat4Operators.forDivisionWithMat4()));
  }

  // ===================================================================================================================
  // Type promotion
  // ===================================================================================================================
  private static Vec2Exp promote(Vec2Like param) {
    return (param instanceof Vec2Exp) ? (Vec2Exp)param : new Vec2Exp((Vec2)param);
  }
  private static Vec3Exp promote(Vec3Like param) {
    return (param instanceof Vec3Exp) ? (Vec3Exp)param : new Vec3Exp((Vec3)param);
  }
  private static Vec4Exp promote(Vec4Like param) {
    return (param instanceof Vec4Exp) ? (Vec4Exp)param : new Vec4Exp((Vec4)param);
  }
  private static Mat2Exp promote(Mat2Like param) {
    return (param instanceof Mat2Exp) ? (Mat2Exp)param : new Mat2Exp((Mat2)param);
  }
  private static Mat3Exp promote(Mat3Like param) {
    return (param instanceof Mat3Exp) ? (Mat3Exp)param : new Mat3Exp((Mat3)param);
  }
  private static Mat4Exp promote(Mat4Like param) {
    return (param instanceof Mat4Exp) ? (Mat4Exp)param : new Mat4Exp((Mat4)param);
  }

  public static Mat4Exp neg(Mat4Like exp) {
    return new Mat4Exp(ImmutableList.<Expression>of(promote(exp)), Mat4Evaluators.forNegation());
  }

  public static Vec4Exp mul(Mat4Like left, Vec4Like right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        Mat4Evaluators.forOperationWithVec4(Mat4Operators.forMultiplicationWithVec4()));
  }

  // ===================================================================================================================
  // GLSL stuff
  // ===================================================================================================================
  public static FloatExp attributef() {
    return new FloatExp(
        Type.FLOAT_T,
        GlSlType.ATTRIBUTE_T,
        ImmutableList.<Expression>of(),
        GlSlEvaluators.<Float>forAttribute());
  }

  public static Vec2Exp attribute2f() {
    return new Vec2Exp(
        Type.VEC2_T,
        GlSlType.ATTRIBUTE_T,
        ImmutableList.<Expression>of(),
        GlSlEvaluators.<Vec2>forAttribute());
  }

  public static Vec3Exp attribute3f() {
    return new Vec3Exp(
        Type.VEC3_T,
        GlSlType.ATTRIBUTE_T,
        ImmutableList.<Expression>of(),
        GlSlEvaluators.<Vec3>forAttribute());
  }

  public static Vec4Exp attribute4f() {
    return new Vec4Exp(
        Type.VEC4_T,
        GlSlType.ATTRIBUTE_T,
        ImmutableList.<Expression>of(),
        GlSlEvaluators.<Vec4>forAttribute());
  }
}
