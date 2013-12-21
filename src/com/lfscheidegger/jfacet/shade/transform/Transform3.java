package com.lfscheidegger.jfacet.shade.transform;

import com.google.common.collect.Lists;
import com.lfscheidegger.jfacet.shade.expression.matrix.Matrix3;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;

import java.util.List;

public class Transform3 {

  private final Matrix3 mTransformMatrix;

  private final List<Transform3> mQueuedTransforms;

  public Transform3(Matrix3 transformMatrix) {
    mTransformMatrix = transformMatrix;

    mQueuedTransforms = Lists.newArrayList();
    mQueuedTransforms.add(this);
  }

  public Vector3 apply(Vector3 exp) {
    Matrix3 mat = mQueuedTransforms.get(0).getMatrix();

    for (int i = 1; i < mQueuedTransforms.size(); i++) {
      mat = mat.mul(mQueuedTransforms.get(i).getMatrix());
    }

    mQueuedTransforms.clear();
    mQueuedTransforms.add(this);

    return mat.mul(exp);
  }

  public Transform3 apply(Transform3 other) {
    mQueuedTransforms.add(other);

    return this;
  }

  public Matrix3 getMatrix() {
    return mTransformMatrix;
  }
}