package com.lfscheidegger.jfacet.shade;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.MatrixExpression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.FunctionEvaluator;

public final class Math {

  public static Real determinant(MatrixExpression mat) {
    return new Real(ImmutableList.<Expression>of(mat), new FunctionEvaluator<Float>(Type.FLOAT_T, "determinant") {
      @Override
      public Float evaluate(Expression<Float> expression) {
        MatrixExpression parent = (MatrixExpression)expression.getParents().get(0);
        return parent.evaluate().determinant();
      }
    });
  }
  public static MatrixExpression inverse(MatrixExpression mat) {
    return new MatrixExpression(
        mat.getType(),
        ImmutableList.<Expression>of(mat),
        new FunctionEvaluator<Matrix>(mat.getType(), "inverse") {
          @Override
          public Matrix evaluate(Expression<Matrix> expression) {
            MatrixExpression parent = (MatrixExpression)expression.getParents().get(0);
            return parent.evaluate().inverse();
          }
        });
  }

  public static MatrixExpression transpose(MatrixExpression mat) {
    return new MatrixExpression(
        mat.getType(),
        ImmutableList.<Expression>of(mat), new FunctionEvaluator<Matrix>(mat.getType(), "transpose") {
      @Override
      public Matrix evaluate(Expression<Matrix> expression) {
        MatrixExpression parent = (MatrixExpression)expression.getParents().get(0);
        return parent.evaluate().transpose();
      }
    });
  }
}

