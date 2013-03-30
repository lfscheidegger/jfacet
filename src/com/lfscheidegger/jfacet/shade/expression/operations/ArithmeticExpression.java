package com.lfscheidegger.jfacet.shade.expression.operations;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.SupportsBasicArithmetic;

/**
 * {@code ArithmeticExpression} encompasses {@code Expression} objects for basic operations that can be
 * applied between two objects of type T or one object of type T and a float.
 */
public class ArithmeticExpression extends AbstractExpression {

  /**
   * Interface for evaluators that actually carry out this expression.
   */
  private interface BasicArithmeticEvaluator {

    /**
     * Evaluate the result of applying this operation between two floats.
     */
    public float evalFloat(float left, float right);

    /**
     * Evaluate the result of applying this operation between an instance of T and another Object.
     * The object can be either another instance of T or a {@code Float} instance.
     */
    <T extends SupportsBasicArithmetic<T>> T evalObject(T left, Object right);

    /**
     * Returns the symbol for the binary operation implemented by this evaluator.
     */
    public String getOperationSymbol();

  }

  /**
   * Implementation of {@code BasicArithmeticEvaluator} for addition
   */
  public static class AddExpressionEvaluator implements BasicArithmeticEvaluator {
    @Override
    public float evalFloat(float left, float right) {
      return left + right;
    }

    @Override
    public <T extends SupportsBasicArithmetic<T>> T evalObject(T left, Object right) {
      if (right instanceof Float) {
        return left.add((Float)right);
      } else {
        return left.add((T)right);
      }
    }

    @Override
    public String getOperationSymbol() {
      return "+";
    }
  }

  /**
   * Implementation of {@code BasicArithmeticEvaluator} for subtraction
   */
  public static class SubExpressionEvaluator implements BasicArithmeticEvaluator {
    @Override
    public float evalFloat(float left, float right) {
      return left - right;
    }

    @Override
    public <T extends SupportsBasicArithmetic<T>> T evalObject(T left, Object right) {
      if (right instanceof Float) {
        return left.sub((Float)right);
      } else {
        return left.sub((T)right);
      }
    }

    @Override
    public String getOperationSymbol() {
      return "-";
    }
  }

  /**
   * Implementation of {@code BasicArithmeticEvaluator} for multiplication
   */
  public static class MulExpressionEvaluator implements BasicArithmeticEvaluator {
    @Override
    public float evalFloat(float left, float right) {
      return left * right;
    }

    @Override
    public <T extends SupportsBasicArithmetic<T>> T evalObject(T left, Object right) {
      if (right instanceof Float) {
        return left.mul((Float)right);
      } else {
        return left.mul((T)right);
      }
    }

    @Override
    public String getOperationSymbol() {
      return "*";
    }
  }

  /**
   * Implementation of {@code BasicArithmeticEvaluator} for division
   */
  public static class DivExpressionEvaluator implements BasicArithmeticEvaluator {
    @Override
    public float evalFloat(float left, float right) {
      return left / right;
    }

    @Override
    public <T extends SupportsBasicArithmetic<T>> T evalObject(T left, Object right) {
      if (right instanceof Float) {
        return left.div((Float)right);
      } else {
        return left.div((T)right);
      }
    }

    @Override
    public String getOperationSymbol() {
      return "/";
    }
  }

  public static Expression getAddExpression(Expression left, Expression right) {
    return new ArithmeticExpression(left, right, new AddExpressionEvaluator());
  }

  public static Expression getSubExpression(Expression left, Expression right) {
    return new ArithmeticExpression(left, right, new SubExpressionEvaluator());
  }

  public static Expression getMulExpression(Expression left, Expression right) {
    return new ArithmeticExpression(left, right, new MulExpressionEvaluator());
  }

  public static Expression getDivExpression(Expression left, Expression right) {
    return new ArithmeticExpression(left, right, new DivExpressionEvaluator());
  }

  private final BasicArithmeticEvaluator mEvaluator;

  private final Type mLeftType;
  private final Type mRightType;

  public ArithmeticExpression(Expression left, Expression right, BasicArithmeticEvaluator evaluator) {
    super(getResultType(left.getType(), right.getType()), ImmutableList.<Expression>of(left, right));

    mEvaluator = evaluator;
    mLeftType = left.getType();
    mRightType = right.getType();
  }

  @Override
  public Object evaluate() {
    if (mLeftType == Type.FLOAT_T) {
      return evaluateFloat();
    } else {
      SupportsBasicArithmetic result = evaluateForType(mLeftType);
      return result;
    }
  }

  @Override
  public String getGlSlExpression() {
    return
        getParents().get(0).getGlSlExpression() +
        " " + mEvaluator.getOperationSymbol() + " " +
        getParents().get(1).getGlSlExpression();
  }

  @Override
  public String getGlSlExpression(CompilationContext context) {
    return
        context.getName(getParents().get(0)) +
        " " + mEvaluator.getOperationSymbol() + " " +
        context.getName(getParents().get(1));
  }

  private static Type getResultType(Type leftType, Type rightType) {
    if (leftType == Type.FLOAT_T && rightType == Type.FLOAT_T) return Type.FLOAT_T;
    else if (leftType == Type.VEC2_T && rightType == Type.FLOAT_T) return Type.VEC2_T;
    else if (leftType == Type.VEC2_T && rightType == Type.VEC2_T) return Type.VEC2_T;
    else if (leftType == Type.VEC3_T && rightType == Type.FLOAT_T) return Type.VEC3_T;
    else if (leftType == Type.VEC3_T && rightType == Type.VEC3_T) return Type.VEC3_T;
    else if (leftType == Type.VEC4_T && rightType == Type.FLOAT_T) return Type.VEC4_T;
    else if (leftType == Type.VEC4_T && rightType == Type.VEC4_T) return Type.VEC4_T;
    else if (leftType == Type.MAT2_T && rightType == Type.FLOAT_T) return Type.MAT2_T;
    else if (leftType == Type.MAT2_T && rightType == Type.MAT2_T) return Type.MAT2_T;
    else if (leftType == Type.MAT3_T && rightType == Type.FLOAT_T) return Type.MAT3_T;
    else if (leftType == Type.MAT3_T && rightType == Type.MAT3_T) return Type.MAT3_T;
    else if (leftType == Type.MAT4_T && rightType == Type.FLOAT_T) return Type.MAT4_T;
    else if (leftType == Type.MAT4_T && rightType == Type.MAT4_T) return Type.MAT4_T;

    throw new IllegalArgumentException("Incompatible primitives for addition: " + leftType + " " + rightType);
  }

  private float evaluateFloat() {
    Preconditions.checkState(mRightType == Type.FLOAT_T, "Cannot add FLOAT_T with " + mRightType);
    return mEvaluator.evalFloat((Float)getParents().get(0).evaluate(), (Float)getParents().get(1).evaluate());
  }

  private SupportsBasicArithmetic evaluateForType(Type type) {
    if (mRightType != Type.FLOAT_T && mRightType != type) {
      throw new IllegalArgumentException("Cannot add " + mLeftType + " with " + mRightType);
    }

    return mEvaluator.evalObject(
        ((SupportsBasicArithmetic)getParents().get(0).evaluate()),
        getParents().get(1).evaluate());
  }
}