package com.indexyear.jd.watchful;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by jd on 5/3/17.
 */

public class ButtonAdapter extends BaseAdapter {
    private Context mContext;

    public ButtonAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mButtonStrings.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        Button appButton;
        final int passPosition = position + 1;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            appButton = new Button(mContext);
            appButton.setLayoutParams(new GridView.LayoutParams(300, 300));
            //appButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
            appButton.setPadding(8, 8, 8, 8);
        } else {
            appButton = (Button) convertView;
        }

        appButton.setText(mButtonStrings[position]);

        appButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(passPosition == 1){
                    //build the intent here that leads to RecyclerView
                    Intent intent = new Intent(mContext, RecyclerActivity.class);
                    mContext.startActivity(intent);
                }
                else {
                    Toast.makeText(mContext, "THING" + passPosition,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return appButton;
    }

    // references to our images
    private String[] mButtonStrings = {
            "Thing1",
            "Thing2",
            "Thing3",
            "Thing4",
    };
}
