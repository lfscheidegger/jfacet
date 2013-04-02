package com.lfscheidegger.jfacet.facet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class BufferHelper {

  public static final int BYTES_PER_FLOAT = 4;

  public static FloatBuffer getBufferFromArray(float[] array) {
    FloatBuffer result = ByteBuffer.allocateDirect(array.length * BYTES_PER_FLOAT)
        .order(ByteOrder.nativeOrder()).asFloatBuffer();
    result.put(array).position(0);
    return result;
  }
}