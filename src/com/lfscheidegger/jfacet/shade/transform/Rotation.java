package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Mat4Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec3Exp;

public class Rotation extends AbstractTransform {

  public Rotation(float angle, Vec3Exp axis) {
    this(Shade.constant(angle), axis);
  }

  public Rotation(FloatExp angle, Vec3Exp axis) {
    super(getMatrix(angle, axis));
  }

  private static Mat4Exp getMatrix(FloatExp angle, Vec3Exp axis) {
    axis = axis.normalize();

    FloatExp s = angle.sin();
    FloatExp c = angle.cos();

    FloatExp t = Shade.sub(1, c);
    FloatExp x = axis.get(0);
    FloatExp y = axis.get(1);
    FloatExp z = axis.get(2);

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
        Shade.vec(0,0,0,1));
  }
}
