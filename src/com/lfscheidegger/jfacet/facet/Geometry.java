package com.lfscheidegger.jfacet.facet;

import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;

import java.nio.FloatBuffer;

public class Geometry {

  private final ModelType mType;
  private final FloatBuffer mVertexPositionBuffer;
  private final int mDimension;
  private final int mBufferSize;

  public Geometry(ModelType type, float[] vertices, int dimension) {
    mType = type;
    mVertexPositionBuffer = BufferHelper.getBufferFromArray(vertices);
    mDimension = dimension;
    mBufferSize = vertices.length;
  }

  public Expression getVertices() {
    switch(mDimension) {
      case 1: return Shade.attributef(mVertexPositionBuffer);
      case 2: return Shade.attribute2f(mVertexPositionBuffer);
      case 3: return Shade.attribute3f(mVertexPositionBuffer);
      case 4: return Shade.attribute4f(mVertexPositionBuffer);
      default:
        throw new RuntimeException("Invalid dimension for Geometry object: " + mDimension);
    }
  }

  public int getElementCount() {
    return mBufferSize / mDimension;
  }
}
