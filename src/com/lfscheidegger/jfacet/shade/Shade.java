package com.lfscheidegger.jfacet.shade;

import android.graphics.Bitmap;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.Mat3;
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

  public static Vec2 vec(Vec2.Primitive primitive) {
    return new Vec2(primitive);
  }

  public static Vec2 vec(float x, float y) {
    return new Vec2(x, y);
  }

  public static Vec2 vec(float x, Real y) {
    return new Vec2(new Real(x), y);
  }

  public static Vec2 vec(Real x, float y) {
    return new Vec2(x, new Real(y));
  }

  public static Vec2 vec(Real x, Real y) {
    return new Vec2(x, y);
  }

  public static Vec2 vec(Vec2 xy) {
    return new Vec2(ImmutableList.<Expression>of(xy), NodeType.CONS);
  }

  public static Vec3 vec(Vec3.Primitive primitive) {
    return new Vec3(primitive);
  }

  public static Vec3 vec(float x, float y, float z) {
    return new Vec3(x, y, z);
  }

  public static Vec3 vec(float x, float y, Real z) {
    return new Vec3(new Real(x), new Real(y), z);
  }

  public static Vec3 vec(float x, Real y, float z) {
    return new Vec3(new Real(x), y, new Real(z));
  }

  public static Vec3 vec(float x, Real y, Real z) {
    return new Vec3(new Real(x), y, z);
  }

  public static Vec3 vec(Real x, float y, float z) {
    return new Vec3(x, new Real(y), new Real(z));
  }

  public static Vec3 vec(Real x, float y, Real z) {
    return new Vec3(x, new Real(y), z);
  }

  public static Vec3 vec(Real x, Real y, float z) {
    return new Vec3(x, y, new Real(z));
  }

  public static Vec3 vec(Real x, Real y, Real z) {
    return new Vec3(x, y, z);
  }

  public static Vec3 vec(Vec2 xy, float z) {
    return vec(xy, new Real(z));
  }

  public static Vec3 vec(Vec2 xy, Real z) {
    return new Vec3(ImmutableList.<Expression>of(xy, z), NodeType.CONS);
  }

  public static Vec3 vec(float x, Vec2 yz) {
    return vec(new Real(x), yz);
  }

  public static Vec3 vec(Real x, Vec2 yz) {
    return new Vec3(ImmutableList.<Expression>of(x, yz), NodeType.CONS);
  }

  public static Vec3 vec(Vec3 xyz) {
    return new Vec3(ImmutableList.<Expression>of(xyz), NodeType.CONS);
  }

  public static Vec4 vec(Vec4.Primitive primitive) {
    return new Vec4(primitive);
  }

  public static Vec4 vec(float x, float y, float z, float w) {
    return new Vec4(x, y, z, w);
  }

  public static Vec4 vec(float x, float y, float z, Real w) {
    return new Vec4(new Real(x), new Real(y), new Real(z), w);
  }

  public static Vec4 vec(float x, float y, Real z, float w) {
    return new Vec4(new Real(x), new Real(y), z, new Real(w));
  }

  public static Vec4 vec(float x, float y, Real z, Real w) {
    return new Vec4(new Real(x), new Real(y), z, w);
  }

  public static Vec4 vec(float x, Real y, float z, float w) {
    return new Vec4(new Real(x), y, new Real(z), new Real(w));
  }

  public static Vec4 vec(float x, Real y, float z, Real w) {
    return new Vec4(new Real(x), y, new Real(z), w);
  }

  public static Vec4 vec(float x, Real y, Real z, float w) {
    return new Vec4(new Real(x), y, z, new Real(w));
  }

  public static Vec4 vec(float x, Real y, Real z, Real w) {
    return new Vec4(new Real(x), y, z, w);
  }

  public static Vec4 vec(Real x, float y, float z, float w) {
    return new Vec4(x, new Real(y), new Real(z), new Real(w));
  }

  public static Vec4 vec(Real x, float y, float z, Real w) {
    return new Vec4(x, new Real(y), new Real(z), w);
  }

  public static Vec4 vec(Real x, float y, Real z, float w) {
    return new Vec4(x, new Real(y), z, new Real(w));
  }

  public static Vec4 vec(Real x, float y, Real z, Real w) {
    return new Vec4(x, new Real(y), z, w);
  }

  public static Vec4 vec(Real x, Real y, float z, float w) {
    return new Vec4(x, y, new Real(z), new Real(w));
  }

  public static Vec4 vec(Real x, Real y, float z, Real w) {
    return new Vec4(x, y, new Real(z), w);
  }

  public static Vec4 vec(Real x, Real y, Real z, float w) {
    return new Vec4(x, y, z, new Real(w));
  }

  public static Vec4 vec(Real x, Real y, Real z, Real w) {
    return new Vec4(x, y, z, w);
  }

  public static Vec4 vec(Vec3 xyz, float w) {
    return vec(xyz, new Real(w));
  }

  public static Vec4 vec(Vec3 xyz, Real w) {
    return new Vec4(ImmutableList.<Expression>of(xyz, w), NodeType.CONS);
  }

  public static Vec4 vec(float x, Vec3 yzw) {
    return vec(new Real(x), yzw);
  }

  public static Vec4 vec(Real x, Vec3 yzw) {
    return new Vec4(ImmutableList.<Expression>of(x, yzw), NodeType.CONS);
  }

  public static Vec4 vec(Vec2 xy, Vec2 zw) {
    return new Vec4(ImmutableList.<Expression>of(xy, zw), NodeType.CONS);
  }

  public static Vec4 vec(Vec2 xy, float z, float w) {
    return vec(xy, new Real(z), new Real(w));
  }

  public static Vec4 vec(Vec2 xy, float z, Real w) {
    return vec(xy, new Real(z), w);
  }

  public static Vec4 vec(Vec2 xy, Real z, float w) {
    return vec(xy, z, new Real(w));
  }

  public static Vec4 vec(Vec2 xy, Real z, Real w) {
    return new Vec4(ImmutableList.<Expression>of(xy, z, w), NodeType.CONS);
  }

  public static Vec4 vec(float x, Vec2 yz, float w) {
    return vec(constant(x), yz, constant(w));
  }

  public static Vec4 vec(float x, Vec2 yz, Real w) {
    return vec(constant(x), yz, w);
  }

  public static Vec4 vec(Real x, Vec2 yz, float w) {
    return vec(x, yz, constant(w));
  }

  public static Vec4 vec(Real x, Vec2 yz, Real w) {
    return new Vec4(ImmutableList.<Expression>of(x, yz, w), NodeType.CONS);
  }

  public static Vec4 vec(float x, float y, Vec2 zw) {
    return vec(constant(x), constant(y), zw);
  }

  public static Vec4 vec(float x, Real y, Vec2 zw) {
    return vec(constant(x), y, zw);
  }

  public static Vec4 vec(Real x, float y, Vec2 zw) {
    return vec(x, constant(y), zw);
  }

  public static Vec4 vec(Real x, Real y, Vec2 zw) {
    return new Vec4(ImmutableList.<Expression>of(x, y, zw), NodeType.CONS);
  }

  public static Mat2 mat(Vec2 c0, Vec2 c1) {
    return new Mat2(c0, c1);
  }

  public static Mat3 mat(Vec3 c0, Vec3 c1, Vec3 c2) {
    return new Mat3(c0, c1, c2);
  }

  public static Mat4 mat(Vec4 c0, Vec4 c1, Vec4 c2, Vec4 c3) {
    return new Mat4(c0, c1, c2, c3);
  }

  public static Vec4 texture2(Bitmap bitmap, Vec2 textureCoordinates) {
    return Parameter.sampler(bitmap).texture(textureCoordinates);
  }

  public static Mat2 identity2() {
    return new Mat2();
  }

  public static Mat3 identity3() {
    return new Mat3();
  }

  public static Mat4 identity4() {
    return new Mat4();
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

  public static Rotation4 rotate(float angle, Vec3.Primitive axis) {
    return new Rotation4(Shade.constant(angle), Shade.vec(axis));
  }

  public static Rotation4 rotate(Real angle, Vec3.Primitive axis) {
    return new Rotation4(angle, Shade.vec(axis));
  }

  public static Rotation4 rotate(float angle, Vec3 axis) {
    return new Rotation4(Shade.constant(angle), axis);
  }

  public static Rotation4 rotate(Real angle, Vec3 axis) {
    return new Rotation4(angle, axis);
  }
}
