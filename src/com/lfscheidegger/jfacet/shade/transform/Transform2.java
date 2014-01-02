package com.lfscheidegger.jfacet.shade.transform;

import com.google.common.collect.Lists;
import com.lfscheidegger.jfacet.shade.expression.Mat2;
import com.lfscheidegger.jfacet.shade.expression.Vec2;

import java.util.List;

public class Transform2 {

  private final Mat2 mTransformMatrix;

  private final List<Transform2> mQueuedTransforms;

  public Transform2(Mat2 transformMatrix) {
    mTransformMatrix = transformMatrix;

    mQueuedTransforms = Lists.newArrayList();
    mQueuedTransforms.add(this);
  }

  public Vec2 apply(Vec2 exp) {
    Mat2 mat = mQueuedTransforms.get(0).getMatrix();

    for (int i = 1; i < mQueuedTransforms.size(); i++) {
      mat = mat.times(mQueuedTransforms.get(i).getMatrix());
    }

    mQueuedTransforms.clear();
    mQueuedTransforms.add(this);

    return mat.times(exp);
  }

  public Transform2 apply(Transform2 other) {
    mQueuedTransforms.add(other);

    return this;
  }

  public Mat2 getMatrix() {
    return mTransformMatrix;
  }
}
