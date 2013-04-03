package com.lfscheidegger.jfacet.activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import com.lfscheidegger.jfacet.R;
import com.lfscheidegger.jfacet.facet.*;
import com.lfscheidegger.jfacet.facet.renderer.FacetRenderer;
import com.lfscheidegger.jfacet.shade.Shade;

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

    Geometry geometry = Facet.model(
        ModelType.TRIANGLES,
        new float[] {0, 0, 1, 0, 1, 1});
    Drawable drawable = Facet.bake(geometry, Shade.attribute2f(geometry.getPositionBuffer()));

    scene.add(drawable);
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
