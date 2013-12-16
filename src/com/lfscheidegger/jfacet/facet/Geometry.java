package com.lfscheidegger.jfacet.facet;

import com.google.common.base.Preconditions;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;
import com.lfscheidegger.jfacet.shade.expression.vector.VectorExpression;

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
    return Preconditions.checkNotNull(mColors, "Color buffer was not specified");
  }

  public AttribBuffer getTexCoordBuffer() {
    return Preconditions.checkNotNull(mTexCoords, "Texture coordinate buffer was not specified");
  }

  public AttribBuffer getNormalBuffer() {
    return Preconditions.checkNotNull(mNormals, "Normal buffer was not specified");
  }

  public AttribBuffer getAttributeBuffer(String key) {
    return Preconditions.checkNotNull(
        mAttribBufferMap.get(key),
        "Attribute buffer '%s' was not specified", key);
  }

  public Real getVertices1() {
    Preconditions.checkArgument(mVertices.getDimension() == 1);
    return new Real(mVertices);
  }

  public Vector2 getVertices2() {
    Preconditions.checkArgument(mVertices.getDimension() == 2);
    return new Vector2(mVertices);
  }

  public Vector3 getVertices3() {
    Preconditions.checkArgument(mVertices.getDimension() == 3);
    return new Vector3(mVertices);
  }

  public Vector4 getVertices4() {
    Preconditions.checkArgument(mVertices.getDimension() == 4);
    return new Vector4(mVertices);
  }

  public Real getColors1() {
    Preconditions.checkNotNull(mColors);
    Preconditions.checkArgument(mColors.getDimension() == 1);

    return new Real(mColors);
  }

  public Vector2 getColors2() {
    Preconditions.checkNotNull(mColors);
    Preconditions.checkArgument(mColors.getDimension() == 2);

    return new Vector2(mColors);
  }

  public Vector3 getColors3() {
    Preconditions.checkNotNull(mColors);
    Preconditions.checkArgument(mColors.getDimension() == 3);

    return new Vector3(mColors);
  }

  public Vector4 getColors4() {
    Preconditions.checkNotNull(mColors);
    Preconditions.checkArgument(mColors.getDimension() == 4);

    return new Vector4(mColors);
  }

  public Real getTexCoords1() {
    Preconditions.checkNotNull(mTexCoords);
    Preconditions.checkArgument(mTexCoords.getDimension() == 1);

    return new Real(mTexCoords);
  }

  public Vector2 getTexCoords2() {
    Preconditions.checkNotNull(mTexCoords);
    Preconditions.checkArgument(mTexCoords.getDimension() == 2);

    return new Vector2(mTexCoords);
  }

  public Vector3 getTexCoords3() {
    Preconditions.checkNotNull(mTexCoords);
    Preconditions.checkArgument(mTexCoords.getDimension() == 3);

    return new Vector3(mTexCoords);
  }

  public Vector4 getTexCoords4() {
    Preconditions.checkNotNull(mTexCoords);
    Preconditions.checkArgument(mTexCoords.getDimension() == 4);

    return new Vector4(mTexCoords);
  }

  public Real getNormals1() {
    Preconditions.checkNotNull(mNormals);
    Preconditions.checkArgument(mNormals.getDimension() == 1);

    return new Real(mNormals);
  }

  public Vector2 getNormals2() {
    Preconditions.checkNotNull(mNormals);
    Preconditions.checkArgument(mNormals.getDimension() == 2);

    return new Vector2(mNormals);
  }

  public Vector3 getNormals3() {
    Preconditions.checkNotNull(mNormals);
    Preconditions.checkArgument(mNormals.getDimension() == 3);

    return new Vector3(mNormals);
  }

  public Vector4 getNormals4() {
    Preconditions.checkNotNull(mNormals);
    Preconditions.checkArgument(mNormals.getDimension() == 4);

    return new Vector4(mNormals);
  }

  public Real getAttribute1(String key) {
    AttribBuffer attributeBuffer = mAttribBufferMap.get(key);
    Preconditions.checkNotNull(attributeBuffer);
    Preconditions.checkArgument(attributeBuffer.getDimension() == 1);

    return new Real(attributeBuffer);
  }

  public Vector2 getAttribute2(String key) {
    AttribBuffer attributeBuffer = mAttribBufferMap.get(key);
    Preconditions.checkNotNull(attributeBuffer);
    Preconditions.checkArgument(attributeBuffer.getDimension() == 2);

    return new Vector2(attributeBuffer);
  }

  public Vector3 getAttribute3(String key) {
    AttribBuffer attributeBuffer = mAttribBufferMap.get(key);
    Preconditions.checkNotNull(attributeBuffer);
    Preconditions.checkArgument(attributeBuffer.getDimension() == 3);

    return new Vector3(attributeBuffer);
  }

  public Vector4 getAttribute4(String key) {
    AttribBuffer attributeBuffer = mAttribBufferMap.get(key);
    Preconditions.checkNotNull(attributeBuffer);
    Preconditions.checkArgument(attributeBuffer.getDimension() == 4);

    return new Vector4(attributeBuffer);
  }

  public <T> Drawable bake(
      VectorExpression<T, Vector4> vertexPosition,
      VectorExpression<T, Vector4> fragmentColor) {
    return new Drawable<T>(this, vertexPosition, fragmentColor);
  }
}
