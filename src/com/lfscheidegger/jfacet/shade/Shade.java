package com.lfscheidegger.jfacet.shade;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.evaluators.*;
import com.lfscheidegger.jfacet.shade.expression.evaluators.glsl.QualifiedGlSlEvaluator;
import com.lfscheidegger.jfacet.shade.expression.operators.BasicArithmeticOperators;
import com.lfscheidegger.jfacet.shade.expression.operators.FloatOperators;
import com.lfscheidegger.jfacet.shade.primitives.Matrix;
import com.lfscheidegger.jfacet.shade.primitives.Vector;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.MatrixLike;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.VectorLike;
import com.lfscheidegger.jfacet.shade.transform.Rotation;
import com.lfscheidegger.jfacet.shade.transform.Scale;
import com.lfscheidegger.jfacet.shade.transform.Transform;
import com.lfscheidegger.jfacet.shade.transform.Translation;

/**
 * Convenience methods to fill primitive types into expressions
 */
public final class Shade {

  // ===================================================================================================================
  // Stuff for float
  // ===================================================================================================================

  public static FloatExpression constant(float c) {
    return new FloatExpression(c);
  }

  public static FloatExpression add(FloatExpression left, FloatExpression right) {
    return new FloatExpression(
        ImmutableList.<Expression>of(left, right),
        new BinaryOperationEvaluator<Float, Float, Float>(FloatOperators.forAddition()));
  }
  public static FloatExpression add(FloatExpression left, float right) {
    return add(left, new FloatExpression(right));
  }
  public static FloatExpression add(float left, FloatExpression right) {
    return add(new FloatExpression(left), right);
  }
  public static FloatExpression add(float left, float right) {
    return add(new FloatExpression(left), new FloatExpression(right));
  }

  public static FloatExpression sub(FloatExpression left, FloatExpression right) {
    return new FloatExpression(
        ImmutableList.<Expression>of(left, right),
        new BinaryOperationEvaluator<Float, Float, Float>(FloatOperators.forSubtraction()));
  }
  public static FloatExpression sub(FloatExpression left, float right) {
    return sub(left, new FloatExpression(right));
  }
  public static FloatExpression sub(float left, FloatExpression right) {
    return sub(new FloatExpression(left), right);
  }
  public static FloatExpression sub(float left, float right) {
    return sub(new FloatExpression(left), new FloatExpression(right));
  }

  public static FloatExpression mul(FloatExpression left, FloatExpression right) {
    return new FloatExpression(
        ImmutableList.<Expression>of(left, right),
        new BinaryOperationEvaluator<Float, Float, Float>(FloatOperators.forMultiplication()));
  }
  public static FloatExpression mul(FloatExpression left, float right) {
    return mul(left, new FloatExpression(right));
  }
  public static FloatExpression mul(float left, FloatExpression right) {
    return mul(new FloatExpression(left), right);
  }
  public static FloatExpression mul(float left, float right) {
    return mul(new FloatExpression(left), new FloatExpression(right));
  }

  public static FloatExpression div(FloatExpression left, FloatExpression right) {
    return new FloatExpression(
        ImmutableList.<Expression>of(left, right),
        new BinaryOperationEvaluator<Float, Float, Float>(FloatOperators.forDivision()));
  }
  public static FloatExpression div(FloatExpression left, float right) {
    return div(left, new FloatExpression(right));
  }
  public static FloatExpression div(float left, FloatExpression right) {
    return div(new FloatExpression(left), right);
  }
  public static FloatExpression div(float left, float right) {
    return div(new FloatExpression(left), new FloatExpression(right));
  }

  public static FloatExpression neg(FloatExpression exp) {
    return new FloatExpression(ImmutableList.<Expression>of(exp), new NegationEvaluator<Float>());
  }

  // ===================================================================================================================
  // Stuff for Vector
  // ===================================================================================================================

  public static VectorExpression vec(Vector val) {
    return new VectorExpression(val);
  }

