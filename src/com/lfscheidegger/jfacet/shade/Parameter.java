package com.lfscheidegger.jfacet.shade;

import android.graphics.Bitmap;
import com.google.common.base.Preconditions;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.Vec2;
import com.lfscheidegger.jfacet.shade.expression.Vec3;
import com.lfscheidegger.jfacet.shade.expression.Vec4;

public final class Parameter {

  private Parameter() {}

  public static Real real(float value) {
    return new Real(NodeType.UniformNodeType.forFloat(value));
  }

  public static Bool bool(boolean value) {
    return new Bool(NodeType.UniformNodeType.forBoolean(value));
  }

  public static Sampler2D sampler(Bitmap texture) {
    return new Sampler2D(NodeType.UniformNodeType.forSampler(texture));
  }

  public static Vec2 vec(float x, float y) {
    return new Vec2(NodeType.UniformNodeType.forVector2(
        new Vec2.Primitive(x, y)));
  }

  public static Vec3 vec(float x, float y, float z) {
    return new Vec3(NodeType.UniformNodeType.forVector3(
        new Vec3.Primitive(x, y, z)));
  }

  public static Vec4 vec(float x, float y, float z, float w) {
    return new Vec4(NodeType.UniformNodeType.forVector4(
        new Vec4.Primitive(x, y, z, w)));
  }

  public static void set(Real parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    getNodeType(parameter).setValue(value);
  }

  public static void setX(Vec2 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setX(value);
  }

  public static void setX(Vec3 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setX(value);
  }

  public static void setX(Vec4 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setX(value);
  }

  public static void setY(Vec2 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setY(value);
  }

  public static void setY(Vec3 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setY(value);
  }

  public static void setY(Vec4 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setY(value);
  }

  public static void setZ(Vec3 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setZ(value);
  }

  public static void setZ(Vec4 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setZ(value);
  }

  public static void setW(Vec4 parameter, float value) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).setW(value);
  }

  public static void set(Vec2 parameter, float value, int idx) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).set(value, idx);
  }

  public static void set(Vec3 parameter, float value, int idx) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    get(parameter).set(value, idx);
  }

  public static void set(Vec4 parameter, float value, int idx) {
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

  public static Sampler2D.SamplerData get(Sampler2D parameter) {
    Preconditions.checkArgument(parameter.getNodeType() instanceof NodeType.UniformNodeType);
    return Parameter.<Sampler2D.SamplerData>getNodeType(parameter).getValue();
  }

  public static Vec2.Primitive get(Vec2 vec) {
    return ((NodeType.UniformNodeType<Vec2.Primitive>) vec.getNodeType()).getValue();
  }

  public static Vec3.Primitive get(Vec3 vec) {
    return ((NodeType.UniformNodeType<Vec3.Primitive>) vec.getNodeType()).getValue();
  }

  public static Vec4.Primitive get(Vec4 vec) {
    return ((NodeType.UniformNodeType<Vec4.Primitive>) vec.getNodeType()).getValue();
  }

  private static <T> NodeType.UniformNodeType<T> getNodeType(Expression vec) {
    return (NodeType.UniformNodeType<T>) vec.getNodeType();
  }
}
