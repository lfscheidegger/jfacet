package com.lfscheidegger.jfacet.shade.transform;

import com.google.common.collect.Lists;
import com.lfscheidegger.jfacet.shade.expression.Matrix4;
import com.lfscheidegger.jfacet.shade.expression.Vector4;

import java.util.List;

public abstract class AbstractTransform4 implements Transform<Matrix4, Vector4> {

  private final Matrix4 mTransformMatrix;

  private final List<Transform<Matrix4, Vector4>> mQueuedTransforms;

  public AbstractTransform4(Matrix4 transformMatrix) {
    mTransformMatrix = transformMatrix;

    mQueuedTransforms = Lists.newArrayList();
    mQueuedTransforms.add(this);
  }

  @Override
  public Vector4 apply(Vector4 exp) {
    Matrix4 mat = mQueuedTransforms.get(0).getMatrix();

    for (int i = 1; i < mQueuedTransforms.size(); i++) {
      mat = mat.mul(mQueuedTransforms.get(i).getMatrix());
    }

    mQueuedTransforms.clear();
    mQueuedTransforms.add(this);

    return mat.transform(exp);
  }

  @Override
  public Transform<Matrix4, Vector4> apply(Transform<Matrix4, Vector4> other) {
    mQueuedTransforms.add(other);

    return this;
  }

  @Override
  public Matrix4 getMatrix() {
    return mTransformMatrix;
  }
}