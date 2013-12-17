package com.lfscheidegger.jfacet.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.R;

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
        "Lesson 0",
        "Lesson 2",
        "Lesson 3",
        "Lesson 4",
        "Lesson 5",
        "Lesson 6",
        "Lesson 7"
    )));

    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(JFacetLandingActivity.this, JFacetDemoActivity.class);
        intent.putExtra("lesson", position);

        startActivity(intent);
      }
    });
  }
}
