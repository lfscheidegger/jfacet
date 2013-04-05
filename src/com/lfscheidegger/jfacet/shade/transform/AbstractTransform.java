package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.Mat4Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec4Exp;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

public abstract class AbstractTransform implements Transform {

  private final Mat4Exp mTransformMatrix;

  public AbstractTransform(Mat4Exp transformMatrix) {
    mTransformMatrix = transformMatrix;
  }

  @Override
  public Vec4Exp apply(Expression exp) {
    Vec4Exp filled = Shade.fill(exp, new Vec4(0, 0, 0, 1));

    return Shade.mul(mTransformMatrix, filled);
  }
}
