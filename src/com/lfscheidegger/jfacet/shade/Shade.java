package com.lfscheidegger.jfacet.shade;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.evaluators.*;
import com.lfscheidegger.jfacet.shade.expression.evaluators.glsl.QualifiedGlSlEvaluator;
import com.lfscheidegger.jfacet.shade.expression.operators.*;
import com.lfscheidegger.jfacet.shade.primitives.*;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.*;
import com.lfscheidegger.jfacet.shade.transform.Rotation;
import com.lfscheidegger.jfacet.shade.transform.Transform;
import com.lfscheidegger.jfacet.shade.transform.Translation;

/**
 * Convenience methods to fill primitive types into expressions
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
        new BinaryOperationEvaluator<Float, Float, Float>(Type.FLOAT_T, FloatOperators.forAddition()));
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
        new BinaryOperationEvaluator<Float, Float, Float>(Type.FLOAT_T, FloatOperators.forSubtraction()));
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
        new BinaryOperationEvaluator<Float, Float, Float>(Type.FLOAT_T, FloatOperators.forMultiplication()));
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
        new BinaryOperationEvaluator<Float, Float, Float>(Type.FLOAT_T, FloatOperators.forDivision()));
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
    return new FloatExp(ImmutableList.<Expression>of(exp), new NegationEvaluator<Float>());
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
    return new Vec2Exp(ImmutableList.<Expression>of(x, y), new ConstructorEvaluator<Vec2>(Type.VEC2_T));
  }

  public static Vec2Exp add(Vec2Like left, float right) {
    return add(left, constant(right));
  }
  public static Vec2Exp add(Vec2Like left, FloatExp right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Vec2, Float, Vec2>(Type.VEC2_T, BasicArithmeticOperators.<Vec2>forAdditionWithFloat()));
  }
  public static Vec2Exp add(Vec2Like left, Vec2Like right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Vec2, Vec2, Vec2>(Type.VEC2_T, BasicArithmeticOperators.<Vec2>forAdditionWithSame()));
  }

  public static Vec2Exp sub(Vec2Like left, float right) {
    return sub(left, constant(right));
  }
  public static Vec2Exp sub(Vec2Like left, FloatExp right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Vec2, Float, Vec2>(Type.VEC2_T, BasicArithmeticOperators.<Vec2>forSubtractionWithFloat()));
  }
  public static Vec2Exp sub(Vec2Like left, Vec2Like right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Vec2, Vec2, Vec2>(Type.VEC2_T, BasicArithmeticOperators.<Vec2>forSubtractionWithSame()));
  }

  public static Vec2Exp mul(Vec2Like left, float right) {
    return mul(left, constant(right));
  }
  public static Vec2Exp mul(Vec2Like left, FloatExp right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Vec2, Float, Vec2>(Type.VEC2_T, BasicArithmeticOperators.<Vec2>forMultiplicationWithFloat()));
  }
  public static Vec2Exp mul(Vec2Like left, Vec2Like right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Vec2, Vec2, Vec2>(Type.VEC2_T, BasicArithmeticOperators.<Vec2>forMultiplicationWithSame()));
  }

  public static Vec2Exp div(Vec2Like left, float right) {
    return div(left, constant(right));
  }
  public static Vec2Exp div(Vec2Like left, FloatExp right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Vec2, Float, Vec2>(Type.VEC2_T, BasicArithmeticOperators.<Vec2>forDivisionWithFloat()));
  }
  public static Vec2Exp div(Vec2Like left, Vec2Like right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Vec2, Vec2, Vec2>(Type.VEC2_T, BasicArithmeticOperators.<Vec2>forDivisionWithSame()));
  }

  public static Vec2Exp neg(Vec2Like exp) {
    return new Vec2Exp(ImmutableList.<Expression>of(promote(exp)), new NegationEvaluator<Vec2>());
  }

  public static FloatExp dot(Vec2Like left, Vec2Like right) {
    return Math.dot(promote(left), promote(right));
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
    return new Vec3Exp(ImmutableList.<Expression>of(x, y, z), new ConstructorEvaluator<Vec3>(Type.VEC3_T));
  }

  public static Vec3Exp add(Vec3Like left, float right) {
    return add(left, constant(right));
  }
  public static Vec3Exp add(Vec3Like left, FloatExp right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Vec3, Float, Vec3>(Type.VEC3_T, BasicArithmeticOperators.<Vec3>forAdditionWithFloat()));
  }
  public static Vec3Exp add(Vec3Like left, Vec3Like right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Vec3, Vec3, Vec3>(Type.VEC3_T, BasicArithmeticOperators.<Vec3>forAdditionWithSame()));
  }

  public static Vec3Exp sub(Vec3Like left, float right) {
    return sub(left, constant(right));
  }
  public static Vec3Exp sub(Vec3Like left, FloatExp right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Vec3, Float, Vec3>(Type.VEC3_T, BasicArithmeticOperators.<Vec3>forSubtractionWithFloat()));
  }
  public static Vec3Exp sub(Vec3Like left, Vec3Like right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Vec3, Vec3, Vec3>(Type.VEC3_T, BasicArithmeticOperators.<Vec3>forSubtractionWithSame()));
  }

  public static Vec3Exp mul(Vec3Like left, float right) {
    return mul(left, constant(right));
  }
  public static Vec3Exp mul(Vec3Like left, FloatExp right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Vec3, Float, Vec3>(Type.VEC3_T, BasicArithmeticOperators.<Vec3>forMultiplicationWithFloat()));
  }
  public static Vec3Exp mul(Vec3Like left, Vec3Like right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Vec3, Vec3, Vec3>(Type.VEC3_T, BasicArithmeticOperators.<Vec3>forMultiplicationWithSame()));
  }

  public static Vec3Exp div(Vec3Like left, float right) {
    return div(left, constant(right));
  }
  public static Vec3Exp div(Vec3Like left, FloatExp right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Vec3, Float, Vec3>(Type.VEC3_T, BasicArithmeticOperators.<Vec3>forDivisionWithFloat()));
  }
  public static Vec3Exp div(Vec3Like left, Vec3Like right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Vec3, Vec3, Vec3>(Type.VEC3_T, BasicArithmeticOperators.<Vec3>forDivisionWithSame()));
  }

  public static Vec3Exp neg(Vec3Like exp) {
    return new Vec3Exp(ImmutableList.<Expression>of(promote(exp)), new NegationEvaluator<Vec3>());
  }

  public static FloatExp dot(Vec3Like left, Vec3Like right) {
    return Math.dot(promote(left), promote(right));
  }

  public static Vec3Exp cross(Vec3Like left, Vec3Like right) {
    return Math.cross(promote(left), promote(right));
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
    return new Vec4Exp(ImmutableList.<Expression>of(x, y, z, w), new ConstructorEvaluator<Vec4>(Type.VEC4_T));
  }

  public static Vec4Exp add(Vec4Like left, float right) {
    return add(left, constant(right));
  }
  public static Vec4Exp add(Vec4Like left, FloatExp right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Vec4, Float, Vec4>(Type.VEC4_T, BasicArithmeticOperators.<Vec4>forAdditionWithFloat()));
  }
  public static Vec4Exp add(Vec4Like left, Vec4Like right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Vec4, Vec4, Vec4>(Type.VEC4_T, BasicArithmeticOperators.<Vec4>forAdditionWithSame()));
  }

  public static Vec4Exp sub(Vec4Like left, float right) {
    return sub(left, constant(right));
  }
  public static Vec4Exp sub(Vec4Like left, FloatExp right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Vec4, Float, Vec4>(Type.VEC4_T, BasicArithmeticOperators.<Vec4>forSubtractionWithFloat()));
  }
  public static Vec4Exp sub(Vec4Like left, Vec4Like right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Vec4, Vec4, Vec4>(Type.VEC4_T, BasicArithmeticOperators.<Vec4>forSubtractionWithSame()));
  }

  public static Vec4Exp mul(Vec4Like left, float right) {
    return mul(left, constant(right));
  }
  public static Vec4Exp mul(Vec4Like left, FloatExp right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Vec4, Float, Vec4>(Type.VEC4_T, BasicArithmeticOperators.<Vec4>forMultiplicationWithFloat()));
  }
  public static Vec4Exp mul(Vec4Like left, Vec4Like right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Vec4, Vec4, Vec4>(Type.VEC4_T, BasicArithmeticOperators.<Vec4>forMultiplicationWithSame()));
  }

  public static Vec4Exp div(Vec4Like left, float right) {
    return div(left, constant(right));
  }
  public static Vec4Exp div(Vec4Like left, FloatExp right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Vec4, Float, Vec4>(Type.VEC4_T, BasicArithmeticOperators.<Vec4>forDivisionWithFloat()));
  }
  public static Vec4Exp div(Vec4Like left, Vec4Like right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Vec4, Vec4, Vec4>(Type.VEC4_T, BasicArithmeticOperators.<Vec4>forDivisionWithSame()));
  }

  public static Vec4Exp neg(Vec4Like exp) {
    return new Vec4Exp(ImmutableList.<Expression>of(promote(exp)), new NegationEvaluator<Vec4>());
  }

  public static FloatExp dot(Vec4Like left, Vec4Like right) {
    return Math.dot(promote(left), promote(right));
  }

  // ===================================================================================================================
  // Stuff for Mat2
  // ===================================================================================================================
  public static Mat2Exp mat(Mat2 val) {
    return new Mat2Exp(val);
  }
  public static Mat2Exp mat(Vec2Like x, Vec2Like y) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(x), promote(y)), new ConstructorEvaluator<Mat2>(Type.MAT2_T));
  }

  public static Mat2Exp add(Mat2Like left, float right) {
    return add(left, constant(right));
  }
  public static Mat2Exp add(Mat2Like left, FloatExp right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Mat2, Float, Mat2>(Type.MAT2_T, BasicArithmeticOperators.<Mat2>forAdditionWithFloat()));
  }
  public static Mat2Exp add(Mat2Like left, Mat2Like right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Mat2, Mat2, Mat2>(Type.MAT2_T, BasicArithmeticOperators.<Mat2>forAdditionWithSame()));
  }

  public static Mat2Exp sub(Mat2Like left, float right) {
    return sub(left, constant(right));
  }
  public static Mat2Exp sub(Mat2Like left, FloatExp right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Mat2, Float, Mat2>(Type.MAT2_T, BasicArithmeticOperators.<Mat2>forSubtractionWithFloat()));
  }
  public static Mat2Exp sub(Mat2Like left, Mat2Like right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Mat2, Mat2, Mat2>(Type.MAT2_T, BasicArithmeticOperators.<Mat2>forSubtractionWithSame()));
  }

  public static Mat2Exp mul(Mat2Like left, float right) {
    return mul(left, constant(right));
  }
  public static Mat2Exp mul(Mat2Like left, FloatExp right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Mat2, Float, Mat2>(Type.MAT2_T, BasicArithmeticOperators.<Mat2>forMultiplicationWithFloat()));
  }
  public static Mat2Exp mul(Mat2Like left, Mat2Like right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Mat2, Mat2, Mat2>(Type.MAT2_T, BasicArithmeticOperators.<Mat2>forMultiplicationWithSame()));
  }

  public static Mat2Exp div(Mat2Like left, float right) {
    return div(left, constant(right));
  }
  public static Mat2Exp div(Mat2Like left, FloatExp right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Mat2, Float, Mat2>(Type.MAT2_T, BasicArithmeticOperators.<Mat2>forDivisionWithFloat()));
  }
  public static Mat2Exp div(Mat2Like left, Mat2Like right) {
    return new Mat2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Mat2, Mat2, Mat2>(Type.MAT2_T, BasicArithmeticOperators.<Mat2>forDivisionWithSame()));
  }

  public static Mat2Exp neg(Mat2Like exp) {
    return new Mat2Exp(ImmutableList.<Expression>of(promote(exp)), new NegationEvaluator<Mat2>());
  }

  public static Vec2Exp mul(Mat2Like left, Vec2Like right) {
    return new Vec2Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Mat2, Vec2, Vec2>(Type.VEC2_T, Mat2Operators.forMultiplicationWithVec2()));
  }

  // ===================================================================================================================
  // Stuff for Mat3
  // ===================================================================================================================
  public static Mat3Exp mat(Mat3 val) {
    return new Mat3Exp(val);
  }
  public static Mat3Exp mat(Vec3Like x, Vec3Like y, Vec3Like z) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(x), promote(y), promote(z)), new ConstructorEvaluator<Mat3>(Type.MAT3_T));
  }

  public static Mat3Exp add(Mat3Like left, float right) {
    return add(left, constant(right));
  }
  public static Mat3Exp add(Mat3Like left, FloatExp right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Mat3, Float, Mat3>(Type.MAT3_T, BasicArithmeticOperators.<Mat3>forAdditionWithFloat()));
  }
  public static Mat3Exp add(Mat3Like left, Mat3Like right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Mat3, Mat3, Mat3>(Type.MAT3_T, BasicArithmeticOperators.<Mat3>forAdditionWithSame()));
  }

  public static Mat3Exp sub(Mat3Like left, float right) {
    return sub(left, constant(right));
  }
  public static Mat3Exp sub(Mat3Like left, FloatExp right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Mat3, Float, Mat3>(Type.MAT3_T, BasicArithmeticOperators.<Mat3>forSubtractionWithFloat()));
  }
  public static Mat3Exp sub(Mat3Like left, Mat3Like right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Mat3, Mat3, Mat3>(Type.MAT3_T, BasicArithmeticOperators.<Mat3>forSubtractionWithSame()));
  }

  public static Mat3Exp mul(Mat3Like left, float right) {
    return mul(left, constant(right));
  }
  public static Mat3Exp mul(Mat3Like left, FloatExp right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Mat3, Float, Mat3>(Type.MAT3_T, BasicArithmeticOperators.<Mat3>forMultiplicationWithFloat()));
  }
  public static Mat3Exp mul(Mat3Like left, Mat3Like right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Mat3, Mat3, Mat3>(Type.MAT3_T, BasicArithmeticOperators.<Mat3>forMultiplicationWithSame()));
  }

  public static Mat3Exp div(Mat3Like left, float right) {
    return div(left, constant(right));
  }
  public static Mat3Exp div(Mat3Like left, FloatExp right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Mat3, Float, Mat3>(Type.MAT3_T, BasicArithmeticOperators.<Mat3>forDivisionWithFloat()));
  }
  public static Mat3Exp div(Mat3Like left, Mat3Like right) {
    return new Mat3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Mat3, Mat3, Mat3>(Type.MAT3_T, BasicArithmeticOperators.<Mat3>forDivisionWithSame()));
  }

  public static Mat3Exp neg(Mat3Like exp) {
    return new Mat3Exp(ImmutableList.<Expression>of(promote(exp)), new NegationEvaluator<Mat3>());
  }

  public static Vec3Exp mul(Mat3Like left, Vec3Like right) {
    return new Vec3Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Mat3, Vec3, Vec3>(Type.VEC3_T, Mat3Operators.forMultiplicationWithVec3()));
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
        new ConstructorEvaluator<Mat4>(Type.MAT4_T));
  }

  public static Mat4Exp add(Mat4Like left, float right) {
    return add(left, constant(right));
  }
  public static Mat4Exp add(Mat4Like left, FloatExp right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Mat4, Float, Mat4>(Type.MAT4_T, BasicArithmeticOperators.<Mat4>forAdditionWithFloat()));
  }
  public static Mat4Exp add(Mat4Like left, Mat4Like right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Mat4, Mat4, Mat4>(Type.MAT4_T, BasicArithmeticOperators.<Mat4>forAdditionWithSame()));
  }

  public static Mat4Exp sub(Mat4Like left, float right) {
    return sub(left, constant(right));
  }
  public static Mat4Exp sub(Mat4Like left, FloatExp right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Mat4, Float, Mat4>(Type.MAT4_T, BasicArithmeticOperators.<Mat4>forSubtractionWithFloat()));
  }
  public static Mat4Exp sub(Mat4Like left, Mat4Like right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Mat4, Mat4, Mat4>(Type.MAT4_T, BasicArithmeticOperators.<Mat4>forSubtractionWithSame()));
  }

  public static Mat4Exp mul(Mat4Like left, float right) {
    return mul(left, constant(right));
  }
  public static Mat4Exp mul(Mat4Like left, FloatExp right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Mat4, Float, Mat4>(Type.MAT4_T, BasicArithmeticOperators.<Mat4>forMultiplicationWithFloat()));
  }
  public static Mat4Exp mul(Mat4Like left, Mat4Like right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Mat4, Mat4, Mat4>(Type.MAT4_T, BasicArithmeticOperators.<Mat4>forMultiplicationWithSame()));
  }

  public static Mat4Exp div(Mat4Like left, float right) {
    return div(left, constant(right));
  }
  public static Mat4Exp div(Mat4Like left, FloatExp right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(left), right),
        new BinaryOperationEvaluator<Mat4, Float, Mat4>(Type.MAT4_T, BasicArithmeticOperators.<Mat4>forDivisionWithFloat()));
  }
  public static Mat4Exp div(Mat4Like left, Mat4Like right) {
    return new Mat4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Mat4, Mat4, Mat4>(Type.MAT4_T, BasicArithmeticOperators.<Mat4>forDivisionWithSame()));
  }

  // ===================================================================================================================
  // Type promotion
  // ===================================================================================================================
  public static Vec2Exp promote(Vec2Like param) {
    return (param instanceof Vec2Exp) ? (Vec2Exp)param : new Vec2Exp((Vec2)param);
  }
  public static Vec3Exp promote(Vec3Like param) {
    return (param instanceof Vec3Exp) ? (Vec3Exp)param : new Vec3Exp((Vec3)param);
  }
  public static Vec4Exp promote(Vec4Like param) {
    return (param instanceof Vec4Exp) ? (Vec4Exp)param : new Vec4Exp((Vec4)param);
  }
  public static Mat2Exp promote(Mat2Like param) {
    return (param instanceof Mat2Exp) ? (Mat2Exp)param : new Mat2Exp((Mat2)param);
  }
  public static Mat3Exp promote(Mat3Like param) {
    return (param instanceof Mat3Exp) ? (Mat3Exp)param : new Mat3Exp((Mat3)param);
  }
  public static Mat4Exp promote(Mat4Like param) {
    return (param instanceof Mat4Exp) ? (Mat4Exp)param : new Mat4Exp((Mat4)param);
  }

  public static FloatExp promotef(Object param) {
    return (param instanceof FloatExp) ? (FloatExp)param : new FloatExp((Float)param);
  }

  public static Mat4Exp neg(Mat4Like exp) {
    return new Mat4Exp(ImmutableList.<Expression>of(promote(exp)), new NegationEvaluator<Mat4>());
  }

  public static Vec4Exp mul(Mat4Like left, Vec4Like right) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(promote(left), promote(right)),
        new BinaryOperationEvaluator<Mat4, Vec4, Vec4>(Type.VEC4_T, Mat4Operators.forMultiplicationWithVec4()));
  }

  // ===================================================================================================================
  // Basic transformations
  // ===================================================================================================================

  public static Transform translation(float x, float y, float z) {
    return translation(Shade.constant(x), Shade.constant(y), Shade.constant(z));
  }

  public static Transform translation(float x, float y, FloatExp z) {
    return translation(Shade.constant(x), Shade.constant(y), z);
  }

  public static Transform translation(float x, FloatExp y, float z) {
    return translation(Shade.constant(x), y, Shade.constant(z));
  }

  public static Transform translation(float x, FloatExp y, FloatExp z) {
    return translation(Shade.constant(x), y, z);
  }

  public static Transform translation(FloatExp x, float y, float z) {
    return translation(x, Shade.constant(y), Shade.constant(z));
  }

  public static Transform translation(FloatExp x, float y, FloatExp z) {
    return translation(x, Shade.constant(y), z);
  }

  public static Transform translation(FloatExp x, FloatExp y, float z) {
    return translation(x, y, Shade.constant(z));
  }

  public static Transform translation(FloatExp x, FloatExp y, FloatExp z) {
    return new Translation(x, y, z);
  }

  public static Transform rotation(float angle, Vec3Like axis) {
    return rotation(Shade.constant(angle), axis);
  }

  public static Transform rotation(FloatExp angle, Vec3Like axis) {
    return new Rotation(angle, promote(axis));
  }

  // ===================================================================================================================
  // GLSL stuff
  // ===================================================================================================================
  public static FloatExp attributef() {
    return new FloatExp(GlSlType.ATTRIBUTE_T, new QualifiedGlSlEvaluator<Float>());
  }

  public static Vec2Exp attribute2f() {
    return new Vec2Exp(GlSlType.ATTRIBUTE_T, new QualifiedGlSlEvaluator<Vec2>());
  }

  public static Vec3Exp attribute3f() {
    return new Vec3Exp(GlSlType.ATTRIBUTE_T, new QualifiedGlSlEvaluator<Vec3>());
  }

  public static Vec4Exp attribute4f() {
    return new Vec4Exp(GlSlType.ATTRIBUTE_T, new QualifiedGlSlEvaluator<Vec4>());
  }

  public static FloatExp varyingf(float c) {
    FloatExp exp = Shade.constant(c);
    return Shade.varyingf(exp);
  }

  public static FloatExp varyingf(FloatExp exp) {
    return new FloatExp(
        GlSlType.VARYING_T, ImmutableList.<Expression>of(exp), new QualifiedGlSlEvaluator<Float>());
  }

  public static Vec2Exp varying2f(Vec2Exp exp) {
    return new Vec2Exp(GlSlType.VARYING_T, ImmutableList.<Expression>of(exp), new QualifiedGlSlEvaluator<Vec2>());
  }

  public static Vec3Exp varying3f(Vec3Exp exp) {
    return new Vec3Exp(GlSlType.VARYING_T, ImmutableList.<Expression>of(exp), new QualifiedGlSlEvaluator<Vec3>());
  }

  public static Vec4Exp varying4f(Vec4Exp exp) {
    return new Vec4Exp(GlSlType.VARYING_T, ImmutableList.<Expression>of(exp), new QualifiedGlSlEvaluator<Vec4>());
  }

  public static Vec4Exp texture2D(Sampler2DExp texture, Expression texCoords) {
    return new Vec4Exp(
        ImmutableList.<Expression>of(texture, texCoords),
        new FunctionEvaluator<Vec4>(Type.VEC4_T, "texture2D") {
          @Override
          public Vec4 evaluate(Expression<Vec4> expression) {
            throw new RuntimeException("Cannot evaluate texture2D expression");
          }
        });
  }

  public static Vec4Exp fill(Expression vector, Vec4Like defaultValues) {
    Vec4Exp defaultExp = promote(defaultValues);

    switch(vector.getType()) {
      case FLOAT_T:
        return Shade.vec((FloatExp)vector, defaultExp.getY(), defaultExp.getZ(), defaultExp.getW());
      case VEC2_T:
        return Shade.vec(((Vec2Exp)vector).getX(), ((Vec2Exp)vector).getY(), defaultExp.getZ(), defaultExp.getW());
      case VEC3_T:
        return Shade.vec(
            ((Vec3Exp)vector).getX(),
            ((Vec3Exp)vector).getY(),
            ((Vec3Exp)vector).getZ(),
            defaultExp.getW());
      case VEC4_T:
        return (Vec4Exp)vector;
      default: throw new RuntimeException("Cannot fill " + vector.getType() + " to vec3");
    }
  }
}
