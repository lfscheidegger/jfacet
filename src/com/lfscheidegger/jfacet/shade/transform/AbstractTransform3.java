package com.lfscheidegger.jfacet.shade.transform;

import com.google.common.collect.Lists;
import com.lfscheidegger.jfacet.shade.expression.Matrix3;
import com.lfscheidegger.jfacet.shade.expression.Vector3;

import java.util.List;

public abstract class AbstractTransform3 implements Transform<Matrix3, Vector3> {

  private final Matrix3 mTransformMatrix;

  private final List<Transform<Matrix3, Vector3>> mQueuedTransforms;

  public AbstractTransform3(Matrix3 transformMatrix) {
    mTransformMatrix = transformMatrix;

    mQueuedTransforms = Lists.newArrayList();
    mQueuedTransforms.add(this);
  }

  @Override
  public Vector3 apply(Vector3 exp) {
    Matrix3 mat = mQueuedTransforms.get(0).getMatrix();

    for (int i = 1; i < mQueuedTransforms.size(); i++) {
      mat = mat.mul(mQueuedTransforms.get(i).getMatrix());
    }

    mQueuedTransforms.clear();
    mQueuedTransforms.add(this);

    return mat.mul(exp);
  }

  @Override
  public Transform<Matrix3, Vector3> apply(Transform<Matrix3, Vector3> other) {
    mQueuedTransforms.add(other);

    return this;
  }

  @Override
  public Matrix3 getMatrix() {
    return mTransformMatrix;
  }
}