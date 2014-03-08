// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet;

import android.opengl.GLES20;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.Vec2;

import java.util.Map;

/**
 * {@link Geometry} holds all per-vertex geometric information needed to display a
 * {@link com.lfscheidegger.jfacet.Drawable}. All objects that you can draw using jfacet require
 * a basic amount of geometric information, such as vertex positions, colors, normals, etc., and
 * {@link Geometry} objects hold this information.
 * <p>
 * You instantiate {@link Geometry} objects directly, by invoking {@link #Geometry(int[], float[], int)},
 * which takes as parameters an array of integer <i>indices</i>, an array of floating-point <i>vertex coordinates</i>, and
 * an integer <i>dimension</i>. The indices you pass in allow you to reuse vertex coordinates, making
 * the vertex coordinate array more compact. The vertex coordinates determine the geometric
 * position used for each vertex, and the dimension determines if this is a 2- or 3-dimensional
 * object. 4 dimensions are also supported, if you want to handle homogeneous coordinates
 * manually.
 * <p>
 * Conceptually, the process that takes a {@link Geometry} to something drawn on screen happens in
 * two steps (the GPU is free to optimize this however it wants). The first step
 * turns the vertex indices and geometry information (coordinates, colors, etc.) into a <i>stream</i>
 * of vertices, and the second step turns this stream into geometrical primitives drawn on screen,
 * based on this object's {@link PrimitiveType}. Let's illustrate this with some examples,
 * starting with a single, two-dimensional triangle. To draw a single triangle, with vertices
 * {@code (0, 0)}, {@code (1, 0)}, {@code (1, 1)}, we instantiate a {@link Geometry} like
 * this:
 * <pre>
 *
 *   Geometry geometry = new Geometry(
 *     new int[]{0, 1, 2},
 *     new float[]{0, 0, 1, 0, 1, 1},
 *     2);
 * </pre>
 * Notice how the vertex coordinates are flattened in the floating point array that you pass in.
 * The first step of the drawing process generates a stream of three vertices, by
 * reading from the vertex array according to the indices. In this case, it extracts this
 * stream: {@code ([0, 0), (1, 0), (1, 1)]}. The second step simply takes these three vertices
 * and draws them as a triangle (since the default {@link PrimitiveType} for a {@link Geometry}
 * is {@link Geometry.PrimitiveType#TRIANGLES}). This example covers the basics of the process,
 * but there's a lot more power in using the indices, which we can illustrate with a more complex
 * example - a square. We will draw a square composed of two triangles - a lower left and an upper right triangle, sharing
 * an edge diagonally across the square. The naive way to do this
 * would involve six vertices - three per triangle. However, two of these vertices are the
 * same (the lower right and upper left vertices), so we can use the index array to avoid repeating them.
 * This is what the {@link Geometry} object would look like in that case:
 * <pre>
 *
 *   Geometry geometry = new Geometry(
 *     new int[]{0, 1, 2, 0, 2, 3},
 *     new float[]{0, 0, 1, 0, 1, 1, 1, 0},
 *     2);
 * </pre>
 * Notice how we have six indices, but only <i>four</i> vertices! The first step of the drawing
 * process, in this case, follows each index <i>into</i> the vertex array, and produces a stream
 * with the six vertices we need: {@code [(0, 0), (1, 0), (1, 1), (0, 0), (1, 1), (0, 1)]}.
 * The second step, then, simply draws the triangles again. From these examples, we can derive
 * some important observations about the sizes of these arrays: the size of the index array
 * dictates the number of <i>total</i> vertices used for drawing. Values in the index
 * array must lie in the range {@code [0, n-1]}, (index referencing is zero-indexed), where <i>n</i>
 * is the number of vertices in the vertex array (or the length of the vertex array divided by its
 * <i>dimension</i>). There is no limitation to the size of the vertex array (barring memory
 * constraints), but you should avoid duplicate vertices, since they can
 * be expressed by entries in the indices array.
 * <p>
 * {@link Geometry} objects also support many different kinds of geometric data, in addition to
 * vertex coordinates. You can specify colors ({@link Geometry#setColors(float[], int)}), texture
 * coordinates ({@link Geometry#setTexCoords(float[], int)}), and normals
 * ({@link Geometry#setNormals(float[], int)}), as well as arbitrary vertex
 * data ({@link Geometry#setVertexDataBuffer(String, float[], int)}). When setting these values,
 * keep in mind that the number of <i>elements</i> (the length of the array divided by its
 * dimension) must always match the number of vertex <i>elements</i> passed in the
 * constructor.
 */
public final class Geometry {

  /**
   * Specifies how the vertex stream in {@link Geometry} is "glued together" to form primitives.
   */
  public static enum PrimitiveType {

