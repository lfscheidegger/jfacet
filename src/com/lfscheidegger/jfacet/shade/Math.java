package com.lfscheidegger.jfacet.shade;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.FloatExpression;
import com.lfscheidegger.jfacet.shade.expression.MatrixExpression;
import com.lfscheidegger.jfacet.shade.expression.VectorExpression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.FunctionEvaluator;
import com.lfscheidegger.jfacet.shade.primitives.Matrix;
import com.lfscheidegger.jfacet.shade.primitives.Vector;

public final class Math {

  public static FloatExpression sqrt(FloatExpression param) {
    return new FloatExpression(ImmutableList.<Expression>of(param), new FunctionEvaluator<Float>(Type.FLOAT_T, "sqrt") {
          @Override
          public Float evaluate(Expression expression) {
            FloatExpression parent = (FloatExpression)expression.getParents().get(0);
            return (float)java.lang.Math.sqrt(parent.evaluate());
          }
        });
  }

  public static FloatExpression sin(FloatExpression param) {
    return new FloatExpression(ImmutableList.<Expression>of(param), new FunctionEvaluator<Float>(Type.FLOAT_T, "sin") {
          @Override
          public Float evaluate(Expression expression) {
            FloatExpression parent = (FloatExpression)expression.getParents().get(0);
            return (float)java.lang.Math.sin(parent.evaluate());
          }
        });
  }

  public static FloatExpression cos(FloatExpression param) {
    return new FloatExpression(ImmutableList.<Expression>of(param), new FunctionEvaluator<Float>(Type.FLOAT_T, "cos") {
          @Override
          public Float evaluate(Expression expression) {
            FloatExpression parent = (FloatExpression)expression.getParents().get(0);
            return (float)java.lang.Math.cos(parent.evaluate());
          }
        });
  }

  public static FloatExpression radians(FloatExpression param) {
    return param.mul((float)(java.lang.Math.PI / 180.0));
  }

  public static VectorExpression normalize(VectorExpression vec) {
    return new VectorExpression(
        vec.getType(),
        ImmutableList.<Expression>of(vec),
        new FunctionEvaluator<Vector>(vec.getType(), "normalize") {
          @Override
          public Vector evaluate(Expression expression) {
            VectorExpression parent = (VectorExpression)expression.getParents().get(0);
            return parent.evaluate().normalize();
          }
        });
  }

  public static FloatExpression dot(VectorExpression left, VectorExpression right) {
    return new FloatExpression(ImmutableList.<Expression>of(left, right), new FunctionEvaluator<Float>(Type.FLOAT_T, "dot") {
      @Override
      public Float evaluate(Expression expression) {
        VectorExpression left = (VectorExpression)expression.getParents().get(0);
        VectorExpression right = (VectorExpression)expression.getParents().get(1);

        return left.evaluate().dot(right.evaluate());
      }
    });
  }

  public static VectorExpression cross(VectorExpression left, VectorExpression right) {
    return new VectorExpression(
        Type.VEC3_T,
        ImmutableList.<Expression>of(left, right),
        new FunctionEvaluator<Vector>(Type.VEC3_T, "cross") {
          @Override
          public Vector evaluate(Expression expression) {
            VectorExpression left = (VectorExpression)expression.getParents().get(0);
            VectorExpression right = (VectorExpression)expression.getParents().get(1);
            return left.evaluate().cross(right.evaluate());
          }
        });
  }

  public static FloatExpression determinant(MatrixExpression mat) {
    return new FloatExpression(ImmutableList.<Expression>of(mat), new FunctionEvaluator<Float>(Type.FLOAT_T, "determinant") {
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

