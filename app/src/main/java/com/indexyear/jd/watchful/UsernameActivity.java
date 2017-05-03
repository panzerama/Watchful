package com.indexyear.jd.watchful;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

public class UsernameActivity extends AppCompatActivity {

    private static final String TAG = "UsernameActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.w(TAG, "onCreateAction");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_username);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ButtonAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(UsernameActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
