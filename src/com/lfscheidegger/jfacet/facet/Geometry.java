package com.lfscheidegger.jfacet.facet;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;
import com.lfscheidegger.jfacet.shade.expression.vector.VectorExpression;

import javax.annotation.Nullable;
import java.util.Map;

public final class Geometry {

  private final IndexBuffer mIndices;
  private final AttribBuffer mVertices;
  private final Map<String, AttribBuffer> mAttribBufferMap;
  private final Map<String, Expression> mExpressionMap;

  private @Nullable AttribBuffer mColors;
  private @Nullable AttribBuffer mTexCoords;
  private @Nullable AttribBuffer mNormals;

  public Geometry(int[] indices, float[] vertices, int vertexDimension) {
    mIndices = new IndexBuffer(indices);
    mVertices = new AttribBuffer(vertices, vertexDimension);
    mAttribBufferMap = Maps.newHashMap();
    mExpressionMap = Maps.newHashMap();
  }

  public Geometry setColors(float[] colors, int dimension) {
    mColors = new AttribBuffer(colors, dimension);
    return this;
  }

  public Geometry setTexCoords(float[] texCoords, int dimension) {
    mTexCoords = new AttribBuffer(texCoords, dimension);
    return this;
  }

  public Geometry setNormals(float[] normals, int dimension) {
    mNormals = new AttribBuffer(normals, dimension);
    return this;
  }

  public Geometry setAttributeValues(float[] values, int dimension, String key) {
    mAttribBufferMap.put(key, new AttribBuffer(values, dimension));
    return this;
  }

  public IndexBuffer getIndexBuffer() {
    return mIndices;
  }

  public Real getVertices1() {
    String attributeName = getNameForVertices(1);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Real(mVertices));
    }

    return (Real)mExpressionMap.get(attributeName);
  }

  public Vector2 getVertices2() {
    String attributeName = getNameForVertices(2);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Vector2(mVertices));
    }

    return (Vector2)mExpressionMap.get(attributeName);
  }

  public Vector3 getVertices3() {
    String attributeName = getNameForVertices(3);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Vector3(mVertices));
    }

    return (Vector3)mExpressionMap.get(attributeName);
  }

  public Vector4 getVertices4() {
    String attributeName = getNameForVertices(4);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Vector4(mVertices));
    }

    return (Vector4)mExpressionMap.get(attributeName);
  }

  public Real getColors1() {
    Preconditions.checkNotNull(mColors);
    String attributeName = getNameForColors(1);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Real(mColors));
    }

    return (Real)mExpressionMap.get(attributeName);
  }

  public Vector2 getColors2() {
    Preconditions.checkNotNull(mColors);
    String attributeName = getNameForColors(2);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Vector2(mColors));
    }

    return (Vector2)mExpressionMap.get(attributeName);
  }

  public Vector3 getColors3() {
    Preconditions.checkNotNull(mColors);
    String attributeName = getNameForColors(3);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Vector3(mColors));
    }

    return (Vector3)mExpressionMap.get(attributeName);
  }

  public Vector4 getColors4() {
    Preconditions.checkNotNull(mColors);
    String attributeName = getNameForColors(4);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Vector4(mColors));
    }

    return (Vector4)mExpressionMap.get(attributeName);
  }

  public Real getTexCoords1() {
    Preconditions.checkNotNull(mTexCoords);
    String attributeName = getNameForTexCoords(1);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Real(mColors));
    }

    return (Real)mExpressionMap.get(attributeName);
  }

  public Vector2 getTexCoords2() {
    Preconditions.checkNotNull(mTexCoords);
    String attributeName = getNameForTexCoords(2);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Vector2(mTexCoords));
    }

    return (Vector2)mExpressionMap.get(attributeName);
  }

  public Vector3 getTexCoords3() {
    Preconditions.checkNotNull(mTexCoords);
    String attributeName = getNameForTexCoords(3);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Vector3(mTexCoords));
    }

    return (Vector3)mExpressionMap.get(attributeName);
  }

  public Vector4 getTexCoords4() {
    Preconditions.checkNotNull(mTexCoords);
    String attributeName = getNameForTexCoords(4);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Vector4(mTexCoords));
    }

    return (Vector4)mExpressionMap.get(attributeName);
  }

  public Real getNormals1() {
    Preconditions.checkNotNull(mNormals);
    String attributeName = getNameForNormals(1);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Real(mNormals));
    }

    return (Real)mExpressionMap.get(attributeName);
  }

  public Vector2 getNormals2() {
    Preconditions.checkNotNull(mNormals);
    String attributeName = getNameForNormals(2);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Vector2(mNormals));
    }

    return (Vector2)mExpressionMap.get(attributeName);
  }

  public Vector3 getNormals3() {
    Preconditions.checkNotNull(mNormals);
    String attributeName = getNameForNormals(3);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Vector3(mNormals));
    }

    return (Vector3)mExpressionMap.get(attributeName);
  }

  public Vector4 getNormals4() {
    Preconditions.checkNotNull(mNormals);
    String attributeName = getNameForNormals(4);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Vector4(mNormals));
    }

    return (Vector4)mExpressionMap.get(attributeName);
  }

  public Real getAttribute1(String attributeName) {
    AttribBuffer attributeBuffer = mAttribBufferMap.get(attributeName);
    Preconditions.checkNotNull(attributeBuffer);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Real(mAttribBufferMap.get(attributeName)));
    }

    return (Real)mExpressionMap.get(attributeName);
  }

  public Vector2 getAttribute2(String attributeName) {
    AttribBuffer attributeBuffer = mAttribBufferMap.get(attributeName);
    Preconditions.checkNotNull(attributeBuffer);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Vector2(mAttribBufferMap.get(attributeName)));
    }

    return (Vector2)mExpressionMap.get(attributeName);
  }

  public Vector3 getAttribute3(String attributeName) {
    AttribBuffer attributeBuffer = mAttribBufferMap.get(attributeName);
    Preconditions.checkNotNull(attributeBuffer);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Vector3(mAttribBufferMap.get(attributeName)));
    }

    return (Vector3)mExpressionMap.get(attributeName);
  }

  public Vector4 getAttribute4(String attributeName) {
    AttribBuffer attributeBuffer = mAttribBufferMap.get(attributeName);
    Preconditions.checkNotNull(attributeBuffer);

    if (mExpressionMap.get(attributeName) == null) {
      mExpressionMap.put(attributeName, new Vector4(mAttribBufferMap.get(attributeName)));
    }

    return (Vector4)mExpressionMap.get(attributeName);
  }

  public <T> Drawable bake(
      VectorExpression<T, Vector4> vertexPosition,
      VectorExpression<T, Vector4> fragmentColor) {
    return new Drawable<T>(this, vertexPosition, fragmentColor);
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
