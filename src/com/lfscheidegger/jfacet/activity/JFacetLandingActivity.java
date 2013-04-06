package com.lfscheidegger.jfacet.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.*;
import com.google.common.collect.ImmutableList;
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

import java.util.List;

public class JFacetLandingActivity extends Activity {

  private static final class DemoSelectorAdapter extends ArrayAdapter<String> {

    public DemoSelectorAdapter(
        Context context,
        int resourceId,
        List<String> objects) {
      super(context, resourceId, objects);
    }
  }

  private ListView mListView;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.landing);

    mListView = (ListView)findViewById(R.id.list_view);
    mListView.setAdapter(new DemoSelectorAdapter(this, R.layout.landing_item, ImmutableList.<String>of(
        "Lesson 2",
        "Lesson 3",
        "Lesson 4"
    )));

    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(JFacetLandingActivity.this, JFacetDemoActivity.class);
        intent.putExtra("lesson", position);

        startActivity(intent);
      }
    });

    /**mSurfaceView = (GLSurfaceView) findViewById(R.id.gl_view);

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
     */
  }

  /*@Override
  public void onResume() {
    super.onStart();

    mSurfaceView.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();

    mSurfaceView.onPause();
  }*/
}
