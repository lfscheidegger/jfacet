package com.lfscheidegger.jfacet.shade;

import com.lfscheidegger.jfacet.shade.internal.*;
import com.lfscheidegger.jfacet.shade.internal.types.*;

/**
 * Namespace for some convenient static methods
 */
public class Shade {

  private static final Constant sConstant = new Constant();

  /**
   * Adds a varying number of {@code Expression} objects together;
   * @param args - varargs for expressions to be added together;
   *
   * @return - an {@code Expression} for the sum of the given arguments
   */
  public static Expression add(Expression... args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("add must take at least one argument");
    }

    if (args.length == 1) {
      return args[0];
    }

    return null;
  }

  //
  // Constant expressions
  //
  public static Expression constant(float... args) {
    return sConstant.constant(args);
  }

  public static Expression constant(Vec2 xy) {
    return sConstant.constant(xy);
  }

  public static Expression constant(Vec3 xyz) {
    return sConstant.constant(xyz);
  }

  public static Expression constant(Vec4 xyzw) {
    return sConstant.constant(xyzw);
  }

  public static Expression constant(Mat2 mat) {
    return sConstant.constant(mat);
  }

  public static Expression constant(Mat3 mat) {
    return sConstant.constant(mat);
  }

  public static Expression constant(Mat4 mat) {
    return sConstant.constant(mat);
  }

  //
  // Aggregate type constructors
  //
  public static Vec2 vec2() {
    return new Vec2();
  }

  public static Vec2 vec2(float x, float y) {
    return new Vec2(x, y);
  }

  public static Vec2 vec2(Vec2 other) {
    return new Vec2(other);
  }

  public static Vec3 vec3() {
    return new Vec3();
  }

  public static Vec3 vec3(float x, float y, float z) {
    return new Vec3(x, y, z);
  }

  public static Vec3 vec3(Vec2 xy, float z) {
    return new Vec3(xy, z);
  }

  public static Vec3 vec3(float x, Vec2 yz) {
    return new Vec3(x, yz);
  }

  public static Vec3 vec3(Vec3 other) {
    return new Vec3(other);
  }

  public static Vec4 vec4() {
    return new Vec4();
  }

  public static Vec4 vec4(float x, float y, float z, float w) {
    return new Vec4(x, y, z, w);
  }

  public static Vec4 vec4(Vec2 xy, float z, float w) {
    return new Vec4(xy, z, w);
  }

  public static Vec4 vec4(float x, Vec2 yz, float w) {
    return new Vec4(x, yz, w);
  }

  public static Vec4 vec4(float x, float y, Vec2 zw) {
    return new Vec4(x, y, zw);
  }

  public static Vec4 vec4(Vec2 xy, Vec2 zw) {
    return new Vec4(xy, zw);
  }

  public static Vec4 vec4(Vec3 xyz, float w) {
    return new Vec4(xyz, w);
  }

  public static Vec4 vec4(float x, Vec3 yzw) {
    return new Vec4(x, yzw);
  }

  public static Vec4 vec4(Vec4 other) {
    return new Vec4(other);
  }

  public static Mat2 mat2() {
    return new Mat2();
  }

  public static Mat2 mat2(Vec2 c0, Vec2 c1) {
    return new Mat2(c0, c1);
  }

  public static Mat2 mat2(Mat2 other) {
    return new Mat2(other);
  }

  public static Mat3 mat3() {
    return new Mat3();
  }

  public static Mat3 mat3(Vec3 c0, Vec3 c1, Vec3 c2) {
    return new Mat3(c0, c1, c2);
  }

  public static Mat3 mat3(Mat3 other) {
    return new Mat3(other);
  }

  public static Mat4 mat4() {
    return new Mat4();
  }

  public static Mat4 mat4(Vec4 c0, Vec4 c1, Vec4 c2, Vec4 c3) {
    return new Mat4(c0, c1, c2, c3);
  }

  public static Mat4 mat4(Mat4 other) {
    return new Mat4(other);
  }
}
