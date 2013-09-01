package com.lfscheidegger.jfacet.facet;

import com.lfscheidegger.jfacet.shade.GlSlType;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.evaluators.glsl.AttributeEvaluator;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;

import java.util.HashMap;
import java.util.Map;

public final class Geometry {

  private final IndexBuffer mIndices;
  private final AttribBuffer mVertices;
  private final Map<String, AttribBuffer> mAttribBufferMap;

  private AttribBuffer mColors;
  private AttribBuffer mTexCoords;
  private AttribBuffer mNormals;

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
    return mColors;
  }

  public AttribBuffer getTexCoordBuffer() {
    return mTexCoords;
  }

  public AttribBuffer getNormalBuffer() {
    return mNormals;
  }

  public AttribBuffer getAttributeBuffer(String key) {
    return mAttribBufferMap.get(key);
  }

  public Vector2 getVertices2() {
    return (Vector2)getExpressionForAttribBuffer(mVertices, 2);
  }

  public Vector3 getVertices3() {
    return (Vector3)getExpressionForAttribBuffer(mVertices, 3);
  }

  public Vector4 getVertices4() {
    return (Vector4)getExpressionForAttribBuffer(mVertices, 4);
  }

  public Vector2 getColors2() {
    return (Vector2)getExpressionForAttribBuffer(mColors, 2);
  }

  public Vector3 getColors3() {
    return (Vector3)getExpressionForAttribBuffer(mColors, 3);
  }

  public Vector4 getColors4() {
    return (Vector4)getExpressionForAttribBuffer(mColors, 4);
  }

  public Vector2 getTexCoords2() {
    return (Vector2)getExpressionForAttribBuffer(mTexCoords, 2);
  }

  public Vector3 getTexCoords3() {
    return (Vector3)getExpressionForAttribBuffer(mTexCoords, 3);
  }

  public Vector4 getTexCoords4() {
    return (Vector4)getExpressionForAttribBuffer(mTexCoords, 4);
  }

  public Vector2 getNormals2() {
    return (Vector2)getExpressionForAttribBuffer(mNormals, 2);
  }

  public Vector3 getNormals3() {
    return (Vector3)getExpressionForAttribBuffer(mNormals, 3);
  }

  public Vector4 getNormals4() {
    return (Vector4)getExpressionForAttribBuffer(mNormals, 4);
  }

  public Vector2 getAttribute2(String key) {
    return (Vector2)getExpressionForAttribBuffer(mAttribBufferMap.get(key), 2);
  }

  public Vector3 getAttribute3(String key) {
    return (Vector3)getExpressionForAttribBuffer(mAttribBufferMap.get(key), 3);
  }

  public Vector4 getAttribute4(String key) {
    return (Vector4)getExpressionForAttribBuffer(mAttribBufferMap.get(key), 4);
  }

  public Drawable bake(Expression vertexPosition, Expression fragmentColor) {
    return new Drawable(this, vertexPosition, fragmentColor);
  }

  private Expression getExpressionForAttribBuffer(AttribBuffer attribBuffer, int dimension) {
    switch(dimension) {
      case 1: return new Real(GlSlType.ATTRIBUTE_T, new AttributeEvaluator<Float>(attribBuffer));
      case 2: return new Vector2(GlSlType.ATTRIBUTE_T, new AttributeEvaluator<Vector2.Primitive>(attribBuffer));
      case 3: return new Vector3(GlSlType.ATTRIBUTE_T, new AttributeEvaluator<Vector3.Primitive>(attribBuffer));
      case 4: return new Vector4(GlSlType.ATTRIBUTE_T, new AttributeEvaluator<Vector4.Primitive>(attribBuffer));
      default:
        throw new RuntimeException("Unsupported dimension for attrib buffer: " + attribBuffer.getDimension());
    }
  }
}
