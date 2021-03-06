package com.indexyear.jd.watchful;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Homework requirement.
 */

public class GridCardAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] mCardStrings;

    public GridCardAdapter(Context c) {
        mContext = c;
        Resources res = mContext.getResources();
        mCardStrings = res.getStringArray(R.array.grid_button_strings);
    }

    public int getCount() {
        return mCardStrings.length;
    }

    // todo what is this meant to accomplish
    public Object getItem(int position) {
        return null;
    }

    // todo what is this meant to return
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View card;
        TextView cardText;
        final int passPosition = position + 1;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            card = new View(mContext);
            card = LayoutInflater.from(mContext).inflate(R.layout.cardview_items,parent,false);
            //card.setLayoutParams(new GridView.LayoutParams(300, 300));
            //appButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
            card.setPadding(8, 8, 8, 8);
        } else {
            card = (View) convertView;
        }

        cardText = (TextView) card.findViewById(R.id.title);

        cardText.setText(mCardStrings[position]);

        card.setOnClickListener(new View.OnClickListener() {
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

        return card;
    }
}
