package com.lfscheidegger.jfacet.facet;

import com.google.common.collect.ImmutableMap;
import com.lfscheidegger.jfacet.shade.expression.Expression;

public final class Geometry {

  private final GeometryConfig mGeometryConfig;
  private final ImmutableMap<AttribBuffer, Expression> mAttributeMap;

  public Geometry(GeometryConfig geometryConfig) {
    mGeometryConfig = geometryConfig;
    mAttributeMap = buildAttributeMap();
  }

  public ImmutableMap<AttribBuffer, Expression> getAttributeMap() {
    return mAttributeMap;
  }

  public IndexBuffer getIndexBuffer() {
    return mGeometryConfig.getIndexBuffer();
  }

  public Expression getVertices() {
    return mGeometryConfig.getVertices();
  }

  public Expression getColors() {
    return mGeometryConfig.getColors();
  }

  public Expression getTexCoords() {
    return mGeometryConfig.getTexCoords();
  }

  public Expression getNormals() {
    return mGeometryConfig.getNormals();
  }

  private ImmutableMap<AttribBuffer, Expression> buildAttributeMap() {
    ImmutableMap.Builder<AttribBuffer, Expression> builder = new ImmutableMap.Builder<AttribBuffer, Expression>();

    builder.put(mGeometryConfig.getVertexBuffer(), mGeometryConfig.getVertices());

    if (mGeometryConfig.getColorBuffer() != null) {
      builder.put(mGeometryConfig.getColorBuffer(), mGeometryConfig.getColors());
    }

    if (mGeometryConfig.getTexCoordBuffer() != null) {
      builder.put(mGeometryConfig.getTexCoordBuffer(), mGeometryConfig.getTexCoords());
    }

    for (String key : mGeometryConfig.getAttribBuffers().keySet()) {
      builder.put(mGeometryConfig.getAttribBuffer(key), mGeometryConfig.getAttrib(key));
    }

    return builder.build();
  }
}
