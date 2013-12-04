package com.lfscheidegger.jfacet.facet;

import com.google.common.base.Preconditions;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public final class Geometry {

  private final IndexBuffer mIndices;
  private final AttribBuffer mVertices;
  private final Map<String, AttribBuffer> mAttribBufferMap;

  private @Nullable AttribBuffer mColors;
  private @Nullable AttribBuffer mTexCoords;
  private @Nullable AttribBuffer mNormals;

  public Geometry(int[] indices, float[] vertices, int vertexDimension) {
    mIndices = new IndexBuffer(indices);
    mVertices = new AttribBuffer(vertices, vertexDimension);
    mAttribBufferMap = new HashMap<String, AttribBuffer>();
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

  public AttribBuffer getVertexBuffer() {
    return mVertices;
  }

  public AttribBuffer getColorBuffer() {
    return Preconditions.checkNotNull(mColors);
  }

  public AttribBuffer getTexCoordBuffer() {
    return Preconditions.checkNotNull(mTexCoords);
  }

  public AttribBuffer getNormalBuffer() {
    return Preconditions.checkNotNull(mNormals);
  }

  public AttribBuffer getAttributeBuffer(String key) {
    return Preconditions.checkNotNull(mAttribBufferMap.get(key));
  }

  public Vector2 getVertices2() {
    return new Vector2(mVertices);
  }

  public Vector3 getVertices3() {
    return new Vector3(mVertices);
  }

  public Vector4 getVertices4() {
    return new Vector4(mVertices);
  }

  public Vector2 getColors2() {
    Preconditions.checkNotNull(mColors);
    return new Vector2(mColors);
  }

  public Vector3 getColors3() {
    Preconditions.checkNotNull(mColors);
    return new Vector3(mColors);
  }

  public Vector4 getColors4() {
    Preconditions.checkNotNull(mColors);
    return new Vector4(mColors);
  }

  public Vector2 getTexCoords2() {
    Preconditions.checkNotNull(mTexCoords);
    return new Vector2(mTexCoords);
  }

  public Vector3 getTexCoords3() {
    Preconditions.checkNotNull(mTexCoords);
    return new Vector3(mTexCoords);
  }

  public Vector4 getTexCoords4() {
    Preconditions.checkNotNull(mTexCoords);
    return new Vector4(mTexCoords);
  }

  public Vector2 getNormals2() {
    Preconditions.checkNotNull(mNormals);
    return new Vector2(mNormals);
  }

  public Vector3 getNormals3() {
    Preconditions.checkNotNull(mNormals);
    return new Vector3(mNormals);
  }

  public Vector4 getNormals4() {
    Preconditions.checkNotNull(mNormals);
    return new Vector4(mNormals);
  }

  public Vector2 getAttribute2(String key) {
    AttribBuffer attributeBuffer = mAttribBufferMap.get(key);
    Preconditions.checkNotNull(attributeBuffer);
    return new Vector2(attributeBuffer);
  }

  public Vector3 getAttribute3(String key) {
    AttribBuffer attributeBuffer = mAttribBufferMap.get(key);
    Preconditions.checkNotNull(attributeBuffer);
    return new Vector3(attributeBuffer);
  }

  public Vector4 getAttribute4(String key) {
    AttribBuffer attributeBuffer = mAttribBufferMap.get(key);
    Preconditions.checkNotNull(attributeBuffer);
    return new Vector4(attributeBuffer);
  }

  public Drawable bake(Expression vertexPosition, Expression fragmentColor) {
    return new Drawable(this, vertexPosition, fragmentColor);
  }
}
