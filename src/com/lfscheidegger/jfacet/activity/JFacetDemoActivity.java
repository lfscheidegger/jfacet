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
import com.lfscheidegger.jfacet.shade.camera.OrthographicConfig;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Real;
import com.lfscheidegger.jfacet.shade.expression.Vector2;
import com.lfscheidegger.jfacet.shade.expression.Vector3;
import com.lfscheidegger.jfacet.shade.expression.Vector4;
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
      case 7: prepareLesson8(scene); break;
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

    Vector3 colors = Shade.vec(0, triangleModel.getColors3().x().y().get());
    scene.add(triangleModel.bake(triangleModel.getVertices2(), colors));
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

    float aspectRatio = mSize.y / (float)mSize.x;

    Camera camera = Camera.ortho(
        new LookAtConfig()
            .setEye(Shade.vec(0, 0, 0))
            .setCenter(Shade.vec(0, 0, -1))
            .setUp(Shade.vec(0, 1, 0)),
        new OrthographicConfig()
            .setLeft(-2).setRight(2)
            .setBottom(-2).setTop(2)
            .setNear(-2).setFar(2));

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

  private void prepareLesson8(Scene scene) {
    float aspectRatio = mSize.y / (float)mSize.x;

    float scale = 1;
    Camera camera = Camera.ortho(
        new LookAtConfig(),
        new OrthographicConfig()
            .setLeft(Shade.constant(-scale)).setRight(Shade.constant(scale))
            .setBottom(Shade.constant(scale * -aspectRatio)).setTop(Shade.constant(scale * aspectRatio))
            .setNear(Shade.constant(-scale)).setFar(Shade.constant(scale)));

    Geometry plane = new Geometry(
        new int[] {0, 1, 2, 0, 2, 3},
        new float[] {
            -2, -2 * aspectRatio,
            2, -2 * aspectRatio,
            2, 2 * aspectRatio,
            -2, 2 * aspectRatio}, 2);

    Vector3 origin = Shade.vec(plane.getVertices2(), 1);
    Vector3 direction = Shade.vec(0, 0, -1);
    Real radius = Shade.constant(1);

    Real A = Shade.constant(1);
    Real B = Shade.constant(2).mul(direction.dot(origin));
    Real C = origin.dot(origin).sub(radius.mul(radius));

    Real discriminant = B.mul(B).sub(Shade.constant(4).mul(A).mul(C));
    Bool isDiscriminantNegative = discriminant.isLessThan(0);

    Real q = B.isLessThan(0)
        .if_(B.neg().add(discriminant.sqrt()).div(2))
        .else_(B.neg().sub(discriminant.sqrt()).div(2));

    // t0 < t1
    Real t0 = C.div(q);

    final Real param = Parameter.real(0);

    Vector3 position = origin.add(direction.mul(t0));
    Transform4 surfaceRotation = getRotation(param);
    Transform4 cloudRotation = getRotation(param.mul(0.95f));

    Vector2 surfaceTexCoords = positionToLatLng(surfaceRotation.apply(Shade.vec(position, 1)));
    Vector2 cloudTexCoords = positionToLatLng(cloudRotation.apply(Shade.vec(position, 1)));

    Bitmap surfaceTexture = BitmapFactory.decodeResource(getResources(), R.drawable.earth);
    Vector4 surfaceColor = Shade.texture2(surfaceTexture, surfaceTexCoords);

    Bitmap clouds = BitmapFactory.decodeResource(getResources(), R.drawable.cloud_combined_2048);
    Vector4 cloudColor = Shade.texture2(clouds, cloudTexCoords).x().x().x().x();

    Bitmap normals = BitmapFactory.decodeResource(getResources(), R.drawable.earth_normal);
    Vector3 normal = Shade.texture2(normals, surfaceTexCoords).x().y().z().get().sub(Shade.vec(.5f, .5f, .5f)).normalize();

    Bitmap specularHighlights = BitmapFactory.decodeResource(getResources(), R.drawable.earthspec__jestr);
    Real specularity = Shade.texture2(specularHighlights, surfaceTexCoords).x().get();
    specularity = specularity.mul(100);

    Transform4 normalTransform = getNormalTransform(position);
    normal = normalTransform.apply(Shade.vec(normal, 1)).x().y().z().get();

    Vector3 light = Shade.vec(-2, -2, -2).normalize();

    Real diffuse = light.neg().dot(normal).max(0);
    Real specular = light.reflect(normal).normalize().dot(direction.neg()).pow(specularity).max(0);

    Vector4 color = surfaceColor.mul(diffuse.add(specular)).add(cloudColor.mul(diffuse));
    color = isDiscriminantNegative.if_(Shade.vec(0, 0, 0, 0)).else_(color);

    scene
        .add(plane.bake(camera.apply(plane.getVertices4()), color))
        .add(new Runnable() {
          @Override
          public void run() {
            Parameter.set(param, Parameter.get(param) + 0.005f);
          }
        });

  }

  private Vector2 positionToLatLng(Vector4 position) {
    Real lat = position.getY().acos().div(Real.PI);
    Real lng = (position.getX().atan(position.getZ())).div(Real.PI.mul(2)).mod(1);

    return Shade.vec(lng, lat);
  }

  private Transform4 getRotation(Real angle) {
    return Shade.rotate(angle, Shade.vec(0, 1, 0)).apply(Shade.rotate(.4f, Shade.vec(1, 1, 1)));
  }

  private Transform4 getNormalTransform(Vector3 position) {
    Vector3 sphereNormal = position;
    Vector3 planeNormal = Shade.vec(0, 0, 1);

    Real angle = sphereNormal.dot(planeNormal).acos();

    Vector3 axis = planeNormal.cross(sphereNormal);//sphereNormal.cross(planeNormal);

    return Shade.rotate(angle, axis);
  }
}
