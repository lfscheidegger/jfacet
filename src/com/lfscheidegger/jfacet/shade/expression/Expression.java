package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlQualifier;

/**
 * A Shade expression.
 */
public interface Expression<T> {

  public static interface NodeType {

    public static final class BasicNodeType implements NodeType {

      private final String mValue;

      BasicNodeType(String value) {
        mValue = value;
      }

      public String getValue() {
        return mValue;
      }
    }

    public static final class OperatorNodeType implements NodeType {

      private final String mOperator;

      OperatorNodeType(String operator) {
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

    /**
     * Used for constructor expressions, where the parents are the components of a compound
     * expression (a vector or matrix)
     */
    public static final NodeType CONS = new BasicNodeType("");

    /**
     * Used for the ternary operator
     */
    public static final NodeType TERNARY = new BasicNodeType("");

    public static final NodeType ADD = new OperatorNodeType("+");
    public static final NodeType SUB = new OperatorNodeType("-");
    public static final NodeType MUL = new OperatorNodeType("*");
    public static final NodeType DIV = new OperatorNodeType("/");
    public static final NodeType NEG = new OperatorNodeType("-");

    public static final NodeType GT = new OperatorNodeType(">");
    public static final NodeType GEQ = new OperatorNodeType(">=");
    public static final NodeType LT = new OperatorNodeType("<");
    public static final NodeType LEQ = new OperatorNodeType("<=");
    public static final NodeType EQ = new OperatorNodeType("==");
    public static final NodeType NEQ = new OperatorNodeType("!=");

    public static final NodeType AND = new OperatorNodeType("&&");
    public static final NodeType OR = new OperatorNodeType("||");
    public static final NodeType XOR = new OperatorNodeType("^^");
    public static final NodeType NOT = new OperatorNodeType("!");
  }

  /**
   * Returns the GLSL type of the value for this expression if it has one, or Optional.absent()
   * otherwise
   */
  public Optional<GlSlQualifier> getGlSlQualifier();

  /**
   * Returns the {@code NodeType} of this expression of its a compound expression, or
   * Optional.absent() otherwise
   */
  public Optional<NodeType> getNodeType();

  /**
   * Returns a possibly empty list of parents for this expression.
   */
  public ImmutableList<Expression> getParents();

  public Expression<T> getExpressionForTernaryOperator(Bool condition, Expression<T> elseExpression);
}
