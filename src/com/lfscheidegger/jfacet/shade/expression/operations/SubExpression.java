package com.lfscheidegger.jfacet.shade.expression.operations;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.primitives.SupportsBasicArithmetic;

public class SubExpression extends AbstractExpression {

  private final Type mLeftType;
  private final Type mRightType;

  public SubExpression(Expression left, Expression right) {
    super(getResultType(left.getType(), right.getType()), ImmutableList.<Expression>of(left, right));

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

    throw new IllegalArgumentException("Incompatible primitives for subtraction: " + leftType + " " + rightType);
  }

  private float evaluateFloat() {
    Preconditions.checkState(mRightType == Type.FLOAT_T, "Cannot subtract FLOAT_T with " + mRightType);
    return (Float)getParents().get(0).evaluate() - (Float)getParents().get(1).evaluate();
  }

  private <T extends SupportsBasicArithmetic<T>> T evaluateForType(Type type) {
    if (mRightType == Type.FLOAT_T) {
      return ((T)getParents().get(0).evaluate()).sub((Float) getParents().get(1).evaluate());
    } else if (mRightType == type) {
      return ((T)getParents().get(0).evaluate()).sub((T) getParents().get(1).evaluate());
    } else {
      throw new IllegalArgumentException("Cannot subtract " + mLeftType + " from " + mRightType);
    }
  }
}
