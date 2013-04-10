package com.lfscheidegger.jfacet.facet;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public class Geometry {

  private final ModelType mType;
  private final int mElementCount;

  // Hold generic attribute buffers. We assume that
  //   .get(0) contains positions,
  //   .get(1) contains colors,
  private final ImmutableMap<AttribBuffer, Expression> mAttributeMap;

  private final IndexBuffer mIndexBuffer;
  private final AttribBuffer mVertexBuffer;
  private final AttribBuffer mColorBuffer;

  public Geometry(
      ModelType type,
      IndexBuffer indexBuffer,
      AttribBuffer vertexPositionBuffer) {
    mType = type;

    mElementCount = vertexPositionBuffer.getElementCount();
    mAttributeMap = buildAttributeMap(ImmutableList.<AttribBuffer>of(vertexPositionBuffer));

    mIndexBuffer = indexBuffer;
    mVertexBuffer = vertexPositionBuffer;
    mColorBuffer = null;
  }

  public Geometry(
      ModelType type,
      IndexBuffer indexBufer,
      AttribBuffer vertexPositionBuffer,
      AttribBuffer colorBuffer) {
    mType = type;

    mElementCount = vertexPositionBuffer.getElementCount();
    mAttributeMap = buildAttributeMap(ImmutableList.<AttribBuffer>of(vertexPositionBuffer, colorBuffer));

    mIndexBuffer = indexBufer;
    mVertexBuffer = vertexPositionBuffer;
    mColorBuffer = colorBuffer;
  }

  private ImmutableMap<AttribBuffer, Expression> buildAttributeMap(ImmutableList<AttribBuffer> attributeBuffers) {
    ImmutableMap.Builder<AttribBuffer, Expression> builder = new ImmutableMap.Builder<AttribBuffer, Expression>();

    for (AttribBuffer attributeBuffer: attributeBuffers) {
      builder.put(attributeBuffer,  getExpressionForBuffer(attributeBuffer));
    }

    return builder.build();
  }

  public ImmutableMap<AttribBuffer, Expression> getAttributeMap() {
    return mAttributeMap;
  }

  public IndexBuffer getIndexBuffer() {
    return mIndexBuffer;
  }

  public Expression getVertices() {
    Preconditions.checkNotNull(mVertexBuffer);
    return mAttributeMap.get(mVertexBuffer);
  }

  public Expression getColors() {
    Preconditions.checkNotNull(mColorBuffer);
    return mAttributeMap.get(mColorBuffer);
  }

  private Expression getExpressionForBuffer(AttribBuffer buffer) {
    switch(buffer.getDimension()) {
      case 1: return Shade.attributef();
      case 2: return Shade.attribute2f();
      case 3: return Shade.attribute3f();
      case 4: return Shade.attribute4f();
      default:
        throw new RuntimeException("Invalid dimension for Geometry object: " + buffer.getDimension());
    }
  }

  public int getElementCount() {
    return mElementCount;
  }
}
