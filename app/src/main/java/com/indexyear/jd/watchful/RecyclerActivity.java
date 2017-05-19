package com.indexyear.jd.watchful;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.util.Base64;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RecyclerActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerActivity: ";

    Intent incomingIntent = getIntent();
    //String searchString = incomingIntent.getStringExtra("EXTRA_MESSAGE");

    Context context;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recylerViewLayoutManager;

    Map<String, String> notTweets = new HashMap<String, String>();
    String url = "http://www.critaholic.com/static/boring.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        requestWindowFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_recycler);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getsupportactionbar().setdisplayhomeasupenabled(true); //under toolbar declaration
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RequestQueue rQueue = Volley.newRequestQueue(context);

        //Debugging connectivity issues
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting()){
            Log.d(TAG, "internet is working!");
        }


        //start the request process here
        //maybe make this a json request?
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response){
                        Log.d(TAG, "Request worked!");
                        //make the response json, parse out list of 'user' and 'text' info
                        processJsonArray(response);
                        recyclerViewAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Request didn't work! " + error.getMessage());
            }
        });

        //queue up request
        rQueue.add(jsonRequest);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout1);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recylerViewLayoutManager = new LinearLayoutManager(context);

        recyclerView.setLayoutManager(recylerViewLayoutManager);

        //Log.d(TAG, "Size of notTweets is " + notTweets.size());

        recyclerViewAdapter = new RecyclerViewAdapter(context, notTweets);

        recyclerView.setAdapter(recyclerViewAdapter);

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

    private void processJsonArray(JSONArray response){
        Log.d(TAG, "processJsonArray");

        for(int i=0; i<response.length(); i++){
            JSONObject userTextPair = null;
            try {
                userTextPair = response.getJSONObject(i);
                notTweets.put(userTextPair.getString("title"), userTextPair.getString("body"));
                Log.d(TAG, "put the pair");
            } catch (JSONException e){
                Log.d(TAG, e.getMessage());
            }
        }
    }
}
