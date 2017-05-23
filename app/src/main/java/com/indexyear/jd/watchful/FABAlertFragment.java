package com.indexyear.jd.watchful;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;

/* homework requirement */


public class FABAlertFragment extends DialogFragment {

    final String TAG = "FABAlertFragment";

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final ArrayList mSelectedPreferences = new ArrayList();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Boom")
                .setMessage("Yes or no?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d(TAG, "Yes");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d(TAG, "Yes");
                    }
                });

        return builder.create();
    }
}