  public static VectorExpression vec(float x, float y) {
    return vec(promote(x), promote(y));
  }

  public static VectorExpression vec(float x, FloatExpression y) {
    return vec(promote(x), y);
  }

  public static VectorExpression vec(FloatExpression x, float y) {
    return vec(x, promote(y));
  }

  public static VectorExpression vec(FloatExpression x, FloatExpression y) {
    return new VectorExpression(
        Type.VEC2_T,
        ImmutableList.<Expression>of(x, y),
        new ConstructorEvaluator<Vector>());
  }

  public static VectorExpression vec(float x, float y, float z) {
    return vec(promote(x), promote(y), promote(z));
  }

  public static VectorExpression vec(float x, float y, FloatExpression z) {
    return vec(promote(x), promote(y), z);
  }

  public static VectorExpression vec(float x, FloatExpression y, float z) {
    return vec(promote(x), y, promote(z));
  }

  public static VectorExpression vec(float x, FloatExpression y, FloatExpression z) {
    return vec(promote(x), y, z);
  }

  public static VectorExpression vec(FloatExpression x, float y, float z) {
    return vec(x, promote(y), promote(z));
  }

  public static VectorExpression vec(FloatExpression x, float y, FloatExpression z) {
    return vec(x, promote(y), z);
  }

  public static VectorExpression vec(FloatExpression x, FloatExpression y, float z) {
    return vec(x, y, promote(z));
  }

  public static VectorExpression vec(FloatExpression x, FloatExpression y, FloatExpression z) {
    return new VectorExpression(
        Type.VEC3_T,
        ImmutableList.<Expression>of(x, y, z),
        new ConstructorEvaluator<Vector>());
  }

  public static VectorExpression vec(float x, float y, float z, float w) {
    return vec(promote(x), promote(y), promote(z), promote(w));
  }

  public static VectorExpression vec(float x, float y, float z, FloatExpression w) {
    return vec(promote(x), promote(y), promote(z), w);
  }

  public static VectorExpression vec(float x, float y, FloatExpression z, float w) {
    return vec(promote(x), promote(y), z, promote(w));
  }

  public static VectorExpression vec(float x, float y, FloatExpression z, FloatExpression w) {
    return vec(promote(x), promote(y), z, w);
  }

  public static VectorExpression vec(float x, FloatExpression y, float z, float w) {
    return vec(promote(x), y, promote(z), promote(w));
  }

  public static VectorExpression vec(float x, FloatExpression y, float z, FloatExpression w) {
    return vec(promote(x), y, promote(z), w);
  }

  public static VectorExpression vec(float x, FloatExpression y, FloatExpression z, float w) {
    return vec(promote(x), y, z, promote(w));
  }

  public static VectorExpression vec(float x, FloatExpression y, FloatExpression z, FloatExpression w) {
    return vec(promote(x), y, z, w);
  }

  public static VectorExpression vec(FloatExpression x, float y, float z, float w) {
    return vec(x, promote(y), promote(z), promote(w));
  }

  public static VectorExpression vec(FloatExpression x, float y, float z, FloatExpression w) {
    return vec(x, promote(y), promote(z), w);
  }

  public static VectorExpression vec(FloatExpression x, float y, FloatExpression z, float w) {
    return vec(x, promote(y), z, promote(w));
  }

  public static VectorExpression vec(FloatExpression x, float y, FloatExpression z, FloatExpression w) {
    return vec(x, promote(y), z, w);
  }

  public static VectorExpression vec(FloatExpression x, FloatExpression y, float z, float w) {
    return vec(x, y, promote(z), promote(w));
  }

  public static VectorExpression vec(FloatExpression x, FloatExpression y, float z, FloatExpression w) {
    return vec(x, y, promote(z), w);
  }

  public static VectorExpression vec(FloatExpression x, FloatExpression y, FloatExpression z, float w) {
    return vec(x, y, z, promote(w));
  }