    /**
     * Draws a line loop by connecting successive vertices, also connecting the last vertex
     * in the stream back to the first.
     */
    LINE_LOOP(GLES20.GL_LINE_LOOP),

    /**
     * Draws a line strip by connecting successive vertices, <i>without</i> connecting the
     * last vertex in the stream back to the first.
     */
    LINE_STRIP(GLES20.GL_LINE_STRIP),

    /**
     * Draws <i>n/2</i> line segments by connecting pairs of successive vertices in the stream.
     * <p>
     * This configuration draws a set of <i>disconnected</i> line segments formed by successive
     * pairs of vertices. If the vertex stream is composed of {@code [v0, v1, v2, v3]}, then this
     * configuration draws two line segments: {@code (v0, v1)} and {@code (v2, 3)}, but it
     * <i>does not</i> draw a line between {@code v1} and {@code v2}.
     * <p>
     * This configuration assumes an <i>even</i> number of vertices in the stream resolved by
     * the indices in its {@link Geometry}.
     */
    LINES(GLES20.GL_LINES),

    /**
     * Draws each vertex as a point.
     */
    POINTS(GLES20.GL_POINTS),

    /**
     * Draws a triangle fan centered on the first vertex.
     * <p>
     * If the vertex stream is composed of
     * {@code [v0, v1, v2, ..., v(n-1)]}, then this draws triangles
     * of the form {@code (v0, v1, v2)}, {@code (v0, v3, v4)}, {@code (v0, v5, v6)}, etc.
     * <p>
     * This configuration assumes an <i>odd</i> number of vertices in the stream resolved by
     * the indices in its {@link Geometry}.
     */
    TRIANGLE_FAN(GLES20.GL_TRIANGLE_FAN),

    /**
     * Draws a triangle strip.
     * <p>
     * The first triangle drawn by this configuration connects the first three vertices
     * in the vertex stream. After these three, each subsequent <i>k-th</i> vertex draws a
     * new triangle of the form {@code (v(k-2), v(k-1), vk)}.
     */
    TRIANGLE_STRIP(GLES20.GL_TRIANGLE_STRIP),

    /**
     * Draws a triangle for every three vertices.
     * <p>
     * This configuration assumes that there are <i>3n</i> vertices, for integer values of <i>n</i>,
     * in the stream resolved by the indices in its {@link Geometry}.
     */
    TRIANGLES(GLES20.GL_TRIANGLES);

    public final int glMode;

    PrimitiveType(int glMode) {
      this.glMode = glMode;
    }
  }

  // Index data for this geometry
  private final IndexBuffer mIndices;

  // VertexDataBuffer containing per-vertex positions
  private final VertexDataBuffer mVertices;

  // A map that holds arbitrary VertexDataBuffer information,
  // keyed by caller-defined strings
  private final Map<String, VertexDataBuffer> mVertexDataBuffers;

  // VertexDataBuffer for per-vertex colors
  private Optional<VertexDataBuffer> mColors = Optional.absent();

  // VertexDataBuffer for per-vertex texture coordinates
  private Optional<VertexDataBuffer> mTexCoords = Optional.absent();

  // VertexDataBuffer for per-vertex normals
  private Optional<VertexDataBuffer> mNormals = Optional.absent();

  // A cache holding expressions for each of the vertex data buffers; we cache
  // these so that subsequent calls to getVertices1(), etc., return the same
  // expression
  private final Map<String, Expression> mExpressionCache;

  // Determines what mode to use when drawing primitives
  private PrimitiveType mPrimitiveType = PrimitiveType.TRIANGLES;

