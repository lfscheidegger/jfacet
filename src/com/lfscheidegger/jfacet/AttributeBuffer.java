// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet;

import com.lfscheidegger.jfacet.utils.BufferUtils;

import java.nio.FloatBuffer;

/**
 * Container for a {@code FloatBuffer} and its accompanying dimension
 */
public final class AttributeBuffer {

  private final FloatBuffer mBuffer;
  private final int mDimension;
  private final int mElementCount;

  public AttributeBuffer(float[] array, int dimension) {
    mBuffer = BufferUtils.getBufferFromArray(array);
    mDimension = dimension;
    mElementCount = array.length / dimension;
  }

  public FloatBuffer getBuffer() {
    return mBuffer;
  }

  public int getDimension() {
    return mDimension;
  }

  public int getElementCount() {
    return mElementCount;
  }
}
