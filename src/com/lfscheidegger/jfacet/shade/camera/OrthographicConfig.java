// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.shade.camera;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Mat4;
import com.lfscheidegger.jfacet.shade.expression.Real;

public class OrthographicConfig implements CameraConfig {

  private Real mLeft, mRight, mBottom, mTop, mNear, mFar;

  public OrthographicConfig() {
    mLeft = Shade.constant(-1); mRight = Shade.constant(1);
    mBottom = Shade.constant(-1); mTop = Shade.constant(1);
    mNear = Shade.constant(1); mFar = Shade.constant(-1);
  }

  public OrthographicConfig setLeft(Real left) {
    mLeft = left;
    return this;
  }

  public OrthographicConfig setLeft(float left) {
    mLeft = Shade.constant(left);
    return this;
  }

  public OrthographicConfig setRight(Real right) {
    mRight = right;
    return this;
  }

  public OrthographicConfig setRight(float right) {
    mRight = Shade.constant(right);
    return this;
  }

  public OrthographicConfig setBottom(Real bottom) {
    mBottom = bottom;
    return this;
  }

  public OrthographicConfig setBottom(float bottom) {
    mBottom = Shade.constant(bottom);
    return this;
  }

  public OrthographicConfig setTop(Real top) {
    mTop = top;
    return this;
  }

  public OrthographicConfig setTop(float top) {
    mTop = Shade.constant(top);
    return this;
  }

  public OrthographicConfig setNear(Real near) {
    mNear = near;
    return this;
  }

  public OrthographicConfig setNear(float near) {
    mNear = Shade.constant(near);
    return this;
  }

  public OrthographicConfig setFar(Real far) {
    mFar = far;
    return this;
  }

  public OrthographicConfig setFar(float far) {
    mFar = Shade.constant(far);
    return this;
  }

  @Override
  public Mat4 getMatrix() {
    Real tx = mRight.plus(mLeft).div(mRight.minus(mLeft));
    Real ty = mTop.plus(mBottom).div(mTop.minus(mBottom));
    Real tz = mFar.plus(mNear).div(mFar.minus(mNear));

    Real x = Shade.constant(2).div(mRight.minus(mLeft));
    Real y = Shade.constant(2).div(mTop.minus(mBottom));
    Real z = Shade.constant(-2).div(mFar.minus(mNear));

    return Shade.mat(
        Shade.vec(x, 0, 0, 0),
        Shade.vec(0, y, 0, 0),
        Shade.vec(0, 0, z, 0),
        Shade.vec(tx, ty, tz, 1));
  }
}
