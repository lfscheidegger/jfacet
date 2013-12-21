package com.lfscheidegger.jfacet.shade.transform;

import com.google.common.collect.Lists;
import com.lfscheidegger.jfacet.shade.expression.matrix.Matrix4;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;

import java.util.List;

public abstract class Transform4 {

  private final Matrix4 mTransformMatrix;

  private final List<Transform4> mQueuedTransforms;

  public Transform4(Matrix4 transformMatrix) {
    mTransformMatrix = transformMatrix;

    mQueuedTransforms = Lists.newArrayList();
    mQueuedTransforms.add(this);
  }

  public Vector4 apply(Vector4 exp) {
    Matrix4 mat = mQueuedTransforms.get(0).getMatrix();

    for (int i = 1; i < mQueuedTransforms.size(); i++) {
      mat = mat.mul(mQueuedTransforms.get(i).getMatrix());
    }

    mQueuedTransforms.clear();
    mQueuedTransforms.add(this);

    return mat.mul(exp);
  }

  public Transform4 apply(Transform4 other) {
    mQueuedTransforms.add(other);

    return this;
  }

  public Matrix4 getMatrix() {
    return mTransformMatrix;
  }
}