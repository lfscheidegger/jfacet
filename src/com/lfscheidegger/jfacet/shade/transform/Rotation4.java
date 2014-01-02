package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Mat4;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.Vec3;

public class Rotation4 extends Transform4 {

  public Rotation4(Real angle, Vec3 axis) {
    super(getMatrix(angle, axis));
  }

  private static Mat4 getMatrix(Real angle, Vec3 axis) {
    axis = axis.normalize();

    Real s = angle.sin();
    Real c = angle.cos();

    Real t = Shade.constant(1).minus(c);
    Real x = axis.get(0);
    Real y = axis.get(1);
    Real z = axis.get(2);

    return Shade.mat(
        Shade.vec(x.times(x).times(t).plus(c),
            y.times(x).times(t).plus(z.times(s)),
            z.times(x).times(t).minus(y.times(s)),
            0),
        Shade.vec(x.times(y).times(t).minus(z.times(s)),
            y.times(y).times(t).plus(c),
            z.times(y).times(t).plus(x.times(s)),
            0),
        Shade.vec(x.times(z).times(t).plus(y.times(s)),
            y.times(z).times(t).minus(x.times(s)),
            z.times(z).times(t).plus(c),
            0),
        Shade.vec(0, 0, 0, 1));
  }
}
