// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

/**
 * {@link IndexBuffer} holds the integer array used by {@link com.lfscheidegger.jfacet.Geometry}
 * to resolve vertices to draw.
 * <p>
 * {@link Geometry} objects use an array of integer indices that point into their vertex data
 * to resolve the <i>stream</i> of vertices passed to the GPU for rendering (see {@link Geometry}
 * for more details). {@link IndexBuffer} is a convenience wrapper around an {@link java.nio.IntBuffer}
 * used for rendering in {@link Drawable}.
 */
public final class IndexBuffer {

  // bytes per int
  private static final int BYTES_PER_INT = 4;

  // in-memory buffer for the integers
  private final IntBuffer mBuffer;

  // number of elements in the buffer
  private final int mSize;

  /**
   * Default constructor.
   * <p>
   * Instantiates a new {@link IndexBuffer}, given an integer array containing the
   * desired indices.
   *
   * @param
   *   indices The indices to be used in this {@link IndexBuffer}
   */
  public IndexBuffer(int[] indices) {
    mBuffer = (IntBuffer) ByteBuffer
        .allocateDirect(indices.length * BYTES_PER_INT)
        .order(ByteOrder.nativeOrder())
        .asIntBuffer()
        .put(indices)
        .position(0);
    mSize = indices.length;
  }

  /**
   * Gets the underlying {@link java.nio.IntBuffer} that backs this {@link IndexBuffer}.
   *
   * @return
   *   The {@link java.nio.IntBuffer} backing this {@link IndexBuffer}
   */
  public IntBuffer getBuffer() {
    return mBuffer;
  }

  /**
   * Gets the size of this {@link IndexBuffer}, i.e., the number of elements in it.
   *
   * @return
   *   The number of elements in this {@link IndexBuffer}
   */
  public int size() {
    return mSize;
  }
}
