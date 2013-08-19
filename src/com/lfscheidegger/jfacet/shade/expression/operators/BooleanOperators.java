package com.lfscheidegger.jfacet.shade.expression.operators;

import com.lfscheidegger.jfacet.shade.expression.SupportsBasicArithmetic;

/**
 * Created with IntelliJ IDEA.
 * User: luiz
 * Date: 8/16/13
 * Time: 6:32 PM
 * To change this template use File | Settings | File Templates.
 */
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

  public static Operator<SupportsBasicArithmetic, SupportsBasicArithmetic, Boolean> forEqualsVector() {
    return new NamedOperator<SupportsBasicArithmetic, SupportsBasicArithmetic, Boolean>("==") {
      @Override
      public Boolean op(SupportsBasicArithmetic left, SupportsBasicArithmetic right) {
        return left.equals(right);
      }
    };
  }

  public static Operator<SupportsBasicArithmetic, SupportsBasicArithmetic, Boolean> forNotEqualsVector() {
    return new NamedOperator<SupportsBasicArithmetic, SupportsBasicArithmetic, Boolean>("!=") {
      @Override
      public Boolean op(SupportsBasicArithmetic left, SupportsBasicArithmetic right) {
        return !left.equals(right);
      }
    };
  }
}
