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
import com.lfscheidegger.jfacet.shade.camera.LookAtConfig;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.FloatExp;
import com.lfscheidegger.jfacet.shade.expression.Vec2Exp;
import com.lfscheidegger.jfacet.shade.expression.Vec4Exp;
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
    mSize.x = display.getWidth();
    mSize.y = display.getHeight();

    mView = (FacetView) findViewById(R.id.gl_view);

    Scene scene = new Scene(new Scene.SceneConfig.Builder().build());

    int demo = getIntent().getIntExtra("lesson", -1);
    switch(demo) {
      case 0: prepareLesson2(scene); break;
      case 1: prepareLesson3(scene); break;
      case 2: prepareLesson4(scene); break;
      case 3: prepareLesson5(scene); break;
      case 4: prepareLesson6(scene); break;
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
    Geometry squareModel = Facet.model(new GeometryConfig(
        new int[] {0, 1, 2, 0, 2, 3},
        new float[] {-1, -1, 1, -1, 1, 1, -1, 1}, 2)
    ), triangleModel = Facet.model(new GeometryConfig(
        new int[] {0, 1, 2},
        new float[] {0, 1, -1, -1, 1, -1}, 2));

    Camera camera = Camera.perspective(mSize.x, mSize.y);
    Expression squarePosition = camera.apply(Shade.translation(1.5f, 0, -12)).apply(squareModel.getVertices());
    Expression trianglePosition = camera.apply(Shade.translation(-1.5f, 0, -12)).apply(triangleModel.getVertices());

    Drawable square = Facet.bake(squareModel, squarePosition);
    Drawable triangle = Facet.bake(triangleModel, trianglePosition);

    scene.add(square);
    scene.add(triangle);
  }

  private void prepareLesson3(Scene scene) {
    Geometry squareModel = Facet.model(new GeometryConfig(
        new int[] {0, 1, 2, 0, 2, 3},
        new float[] {-1, -1, 1, -1, 1, 1, -1, 1}, 2
    )), triangleModel = Facet.model(new GeometryConfig(
        new int[]{0, 1, 2},
        new float[]{0, 1, -1, -1, 1, -1}, 2)
        .setColors(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1}, 3));

    Camera camera = Camera.perspective(mSize.x, mSize.y);

    Expression squarePosition = camera.apply(Shade.translation(1.5f, 0, -12)).apply(squareModel.getVertices());
    Expression trianglePosition = camera.apply(Shade.translation(-1.5f, 0, -12)).apply(triangleModel.getVertices());

    Drawable square = Facet.bake(squareModel, squarePosition, Shade.vec(0.5f, 0.5f, 1));
    Drawable triangle = Facet.bake(triangleModel, triangleModel.getColors());

    scene.add(square);
    scene.add(triangle);
  }

  private void prepareLesson4(Scene scene) {
    Geometry squareModel = Facet.model(new GeometryConfig(
        new int[] {0, 1, 2, 0, 2, 3},
        new float[] {-1, -1, 1, -1, 1, 1, -1, 1}, 2
    )), triangleModel = Facet.model(new GeometryConfig(
        new int[] {0, 1, 2},
        new float[] {0, 1, -1, -1, 1, -1}, 2)
        .setColors(new float[] {1, 0, 0, 0, 1, 0, 0, 0, 1}, 3));

    Camera camera = Camera.perspective(mSize.x, mSize.y);
    FloatExp angle = Parameter.now().mul(50).radians();

    Expression squarePosition = camera.apply(
        Shade.translation(1.5f, 0, -12)).apply(
        Shade.rotation(angle, Shade.vec(1, 0, 0))).apply(
        squareModel.getVertices());
    Expression trianglePosition = camera.apply(
        Shade.translation(-1.5f, 0, -12)).apply(
        Shade.rotation(angle, Shade.vec(0, 1, 0))).apply(
        triangleModel.getVertices());

    Drawable square = Facet.bake(squareModel, squarePosition, Shade.vec(0.5f, 0.5f, 1));
    Drawable triangle = Facet.bake(triangleModel, trianglePosition, triangleModel.getColors());

    scene.add(square);
    scene.add(triangle);
  }

  private void prepareLesson5(Scene scene) {
    Geometry cubeModel = Facet.model(new GeometryConfig(
        new int[]{
            0, 1, 2, 0, 2, 3,
            4, 5, 6, 4, 6, 7,
            8, 9, 10, 8, 10, 11,
            12, 13, 14, 12, 14, 15,
            16, 17, 18, 16, 18, 19,
            20, 21, 22, 20, 22, 23},
        new float[]{
            1, 1, -1, -1, 1, -1, -1, 1, 1, 1, 1, 1,
            1, -1, 1, -1, -1, 1, -1, -1, -1, 1, -1, -1,
            1, 1, 1, -1, 1, 1, -1, -1, 1, 1, -1, 1,
            1, -1, -1, -1, -1, -1, -1, 1, -1, 1, 1, -1,
            -1, 1, 1, -1, 1, -1, -1, -1, -1, -1, -1, 1,
            1, 1, -1, 1, 1, 1, 1, -1, 1, 1, -1, -1}, 3)
        .setColors(new float[]{
            0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0,
            1, 0.5f, 0, 1, 0.5f, 0, 1, 0.5f, 0, 1, 0.5f, 0,
            1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0,
            1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0,
            0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1,
            1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1}, 3));


    Geometry pyramidModel = Facet.model(new GeometryConfig(
        new int[] {
            0, 1, 2,
            0, 2, 3,
            0, 3, 4,
            0, 4, 1},
        new float[] {
            0,  1,  0,
            -1, -1,  1,
            -1, -1, -1,
            1, -1, -1,
            1, -1,  1}, 3)
        .setColors(new float[] {
            1, 0, 0,
            0, 1, 0,
            0, 0, 1,
            0, 1, 0,
            0, 0, 1
        }, 3));

    Camera camera = Camera.perspective(mSize.x, mSize.y);
    FloatExp angle = Parameter.now().mul(50).radians();

    Expression cubePosition =
        camera.apply(
            Shade.translation(1.5f, 0, -12)).apply(
            Shade.rotation(angle, Shade.vec(1, 1, 1))).apply(
            cubeModel.getVertices());

    Expression pyramidPosition =
        camera.apply(
        Shade.translation(-1.5f, 0, -12)).apply(
        Shade.rotation(angle, Shade.vec(0, 1, 0))).apply(
        cubeModel.getVertices());

    scene.add(Facet.bake(cubeModel, cubePosition, cubeModel.getColors()));
    scene.add(Facet.bake(pyramidModel, pyramidPosition, pyramidModel.getColors()));
  }

  private void prepareLesson6(Scene scene) {
    Geometry cube = Model.flatCube();

    Camera camera = Camera.perspective(
        new LookAtConfig()
            .setEye(Shade.vec(0, 0, 12))
            .setCenter(Shade.vec(0, 0, -1))
            .setUp(Shade.vec(0, 1, 0)), mSize.x, mSize.y);

    FloatExp angle = Parameter.now().mul(50).radians();

    scene.add(Facet.bake(
        cube,
        camera
            .apply(Shade.rotation(angle, Shade.vec(1, 1, 1)))
            .apply(cube.getVertices()),
        Shade.texture2D(
            Facet.texture(getResources(), R.drawable.nehe),
            Shade.varying2f((Vec2Exp) cube.getTexCoords()))));
  }
}
