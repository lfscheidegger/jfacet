package com.lfscheidegger.jfacet.shade.transform;

import com.google.common.collect.Lists;
import com.lfscheidegger.jfacet.shade.expression.Mat3;
import com.lfscheidegger.jfacet.shade.expression.Vec3;

import java.util.List;

public class Transform3 {

  private final Mat3 mTransformMatrix;

  private final List<Transform3> mQueuedTransforms;

  public Transform3(Mat3 transformMatrix) {
    mTransformMatrix = transformMatrix;

    mQueuedTransforms = Lists.newArrayList();
    mQueuedTransforms.add(this);
  }

  public Vec3 apply(Vec3 exp) {
    Mat3 mat = mQueuedTransforms.get(0).getMatrix();

    for (int i = 1; i < mQueuedTransforms.size(); i++) {
      mat = mat.times(mQueuedTransforms.get(i).getMatrix());
    }

    mQueuedTransforms.clear();
    mQueuedTransforms.add(this);

    return mat.times(exp);
  }

  public Transform3 apply(Transform3 other) {
    mQueuedTransforms.add(other);

    return this;
  }

  public Mat3 getMatrix() {
    return mTransformMatrix;
  }
}