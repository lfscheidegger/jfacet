package com.lfscheidegger.jfacet.facet;

import java.nio.FloatBuffer;

public class Geometry {

  private final ModelType mType;
  private final FloatBuffer mVertexPositionBuffer;
  private final int mBufferSize;

  public Geometry(ModelType type, float[] vertices) {
    mType = type;
    mVertexPositionBuffer = BufferHelper.getBufferFromArray(vertices);
    mBufferSize = vertices.length;
  }

  public FloatBuffer getPositionBuffer() {
    return mVertexPositionBuffer;
  }

  public int getBufferSize() {
    return mBufferSize;
  }
}
