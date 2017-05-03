package com.indexyear.jd.watchful;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by jd on 5/3/17.
 */

public class ButtonAdapter extends BaseAdapter {
    private Context mContext;

    public ButtonAdapter(Context c){
        mContext = c;
    }

    public int getCount() {
        return mButtonsList.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Button appButton;

        if (convertView == null){
            appButton = new Button(mContext);
            appButton.setLayoutParams(new GridView.LayoutParams(85, 85));
            appButton.setPadding(5,5,5,5);
        } else {
            appButton = (Button) convertView;
        }

        appButton.setText(mButtonsList[position]);
        appButton.setId(position);
        return appButton;
    }

    private String[] mButtonsList = {
            "Thing1",
            "Thing2",
            "Thing3",
            "Thing4"
    };
}