  public static VectorExpression vec(FloatExpression x, FloatExpression y, FloatExpression z, FloatExpression w) {
    return new VectorExpression(
        Type.VEC4_T,
        ImmutableList.<Expression>of(x, y, z, w),
        new ConstructorEvaluator<Vector>());
  }

  public static VectorExpression add(VectorLike left, float right) {
    return add(left, promote(right));
  }

  public static VectorExpression add(VectorLike left, FloatExpression right) {
    VectorExpression leftExpression = promote(left);
    return new VectorExpression(
        leftExpression.getType(),
        ImmutableList.<Expression>of(leftExpression, right),
        new BinaryOperationEvaluator<Vector, Float, Vector>(BasicArithmeticOperators.<Vector>forAdditionWithFloat()));
  }

  public static VectorExpression add(VectorLike left, VectorLike right) {
    VectorExpression leftExpression = promote(left);
    VectorExpression rightExpression = promote(right);
    Preconditions.checkState(leftExpression.getType() == rightExpression.getType());
    return new VectorExpression(
        leftExpression.getType(),
        ImmutableList.<Expression>of(leftExpression, rightExpression),
        new BinaryOperationEvaluator<Vector, Vector, Vector>(BasicArithmeticOperators.<Vector>forAdditionWithSame()));
  }

  public static VectorExpression sub(VectorLike left, float right) {
    return sub(left, promote(right));
  }

  public static VectorExpression sub(VectorLike left, FloatExpression right) {
    VectorExpression leftExpression = promote(left);
    return new VectorExpression(
        leftExpression.getType(),
        ImmutableList.<Expression>of(leftExpression, right),
        new BinaryOperationEvaluator<Vector, Float, Vector>(
            BasicArithmeticOperators.<Vector>forSubtractionWithFloat()));
  }

  public static VectorExpression sub(VectorLike left, VectorLike right) {
    VectorExpression leftExpression = promote(left);
    VectorExpression rightExpression = promote(right);
    Preconditions.checkState(leftExpression.getType() == rightExpression.getType());
    return new VectorExpression(
        leftExpression.getType(),
        ImmutableList.<Expression>of(leftExpression, rightExpression),
        new BinaryOperationEvaluator<Vector, Vector, Vector>(BasicArithmeticOperators.<Vector>forSubtractionWithSame()));
  }

  public static VectorExpression mul(VectorLike left, float right) {
    return mul(left, promote(right));
  }

  public static VectorExpression mul(VectorLike left, FloatExpression right) {
    VectorExpression leftExpression = promote(left);
    return new VectorExpression(
        leftExpression.getType(),
        ImmutableList.<Expression>of(leftExpression, right),
        new BinaryOperationEvaluator<Vector, Float, Vector>(
            BasicArithmeticOperators.<Vector>forMultiplicationWithFloat()));
  }

  public static VectorExpression mul(VectorLike left, VectorLike right) {
    VectorExpression leftExpression = promote(left);
    VectorExpression rightExpression = promote(right);
    Preconditions.checkState(leftExpression.getType() == rightExpression.getType());
    return new VectorExpression(
        leftExpression.getType(),
        ImmutableList.<Expression>of(leftExpression, rightExpression),
        new BinaryOperationEvaluator<Vector, Vector, Vector>(
            BasicArithmeticOperators.<Vector>forMultiplicationWithSame()));
  }

  public static VectorExpression div(VectorLike left, float right) {
    return div(left, promote(right));
  }

  public static VectorExpression div(VectorLike left, FloatExpression right) {
    VectorExpression leftExpression = promote(left);
    return new VectorExpression(
        leftExpression.getType(),
        ImmutableList.<Expression>of(leftExpression, right),
        new BinaryOperationEvaluator<Vector, Float, Vector>(BasicArithmeticOperators.<Vector>forDivisionWithFloat()));
  }

