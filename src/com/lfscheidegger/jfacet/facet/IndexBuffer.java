package com.lfscheidegger.jfacet.facet;

import java.nio.IntBuffer;

public class IndexBuffer {

  private final IntBuffer mBuffer;
  private final int mElementCount;

  public IndexBuffer(int[] indices) {
    mBuffer = BufferHelper.getBufferFromArray(indices);
    mElementCount = indices.length;
  }

  public IntBuffer getBuffer() {
    return mBuffer;
  }

  public int getElementCount() {
    return mElementCount;
  }
}