  /**
   * Returns a {@link Geometry} for a flat-shaded cube.
   * <p>
   * Returns a new {@link Geometry} for a flat-shaded cube, with vertices at {@code (-1, -1, -1)},
   * {@code (1, -1, -1)}, {@code (-1, 1, -1)}, {@code (1, 1, -1)}, {@code (-1, -1, 1)},
   * {@code (1, -1, 1)}, {@code (-1, 1, 1)}, and {@code (1, 1, 1)}. Therefore, the edge-length
   * of the cube is 2. For convenience, we also set some colors, texture coordinates
   * (which you're free to replace), and outward-facing normals on the vertices.
   *
   * @return
   *   A new {@link Geometry} for a flat-shaded cube
   */
  public static Geometry newFlatCube() {
    return new Geometry(
        new int[]{
            0, 1, 2, 0, 2, 3,
            5, 4, 7, 5, 7, 6,
            9, 8, 11, 9, 11, 10,
            12, 13, 14, 12, 14, 15,
            16, 17, 18, 16, 18, 19,
            21, 20, 23, 21, 23, 22},
        new float[]{
          /* x   y   z |  x   y   z |  x   y   z |  x   y   z */
            -1, -1, -1,   1, -1, -1,   1,  1, -1,  -1,  1, -1, // front
            -1, -1,  1,   1, -1,  1,   1,  1,  1,  -1,  1,  1, // back
            -1, -1, -1,   1, -1, -1,   1, -1,  1,  -1, -1,  1, // bottom
            -1,  1, -1,   1,  1, -1,   1,  1,  1,  -1,  1,  1, // top
            -1, -1,  1,  -1, -1, -1,  -1,  1, -1,  -1,  1,  1, // left
            1, -1,  1,   1, -1, -1,   1,  1, -1,   1,  1,  1  // right
        }, 3)
        .setColors(new float[]{
            0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0,
            1, 0.5f, 0, 1, 0.5f, 0, 1, 0.5f, 0, 1, 0.5f, 0,
            1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0,
            1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0,
            0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1,
            1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1}, 3)
        .setTexCoords(new float[] {
            0, 0, 1, 0, 1, 1, 0, 1,
            0, 0, 1, 0, 1, 1, 0, 1,
            0, 0, 1, 0, 1, 1, 0, 1,
            0, 0, 1, 0, 1, 1, 0, 1,
            0, 0, 1, 0, 1, 1, 0, 1,
            0, 0, 1, 0, 1, 1, 0, 1
        }, 2)
        .setNormals(new float[] {
            0,  0, -1,   0,  0, -1,   0,  0, -1,   0,  0, -1,
            0,  0,  1,   0,  0,  1,   0,  0,  1,   0,  0,  1,
            0, -1,  0,   0, -1,  0,   0, -1,  0,   0, -1,  0,
            0,  1,  0,   0,  1,  0,   0,  1,  0,   0,  1,  0,
            -1,  0,  0,  -1,  0,  0,  -1,  0,  0,  -1,  0,  0,
            1,  0,  0,   1,  0,  0,   1,  0,  0,   1,  0,  0
        }, 3);
  }

  /**
   * Returns a {@link Geometry} for a flat-shaded, four-sided pyramid.
   * <p>
   * Returns a new {@link Geometry} for a flat-shaded, four-sided pyramid. The apex of the
   * pyramid is at {@code (0, 1, 0)}, and the base is a square with vertices at {@code (-1, -1, 1)},
   * {@code (-1, -1, -1)}, {@code (1, -1, -1)}, and {@code (1, -1, 1)}. This pyramid includes only
   * the triangles for its sides (the bottom is hollow). We set colors on the vertices, for
   * convenience, but nothing else.
   *
   * @return
   *   A new {@link Geometry} for a flat-shaded, four-sided pyramid
   */
  public static Geometry newFlatPyramid() {
    return new Geometry(
        new int[]{
            0, 1, 2,
            0, 2, 3,
            0, 3, 4,
            0, 4, 1},
        new float[]{
            0, 1, 0,
            -1, -1, 1,
            -1, -1, -1,
            1, -1, -1,
            1, -1, 1}, 3)
        .setColors(new float[]{
            1, 0, 0,
            0, 1, 0,
            0, 0, 1,
            0, 1, 0,
            0, 0, 1
        }, 3);
  }

  /**
   * Default constructor.
   * <p>
   * The three parameters passed into this constructor define the full geometric information for
   * this {@link Geometry}.
   *
   * @param indices
   *   An integer array whose values point to entries in {@code vertices}
   * @param vertices
   *   A floating point array with vertex coordinates
   * @param vertexDimension
   *   The dimension of each vertex. Must be 1, 2, 3, or 4
   */
  public Geometry(int[] indices, float[] vertices, int vertexDimension) {
    mIndices = new IndexBuffer(indices);
    mVertices = new VertexDataBuffer(vertices, vertexDimension);
    mVertexDataBuffers = Maps.newHashMap();
    mExpressionCache = Maps.newHashMap();
  }

  /**
   * Gets the indices for this {@link Geometry}.
   *
   * @return
   *   An {@link IndexBuffer} with the indices for this {@link Geometry}
   */
  public IndexBuffer getIndexBuffer() {
    return mIndices;
  }

