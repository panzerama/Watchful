package com.indexyear.jd.watchful;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import static android.R.id.list;

public class UserTimelineActivity extends ListActivity {
    private static final String TAG = "UserTimelineActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("twitterdev")
                .build();

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();

        if(adapter != null){
            Log.d(TAG, "adapter exists");
        }

        setListAdapter(adapter);
    }

}
