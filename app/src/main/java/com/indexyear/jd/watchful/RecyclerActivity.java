package com.indexyear.jd.watchful;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerActivity: ";

    Intent incomingIntent = getIntent();
    //todo this was throwing an error repeatedly. move on and come back to it
    //String searchString = incomingIntent.getStringExtra("EXTRA_MESSAGE");

    Context context;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recylerViewLayoutManager;
    final String url = "http://dev.critaholic.com/static/boring_json.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_recycler);
        context = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //address intellij warning

        /*
        setContentView(R.layout.activity_category_result);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
         */



        //relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout1);
        //todo is this needed?

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        recylerViewLayoutManager = new LinearLayoutManager(context);

        recyclerView.setLayoutManager(recylerViewLayoutManager);

        if(/* make a network test thing*/ true){
            //invoke requestqueuesingleton here
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
            //recyclerViewAdapter = new RecyclerViewAdapter(context, leftStrings, rightStrings);
            //recyclerView.setAdapter(recyclerViewAdapter);
        } else {
            Toast.makeText(RecyclerActivity.this, "Network unavailable",
                    Toast.LENGTH_SHORT).show();
        }



    }

    public class Atom{ //todo refactor
        private String key;
        private String value;

        public Atom(String key, String value){
            this.key = key;
            this.value = value;
        }

        public String getKey() { return key; }
        public String getValue(){ return value; }
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

    String[] leftStrings = {"j6BSQMKwsM", "yR632nnnve", "zx99PkfjOg", "cfR2AurdYl", "W3W9T7G8xX",
            "Hzti5z9LCl", "MFQYmrc7Hw", "irj7yDcL3Y", "Jl9sz5KS1m", "rG1ogatsso", "JRNlkwtRbO",
            "HIOriyeXTf", "4L9oZoPgMM", "bMNh5bXnF9", "DnWMWSwYDc", "G6X9R5dnmR", "Kun6oAyEfh",
            "Rxd7oq9cfe", "P7qlydlkI4", "CXIxJIcacv", "cDIXKNv5My", "QIxwGOO5Oc", "aIabanZOhY",
            "91oXWjZW62", "2LV4v9NGJi", "flLK0XVfl9", "SiHghOamvO", "EQX1jByuG1", "2lbte5Ag0V",
            "B3j5SV9s41", "GeORaOyIzN", "TKsIGofqeq", "19UmmLwHKd", "ULcN0M4sqs", "ewm8TOUQXr",
            "s6f5K5dQvE", "BQkMqwGXLn", "tBdPI27Aei", "sbudLVTv7k", "4mItZuw2dh", "LKVHjEHzuu",
            "iiKq0jbO1J", "egk6LnXMvp", "qW8ztsfcNB", "s52MlwSG44", "QH9iL2VfSL", "IyhNufjWLQ",
            "DBWxhqCMwp", "zedta6goyO", "opjgAM51Tq"};

    String[] rightStrings = {"yeDTw3dlhV", "llX0sqGKvu", "K3YHF1Qsrt", "SO0VcbpFnv", "kTeQwwLWob",
            "m7IVFA5EY6", "8WfIZjL78K", "sXScgdwlKc", "k8FpdCiXir", "cHWBlsH5Ok", "I639TWTr3g",
            "SUggTdtJCL", "eMsSt4Da8L", "9dIrq7FMqw", "huAH4X6fbI", "DZ5EjrN9dM", "CM17cs1fIc",
            "k2jRKOoziF", "4meF8MgWkC", "9dHhfDq3zF", "Pdg3yPDvuK", "JnbvVxJcdj", "DDYIbxD4X5",
            "vtjr9XFi68", "Rt9r4DOYpz", "qlFydfzjSr", "k4g6zutD0T", "9QwBLumQdi", "Ec6bLC0xUz",
            "uYSmeQYAq1", "28vRLWXU8H", "wF7qz3jfxQ", "2IWTicKjBJ", "I0s1OTZPmH", "PESol9xBH0",
            "gGMSLmlFVE", "EfNbUsEwpJ", "6kc75d4F2V", "FRJVBWN0Be", "TJc3mF1L9g", "2wshrnfTIj",
            "zQLWFVEWqb", "I07iGs6mvS", "KUEE0l8BSh", "9gREf6zw24", "0nwyJErSAO", "2E1zCN9bHZ",
            "TViUd2qeO2", "TpOiolQr3x", "atV6sAOerR"};

}
