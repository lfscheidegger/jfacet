package com.lfscheidegger.jfacet.shade.transform;

import com.google.common.collect.Lists;
import com.lfscheidegger.jfacet.shade.expression.matrix.Matrix2;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;

import java.util.List;

public abstract class Transform2 {

  private final Matrix2 mTransformMatrix;

  private final List<Transform2> mQueuedTransforms;

  public Transform2(Matrix2 transformMatrix) {
    mTransformMatrix = transformMatrix;

    mQueuedTransforms = Lists.newArrayList();
    mQueuedTransforms.add(this);
  }

  public Vector2 apply(Vector2 exp) {
    Matrix2 mat = mQueuedTransforms.get(0).getMatrix();

    for (int i = 1; i < mQueuedTransforms.size(); i++) {
      mat = mat.mul(mQueuedTransforms.get(i).getMatrix());
    }

    mQueuedTransforms.clear();
    mQueuedTransforms.add(this);

    return mat.mul(exp);
  }

  public Transform2 apply(Transform2 other) {
    mQueuedTransforms.add(other);

    return this;
  }

  public Matrix2 getMatrix() {
    return mTransformMatrix;
  }
}