  /**
   * Gets a {@link Real} object for the vertices in this {@link Geometry}.
   * <p>
   * Returns a one-dimensional view into the vertices in this geometry. If the
   * vertices passed in have more than one dimension, the value returned by this method refers
   * only to the x coordinate.
   *
   * @return
   *   A {@link Real} referring to the vertices for this {@link Geometry}
   */
  public Real getVertices1() {
    String attributeName = getNameForVertices(1);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Real(mVertices));
    }

    return (Real) mExpressionCache.get(attributeName);
  }

  /**
   * Gets a {@link Vec2} object for the vertices in this {@link Geometry}.
   * <p>
   * Returns a two-dimensional view into the vertices in this geometry. If the
   * vertices passed in have more than two dimensions, the value returned by this method refers
   * only to the x and y coordinates. If the vertices passed in have <i>less</i> than two
   * dimensions, the value of the y coordinate is left to the GPU (this is usually, but not
   * guaranteed to be, 0).
   *
   * @return
   *   A {@link Vec2} referring to the vertices for this {@link Geometry}
   */
  public Vec2 getVertices2() {
    String attributeName = getNameForVertices(2);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec2(mVertices));
    }

    return (Vec2) mExpressionCache.get(attributeName);
  }

  /**
   * Gets a {@link Vec3} object for the vertices in this {@link Geometry}.
   * <p>
   * Returns a three-dimensional view into the vertices in this geometry. If the
   * vertices passed in have more than three dimensions, the value returned by this method refers
   * only to the x, y, and z coordinates. If the vertices passed in have <i>less</i> than three
   * dimensions, the value of the y and z coordinates is left to the GPU (these are usually, but not
   * guaranteed to be, 0).
   *
   * @return
   *   A {@link Vec3} referring to the vertices for this {@link Geometry}
   */
  public Vec3 getVertices3() {
    String attributeName = getNameForVertices(3);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec3(mVertices));
    }

    return (Vec3) mExpressionCache.get(attributeName);
  }

  /**
   * Gets a {@link Vec4} object for the vertices in this {@link Geometry}.
   * <p>
   * Returns a four-dimensional view into the vertices in this geometry. This includes
   * the three conventional x, y, and z coordinates, as well as the <i>homogeneous</i> w
   * coordinate. If the vertices passed in have <i>less</i> than four dimensions, the value of
   * the y, z, and w coordinates is left to the GPU (these are usually, but not guaranteed to be,
   * 0, 0, and 1, respectively)
   *
   * @return
   *   A {@link Vec4} referring to the vertices for this {@link Geometry}
   */
  public Vec4 getVertices4() {
    String attributeName = getNameForVertices(4);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec4(mVertices));
    }

    return (Vec4) mExpressionCache.get(attributeName);
  }

  /**
   * Sets color information on this {@link Geometry}.
   * <p>
   * This method supports passing colors in one, two, three, or four dimensions. They correspond
   * to red, green, blue, and the alpha channel, respectively. The number of color <i>elements</i>
   * supplied ({@code colors.length / dimension}) must match the number of vertex elements used
   * to instantiate this {@link Geometry}.
   *
   * @param colors
   *   A floating point array with color data
   * @param dimension
   *   The number of color components. Must be 1, 2, 3, or 4
   * @return
   *   This {@link Geometry}, for chaining setter calls
   */
  public Geometry setColors(float[] colors, int dimension) {
    Preconditions.checkState(
        mVertices.getElementCount() == colors.length / dimension,
        "Number of color elements and vertex elements do not match");

    mColors = Optional.of(new VertexDataBuffer(colors, dimension));
    return this;
  }

  /**
   * Gets a {@link Real} object for the colors in this {@link Geometry}.
   * <p>
   * Returns a one-dimensional (red only) view into the colors in this geometry. If the
   * colors passed in have more than one dimension, the value returned by this method refers
   * only to the red component.
   *
   * @return
   *   A {@link Real} referring to the colors for this {@link Geometry}
   *
   * @throws
   *   {@link java.lang.IllegalStateException} if colors haven't been set
   */
  public Real getColors1() {
    String attributeName = getNameForColors(1);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Real(mColors.get()));
    }

    return (Real) mExpressionCache.get(attributeName);
  }

  /**
   * Gets a {@link Vec2} object for the colors in this {@link Geometry}.
   * <p>
   * Returns a two-dimensional (red and green) view into the colors in this geometry.
   * If the colors passed in have more than two dimensions, the value returned by this method refers
   * only to the red and green components. If the colors passed in have <i>less</i> than two
   * dimensions, the value of the green component is left to the GPU (this is usually, but not
   * guaranteed to be, 0).
   *
   * @return
   *   A {@link Vec2} referring to the colors for this {@link Geometry}
   *
   * @throws
   *   {@link java.lang.IllegalStateException} if colors haven't been set
   */
  public Vec2 getColors2() {
    String attributeName = getNameForColors(2);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec2(mColors.get()));
    }

    return (Vec2) mExpressionCache.get(attributeName);
  }

  /**
   * Gets a {@link Vec3} object for the colors in this {@link Geometry}.
   * <p>
   * Returns a three-dimensional (red, green, and blue) view into the colors in this
   * geometry. If the colors passed in have more than three dimensions, the value returned by this
   * method refers only to the red, green, and blue components. If the colors passed in have <i>less</i> than
   * three dimensions, the value of the green and blue components is left to the GPU (these are
   * usually, but not guaranteed to be, 0).
   *
   * @return
   *   A {@link Vec3} referring to the colors for this {@link Geometry}
   *
   * @throws
   *   {@link java.lang.IllegalStateException} if colors haven't been set
   */
  public Vec3 getColors3() {
    String attributeName = getNameForColors(3);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec3(mColors.get()));
    }

    return (Vec3) mExpressionCache.get(attributeName);
  }

  /**
   * Gets a {@link Vec4} object for the colors in this {@link Geometry}.
   * <p>
   * Returns a four-dimensional (red, green, blue, and alpha channel) view into the
   * colors in this geometry. If the colors passed in have <i>less</i> than four dimensions, the value of
   * the green, blue, and alpha channel components is left to the GPU (these are usually, but not
   * guaranteed to be, 0, 0, and 1, respectively)
   *
   * @return
   *   A {@link Vec4} referring to the colors for this {@link Geometry}
   *
   * @throws
   *   {@link java.lang.IllegalStateException} if colors haven't been set
   */
  public Vec4 getColors4() {
    String attributeName = getNameForColors(4);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec4(mColors.get()));
    }

    return (Vec4) mExpressionCache.get(attributeName);
  }

  /**
   * Sets texture coordinate information on this {@link Geometry}.
   * <p>
   * This method supports passing texture coordinates in one, two, three, or four dimensions.
   * They correspond the s, t, p, and q coordinates, respectively. The number of texture coordinate
   * <i>elements</i> supplied ({@code texCoords.length / dimension}) must match the number of vertex
   * elements used to instantiate this {@link Geometry}.
   *
   * @param texCoords
   *   A floating point array with texture coordinate data
   * @param dimension
   *   The number of texture coordinate dimensions. Must be 1, 2, 3, or 4
   * @return
   *   This {@link Geometry}, for chaining setter calls
   */
  public Geometry setTexCoords(float[] texCoords, int dimension) {
    Preconditions.checkState(
        mVertices.getElementCount() == texCoords.length / dimension,
        "Number of texture coordinate elements and vertex elements do not match");

    mTexCoords = Optional.of(new VertexDataBuffer(texCoords, dimension));
    return this;
  }

  /**
   * Gets a {@link Real} object for the texture coordinates in this {@link Geometry}.
   * <p>
   * Returns a one-dimensional (s only) view into the texture coordinates in this
   * geometry. If the texture coordinates  passed in have more than one dimension, the value returned
   * by this method refers only to the s component.
   *
   * @return
   *   A {@link Real} referring to the texture coordinates for this {@link Geometry}
   *
   * @throws
   *   {@link java.lang.IllegalStateException} if texture coordinates haven't been set
   */
  public Real getTexCoords1() {
    String attributeName = getNameForTexCoords(1);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Real(mColors.get()));
    }

    return (Real) mExpressionCache.get(attributeName);
  }

  /**
   * Gets a {@link Vec2} object for the texture coordinates in this {@link Geometry}.
   * <p>
   * Returns a two-dimensional (s and t) view into the texture coordinates in this geometry.
   * If the texture coordinates passed in have more than two dimensions, the value returned by this method refers
   * only to the s and t components. If the texture coordinates passed in have <i>less</i> than two
   * dimensions, the value of the t component is left to the GPU (this is usually, but not
   * guaranteed to be, 0).
   *
   * @return
   *   A {@link Vec2} referring to the texture coordinates for this {@link Geometry}
   *
   * @throws
   *   {@link java.lang.IllegalStateException} if texture coordinates haven't been set
   */
  public Vec2 getTexCoords2() {
    String attributeName = getNameForTexCoords(2);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec2(mTexCoords.get()));
    }

    return (Vec2) mExpressionCache.get(attributeName);
  }

  /**
   * Gets a {@link Vec3} object for the texture coordinates in this {@link Geometry}.
   * <p>
   * Returns a three-dimensional (s, t, and p) view into the texture coordinates in this
   * geometry. If the texture coordinates passed in have more than three dimensions, the value returned by this
   * method refers only to the s, t, and p components. If the texture coordinates passed in have <i>less</i> than
   * three dimensions, the value of the t and p components is left to the GPU (these are
   * usually, but not guaranteed to be, 0).
   *
   * @return
   *   A {@link Vec3} referring to the texture coordinates for this {@link Geometry}
   *
   * @throws
   *   {@link java.lang.IllegalStateException} if texture coordinates haven't been set
   */
  public Vec3 getTexCoords3() {
    String attributeName = getNameForTexCoords(3);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec3(mTexCoords.get()));
    }

    return (Vec3) mExpressionCache.get(attributeName);
  }

  /**
   * Gets a {@link Vec4} object for the texture coordinates in this {@link Geometry}.
   * <p>
   * Returns a four-dimensional (s, t, p, and q) view into the
   * texture coordinates in this geometry. If the texture coordinates passed in have <i>less</i> than
   * four dimensions, the value of the t, p, and q components is left to the GPU (these are usually, but not
   * guaranteed to be, 0, 0, and 1, respectively)
   *
   * @return
   *   A {@link Vec4} referring to the texture coordinates for this {@link Geometry}
   *
   * @throws
   *   {@link java.lang.IllegalStateException} if texture coordinates haven't been set
   */
  public Vec4 getTexCoords4() {
    String attributeName = getNameForTexCoords(4);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec4(mTexCoords.get()));
    }

    return (Vec4) mExpressionCache.get(attributeName);
  }

  /**
   * Sets normal information on this {@link Geometry}.
   * <p>
   * This method supports passing normals in one, two, three, or four dimensions.
   * They correspond the x, y, z, and w coordinates, respectively. The number of normal
   * <i>elements</i> supplied ({@code normals.length / dimension}) must match the number of vertex
   * elements used to instantiate this {@link Geometry}.
   *
   * @param normals
   *   A floating point array with normal data
   * @param dimension
   *   The number of normal dimensions. Must be 1, 2, 3, or 4
   * @return
   *   This {@link Geometry}, for chaining setter calls
   */
  public Geometry setNormals(float[] normals, int dimension) {
    Preconditions.checkState(
        mVertices.getElementCount() == normals.length / dimension,
        "Number of normal elements and vertex elements do not match");

    mNormals = Optional.of(new VertexDataBuffer(normals, dimension));
    return this;
  }

  /**
   * Gets a {@link Real} object for the normals in this {@link Geometry}
   * <p>
   * Returns a one-dimensional (x only) view into the normals in this
   * geometry. If the normals  passed in have more than one dimension, the value returned
   * by this method refers only to the x component.
   *
   * @return
   *   A {@link Real} referring to the normals for this {@link Geometry}
   *
   * @throws
   *   {@link java.lang.IllegalStateException} if normals haven't been set
   */
  public Real getNormals1() {
    String attributeName = getNameForNormals(1);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Real(mNormals.get()));
    }

    return (Real) mExpressionCache.get(attributeName);
  }

  /**
   * Gets a {@link Vec2} object for the normals in this {@link Geometry}.
   * <p>
   * Returns a two-dimensional (x and y) view into the normals in this geometry.
   * If the normals passed in have more than two dimensions, the value returned by this method refers
   * only to the x and y components. If the normals passed in have <i>less</i> than two
   * dimensions, the value of the y component is left to the GPU (this is usually, but not
   * guaranteed to be, 0).
   *
   * @return
   *   A {@link Vec2} referring to the normals for this {@link Geometry}
   *
   * @throws
   *   {@link java.lang.IllegalStateException} if normals haven't been set
   */
  public Vec2 getNormals2() {
    String attributeName = getNameForNormals(2);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec2(mNormals.get()));
    }

    return (Vec2) mExpressionCache.get(attributeName);
  }

  /**
   * Gets a {@link Vec3} object for the normals in this {@link Geometry}.
   * <p>
   * Returns a three-dimensional (x, y, and z) view into the normals in this
   * geometry. If the normals passed in have more than three dimensions, the value returned by this
   * method refers only to the x, y, and z components. If the normals passed in have <i>less</i> than
   * three dimensions, the value of the y and z components is left to the GPU (these are
   * usually, but not guaranteed to be, 0).
   *
   * @return
   *   A {@link Vec3} referring to the normals for this {@link Geometry}
   *
   * @throws
   *   {@link java.lang.IllegalStateException} if normals haven't been set
   */
  public Vec3 getNormals3() {
    String attributeName = getNameForNormals(3);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec3(mNormals.get()));
    }

    return (Vec3) mExpressionCache.get(attributeName);
  }

  /**
   * Gets a {@link Vec4} object for the normals in this {@link Geometry}.
   * <p>
   * Returns a four-dimensional (x, y, z, and w) view into the
   * normals in this geometry. If the normals passed in have <i>less</i> than
   * four dimensions, the value of the y, z, and w components is left to the GPU (these are usually, but not
   * guaranteed to be, 0, 0, and 1, respectively)
   *
   * @return
   *   A {@link Vec4} referring to the normals for this {@link Geometry}
   *
   * @throws
   *   {@link java.lang.IllegalStateException} if normals haven't been set
   */
  public Vec4 getNormals4() {
    String attributeName = getNameForNormals(4);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec4(mNormals.get()));
    }

    return (Vec4) mExpressionCache.get(attributeName);
  }

  /**
   * Sets arbitrary vertex data information on this {@link Geometry}.
   * <p>
   * This method supports passing in arbitrary vertex-level data with one, two, three, or
   * four components. Data objects passed in are keyed by {@code key}, and retrieved using
   * {@link Geometry#getVertexData1(String)}, {@link Geometry#getVertexData2(String)},
   * {@link Geometry#getVertexData3(String)}, and {@link com.lfscheidegger.jfacet.Geometry#getTexCoords4()}.
   * The number of vertex data <i>elements</i> supplied ({@code values.length / dimension}) must match
   * the number of vertex elements used to instantiate this {@link Geometry}.
   *
   * @param key
   *   A {@link java.lang.String} to identify this set of vertex data
   * @param values
   *   A floating point array with the vertex data
   * @param dimension
   *   The number of vertex data dimensions for this set. Must be 1, 2, 3, or 4
   * @return
   *   This {@link Geometry}, for chaining setter calls
   */
  public Geometry setVertexDataBuffer(String key, float[] values, int dimension) {
    Preconditions.checkState(
        mVertices.getElementCount() == values.length / dimension,
        "Number of vertex data elements and vertex elements do not match");

    mVertexDataBuffers.put(key, new VertexDataBuffer(values, dimension));
    return this;
  }

  /**
   * Gets a {@link Real} object for the vertex data in this {@link Geometry}, keyed by {@code key}.
   * <p>
   * Returns a one-dimensional (first component) view into the vertex data in this
   * geometry keyed by {@code key}. If the vertex data passed in has more than one dimension,
   * the value returned by this method refers only to the first component.
   *
   * @param key
   *   A {@link java.lang.String} to identify this set of vertex data
   *
   * @return
   *   A {@link Real} referring to the vertex data keyed by {@code key} for this {@link Geometry}
   *
   * @throws
   *   {@link java.lang.NullPointerException} if vertex data for {@code key} hasn't been set
   */
  public Real getVertexData1(String key) {
    VertexDataBuffer vertexDataBuffer = mVertexDataBuffers.get(key);
    Preconditions.checkNotNull(vertexDataBuffer);

    if (mExpressionCache.get(key) == null) {
      mExpressionCache.put(key, new Real(mVertexDataBuffers.get(key)));
    }

    return (Real) mExpressionCache.get(key);
  }

  /**
   * Gets a {@link Vec2} object for the vertex data in this {@link Geometry}, keyed by
   * {@code key}.
   * <p>
   * Returns a two-dimensional (first and second components) view into the vertex data in this
   * geometry keyed by {@code key}. If the vertex data passed in has more than two dimensions,
   * the value returned by this method refers only to the first component and second components. If
   * the vertex data passed in has <i>less</i> than two dimensions, the value of the second
   * component is left to the GPU (this is usually, but not guaranteed to be, 0).
   *
   * @param key
   *   A {@link java.lang.String} to identify this set of vertex data
   *
   * @return
   *   A {@link Vec2} referring to the vertex keyed by {@code key} for this {@link Geometry}
   *
   * @throws
   *   {@link java.lang.NullPointerException} if vertex data for {@code key} hasn't been set
   */
  public Vec2 getVertexData2(String key) {
    VertexDataBuffer vertexDataBuffer = mVertexDataBuffers.get(key);
    Preconditions.checkNotNull(vertexDataBuffer);

    if (mExpressionCache.get(key) == null) {
      mExpressionCache.put(key, new Vec2(mVertexDataBuffers.get(key)));
    }

    return (Vec2) mExpressionCache.get(key);
  }

  /**
   * Gets a {@link Vec3} object for the vertex data in this {@link Geometry}, keyed by
   * {@code key}.
   * <p>
   * Returns a three-dimensional (first, second, and third components) view into the
   * vertex data in this geometry keyed by {@code key}. If the vertex data passed in has more than three dimensions,
   * the value returned by this method refers only to the first, second, and third components. If
   * the vertex data passed in has <i>less</i> than three dimensions, the value of the second and
   * third components is left to the GPU (these are usually, but not guaranteed to be, 0).
   *
   * @param key
   *   A {@link java.lang.String} to identify this set of vertex data
   *
   * @return
   *   A {@link Vec3} referring to the vertex keyed by {@code key} for this {@link Geometry}
   *
   * @throws
   *   {@link java.lang.NullPointerException} if vertex data for {@code key} hasn't been set
   */
  public Vec3 getVertexData3(String key) {
    VertexDataBuffer vertexDataBuffer = mVertexDataBuffers.get(key);
    Preconditions.checkNotNull(vertexDataBuffer);

    if (mExpressionCache.get(key) == null) {
      mExpressionCache.put(key, new Vec3(mVertexDataBuffers.get(key)));
    }

    return (Vec3) mExpressionCache.get(key);
  }

  /**
   * Gets a {@link Vec4} object for the vertex data in this {@link Geometry}, keyed by
   * {@code key}.
   * <p>
   * Returns a four-dimensional view into the
   * vertex data in this geometry keyed by {@code key}. If
   * the vertex data passed in has <i>less</i> than four dimensions, the value of the second, third,
   * and fourth components is left to the GPU (these are usually, but not guaranteed to be, 0, 0,
   * and 1, respectively).
   *
   * @param key
   *   A {@link java.lang.String} to identify this set of vertex data
   *
   * @return
   *   A {@link Vec4} referring to the vertex keyed by {@code key} for this {@link Geometry}
   *
   * @throws
   *   {@link java.lang.NullPointerException} if vertex data for {@code key} hasn't been set
   */
  public Vec4 getVertexData4(String key) {
    VertexDataBuffer vertexDataBuffer = mVertexDataBuffers.get(key);
    Preconditions.checkNotNull(vertexDataBuffer);

    if (mExpressionCache.get(key) == null) {
      mExpressionCache.put(key, new Vec4(mVertexDataBuffers.get(key)));
    }

    return (Vec4) mExpressionCache.get(key);
  }

  /**
   * Sets the {@link Geometry.PrimitiveType} to use when rendering this {@link Geometry}.
   * <p>
    * {@link Geometry.PrimitiveType} can customize how the GPU draws geometries. They can
   * be used to draw lines, triangle strips, triangle fans, and more.
   * <p>
   * If not set, the default is {@link Geometry.PrimitiveType#TRIANGLES}.
   * @param
   *   primitiveType The {@link PrimitiveType} to use with this {@link Geometry}
   * @return
   *   This {@link Geometry}, for chaining setter calls
   */
  public Geometry setPrimitiveType(PrimitiveType primitiveType) {
    mPrimitiveType = primitiveType;
    return this;
  }

  /**
   * Gets the {@link PrimitiveType} for this {@link Geometry}.
   * <p>
   * Returns the {@link PrimitiveType} used to determine how to build the primitives in this
   * {@link Geometry}. Different values of {@link PrimitiveType} can customize how the GPU
   * draws this {@link Geometry}, and support drawing lines, triangle strips, triangle fans,
   * and more. If this value is never set (see {@link Geometry#setPrimitiveType(com.lfscheidegger.jfacet.Geometry.PrimitiveType)},
   * it defaults to {@link PrimitiveType#TRIANGLES}.
   *
   * @return
   *   The {@link PrimitiveType} for this {@link Geometry}
   */
  public PrimitiveType getPrimitiveType() {
    return mPrimitiveType;
  }

  /**
   * Returns a {@link com.lfscheidegger.jfacet.Drawable} for this {@link com.lfscheidegger.jfacet.Geometry}.
   * <p>
   * Returns a new {@link com.lfscheidegger.jfacet.Drawable} that you can use to draw
   * the object represented by this {@link com.lfscheidegger.jfacet.Geometry}. It takes as parameters
   * {@link com.lfscheidegger.jfacet.shade.expression.VecLike} expressions for the (fully modelview/projection
   * transformed) vertices, and final fragment colors.
   * <p>
   * jfacet maintains a separation between the <i>specification</i> of a geometrical object (represented
   * by a {@link com.lfscheidegger.jfacet.Geometry} object) and the <i>drawing</i> of that object
   * (represented by a {@link Drawable}). This allows you to specify different ways of drawing the
   * same geometry, by making successive calls to this method using different parameters. The
   * underlying geometry data for all {@link com.lfscheidegger.jfacet.Drawable} objects returned
   * is the same.
   *
   * @param
   *   vertexPosition A {@link com.lfscheidegger.jfacet.shade.expression.VecLike} object
   *   representing the fully modelview/projection-transformed vertex data for the {@link com.lfscheidegger.jfacet.Drawable}
   *
   * @param
   *   fragmentColor A {@link com.lfscheidegger.jfacet.shade.expression.VecLike} object
   *   representing the final per-fragment color for the {@link com.lfscheidegger.jfacet.Drawable}
   *
   * @return
   *   A new {@link com.lfscheidegger.jfacet.Drawable} used to draw this
   *   {@link com.lfscheidegger.jfacet.Geometry} with the transformed vertex expression given by
   *   {@code vertexPosition} and final per-fragment color given by {@code fragmentColor}
   */
  public Drawable getDrawable(VecLike vertexPosition, VecLike fragmentColor) {
    return new Drawable(this, vertexPosition, fragmentColor);
  }

  private String getNameForVertices(int dimension) {
    return "__vertices__" + dimension;
  }

  private String getNameForColors(int dimension) {
    return "__colors__" + dimension;
  }

  private String getNameForNormals(int dimension) {
    return "__normals__" + dimension;
  }

  private String getNameForTexCoords(int dimension) {
    return "__texCoords__" + dimension;
  }
}
