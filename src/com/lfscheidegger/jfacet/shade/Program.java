package com.lfscheidegger.jfacet.shade;

/**
 * A Shade Program. Encapsulates both fragment and vertex shader
 * operations
 */
public class Program {

  private final Expression mVertexPosition;
  private final Expression mFragmentColor;

  /**
   *
   * @param vertexPosition A {@code Expression} for the vertex coordinates, in homogeneous coordinates
   * @param fragmentColor A {@code Expression} for the color of the final fragment
   */
  public Program(
      Expression vertexPosition,
      Expression fragmentColor) {
    mVertexPosition = vertexPosition;
    mFragmentColor = fragmentColor;
  }
}
