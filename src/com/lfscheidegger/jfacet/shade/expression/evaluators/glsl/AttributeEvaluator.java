package com.lfscheidegger.jfacet.shade.expression.evaluators.glsl;

import com.lfscheidegger.jfacet.facet.AttribBuffer;
import com.lfscheidegger.jfacet.shade.compiler.CompilationContext;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.evaluators.Evaluator;

public class AttributeEvaluator<T> implements Evaluator<T> {

  private final AttribBuffer mAttributeBuffer;

  public AttributeEvaluator(AttribBuffer attributeBuffer) {
    mAttributeBuffer = attributeBuffer;
  }

  @Override
  public T evaluate(Expression expression) {
    throw new RuntimeException("Cannot evaluate attribute expression");
  }

  /*@Override
  public String getGlSlString(Expression expression, CompilationContext context) {
    return context.getExpressionName(expression);
  }*/

  public AttribBuffer getAttributeBuffer() {
    return mAttributeBuffer;
  }
}
