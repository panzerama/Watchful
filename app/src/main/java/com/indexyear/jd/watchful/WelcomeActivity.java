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

import com.indexyear.jd.watchful.fragments.FABAlertFragment;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;

import io.fabric.sdk.android.Fabric;

/**
 * Main activity for watchful.
 * todo wire up username to require twitter username (@ optional)
 * todo load user settings from firebase? keep a list of past tweets
 * todo unify app theme
 */

public class WelcomeActivity extends AppCompatActivity{

    private TwitterLoginButton loginButton;
    private static final String TAG = "WelcomeActivity: ";
    public static final String EXTRA_MESSAGE = "com.indexyear.jd.watchful.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(getString(R.string.twitter_key), getString(R.string.twitter_secret));
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.activity_welcome);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                Log.d(TAG, "Twitter Login Success.");
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Log.d(TAG, "Your login is bad and you should feel bad.");
            }
        });

        //todo make this button an info or menu
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FABAlertFragment newFragment = new FABAlertFragment();
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
            return true; //todo replace this with a settings menu, esp as you integrate firebase for storing user info HW 6
        } else if (id == R.id.action_about){
            intent.setClass(this, AboutActivity.class);
        } else if (id == R.id.action_grid) {
            intent.setClass(this, GridCardActivity.class);
        } else if (id == R.id.action_recycler){
            intent.setClass(this, RecyclerActivity.class);
            /*EditText editText = (EditText) findViewById(R.id.username_search);
            String username = (editText.getText().toString().length() > 0) ? editText.getText().toString() : "defaultusername";
            Log.d(TAG, "usernamesearch = " + username);

            intent.putExtra(EXTRA_MESSAGE, username);*/
        }

        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, RecyclerActivity.class);
        /*EditText editText = (EditText) findViewById(R.id.username_search);
        String username = (editText.getText().toString().length() > 0) ? editText.getText().toString() : "defaultusername";
        Log.d(TAG, "usernamesearch = " + username);

        intent.putExtra(EXTRA_MESSAGE, username);*/

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

}
