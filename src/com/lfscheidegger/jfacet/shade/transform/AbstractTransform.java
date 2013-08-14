package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTransform implements Transform {

  private final MatrixExpression mTransformMatrix;

  private final List<Transform> mQueuedTransforms;

  public AbstractTransform(MatrixExpression transformMatrix) {
    mTransformMatrix = transformMatrix;

    mQueuedTransforms = new ArrayList<Transform>();
    mQueuedTransforms.add(this);
  }

  @Override
  public VectorExpression apply(Expression exp) {
    VectorExpression result = Shade.fill(exp, new Vector(0, 0, 0, 1));

    MatrixExpression mat = mQueuedTransforms.get(0).getMatrix();

    for (int i = 1; i < mQueuedTransforms.size(); i++) {
      mat = mat.mul(mQueuedTransforms.get(i).getMatrix());
    }

    mQueuedTransforms.clear();
    mQueuedTransforms.add(this);

    return mat.mul(result);
  }

  @Override
  public Transform apply(Transform other) {
    mQueuedTransforms.add(other);

    return this;
  }

  @Override
  public MatrixExpression getMatrix() {
    return mTransformMatrix;
  }
}
