// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.lfscheidegger.jfacet.shade.expression.*;
import com.lfscheidegger.jfacet.shade.expression.Vec2;

import javax.annotation.Nullable;
import java.util.Map;

public final class Geometry {

  private final IndexBuffer mIndices;
  private final AttributeBuffer mVertices;
  private final Map<String, AttributeBuffer> mAttributeBuffers;
  private final Map<String, Expression> mExpressions;

  private @Nullable AttributeBuffer mColors;
  private @Nullable AttributeBuffer mTexCoords;
  private @Nullable AttributeBuffer mNormals;

  public Geometry(int[] indices, float[] vertices, int vertexDimension) {
    mIndices = new IndexBuffer(indices);
    mVertices = new AttributeBuffer(vertices, vertexDimension);
    mAttributeBuffers = Maps.newHashMap();
    mExpressions = Maps.newHashMap();
  }

  public Geometry setColors(float[] colors, int dimension) {
    mColors = new AttributeBuffer(colors, dimension);
    return this;
  }

  public Geometry setTexCoords(float[] texCoords, int dimension) {
    mTexCoords = new AttributeBuffer(texCoords, dimension);
    return this;
  }

  public Geometry setNormals(float[] normals, int dimension) {
    mNormals = new AttributeBuffer(normals, dimension);
    return this;
  }

  public Geometry setAttributeValues(float[] values, int dimension, String key) {
    mAttributeBuffers.put(key, new AttributeBuffer(values, dimension));
    return this;
  }

  public IndexBuffer getIndexBuffer() {
    return mIndices;
  }

  public Real getVertices1() {
    String attributeName = getNameForVertices(1);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Real(mVertices));
    }

    return (Real) mExpressions.get(attributeName);
  }

  public Vec2 getVertices2() {
    String attributeName = getNameForVertices(2);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Vec2(mVertices));
    }

    return (Vec2) mExpressions.get(attributeName);
  }

  public Vec3 getVertices3() {
    String attributeName = getNameForVertices(3);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Vec3(mVertices));
    }

    return (Vec3) mExpressions.get(attributeName);
  }

  public Vec4 getVertices4() {
    String attributeName = getNameForVertices(4);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Vec4(mVertices));
    }

    return (Vec4) mExpressions.get(attributeName);
  }

  public Real getColors1() {
    Preconditions.checkNotNull(mColors);
    String attributeName = getNameForColors(1);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Real(mColors));
    }

    return (Real) mExpressions.get(attributeName);
  }

  public Vec2 getColors2() {
    Preconditions.checkNotNull(mColors);
    String attributeName = getNameForColors(2);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Vec2(mColors));
    }

    return (Vec2) mExpressions.get(attributeName);
  }

  public Vec3 getColors3() {
    Preconditions.checkNotNull(mColors);
    String attributeName = getNameForColors(3);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Vec3(mColors));
    }

    return (Vec3) mExpressions.get(attributeName);
  }

  public Vec4 getColors4() {
    Preconditions.checkNotNull(mColors);
    String attributeName = getNameForColors(4);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Vec4(mColors));
    }

    return (Vec4) mExpressions.get(attributeName);
  }

  public Real getTexCoords1() {
    Preconditions.checkNotNull(mTexCoords);
    String attributeName = getNameForTexCoords(1);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Real(mColors));
    }

    return (Real) mExpressions.get(attributeName);
  }

  public Vec2 getTexCoords2() {
    Preconditions.checkNotNull(mTexCoords);
    String attributeName = getNameForTexCoords(2);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Vec2(mTexCoords));
    }

    return (Vec2) mExpressions.get(attributeName);
  }

  public Vec3 getTexCoords3() {
    Preconditions.checkNotNull(mTexCoords);
    String attributeName = getNameForTexCoords(3);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Vec3(mTexCoords));
    }

    return (Vec3) mExpressions.get(attributeName);
  }

  public Vec4 getTexCoords4() {
    Preconditions.checkNotNull(mTexCoords);
    String attributeName = getNameForTexCoords(4);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Vec4(mTexCoords));
    }

    return (Vec4) mExpressions.get(attributeName);
  }

  public Real getNormals1() {
    Preconditions.checkNotNull(mNormals);
    String attributeName = getNameForNormals(1);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Real(mNormals));
    }

    return (Real) mExpressions.get(attributeName);
  }

  public Vec2 getNormals2() {
    Preconditions.checkNotNull(mNormals);
    String attributeName = getNameForNormals(2);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Vec2(mNormals));
    }

    return (Vec2) mExpressions.get(attributeName);
  }

  public Vec3 getNormals3() {
    Preconditions.checkNotNull(mNormals);
    String attributeName = getNameForNormals(3);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Vec3(mNormals));
    }

    return (Vec3) mExpressions.get(attributeName);
  }

  public Vec4 getNormals4() {
    Preconditions.checkNotNull(mNormals);
    String attributeName = getNameForNormals(4);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Vec4(mNormals));
    }

    return (Vec4) mExpressions.get(attributeName);
  }

  public Real getAttribute1(String attributeName) {
    AttributeBuffer attributeBuffer = mAttributeBuffers.get(attributeName);
    Preconditions.checkNotNull(attributeBuffer);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Real(mAttributeBuffers.get(attributeName)));
    }

    return (Real) mExpressions.get(attributeName);
  }

  public Vec2 getAttribute2(String attributeName) {
    AttributeBuffer attributeBuffer = mAttributeBuffers.get(attributeName);
    Preconditions.checkNotNull(attributeBuffer);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Vec2(mAttributeBuffers.get(attributeName)));
    }

    return (Vec2) mExpressions.get(attributeName);
  }

  public Vec3 getAttribute3(String attributeName) {
    AttributeBuffer attributeBuffer = mAttributeBuffers.get(attributeName);
    Preconditions.checkNotNull(attributeBuffer);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Vec3(mAttributeBuffers.get(attributeName)));
    }

    return (Vec3) mExpressions.get(attributeName);
  }

  public Vec4 getAttribute4(String attributeName) {
    AttributeBuffer attributeBuffer = mAttributeBuffers.get(attributeName);
    Preconditions.checkNotNull(attributeBuffer);

    if (mExpressions.get(attributeName) == null) {
      mExpressions.put(attributeName, new Vec4(mAttributeBuffers.get(attributeName)));
    }

    return (Vec4) mExpressions.get(attributeName);
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
