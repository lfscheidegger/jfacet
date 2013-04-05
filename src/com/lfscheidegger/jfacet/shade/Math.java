package com.lfscheidegger.jfacet.shade;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.FunctionEvaluator;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec2Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec3Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec4Exp;
import com.lfscheidegger.jfacet.shade.primitives.Vec2;
import com.lfscheidegger.jfacet.shade.primitives.Vec3;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

public class Math {
  public static FloatExp sqrt(FloatExp param) {
    return new FloatExp(Type.FLOAT_T, GlSlType.DEFAULT_T, ImmutableList.<Expression>of(param),
        new FunctionEvaluator<Float>(Type.FLOAT_T, "sqrt") {
          @Override
          public Float evaluate(Expression expression) {
            FloatExp parent = (FloatExp)expression.getParents().get(0);
            return (float)java.lang.Math.sqrt(parent.evaluate());
          }
        });
  }

  public static Vec2Exp normalize(Vec2Exp vec) {
    return new Vec2Exp(Type.VEC2_T, GlSlType.DEFAULT_T, ImmutableList.<Expression>of(vec),
        new FunctionEvaluator<Vec2>(Type.VEC2_T, "normalize") {
          @Override
          public Vec2 evaluate(Expression expression) {
            Vec2Exp parent = (Vec2Exp)expression.getParents().get(0);
            float norm = (float)java.lang.Math.sqrt(
                parent.getX().evaluate() * parent.getX().evaluate() +
                    parent.getY().evaluate() * parent.getY().evaluate());
            return parent.evaluate().div(norm);
          }
        });
  }

  public static Vec3Exp normalize(Vec3Exp vec) {
    return new Vec3Exp(Type.VEC3_T, GlSlType.DEFAULT_T, ImmutableList.<Expression>of(vec),
        new FunctionEvaluator<Vec3>(Type.VEC3_T, "normalize") {
          @Override
          public Vec3 evaluate(Expression expression) {
            Vec3Exp parent = (Vec3Exp)expression.getParents().get(0);
            float norm = (float)java.lang.Math.sqrt(
                parent.getX().evaluate() * parent.getX().evaluate() +
                parent.getY().evaluate() * parent.getY().evaluate() +
                parent.getZ().evaluate() * parent.getZ().evaluate());
            return parent.evaluate().div(norm);
          }
        });
  }

  public static Vec4Exp normalize(Vec4Exp vec) {
    return new Vec4Exp(Type.VEC4_T, GlSlType.DEFAULT_T, ImmutableList.<Expression>of(vec),
        new FunctionEvaluator<Vec4>(Type.VEC4_T, "normalize") {
          @Override
          public Vec4 evaluate(Expression expression) {
            Vec4Exp parent = (Vec4Exp)expression.getParents().get(0);
            float norm = (float)java.lang.Math.sqrt(
                parent.getX().evaluate() * parent.getX().evaluate() +
                parent.getY().evaluate() * parent.getY().evaluate() +
                parent.getZ().evaluate() * parent.getZ().evaluate() +
                parent.getW().evaluate() * parent.getW().evaluate());
            return parent.evaluate().div(norm);
          }
        });
  }
}
