package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.matrix.Matrix4;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;

public class Rotation4 extends AbstractTransform4 {

  public Rotation4(Real angle, Vector4 axis) {
    super(getMatrix(angle, axis));
  }

  private static Matrix4 getMatrix(Real angle, Vector4 axis) {
    return null;
    /*axis = axis.normalize();

    Real s = angle.sin();
    Real c = angle.cos();

    Real t = Shade.constant(1).sub(c);
    Real x = axis.get(0);
    Real y = axis.get(1);
    Real z = axis.get(2);

    return Shade.mat(
        Shade.vec(x.mul(x).mul(t).add(c),
            y.mul(x).mul(t).add(z.mul(s)),
            z.mul(x).mul(t).sub(y.mul(s)),
            0),
        Shade.vec(x.mul(y).mul(t).sub(z.mul(s)),
            y.mul(y).mul(t).add(c),
            z.mul(y).mul(t).add(x.mul(s)),
            0),
        Shade.vec(x.mul(z).mul(t).add(y.mul(s)),
            y.mul(z).mul(t).sub(x.mul(s)),
            z.mul(z).mul(t).add(c),
            0),
        Shade.vec(0,0,0,1));*/
  }
}
