package com.lfscheidegger.jfacet.facet;

import com.google.common.collect.ImmutableMap;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.expression.Expression;

import java.util.HashMap;
import java.util.Map;

public final class GeometryConfig {

  private final IndexBuffer mIndices;
  private final AttribBuffer mVertices;
  private final Map<String, AttribBuffer> mAttribBufferMap;
  private final Map<AttribBuffer, Expression> mExpressionMap;

  private AttribBuffer mColors;
  private AttribBuffer mTexCoords;
  private AttribBuffer mNormals;

  public GeometryConfig(int[] indices, float[] vertices, int vertexDimension) {
    mIndices = new IndexBuffer(indices);
    mVertices = new AttribBuffer(vertices, vertexDimension);
    mAttribBufferMap = new HashMap<String, AttribBuffer>();
    mExpressionMap = new HashMap<AttribBuffer, Expression>();
  }

  public Expression getVertices() {
    return getExpressionForAttribBuffer(mVertices);
  }

  public AttribBuffer getVertexBuffer() {
    return mVertices;
  }

  public IndexBuffer getIndexBuffer() {
    return mIndices;
  }

  public GeometryConfig setColors(float[] colors, int dimension) {
    mColors = new AttribBuffer(colors, dimension);
    return this;
  }

  public Expression getColors() {
    return getExpressionForAttribBuffer(mColors);
  }

  public AttribBuffer getColorBuffer() {
    return mColors;
  }

  public GeometryConfig setTexCoords(float[] texCoords, int dimension) {
    mTexCoords = new AttribBuffer(texCoords, dimension);
    return this;
  }

  public Expression getTexCoords() {
    return getExpressionForAttribBuffer(mTexCoords);
  }

  public AttribBuffer getTexCoordBuffer() {
    return mTexCoords;
  }

  public GeometryConfig setNormals(float[] normals, int dimension) {
    mNormals = new AttribBuffer(normals, dimension);
    return this;
  }

  public Expression getNormals() {
    return getExpressionForAttribBuffer(mNormals);
  }

  public AttribBuffer getNormalBuffer() {
    return mNormals;
  }

  public GeometryConfig setAttribBuffer(String name, float[] values, int dimension) {
    mAttribBufferMap.put(name, new AttribBuffer(values, dimension));
    return this;
  }

  public ImmutableMap<String, AttribBuffer> getAttribBuffers() {
    return ImmutableMap.copyOf(mAttribBufferMap);
  }

  public AttribBuffer getAttribBuffer(String name) {
    return mAttribBufferMap.get(name);
  }

  public Expression getAttrib(String name) {
    return getExpressionForAttribBuffer(mAttribBufferMap.get(name));
  }

  private Expression getExpressionForAttribBuffer(AttribBuffer attribBuffer) {
    return null;
    /*Expression expression;

    if (attribBuffer != null) {
      if (mExpressionMap.containsKey(attribBuffer)) {
        return mExpressionMap.get(attribBuffer);
      }

      switch(attribBuffer.getDimension()) {
        case 1: expression = Shade.attribute1f(); break;
        case 2: expression =  Shade.attribute2f(); break;
        case 3: expression =  Shade.attribute3f(); break;
        case 4: expression = Shade.attribute4f(); break;
        default:
          throw new RuntimeException("Unsupported dimension for attrib buffer: " + attribBuffer.getDimension());
      }
    } else {
      throw new RuntimeException("Attempting to get attrib buffer without setting it");
    }

    mExpressionMap.put(attribBuffer, expression);
    return expression;*/
  }
}
