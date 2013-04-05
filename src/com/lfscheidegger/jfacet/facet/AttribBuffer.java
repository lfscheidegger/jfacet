package com.lfscheidegger.jfacet.facet;

import java.nio.FloatBuffer;

/**
 * Container for a {@code FloatBuffer} and its accompanying dimension
 */
public class AttribBuffer {

  private final FloatBuffer mBuffer;
  private final int mDimension;
  private final int mElementCount;

  public AttribBuffer(float[] array, int dimension) {
    mBuffer = BufferHelper.getBufferFromArray(array);
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
