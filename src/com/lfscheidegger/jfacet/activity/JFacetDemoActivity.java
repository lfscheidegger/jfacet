package com.lfscheidegger.jfacet.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Display;
import com.lfscheidegger.jfacet.R;
import com.lfscheidegger.jfacet.facet.Drawable;
import com.lfscheidegger.jfacet.facet.Geometry;
import com.lfscheidegger.jfacet.facet.Models;
import com.lfscheidegger.jfacet.facet.Scene;
import com.lfscheidegger.jfacet.facet.renderer.FacetRenderer;
import com.lfscheidegger.jfacet.shade.Parameter;
import com.lfscheidegger.jfacet.shade.Shade;
import com.lfscheidegger.jfacet.shade.camera.Camera;
import com.lfscheidegger.jfacet.shade.camera.LookAtConfig;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;
import com.lfscheidegger.jfacet.shade.transform.Transform4;
import com.lfscheidegger.jfacet.shade.transform.Translation4;
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

    Scene scene = new Scene();

    int demo = getIntent().getIntExtra("lesson", -1);
    switch(demo) {
      case 0: prepareLesson0(scene); break;
      case 1: prepareLesson2(scene); break;
      case 2: prepareLesson3(scene); break;
      case 3: prepareLesson4(scene); break;
      case 4: prepareLesson5(scene); break;
      case 5: prepareLesson6(scene); break;
      case 6: prepareLesson7(scene); break;
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

  private void prepareLesson0(Scene scene) {
    Geometry triangleModel = new Geometry(
        new int[] {0, 1, 2},
        new float[] {
            0, 0,
            1, 0,
            1, 1}, 2)
        .setColors(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1}, 3);
    Drawable triangle = triangleModel.bake(
        triangleModel.getVertices2(),
        triangleModel.getColors3());

    scene.add(triangle);
  }

  private void prepareLesson2(Scene scene) {
    Geometry squareModel = new Geometry(
        new int[] {0, 1, 2, 0, 2, 3},
        new float[] {-1, -1, 1, -1, 1, 1, -1, 1}, 2),
        triangleModel = new Geometry(
            new int[] {0, 1, 2},
            new float[] {0, 1, -1, -1, 1, -1}, 2);

    Camera camera = Camera.perspective(mSize.x, mSize.y);

    Vector4 squarePosition = camera.apply(Shade.translate(1.5f, 0, -12)).apply(squareModel.getVertices4());
    Vector4 trianglePosition = camera.apply(Shade.translate(-1.5f, 0, -12)).apply(triangleModel.getVertices4());

    Drawable square = squareModel.bake(squarePosition, Shade.vec(1, 1, 1));
    Drawable triangle = triangleModel.bake(trianglePosition, Shade.vec(1, 1, 1));

    scene.add(square, triangle);
  }

  private void prepareLesson3(Scene scene) {
    Geometry squareModel = new Geometry(
        new int[] {0, 1, 2, 0, 2, 3},
        new float[] {-1, -1, 1, -1, 1, 1, -1, 1}, 2
    ), triangleModel = new Geometry(
        new int[]{0, 1, 2},
        new float[]{0, 1, 0, -1, -1, 0, 1, -1, 0}, 3)
        .setColors(new float[]{
            1, 0, 0,
            0, 1, 0,
            0, 0, 1}, 3);

    Camera camera = Camera.perspective(mSize.x, mSize.y);

    Vector4 squarePosition = camera.apply(new Translation4(1.5f, 0, -12)).apply(squareModel.getVertices4());
    Vector4 trianglePosition = camera.apply(new Translation4(-1.5f, 0, -12)).apply(triangleModel.getVertices4());

    Drawable square = squareModel.bake(squarePosition, Shade.vec(0.5f, 0.5f, 1));
    Drawable triangle = triangleModel.bake(trianglePosition, triangleModel.getColors3());

    scene.add(square, triangle);
  }

  private void prepareLesson4(Scene scene) {
    Geometry squareModel = new Geometry(
        new int[] {0, 1, 2, 0, 2, 3},
        new float[] {-1, -1, 1, -1, 1, 1, -1, 1}, 2
    ), triangleModel = new Geometry(
        new int[] {0, 1, 2},
        new float[] {0, 1, -1, -1, 1, -1}, 2)
        .setColors(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1}, 3);

    Camera camera = Camera.perspective(mSize.x, mSize.y);
    final Real param = Parameter.real(0);
    Real angle = param.mul(50).radians();

    Vector4 squarePosition = camera
        .apply(Shade.translate(1.5f, 0, -12))
        .apply(Shade.rotate(angle, Shade.vec(1, 0, 0)))
        .apply(squareModel.getVertices4()),

        trianglePosition = camera
        .apply(Shade.translate(-1.5f, 0, -12))
        .apply(Shade.rotate(angle, Shade.vec(0, 1, 0)))
        .apply(triangleModel.getVertices4());

    Drawable square = squareModel.bake(squarePosition, Shade.vec(0.5f, 0.5f, 1)),
        triangle = triangleModel.bake(trianglePosition, triangleModel.getColors3());

    scene
        .add(square, triangle)
        .add(new Runnable() {
          @Override
          public void run() {
            Parameter.set(param, (float) SystemClock.uptimeMillis() / 1000);
          }
        });
  }

  private void prepareLesson5(Scene scene) {
    Geometry
        cubeModel = Models.flatCube(),
        pyramidModel = Models.flatPyramid();

    Camera camera = Camera.perspective(mSize.x, mSize.y);
    final Real param = Parameter.real(0);
    Real angle = param.mul(50).radians();

    Vector4 cubePosition = camera
        .apply(Shade.translate(1.5f, 0, -12))
        .apply(Shade.rotate(angle, Shade.vec(1, 1, 1)))
        .apply(cubeModel.getVertices4());

    Vector4 pyramidPosition = camera
        .apply(Shade.translate(-1.5f, 0, -12))
        .apply(Shade.rotate(angle, Shade.vec(0, 1, 0)))
        .apply(pyramidModel.getVertices4());

    scene
        .add(cubeModel.bake(cubePosition, cubeModel.getColors3()))
        .add(pyramidModel.bake(pyramidPosition, pyramidModel.getColors3()))
        .add(new Runnable() {
          @Override
          public void run() {
            Parameter.set(param, (float) SystemClock.uptimeMillis() / 1000);
          }
        });
  }

  private void prepareLesson6(Scene scene) {
    Geometry cube = Models.flatCube();

    Camera camera = Camera.perspective(
        new LookAtConfig()
            .setEye(Shade.vec(0, 0, 12))
            .setCenter(Shade.vec(0, 0, -1))
            .setUp(Shade.vec(0, 1, 0)), mSize.x, mSize.y);

    final Real param = Parameter.real(0);
    Real angle = param.mul(50).radians();

    Vector4 cubePosition = camera
        .apply(Shade.rotate(angle, Shade.vec(1, 1, 1)))
        .apply(cube.getVertices4());


    Bitmap texture = BitmapFactory.decodeResource(getResources(), R.drawable.crate);
    Vector4 cubeColor = Shade.texture2(texture, cube.getTexCoords2());

    scene
        .add(cube.bake(cubePosition, cubeColor))
        .add(new Runnable() {
          @Override
          public void run() {
            Parameter.set(param, (float) SystemClock.uptimeMillis() / 1000);
          }
        });
  }

  private void prepareLesson7(Scene scene) {
    Geometry cube = Models.flatCube();

    Camera camera = Camera.perspective(
        new LookAtConfig()
            .setEye(Shade.vec(0, 0, 12))
            .setCenter(Shade.vec(0, 0, -1))
            .setUp(Shade.vec(0, 1, 0)), mSize.x, mSize.y);

    final Real param = Parameter.real(0);
    Real angle = param.mul(50).radians();

    Transform4 modelTransform = Shade.rotate(angle, Shade.vec(1, 1, 1));
    scene.add(
        cube.bake(camera.apply(modelTransform).apply(cube.getVertices4()), light(cube, modelTransform)))
        .add(new Runnable() {
          @Override
          public void run() {
            Parameter.set(param, (float) SystemClock.uptimeMillis() / 1000);
          }
        });
  }

  private Vector4 light(Geometry cube, Transform4 modelTransform) {
    Bitmap texture = BitmapFactory.decodeResource(getResources(), R.drawable.crate);
    Vector4 materialColor = Shade.texture2(texture, cube.getTexCoords2());

    Vector3 lightPosition = Shade.vec(0, 0, 5);

    Vector3 fragPosition = modelTransform.apply(cube.getVertices4()).x().y().z().get();
    Vector3 normal = modelTransform.apply(cube.getNormals4()).x().y().z().get();

    Real diffuse = lightPosition.sub(fragPosition).normalize().dot(normal);

    return materialColor.mul(diffuse);
  }
}
