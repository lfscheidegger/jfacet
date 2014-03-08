// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet;

import android.opengl.GLES20;
import com.lfscheidegger.jfacet.shade.expression.VecLike;

/**
 * {@link Drawable} objects represent things that {@link Scene} instances can draw. They
 * contain all necessary geometrical information (in the form of a {@link Geometry} object),
 * as well as {@link com.lfscheidegger.jfacet.shade.expression.VecLike} objects representing
 * the geometry's fully transformed position, and final per-fragment color.
 * <p>
 * jfacet maintains a separation between the <i>specification</i> of a geometrical object (represented
 * by a {@link com.lfscheidegger.jfacet.Geometry} object) and the <i>drawing</i> of that object
 * (represented by a {@link Drawable}). This allows you to specify different ways of drawing the
 * same geometry, by assigning different {@link com.lfscheidegger.jfacet.shade.expression.VecLike}
 * objects for vertex positions and colors to a {@link Geometry}, obtaining different instances of
 * {@link com.lfscheidegger.jfacet.Drawable}.
 * <p>
 * {@link Drawable} objects can be instantiated directly, or more conveniently, by calling
 * {@link Geometry#getDrawable(VecLike, VecLike)}.
 * Once instantiated, you add them to a {@link com.lfscheidegger.jfacet.Scene} (using
 * {@link Scene#add(Runnable...)}), and finally draw them using {@link com.lfscheidegger.jfacet.Scene#run()}.
 */
public final class Drawable implements Runnable {

  // Reference to a Geometry with geometric information for this Drawable
  private final Geometry mGeometry;

  // Reference to the Program used to render this Drawable
  private Program mProgram;

  // Expression for the transformed per-vertex position
  private final VecLike mVertexPosition;

  // Expression for the final, per-fragment color
  private final VecLike mFragmentColor;

  /**
   * Default constructor.
   *
   * @param geometry
   *   A {@link Geometry} with the geometric information (vertex positions, colors, normals, etc.)
   *   for this {@link Drawable}
   * @param vertexPosition
   *   A {@link com.lfscheidegger.jfacet.shade.expression.VecLike} object encoding the fully
   *   transformed vertex position for this {@link Drawable}
   * @param fragmentColor
   *   A {@link com.lfscheidegger.jfacet.shade.expression.VecLike} object encoding the final,
   *   per fragment color for this {@link Drawable}
   */
  public Drawable(Geometry geometry, VecLike vertexPosition, VecLike fragmentColor) {
    mVertexPosition = vertexPosition;
    mFragmentColor = fragmentColor;
    mGeometry = geometry;
  }

  public void build() {
    mProgram = new Program(mVertexPosition, mFragmentColor);
    mProgram.bake();
  }

  public void run() {
    mProgram.use();
    GLES20.glDrawElements(
        mGeometry.getPrimitiveType().glMode,
        mGeometry.getIndexBuffer().getElementCount(),
        GLES20.GL_UNSIGNED_INT,
        mGeometry.getIndexBuffer().getBuffer());
  }
}