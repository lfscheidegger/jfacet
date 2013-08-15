package com.lfscheidegger.jfacet.shade;

import com.lfscheidegger.jfacet.shade.expression.*;

/**
 * Convenience methods to fill primitive types into expressions
 */
public final class Shade {

  public static Real constant(float c) {
    return new Real(c);
  }

  public static Vector2 vec(Vector2.Primitive vec) {
    return new Vector2(vec);
  }

  public static Vector2 vec(float x, float y) {
    return new Vector2(new Vector2.Primitive(x, y));
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

  public static Vector3 vec(Vector3.Primitive vec) {
    return new Vector3(vec);
  }

  public static Vector3 vec(float x, float y, float z) {
    return new Vector3(new Vector3.Primitive(x, y, z));
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

  public static Vector4 vec(Vector4.Primitive vec) {
    return new Vector4(vec);
  }

  public static Vector4 vec(float x, float y, float z, float w) {
    return new Vector4(new Vector4.Primitive(x, y, z, w));
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

  public static Matrix2 mat(Matrix2.Primitive mat) {
    return new Matrix2(mat);
  }

  public static Matrix2 mat(Vector2 c0, Vector2 c1) {
    return new Matrix2(c0, c1);
  }

  public static Matrix3 mat(Matrix3.Primitive mat) {
    return new Matrix3(mat);
  }

  public static Matrix3 mat(Vector3 c0, Vector3 c1, Vector3 c2) {
    return new Matrix3(c0, c1, c2);
  }

  public static Matrix4 mat(Matrix4.Primitive mat) {
    return new Matrix4(mat);
  }

  public static Matrix4 mat(Vector4 c0, Vector4 c1, Vector4 c2, Vector4 c3) {
    return new Matrix4(c0, c1, c2, c3);
  }
}
