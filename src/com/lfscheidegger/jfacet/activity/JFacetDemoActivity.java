package com.lfscheidegger.jfacet.activity;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import com.lfscheidegger.jfacet.R;
import com.lfscheidegger.jfacet.facet.*;
import com.lfscheidegger.jfacet.facet.renderer.FacetRenderer;
import com.lfscheidegger.jfacet.shade.camera.Camera;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector2;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector3;
import com.lfscheidegger.jfacet.shade.expression.vector.Vector4;
import com.lfscheidegger.jfacet.shade.transform.Translation4;
import com.lfscheidegger.jfacet.view.FacetView;

import java.util.Vector;

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
      //case 5: prepareLesson7(scene); break;
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
    //new Vector3(
    //    triangleModel.getVertices2().getX(),
    //    triangleModel.getVertices2().getY(), new Real(1)));

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

    Vector4 squarePosition = camera.apply(new Translation4(1.5f, 0, -12)).apply(squareModel.getVertices4());
    Vector4 trianglePosition = camera.apply(new Translation4(-1.5f, 0, -12)).apply(triangleModel.getVertices4());

    Drawable square = squareModel.bake(squarePosition, new Vector3(1, 1, 1));
    Drawable triangle = triangleModel.bake(trianglePosition, new Vector3(1, 1, 1));

    scene.add(square);
    scene.add(triangle);
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

    Drawable square = squareModel.bake(squarePosition, new Vector3(0.5f, 0.5f, 1));
    Drawable triangle = triangleModel.bake(trianglePosition, triangleModel.getColors3());

    scene.add(square);
    scene.add(triangle);
  }

  private void prepareLesson4(Scene scene) {
    /*Geometry squareModel = Facet.model(new GeometryConfig(
        new int[] {0, 1, 2, 0, 2, 3},
        new float[] {-1, -1, 1, -1, 1, 1, -1, 1}, 2
    )), triangleModel = Facet.model(new GeometryConfig(
        new int[] {0, 1, 2},
        new float[] {0, 1, -1, -1, 1, -1}, 2)
        .setColors(new float[] {1, 0, 0, 0, 1, 0, 0, 0, 1}, 3));

    Camera camera = Camera.perspective(mSize.x, mSize.y);
    Real angle = Parameter.now().mul(50).radians();

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
    scene.add(triangle);*/
  }

  private void prepareLesson5(Scene scene) {
    /*Geometry cubeModel = Facet.model(new GeometryConfig(
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
        new int[]{
            0, 1, 2,
            0, 2, 3,
            0, 3, 4,
            0, 4, 1},
        new float[]{
            0, 1, 0,
            -1, -1, 1,
            -1, -1, -1,
            1, -1, -1,
            1, -1, 1}, 3)
        .setColors(new float[]{
            1, 0, 0,
            0, 1, 0,
            0, 0, 1,
            0, 1, 0,
            0, 0, 1
        }, 3));

    Camera camera = Camera.perspective(mSize.x, mSize.y);
    Real angle = Parameter.now().mul(50).radians();

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
    scene.add(Facet.bake(pyramidModel, pyramidPosition, pyramidModel.getColors()));*/
  }

  private void prepareLesson6(Scene scene) {
    /*Geometry cube = Models.flatCube();

    Camera camera = Camera.perspective(
        new LookAtConfig()
            .setEye(Shade.vec(0, 0, 12))
            .setCenter(Shade.vec(0, 0, -1))
            .setUp(Shade.vec(0, 1, 0)), mSize.x, mSize.y);

    Real angle = Parameter.now().mul(50).radians();

    scene.add(Facet.bake(
        cube,
        camera.apply(Shade.rotation(angle, Shade.vec(1, 1, 1))).apply(cube.getVertices()),
        Shade.texture2D(Facet.texture(getResources(), R.drawable.nehe), cube.getTexCoords())));*/
  }

  /*private void prepareLesson7(Scene scene) {
    Geometry cube = Models.flatCube();

    Camera camera = Camera.perspective(
        new LookAtConfig()
            .setEye(Shade.vec(0, 0, 12))
            .setCenter(Shade.vec(0, 0, -1))
            .setUp(Shade.vec(0, 1, 0)), mSize.x, mSize.y);

    Real angle = Parameter.now().mul(50).radians();

    Transform modelTransform = Shade.rotation(angle, Shade.vec(1, 1, 1));
    scene.add(Facet.bake(
        cube,
        camera.apply(cube.getVertices()),
        light(cube)));
  }

  private Vec4Exp light(Geometry cube) {//}, Transform modelTransform) {
    Vec4Exp materialColor = Shade.texture2D(Facet.texture(getResources(), R.drawable.crate), cube.getTexCoords());

    Vec4Exp ambientLight = Shade.vec(0.1f, 0.1f, 0.1f, 0.1f);

    Vec3Exp lightPosition = Shade.vec(2, 2, 2);

    Vec3Exp fragPosition = Shade.varying3f((Vec3Exp) cube.getVertices());
    Vec3Exp normal = (Vec3Exp)cube.getNormals();

    Real diffuse = lightPosition.sub(fragPosition).dot(normal);

    return materialColor.mul(ambientLight).add(materialColor.mul(diffuse));
  }   */
}