  public static VectorExpression div(VectorLike left, VectorLike right) {
    VectorExpression leftExpression = promote(left);
    VectorExpression rightExpression = promote(right);
    Preconditions.checkState(leftExpression.getType() == rightExpression.getType());
    return new VectorExpression(
        leftExpression.getType(),
        ImmutableList.<Expression>of(leftExpression, rightExpression),
        new BinaryOperationEvaluator<Vector, Vector, Vector>(BasicArithmeticOperators.<Vector>forDivisionWithSame()));
  }

  public static VectorExpression neg(VectorLike exp) {
    VectorExpression expression = promote(exp);
    return new VectorExpression(
        expression.getType(),
        ImmutableList.<Expression>of(expression),
        new NegationEvaluator<Vector>());
  }

  public static FloatExpression dot(VectorLike left, VectorLike right) {
    return Math.dot(promote(left), promote(right));
  }

  public static VectorExpression cross(VectorLike left, VectorLike right) {
    VectorExpression leftExpression = promote(left);
    VectorExpression rightExpression = promote(right);
    Preconditions.checkState(leftExpression.getType() == Type.VEC3_T && rightExpression.getType() == Type.VEC3_T);
    return Math.cross(leftExpression, rightExpression);
  }

  public static VectorExpression normalize(VectorLike val) {
    return Math.normalize(promote(val));
  }

  public static FloatExpression swizzle(VectorLike val, char x) {
    VectorExpression valExpression = promote(val);
    return new FloatExpression(ImmutableList.<Expression>of(valExpression), new SwizzleEvaluator<Float>(x));
  }

  public static VectorExpression swizzle(VectorLike val, char x, char y) {
    VectorExpression valExpression = promote(val);
    return new VectorExpression(
        Type.VEC2_T,
        ImmutableList.<Expression>of(valExpression),
        new SwizzleEvaluator<Vector>(x, y));
  }

  public static VectorExpression swizzle(VectorLike val, char x, char y, char z) {
    VectorExpression valExpression = promote(val);
    return new VectorExpression(
        Type.VEC2_T,
        ImmutableList.<Expression>of(valExpression),
        new SwizzleEvaluator<Vector>(x, y, z));
  }

  public static VectorExpression swizzle(VectorLike val, char x, char y, char z, char w) {
    VectorExpression valExpression = promote(val);
    return new VectorExpression(
        Type.VEC2_T,
        ImmutableList.<Expression>of(valExpression),
        new SwizzleEvaluator<Vector>(x, y, z, w));
  }

  // ===================================================================================================================
  // Stuff for Matrix
  // ===================================================================================================================
  public static MatrixExpression mat(Matrix val) {
    return new MatrixExpression(val);
  }

  public static MatrixExpression mat(VectorLike x, VectorLike y) {
    VectorExpression xExp = promote(x);
    VectorExpression yExp = promote(y);
    Preconditions.checkState(xExp.getType() == Type.VEC2_T && yExp.getType() == Type.VEC2_T);
    return new MatrixExpression(
        Type.MAT2_T,
        ImmutableList.<Expression>of(xExp, yExp),
        new ConstructorEvaluator<Matrix>());
  }

  public static MatrixExpression mat(VectorLike x, VectorLike y, VectorLike z) {
    VectorExpression xExp = promote(x);
    VectorExpression yExp = promote(y);
    VectorExpression zExp = promote(z);
    Preconditions.checkState(
        xExp.getType() == Type.VEC3_T &&
        yExp.getType() == Type.VEC3_T &&
        zExp.getType() == Type.VEC3_T);
    return new MatrixExpression(
        Type.MAT3_T,
        ImmutableList.<Expression>of(xExp, yExp, zExp),
        new ConstructorEvaluator<Matrix>());
  }

  public static MatrixExpression mat(VectorLike x, VectorLike y, VectorLike z, VectorLike w) {
    VectorExpression xExp = promote(x);
    VectorExpression yExp = promote(y);
    VectorExpression zExp = promote(z);
    VectorExpression wExp = promote(w);
    Preconditions.checkState(
        xExp.getType() == Type.VEC4_T &&
        yExp.getType() == Type.VEC4_T &&
        zExp.getType() == Type.VEC4_T &&
        wExp.getType() == Type.VEC4_T);

    return new MatrixExpression(
        Type.MAT4_T,
        ImmutableList.<Expression>of(xExp, yExp, zExp, wExp),
        new ConstructorEvaluator<Matrix>());
  }

