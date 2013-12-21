package com.lfscheidegger.jfacet.shade;

import android.graphics.Bitmap;
import com.google.common.base.Preconditions;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;

public final class Parameter {

  private Parameter() {}

  public static Real real(float value) {
    return new Real(NodeType.UniformNodeType.forFloat(value));
  }

  public static Bool bool(boolean value) {
    return new Bool(NodeType.UniformNodeType.forBoolean(value));
  }

  public static Sampler sampler(Bitmap texture) {
    return new Sampler(NodeType.UniformNodeType.forSampler(texture));
  }

  public static Vector2 vec(float x, float y) {
    return new Vector2(NodeType.UniformNodeType.forVector2(
        new Vector2.Primitive(x, y)));
  }

  public static Vector3 vec(float x, float y, float z) {
    return new Vector3(NodeType.UniformNodeType.forVector3(
        new Vector3.Primitive(x, y, z)));
  }

  public static Vector4 vec(float x, float y, float z, float w) {
    return new Vector4(NodeType.UniformNodeType.forVector4(
        new Vector4.Primitive(x, y, z, w)));
  }

  public static void set(Real parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    getNodeType(parameter).setValue(value);
  }

  public static void setX(Vector2 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setX(value);
  }

  public static void setX(Vector3 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setX(value);
  }

  public static void setX(Vector4 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setX(value);
  }

  public static void setY(Vector2 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setY(value);
  }

  public static void setY(Vector3 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setY(value);
  }

  public static void setY(Vector4 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setY(value);
  }

  public static void setZ(Vector3 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setZ(value);
  }

  public static void setZ(Vector4 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setZ(value);
  }

  public static void setW(Vector4 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setW(value);
  }

  public static void set(Vector2 parameter, float value, int idx) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).set(value, idx);
  }

  public static void set(Vector3 parameter, float value, int idx) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).set(value, idx);
  }

  public static void set(Vector4 parameter, float value, int idx) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).set(value, idx);
  }

  public static float get(Real parameter) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    return Parameter.<Float>getNodeType(parameter).getValue();
  }

  public static boolean get(Bool parameter) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    return Parameter.<Boolean>getNodeType(parameter).getValue();
  }

  public static Sampler.SamplerData get(Sampler parameter) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    return Parameter.<Sampler.SamplerData>getNodeType(parameter).getValue();
  }

  public static Vector2.Primitive get(Vector2 vec) {
    return ((NodeType.UniformNodeType<Vector2.Primitive>) vec.getNodeType()).getValue();
  }

  public static Vector3.Primitive get(Vector3 vec) {
    return ((NodeType.UniformNodeType<Vector3.Primitive>) vec.getNodeType()).getValue();
  }

  public static Vector4.Primitive get(Vector4 vec) {
    return ((NodeType.UniformNodeType<Vector4.Primitive>) vec.getNodeType()).getValue();
  }

  private static <T> NodeType.UniformNodeType<T> getNodeType(Expression vec) {
    return (NodeType.UniformNodeType<T>) vec.getNodeType();
  }
}
