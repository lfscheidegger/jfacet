package com.lfscheidegger.jfacet.activity;

import android.app.Activity;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Display;
import com.lfscheidegger.jfacet.R;
import com.lfscheidegger.jfacet.facet.*;
import com.lfscheidegger.jfacet.facet.renderer.FacetRenderer;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.camera.Camera;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.primitives.Vec2Exp;
import com.lfscheidegger.jfacet.shade.primitives.Mat2;
import com.lfscheidegger.jfacet.shade.primitives.Mat4;
import com.lfscheidegger.jfacet.shade.primitives.Vec4;

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
        new float[] {0, 1, -1, -1, 1, -1}, 2);

    Display display = getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);

    Camera camera = Camera.perspective(size.x, size.y);
    Expression squarePosition = camera.apply(Shade.translation(1.5f, 0, -12).apply(squareModel.getVertices()));
    Expression trianglePosition = camera.apply(Shade.translation(-1.5f, 0, -12).apply(triangleModel.getVertices()));

    Drawable square = Facet.bake(squareModel, squarePosition);
    Drawable triangle = Facet.bake(triangleModel, trianglePosition);

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
