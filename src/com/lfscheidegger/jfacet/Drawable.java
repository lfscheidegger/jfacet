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
 * objects for vertex positions and fragment colors to a {@link Geometry}, obtaining different instances of
 * {@link com.lfscheidegger.jfacet.Drawable}.
 * <p>
 * In order to draw an object, jfacet tells the GPU to transform the raw vertex coordinates supplied
 * to a {@link Geometry} by the first {@link com.lfscheidegger.jfacet.shade.expression.VecLike} expression
 * passed in to {@link Drawable}'s constructor. It does the same for per-fragment colors, using the
 * second {@link com.lfscheidegger.jfacet.shade.expression.VecLike} passed in. As an example,
 * consider a {@link Geometry} that contains raw vertex data for a single, red, triangle. In order
 * to obtain its {@link Drawable}, you simply instantiate it:
 * <pre>
 *   Geometry triangleGeometry; // contains geometry for a single, red, triangle
 *   Drawable triangleDrawable = new Drawable(
 *     triangleGeometry,
 *     triangleGeometry.getVertices2(),
 *     triangleGeometry.getColors3());
 * </pre>
 * As a convenience, you can also obtain a {@link Drawable} directly from a {@link Geometry}:
 * <pre>
 *   Drawable triangleDrawable = triangleGeometry.getDrawable(
 *     triangleGeometry.getVertices2(),
 *     triangleGeometry.getColors3());
 * </pre>
 * This demonstrates the basics of {@link Drawable}, but jfacet supports a lot more power and
 * flexibility. We can illustrate this by scaling the triangle from the previous example. If
 * we want to render that triangle, but to half its size, we can use an
 * {@link com.lfscheidegger.jfacet.shade.expression.Expression} to help us:
 * <pre>
 *   // returns a new Vec3 that is half the size of the original
 *   Vec3 scaledVertexPosition = triangleGeometry.getVertices3().times(0.5f);
 *
 *   Drawable triangleDrawable = triangleGeometry.getDrawable(
 *     scaledVertexPosition,
 *     triangleGeometry.getColors3());
 * </pre>
 * {@link com.lfscheidegger.jfacet.shade.expression.Expression} objects can be combined in virtually
 * infinite ways to completely customize the drawing of {@link Drawable} objects. They support
 * most built-in GPU functionality, texture lookups, and even user input. Their
 * combinations constitute the majority of the power behind jfacet (for more details, see the
 * documentation for {@link com.lfscheidegger.jfacet.shade.expression.Expression}).
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

  /**
   * Gets the {@link Geometry} associated with this {@link Drawable}.
   *
   * @return
   *   The {@link Geometry} containing the geometrical information for this {@link Drawable}
   */
  public Geometry getGeometry() {
    return mGeometry;
  }

  /**
   * Gets the {@link com.lfscheidegger.jfacet.shade.expression.VecLike} object used to transform vertex positions
   * into their final, drawable positions.
   * <p>
   * {@link Drawable} draws its contents by transforming the raw vertex coordinates passed
   * in to the constructor (in a {@link Geometry}) using a {@link com.lfscheidegger.jfacet.shade.expression.VecLike}
   * object. This method returns that object.
   *
   * @return
   *   The {@link com.lfscheidegger.jfacet.shade.expression.VecLike} object used to transform
   *   raw vertex positions into final positions for drawing
   */
  public VecLike getVertexPosition() {
    return mVertexPosition;
  }

  /**
   * Gets the {@link com.lfscheidegger.jfacet.shade.expression.VecLike} object whose value is used as the final
   * per-fragment color for this {@link com.lfscheidegger.jfacet.Drawable}.
   * <p>
   * {@link Drawable} colors fragments that it draws using the value of this {@link com.lfscheidegger.jfacet.shade.expression.VecLike}.
   * For example: to draw a solid red object, you pass a {@code new Vec3(1, 0, 0)} into
   * {@link Drawable}'s constructor. This method returns that expression.
   *
   * @return
   *   The {@link com.lfscheidegger.jfacet.shade.expression.VecLike} object that represents
   *   the final, per-fragment color for this {@link com.lfscheidegger.jfacet.Drawable}
   */
  public VecLike getFragmentColor() {
    return mFragmentColor;
  }

  /**
   * Builds this {@link Drawable}, making it ready for drawing.
   * <p>
   * Before {@link Drawable} objects can be drawn, jfacet needs to transform its vertex position
   * and fragment color {@link com.lfscheidegger.jfacet.shade.expression.VecLike} objects into
   * GPU code, and load that code into the GPU. This method does exactly that, and must be called
   * before drawing can occur. Typically, however, you do not need to call this manually. When you add
   * {@link Drawable} objects to a {@link com.lfscheidegger.jfacet.Scene}, and use
   * {@link com.lfscheidegger.jfacet.renderer.FacetRenderer} for displaying your graphics, this
   * method gets called automatically for you.
   * <p>
   * This method can only be called once a valid OpenGL context exists. Multiple calls to this
   * method do nothing.
   */
  public void build() {
    if (mProgram != null) {
      return;
    }

    mProgram = new Program(mVertexPosition, mFragmentColor);
    mProgram.bake();
  }

  /**
   * Returns if this {@link Drawable} is built.
   * <p>
   * {@link Drawable} objects must be built before they can be used for drawing. The process of
   * building them generates GPU code for the object being drawn, and loads that code into the GPU.
   * This method returns {@code true} if the build process is complete (see {@link #build()}).
   *
   * @return
   *   {@code true} if this {@link com.lfscheidegger.jfacet.Drawable} is built, {@code false} otherwise
   */
  public boolean isBuilt() {
    return mProgram != null;
  }

  /**
   * Draws this object.
   * <p>
   * A call to {@link #run()} immediately draws this object into the current OpenGL context. Typically, you
   * don't need to call this manually - it is done for you in {@link com.lfscheidegger.jfacet.renderer.FacetRenderer}.
   * However, if you are implementing your own {@link android.opengl.GLSurfaceView.Renderer}, you'll
   * want to have it call this method.
   */
  @Override
  public void run() {
    mProgram.use();
    GLES20.glDrawElements(
        mGeometry.getPrimitiveType().glMode,
        mGeometry.getIndexBuffer().getElementCount(),
        GLES20.GL_UNSIGNED_INT,
        mGeometry.getIndexBuffer().getBuffer());
  }
}