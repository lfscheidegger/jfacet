// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.Vec2;

import java.util.Map;

public final class Geometry {

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

  public Geometry(int[] indices, float[] vertices, int vertexDimension) {
    mIndices = new IndexBuffer(indices);
    mVertices = new VertexDataBuffer(vertices, vertexDimension);
    mVertexDataBuffers = Maps.newHashMap();
    mExpressionCache = Maps.newHashMap();
  }

  public Geometry setColors(float[] colors, int dimension) {
    mColors = Optional.of(new VertexDataBuffer(colors, dimension));
    return this;
  }

  public Geometry setTexCoords(float[] texCoords, int dimension) {
    mTexCoords = Optional.of(new VertexDataBuffer(texCoords, dimension));
    return this;
  }

  public Geometry setNormals(float[] normals, int dimension) {
    mNormals = Optional.of(new VertexDataBuffer(normals, dimension));
    return this;
  }

  public Geometry setVertexDataBuffer(String key, float[] values, int dimension) {
    mVertexDataBuffers.put(key, new VertexDataBuffer(values, dimension));
    return this;
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
