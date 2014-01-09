// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.shade.expression;

import android.graphics.Bitmap;
import com.lfscheidegger.jfacet.AttributeBuffer;

public interface NodeType {

  public static final class BasicNodeType implements NodeType {}

  public static final class OperatorNodeType implements NodeType {

    private final String mOperator;

    OperatorNodeType(String operator) {
      mOperator = operator;
    }

    public String getOperator() {
      return mOperator;
    }
  }

  public static final class UnaryNodeType implements NodeType {

    private final String mOperator;

    UnaryNodeType(String operator) {
      mOperator = operator;
    }

    public String getOperator() {
      return mOperator;
    }
  }

  /**
   * Used for component expressions, where the parent is a compound expression (a vector
   * or matrix)
   */
  public static final class ComponentNodeType implements NodeType {

    private final int mComponent;

    ComponentNodeType(int component) {
      mComponent = component;
    }

    public static NodeType forComponent(int component) {
      return new ComponentNodeType(component);
    }

    public int getComponent() {
      return mComponent;
    }
  }

  public static final class FunctionNodeType implements NodeType {

    private final String mFunctionName;

    FunctionNodeType(String functionName) {
      mFunctionName = functionName;
    }

    public static NodeType forFunction(String functionName) {
      return new FunctionNodeType(functionName);
    }

    public String getFunctionName() {
      return mFunctionName;
    }
  }

  public static final class SwizzleNodeType implements NodeType {

    private final String mSwizzleString;

    SwizzleNodeType(String swizzleString) {
      mSwizzleString = swizzleString;
    }

    public static NodeType forSwizzle(String swizzleString) {
      return new SwizzleNodeType(swizzleString);
    }

    public String getSwizzleString() {
      return mSwizzleString;
    }
  }

  public static final class PrimitiveNodeType<T> implements NodeType {

    private final T mValue;

    PrimitiveNodeType(T value) {
      mValue = value;
    }

    public static <T> NodeType forPrimitive(T primitive) {
      return new PrimitiveNodeType<T>(primitive);
    }

    public T getPrimitive() {
      return mValue;
    }
  }

  public static final class AttributeNodeType implements NodeType {

    private final AttributeBuffer mAttributeBuffer;

    AttributeNodeType(AttributeBuffer attributeBuffer) {
      mAttributeBuffer = attributeBuffer;
    }

    public static NodeType forAttribute(AttributeBuffer attributeBuffer) {
      return new AttributeNodeType(attributeBuffer);
    }

    public AttributeBuffer getAttributeBuffer() {
      return mAttributeBuffer;
    }
  }

  public static final class UniformNodeType<T> implements NodeType {

    private T mValue;

    UniformNodeType(T value) {
      mValue = value;
    }

    public static UniformNodeType<Sampler2D.SamplerData> forSampler(Bitmap bitmap) {
      Sampler2D.SamplerData primitive = new Sampler2D.SamplerData();
      primitive.bitmap = bitmap;
      return new UniformNodeType<Sampler2D.SamplerData>(primitive);
    }

    public static UniformNodeType<Float> forFloat(Float primitive) {
      return new UniformNodeType<Float>(primitive);
    }

    public static UniformNodeType<Vec2.Primitive> forVector2(Vec2.Primitive primitive) {
      return new UniformNodeType<Vec2.Primitive>(primitive);
    }

    public static UniformNodeType<Vec3.Primitive> forVector3(Vec3.Primitive primitive) {
      return new UniformNodeType<Vec3.Primitive>(primitive);
    }

    public static UniformNodeType<Vec4.Primitive> forVector4(Vec4.Primitive primitive) {
      return new UniformNodeType<Vec4.Primitive>(primitive);
    }

    public static UniformNodeType<Boolean> forBoolean(Boolean primitive) {
      return new UniformNodeType<Boolean>(primitive);
    }

    public static UniformNodeType<BVec2.Primitive> forBVector2(BVec2.Primitive primitive) {
      return new UniformNodeType<BVec2.Primitive>(primitive);
    }

    public static UniformNodeType<BVec3.Primitive> forBVector3(BVec3.Primitive primitive) {
      return new UniformNodeType<BVec3.Primitive>(primitive);
    }

    public static UniformNodeType<BVec4.Primitive> forBVector4(BVec4.Primitive primitive) {
      return new UniformNodeType<BVec4.Primitive>(primitive);
    }

    public static UniformNodeType<Mat2.Primitive> forMatrix2(Mat2.Primitive primitive) {
      return new UniformNodeType<Mat2.Primitive>(primitive);
    }

    public static UniformNodeType<Mat3.Primitive> forMatrix3(Mat3.Primitive primitive) {
      return new UniformNodeType<Mat3.Primitive>(primitive);
    }

    public static UniformNodeType<Mat4.Primitive> forMatrix4(Mat4.Primitive primitive) {
      return new UniformNodeType<Mat4.Primitive>(primitive);
    }

    public T getValue() {
      return mValue;
    }

    public void setValue(T value) { mValue = value; }
  }

  /**
   * Used for constructor expressions, where the parents are the components of a compound
   * expression (a vector or matrix)
   */
  public static final NodeType CONS = new BasicNodeType();

  /**
   * Used for the ternary operator
   */
  public static final NodeType TERNARY = new BasicNodeType();

  public static final NodeType ADD = new OperatorNodeType("+");
  public static final NodeType SUB = new OperatorNodeType("-");
  public static final NodeType MUL = new OperatorNodeType("*");
  public static final NodeType DIV = new OperatorNodeType("/");

  public static final NodeType NEG = new UnaryNodeType("-");

  public static final NodeType GT = new OperatorNodeType(">");
  public static final NodeType GEQ = new OperatorNodeType(">=");
  public static final NodeType LT = new OperatorNodeType("<");
  public static final NodeType LEQ = new OperatorNodeType("<=");
  public static final NodeType EQ = new OperatorNodeType("==");
  public static final NodeType NEQ = new OperatorNodeType("!=");

  public static final NodeType AND = new OperatorNodeType("&&");
  public static final NodeType OR = new OperatorNodeType("||");
  public static final NodeType XOR = new OperatorNodeType("^^");

  public static final NodeType NOT = new UnaryNodeType("!");
}
