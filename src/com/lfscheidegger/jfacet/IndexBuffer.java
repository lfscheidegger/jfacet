// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet;

import com.lfscheidegger.jfacet.utils.BufferUtils;

import java.nio.IntBuffer;

public final class IndexBuffer {

  private final IntBuffer mBuffer;
  private final int mElementCount;

  public IndexBuffer(int[] indices) {
    mBuffer = BufferUtils.getBufferFromArray(indices);
    mElementCount = indices.length;
  }

  public IntBuffer getBuffer() {
    return mBuffer;
  }

  public int getElementCount() {
    return mElementCount;
  }
}
