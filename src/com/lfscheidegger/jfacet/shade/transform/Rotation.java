package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.FloatExpression;
import com.lfscheidegger.jfacet.shade.expression.MatrixExpression;
import com.lfscheidegger.jfacet.shade.expression.VectorExpression;

public class Rotation extends AbstractTransform {

  public Rotation(FloatExpression angle, VectorExpression axis) {
    super(getMatrix(angle, axis));
  }

  private static MatrixExpression getMatrix(FloatExpression angle, VectorExpression axis) {
    axis = axis.normalize();

    FloatExpression s = angle.sin();
    FloatExpression c = angle.cos();

    FloatExpression t = Shade.sub(1, c);
    FloatExpression x = axis.get(0);
    FloatExpression y = axis.get(1);
    FloatExpression z = axis.get(2);

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
