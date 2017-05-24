package com.indexyear.jd.watchful;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.Window;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.indexyear.jd.watchful.adapters.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * List of (soon to be) tweets
 * todo Twitter auth
 * todo twitter rest api client
 * todo ensure that scrolling is endless
 * todo username picture, tap takes to analysis (static for now)
 */

public class RecyclerActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerActivity: ";
    public static final String EXTRA_MESSAGE = "com.indexyear.jd.watchful.MESSAGE";

    Context context;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recylerViewLayoutManager;
    final String url = "http://dev.critaholic.com/static/boring_json.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(TAG, "onCreate");

        Intent incomingIntent = getIntent();
        String searchString = incomingIntent.getStringExtra(EXTRA_MESSAGE);

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_recycler);
        context = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //address intellij warning

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        recylerViewLayoutManager = new LinearLayoutManager(context);

        recyclerView.setLayoutManager(recylerViewLayoutManager);

        if(isNetworkUp()){
            JsonArrayRequest boringJsonRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    try {
                        String[] keys = new String[response.length()];
                        String[] values = new String[response.length()];

                        JSONObject item;
                        for(int idx = 0; idx<response.length(); idx++){
                            item = response.getJSONObject(idx);
                            keys[idx] = item.getString("title");
                            values[idx] = item.getString("body");
                        }

                        RecyclerViewAdapter recycAdapter = new RecyclerViewAdapter(getApplicationContext(), keys, values);
                        recyclerView.setAdapter(recycAdapter);
                    } catch (JSONException e) {
                        Log.d(TAG, "JSONException: " + e.getMessage());
                    }
                    Log.d(TAG, "Connection successful!");
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RecyclerActivity.this, "" + error,
                            Toast.LENGTH_SHORT).show();

                }
            });

            RequestQueueSingleton.getInstance(this).addToRequestQueue(boringJsonRequest);
        } else {
            Toast.makeText(RecyclerActivity.this, "Network unavailable",
                    Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.w(TAG, "onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
        menu.findItem(R.id.action_recycler).setVisible(false);

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

    public boolean isNetworkUp(){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

    }

    public void retrieveTwitterInfo(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String results;
        JSONObject timeline;

        String authInfo = encodeAuthInfo();

        twitterAuth(authInfo, queue);
        retrieveSearchResults(queue);

    }

    public String encodeAuthInfo(){
        String authString = /*R.string.twitter_api + */":"/* + R.string.twitter_secret*/;
        return Base64.encodeToString(authString.getBytes(), Base64.DEFAULT);
    }

    public void twitterAuth(String authInfo, RequestQueue queue){
        String url = "https://api.twitter.com/oauth2/token";
        StringRequest tokenRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Auth success!");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "The auth request didn't work! Message: " + error.getMessage());
            }
        });
    }

    public String[] retrieveSearchResults(RequestQueue queue){
        String[] empty = {"", ""};
        return empty;
    }

}
