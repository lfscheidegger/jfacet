package com.lfscheidegger.jfacet.shade.transform;

import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.Mat4Exp;
import com.lfscheidegger.jfacet.shade.expression.Vec4Exp;

public interface Transform {

  public Vec4Exp apply(Expression exp);

  public Transform apply(Transform other);

  public Mat4Exp getMatrix();
}