  public static MatrixExpression add(MatrixLike left, float right) {
    return add(left, promote(right));
  }

  public static MatrixExpression add(MatrixLike left, FloatExpression right) {
    MatrixExpression leftExpression = promote(left);
    return new MatrixExpression(
        leftExpression.getType(),
        ImmutableList.<Expression>of(leftExpression, right),
        new BinaryOperationEvaluator<Matrix, Float, Matrix>(BasicArithmeticOperators.<Matrix>forAdditionWithFloat()));
  }

  public static MatrixExpression add(MatrixLike left, MatrixLike right) {
    MatrixExpression leftExpression = promote(left);
    MatrixExpression rightExpression = promote(right);
    Preconditions.checkState(leftExpression.getType() == rightExpression.getType());
    return new MatrixExpression(
        leftExpression.getType(),
        ImmutableList.<Expression>of(leftExpression, rightExpression),
        new BinaryOperationEvaluator<Matrix, Matrix, Matrix>(BasicArithmeticOperators.<Matrix>forAdditionWithSame()));
  }

  public static MatrixExpression sub(MatrixLike left, float right) {
    return sub(left, promote(right));
  }

  public static MatrixExpression sub(MatrixLike left, FloatExpression right) {
    MatrixExpression leftExpression = promote(left);
    return new MatrixExpression(
        leftExpression.getType(),
        ImmutableList.<Expression>of(leftExpression, right),
        new BinaryOperationEvaluator<Matrix, Float, Matrix>(
            BasicArithmeticOperators.<Matrix>forSubtractionWithFloat()));
  }

  public static MatrixExpression sub(MatrixLike left, MatrixLike right) {
    MatrixExpression leftExpression = promote(left);
    MatrixExpression rightExpression = promote(right);
    Preconditions.checkState(leftExpression.getType() == rightExpression.getType());
    return new MatrixExpression(
        leftExpression.getType(),
        ImmutableList.<Expression>of(leftExpression, rightExpression),
        new BinaryOperationEvaluator<Matrix, Matrix, Matrix>(
            BasicArithmeticOperators.<Matrix>forSubtractionWithSame()));
  }

  public static MatrixExpression mul(MatrixLike left, float right) {
    return mul(left, promote(right));
  }

  public static MatrixExpression mul(MatrixLike left, FloatExpression right) {
    MatrixExpression leftExpression = promote(left);
    return new MatrixExpression(
        leftExpression.getType(),
        ImmutableList.<Expression>of(leftExpression, right),
        new BinaryOperationEvaluator<Matrix, Float, Matrix>(
            BasicArithmeticOperators.<Matrix>forMultiplicationWithFloat()));
  }

  public static VectorExpression mul(MatrixLike left, VectorLike right) {
    MatrixExpression leftExp = promote(left);
    VectorExpression rightExp = promote(right);
    Preconditions.checkState(
        leftExp.getType() == Type.MAT2_T && rightExp.getType() == Type.VEC2_T ||
        leftExp.getType() == Type.MAT3_T && rightExp.getType() == Type.VEC3_T ||
        leftExp.getType() == Type.MAT4_T && rightExp.getType() == Type.VEC4_T);
    return new VectorExpression(
        rightExp.getType(),
        ImmutableList.<Expression>of(leftExp, rightExp),
        new BinaryOperationEvaluator<Matrix, Vector, Vector>(
            BasicArithmeticOperators.forLinearTransform()));
  }

