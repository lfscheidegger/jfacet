package com.lfscheidegger.jfacet.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public final class BufferUtils {

  private static final int BYTES_PER_FLOAT = 4;

  private static final int BYTES_PER_INT = 4;

  public static FloatBuffer getBufferFromArray(float[] array) {
    FloatBuffer result = ByteBuffer.allocateDirect(array.length * BYTES_PER_FLOAT)
        .order(ByteOrder.nativeOrder()).asFloatBuffer();
    result.put(array).position(0);
    return result;
  }

  public static IntBuffer getBufferFromArray(int[] array) {
    IntBuffer result = ByteBuffer.allocateDirect(array.length * BYTES_PER_INT)
        .order(ByteOrder.nativeOrder()).asIntBuffer();
    result.put(array).position(0);
    return result;
  }
}