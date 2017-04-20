package com.indexyear.jd.watchful;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.util.Log;

public class UsernameActivity extends AppCompatActivity {

    private static final String TAG = "UsernameActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.w(TAG, "onCreateAction");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get the intent that started this activity
        Intent intent = getIntent();
        String message = intent.getStringExtra(WelcomeActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.textView4);
        textView.setText(message);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
