package com.indexyear.jd.watchful;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

public class GridCardActivity extends AppCompatActivity {

    private static final String TAG = "GridCardActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Instantiate the GridView
        GridView gridview = (GridView) findViewById(R.id.gridview);
        //Do I need a layout manager here, like RecyclerActivity?
        gridview.setAdapter(new GridCardAdapter(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
        menu.findItem(R.id.action_grid).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.w(TAG, "onOptionsItemSelected");

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = new Intent(this, WelcomeActivity.class);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_about){
            intent.setClass(this, AboutActivity.class);
        } else if (id == R.id.action_grid) {
            intent.setClass(this, GridCardActivity.class);
        } else if (id == R.id.action_recycler){
            intent.setClass(this, RecyclerActivity.class);
        }

        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

}
