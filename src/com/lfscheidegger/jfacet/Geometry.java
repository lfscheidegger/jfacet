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
 * a basic amount of geometric information, such as vertex positions, colors, normals, etc, and
 * {@link Geometry} objects hold this information.
 * <p>
 * You can instantiate {@link Geometry} objects directly, by invoking {@link #Geometry(int[], float[], int)},
 * which requires an array of integer <i>indices</i>, and array of floating-point <i>vertex coordinates</i>, and
 * an integer <i>dimension</i>. The indices you pass in allow you to reuse vertex coordinates, allowing
 * the vertex coordinate array to be more complex.
 */
public final class Geometry {

  /**
   * Specifies how vertices in {@link Geometry} are "glued together" to form primitives.
   */
  public static enum PrimitiveType {

    /**
     * Draws a line loop by connecting successive vertices, and also connecting the last vertex
     * back to the first.
     */
    LINE_LOOP(GLES20.GL_LINE_LOOP),

    /**
     * Draws line strip by connecting successive vertices, <i>without</i> connecting the
     * last vertex back to the first.
     */
    LINE_STRIP(GLES20.GL_LINE_STRIP),

    /**
     * For a {@link Geometry} with <i>n</i> vertices, Draws <i>n/2</i> line segments by connecting
     * pairs of successive vertices.
     * <p>
     * This configuration assumes an <i>even</i> number of vertices.
     */
    LINES(GLES20.GL_LINES),

    /**
     * Draws each vertex as a point.
     */
    POINTS(GLES20.GL_POINTS),

    /**
     * Draws a triangle fan centered on the first vertex.
     * <p>
     * If we call the vertices in a {@link com.lfscheidegger.jfacet.Geometry}
     * {@code [v0, v1, v2, ..., v(n-1)]}, then this will draw triangles with vertex triples
     * of the form {@code (v0, v1, v2)}, {@code (v0, v3, v4)}, {@code (v0, v5, v6)}, etc.
     * <p>
     * This configuration assumes an <i>odd</i> number of vertices.
     */
    TRIANGLE_FAN(GLES20.GL_TRIANGLE_FAN),

    /**
     * Draws a triangle strip.
     * <p>
     * The first triangle drawn by this configuration connects the first three vertices
     * in a {@link Geometry}. After these three, each subsequent <i>k-th</i> vertex produces a
     * triangle of the form {@code (v(k-2), v(k-1), vk)}.
     */
    TRIANGLE_STRIP(GLES20.GL_TRIANGLE_STRIP),

    /**
     * Draws a triangle for every three vertices.
     * <p>
     * This configuration assumes that there are <i>3n</i> vertices, for integer values of <i>n</i>.
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

  public Geometry(int[] indices, float[] vertices, int vertexDimension) {
    mIndices = new IndexBuffer(indices);
    mVertices = new VertexDataBuffer(vertices, vertexDimension);
    mVertexDataBuffers = Maps.newHashMap();
    mExpressionCache = Maps.newHashMap();
  }

  public IndexBuffer getIndexBuffer() {
    return mIndices;
  }

  public Real getVertices1() {
    String attributeName = getNameForVertices(1);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Real(mVertices));
    }

    return (Real) mExpressionCache.get(attributeName);
  }

  public Vec2 getVertices2() {
    String attributeName = getNameForVertices(2);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec2(mVertices));
    }

    return (Vec2) mExpressionCache.get(attributeName);
  }

  public Vec3 getVertices3() {
    String attributeName = getNameForVertices(3);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec3(mVertices));
    }

    return (Vec3) mExpressionCache.get(attributeName);
  }

  public Vec4 getVertices4() {
    String attributeName = getNameForVertices(4);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec4(mVertices));
    }

    return (Vec4) mExpressionCache.get(attributeName);
  }

  public Geometry setColors(float[] colors, int dimension) {
    mColors = Optional.of(new VertexDataBuffer(colors, dimension));
    return this;
  }

  public Real getColors1() {
    String attributeName = getNameForColors(1);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Real(mColors.get()));
    }

    return (Real) mExpressionCache.get(attributeName);
  }

  public Vec2 getColors2() {
    String attributeName = getNameForColors(2);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec2(mColors.get()));
    }

    return (Vec2) mExpressionCache.get(attributeName);
  }

  public Vec3 getColors3() {
    String attributeName = getNameForColors(3);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec3(mColors.get()));
    }

    return (Vec3) mExpressionCache.get(attributeName);
  }

  public Vec4 getColors4() {
    String attributeName = getNameForColors(4);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec4(mColors.get()));
    }

    return (Vec4) mExpressionCache.get(attributeName);
  }

  public Geometry setTexCoords(float[] texCoords, int dimension) {
    mTexCoords = Optional.of(new VertexDataBuffer(texCoords, dimension));
    return this;
  }

  public Real getTexCoords1() {
    String attributeName = getNameForTexCoords(1);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Real(mColors.get()));
    }

    return (Real) mExpressionCache.get(attributeName);
  }

  public Vec2 getTexCoords2() {
    String attributeName = getNameForTexCoords(2);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec2(mTexCoords.get()));
    }

    return (Vec2) mExpressionCache.get(attributeName);
  }

  public Vec3 getTexCoords3() {
    String attributeName = getNameForTexCoords(3);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec3(mTexCoords.get()));
    }

    return (Vec3) mExpressionCache.get(attributeName);
  }

  public Vec4 getTexCoords4() {
    String attributeName = getNameForTexCoords(4);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec4(mTexCoords.get()));
    }

    return (Vec4) mExpressionCache.get(attributeName);
  }

  public Geometry setNormals(float[] normals, int dimension) {
    mNormals = Optional.of(new VertexDataBuffer(normals, dimension));
    return this;
  }

  public Real getNormals1() {
    String attributeName = getNameForNormals(1);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Real(mNormals.get()));
    }

    return (Real) mExpressionCache.get(attributeName);
  }

  public Vec2 getNormals2() {
    String attributeName = getNameForNormals(2);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec2(mNormals.get()));
    }

    return (Vec2) mExpressionCache.get(attributeName);
  }

  public Vec3 getNormals3() {
    String attributeName = getNameForNormals(3);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec3(mNormals.get()));
    }

    return (Vec3) mExpressionCache.get(attributeName);
  }

  public Vec4 getNormals4() {
    String attributeName = getNameForNormals(4);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec4(mNormals.get()));
    }

    return (Vec4) mExpressionCache.get(attributeName);
  }

  public Geometry setVertexDataBuffer(String key, float[] values, int dimension) {
    mVertexDataBuffers.put(key, new VertexDataBuffer(values, dimension));
    return this;
  }

  public Real getAttribute1(String attributeName) {
    VertexDataBuffer vertexDataBuffer = mVertexDataBuffers.get(attributeName);
    Preconditions.checkNotNull(vertexDataBuffer);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Real(mVertexDataBuffers.get(attributeName)));
    }

    return (Real) mExpressionCache.get(attributeName);
  }

  public Vec2 getAttribute2(String attributeName) {
    VertexDataBuffer vertexDataBuffer = mVertexDataBuffers.get(attributeName);
    Preconditions.checkNotNull(vertexDataBuffer);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec2(mVertexDataBuffers.get(attributeName)));
    }

    return (Vec2) mExpressionCache.get(attributeName);
  }

  public Vec3 getAttribute3(String attributeName) {
    VertexDataBuffer vertexDataBuffer = mVertexDataBuffers.get(attributeName);
    Preconditions.checkNotNull(vertexDataBuffer);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec3(mVertexDataBuffers.get(attributeName)));
    }

    return (Vec3) mExpressionCache.get(attributeName);
  }

  public Vec4 getAttribute4(String attributeName) {
    VertexDataBuffer vertexDataBuffer = mVertexDataBuffers.get(attributeName);
    Preconditions.checkNotNull(vertexDataBuffer);

    if (mExpressionCache.get(attributeName) == null) {
      mExpressionCache.put(attributeName, new Vec4(mVertexDataBuffers.get(attributeName)));
    }

    return (Vec4) mExpressionCache.get(attributeName);
  }

  public Geometry setPrimitiveType(PrimitiveType primitiveType) {
    mPrimitiveType = primitiveType;
    return this;
  }

  public PrimitiveType getPrimitiveType() {
    return mPrimitiveType;
  }

  public Drawable bake(VecLike vertexPosition, VecLike fragmentColor) {
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
