package com.lfscheidegger.jfacet.activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import com.lfscheidegger.jfacet.R;
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
    mSurfaceView.setEGLContextClientVersion(2);
    mSurfaceView.setRenderer(new FacetRenderer(Shade.vec(0, 0, 0, 0)));
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
