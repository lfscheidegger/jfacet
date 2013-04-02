package com.lfscheidegger.jfacet.facet;

import java.nio.FloatBuffer;

public class Geometry {

  private final ModelType mType;
  private final FloatBuffer mVertexPositionBuffer;

  public Geometry(ModelType type, FloatBuffer vertexPositionBuffer) {
    mType = type;
    mVertexPositionBuffer = vertexPositionBuffer;
  }
}