  public static MatrixExpression mul(MatrixLike left, MatrixLike right) {
    MatrixExpression leftExpression = promote(left);
    MatrixExpression rightExpression = promote(right);
    Preconditions.checkState(leftExpression.getType() == rightExpression.getType());
    return new MatrixExpression(
        leftExpression.getType(),
        ImmutableList.<Expression>of(leftExpression, rightExpression),
        new BinaryOperationEvaluator<Matrix, Matrix, Matrix>(
            BasicArithmeticOperators.<Matrix>forMultiplicationWithSame()));
  }

  public static MatrixExpression div(MatrixLike left, float right) {
    return div(left, promote(right));
  }

  public static MatrixExpression div(MatrixLike left, FloatExpression right) {
    MatrixExpression leftExpression = promote(left);
    return new MatrixExpression(
        leftExpression.getType(),
        ImmutableList.<Expression>of(leftExpression, right),
        new BinaryOperationEvaluator<Matrix, Float, Matrix>(BasicArithmeticOperators.<Matrix>forDivisionWithFloat()));
  }

  public static MatrixExpression div(MatrixLike left, MatrixLike right) {
    MatrixExpression leftExpression = promote(left);
    MatrixExpression rightExpression = promote(right);
    Preconditions.checkState(leftExpression.getType() == rightExpression.getType());
    return new MatrixExpression(
        leftExpression.getType(),
        ImmutableList.<Expression>of(leftExpression, rightExpression),
        new BinaryOperationEvaluator<Matrix, Matrix, Matrix>(BasicArithmeticOperators.<Matrix>forDivisionWithSame()));
  }

  public static MatrixExpression neg(MatrixExpression exp) {
    return new MatrixExpression(exp.getType(), ImmutableList.<Expression>of(exp), new NegationEvaluator<Matrix>());
  }

  public static FloatExpression determinant(MatrixLike val) {
    return Math.determinant(promote(val));
  }

  public static MatrixExpression inverse(MatrixLike val) {
    return Math.inverse(promote(val));
  }

  public static MatrixExpression transpose(MatrixLike val) {
    return Math.transpose(promote(val));
  }

  // ===================================================================================================================
  // Type promotion
  // ===================================================================================================================
  public static FloatExpression promote(float param) {
    return constant(param);
  }

  public static VectorExpression promote(VectorLike param) {
    return (param instanceof VectorExpression) ? (VectorExpression)param : new VectorExpression((Vector)param);
  }

  public static MatrixExpression promote(MatrixLike param) {
    return (param instanceof MatrixExpression) ? (MatrixExpression)param : new MatrixExpression((Matrix)param);
  }

  // ===================================================================================================================
  // Basic transformations
  // ===================================================================================================================

  public static Transform scale(float sx, float sy, float sz) {
    return scale(Shade.constant(sx), Shade.constant(sy), Shade.constant(sz));
  }

  public static Transform scale(float sx, float sy, FloatExpression sz) {
    return scale(Shade.constant(sx), Shade.constant(sy), sz);
  }

  public static Transform scale(float sx, FloatExpression sy, float sz) {
    return scale(Shade.constant(sx), sy, Shade.constant(sz));
  }

  public static Transform scale(float sx, FloatExpression sy, FloatExpression sz) {
    return scale(Shade.constant(sx), sy, sz);
  }

  public static Transform scale(FloatExpression sx, float sy, float sz) {
    return scale(sx, Shade.constant(sy), Shade.constant(sz));
  }

  public static Transform scale(FloatExpression sx, float sy, FloatExpression sz) {
    return scale(sx, Shade.constant(sy), sz);
  }

  public static Transform scale(FloatExpression sx, FloatExpression sy, float sz) {
    return scale(sx,  sy, Shade.constant(sz));
  }

  public static Transform scale(FloatExpression sx, FloatExpression sy, FloatExpression sz) {
    return new Scale(sx, sy, sz);
  }

  public static Transform translation(float x, float y, float z) {
    return translation(Shade.constant(x), Shade.constant(y), Shade.constant(z));
  }

  public static Transform translation(float x, float y, FloatExpression z) {
    return translation(Shade.constant(x), Shade.constant(y), z);
  }

