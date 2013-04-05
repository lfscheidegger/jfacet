package com.lfscheidegger.jfacet.facet;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public class Geometry {

  private final ModelType mType;
  private final int mElementCount;

  // Hold generic attribute buffers. We assume that
  //   .get(0) contains positions,
  //   .get(1) contains colors,
  private final ImmutableList<AttribBuffer> mAttributeBuffers;

  public Geometry(ModelType type, AttribBuffer vertexPositionBuffer) {
    mType = type;
    mElementCount = vertexPositionBuffer.getElementCount();
    mAttributeBuffers = ImmutableList.<AttribBuffer>of(vertexPositionBuffer);
  }

  public Geometry(
      ModelType type,
      AttribBuffer vertexPositionBuffer,
      AttribBuffer colorBuffer) {
    mType = type;
    mElementCount = vertexPositionBuffer.getElementCount();
    mAttributeBuffers = ImmutableList.<AttribBuffer>of(vertexPositionBuffer, colorBuffer);
  }

  public Expression getVertices() {
    return getExpressionForBuffer(mAttributeBuffers.get(0));
  }

  public Expression getColors() {
    return getExpressionForBuffer(mAttributeBuffers.get(1));
  }

  private Expression getExpressionForBuffer(AttribBuffer buffer) {
    switch(buffer.getDimension()) {
      case 1: return Shade.attributef(buffer.getBuffer());
      case 2: return Shade.attribute2f(buffer.getBuffer());
      case 3: return Shade.attribute3f(buffer.getBuffer());
      case 4: return Shade.attribute4f(buffer.getBuffer());
      default:
        throw new RuntimeException("Invalid dimension for Geometry object: " + buffer.getDimension());
    }
  }

  public int getElementCount() {
    return mElementCount;
  }
}
