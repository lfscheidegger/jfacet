package com.lfscheidegger.jfacet.activity;

import android.app.Activity;
import android.graphics.Point;
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
import com.lfscheidegger.jfacet.view.FacetView;

public class JFacetDemoActivity extends Activity {

  private FacetView mView;
  private Point mSize;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main);

    mSize = new Point();
    Display display = getWindowManager().getDefaultDisplay();
    display.getSize(mSize);

    mView = (FacetView) findViewById(R.id.gl_view);

    Scene scene = new Scene(new Scene.SceneConfig.Builder().build());

    int demo = getIntent().getIntExtra("lesson", -1);
    switch(demo) {
      case 0: prepareLesson2(scene); break;
      case 1: prepareLesson3(scene); break;
      case 2: prepareLesson4(scene); break;
    }

    mView.setRenderer(new FacetRenderer(scene));
  }

  @Override
  public void onResume() {
    super.onResume();
    mView.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();
    mView.onPause();
  }

  private void prepareLesson2(Scene scene) {
    Geometry squareModel = Facet.model(
        ModelType.TRIANGLES,
        new float[] {-1, -1, 1, -1, 1, 1, -1, -1, 1, 1, -1, 1}, 2
    ), triangleModel = Facet.model(
        ModelType.TRIANGLES,
        new float[] {0, 1, -1, -1, 1, -1}, 2
    );

    Camera camera = Camera.perspective(mSize.x, mSize.y);
    Expression squarePosition = camera.apply(Shade.translation(1.5f, 0, -12).apply(squareModel.getVertices()));
    Expression trianglePosition = camera.apply(Shade.translation(-1.5f, 0, -12).apply(triangleModel.getVertices()));

    Drawable square = Facet.bake(squareModel, squarePosition);
    Drawable triangle = Facet.bake(triangleModel, trianglePosition);

    scene.add(square);
    scene.add(triangle);
  }

  private void prepareLesson3(Scene scene) {
    Geometry squareModel = Facet.model(
        ModelType.TRIANGLES,
        new float[] {-1, -1, 1, -1, 1, 1, -1, -1, 1, 1, -1, 1}, 2
    ), triangleModel = Facet.model(
        ModelType.TRIANGLES,
        new float[] {0, 1, -1, -1, 1, -1}, 2,
        new float[] {1, 0, 0, 0, 1, 0, 0, 0, 1}, 3
    );

    Camera camera = Camera.perspective(mSize.x, mSize.y);
    Expression squarePosition = camera.apply(Shade.translation(1.5f, 0, -12).apply(squareModel.getVertices()));
    Expression trianglePosition = camera.apply(Shade.translation(-1.5f, 0, -12).apply(triangleModel.getVertices()));

    Drawable square = Facet.bake(squareModel, squarePosition, Shade.vec(0.5f, 0.5f, 1));
    Drawable triangle = Facet.bake(triangleModel, trianglePosition, triangleModel.getColors());

    scene.add(square);
    scene.add(triangle);
  }

  private void prepareLesson4(Scene scene) {
    Geometry squareModel = Facet.model(
        ModelType.TRIANGLES,
        new float[] {-1, -1, 1, -1, 1, 1, -1, -1, 1, 1, -1, 1}, 2
    ), triangleModel = Facet.model(
        ModelType.TRIANGLES,
        new float[] {0, 1, -1, -1, 1, -1}, 2,
        new float[] {1, 0, 0, 0, 1, 0, 0, 0, 1}, 3
    );

    Camera camera = Camera.perspective(mSize.x, mSize.y);
    FloatExp angle = Parameter.now().mul(50).radians();

    Expression squarePosition = camera.apply(
        Shade.translation(1.5f, 0, -12).apply(
        Shade.rotation(angle, Shade.vec(1, 0, 0)).apply(
        squareModel.getVertices())));
    Expression trianglePosition = camera.apply(
        Shade.translation(-1.5f, 0, -12).apply(
        Shade.rotation(angle, Shade.vec(0, 1, 0)).apply(
        triangleModel.getVertices())));

    Drawable square = Facet.bake(squareModel, squarePosition, Shade.vec(0.5f, 0.5f, 1));
    Drawable triangle = Facet.bake(triangleModel, trianglePosition, Shade.varying((Vec3Exp)triangleModel.getColors()));

    scene.add(square);
    scene.add(triangle);
  }
}
