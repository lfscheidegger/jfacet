package com.lfscheidegger.jfacet.shade.transform;

import com.google.common.collect.Lists;
import com.lfscheidegger.jfacet.shade.expression.Mat4;
import com.lfscheidegger.jfacet.shade.expression.Vec4;

import java.util.List;

public class Transform4 {

  private final Mat4 mTransformMatrix;

  private final List<Transform4> mQueuedTransforms;

  public Transform4(Mat4 transformMatrix) {
    mTransformMatrix = transformMatrix;

    mQueuedTransforms = Lists.newArrayList();
    mQueuedTransforms.add(this);
  }

  public Vec4 apply(Vec4 exp) {
    Mat4 mat = mQueuedTransforms.get(0).getMatrix();

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

  public Mat4 getMatrix() {
    return mTransformMatrix;
  }
}