package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Type;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.*;
import com.lfscheidegger.jfacet.utils.StringUtils;

import java.util.Arrays;

public class BVector3 extends AbstractExpression<BVector3.Primitive> {

  public static final class Primitive {

    private final boolean[] mValues;

    public Primitive(boolean x, boolean y, boolean z) {
      mValues = new boolean[] {x, y, z};
    }

    private Primitive(boolean[] other) {
      mValues = other;
    }

    public boolean getX() {
      return mValues[0];
    }

    public boolean getY() {
      return mValues[1];
    }

    public boolean getZ() {
      return mValues[2];
    }

    public boolean get(int idx) {
      return mValues[idx];
    }

    public boolean any() {
      return mValues[0] || mValues[1] || mValues[2];
    }

    public boolean all() {
      return mValues[0] && mValues[1] || mValues[2];
    }

    public Primitive not() {
      return new Primitive(new boolean[]{!mValues[0], !mValues[1], !mValues[2]});
    }

    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Primitive)) {
        return false;
      }

      return Arrays.equals(mValues, ((Primitive) other).mValues);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(mValues);
    }

    @Override
    public String toString() {
      return StringUtils.toStringHelper(Type.BVEC3_T)
          .addValue(mValues[0])
          .addValue(mValues[1])
          .addValue(mValues[2])
          .toString();
    }
  }

  public BVector3(boolean x, boolean y, boolean z) {
    this(ImmutableList.<Expression>of(), new ConstantEvaluator<Primitive>(new Primitive(x, y, z)));
  }

  public BVector3(Bool x, Bool y, Bool z) {
    this(ImmutableList.<Expression>of(x, y, z), new ConstructorEvaluator<Primitive>());
  }

  public BVector3(ImmutableList<Expression> parents, Evaluator<Primitive> evaluator) {
    this(GlSlType.DEFAULT_T, parents, evaluator);
  }

  public BVector3(GlSlType glSlType, Evaluator<Primitive> evaluator) {
    this(glSlType, ImmutableList.<Expression>of(), evaluator);
  }

  private BVector3(GlSlType glSlType, ImmutableList<Expression> parents, Evaluator<Primitive> evaluator) {
    super(Type.BVEC3_T, glSlType, parents, evaluator);
  }

  public Bool getX() {
    return get(0);
  }

  public Bool getY() {
    return get(1);
  }

  public Bool getZ() {
    return get(2);
  }

  public Bool get(int idx) {
    Preconditions.checkState(idx < 3);
    return new Bool(ImmutableList.<Expression>of(this), new ComponentEvaluator<Boolean>(idx));
  }

  public Bool any() {
    return new Bool(
        ImmutableList.<Expression>of(this),
        new FunctionEvaluator<Boolean>(Type.BOOL_T, "any") {
          @Override
          public Boolean evaluate(Expression<Boolean> expression) {
            BVector3 parent = (BVector3)expression.getParents().get(0);
            return parent.evaluate().any();
          }
        });
  }

  public Bool all() {
    return new Bool(
        ImmutableList.<Expression>of(this),
        new FunctionEvaluator<Boolean>(Type.BOOL_T, "all") {
          @Override
          public Boolean evaluate(Expression<Boolean> expression) {
            BVector3 parent = (BVector3)expression.getParents().get(0);
            return parent.evaluate().all();
          }
        });
  }

  public BVector3 not() {
    return new BVector3(
        ImmutableList.<Expression>of(this),
        new FunctionEvaluator<Primitive>(Type.BVEC3_T, "not") {
          @Override
          public Primitive evaluate(Expression<Primitive> expression) {
            BVector3 parent = (BVector3)expression.getParents().get(0);
            return parent.evaluate().not();
          }
        });
  }
}
