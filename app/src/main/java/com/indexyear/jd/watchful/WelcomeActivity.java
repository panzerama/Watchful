package com.indexyear.jd.watchful;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.EditText;

public class WelcomeActivity extends AppCompatActivity{

    private static final String TAG = "WelcomeActivity: ";
    public static final String EXTRA_MESSAGE = "com.indexyear.jd.watchful.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //todo make this button thing something useful
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetweetFragment newFragment = new RetweetFragment();
                newFragment.show(getSupportFragmentManager(), "missiles");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.w(TAG, "onCreateOptionsMenu");

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.w(TAG, "onOptionsItemSelected");

        int id = item.getItemId();
        Intent intent = new Intent(this, WelcomeActivity.class);

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_about){
            intent.setClass(this, AboutActivity.class);
        } else if (id == R.id.action_grid) {
            intent.setClass(this, GridCardActivity.class);
        } else if (id == R.id.action_recycler){
            intent.setClass(this, RecyclerActivity.class);
            EditText editText = (EditText) findViewById(R.id.username_search);
            String username = editText.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, username);

        }

        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

}
