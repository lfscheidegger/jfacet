package com.lfscheidegger.jfacet.shade;

import com.lfscheidegger.jfacet.shade.internal.*;

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

  public static Expression constant(float... args) {
    return sConstant.constant(args);
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
}