  public static Transform translation(float x, FloatExpression y, float z) {
    return translation(Shade.constant(x), y, Shade.constant(z));
  }

  public static Transform translation(float x, FloatExpression y, FloatExpression z) {
    return translation(Shade.constant(x), y, z);
  }

  public static Transform translation(FloatExpression x, float y, float z) {
    return translation(x, Shade.constant(y), Shade.constant(z));
  }

  public static Transform translation(FloatExpression x, float y, FloatExpression z) {
    return translation(x, Shade.constant(y), z);
  }

  public static Transform translation(FloatExpression x, FloatExpression y, float z) {
    return translation(x, y, Shade.constant(z));
  }

  public static Transform translation(FloatExpression x, FloatExpression y, FloatExpression z) {
    return new Translation(x, y, z);
  }

  public static Transform rotation(float angle, VectorLike axis) {
    return rotation(Shade.constant(angle), axis);
  }

  public static Transform rotation(FloatExpression angle, VectorLike axis) {
    return new Rotation(angle, promote(axis));
  }

  // ===================================================================================================================
  // GLSL stuff
  // ===================================================================================================================

  public static FloatExpression attributef() {
    return new FloatExpression(GlSlType.ATTRIBUTE_T, new QualifiedGlSlEvaluator<Float>());
  }

  public static VectorExpression attribute2f() {
    return new VectorExpression(Type.VEC2_T, GlSlType.ATTRIBUTE_T, new QualifiedGlSlEvaluator<Vector>());
  }

  public static VectorExpression attribute3f() {
    return new VectorExpression(Type.VEC3_T, GlSlType.ATTRIBUTE_T, new QualifiedGlSlEvaluator<Vector>());
  }

  public static VectorExpression attribute4f() {
    return new VectorExpression(Type.VEC4_T, GlSlType.ATTRIBUTE_T, new QualifiedGlSlEvaluator<Vector>());
  }

  public static FloatExpression varying(float c) {
    FloatExpression exp = Shade.constant(c);
    return Shade.varying(exp);
  }

  public static FloatExpression varying(FloatExpression exp) {
    return new FloatExpression(
        GlSlType.VARYING_T, ImmutableList.<Expression>of(exp), new QualifiedGlSlEvaluator<Float>());
  }

  public static VectorExpression varying(VectorExpression exp) {
    return new VectorExpression(
        exp.getType(),
        GlSlType.VARYING_T,
        ImmutableList.<Expression>of(exp),
        new QualifiedGlSlEvaluator<Vector>());
  }

  public static VectorExpression texture2D(SamplerExpression texture, Expression texCoords) {
    return new VectorExpression(
        Type.VEC4_T,
        ImmutableList.<Expression>of(texture, texCoords),
        new FunctionEvaluator<Vector>(Type.VEC4_T, "texture2D") {
          @Override
          public Vector evaluate(Expression<Vector> expression) {
            throw new RuntimeException("Cannot evaluate texture2D expression");
          }
        });
  }

  public static VectorExpression fill(Expression vector, VectorLike defaultValues) {
    VectorExpression defaultExpression = promote(defaultValues);

    switch(vector.getType()) {
      case FLOAT_T:
        return Shade.vec(
            (FloatExpression)vector,
            defaultExpression.getY(),
            defaultExpression.getZ(),
            defaultExpression.getW());
      case VEC2_T:
        return Shade.vec(
            ((VectorExpression)vector).getX(),
            ((VectorExpression)vector).getY(),
            defaultExpression.getZ(),
            defaultExpression.getW());
      case VEC3_T:
        return Shade.vec(
            ((VectorExpression)vector).getX(),
            ((VectorExpression)vector).getY(),
            ((VectorExpression)vector).getZ(),
            defaultExpression.getW());
      case VEC4_T:
        return (VectorExpression)vector;
      default: throw new RuntimeException("Cannot fill " + vector.getType() + " to vec4");
    }
  }
}
