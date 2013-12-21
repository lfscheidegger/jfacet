package com.lfscheidegger.jfacet.shade;

import android.graphics.Bitmap;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.NodeType;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.matrix.Matrix2;
import com.lfscheidegger.jfacet.shade.expression.matrix.Matrix3;
import com.lfscheidegger.jfacet.shade.expression.matrix.Matrix4;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;
import com.lfscheidegger.jfacet.shade.transform.Rotation4;
import com.lfscheidegger.jfacet.shade.transform.Translation2;
import com.lfscheidegger.jfacet.shade.transform.Translation3;
import com.lfscheidegger.jfacet.shade.transform.Translation4;

/**
 * Convenience methods to fill primitive types into expressions
 */
public final class Shade {

  private Shade() {}

  public static Real constant(float c) {
    return new Real(c);
  }

  public static Vector2 vec(Vector2.Primitive primitive) {
    return new Vector2(primitive);
  }

  public static Vector2 vec(float x, float y) {
    return new Vector2(x, y);
  }

  public static Vector2 vec(float x, Real y) {
    return new Vector2(new Real(x), y);
  }

  public static Vector2 vec(Real x, float y) {
    return new Vector2(x, new Real(y));
  }

  public static Vector2 vec(Real x, Real y) {
    return new Vector2(x, y);
  }

  public static Vector2 vec(Vector2 xy) {
    return new Vector2(ImmutableList.<Expression>of(xy), NodeType.CONS);
  }

  public static Vector3 vec(Vector3.Primitive primitive) {
    return new Vector3(primitive);
  }

  public static Vector3 vec(float x, float y, float z) {
    return new Vector3(x, y, z);
  }

  public static Vector3 vec(float x, float y, Real z) {
    return new Vector3(new Real(x), new Real(y), z);
  }

  public static Vector3 vec(float x, Real y, float z) {
    return new Vector3(new Real(x), y, new Real(z));
  }

  public static Vector3 vec(float x, Real y, Real z) {
    return new Vector3(new Real(x), y, z);
  }

  public static Vector3 vec(Real x, float y, float z) {
    return new Vector3(x, new Real(y), new Real(z));
  }

  public static Vector3 vec(Real x, float y, Real z) {
    return new Vector3(x, new Real(y), z);
  }

  public static Vector3 vec(Real x, Real y, float z) {
    return new Vector3(x, y, new Real(z));
  }

  public static Vector3 vec(Real x, Real y, Real z) {
    return new Vector3(x, y, z);
  }

  public static Vector3 vec(Vector2 xy, float z) {
    return vec(xy, new Real(z));
  }

  public static Vector3 vec(Vector2 xy, Real z) {
    return new Vector3(ImmutableList.<Expression>of(xy, z), NodeType.CONS);
  }

  public static Vector3 vec(float x, Vector2 yz) {
    return vec(new Real(x), yz);
  }

  public static Vector3 vec(Real x, Vector2 yz) {
    return new Vector3(ImmutableList.<Expression>of(x, yz), NodeType.CONS);
  }

  public static Vector3 vec(Vector3 xyz) {
    return new Vector3(ImmutableList.<Expression>of(xyz), NodeType.CONS);
  }

  public static Vector4 vec(Vector4.Primitive primitive) {
    return new Vector4(primitive);
  }

  public static Vector4 vec(float x, float y, float z, float w) {
    return new Vector4(x, y, z, w);
  }

  public static Vector4 vec(float x, float y, float z, Real w) {
    return new Vector4(new Real(x), new Real(y), new Real(z), w);
  }

  public static Vector4 vec(float x, float y, Real z, float w) {
    return new Vector4(new Real(x), new Real(y), z, new Real(w));
  }

  public static Vector4 vec(float x, float y, Real z, Real w) {
    return new Vector4(new Real(x), new Real(y), z, w);
  }

  public static Vector4 vec(float x, Real y, float z, float w) {
    return new Vector4(new Real(x), y, new Real(z), new Real(w));
  }

  public static Vector4 vec(float x, Real y, float z, Real w) {
    return new Vector4(new Real(x), y, new Real(z), w);
  }

  public static Vector4 vec(float x, Real y, Real z, float w) {
    return new Vector4(new Real(x), y, z, new Real(w));
  }

  public static Vector4 vec(float x, Real y, Real z, Real w) {
    return new Vector4(new Real(x), y, z, w);
  }

  public static Vector4 vec(Real x, float y, float z, float w) {
    return new Vector4(x, new Real(y), new Real(z), new Real(w));
  }

  public static Vector4 vec(Real x, float y, float z, Real w) {
    return new Vector4(x, new Real(y), new Real(z), w);
  }

  public static Vector4 vec(Real x, float y, Real z, float w) {
    return new Vector4(x, new Real(y), z, new Real(w));
  }

  public static Vector4 vec(Real x, float y, Real z, Real w) {
    return new Vector4(x, new Real(y), z, w);
  }

  public static Vector4 vec(Real x, Real y, float z, float w) {
    return new Vector4(x, y, new Real(z), new Real(w));
  }

  public static Vector4 vec(Real x, Real y, float z, Real w) {
    return new Vector4(x, y, new Real(z), w);
  }

  public static Vector4 vec(Real x, Real y, Real z, float w) {
    return new Vector4(x, y, z, new Real(w));
  }

