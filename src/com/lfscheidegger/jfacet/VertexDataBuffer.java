// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Container for a {@code FloatBuffer} and its accompanying dimension
 */
public final class VertexDataBuffer {

  private static final int BYTES_PER_FLOAT = 4;

  private final FloatBuffer mBuffer;
  private final int mDimension;
  private final int mSize;

  public VertexDataBuffer(float[] array, int dimension) {
    mBuffer = (FloatBuffer) ByteBuffer
        .allocateDirect(array.length * BYTES_PER_FLOAT)
        .order(ByteOrder.nativeOrder())
        .asFloatBuffer()
        .put(array)
        .position(0);
    mDimension = dimension;
    mSize = array.length / dimension;
  }

  public FloatBuffer getBuffer() {
    return mBuffer;
  }

  public int getDimension() {
    return mDimension;
  }

  public int size() {
    return mSize;
  }
}
