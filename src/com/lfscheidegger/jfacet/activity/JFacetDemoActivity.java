package com.lfscheidegger.jfacet.activity;

import android.app.Activity;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Display;
import com.lfscheidegger.jfacet.R;
import com.lfscheidegger.jfacet.facet.*;
import com.lfscheidegger.jfacet.facet.renderer.FacetRenderer;
import com.lfscheidegger.jfacet.shade.Parameter;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.camera.Camera;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec3Exp;
import com.lfscheidegger.jfacet.shade.expression.primitives.uniform.FloatUniform;

public class JFacetDemoActivity extends Activity {

  private GLSurfaceView mSurfaceView;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    mSurfaceView = (GLSurfaceView) findViewById(R.id.gl_view);

    Scene scene = new Scene(new Scene.SceneConfig.Builder().build());

    Geometry squareModel = Facet.model(
        ModelType.TRIANGLES,
        new float[] {-1, -1, 1, -1, 1, 1, -1, -1, 1, 1, -1, 1}, 2);
    Geometry triangleModel = Facet.model(
        ModelType.TRIANGLES,
        new float[] {0, 1, -1, -1, 1, -1}, 2,
        new float[] {1, 0, 0, 0, 1, 0, 0, 0, 1}, 3);

    Display display = getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);

    Camera camera = Camera.perspective(size.x, size.y);

    FloatExp angle = Parameter.now().mul(50).radians();

        new FloatUniform(Shade.constant(0));
    Expression squarePosition =
        camera.apply(
        Shade.translation(1.5f, 0, -12).apply(
        Shade.rotation(angle, Shade.vec(1, 0, 0)).apply(
            squareModel.getVertices())));

    Expression trianglePosition =
        camera.apply(
        Shade.translation(-1.5f, 0, -12).apply(
        Shade.rotation(angle, Shade.vec(0, 1, 0)).apply(
            triangleModel.getVertices())));

    Drawable square = Facet.bake(squareModel, squarePosition, Shade.vec(0.5f, 0.5f, 1));
    Drawable triangle = Facet.bake(triangleModel, trianglePosition, Shade.varying((Vec3Exp) triangleModel.getColors()));

    scene.add(square);
    scene.add(triangle);

    mSurfaceView.setRenderer(new FacetRenderer(scene));
  }

  @Override
  public void onResume() {
    super.onStart();

    mSurfaceView.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();

    mSurfaceView.onPause();
  }
}