  public static Vector4 vec(Real x, Real y, Real z, Real w) {
    return new Vector4(x, y, z, w);
  }

  public static Vector4 vec(Vector3 xyz, float w) {
    return vec(xyz, new Real(w));
  }

  public static Vector4 vec(Vector3 xyz, Real w) {
    return new Vector4(ImmutableList.<Expression>of(xyz, w), NodeType.CONS);
  }

  public static Vector4 vec(float x, Vector3 yzw) {
    return vec(new Real(x), yzw);
  }

  public static Vector4 vec(Real x, Vector3 yzw) {
    return new Vector4(ImmutableList.<Expression>of(x, yzw), NodeType.CONS);
  }

  public static Vector4 vec(Vector2 xy, Vector2 zw) {
    return new Vector4(ImmutableList.<Expression>of(xy, zw), NodeType.CONS);
  }

  public static Vector4 vec(Vector2 xy, float z, float w) {
    return vec(xy, new Real(z), new Real(w));
  }

  public static Vector4 vec(Vector2 xy, float z, Real w) {
    return vec(xy, new Real(z), w);
  }

  public static Vector4 vec(Vector2 xy, Real z, float w) {
    return vec(xy, z, new Real(w));
  }

  public static Vector4 vec(Vector2 xy, Real z, Real w) {
    return new Vector4(ImmutableList.<Expression>of(xy, z, w), NodeType.CONS);
  }

  public static Vector4 vec(float x, Vector2 yz, float w) {
    return vec(constant(x), yz, constant(w));
  }

  public static Vector4 vec(float x, Vector2 yz, Real w) {
    return vec(constant(x), yz, w);
  }

  public static Vector4 vec(Real x, Vector2 yz, float w) {
    return vec(x, yz, constant(w));
  }

  public static Vector4 vec(Real x, Vector2 yz, Real w) {
    return new Vector4(ImmutableList.<Expression>of(x, yz, w), NodeType.CONS);
  }

  public static Vector4 vec(float x, float y, Vector2 zw) {
    return vec(constant(x), constant(y), zw);
  }

  public static Vector4 vec(float x, Real y, Vector2 zw) {
    return vec(constant(x), y, zw);
  }

  public static Vector4 vec(Real x, float y, Vector2 zw) {
    return vec(x, constant(y), zw);
  }

  public static Vector4 vec(Real x, Real y, Vector2 zw) {
    return new Vector4(ImmutableList.<Expression>of(x, y, zw), NodeType.CONS);
  }

  public static Matrix2 mat(Vector2 c0, Vector2 c1) {
    return new Matrix2(c0, c1);
  }

  public static Matrix3 mat(Vector3 c0, Vector3 c1, Vector3 c2) {
    return new Matrix3(c0, c1, c2);
  }

  public static Matrix4 mat(Vector4 c0, Vector4 c1, Vector4 c2, Vector4 c3) {
    return new Matrix4(c0, c1, c2, c3);
  }

  public static Vector4 texture2(Bitmap bitmap, Vector2 textureCoordinates) {
    return Parameter.sampler(bitmap).texture(textureCoordinates);
  }

  public static Matrix2 identity2() {
    return new Matrix2();
  }

  public static Matrix3 identity3() {
    return new Matrix3();
  }

  public static Matrix4 identity4() {
    return new Matrix4();
  }

  public static Translation4 translate(float x, float y, float z) {
    return new Translation4(new Real(x), new Real(y), new Real(z));
  }

  public static Translation4 translate(float x, float y, Real z) {
    return new Translation4(new Real(x), new Real(y), z);
  }

  public static Translation4 translate(float x, Real y, float z) {
    return new Translation4(new Real(x), y, new Real(z));
  }

  public static Translation4 translate(float x, Real y, Real z) {
    return new Translation4(new Real(x), y, z);
  }

  public static Translation4 translate(Real x, float y, float z) {
    return new Translation4(x, new Real(y), new Real(z));
  }

  public static Translation4 translate(Real x, float y, Real z) {
    return new Translation4(x, new Real(y), z);
  }

  public static Translation4 translate(Real x, Real y, float z) {
    return new Translation4(x, y, new Real(z));
  }

  public static Translation4 translate(Real x, Real y, Real z) {
    return new Translation4(x, y, z);
  }

  public static Translation3 translate(float x, float y) {
    return new Translation3(new Real(x), new Real(y));
  }

  public static Translation3 translate(float x, Real y) {
    return new Translation3(new Real(x), y);
  }

  public static Translation3 translate(Real x, float y) {
    return new Translation3(x, new Real(y));
  }

  public static Translation3 translate(Real x, Real y) {
    return new Translation3(x, y);
  }

  public static Translation2 translate(float x) {
    return new Translation2(new Real(x));
  }

  public static Translation2 translate(Real x) {
    return new Translation2(x);
  }

  public static Rotation4 rotate(float angle, Vector3.Primitive axis) {
    return new Rotation4(Shade.constant(angle), Shade.vec(axis));
  }

  public static Rotation4 rotate(Real angle, Vector3.Primitive axis) {
    return new Rotation4(angle, Shade.vec(axis));
  }

  public static Rotation4 rotate(float angle, Vector3 axis) {
    return new Rotation4(Shade.constant(angle), axis);
  }

  public static Rotation4 rotate(Real angle, Vector3 axis) {
    return new Rotation4(angle, axis);
  }
}
