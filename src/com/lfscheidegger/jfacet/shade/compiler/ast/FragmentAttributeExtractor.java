package com.lfscheidegger.jfacet.shade.compiler.ast;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.AbstractExpression;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.FloatExpression;
import com.lfscheidegger.jfacet.shade.expression.VectorExpression;

/**
 * {@code ASTProcessor} object responsible for filtering ATTRIBUTE_T expressions from the AST used for
 * fragment colors.
 *
 * This is required because attributes aren't allowed in fragment shaders. What we do instead
 * is we replace that ATTRIBUTE_T expression by an equivalent VARYING_T expression that has the original
 * ATTRIBUTE_T expression as its parent. This way, the varying will take care of interpolating the value
 * of the ATTRIBUTE_T to all fragments.
 */
public class FragmentAttributeExtractor implements ASTProcessor {

  @Override
  public Expression process(Expression expression) {
    if (expression.getGlSlType() == GlSlType.ATTRIBUTE_T) {
      return getVaryingForAttribute(expression);
    }

    // Since uniforms have no parents, and varyings are already correct,
    // we should only deal with defaults.
    else if (expression.getGlSlType() != GlSlType.DEFAULT_T) {
      return expression;
    }

    ((AbstractExpression)expression).resetParents(
        ImmutableList.<Expression>copyOf(
            Collections2.transform(
                expression.getParents(),
                new Function<Expression, Expression>() {
                  @Override
                  public Expression apply(Expression expression) {
                    return process(expression);
                  }
                })));

    return expression;
  }

  private Expression getVaryingForAttribute(Expression attribute) {
    switch(attribute.getType()) {
      case FLOAT_T: return Shade.varying((FloatExpression)attribute);
      case VEC2_T: // fallthrough
      case VEC3_T: // fallthrough
      case VEC4_T: return Shade.varying((VectorExpression)attribute);
    }

    throw new RuntimeException("Cannot get varying for attribute of type: " + attribute.getType());
  }
}
