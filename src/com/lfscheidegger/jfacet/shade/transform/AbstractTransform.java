package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.Mat4Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec4Exp;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTransform implements Transform {

  private final Mat4Exp mTransformMatrix;

  private final List<Transform> mQueuedTransforms;

  public AbstractTransform(Mat4Exp transformMatrix) {
    mTransformMatrix = transformMatrix;

    mQueuedTransforms = new ArrayList<Transform>();
    mQueuedTransforms.add(this);
  }

  @Override
  public Vec4Exp apply(Expression exp) {
    Vec4Exp result = Shade.fill(exp, new Vec4(0, 0, 0, 1));
    for (int i = mQueuedTransforms.size() - 1; i >= 0; i--) {
      result = mQueuedTransforms.get(i).getMatrix().mul(result);
    }

    mQueuedTransforms.clear();
    mQueuedTransforms.add(this);
    return result;
  }

  @Override
  public Transform apply(Transform other) {
    mQueuedTransforms.add(other);

    return this;
  }

  @Override
  public Mat4Exp getMatrix() {
    return mTransformMatrix;
  }
}
