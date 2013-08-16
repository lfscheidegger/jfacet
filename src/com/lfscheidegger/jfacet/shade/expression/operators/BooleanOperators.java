package com.lfscheidegger.jfacet.shade.expression.operators;

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
}
