package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.expression.SupportsBasicArithmetic;

public final class BooleanOperators {

  public static Operator<Float, Float, Boolean> forGreaterThanReal() {
    return new NamedOperator<Float, Float, Boolean>(">") {
      @Override
      public Boolean op(Float left, Float right) {
        return left > right;
      }
    };
  }

  public static Operator<Float, Float, Boolean> forGreaterThanOrEqualReal() {
    return new NamedOperator<Float, Float, Boolean>(">=") {
      @Override
      public Boolean op(Float left, Float right) {
        return left >= right;
      }
    };
  }

  public static Operator<Float, Float, Boolean> forLessThanReal() {
    return new NamedOperator<Float, Float, Boolean>("<") {
      @Override
      public Boolean op(Float left, Float right) {
        return left < right;
      }
    };
  }

  public static Operator<Float, Float, Boolean> forLessThanOrEqualReal() {
    return new NamedOperator<Float, Float, Boolean>("<=") {
      @Override
      public Boolean op(Float left, Float right) {
        return left <= right;
      }
    };
  }

  public static Operator<Float, Float, Boolean> forEqualsReal() {
    return new NamedOperator<Float, Float, Boolean>("==") {
      @Override
      public Boolean op(Float left, Float right) {
        return left.equals(right);
      }
    };
  }

  public static Operator<Float, Float, Boolean> forNotEqualsReal() {
    return new NamedOperator<Float, Float, Boolean>("!=") {
      @Override
      public Boolean op(Float left, Float right) {
        return !left.equals(right);
      }
    };
  }

  public static Operator<SupportsBasicArithmetic, SupportsBasicArithmetic, Boolean> forEqualsObject() {
    return new NamedOperator<SupportsBasicArithmetic, SupportsBasicArithmetic, Boolean>("==") {
      @Override
      public Boolean op(SupportsBasicArithmetic left, SupportsBasicArithmetic right) {
        return left.equals(right);
      }
    };
  }

  public static Operator<SupportsBasicArithmetic, SupportsBasicArithmetic, Boolean> forNotEqualsObject() {
    return new NamedOperator<SupportsBasicArithmetic, SupportsBasicArithmetic, Boolean>("!=") {
      @Override
      public Boolean op(SupportsBasicArithmetic left, SupportsBasicArithmetic right) {
        return !left.equals(right);
      }
    };
  }

  public static Operator<Boolean, Boolean, Boolean> forAnd() {
    return new NamedOperator<Boolean, Boolean, Boolean>("&&") {
      @Override
      public Boolean op(Boolean left, Boolean right) {
        return left && right;
      }
    };
  }

  public static Operator<Boolean, Boolean, Boolean> forOr() {
    return new NamedOperator<Boolean, Boolean, Boolean>("||") {
      @Override
      public Boolean op(Boolean left, Boolean right) {
        return left || right;
      }
    };
  }

  public static Operator<Boolean, Boolean, Boolean> forXor() {
    return new NamedOperator<Boolean, Boolean, Boolean>("^^") {
      @Override
      public Boolean op(Boolean left, Boolean right) {
        return left ^ right;
      }
    };
  }
}
