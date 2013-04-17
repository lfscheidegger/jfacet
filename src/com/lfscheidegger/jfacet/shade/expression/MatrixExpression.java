package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ComponentEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.ConstantEvaluator;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;
import com.lfscheidegger.jfacet.shade.primitives.Matrix;
import com.lfscheidegger.jfacet.shade.primitives.Vector;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.MatrixLike;
import com.lfscheidegger.jfacet.shade.primitives.interfaces.VectorLike;

public final class MatrixExpression extends AbstractExpression<Matrix> implements MatrixLike {

  public MatrixExpression(Matrix mat) {
    this(Type.getMatTypeForDimension(mat.getDimension()), new ConstantEvaluator<Matrix>(mat));
  }

  public MatrixExpression(Type type, Evaluator<Matrix> evaluator) {
    this(type, ImmutableList.<Expression>of(), evaluator);
  }

  public MatrixExpression(Type type, ImmutableList<Expression> parents, Evaluator<Matrix> evaluator) {
    this(type, GlSlType.DEFAULT_T, parents, evaluator);
  }

  public MatrixExpression(Type type, GlSlType glSlType, Evaluator<Matrix> evaluator) {
    this(type, glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  public MatrixExpression(Type type, GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Matrix> evaluator) {
    super(type, glSlType, parents, evaluator);
  }

  public VectorExpression getC0() {
    return get(0);
  }

  public VectorExpression getC1() {
    return get(1);
  }

  public VectorExpression getC2() {
    return get(2);
  }

  public VectorExpression getC3() {
    return get(3);
  }

  public VectorExpression get(int idx) {
    Preconditions.checkState(idx < getType().getDimension());

    return new VectorExpression(
        Type.getVecTypeForDimension(getType().getDimension()),
        ImmutableList.<Expression>of(this),
        new ComponentEvaluator<Vector>(idx));
  }

  public MatrixExpression add(float other) { return Shade.add(this, other); }
  public MatrixExpression add(FloatExpression other) { return Shade.add(this, other); }
  public MatrixExpression add(MatrixLike other) { return Shade.add(this, other); }

  public MatrixExpression sub(float other) { return Shade.sub(this, other); }
  public MatrixExpression sub(FloatExpression other) { return Shade.sub(this, other); }
  public MatrixExpression sub(MatrixLike other) { return Shade.sub(this, other); }

  public MatrixExpression mul(float other) { return Shade.mul(this, other); }
  public MatrixExpression mul(FloatExpression other) { return Shade.mul(this, other); }
  public MatrixExpression mul(MatrixLike other) { return Shade.mul(this, other); }

  public VectorExpression mul(VectorLike other) { return Shade.mul(this, other); }

  public MatrixExpression div(float other) { return Shade.div(this, other); }
  public MatrixExpression div(FloatExpression other) { return Shade.div(this, other); }
  public MatrixExpression div(MatrixLike other) { return Shade.div(this, other); }

  public FloatExpression determinant() { return Shade.determinant(this); }

  public MatrixExpression inverse() { return Shade.inverse(this); }

  public MatrixExpression transpose() { return Shade.transpose(this); }
}
