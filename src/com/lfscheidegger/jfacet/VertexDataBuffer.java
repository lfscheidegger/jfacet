// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * {@link VertexDataBuffer} holds a floating point array used by {@link Geometry} to hold
 * geometrical information, such as vertex coordinates, colors, texture coordinates, normals,
 * or custom user-defined per-vertex data.
 * <p>
 * {@link Geometry} objects contain references to a number of {@link VertexDataBuffer} objects, which
 * hold all the necessary per-vertex geometrical information for rendering. At a minimum, a
 * {@link Geometry} requires vertex coordinates. But you can also set colors, texture
 * coordinates, normals, and custom per-vertex data. {@link VertexDataBuffer} is a convenience
 * wrapper around a {@link java.nio.FloatBuffer} used for holding this per-vertex information.
 */
public final class VertexDataBuffer {

  // bytes per float
  private static final int BYTES_PER_FLOAT = 4;

  // in-memory for the floats
  private final FloatBuffer mBuffer;

  // dimension for the float data.
  private final int mDimension;

  // number of elements in the buffer. This is *not* the number of floats,
  // because we have to take mDimension into account
  private final int mSize;

  /**
   * Default constructor.
   * <p>
   * Instantiates a new {@link VertexDataBuffer}, given a floating point array with the geometrical
   * data, and an integer for its dimension. {@link Geometry} supports per-vertex data of 1, 2, 3,
   * or 4 dimensions, which is specified in this constructor.
   * <p>
   * For example, to specify a triangle in two-dimensional space, e.g.
   * {@code [(0, 0), (1, 0), (1, 1)]}, you would use this
   * floating point array: {@code new float[]{0, 0, 1, 0, 1, 1}}, and a dimension of 2.
   *
   * @param
   *   array A floating point array with the geometrical information for this {@link VertexDataBuffer}
   * @param
   *   dimension The dimension for the elements stored in this {@link VertexDataBuffer}
   */
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

  /**
   * Gets the underlying {@link java.nio.FloatBuffer} that backs this {@link VertexDataBuffer}.
   *
   * @return
   *   The {@link java.nio.FloatBuffer} backing this {@link VertexDataBuffer}
   */
  public FloatBuffer getBuffer() {
    return mBuffer;
  }

  /**
   * Gets the dimension of the elements in this {@link VertexDataBuffer}.
   * <p>
   * {@link VertexDataBuffer} objects hold per-vertex information that can be 1, 2, 3, or 4
   * dimensional. Examples of this kind of data include vertex coordinates (which can contain
   * x, y, z, and w coordinates), color information (which can contain red, green, blue, and the
   * alpha channel), texture coordinates (which can contain s, t, p, and q coordinates), normals
   * (which can contain x, y, z, and w coordinates), and arbitrary per-vertex information (which
   * can use one, two, three, or for components).
   * <p>
   * The value of {@link #getDimension()} returns
   * the dimension of the elements in this {@link VertexDataBuffer}.
   *
   * @return
   *   The dimension of the elements in this {@link VertexDataBuffer}
   *
   */
  public int getDimension() {
    return mDimension;
  }

  /**
   * Gets the size of this {@link VertexDataBuffer}, i.e., the number of elements in it.
   * <p>
   * Gets the size of this {@link VertexDataBuffer}, i.e., the number of elements in it. Notice
   * that this is <i>not</i> the number of floating point values in the buffer. Rather, it is
   * that number divided by this {@link VertexDataBuffer}'s dimension (see also {@link #getDimension()}).
   *
   * @return
   *   The number of elements in this {@link VertexDataBuffer}
   */
  public int size() {
    return mSize;
  }
}
